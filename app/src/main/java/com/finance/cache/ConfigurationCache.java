/**
 * 
 */
package com.finance.cache;

import android.text.TextUtils;

import com.supermarket.utils.PreferUtils;
import com.supermarket.utils.TimeUtils;
import com.supermarket.utils.TimeUtils.TimeFormat;

/**
 * @author liqiang
 * @data 2014年9月13日
 *
 */
public class ConfigurationCache {
	public static void setFirstApp(boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_FIRST, value);
	}
	
	public static boolean getFirstApp() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_FIRST, false);
	}
	
	public static void setFirstUseApp(boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_FIRST_USE, value);
	}
	
	public static boolean getFirstUseApp() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_FIRST_USE, false);
	}
	/**启动页广告图*/
	public static void setAppStartAdvert(String value) {
		PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.APP_START_ADVERT, value);
	}
	
	public static String getAppStartAdvert() {
		return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.APP_START_ADVERT);
	}

	/**启动页广告图_ 点击到查看 外部 url*/
	public static void setAppStartAdvertUrl(String value) {
		PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.APP_START_ADVERT_URL, value);
	}

	public static String getAppStartAdvertUrl() {
		return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.APP_START_ADVERT_URL);
	}
	
	/**启动页广告图显示次数*/
	public static void setAppStartAdvertShowCount(int value) {
		PreferUtils.putInt(PreferencesCache.getConfigurationPrefer(), Constants.APP_START_ADVERT_SHOWCOUNT, value);
	}
	
	public static int getAppStartAdvertShowCount() {
		return PreferUtils.getInt(PreferencesCache.getConfigurationPrefer(), Constants.APP_START_ADVERT_SHOWCOUNT,0);
	}
	
	public static void setTagCacheTime(String flag) {
		PreferUtils.putString(PreferencesCache.getGlobalPrefer(), flag, TimeUtils.getTimeForSpecialFormat(TimeFormat.TimeFormat1));
	}
	
	public static boolean getTagCacheValid(String flag) {
		String result = PreferUtils.getString(PreferencesCache.getGlobalPrefer(), flag);
		return result.equals(TimeUtils.getTimeForSpecialFormat(TimeFormat.TimeFormat1));
	}
	
	public static void setCategoryCache(boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_CATEGORY_CACHE, value);
	}
	
	public static boolean getCategoryCache() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_CATEGORY_CACHE, false);
	}
	
	public static void setCurrentPlayId(String flag, int value) {
		PreferUtils.putInt(PreferencesCache.getConfigurationPrefer(), flag, value);
	}
	
	public static int getCurrentPlayId(String flag) {
		return PreferUtils.getInt(PreferencesCache.getConfigurationPrefer(), flag, 0);
	}

	/**
	 * 设置定时器的开始时间
	 */
	public static void setTimerStartTime(long time) {
		PreferUtils.putLong(PreferencesCache.getConfigurationPrefer(), Constants.PLAY_TIMER_START, time);
	}
	
	/**
	 * 得到定时器的开始时间
	 * @return
	 */
	public static long getTimerStartTime() {
		return PreferUtils.getLong(PreferencesCache.getConfigurationPrefer(), Constants.PLAY_TIMER_START, 0l);
	}
	
	/**
	 * 设置定时器的时长
	 */
	public static void setTimerTimeRangle(int rangleTime) {
		PreferUtils.putInt(PreferencesCache.getConfigurationPrefer(), Constants.PLAY_TIMER_RANGLE, rangleTime);
	}
	
	/**
	 * 得到定时器的时长
	 * @return
	 */
	public static int getTimerTimeRangle() {
		return PreferUtils.getInt(PreferencesCache.getConfigurationPrefer(), Constants.PLAY_TIMER_RANGLE, 0);
	}
	
	/**
	 * 设置下载版本的ID
	 * @param flagId
	 */
	public static void setVersionDownloadId(long flagId) {
		PreferUtils.putLong(PreferencesCache.getConfigurationPrefer(), Constants.VERSION_DOWNLOAD_ID, flagId);
	}
	
	/**
	 * 得到下载版本的ID
	 * @return
	 */
	public static long getVersionDownloadId() {
		return PreferUtils.getLong(PreferencesCache.getConfigurationPrefer(), Constants.VERSION_DOWNLOAD_ID, 0);
	}
	
	public static void setVersionInterrupt(boolean isInterrupt) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.VERSION_INTERRUPT_DOWNLOAD, isInterrupt);
	}
	
	public static boolean getVersionInterrupt() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.VERSION_INTERRUPT_DOWNLOAD, false);
	}
	
	public static void setVersionDownloadDate(String date) {
		PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.VERSION_DOWNLOAD_DATE, date);
	}
	
	public static String getVersionDownloadDate() {
		return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.VERSION_DOWNLOAD_DATE);
	}
	
	public static void  setInstall() {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_COLLECT_INSTALL, true);
