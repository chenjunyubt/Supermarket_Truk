package com.finance.update;

import android.content.IntentFilter;

import com.finance.core.TTApplication;

public class UpdateManager {
	private UpdateBroadCastReceiver broadCast = null;
	private UpdateHandler handler;
	private static UpdateManager manage;
	
	private UpdateManager() {
		handler = new UpdateHandler();
	}
	
	//注册广播  
	public void registerUpdateBroadCast() {
		broadCast = new UpdateBroadCastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
//        counterActionFilter.addAction("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
        filter.addAction("android.intent.action.ACTION_SHUTDOWN");
        
        TTApplication.getAppContext().registerReceiver(broadCast, filter);  
	}
	
	public void unRegisterUpdateBroadCast() {
		TTApplication.getAppContext().unregisterReceiver(broadCast);
	}
	
	/**
	 * 检查更新
	 */
	public void checkUpdate(boolean isShow) {
		new Thread(new UpdateRunnable(isShow, handler)).start();
	}
	
	public static UpdateManager getInstance() {
		if (manage == null) {
			manage = new UpdateManager();
		}
		
		return manage;
	}
	
}
