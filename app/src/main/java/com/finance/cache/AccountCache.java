/**
 * 
 */
package com.finance.cache;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.supermarket.utils.PreferUtils;


/**
 * @author liqiang
 * @data 2014年9月15日
 * 
 */
public class AccountCache {
	private static String sessionKey;

	public enum EAccountStatus {
		ACCOUNT_GUEST, ACCOUNT_ORDINARY, ACCOUNT_AUTH, ACCOUNT_PRESENTER, ACCOUNT_BOKE;
	}

	public static EAccountStatus getCurrentAccountStatus(int level) {
		EAccountStatus eAccountStatus;
		switch (level) {
		case 0:// 普通用户
			eAccountStatus = EAccountStatus.ACCOUNT_ORDINARY;
			break;
		case 1:// 实名认证
			eAccountStatus = EAccountStatus.ACCOUNT_AUTH;
			break;
		case 2:// 主持人
			eAccountStatus = EAccountStatus.ACCOUNT_PRESENTER;
			break;
		case 3:// 播客
			eAccountStatus = EAccountStatus.ACCOUNT_BOKE;
			break;
		default:// 游客
			eAccountStatus = EAccountStatus.ACCOUNT_GUEST;
			break;
		}
		return eAccountStatus;
	}

	public static EAccountStatus getLocalAccountStatus() {
		int status = PreferUtils.getInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_VIPLEVEL, -1);
		return getCurrentAccountStatus(status);
	}
	/**
	 * 认证用户
	 * @return
	 */
	public static EAccountStatus getAuthState(){
		int status = PreferUtils.getInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_AUTH_STATE, -1);
		return getCurrentAccountStatus(status);
	}

	public static int getAccountId() {
		return PreferUtils.getInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_USERID, 0);
	}

	/**
	 * 返回当前登录的状态
	 * @return true 表示登录 false 表示未登录 
	 */
	public static boolean isLogin() {
		return !TextUtils.isEmpty(getSessionKey());
	}

	public static void logout() {
		sessionKey = null;
		PreferencesCache.getAccountPrefer().edit().clear().commit();
		PreferencesCache.getFavoritePrefer().edit().clear().commit();
	}

	public static int getMessageAcl() {
		return PreferUtils.getInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_MESSAGE_ACL, 0);
	}
	
	public static void setMessageAcl(int value) {
		PreferUtils.putInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_MESSAGE_ACL, value);
	}

	public static String getSessionKey() {
		if (!TextUtils.isEmpty(sessionKey)) {
			return sessionKey;
		}

		sessionKey = PreferUtils.getString(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_SESSION_KEY);
		return sessionKey;
	}

	public static void setFollowNum(int value) {
		int total = PreferUtils.getInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_FOLLOW_TOTAL, 0);
		if (total == 0 && value < 0) {
			return;
		} else {
			PreferUtils.putInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_FOLLOW_TOTAL, total + value);
		}
	}

	public static void setFansNum(int value) {
		int total = PreferUtils.getInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_FANS_TOTAL, 0);
		if (total == 0 && value < 0) {
			return;
		} else {
			PreferUtils.putInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_FANS_TOTAL, total + value);
		}
	}

	public static int getFollowNum() {
		return PreferUtils.getInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_FOLLOW_TOTAL, 0);

	}

	public static int getFansNum() {
		return PreferUtils.getInt(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_FANS_TOTAL, 0);

	}

	public static void setUserNickname(String nickName) {

		PreferUtils.putString(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_NICKNAME, nickName);
	}

	public static void setUserSign(String sign) {
		PreferUtils.putString(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_SIGN, sign);
	}

	public static void setUserFaceurl(String faceUrl) {
		PreferUtils.putString(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_FACEURL, faceUrl);
	}

	public static String getNickname() {
		return PreferUtils.getString(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_NICKNAME);

	}

	public static String getSign() {
		return PreferUtils.getString(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_SIGN);

	}

	public static String getFaceurl() {
		return PreferUtils.getString(PreferencesCache.getAccountPrefer(), Constants.ACCOUNT_FACEURL);

	}

	/**
	 * phone
	 * 
	 * @return
	 */
	public static void setPhoneNum(String phonenum) {
		PreferUtils.putString(PreferencesCache.getAccountPrefer(), Constants.PHONE_NUM, phonenum);
	}

	public static String getPhoneNUm() {
		return PreferUtils.getString(PreferencesCache.getAccountPrefer(), Constants.PHONE_NUM);
	}

	/**
	 * 首页是否显示tips
	 */
	public static void setTipsStatus(boolean boo){
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.TIPSSTATUA, boo);
	}
	/**
	 * 首页是否显示tips
	 */
	public static Boolean getTipsStatus(){
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.TIPSSTATUA,false);
	}
	
 


}
