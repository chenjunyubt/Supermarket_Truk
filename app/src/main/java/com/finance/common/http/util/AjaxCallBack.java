package com.finance.common.http.util;

/**
 * 
 * @author chenyuxiang
 *
 */
public abstract class AjaxCallBack<T> {
	
	private boolean progress = true;
	private int rate = 1000 * 1;
	
	public boolean isProgress() {
		return progress;
	}
	
	public int getRate() {
		return rate;
	}
	
	public AjaxCallBack<T> progress(boolean progress , int rate) {
		this.progress = progress;
		this.rate = rate;
		return this;
	}

	public void onStart(){};
	/**
	 * @param count
	 * @param current
	 */
	public void onLoading(long count, long current){};
	public void onSuccess(T result){};
	public void onFailure(Throwable t, int errorNo, String strMsg){};
}
