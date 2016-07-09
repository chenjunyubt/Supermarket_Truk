package com.supermarket.hprose;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.supermarket.R;
import com.supermarket.utils.TTLog;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hprose.client.HproseHttpClient;
import hprose.common.HproseCallback1;
import hprose.common.HproseErrorEvent;
import hprose.io.HproseClassManager;

/**
 * Created by chenjunyu on 2016-07-09.
 */
public class HproseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hprose);

        ButterKnife.bind(this);

        textHprose();
    }

    @OnClick({R.id.h_btn})
    public void onClickListener(View view){

        switch (view.getId()){
            case R.id.h_btn:
                try {
                    //hprose("js");
                    currentHprose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /*****
     *
     */
    //--------------------------------
    static public enum Sex {
        Unknown, Male, Female, InterSex
    }
    static public class User {
        public String name;
        public Sex sex;
        public Date birthday;
        public int age;
        public boolean married;
    }

    public void textHprose(){
        HproseClassManager.register(User.class, "User");
        final Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HproseHttpClient client = new HproseHttpClient("http://hprose.com/example/index.php");
                client.useService("mmmmm");
                client.invoke("hello", new Object[] {"hprose"}, new HproseCallback1<String>() {
                    @Override
                    public void handler(final String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button.setText(s);
                            }
                        });
                    }
                });
                client.invoke("getUserList", new HproseCallback1<User[]>() {
                            @Override
                            public void handler(final User[] users) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView text = (TextView)findViewById(R.id.textView);
                                        StringBuilder sb = new StringBuilder();
                                        for (int i = 0; i < users.length; i++) {
                                            sb.append("name: ");
                                            sb.append(users[i].name);
                                            sb.append("\r\n");
                                            sb.append("sex: ");
                                            sb.append(users[i].sex);
                                            sb.append("\r\n");
                                            sb.append("birthday: ");
                                            sb.append(users[i].birthday);
                                            sb.append("\r\n");
                                            sb.append("age: ");
                                            sb.append(users[i].age);
                                            sb.append("\r\n");
                                            sb.append("married: ");
                                            sb.append(users[i].married);
                                            sb.append("\r\n");
                                            sb.append("\r\n");
                                        }
                                        text.setText(sb.toString());
                                    }
                                });
                            }
                        },
                        new HproseErrorEvent() {
                            @Override
                            public void handler(final String s, final Throwable throwable) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView text = (TextView)findViewById(R.id.textView);
                                        android.util.Log.e(s, throwable.getMessage(), throwable);
                                    }
                                });
                            }
                        }, User[].class);
                //button.setVisibility(View.GONE);//设置button隐藏不可见
            }
        });

    }
    //-----------------------------------

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    //
    public void currentHprose() throws IOException {
        /*HproseHttpClient client = new HproseHttpClient();
        client.useService("http://10.0.0.100:9090/hprose_demo/Hello");
        String result = (String) client.invoke("sayHello", new Object[]{"Hprose"});
        System.out.println(result);
        result = (String) client.invoke("sayHello", new Object[]{"中国"});
        System.out.println(result);
        System.out.println(client.invoke("add", new Object[]{115, 316}));
*/
    }
    //
    /**
     * hprose
     *
     * @return
     * @throws IOException
     * @throws //NoSuchAlgorithmException
     * @throws //KeyManagementException
     */
    public String hprose(String jsonString) throws IOException {

        HproseHttpClient client = new HproseHttpClient();
        NetHelper(client);
        client.useService("https://192.168.0.250:8443/Hello");
        //
        client.invoke("sayHello", new Object[]{"Hprose"}, new HproseCallback1<String>() {
            @Override
            public void handler(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TTLog.d("HPROSE", "resut: " + s);
                    }
                });
            }
        },new HproseErrorEvent() {
            @Override
            public void handler(final String s, final Throwable throwable) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TTLog.d("HPROSE", "resut: " + s + "---" + throwable.getMessage());
                    }
                });
            }
        });
        //

       /* String result = (String) client.invoke("sayHello",
                new Object[] { "Hprose" });

        TTLog.d("HPROSE", "resut: " + result);
        return result;*/
        return "";
    }

    private void NetHelper(HproseHttpClient client) {
        X509HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
        SSLContext sslContext = null;
        try {
            MyX509TrustManager mtm = new MyX509TrustManager();
            TrustManager[] tms = new TrustManager[] { mtm };

            // 初始化X509TrustManager中的SSLContext
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tms, new java.security.SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 为javax.net.ssl.HttpsURLConnection设置默认的SocketFactory和HostnameVerifier
        if (sslContext != null) {
            // client.setDefaultSSLSocketFactory(sslContext
            // .getSocketFactory());
            client.setSSLSocketFactory(sslContext.getSocketFactory());
        }
        // client.setDefaultHostnameVerifier(hostnameVerifier);
        client.setHostnameVerifier(hostnameVerifier);
    }

    public class MyX509TrustManager implements X509TrustManager {
        X509TrustManager myJSSEX509TrustManager = null;

        public MyX509TrustManager() throws Exception {
            KeyStore ks = KeyStore.getInstance("BKS");
            // ks.load(new FileInputStream("trustedCerts"),
            // "passphrase".toCharArray()); //---->
            // 这是加载自己的数字签名证书文件和密码，在这里这里没有，所以不需要
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
            tmf.init(ks);
            TrustManager tms[] = tmf.getTrustManagers();
            for (int i = 0; i < tms.length; i++) {
                if (tms[i] instanceof X509TrustManager) {
                    myJSSEX509TrustManager = (X509TrustManager) tms[i];
                    return;
                }
            }
        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // sunJSSEX509TrustManager.checkClientTrusted(arg0, arg1);
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // sunJSSEX509TrustManager.checkServerTrusted(arg0, arg1);
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // X509Certificate[] acceptedIssuers = sunJSSEX509TrustManager
            // .getAcceptedIssuers();
            // return acceptedIssuers;
            return null;
        }
    }
}
