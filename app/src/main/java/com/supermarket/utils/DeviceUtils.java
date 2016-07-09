package com.supermarket.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.finance.core.TTApplication;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DeviceUtils {
    private static DisplayMetrics dm;
    private static TelephonyManager telephonyManager = null;
    private static ConnectivityManager connManager = null;

    /**
     * 获取当前手机的密度
     * 
     * @return
     */
    public static float getDensity() {
        if (dm == null) {
            dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) TTApplication.getAppContext().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
        }

        return dm.density;
    }
    
    /**
     * 获取手机分辨率 USystem.getScreenResolution()<BR>
     * 
     * @return
     */
    public static String getScreenResolution() {
        if (dm == null) {
            dm = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) TTApplication.getAppContext().getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
        }
        return dm.widthPixels + "X" + dm.heightPixels;
    }

    /**
     * 获取屏幕尺寸 USystem.getScreenSize()<BR>
     * 
     * @param context
     * @return
     */
    public static String getScreenSize(Context context) {
        if (dm == null) {
            dm = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
        }
        double dia = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
        double screenInches = dia / (160 * dm.density);
        return String.format("%.1f", screenInches);
    }

    /**
     * 获取系统版本 DeviceUtils.getSDKVersion()<BR>
     * 
     * @return string
     */
    public static String getAndroidSDKVersion() {
        return android.os.Build.VERSION.SDK;
    }

    /**
     * 获取操作系统的版本号
     * 
     * @return String 系统版本号
     */
    public static String getSysRelease() {
        return android.os.Build.VERSION.RELEASE;
    }
    
    /**
     * 获取手机型号
     * 
     * @return String 手机型号
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机品牌
     * 
     * @return String 手机品牌
     */
    public static String getBrand() {
        return android.os.Build.BRAND;
    }
    
    /**
     *  是否为开发者 调试模式
     *  return boolean true为调试模式
     */
    public static boolean enableAdb(){
    	int adb_enabled = Settings.Secure.getInt(TTApplication.getAppContext().getContentResolver(), Settings.Secure.ADB_ENABLED, 0);
		return adb_enabled > 0;
    }

    /**
     * 
     * 读取手机串号 IMEI
     * 
     * @return String 手机串号
     */
    public static String getTelephoneSerialNum() {
        if (telephonyManager == null) {
            telephonyManager = (TelephonyManager) TTApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        }
        
        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }
        return "";
    }
    
    public static String getDeviceId() {
        try {
        	String temp = getTelephoneSerialNum();
        	if (TextUtils.isEmpty(temp)) {
        		temp = getLocalMacAddress();
        	}
            return signUnionMD5(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static String signUnionMD5(String content) throws Exception {
        String tosign = (content == null ? "" : content);

        return "android_" +  encryption(tosign);
    }
    
    public static String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
 
            int i;
 
            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
				i = b[offset] & 0xff;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
 
            re_md5 = buf.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }
    
    /**
     * 获取手机MAC地址
     * 
     * @return
     */
    public static String getLocalMacAddress() {
        WifiManager wifi = (WifiManager) TTApplication.getAppContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 判断网络是否连接
     * 
     * @param context
     * @return
     */
    public static boolean isNetWork(Context context) {
        if (connManager == null) {
            connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable()) {
            return true;
        }
        return false;
    }

    /**
     * 获取网络类型 USystem.getNetType()<BR>
     * 
     * @return
     */
    public static String getNetType() {
        ConnectivityManager connManager = (ConnectivityManager) TTApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo gprs = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi != null && wifi.getState() == State.CONNECTED) {
            return "WIFI";
        } else if (gprs != null && gprs.getState() == State.CONNECTED) {
            return "3G/GPRS";
        }
        return "no-net";
    }

    /**
     * 读取asset文件
     * 
     * @param context
     * @param filename
     *            文件名
     * @return 文件内容(字符串)
     */
    public static String readFileByAsset(Context context, String filename) {
        String text = "";
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(filename);
            int length = inputStream.available();
            byte[] buffer = new byte[length];
            inputStream.read(buffer);
            text = EncodingUtils.getString(buffer, "UTF-8");
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 判断是否使用小米手机，显示为小米锁屏音乐
     * 比较当前手机使用的版本号是否为 4.4以上版本
     * 同时系统版本在 V6以上版本
     * @return true 支持音乐锁屏
     */
    public static boolean isMIUIMusicShow(){
    	String brand = getBrand();
    	if("Xiaomi".equalsIgnoreCase(brand)){
			String sysRelease = DeviceUtils.getSysRelease().substring(0, 3);
			double release = Double.valueOf(sysRelease);
			
			try{
				String miuiOs = getSystemProperty("ro.miui.ui.version.name");
				String osLever = miuiOs.substring(1, 2);
				int lever = Integer.valueOf(osLever);
				if(release >= 4.4 && lever >= 6){
					return true;
				}
			}catch(Exception e){
				return false;
			}
    	}
		return false;
    }
    
    /**
     * 获取 小米 手机版本  V6以上为支持音乐锁屏
     * @param propName
     * @return
     */
	public static String getSystemProperty(String propName) {
		String line;
		BufferedReader input = null;
		try {
			Process p = Runtime.getRuntime().exec("getprop " + propName);
			input = new BufferedReader(
					new InputStreamReader(p.getInputStream()), 1024);
			line = input.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return line;
	}

}
