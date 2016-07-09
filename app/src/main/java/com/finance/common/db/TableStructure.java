package com.finance.common.db;

public class TableStructure {
	/**
	 * 首页栏目表
	 */
	static final String TABLE_CATEGORY = "homeCategory";
	static final String TABLE_CATEGORY_ID = "id";
	static final String TABLE_CATEGORY_NAME = "name";
	static final String TABLE_CATEGORY_ORDERID = "orderId";
	static final String TABLE_CATEGORY_SELECTED = "selected";
	static final String TABLE_CATEGORY_RED = "red";
	static final String TABLE_CATEGORY_SUB = "sub";

	/**
	 * 首页信息缓存表
	 */
	static final String TABLE_HOME = "homeCache";
	static final String TABLE_HOME_TOPLIST = "homeTopListCache";
	static final String TABLE_HOME_ADVERT = "homeAdvertCache";
	static final String TABLE_HOEM_ID = "id";
	static final String TABLE_HOME_URL = "url";
	static final String TABLE_HOME_CONTENT = "content";
	static final String TABLE_HOME_TIME = "createtime";
	static final String TABLE_HOME_TYPE = "type";
	static final String TABLE_HOME_ADDTIME = "addtime";

	/**
	 * 电台信息缓存表
	 */
	static final String TABLE_MUSIC_RECOMMENT = "recommentCacheFM";//音乐电台->推荐表名字
	static final String TABLE_HOME_FM = "homeCacheFM";    //电台->精选电台表名字
	static final String TABLE_HOME_FM_FM_ID = "fm_id";
	static final String TABLE_HOME_FM_NAME = "name";
	static final String TABLE_HOME_FM_FREQUENCY = "frequency";
	static final String TABLE_HOME_FM_PLAY_TIMES = "total_play_times";
	static final String TABLE_HOME_FM_LIVE_URL = "kt_live_url";
	static final String TABLE_HOME_FM_IS_FAVORITE = "is_favorite";
	static final String TABLE_HOME_FM_RECOMMENDATION = "recommendation";

	static final String TABLE_HOME_FM_CONTENT_CLASS_ID = "fm_content_class_id";
	static final String TABLE_HOME_FM_CONTENT_CALSS_NAME = "fm_content_class_name";
	static final String TABLE_HOME_FM_RADIO_ID = "radio_id";
	static final String TABLE_HOME_FM_COVER_BASE_URL = "cover_base_url";
	static final String TABLE_HOME_FM_IS_MUSIC = "is_music";

	static final String TABLE_HOME_FM_PROGRAM_ALBUM_ID = "program_album_id";
	static final String TABLE_HOME_FM_PROGRAM_ID = "program_id";
	static final String TABLE_HOME_FM_ANCHOR_NAME = "anchor_name";
	static final String TABLE_HOME_FM_PROGRAM_NAME = "program_name";
	static final String TABLE_HOME_FM_ST = "st";
	static final String TABLE_HOME_FM_ED = "et";
	static final String TABLE_HOME_FM_NEXT_PROGRAM_ALBUM_ID = "next_program_album_id";
	static final String TABLE_HOME_FM_NEXT_PROGRAM_ID = "next_program_id";
	static final String TABLE_HOME_FM_NEXT_PROGRAM_NAME = "next_program_name";
	static final String TABLE_HOME_FM_NEXT_ST = "next_st";
	static final String TABLE_HOME_FM_NEXT_ED = "next_et";
	// 收藏表

    static final String TABLE_FAVORITE = "table_favorite";
    static final String TABLE_FAVORITE_TYPE = "table_favorite_type";
    static final String TABLE_FAVORITE_ID = "table_favorite_id";
    static final String TABLE_FAVORITE_SUBID = "table_favorite_subid";
    static final String TABLE_FAVORITE_STATUS = "table_favorite_status";
    static final String TABLE_FAVORITE_USER_ID = "user_id";

	// 订阅表
	static final String TABLE_SUBSCRIBE_ALL = "table_subscribe";
	static final String TABLE_SUBSCRIBE_ALL_ALBUM_ID = "subscribe_album_id";
	static final String TABLE_SUBSCRIBE_ALL_STATUS = "subscribe_status";
	static final String TABLE_SUBSCRIBE_ALL_USER_ID = "user_id";

    /**
	 * 音频表
	 */

