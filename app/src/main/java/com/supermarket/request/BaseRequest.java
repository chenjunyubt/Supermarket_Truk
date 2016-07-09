/**
 * 
 */
package com.supermarket.request;

import com.finance.cache.AccountCache;
import com.finance.common.utils.AppUtils;
import com.google.gson.annotations.Expose;
import com.supermarket.utils.DeviceUtils;

/**
 * @author liqiang
 * @data 2014年8月30日
 *
 */
public class BaseRequest {
	//是否开启缓存
	@Expose
	public boolean isCache = false;
	//缓存时长
	@Expose
	public long invalidTime = 5 * 60 * 1000;
	@Expose
	public String session_key = null;
	@Expose
	public String client = null;
	@Expose
	public String version = null;
	
	public BaseRequest() {
		client = DeviceUtils.getDeviceId();
		version = "android_" + AppUtils.getVersionName();
		session_key = AccountCache.getSessionKey();
	}
	
	protected void setValidTime(long value) {
		invalidTime = value;
	}
}
