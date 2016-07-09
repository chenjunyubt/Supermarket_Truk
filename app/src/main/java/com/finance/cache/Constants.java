/**
 * 
 */
package com.finance.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liqiang
 * @data 2014年9月13日
 * 
 */
public class Constants {
	//downloadData
	public static Map<Integer, Map<Integer, String>> downlaodAudioMap = new HashMap<Integer, Map<Integer, String>>();

    //关键存储变量
    public static final String SHUZILM_STOCK = "shuzilm_stock";
	/** 首页 频道页请求参数 */
	public static final String HOME_REQUEST_PROGRAM = "album,vod,special,fm";//,anchor
	public static final String HOME_ANCHOR_REQUEST_PROGRAM = "special,anchor";
	public static final String downlaodPaht = "downlaodPaht";
	public static final String HOME_FUNCTION_ICON = "home_function_icon";//首页功能区图标
	/**
	 * 与应用相关属性
	 */
	public static final String CON_FIRST = "first";
	public static final String CON_CATEGORY_TIME = "category_time";
	public static final String CON_CATEGORY_CACHE = "is_cache";
	public static final String CON_FIRST_USE = "first_use_app";
	public static final String CON_FIRST_USE_TIME = "first_use_time";
	public static final String CON_ALLOW_NET = "allow_network";
	public static final String CON_GLOBAL_CONFIG = "global_config";
	public static final String CON_ACTIVITY_STATUS = "activity_status";
	public static final String CON_FIRST_SATIME = "first_start_app_time";
    public static final String CON_CLEAN_DATA = "clean_data";
	public static final String CON_LIVE_CATEGORY_TIME = "live_category_time";
	public static final String CON_LIVE_SCREEN_TIME = "live_screen_time";
	public static final String CON_LIVE_LOCATION_PROVICE = "live_location_provice";
	public static final String CON_FIRST_SHOW_MASK = "first_show_mask";//第一次显示首页播放器蒙板
	public static final String CON_FIRST_SHOW_GUIDE_PAGE = "first_show_guide_page";//第一次显示启动引导图
	/**
	 * 心跳存活
	 */
	public static final String HEARTBEAT_TIME = "heartbeat_time";
	public static final String HEARTBEAT_SUCC = "heartbeat_succ";

	/**
	 * 二级导航（电台，原创播客）数据标识
	 */
	public static final String DATA_CACHE_RADIO = "unique";
	public static final String DATA_CACHE_BLOG = "cache_blog";

	/**
	 * 当前服务器时间
	 */
	public static final String SERVER_TIME = "server_time";
	/**启动页的广告图Url*/
	public static final String APP_START_ADVERT = "app_start_advert_img";
	/**启动页广告图显示次数*/
	public static final String APP_START_ADVERT_SHOWCOUNT = "app_start_advert_showcount";
	/**启动页的广告图 点击所需的Url*/
	public static final String APP_START_ADVERT_URL = "app_start_advert_url";
	/**
	 * 音乐电台类型缓存标记
	 */
	public static final String DISCOVER_CATEGORY_TIME = "discover_category_time";
	/**
	 * 音乐电台list数据缓存标记
	 */
	public static final String MUSIC_RADIO_DATA = "discover_category_time";
	/**
	 * 音乐电台推荐数据缓存标记
	 */
	public static final String RADIO_RECOMMENT_TIME = "radio_recomment_time";

	/*-----------------------播放器相关 start--------------------------------*/
	/**
	 * 与播放器相关
	 */
	public static final String CON_PLAY_ALBUM_ID = "album_id";
	public static final String CON_PLAY_FM_ID = "fm_id";
	public static final String CON_PLAY_PRI_FM_ID = "pr_fm_id"; 
	public static final String CON_PLAY_VOD_ID = "vod_id";
	/**
	 * 当前播放器的播放状态
	 */
	public static final String CON_CURRENT_PLAY_STATE = "cur_play_state";
	public static final String CON_CURRENT_DATE = "cur_date";//当前直播日期
	public static final String CON_IS_RELOAD_PLAYLIST = "reload_playlist";//是否需要重新加载播放列表
	/**
	 * 缓存播放器进度条记录
	 */
	public static final String CON_PLAY_SEEKBAR_START = "seekbar_start";
	public static final String CON_PLAY_SEEKBAR_END = "seekbar_end";
	public static final String CON_PLAY_SEEKBAR_PROGRAM = "seekbar_program";
	public static final String CON_PLAY_SEEKBAR_PROGRAM_MAX = "seekbar_program_max";
	/**
	 * 与播放器定时相关
	 */
	public static final String PLAY_TIMER_START = "play_timer_start";
	public static final String PLAY_TIMER_RANGLE = "play_timer_rangle";

	public static final String CUR_PLAY_URL = "cur_play_url";
	public static final String CUR_PLAY_TITLE = "cur_play_title";
	public static final String CUR_PLAY_SUBTITLE = "cur_play_subtitle";
//	public static final String CUR_PLAY_URL = "cur_play_url";

    public static final String PLAY_LIST_SORT = "play_list_sort";//播放列表顺序，默认为正序（-1），反之倒序（0）
	/*-----------------------播放器相关 end--------------------------------*/
	
	public static final String FM_ID = "fmId"; //预约提醒状态栏到对应频率id.shakeServiceRun

	/**
	 * 数据收集相关属性
	 */
	public static final String CON_COLLECT_INSTALL = "dc_install";
	public static final String CON_COLLECT_ACTIVE = "dc_active";
	public static final String CON_COLLECT_CONNECT = "dc_connect";
	public static final String CON_COLLECT_INSTALL_TIME = "dc_install_time";
	