//		setTagCacheTime(Constants.CON_COLLECT_INSTALL_TIME);
	}
	
	public static boolean getInstallStatus() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_COLLECT_INSTALL, false);
	}
	
	public static void  setActive() {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_COLLECT_ACTIVE, true);
	}
	/**
	 * 获取是否激活状态
	 * @return
	 */
	public static boolean getActiveStatus() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_COLLECT_ACTIVE, false);
	}
	
	//得到多节目列表选中的节目ID
	public static int getSelectProgramId() {
		return PreferUtils.getInt(PreferencesCache.getConfigurationPrefer(), Constants.PLAY_PROGRAM_ID, 0);
	}
	
	//设置多节目列表选中的节目ID
	public static void setSelectProgramId(int userFmProgramId) {
		PreferUtils.putInt(PreferencesCache.getConfigurationPrefer(), Constants.PLAY_PROGRAM_ID, userFmProgramId);
	}
	
	
	/**
	 * 
	 * 设置广告跳点状态</br>
	 * @param value true or false
	 * @author lqsir
	 */
	public static void setAdvertPointStatus(boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.SET_ADVERT_POINT_STATUS, value);
	}
	
	/**
	 * 
	 * 获取广告跳点状态
	 * @author lqsir
	 */
	public static boolean getAdvertPointStatus() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.SET_ADVERT_POINT_STATUS, false);
	}
	
	/**
	 * 得到当前的播放状态
	 * @return true 表示当前正处于播放状态 false表示播放暂停或未播放过
	 * @author lqsir
	 */
	public static boolean getCurrentPlayState() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_CURRENT_PLAY_STATE, true);
	}
	
	/**
	 * 设置当前的播放状态
	 * @param value true 表示当前正处于播放状态 false 表示播放暂停或未播放过
	 * @author lqsir
	 */
	public static void setCurrentPlayState(boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_CURRENT_PLAY_STATE, value);
	}
	
	/**
	 * 保存当前服务器时间
	 * @param time
	 * @author lqsir
	 */
	public static void setServerTime(long time) {
		PreferUtils.putLong(PreferencesCache.getConfigurationPrefer(), Constants.SERVER_TIME, time);
	}
	
	/**
	 * 获取保存的服务器时间
	 * @return
	 * @author lqsir
	 */
	public static long getServerTime() {
		return PreferUtils.getLong(PreferencesCache.getConfigurationPrefer(), Constants.SERVER_TIME, 0l);
	}
	
	/**
	 * 设置是否支持4G、3G、2G网络播放
	 * @param value true is open, other is false
	 * @author lqsir
	 */
	public static void set4GPlayStatus(boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.SET_ON4G_PLAY, value);
	}
	
	/**
	 * 获取是否开启支持手机网络播放的标志
	 * @author lqsir
	 */
	public static boolean get4GPlayStatus() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.SET_ON4G_PLAY, false);
	}
	/**
	 * 设置是否支持4G、3G、2G网络下下载
	 * @param value true is open, other is false
	 * @author lqsir
	 */
	public static void setIsAudioDownloadStatus(boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.IS_AUDIO_DOWNLOAD, value);
	}
	
	/**
	 * 获取是否在流量下进行下载
	 * @author lqsir
	 */
	public static boolean getIsAudioDownloadStatus() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.IS_AUDIO_DOWNLOAD, false);
	}
	
	public static void setTagInt(String flag,int value){
		PreferUtils.putInt(PreferencesCache.getConfigurationPrefer(), flag, value);
	}
	
	public static int getTagInt(String flag){
		return PreferUtils.getInt(PreferencesCache.getConfigurationPrefer(), flag, 0);
	}
	/**
	 * 设置是否允许网络访问
	 * @param value
	 * @author lqsir
	 */
	public static void setAllowNetWorkStatus(boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_ALLOW_NET, value);
	}
	
	/**
	 * 得到是否允许网络访问
	 * @return
	 * @author lqsir
	 */
	public static boolean getAllowNetWorkStatus() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.CON_ALLOW_NET, false);
	}
	
	/**
	 * 保存当前全局缓存值
	 * @param value
	 * @author lqsir
	 */
	public static void setGlobalCacheValue(String value) {
		PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.CON_GLOBAL_CONFIG, value);
	}
	
	/**
	 * 得到当前全局缓存值
	 * @return
	 * @author lqsir
	 */
	public static String getGlobalCacheValue() {
		return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.CON_GLOBAL_CONFIG);
	}
	
	/**
	 * 设置活动状态，1开启，0不开启
	 * @param status
	 */
	public static void setActivityStatus(int status) {
		PreferUtils.putInt(PreferencesCache.getConfigurationPrefer(), Constants.CON_ACTIVITY_STATUS, status);
	}
	
	/**
	 * 得到活动的状态 true为开启，false不开启
	 * @return
	 */
	public static boolean getActivityStatus() {
		int value = PreferUtils.getInt(PreferencesCache.getConfigurationPrefer(), Constants.CON_ACTIVITY_STATUS, 0); 
		return value == 0 ? false : true;
	}
	
	public static void setFirstAppStartTime(String value) {
		PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.CON_FIRST_SATIME, value);
	}
	
	public static String getFirstAppStartTime() {
		return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.CON_FIRST_SATIME);
	}
	
	/**
	 * 第一次使用时的时间
	 * @param value
	 */
	public static void setFirstUseTime(long value) {
		PreferUtils.putLong(PreferencesCache.getConfigurationPrefer(), Constants.CON_FIRST_USE_TIME, value);
	}
	
	public static long getFirstUseTime() {
		return PreferUtils.getLong(PreferencesCache.getConfigurationPrefer(), Constants.CON_FIRST_USE_TIME, 0);
	}
	
	/**
	 * 得到推送被打开,没有反馈成功的taskId
	 * @return
	 */
	public static String getPushRecordOpenTaskId() {
		String taskIdStr = PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_RECORD_OPEN_TASKID); 
		if (TextUtils.isEmpty(taskIdStr)) {
			return "";
		}
		
		return taskIdStr.endsWith(",") ? taskIdStr.substring(0, taskIdStr.length() - 1) : taskIdStr;
	}
	
	/**
	 * 设置推送被打开,没有反馈成功的taskId
	 * @param taskId
	 */
	public static void setPushRecordOpenTaskId(String taskId) {
		if (TextUtils.isEmpty(taskId)) {
			PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_RECORD_OPEN_TASKID, taskId);
			return;
		}
		String taskIdStr = getPushRecordOpenTaskId();
		if (!TextUtils.isEmpty(taskIdStr)) {
			taskIdStr += ",";
		}
		taskIdStr += taskId + ",";
		PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_RECORD_OPEN_TASKID, taskIdStr);
	}
	
	/**
	 * 得到推送被接收,没有反馈成功的taskId
	 * @return
	 */
	public static String getPushRecordArriveTaskId() {
		String taskIdStr = PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_RECORD_ARRIVE_TASKID); 
		if (TextUtils.isEmpty(taskIdStr)) {
			return "";
		}
		
		return taskIdStr.endsWith(",") ? taskIdStr.substring(0, taskIdStr.length() - 1) : taskIdStr;
	}
	
	/**
	 * 设置推送被接收,没有反馈成功的taskId
	 * @param taskId
	 */
	public static void setPushRecordArriveTaskId(String taskId) {
		if (TextUtils.isEmpty(taskId)) {
			PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_RECORD_ARRIVE_TASKID, taskId);
			return;
		}
		
		String taskIdStr = getPushRecordArriveTaskId();
		if (!TextUtils.isEmpty(taskIdStr)) {
			taskIdStr += ",";
		}
		taskIdStr += taskId + ",";
		PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_RECORD_ARRIVE_TASKID, taskIdStr);
	}
	
	/**
	 * 设置推送开关的状态
	 * true 推送状态开
	 * false 推送状态关闭
	 * @param value
	 */
	public static void setPushControlStatus(boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_CONTROL_FLAG, value); 
	}
	
	/**
	 * 得到推送开关的状态
	 * true 推送状态开
	 * false 推送状态关闭
	 * @return
	 */
	public static boolean getPushControlStatus() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_CONTROL_FLAG, false);
	}
	
	/**
	 * 设置是否设置过Push，应用第一次启动时或清除缓存后重新启动，需要向服务端反馈当前Push状态，保持客户端与服务器端同步
	 * @param value
	 */
	public static void setDefaultPush(boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_FLAG, value); 
	}
	
	/**
	 * 得到是否设置过Push，保持客户端与服务器端同步
	 * @return
	 */
	public static boolean getDefaultPush() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_FLAG, false);
	}
	
	/**
	 * 缓存push内容
	 * @param message
	 */
	public static void setPushMessage(String message) {
		PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_MESSAGE, message);
	}
	
	/**
	 * 得到缓存push内容
	 * @return
	 */
	public static String getPushMessage() {
		return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_MESSAGE);
	}
	
	/*
	 * 设置百度Push 渠道ID
	 * 
	 * @param channelId
	 */
	public static void setBaiduPushChannelId(String channelId) {
		if (!TextUtils.isEmpty(channelId)) {
			PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.BAIDU_CHANNEL_ID, channelId);
		}
	}

	/**
	 * 得到百度Push 渠道ID
	 * 
	 * @return
	 */
	public static String getBaiduPushChannelId() {
		return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.BAIDU_CHANNEL_ID);
	}
	
	/**
	 * 设置当前用户是否解绑状态
	 * @param isBind true 解绑成功, false未解绑
	 */
	public static void setUserIsUnbindStatus(boolean isBind) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_IS_UNBIND_FOR_USER, isBind);
	}
	
	/**
	 * 得到当前用户是否绑定状态
	 */
	public static boolean getUserIsUnbindStatus() {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), Constants.PUSH_IS_UNBIND_FOR_USER, false);
	}
	
		/**************************播放器进度条缓存记录 start*************************************/
	public static void setSeekBarStart(long value) {
		PreferUtils.putLong(PreferencesCache.getConfigurationPrefer(), Constants.CON_PLAY_SEEKBAR_START, value); 
	}

	public static void setSeekBarEnd(String value) {
		PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.CON_PLAY_SEEKBAR_END, value); 
	}

	public static void setSeekBarProgram(int value) {
		PreferUtils.putInt(PreferencesCache.getConfigurationPrefer(), Constants.CON_PLAY_SEEKBAR_PROGRAM, value); 
	}

	public static void setSeekBarProgramMax(int value) {
		PreferUtils.putInt(PreferencesCache.getConfigurationPrefer(), Constants.CON_PLAY_SEEKBAR_PROGRAM_MAX, value); 
	}
	
	public static long getSeekBarStart() {
		return PreferUtils.getLong(PreferencesCache.getConfigurationPrefer(), Constants.CON_PLAY_SEEKBAR_START, 0l);
	}

	public static String getSeekBarEnd() {
		String temp = PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.CON_PLAY_SEEKBAR_END);
		if (TextUtils.isEmpty(temp)) {
			temp = "00:00:00";
		}

		return temp;
	}

	public static int getSeekBarProgram() {
		return PreferUtils.getInt(PreferencesCache.getConfigurationPrefer(), Constants.CON_PLAY_SEEKBAR_PROGRAM, 0); 
	}

	public static int getSeekBarProgramMax() {
		return PreferUtils.getInt(PreferencesCache.getConfigurationPrefer(), Constants.CON_PLAY_SEEKBAR_PROGRAM_MAX, 0); 
	}
	/**************************播放器进度条缓存记录 end*************************************/
	
	/**
	 * 得到状态(根据当前值来获取当前的状态，不同的值对应不同的状态值）
	 * @return
	 */
	public static boolean getStatus(String value) {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), value, false);
	}
	
	/**
	 * [String]根据给定的Key值得到对应的Values
	 * @param key 给定的Key
	 * @return 对应的Values
	 */
	public static String getSValues(String key) {
		return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), key);
	}
	
	/**
	 * 设定一组key-values
	 * @param key 指定的key
	 * @param value 指定的values
	 */
	public static void setSValues(String key, String value) {
		PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), key, value);
	}
	
	/**
	 * [Boolean]根据给定的Key值得到对应的Values
	 * @param key 给定的Key
	 * @return 对应的Values
	 */
	public static boolean getBValues(String key) {
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(), key, false);
	}
	
	/**
	 * 设定一组key-values
	 * @param key 指定的key
	 * @param value 指定的values
	 */
	public static void setBValues(String key, boolean value) {
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), key, value);
	}
	
	/**
	 * [Long]根据给定的Key值得到对应的Values
	 * @param key 给定的Key
	 * @return 对应的Values
	 */
	public static long getLValues(String key) {
		return PreferUtils.getLong(PreferencesCache.getConfigurationPrefer(), key, -1);
	}
	
	/**
	 * 设定一组key-values
	 * @param key 指定的key
	 * @param value 指定的values
	 */
	public static void setLValues(String key, long value) {
		PreferUtils.putLong(PreferencesCache.getConfigurationPrefer(), key, value);
	}

	/**
	 * [Integer]根据给定的Key值得到对应的Values
	 * @param key 给定的Key
	 * @return 对应的Values
	 */
	public static int getIValues(String key) {
		return PreferUtils.getInt(PreferencesCache.getConfigurationPrefer(), key, -1);
	}

	/**
	 * 设定一组key-values
	 * @param key 指定的key
	 * @param value 指定的values
	 */
	public static void setIValues(String key, int value) {
		PreferUtils.putInt(PreferencesCache.getConfigurationPrefer(), key, value);
	}

	/**
	 * 锁屏播放
	 */

	public static void setLockScreen(boolean boo){
		PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.isLockScreen, boo);
	}

	/**
	 * 得到锁屏的值
	 */
	public static boolean getLockSceen(){
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(),Constants.isLockScreen,false);
	}

	/**
	 *设置用户是否有喜好数据
	 * @param boo
     */
	public static void setIsLike(boolean boo){
		 PreferUtils.putBoolean(PreferencesCache.getConfigurationPrefer(), Constants.isLike,boo);
	}

	/**
	 * 得到用户是否有喜好数据
	 * @return
     */
	public static boolean getIsLike(){
		return PreferUtils.getBoolean(PreferencesCache.getConfigurationPrefer(),Constants.isLike,false);
	}

	/**
	 * 设置电台页->精选电台时间
	 * @param time
	 */
	public static void setSelectionTime(int time) {
		PreferUtils.putInt(PreferencesCache.getGlobalPrefer(), Constants.GET_SELECTION_TIME, time);
	}

    /**
	 * 获取电台页->精选电台时间
	 */
	public static int getSelectionTime(){
		return PreferUtils.getInt(PreferencesCache.getGlobalPrefer(), Constants.GET_SELECTION_TIME, 0);
	}

	/**
	 * 设置电台页->精选电台时间,音乐电台推荐时间
	 * @param time
	 */
	public static void setRefreshTime(Long time) {
		PreferUtils.putLong(PreferencesCache.getGlobalPrefer(), Constants.SELECTION_REFRESH_TIME, time);
	}

	/**
	 * 获取电台页->精选电台时间，音乐电台推荐时间
	 */
	public static Long getRefreshTime(){
		return PreferUtils.getLong(PreferencesCache.getGlobalPrefer(), Constants.SELECTION_REFRESH_TIME, 0);
	}

	/**
	 * 登录和未登录状态下
	 * 得到缓存所有Fmid
	 * @return
     */
	public static String getFmIds() {
		if(AccountCache.isLogin()){
			return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.FM_IDS);
		}else{
			return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.FM_IDS_NO);
		}
	}

	/**
	 * 登录和未登录状态下
	 * 缓存所有Fmid
	 * @param value
     */
	public static void setFmIds(String value) {
		if(AccountCache.isLogin()){
			PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.FM_IDS,value);
		}else{
			PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.FM_IDS_NO,value);
		}

	}

	/**
	 * 登录和未登录状态下
	 * 得到缓存所有订阅
	 * @return
	 */
	public static String getSubIds() {
		if(AccountCache.isLogin()){
			return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.SUB_IDS);
		}else{
			return PreferUtils.getString(PreferencesCache.getConfigurationPrefer(), Constants.SUB_IDS_NO);
		}
	}

	/**
	 * 登录和未登录状态下
	 * 缓存所有Fmid
	 * @param value
	 */
	public static void setSubIds(String value) {
		if(AccountCache.isLogin()){
			PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.SUB_IDS,value);
		}else{
			PreferUtils.putString(PreferencesCache.getConfigurationPrefer(), Constants.SUB_IDS_NO,value);
		}

	}
}