	public static final String TABLE_FAVORITE_AUDIO = "favoriteaudio";
	static final String TABLE_FAVORITE_AUDIO_VOI_ID = "vod_id";
	static final String TABLE_FAVORITE_AUDIO_ALBUM_ID = "album_id";
	static final String TABLE_FAVORITE_AUDIO_NAME = "audio_name";
	static final String TABLE_FAVORITE_AUDIO_COVER_URL = "cover_url";
	static final String TABLE_FAVORITE_AUDIO_DURATION = "duration";
	static final String TABLE_FAVORITE_AUDIO_TIMES = "play_times";
	static final String TABLE_FAVORITE_AUDIO_PLAY_URL = "play_url";
	static final String TABLE_FAVORITE_AUDIO_TITLE = "title";
	static final String TABLE_FAVORITE_AUDIO_USER_ID = "user_id";
	
	/**
	 * 发现首页信息缓存表
	 */
	static final String TABLE_DISCOVER = "discoverCache";
	static final String TABLE_DISCOVER_UNIQUE= "discoverunique";
	static final String TABLE_DISCOVER_CONTENT = "discovercontent";

	/**
	 * 专辑表
	 * 
	 */

	public static final String TABLE_FAVORITE_ALBUM = "favoritealbum";
	static final String TABLE_FAVORITE_ALBUM_ALBUM_ID = "album_id";
	static final String TABLE_FAVORITE_ALBUM_PROGRAM_ID = "program_id";
	static final String TABLE_FAVORITE_ALBUM_ALBUM_TYPE = "album_type";
	static final String TABLE_FAVORITE_ALBUM_TITLE = "title";
	static final String TABLE_FAVORITE_ALBUM_IS_END = "is_end";
	static final String TABLE_FAVORITE_ALBUM_COVER_URL = "cover_url";
	static final String TABLE_FAVORITE_ALBUM_TIMES = "play_times";
	static final String TABLE_FAVORITE_ALBUM_VOD_NUM = "vod_num";
	static final String TABLE_FAVORITE_ALBUM_USER_ID = "user_id";

	/**
	 * 专题表
	 * 
	 */
	public static final String TABLE_FAVORITE_SPECIAL = "favoriteSpecial";
	public static final String TABLE_FAVORITE_SPECIAL_ID = "special_id";
	public static final String TABLE_FAVORITE_SPECIAL_TITLE = "title";
	public static final String TABLE_FAVORITE_SPECIAL_COVER_BASE_URL = "cover_base_url";
	public static final String TABLE_FAVORITE_SPECIAL_PLAY_TIMES = "play_times";
	public static final String TABLE_FAVORITE_SPECIAL_MTIME = "mtime";
	public static final String TABLE_FAVORITE_SPECIAL_RECOMMENDATION = "recommendation";
	public static final String TABLE_FAVORITE_SPECIAL_USER_ID = "user_id";
	/**
	 * 私人电台
	 */

	public static final String TABLE_FAVORITE_PRIVATE_RADIO = "favoriteradio";
	static final String TABLE_FAVORITE_RADIO_USER_FM_ID = "user_fm_id";
	static final String TABLE_FAVORITE_RADIO_NAME = "fm_name";
	static final String TABLE_FAVORITE_RADIO_TYPE = "fm_type";
	static final String TABLE_FAVORITE_RADIO_ACL = "acl";
	static final String TABLE_FAVORITE_RADIO_COVER_URL = "cover_url";
	static final String TABLE_FAVORITE_RADIO_NUM = "audio_num";
	static final String TABLE_FAVORITE_RADIO_PROGRAMNUM = "programme_num";
	static final String TABLE_FAVORITE_RADIO_TIMES = "total_play_times";
	static final String TABLE_FAVORITE_RADIO_USERID = "userid";
	static final String TABLE_FAVORITE_RADIO_NICK_NAME = "nickname";
	static final String TABLE_FAVORITE_RADIO_USER_ID = "user_id";

