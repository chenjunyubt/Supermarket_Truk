package com.finance.common.http.entityhandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapEntityHandler implements EntityHandler{

	@Override
	public Object handleEntity(HttpEntity entity) throws IOException {
		handleEntity(entity, null);
		return null;
	}
	
    public Object handleEntity(HttpEntity entity, EntityCallBack callback) throws IOException {
        if (entity == null)
            return null;

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        long count = entity.getContentLength();
        long curCount = 0;
        int len = -1;
        InputStream is = entity.getContent();
        while ((len = is.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
            curCount += len;
            if (callback!=null) {
                callback.callBack(count, curCount, false);
            }
        }
        if(callback!=null)
            callback.callBack(count, curCount, true);
        byte[] data = outStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

        outStream.close();
        is.close();
        return bitmap;
    }

}
