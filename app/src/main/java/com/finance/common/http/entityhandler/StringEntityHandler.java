package com.finance.common.http.entityhandler;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class StringEntityHandler implements EntityHandler{
	
	private String mCharset;


	public StringEntityHandler(String charset){
		mCharset = charset;
	}
	
	public StringEntityHandler() {
	}

	@Override
	public Object handleEntity(HttpEntity entity)throws IOException {
		return handleEntity(entity, null, mCharset);
	}

	public Object handleEntity(HttpEntity entity, EntityCallBack callback, String charset)throws IOException {
		Log.d("time", "start dispatch net data : " + System.currentTimeMillis());
		if (entity == null)
			return null;
		
		String comment = EntityUtils.toString(entity, charset);
		Log.d("time", "end dispatch net data : " + System.currentTimeMillis());
		return comment;
//		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8 * 1024];
//
//		long count = entity.getContentLength();
//		long curCount = 0;
//		int len = -1;
//		InputStream is = entity.getContent();
//		while ((len = is.read(buffer)) != -1) {
//			outStream.write(buffer, 0, len);
//			curCount += len;
//			if (callback !=null) {
//	            callback.callBack(count, curCount, false);
//			}
//		}
//		if(callback !=null)
//			callback.callBack(count, curCount, true);
//		byte[] data = outStream.toByteArray();
//		outStream.close();
//		is.close();

//		return new String(data, charset);
	}

}
