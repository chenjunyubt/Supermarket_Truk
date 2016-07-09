/**
 * 
 */
package com.supermarket.response;

import com.google.gson.annotations.Expose;

/**
 * @author liqiang
 * @data 2014年9月10日
 *
 */
public class BaseResponse {
	@Expose
	public int errno;	//错误码，0表示操作成功，否则表示失败
	@Expose
	public String error;	//错误描述
	@Expose
	public String server_time;	//服务器时间戳
	@Expose
	public String server; //服务器标识（用来标识是哪台服务器返回）
}
