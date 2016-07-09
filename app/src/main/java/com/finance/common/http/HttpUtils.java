package com.finance.common.http;

import com.finance.common.http.util.AjaxParams;
import com.supermarket.utils.TTLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.IOException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
	private static final String TAG = "TTFM/HttpUtils >>> ";
//	private static ASyncHttpClient httpClient = new ASyncHttpClient();
	    
	/**
	 * 当Scheme 为https时，且为TLS验证，设置为flase 反之为SSL验证
	 * 
	 * @param isSsl
	 */
	public void setHttpsMoth(boolean isSsl) {
		if (!isSsl) {
			try {
				KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);
				SSLSocketFactory ssl = new SSLSocketFactoryEx(trustStore);
				ssl.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				new ASyncHttpClient().configSSLSocketFactory(ssl);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
     
     public static String postSync(String url, Object params, Map<String, String> m) throws IOException {
    	 return (String) new ASyncHttpClient().postSync(getUrl(url, null), new AjaxParams(m));
     }
     
     public static String getSync(String url, Object params) throws IOException {
 		return (String) new ASyncHttpClient().getSync(getUrl(url, params));
 	}
     
     public static String getUrl(String url, Object params) {
 		if (params == null) {
 			TTLog.i(TAG + "url %1$s", url + " currentTime : " + System.currentTimeMillis());
 			return url;
 		}
 		
 		Map<String, String> m = new Gson().typeToType(params,
 				new TypeToken<Map<String, String>>() {
 				}.getType());

 		if (m == null) {
 			m = new HashMap<String, String>();
 		}
 		
 		if (url.contains("?")) {
 			String temp = url.substring(url.indexOf("?") + 1);
 			if (temp.contains("&")) {
 				String[] values = temp.split("&");
 				for (String str : values) {
 					String[] s = str.split("=");
 					m.put(s[0], s[1]);
 				}
 			} else {
 				String[] s = temp.split("=");
 				m.put(s[0], s[1]);
 			}
 		}

		url = ASyncHttpClient.getUrlWithQueryString(url.contains("?") ? url.substring(0, url.indexOf("?")) : url, new AjaxParams(m));
 		TTLog.i(TAG + "getUrl " + url);
 		return url;
 	}
}
