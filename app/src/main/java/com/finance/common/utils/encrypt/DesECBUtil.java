package com.finance.common.utils.encrypt;



import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.supermarket.utils.DeviceUtils;



/***
 * DES ECB对称加密 解密
 * @author lqsir
 */
public class DesECBUtil {
	private static String key = "#**$(UdS";
	private static String ENCODING = "UTF-8";
	
	/**
	 * 给定的内容按固定的Key来加密
	 * @param encryptString 需要加密的内容
	 * @return 返回加密后的内容
	 * @author lqsir
	 */
	public static String encryptDES(String encryptString) {
		try {
			return encryptDES(encryptString, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 加密数据
	 * @param encryptString  注意：这里的数据长度只能为8的倍数
	 * @param encryptKey
	 * @return
	 * @throws Exception
	 * YA0yKVwqgJuuuCD6QVYe7g==
	 */
	public static String encryptDES(String encryptString, String encryptKey) throws Exception {
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
		return ConvertUtil.bytesToHexString(encryptedData);
	}
	
	/**
	 * 自定义一个key
	 * @param keyRule
	 */
	public static byte[] getKey(String keyRule) {
		byte[] keyByte = keyRule.getBytes();
		// 创建一个空的八位数组,默认情况下为0
		byte[] byteTemp = new byte[16];
		// 将用户指定的规则转换成八位数组
		for (int i = 0; i < keyByte.length; i++) {
			byteTemp[i] = keyByte[i];
		}
		return byteTemp;
	}
	
	/***
	 * 解密数据
	 * @param decryptString
	 * @param decryptKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptDES(String decryptString, String decryptKey) throws Exception {
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte decryptedData[] = cipher.doFinal(ConvertUtil.hexStringToByte(decryptString));
		return new String(decryptedData);
	}
	
	public static String getSecreteToken(Map<String, String> maps) {
    	String result = "";
    	try {
	    	Map<String, String> posts = maps;
	        List<String> keys = new ArrayList<String>();
	        for (String str : posts.keySet()) {
	            keys.add(str);
	        }
	        Collections.sort(keys);
	
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < keys.size(); i++) {
	            sb.append(URLEncoder.encode(keys.get(i), ENCODING));
	            sb.append("=");
	            sb.append(URLEncoder.encode(posts.get(keys.get(i)), ENCODING));
	            sb.append("&");
	        }
	        
	        result = sb.toString().substring(0, sb.toString().length() -1); 
	        result += "_" + "bw(*ez$@]a.bokLi";
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        return DeviceUtils.encryption(result);
    }
}