	/**
	 * 私人电台节目
	 */
	public static final String TABLE_FAVORITE_PRIVATE_PROGRAM = "favoriteprogram";
	static final String TABLE_FAVORITE_PROGRAM_ID = "fm_programme_id";
	static final String TABLE_FAVORITE_PROGRAM_USER_FM_ID = "user_fm_id";
	static final String TABLE_FAVORITE_FM_PROGRAM_NAME = "fm_name";
	static final String TABLE_FAVORITE_PROGRAM_ACL = "acl";
	static final String TABLE_FAVORITE_PROGRAM_COVER_URL = "cover_url";
	static final String TABLE_FAVORITE_PROGRAM_NUM = "audio_num";
	static final String TABLE_FAVORITE_PROGRAM_PLAY_NUM = "play_times";
	static final String TABLE_FAVORITE_PROGRAM_USERID = "userid";
	static final String TABLE_FAVORITE_PROGRAM_NICK_NAME = "nickname";
	static final String TABLE_FAVORITE_PROGRAM_NAME = "programme_name";
	static final String TABLE_FAVORITE_PROGRAM_USER_ID = "user_id";
	
	/**自定义标签保存**/
	public static final String TABLE_CUSTOM_STYLE_NORMAL = "customstylenormal";
	static final String TABLE_CUSTOM_STYLE_NORMAL_ID = "tag_id";
	static final String TABLE_CUSTOM_STYLE_NORMAL_NAME = "name";
	static final String TABLE_CUSTOM_STYLE_ORDER_ID = "order_id";
	static final String TABLE_CUSTOM_STYLE_SELECTED = "selected";
	static final String TABLE_CUSTOM_STYLE_USER_ID = "user_id";
	
	/**
	 * 收藏发言
	 */

	public static final String TABLE_FAVORITE_SPEECH = "favoritespeech";
	static final String TABLE_FAVORITE_SPEECH_SPEECH_ID = "speech_id";
	static final String TABLE_FAVORITE_SPEECH_AUDIO_TEXT = "audio_text";
	static final String TABLE_FAVORITE_SPEECH_AUDIO_URL = "audio_url";
	static final String TABLE_FAVORITE_SPEECH_USERID = "userid";
	static final String TABLE_FAVORITE_SPEECH_NICK_NAME = "nickname";
	static final String TABLE_FAVORITE_SPEECH_FACE = "face";
	static final String TABLE_FAVORITE_SPEECH_TIMES = "ctime";
	static final String TABLE_FAVORITE_SPEECH_DIGG = "digg";
	static final String TABLE_FAVORITE_SPEECH_EGG = "egg";
	static final String TABLE_FAVORITE_SPEECH_USER_ID = "user_id";

	/***
	 * 收藏我的电台
	 */
	public static final String TABLE_FAVORITE_RADIO = "myradio";
	static final String TABLE_FAVORITE_MYRADIO_FM_ID = "fm_id";
	static final String TABLE_FAVORITE_MYRADIO_NAME = "name";
	static final String TABLE_FAVORITE_MYRADIO_FREQUENCY = "frequency";
	static final String TABLE_FAVORITE_MYRADIO_COVER_URL = "cover_url";
	static final String TABLE_FAVORITE_MYRADIO_TOTAL_PLAY_TIMES = "total_play_times";
	static final String TABLE_FAVORITE_MYRADIO_FM_IPROGRAME_ID = "program_id";
	static final String TABLE_FAVORITE_MYRADIO_PROGRAME_NAME = "program_name";
	static final String TABLE_FAVORITE_MYRADIO_USER_ID = "user_id";
	static final String TABLE_FAVORITE_MYRADIO_ORDER_ID = "order_id";
	/**
	 * 
	 * 搜索历史记录
	 */
	static final String TABLE_SEARCH_HISTORY = "searchhistory";
	static final String TABLE_SEARCH_HISTORY_USER_ID = "userid";
	static final String TABLE_SEARCH_HISTORY_VALUE = "historyvalue";
	static final String TABLE_SEARCH_HISTORY_TYPE_KEY = "historytypekey";

