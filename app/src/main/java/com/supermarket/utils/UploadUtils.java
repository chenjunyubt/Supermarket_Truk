package com.supermarket.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.finance.cache.AccountCache;
import com.finance.common.utils.AppUtils;
import com.finance.common.utils.encrypt.DesECBUtil;
import com.supermarket.response.UploadFaceResponse;

/**
 * 
 * @author tianhu 2014-11-11
 * 
 */
public class UploadUtils extends Thread {
	public final static int UPLOAG_DATA = 0x7101;// success
	public final static int UPLOAG_FAIL = 0x7102;// fail
	private String face_filePath;
	private String mfileKey;
	private Handler mhandler;
	private String mtype, mUrl;
	private static final String BOUNDARY = UUID.randomUUID().toString(); // 随机生成
																			// 边界标识
	private static final String PREFIX = "--";
	private static final String LINE_END = "\r\n";
	private static final String CONTENT_TYPE = "multipart/form-data"; // 内容类型
	private int requestTime = 0;

	public UploadUtils(String filePath, String fileKey, String type, String Url, Handler handler) {
		face_filePath = filePath;//文件
		mfileKey = fileKey;//audio
		mhandler = handler;
		mtype = type;//cm
		mUrl = Url;
	}

	@Override
	public void run() {
		super.run();
		// 组装post参数
		final Map<String, String> params = new HashMap<String, String>();
		params.put("client", "android_" + DeviceUtils.getTelephoneSerialNum());
		params.put("version", "android_" + AppUtils.getVersionName());
		params.put("session_key", AccountCache.getSessionKey());
		params.put("type", mtype);
		if (mfileKey.equals("pic")) {
			params.put("pic", face_filePath);
		} else {
			params.put("audio", face_filePath);
		}
		params.put("api_sign", DesECBUtil.getSecreteToken(params));

		toUploadFile(mfileKey, mUrl, params, new File(face_filePath));
	}

	private void toUploadFile(String fileKey, String RequestURL, Map<String, String> param, File... file) {
		requestTime = 0;

		long requestTime = System.currentTimeMillis();
		long responseTime = 0;

		try {
			URL url = new URL(RequestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5 * 1000);
			conn.setConnectTimeout(5 * 1000);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", "utf-8"); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
			// conn.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");

			/**
			 * 当文件不为空，把文件包装并且上传
			 */
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			StringBuffer sb = null;

			/***
			 * 以下是用于上传参数
			 */
			if (param != null && param.size() > 0) {
				Iterator<String> it = param.keySet().iterator();
				while (it.hasNext()) {
					sb = null;
					sb = new StringBuffer();
					String key = it.next();
					String value = param.get(key);
					sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
					sb.append("Content-Disposition: form-data; name=\"").append(key).append("\"").append(LINE_END).append(LINE_END);
					sb.append(value).append(LINE_END);
					dos.write(sb.toString().getBytes());
					// dos.flush();
				}
			}

			for (File f : file) {

				sb = null;
				sb = new StringBuffer();
				/**
				 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
				 * filename是文件的名字，包含后缀名的 比如:abc.png
				 */
				sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
				sb.append("Content-Disposition:form-data; name=\"" + fileKey + "\"; filename=\"" + f.getName() + "\"" + LINE_END);
				if (fileKey.equals("pic")) {
					sb.append("Content-Type:image/pjpeg" + LINE_END); // 这里配置的Content-type很重要的
																		// ，用于服务器端辨别文件的类型的
				} else {
					sb.append("Content-Type:audio/mpeg" + LINE_END); // 这里配置的Content-type很重要的
																		// ，用于服务器端辨别文件的类型的
				}
				sb.append(LINE_END);

				dos.write(sb.toString().getBytes());
				/** 上传文件 */
				InputStream is = new FileInputStream(f);
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = is.read(bytes)) != -1) {
					dos.write(bytes, 0, len);
				}
				is.close();

				dos.write(LINE_END.getBytes());
			}
			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
			dos.write(end_data);
			dos.flush();
			
			/**
			 * 获取响应码 200=成功 当响应成功，获取响应的流
			 */
			int res = conn.getResponseCode();
			responseTime = System.currentTimeMillis();
			this.requestTime = (int) ((responseTime - requestTime) / 1000);
			if (res == 200) {
				InputStream input = conn.getInputStream();
				StringBuffer sb1 = new StringBuffer();
				int ss;
				while ((ss = input.read()) != -1) {
					sb1.append((char) ss);
				}
				
				UploadFaceResponse audio = GsonUtil.fromJson(sb1.toString(), UploadFaceResponse.class);
				if (audio.data != null) {
					Message mMes = mhandler.obtainMessage();
					mMes.what = UPLOAG_DATA;
					Bundle bundle = new Bundle();
					bundle.putString("path", audio.data.path);
					bundle.putString("url", audio.data.url);
					mMes.setData(bundle);
					
					mhandler.sendMessage(mMes);
					TTLog.i("Comment_test", "----upload voice success" + audio.data.path);
				} else {
					mhandler.sendEmptyMessage(UPLOAG_FAIL);
				}
				return;
			} else {
				mhandler.sendEmptyMessage(UPLOAG_FAIL);
				return;
			}
		} catch (Exception e) {
			mhandler.sendEmptyMessage(UPLOAG_FAIL);
			e.printStackTrace();
			return;
		}
	}
}
