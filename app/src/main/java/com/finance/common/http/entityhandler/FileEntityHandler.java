package com.finance.common.http.entityhandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;


public class FileEntityHandler implements EntityHandler {

	private boolean mStop = false;
	private File mTarget;

	public FileEntityHandler(File target) {
		mTarget = target;
	}

	public FileEntityHandler() {
	}

	public boolean isStop() {
		return mStop;
	}

	public void setStop(boolean stop) {
		this.mStop = stop;
	}

	@Override
	public Object handleEntity(HttpEntity entity) throws IOException {
		return handleEntity(entity, null, mTarget, false);
	}

	public Object handleEntity(HttpEntity entity, EntityCallBack callback,
			File targetFile, boolean isResume) throws IOException {
		// if (TextUtils.isEmpty(target) || target.trim().length() == 0)
		// return null;
		//
		// File targetFile = new File(target);

		if (!targetFile.exists()) {
			targetFile.createNewFile();
		}

		if (mStop) {
			return targetFile;
		}

		long current = 0;
		FileOutputStream os = null;
		if (isResume) {
			current = targetFile.length();
			os = new FileOutputStream(targetFile, true);
		} else {
			os = new FileOutputStream(targetFile);
		}
		try {
			if (mStop) {
				return targetFile;
			}

			InputStream input = entity.getContent();
			long count = entity.getContentLength() + current;

			if (current >= count || mStop) {
				return targetFile;
			}

			int readLen = 0;
			byte[] buffer = new byte[1024];
			while (!mStop && !(current >= count)
					&& ((readLen = input.read(buffer, 0, 1024)) > 0)) {
				os.write(buffer, 0, readLen);
				current += readLen;
				if (callback != null) {
					callback.callBack(count, current, false);
				}
			}
			if (callback != null) {
				callback.callBack(count, current, true);
			}

			if (mStop && current < count) {
				throw new IOException("user stop download thread");
			}
		} finally {
			IOUtils.closeQuietly(os);
		}

		return targetFile;
	}
}