	/**
	 * 下载数据Album表
	 */
	static final String TABLE_DOWNLOAD_ALBUM = "downLoadAlbumData";
	static final String TABLE_DOWNLOAD_ALBUM_ALBUM_ID = "album_id";
	static final String TABLE_DOWNLOAD_ALBUM_TITLE = "title";
	static final String TABLE_DOWNLOAD_ALBUM_COVER_URL = "cover_url";
	static final String TABLE_DOWNLOAD_ALBUM_IS_END = "is_end";
	static final String TABLE_DOWNLOAD_ALBUM_TYPE_ID = "type_id";
	static final String TABLE_DOWNLOAD_DOWNLOADTYPE = "downloadType";
	static final String TABLE_DOWNLOAD_USERID= "mUserId";
	static final String TABLE_DOWNLOAD_PROGRAMTYPE = "programType";
	static final String TABLE_DOWNLOAD_PRIVATERADIOID = "privateRadioId";
	static final String TABLE_DOWNLOAD_PROGRAMID = "programId";
	static final String TABLE_DOWNLOAD_DATETIME = "dateAndTime";
	/**
	 * 下载数据audio表
	 */
	static final String TABLE_DOWNLOAD = "downLoadData";
	static final String TABLE_DOWNLOAD_ALBUM_ID = "album_id";
	static final String TABLE_DOWNLOAD_VOD_ID = "vod_id";
	static final String TABLE_DOWNLOAD_TITLE = "title";
	static final String TABLE_DOWNLOAD_AUDIO_NAME = "audio_name";
	static final String TABLE_DOWNLOAD_DOWNLOAD_URL = "download_url";
	static final String TABLE_DOWNLOAD_DURATION = "duration";
	static final String TABLE_DOWNLOAD_PLAY_URL = "play_url";
	// 下载状态，0未下载，1等待下载，2暂停下载，3下载完成 , 4下载失败
	static final String TABLE_DOWNLOAD_DOWNLOAD_FLAG = "download_flag";
	static final String TABLE_DOWNLOAD_DOWNLOAD_LENGTH = "download_length";
	static final String TABLE_DOWNLOAD_CONTEXT_LENGTH = "content_length";
	static final String TABLE_DOWNLOAD_FILE_PATH = "file_path";
	static final String TABLE_DOWNLOAD_USER_ID = "user_id";
	static final String TABLE_DOWNLOAD_ID = "_id";
	static final String TABLE_DOWNLOADTYPE = "downloadtype";
	static final String TABLE_DOWNLOADTYPE_01 = "type_01";  //audioOrder
	static final String TABLE_DOWNLOAD_STRING1= "str1";
	static final String TABLE_DOWNLOAD_COVER_URL = "cover_url";
	
	
	//播放历史(播放历史跟用户无关)
	static final String TABLE_PLAY_HISTORY = "playhistory";
	static final String PLAY_HISTORY_ALBUM_ID = "album_id";//专辑ID
	/**
	 * 此字段不再使用 使用{@link #PLAY_HISTORY_ITEM_TYPE}替代
	 */
	@Deprecated
	static final String PLAY_HISTORY_ALBUM_TYPE = "album_type";//专辑类型
	static final String PLAY_HISTORY_ALBUM_NAME = "album_name";//专辑名称
	/**
	 * 此字段不再使用 使用{@link #PLAY_HISTORY_ALBUM_ID}替代
	 */
	static final String PLAY_HISTORY_PROGRAM_ID = "program_id";//节目ID 私人电台多节目ID（暂时只能用于）
	static final String PLAY_HISTORY_VOD_ID = "vod_id";//音频ID
	static final String PLAY_HISTORY_VOD_NAME = "vod_name";//音频名称
	static final String PLAY_HISTORY_CREATE_NAME = "album_create_name";//创建人
	static final String PLAY_HISTORY_ALBUM_URL = "album_url";//专辑封面url
	static final String PLAY_HISTORY_TIME = "album_play_time";//专辑播放时长
	static final String PLAY_HISTORY_FM_ID = "fm_id";//频率ID
	static final String PLAY_HISTORY_FREQUENCY = "fm_frequency";//频率名称
	static final String PLAY_HISTORY_FM_PROGRAM_NAME = "fm_program_name";//频率节目名称
	static final String PLAY_HISTORY_FM_PROGRAM_NUM = "fm_program_play_num";//频率节目播放数
	static final String PLAY_HISTORY_UPDATETIME = "fm_updatetime";//添加入库时间
	static final String PLAY_HISTORY_USER_ID = "user_id";
	static final String PLAY_HISTORY_ITEM_TYPE = "item_type";//单条记录的类型
	/**
	 * 私信信息表 by tianhu  2014-11-18
	 */
	static final String TABLE_LETTER_CHAT	="letterchat";
	static final String TABLE_LETTER_CHAT_MESSAGEID = "message_id";
	static final String TABLE_LETTER_CHAT_SENDUSERID = "send_userid";
	static final String TABLE_LETTER_CHAT_OWNUSERID = "own_userid";
	static final String TABLE_LETTER_CHAT_ANOTHERID = "another_userid";
	static final String TABLE_LETTER_CHAT_SENDTIME = "send_time";
	static final String TABLE_LETTER_CHAT_AUDIOTEXT = "audio_text";
	static final String TABLE_LETTER_CHAT_AUDIOFULLURL = "audio_full_url";
	static final String TABLE_LETTER_CHAT_MESSAGETYPE = "message_type";
	static final String TABLE_LETTER_CHAT_DURATION = "duration";
	static final String TABLE_LETTER_CHAT_SENDFLAG = "sendflag";
	static final String TABLE_LETTER_CHAT_TYPE = "flag";
	/**
	 * 私信用户表 by tianhu  2014-11-18
	 */
	static final String TABLE_CHAT_USER ="letteruser";
	static final String TABLE_CHAT_USER_USERID ="userid";
	static final String TABLE_CHAT_USER_NICKNAME ="nickname";
	static final String TABLE_CHAT_USER_FACEURL ="face_url";
	static final String TABLE_CHAT_USER_USERSTATUS ="userStatus";
	static final String TABLE_CHAT_USER_CHATNUM ="chatNum";
	static final String TABLE_CHAT_USER_SENDTIME ="send_time";
	static final String TABLE_CHAT_USER_AUDIOTEXT ="audio_text";
	static final String TABLE_CHAT_USER_MESSAGEYPTE ="message_type";
	static final String TABLE_CHAT_USER_USER_ID = "user_id";
	