	/**
	 * 帐号相关属性
	 */
	public static final String ACCOUNT_USERID = "userid";
	public static final String ACCOUNT_NICKNAME = "nickname";
	public static final String ACCOUNT_GENDER = "gender";
	public static final String ACCOUNT_FACEURL = "face_url";
	public static final String ACCOUNT_LOCATION = "location";
	public static final String ACCOUNT_VIPLEVEL = "vip_level";
	public static final String ACCOUNT_AUTH_STATE = "auth_state";
	public static final String ACCOUNT_SESSION_KEY = "session_key";
	public static final String ACCOUNT_FANS_TOTAL = "fans_total";
	public static final String ACCOUNT_FOLLOW_TOTAL = "follow_total";
	public static final String ACCOUNT_SIGN = "sign";
	public static final String ACCOUNT_NEW_USER = "is_new_user";
	public static final String ACCOUNT_TOKEN = "token";
	public static final String ACCOUNT_MESSAGE_ACL = "message_acl";
	/**
	 * 手机号登录记录的手机号码：
	 */
	public static final String PHONE_NUM = "phonenum";


	/*-----------------------push相关 start--------------------------------*/
	/**
	 * 百度渠道ID
	 */
	public static final String BAIDU_CHANNEL_ID = "baidu_channel_id";
	/**
	 * 百度推送打开通知，反馈失败的通知ID
	 */
	public static final String PUSH_RECORD_OPEN_TASKID = "open_taskid";
	/**
	 * 百度推送接收通知，反馈失败的通知ID
	 */
	public static final String PUSH_RECORD_ARRIVE_TASKID = "arrive_taskid";
	/**
	 * push开关
	 */
	public static final String PUSH_CONTROL_FLAG = "push_control_flag";
	/**
	 * 是否设置过Push，应用第一次启动时或清除缓存后重新启动，需要向服务端反馈当前Push状态
	 */
	public static final String PUSH_FLAG = "push_flag";
	/**
	 * push内容
	 */
	public static final String PUSH_MESSAGE = "push_message";
	/**
	 * 当前用户推送是否解绑（自己服务器）
	 */
	public static final String PUSH_IS_UNBIND_FOR_USER = "push_is_unbind_for_user";

	/*-----------------------push相关 end--------------------------------*/
	/**
	 * 当前播放节目的类型
	 * fm/vod
	 */
	public static final String PLAY_TYPE = "play_type";
	
	/**
	 * 记录多节目列表，当前选中的节目ID
	 */
	public static final String PLAY_PROGRAM_ID = "play_program_id";

	/**
	 * 同步本地数据到网络帐户
	 */
	public static final String ISSYNCDATA =  "isSyncData";
	
	/**
	 * 版本升级有关
	 */
	public static final String VERSION_DOWNLOAD_ID = "versionDownloadId";
	public static final String VERSION_INTERRUPT_DOWNLOAD = "isInterruptDownload";
	public static final String VERSION_DOWNLOAD_DATE = "versionDownloadDate";
	
	/**
	 * 广告跳点的状态值
	 */
	public static final String SET_ADVERT_POINT_STATUS = "advert_point_status";
	/**
	 * 是否设置允许4G播放
	 */
	public static final String SET_ON4G_PLAY = "is4GPlay";
	/**
	 * 是否允许在4G下下载
	 */
	public static final String IS_AUDIO_DOWNLOAD  = "isAudioDownload";
	
	/**
	 * 直播（电台）收藏
	 */
	public static final String FMFavBoo = "fmfav";

	/**
	 * 私人电台收藏
	 */
	public static final String PrivateFMFavBoo  = "privateFmFav";
	/**
	 * 私人电台节目收藏
	 */
	public static final String PrivateProgramFavBoo  = "privateProgramFav";

	/**
	 * 音频收藏
	 */
	public static final String VoidFavBoo  = "voidFav";

	/**
	 * 专辑收藏
	 */
	public static final String AlbumFavBoo  = "albumFav";
	/**
	 * 专题收藏
	 */
	public static final String SpecialFavBoo  = "specialFav";
	
	/**
	 * 是否应该刷新当前界面
	 */
	public static final String isUpdateStatus = "isUpdate";

	/**
	 * 锁频播放
	 */
	public static final String isLockScreen = "isLock";

    public static final String DMPLeftContent = "dmpLeftContent";//默认mini播放器左边显示内容

	/**
	 * 是否有喜欢调查数据
	 */
	public static final String isLike = "isLike";

    //电台页精选电台数据获取时间标记
	public static final String GET_SELECTION_TIME = "selectionTime";

	/***是否首页显示tips**/
	public static final String TIPSSTATUA = "isTipsSTATU";

	/**
	 * 电台页精选电台数据隔半点，整点刷新标记时间
	 */
	public static final String SELECTION_REFRESH_TIME = "selectionRefreshTime";
	/**
	 * 登录状态下缓存所有fmid
	 */
	public static final String FM_IDS = "fm_ids";
	/**
	 * 未登录状态下缓存所有fmid
	 */
	public static final String FM_IDS_NO = "fm_ids_no";
	/**
	 * 登录状态下缓存所有subid
	 */
	public static final String SUB_IDS = "sub_ids";
	/**
	 * 未登录状态下缓存所有subid
	 */
	public static final String SUB_IDS_NO = "sub_ids_no";
}
