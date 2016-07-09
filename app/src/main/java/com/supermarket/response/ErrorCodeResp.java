/**
 * 
 */
package com.supermarket.response;

import com.google.gson.annotations.Expose;

/**
 * @author liqiang
 * @data 2014年8月21日
 *
 */
public class ErrorCodeResp {
	@Expose
	public int errno;
	@Expose
	public String error;
}