	/**
	 * 订阅
	 */
	static final String TABLE_SUBSCRIBE = "subscribe";//订阅表名
	static final String TABLE_SUBSCRIBE_LIST_ALBUM_ID = "subscribe_list_album_id";//订阅列表item的id
	static final String TABLE_SUBSCRIBE_VOD_ID = "subscribe_vod_id";// 订阅item专辑下的音频列表下的所有id

	/**
	 * 全局颜色值表
	 */
	static final String TABLE_GLOBAL_COLOR = "globalcolors";//全局颜色值 (类型/流派)使用
	static final String TABLE_GLOBAL_COLOR_COLORFLAG = "color_flag";//颜色值 1,2,3,4,5,6,7
	static final String TABLE_GLOBAL_COLOR_ID = "color_id";//颜色匹配id.
	static final String TABLE_GLOBAL_COLOR_type = "color_str";//保留字段

	/**
	 * 预约数据表
	 */
	static final String TABLE_MAKE_NAME = "maketable";//预约表名
	static final String TABLE_MAKE_NAME_PROGRAM_ID = "id";//节目Id
    static final String TABLE_MAKE_NAME_FM_ID = "fmId";//频率Id
	static final String TABLE_MAKE_NAME_NAME = "name";//频率名
	static final String TABLE_MAKE_FREQUENCY = "frequency";
	static final String TABLE_MAKE_NAME_COVERURL = "coverUrl";//FM108.6
	static final String TABLE_MAKE_NAME_PROGRAMANEM = "programname";//节目名字
	static final String TABLE_MAKE_NAME_ET = "ettime";//开始时间
	static final String TABLE_MAKE_NAME_ST = "sttime";//结束时间
	static final String TABLE_MAKE_START_DATE = "startdate";//预约开始日期
	static final String TABLE_MAKE_END_DATE = "enddate";//预约结束日期
	static final String TABLE_MAKE_TYPE = "type";//类型，区别摇一摇提醒。
	static final String TABLE_MAKE_INTERACTION_FLAG = "interactionflag";//互动标识，区分摇一摇互动界面添加，还是首页界面添加。

	/**
	 * 正在直播 地区表
	 */
	static final String TABLE_LIVE_AREA = "live_area_table";//正在直播地区表名
	static final String TABLE_LIVE_AREA_ID = "id";
	static final String TABLE_LIVE_AREA_NAME = "name";

	/**
	 *   正在直播 频道表
	 */
	static final String TABLE_LIVE_CATEGORY = "live_category_table";//正在直播频道表名
	static final String TABLE_LIVE_CATEGORY_ID = "id";
	static final String TABLE_LIVE_CATEGORY_NAME = "name";
	static final String TABLE_LIVE_CATEGORY_ORDERID = "orderId";
	static final String TABLE_LIVE_CATEGORY_TYPE = "type";
}
