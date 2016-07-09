package com.supermarket.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

// by tianhu 2014-11-12
public class DownlaodUtils {
	public static final int DOWNLOADUTILS_SUCCESS = 0x2221;
	public static final int DOWNLOADUTILS_FAIL = 0x222;
	private String mFileDir;
	private final int BUFFERSIZE = 1024;
	private Context mContext;
	private String download_rul;
	private Handler mhandler;
	private int mComDownloadId;

	public synchronized void startDownload() {
		new Thread() {
			private HttpURLConnection http;
			private InputStream inStream;
			private RandomAccessFile threadfile;

			@Override
			public void run() {
				try {
					URL downUrl = new URL(download_rul);
					http = (HttpURLConnection) downUrl.openConnection();
					http.setConnectTimeout(5* 1000);
					http.setReadTimeout(5 * 1000);
					http.setRequestMethod("GET");
					http.setRequestProperty(
							"Accept",
							"image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
					http.setRequestProperty("Accept-Language", "zh-CN");
					http.setRequestProperty("Referer", download_rul);
					http.setRequestProperty("Charset", "UTF-8");
					http.setDoInput(true);
					// http.setRequestProperty("Range", "bytes=" + downLength+"-" );
					http.setRequestProperty(
							"User-Agent",
							"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
					http.setRequestProperty("Connection", "Keep-Alive");
					http.connect();
					Log.i("DownloadThreadthwy", "Response");
					int reSponseCode = http.getResponseCode();
					Log.i("DownloadThreadthwy", "reSponseCode:" + reSponseCode);
					if (reSponseCode == 200 || reSponseCode == 206) {
						inStream = http.getInputStream();
						File mdirFile = new File(mFileDir);
						if (!mdirFile.exists())
							mdirFile.mkdirs();
						String filePath = mFileDir + File.separator + getFileName(download_rul);
						threadfile = new RandomAccessFile(filePath, "rwd");
//						threadfile.seek(http.getContentLength());
						byte[] buffer = new byte[BUFFERSIZE];
						int offset = 0;
						while ((offset = inStream.read(buffer, 0, BUFFERSIZE)) != -1) {
							threadfile.write(buffer, 0, offset);
						}
						Message mes = mhandler.obtainMessage();
						mes.what = DOWNLOADUTILS_SUCCESS;
						mes.arg1 = mComDownloadId;
						mes.obj = filePath;
						mhandler.sendMessage(mes);
					} else {
						mhandler.sendEmptyMessage(DOWNLOADUTILS_FAIL);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					mhandler.sendEmptyMessage(DOWNLOADUTILS_FAIL);
				} catch (Exception e) {
					e.printStackTrace();
					mhandler.sendEmptyMessage(DOWNLOADUTILS_FAIL);
				} finally {
					close(http, inStream, threadfile);
				}
				super.run();
			}

			private void close(HttpURLConnection http, InputStream inStream, RandomAccessFile threadfile) {
				try {
					if (null != threadfile) {
						threadfile.close();
						threadfile = null;
					}
					if (null != inStream) {
						inStream.close();
						inStream = null;
					}
					if (null != http) {
						http = null;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	private String getFileName(String downloadUrl) {
		String filename = downloadUrl.substring(downloadUrl.lastIndexOf('/') + 1);
		return filename;
	}

	public void setmFileDir(String mFileDir) {
		this.mFileDir = mFileDir;
	}

	public void setDownload_rul(String download_rul) {
		this.download_rul = download_rul;
	}

	public void setHanlder(Handler basicHandler) {
		mhandler = basicHandler;
	}

	public void setDownload_Id(int comment_id) {
		mComDownloadId = comment_id;
	}
}
