package com.finance.common.core;

public class TTUrlManager {
    /**
     * 图片磁盘缓存url：<code>IMGCACHEDIR</code> 全路径
     * android/data/packagename/cache/tingting
     */
    public static final String IMGCACHEDIR = "tingting";
    /** 存储路径 */
    public static final String TINGTING_EXTERNAL_FILEPATH = "/tingting/";

    public static final String NETSERVICEDOMIN = TTUrlSwitch.SERVERURL;

    //首页
    /**读取首页频道和二级频道信息*/
    public static final String HOME_READ_CATEGORY = NETSERVICEDOMIN + "/recommendv2/get_index_channel_setting";
    /** 读取首页二级频道的精选数据 **/
    public static final String HOME_READ_SEC_CHANNEL_SELECTED_RECORD = NETSERVICEDOMIN + "/recommendv2/get_sub_channel_selected";
    /** 读取首页二级频道的最新/最热数据 **/
    public static final String HOME_READ_SEC_CHANNEL_NEW_OR_HOT_RECORD = NETSERVICEDOMIN + "/recommendv2/get_sub_channel_album_list";
    /**保存用户首页分类定制*/
    public static final String HOME_SAVE_CATEGORY = NETSERVICEDOMIN + "/recommend/save_recommend_setting_v1_3";
    /**二级频道 读取某个频道推荐数据*/
    public static final String HOME_READ_RECOMMEND_CHANNEL_RECORD = NETSERVICEDOMIN + "/recommendv2/get_index_channel_recommend_v2";
    /**读取首页综合推荐数据*/
    public static final String HOME_READ_RECOMMEND_RECORD = NETSERVICEDOMIN + "/recommendv2/get_index_recommend_v2";
    /**读取电台推荐*/
	public static final String HOME_READ_FM_RECOMMEND = NETSERVICEDOMIN + "/recommend/get_index_recommend_radios_v1_2";

    /**读取精选电台频率列表*/
    public static final String GET_FM_RECOMMEND = NETSERVICEDOMIN + "/radiov2/get_fm_recommend";

    /**读取指定频率的当前节目信息*/
    public static final String HOME_READ_CURRENT_FM_RECOMMEND = NETSERVICEDOMIN + "/live/get_fm_current_program_info_v1_2";
    /**设置不喜欢   用户叉掉的内容，记录为不喜欢的数据*/
    public static final String HOME_SET_DISLIKE = NETSERVICEDOMIN + "/like/hate";
    /**设置音频播放超过50%*/
    public static final String HOME_AUDIO_PLAY_HALF = NETSERVICEDOMIN + "/recommend/vod_playhalf";
    /**广告位*/
    public static final String ADVERT= NETSERVICEDOMIN + "/ad/get_ad_v2_0";
    /**启动页广告位*/
    public static final String ADVERT_SATRT_PAGE= NETSERVICEDOMIN + "/ad/get_start_page";

    ///**首页定位获取摇一摇数据  @chenjunyu 1.8版本把摇一摇功能去掉了*/
    //public static final String HOME_SHAKE_GPS = NETSERVICEDOMIN + "/recommend/get_recommend_radio_by_gps";
    ///**首页  摇一摇触发获取数据*/
    //public static final String HOME_SHAKE = NETSERVICEDOMIN + "/recommend/get_yaoyiyao_program";
    /**首页  专题信息获取   v2.0.1 修改/specialtopic/get_specialtopic_detailv2*/
    public static final String SPECIAL_TOPICS_DETAIL = NETSERVICEDOMIN + "/specialtopic/get_specialtopic_detailv2";

    /**读取音乐流派的频率列表，按照编辑评分排行**/
    public static final String GET_FM_GENRE = NETSERVICEDOMIN + "/discover/get_fm_genre";

    /**收藏 */
    public static final String COLLECTION = NETSERVICEDOMIN + "/vod/list_favorite";
	/**取消一类收藏*/
	public static final String CANCEL_FAVORITE_TYPE = NETSERVICEDOMIN + "/vod/cancel_favorite_by_type";
    /**微博登录**/
    public static final String SinaWeiBoLogin = NETSERVICEDOMIN + "/user/login_with_weibo";
    /**腾讯登录**/
    public static final String QQLogin = NETSERVICEDOMIN + "/user/login_with_tencent_v1_3";
    /** 手机号注册**/
    public static final String PHONE_REGISTER = NETSERVICEDOMIN + "/user/register_by_mobile_v1_3";
    /** 手机号登录**/
    public static final String PHONE_LOGIN = NETSERVICEDOMIN + "/user/login_with_mobile";
    /**获取验证码*/
    public static final String GET_CODE = NETSERVICEDOMIN + "/user/get_sms_code_v1_3";
    /**判断验证码*/
    public static final String CHECK_CODE = NETSERVICEDOMIN + "/user/check_if_code_effective_v1_3";
    /**修改密码*/
    public static final String UPDATE_CODE = NETSERVICEDOMIN + "/user/update_password_v1_3";
    //搜索
    /**请求提示热词*/
    public static final String SEARCH_HOT_WORDS = NETSERVICEDOMIN + "/search/get_hot_words";
    /**关键搜索*/
    public static final String SEARCH_BY_KEY_WORD = NETSERVICEDOMIN + "/search/search_keyword";
    /**跟随字搜索*/
    public static final String SEARCH_BY_SUGGEST_WORD = NETSERVICEDOMIN + "/search/suggest_words";
    /**订阅搜索*/
    public static final String SEARCH_BY_SUBSCRIBE_KEY_WORD = NETSERVICEDOMIN + "/search/search_album";
    /**电台搜索*/
    public static final String SEARCH_BY_RADIO_KEY_WORD = NETSERVICEDOMIN + "/search/search_fm";
    /**音频搜索*/
    public static final String SEARCH_BY_VOD_KEY_WORD = NETSERVICEDOMIN + "/search/search_vod";
    /**标签筛选页*/
    public static final String SEARCH_TAG = NETSERVICEDOMIN + "/search/search_tag";
    /**
     * 请求发现页数据1.7版本
     */
//    public static final String LOAD_DISCOVER_INFO = NETSERVICEDOMIN + "/discover/get_discover_info_v1_7";

    /**
     * 请求发现页数据1.9版本
     */
    public static final String LOAD_DISCOVER_INFO = NETSERVICEDOMIN + "/discover/get_discover_info_v1_9";

    /**
     * 读取电台首页结构配置。
     */
    public static final String GET_RADIO_INDEX_SETTING = NETSERVICEDOMIN + "/radiov2/get_radio_index_setting";

    /**
     * 请求发现本地定位地区列表数据
     */
    public static final String LOAD_DISCOVER_NATIVE_AREA_LIST = NETSERVICEDOMIN + "/discover/get_gps_fm_area_list";
    /**
     * 请求本地定位区域列表数据
     */
    public static final String LOAD_DISCOVER_SCORE_AREA_FM_LIST = NETSERVICEDOMIN + "/discover/get_fm_area_score";
    /**
     * 请求地区或者中央列表数据
     */
    public static final String LOAD_DISCOVER_AREA_OR_CENTRAL_FM_LIST = NETSERVICEDOMIN + "/discover/get_fm_area_zone_hot";
    /**
     * 请求最热列表数据
     */
    public static final String LOAD_DISCOVER_HOT_FM_LIST = NETSERVICEDOMIN + "/discover/get_fm_area_hot";

    /**读取音乐电台推荐列表*/
    public static final String GET_MUSIC_FM_RECOMMEND = NETSERVICEDOMIN + "/radiov2/get_music_fm_recommend";

    /**
     * 请求语言列表数据
     */
    public static final String LOAD_DISCOVER_LANGUAGES_FM_LIST = NETSERVICEDOMIN + "/discover/get_fm_language";
    /**
     * 请求流派列表数据
     */
    public static final String LOAD_DISCOVER_SECT_FM_LIST = NETSERVICEDOMIN + "/discover/get_fm_liupai";
    /**
     * 请求音乐列表数据
     */
    public static final String LOAD_DISCOVER_GET_FM_GENRE = NETSERVICEDOMIN + "/discover/get_fm_genre";
    /**
     * 请求网络列表数据
     */
    public static final String LOAD_DISCOVER_NETWORK_FM_LIST = NETSERVICEDOMIN + "/discover/get_fm_network";
    /**
     * 请求电视列表数据
     */
    public static final String GET_FM_TV = NETSERVICEDOMIN + "/discover/get_fm_tv";

    /**
     * 请求类型的专辑列表
     */
    public static final String LOAD_DISCOVER_TYPE_NORMAL_ALBUM_FM_LIST = NETSERVICEDOMIN + "/discover/get_discover_vod_list";
    /**
     * 请求类型的全部专辑列表
     */
    public static final String LOAD_DISCOVER_TYPE_ALL_ALBUM_FM_LIST = NETSERVICEDOMIN + "/discover/get_discover_vod_all_list";
    /**
     * 请求主播列表
     */
    public static final String LOAD_DISCOVER_TYPE_ANCHOR_FM_LIST = NETSERVICEDOMIN + "/discover/get_user_list_by_type";
    /**
     * 请求私人电台列表
     */
    public static final String LOAD_DISCOVER_PRIVATE_RADIO_LIST = NETSERVICEDOMIN + "/discover/get_user_fm";
    /**
     * 请求推荐私人电台列表
     */
    public static final String LOAD_DISCOVER_PRIVATE_RADIO_RECOMMENT_LIST = NETSERVICEDOMIN + "/discover/get_userfm_recommend";
    /**
     * 获取区域列表(for anchor)
     */
    public static final String LOAD_DISCOVER_ANCHOR_AREA_LIST = NETSERVICEDOMIN + "/discover/get_anchor_area_list";
    /**
     * 获取区域频率列表(for anchor)
     */
    public static final String LOAD_DISCOVER_ANCHOR_AREA_FM_LIST = NETSERVICEDOMIN + "/discover/get_anchor_area_fm";
    /**
     * 根据区域获取主持人列表(for anchor)
     */
    public static final String LOAD_DISCOVER_ANCHOR_AREA_ANCHOR_LIST = NETSERVICEDOMIN + "/discover/get_anchor_area_anchor";
    /**
     * 根据频率获取主持人列表(for anchor)
     */
    public static final String LOAD_DISCOVER_ANCHOR_FM_ANCHOR_LIST = NETSERVICEDOMIN + "/discover/get_anchor_fm_anchor";
    /**
     * 发现-节目单-节目推荐信息列表(for anchor)
     */
    public static final String LOAD_DISCOVER_PROGRAMME_RECOMMEND_LIST = NETSERVICEDOMIN + "/discover/get_programme_recommend";
    /**
     * 发现-节目单-节目-读取节目单信息列表(for anchor)
     */
    public static final String LOAD_DISCOVER_PROGRAMME_BY_CLASS_AREA_LIST = NETSERVICEDOMIN + "/discover/get_discover_programme_v1_3";
    /**
     * 发现-节目单-电台-读取推荐电台信息列表(for anchor)
     */
    public static final String LOAD_DISCOVER_PROGRAMME_FM_LIST = NETSERVICEDOMIN + "/discover/get_programme_fm_recommend";
    /**
     * 发现-节目单-电台-读取频率列表信息列表(for anchor)
     */
    public static final String LOAD_DISCOVER_PROGRAMME_FM_BY_CLASS_LIST = NETSERVICEDOMIN + "/discover/get_programme_fm_by_class_area_v1_3";
    /**
     * 发现-节目单-节目-选项配置(for anchor)
     */
    public static final String LOAD_DISCOVER_PROGRAMME_CLASS_AREA_LANG_LIST = NETSERVICEDOMIN + "/discover/get_programme_class_area_lang_config_v1_3";
    /**
     * 发现-专题-推荐专题列表【版本1.4】
     */
    public static final String SPECIALTOPICS_RECOMMEND = NETSERVICEDOMIN + "/discover/get_specialtopics_recommend";
    /**
     * 发现-专题-全部列表【版本1.4】
     */
    public static final String SPECIALTOPICS_LIST = NETSERVICEDOMIN + "/discover/get_specialtopics_list";
	/**
	 * 发现-播客-邀请赛
	 */
	public static final String LOAD_DISCOVER_2015ZBDS= NETSERVICEDOMIN + "/discover/get_2015zbds_album_list";
    /**
     * 添加自己的私人电台
     */
    public static final String PRIVATE_RADIO_ADD = NETSERVICEDOMIN + "/userfm/create_user_fm";
    /**
     * 删除自己的私人电台
     */
    public static final String PRIVATE_RADIO_DELETE = NETSERVICEDOMIN + "/userfm/delete_user_fm";
    
    /**
     * 读取某个用户的私人电台列表
     */
    public static final String PRIVATE_RADIO_LIST_BY_USER = NETSERVICEDOMIN + "/userfm/get_user_fm_list";
    /**
     * 读取某个私人电台的信息
     */
    public static final String PRIVATE_RADIO_GET_INFO = NETSERVICEDOMIN + "/userfm/get_user_fm_detail";
    /**
     * 读取某个私人电台下的音频列表
     */
    public static final String PRIVATE_RADIO_GET_VOD = NETSERVICEDOMIN + "/userfm/get_user_fm_audios";
    /**
     * 读取某个私人电台下的节目列表
     */
    public static final String PRIVATE_RADIO_GET_PROGRAM = NETSERVICEDOMIN + "/userfm/get_programme_list";
    /**
     * 置顶节目
     */
    public static final String PRIVATE_RADIO_UP_TOP_PROGRAM = NETSERVICEDOMIN + "/userfm/set_first_programme";
    /**
     * 创建节目
     */
    public static final String PRIVATE_RADIO_CREATE_PROGRAM = NETSERVICEDOMIN + "/userfm/create_programme";
    /**
     * 修改节目
     */
    public static final String PRIVATE_RADIO_RESET_PROGRAM = NETSERVICEDOMIN + "/userfm/update_programme";
    /**
     * 删除节目
     */
    public static final String PRIVATE_RADIO_DELETE_PROGRAM = NETSERVICEDOMIN + "/userfm/delete_programme";
	/**
     * 读取私人电台下的单个节目的信息
     */
    public static final String PRIVATE_FM_PROGRAM_GET_INFO = NETSERVICEDOMIN + "/userfm/get_programme_detail";
    /**
     * 读取节目下的音频列表
     */
    public static final String PRIVATE_FM_PROGRAM_VOD_GET_INFO = NETSERVICEDOMIN + "/userfm/get_programme_audios";
    /**
     * 排序私人电台下的节目音频列表
     */
    public static final String PRIVATE_FM_PROGRAM_VOD_SORT = NETSERVICEDOMIN + "/userfm/save_programme_audios";
    /**
     * 排序私人电台下的音频列表
     */
    public static final String PRIVATE_FM_VOD_SORT = NETSERVICEDOMIN + "/userfm/save_user_fm_audios";
    /**
     * 将音频加入到私人电台
     */
    public static final String PRIVATE_RADIO_ADD_VOD = NETSERVICEDOMIN + "/userfm/add_audios_to_user_fm";
    /**
     * 将音频加入到节目
     */
    public static final String PRIVATE_RADIO_PROGRAM_ADD_VOD = NETSERVICEDOMIN + "/userfm/add_audios_to_programme";
    /**
     * 将音频移出私人电台
     */
    public static final String PRIVATE_RADIO_DELETE_VOD = NETSERVICEDOMIN + "/userfm/remove_audios_from_user_fm";
    /**
     * 将音频移出节目
     */
    public static final String PRIVATE_RADIO_PROGRAM_DELETE_VOD = NETSERVICEDOMIN + "/userfm/remove_audios_from_programme";
    /**
     * 设置私人电台音频别名
     */
    public static final String PRIVATE_RADIO_CHANGE_VOD_NAME = NETSERVICEDOMIN + "/userfm/set_user_fm_vod_alias";
    /**
     * 设置节目音频别名
     */
    public static final String PRIVATE_RADIO_CHANGE_PROGAM_VOD_NAME = NETSERVICEDOMIN + "/userfm/set_programme_vod_alias";
    /**
     * 获取所有系统保留标签
     */
    public static final String PRIVATE_RADIO_GET_SYSTEM_STYLE = NETSERVICEDOMIN + "/userfm/get_all_reserved_tags";
    /**
     * 创建标签
     */
    public static final String PRIVATE_RADIO_ADD_CUSTOM_STYLE = NETSERVICEDOMIN + "/userfm/create_tag";
    /**
     * 获取类型列表
     */
    public static final String PRIVATE_RADIO_GET_TAG = NETSERVICEDOMIN + "/userfm/get_user_fm_content_class_list";
    /**
     * 修改私人电台
     */
    public static final String PRIVATE_RADIO_EDIT = NETSERVICEDOMIN + "/userfm/update_user_fm";
    /**
     * 单节目私人电台升级为多节目私人电台
     */
    public static final String PRIVATE_RADIO_EDIT_CHANGE_TO_MORE = NETSERVICEDOMIN + "/userfm/change_single_programme_user_fm_to_multi";
    /**
     * 请求用户的收集电台列表
     */
    public static final String COLLECTION_RADIO_LIST_BY_USER = NETSERVICEDOMIN + "/live/get_user_live_clue_list";
    /**
     * 请求国家列表
     */
    public static final String GET_COUNTRY_LIST_BY_NETWORK = NETSERVICEDOMIN + "/live/get_live_clue_country_list";
    /**
     * 请求语言列表
     */
    public static final String GET_LANGUAGE_LIST_BY_NETWORK = NETSERVICEDOMIN + "/live/get_live_clue_lang_list";
    /**
     * 请求收集电台分类列表
     */
    public static final String GET_RADIO_TYPE_LIST_BY_NETWORK = NETSERVICEDOMIN + "/live/get_live_clue_content_class_list";
    /**
     * 请求添加收集电台
     */
    public static final String ADD_COLLECTION_RADIO_TO_NETWORK = NETSERVICEDOMIN + "/live/add_live_clue";
    /**
    * 请求编辑收集电台
    */
    public static final String EDIT_COLLECTION_RADIO_TO_NETWORK = NETSERVICEDOMIN + "/live/edit_live_clue";
    
    //用户
    /**读取某个用户的信息*/
    public static final String USER_READ_INFO = NETSERVICEDOMIN + "/user/get_user_info";
    /**修改个人资料*/
    public static final String USER_UPDATE_INFO = NETSERVICEDOMIN + "/user/save_profile";
    /**读取用户新消息和新通知数据*/
    public static final String USER_READ_NOTICE = NETSERVICEDOMIN + "/user/get_user_new_num";
    /**关注用户*/
    public static final String USER_FOLLOW = NETSERVICEDOMIN + "/user/follow_user";
    /**取消关注*/
    public static final String USER_UN_FOLLOW = NETSERVICEDOMIN + "/user/cancel_follow_user";
    /**读取用户关注列表*/
    public static final String USER_READ_FOLLOW = NETSERVICEDOMIN + "/user/get_user_follow";
    /**读取用户粉丝列表*/
    public static final String USER_READ_FANS = NETSERVICEDOMIN + "/user/get_user_fans";
    /**关注机构、频率、节目*/
    public static final String USER_FOLLOW_LIVE = NETSERVICEDOMIN + "/user/follow_live";
    /**取消关注机构、频率、节目*/
    public static final String USER_UN_FOLLOW_LIVE = NETSERVICEDOMIN + "/user/cancel_follow_live";
    /**读取机构、频率、节目粉丝列表*/
    public static final String USER_READ_FOLLOW_LIVE = NETSERVICEDOMIN + "/user/get_live_fans";
    /**读取用户正在主持的节目列表*/
    public static final String USER_READ_PROGRAM_LIVE = NETSERVICEDOMIN + "/user/get_user_programs"; 
    /**读取某个用户的播客专辑列表*/
    public static final String USER_READ_BOKE_LIVE = NETSERVICEDOMIN + "/podcast/user_album_list";
    /**保存用户的喜欢调查数据**/
    public static final String USER_SAVE_LIKE = NETSERVICEDOMIN + "/like/save_like";
    /**读取喜欢调查数据**/
    public static final String GET_SAVE_LIKE = NETSERVICEDOMIN + "/like/get_like";
    /**同步喜好数据**/
    public static final String GET_SYNC_LIKE = NETSERVICEDOMIN + "/like/sync_like_data";
    /**是否显示喜好数据**/
    public static final String SHOW_LIKE = NETSERVICEDOMIN + "/like/is_show_like";
    
    //专辑
    /**读取某个用户的播客专辑列表（我的播客）*/
    public static final String USER_ABLUM_LIST = NETSERVICEDOMIN + "/podcast/user_album_list";
    /**读取某个用户的播客音频列表*/
    public static final String USER_VOD_LIST = NETSERVICEDOMIN + "/podcast/user_vod_list";
    /**读取某个专辑下的音频列表*/
    public static final String ALBUM_VOD_LIST = NETSERVICEDOMIN + "/podcast/album_vod_list";
    /**读取某个专辑的详细信息*/
    public static final String ALBUM_DETAIL = NETSERVICEDOMIN + "/podcast/get_album_detail";
    /**读取用户的上传专辑列表（我的专辑）*/
    public static final String USER_UPLOAD_ALBUM_LIST = NETSERVICEDOMIN + "/podcast/user_upload_album_list";
    
    /**提供专辑或音频下载地址*/
    public static final String HOME_READ_VOD_DOWNLOAD = NETSERVICEDOMIN + "/vod/download";
    /**提供私人电台单个节目下载地址*/
    public static final String USERFM_DOWNLOAD_USER_FM = NETSERVICEDOMIN + "/userfm/download_user_fm";
    /**提供私人电台单个节目中某个音频下载地址*/
    public static final String AUDIO_OF_USER_FM = NETSERVICEDOMIN + "/userfm/download_audio_of_user_fm";
    /**编辑资料 头像上传地址*/
    public static final String EDITDATA_FACE_UPLOAD = NETSERVICEDOMIN + "/upload/upload_image";
    /**提供私人电台多节目下载地址*/
    public static final String PROGRAMME_ALL = NETSERVICEDOMIN + "/userfm/download_programme";
    /**提供私人电台多节目中某个音频下载地址*/
    public static final String AUDIO_OF_PROGRAMME = NETSERVICEDOMIN + "/userfm/download_audio_of_programme";
    
    //修改个人资料
    public static final String UPDATE_PERSON_PIC = NETSERVICEDOMIN + "/upload/upload_image";
    public static final String UPDATE_PERSON_DATA = NETSERVICEDOMIN + "/user/save_profile";
    public static final String FAVORITE_MY_RADIO = NETSERVICEDOMIN + "/vod/get_favo_fm_info_v1_3";
    
   
    //我的电台
    /**
     * 返回用户收藏的所有电台ID
     */
    public static final String ADD_FM_ALLID = NETSERVICEDOMIN + "/vod/get_all_favorite_fm_id_v1_3";
	/** 添加电台中的地区配置*/
	public static final String ADD_AREA_CONFIG = NETSERVICEDOMIN + "/vod/get_favorite_fm_area_config";
	/**推荐列表*/
	public static final String ADD_RECOMMEND = NETSERVICEDOMIN + "/vod/get_favorite_fm_recmmend_list";
	/** 根据区域获取频率列表*/
	public static final String ADD_BY_AREA = NETSERVICEDOMIN + "/vod/get_fm_list_by_area";
	/** 添加电台中的网络*/
	public static final String ADD_NET_RADIO = NETSERVICEDOMIN + "/vod/get_favorite_fm_list_network";
		/** 取消收藏 */
	public static final String CANCEL_FAVORITE = NETSERVICEDOMIN + "/vod/cancel_favorite";
	/** 添加收藏*/
	public static final String ADD_FAVORITE = NETSERVICEDOMIN + "/vod/add_favorite";

	/*播放start-----------------------------------------------------------*/
	/**今日精选*/
	public static final String PLAY_TODAY_SELECTION = NETSERVICEDOMIN + "/recommendv2/get_today_selected";
    /**点播播放器*/
	public static final String PLAY_ALONE_VOD = NETSERVICEDOMIN + "/vod/play_vod_v1_5";
	/**直播播放器*/
	public static final String PLAY_LIVE = NETSERVICEDOMIN + "/live/play_fm_v1_3";
	/** 点播播放统计*/
	public static final String PLAY_VOD_COUNTER= NETSERVICEDOMIN + "/vod/play_vod_counter";
	/** 直播播放统计*/
	public static final String PLAY_LIVE_COUNTER = NETSERVICEDOMIN + "/live/play_fm_counter";
	/** 直播播放节目统计*/
	public static final String PLAY_LIVE_PROGRAM_COUNTER = NETSERVICEDOMIN + "/live/play_program_counter";
	/** 私人电台统计 */
	public static final String PLAY_USERFM_COUNTER = NETSERVICEDOMIN + "/userfm/play_user_fm_counter";
	/** 私人电台节目统计 */
	public static final String PLAY_USERFM_PROGRAM_COUNTER = NETSERVICEDOMIN + "/userfm/play_programme_counter";
	/**相似推荐*/
	public static final String PLAY_SIMLAR = NETSERVICEDOMIN + "/recommend/get_maybe_love_v1_3";
	/**私人电台单节目播放*/
	public static final String PLAY_USERFM = NETSERVICEDOMIN + "/userfm/play_userfm";
	/**私人电台多节目播放*/
	public static final String PLAY_USERFM_PROGRAM = NETSERVICEDOMIN + "/userfm/play_userfm_program";
	/**获取私人电台节目列表*/
	public static final String USERFM_PROGRAMME_LIST = NETSERVICEDOMIN + "/userfm/get_programme_list";

    //播放列表主播身份
    public static final String PLAY_ANCHOR = NETSERVICEDOMIN + "/vod/get_anchors";
    /*播放end-----------------------------------------------------------*/

	//tianhu 我的订阅       begin
	/**获取订阅的专辑编号列表*/
	public static final String SUBSCRIBE_GET_SUBSCRIBE_LIST = NETSERVICEDOMIN + "/vod/get_subscribe_album_id_list";
	/**获取订阅的专辑信息*/
	public static final String SUBSCRIBE_GET_SUBSCRIBE_ALBUM_INFO = NETSERVICEDOMIN + "/vod/get_subscribe_album_info";
	/**获取订阅分类列表*/
	public static final String SUBSCRIBE_GET_SUBSCRIBE_TYPE_LIST= NETSERVICEDOMIN + "/vod/get_subscribe_type_list";
	/**获取某个分类的可订阅专辑*/
	public static final String SUBSCRIBE_ALBUM_LIST_BY_TYPE= NETSERVICEDOMIN + "/vod/get_subscribe_album_list_by_type";
	/**获取专辑订阅推荐列表*/
	public static final String SUBSCRIBE_RECOMMEND_LIST= NETSERVICEDOMIN + "/vod/get_album_subscribe_recmmend_list";
	/**添加订阅*/
	public static final String SUBSCRIBE_ADD_SUBSCRIBE= NETSERVICEDOMIN + "/vod/add_subscribe";
	/**取消订阅*/
	public static final String SUBSCRIBE_CANCEL_SUBSCRIBE= NETSERVICEDOMIN + "/vod/cancel_subscribe";
	/**设置有新的订阅已读状态 */
	public static final String SUBSCRIBE_UPDATA_READ= NETSERVICEDOMIN + "/vod/update_subscribe_read";
	/**同步订阅——张强 */
	public static final String SUBSCRIBE_SYNC= NETSERVICEDOMIN + "/vod/sync_subscribe";
	/**同步收藏——张强 */
	public static final String FAVORITE_SYNC= NETSERVICEDOMIN + "/vod/sync_favorite";
	/**转发音频、专辑、节目、频率、发言、私人电台 */
	public static final String FORWARD= NETSERVICEDOMIN + "/forward/send_forward";
	/**读取用户关注列表*/
    public static final String GET_USER_FOLLOW = NETSERVICEDOMIN + "/user/get_user_follow";
    /**读取评论数量——张强**/
    public static final String GET_COMMENT_COUNT = NETSERVICEDOMIN + "/comment/get_comment_total";
	/**发送评论*/
    public static final String SEND_COMMENT = NETSERVICEDOMIN + "/comment/send_comment"; 
	/**获取评论*/
    public static final String GET_COMMENT_LIST = NETSERVICEDOMIN + "/comment/get_comment_list";
	/**顶  peak*/
    public static final String COMMENT_DIGG= NETSERVICEDOMIN + "/digg/digg";
	/**踩  treak*/
    public static final String COMMENT_EGG= NETSERVICEDOMIN + "/digg/egg";
	/**评论、聊吧、私信音频上传——潘峰*/
    public static final String UPLOAD_UPLOAD_AUDIO= NETSERVICEDOMIN + "/upload/upload_audio";
	/**发送私信——潘峰*/
    public static final String LETTER_SEND_MESSAGE= NETSERVICEDOMIN + "/user/send_message";
	/**读取与某个用户之间的私信列表——潘峰*/
    public static final String GET_MESSAGE_LIST_USER= NETSERVICEDOMIN + "/user/get_message_list_with_user";
	/**读取新消息列表——潘峰*/
    public static final String GET_USER_MESSAGE_LIST= NETSERVICEDOMIN + "/user/get_user_new_message_list";
	/**保存私信隐私设置——潘峰*/
    public static final String SAVE_MESSAGE_ACL= NETSERVICEDOMIN + "/user/save_message_acl";
    
	/**返回一组音频在专辑、节目、私人电台、私人电台节目等中的序号——张强*/
    public static final String GET_VODS_ORDER= NETSERVICEDOMIN + "/vod/get_vods_order_v1_4";
    
	//tianhu 我的订阅    end

    //tianhu 原创播客-----------------start

    /*播客原创首页——张强*/
    public static final String GET_BOKE_RECOMMEND= NETSERVICEDOMIN + "/boke/recommend";
    //播客类型列表（播客二级频道）——张强
    public static final String GET_BOKE_PODCASTT_TYPE_LIST= NETSERVICEDOMIN + "/boke/podcast_type_list";
    //播客列表（按播客类型和推荐类型）——张强
    public static final String GET_BOKE_PODCAST_TYPE_ALBUM_LIST= NETSERVICEDOMIN + "/boke/podcast_type_album_list";
    //听听自制——张强
    public static final String GET_BOKE_HOMEMADE= NETSERVICEDOMIN + "/boke/homemade";
    //地区列表——张强
    public static final String GET_BOKE_AREA_LIST= NETSERVICEDOMIN + "/boke/area_list";
    //地区播客列表——张强
    public static final String GET_BOKE_AREA_ALBUM_LIST= NETSERVICEDOMIN + "/boke/area_album_list";
    //地区列表（同城）——张强
    public static final String GET_BOKE_GPS_AREA_LIST= NETSERVICEDOMIN + "/boke/gps_area_list";
    //地区列表（同城）——张强
    public static final String GET_BOKE_GPS_AREA_ALBUM_LIST= NETSERVICEDOMIN + "/boke/gps_area_album_list";
    //tianhu 原创播客-----------------end

    /**
     *  摇一摇接口
     */
    //识别正在播放的频率
    public static final String SHAKE_WAVUPDATA = "http://221.122.71.122/shake.php";
    //用户摇一摇，调此接口返回活动信息  0 未识别成功，返回列表进行显示
    public static final String SHAKE_GET_ACTIVITY = TTUrlSwitch.SHAKEURL+"/activity/get_activity";
    //进入摇奖主页(H5页)
    public static final String SHAKE_YAO_ENTER = TTUrlSwitch.YAOPRIZEH5+"/yao/enter";
    //进入中奖详情页
    public static final String SHAKE_AWARD_DETAIL = TTUrlSwitch.YAOPRIZEH5+"/yao/award_detail?collect_id=";

	/** 频率节目单*/
	public static final String GET_FREQUENCY_PROGRAME_LIST= NETSERVICEDOMIN + "/live/fm_programme_v1_2";
	/**获取频率信息以及该频率的主持人列表。*/
	public static final String GET_FREQUENCY_DETAIL= NETSERVICEDOMIN + "/live/fm_detail";
	/**获取一个机构的信息以及该机构频率的列表*/
	public static final String GET_INSTITUTION_DETAIL= NETSERVICEDOMIN + "/live/radio_detail";
	/**获取该机构的主持人列表*/
	public static final String GET_INSTITUTION_ANCHOR= NETSERVICEDOMIN + "/live/radio_anchor_list";
	/**通过节目ID获取节目信息*/
	public static final String GET_PROGRAM_DETAIL= NETSERVICEDOMIN + "/live/program_detail";
	/**获取节目音频列表*/
	public static final String GET_PROGRAM_LIST_DETAIL= NETSERVICEDOMIN + "/live/program_vod_list";

    /*** live playing start **/
    /**
     * 特别推荐 （首页，频道页，正在直播首页、综艺、赛事，电台）
     */
    public static final String RECOMMEND_PAGE_LIST = NETSERVICEDOMIN + "/discover/get_special_recommend_page_list";
    /*** 读取"正在直播" 配置列表——李雷 */
    public static final String LIVE_PLAY_CATEGORY_CONFIG = NETSERVICEDOMIN + "/discover/get_playing_category_config";
    /** 读取"正在直播" 推荐(首页)数据——李雷 */
    public static final String LIVE_PLAY_RECOMMEND_LIST = NETSERVICEDOMIN + "/discover/get_playing_recommend_list";
    /*** 读取"正在直播" 筛选数据— 二级频道数据—李雷*/
    public static final String LIVE_PLAY_RECOMMEND_FILTER_LIST = NETSERVICEDOMIN + "/discover/get_playing_recommend_filter_list";
    /*** live playing end **/

    /** 听听商城*/
    public static final String TT_MALL_URL = "http://www.wemart.cn/v2/weimao/index.html?shopId=shop000201604262576";
    /** 悦驾精灵 说明书*/
    public static final String TT_JOYRIDE_YJJL_URL = "http://m.tingtingfm.com/topic/yjjl/";
	//转发
	/**
	 * 读取用户相关的转发列表 
	 */

	public static final String GET_FORWORD_LIST= NETSERVICEDOMIN + "/forward/get_user_forward_list";
	/**
	 * 删除自己的某条转发
	 */
	public static final String DELETE_FORWORD_ITEM= NETSERVICEDOMIN + "/forward/delete_forward";
	public static final String DELETE_FORWORD_LIST= NETSERVICEDOMIN + "/forward/clear_forward";
    /**
     * 摇广播 我的奖品列表
     */
    public static final String GET_AWARD_LIST = TTUrlSwitch.SHAKEURL + "/award/get_user_award_list";
    /**
     * 摇广播 过期奖品列表
     */
    public static final String GET_EXPIRE_AWARD_LIST = TTUrlSwitch.SHAKEURL + "/award/get_user_expire_award_list";
    /**
     * 摇广播 删除某个过期奖品
     */
    public static final String DELETE_EXPIRE_AWARD = TTUrlSwitch.SHAKEURL + "/award/delete_user_expire_award";
    /**
     * 摇广播 清空过期奖品
     */
    public static final String CLEAR_EXPIRE_AWARD = TTUrlSwitch.SHAKEURL + "/award/clear_user_expire_award";


    /**
	 * 版本升级
	 */
	public static final String CHECK_VERSION_UPDATE = NETSERVICEDOMIN + "/config/check_version";
	
	/**
	 * 心跳 存活接口
	 */
	public static final String  HEARTBEAT_TOSURVIVE = TTUrlSwitch.CHANNELURL +"/record/tosurvive";

	/**
	 * 全局配置接口
	 */
	public static final String GLOBAL_CONFIG = NETSERVICEDOMIN + "/config/global_config";
	
	/**
	 * 应用换量接口
	 */
	public static final String APP_STORE_MAIN = TTUrlSwitch.CHANNELURL + "/app/app_list";
	/**
	 * 单个应用的数据
	 */
	public static final String APP_STORE_DETAIL = TTUrlSwitch.CHANNELURL + "/app/app_info";
	
	/**
	 * 应用换量下载统计接口
	 */
	public static final String APP_STORE_DOWNLOAD_RECORD = TTUrlSwitch.CHANNELURL + "/app/down_count_num";
	
	/**
	 * 分享
	 */
	public static final String  SHARE_ALBUM_URL =  TTUrlSwitch.SHAREURL + "/album/play/";
	public static final String  SHARE_PROGRAM_URL = TTUrlSwitch.SHAREURL + "/program/play/";
	public static final String  SHARE_VOD_URL = TTUrlSwitch.SHAREURL + "/vod/play/";
	public static final String  SHARE_FM_URL = TTUrlSwitch.SHAREURL + "/fm/play/";
	public static final String  SHARE_USERFM_URL = TTUrlSwitch.SHAREURL + "/userfm/play/";
	public static final String  SHARE_SPECIALTOPICS_URL = TTUrlSwitch.SHAREURL + "/topic/";
	
	
	/**
	 * Push接口
	 */
	public static final String PUSH_OPEN = NETSERVICEDOMIN + "/push/open_push";
	public static final String PUSH_CLOSE = NETSERVICEDOMIN + "/push/close_push";
	public static final String PUSH_ARRIVE = NETSERVICEDOMIN + "/push/record_push_arrive";
	public static final String PUSH_CLICK = NETSERVICEDOMIN + "/push/record_push_open";
	public static final String PUSH_BIND_DEVICE = NETSERVICEDOMIN + "/push/bind_client_channelid";
	public static final String PUSH_BIND_USER = NETSERVICEDOMIN + "/push/bind_userid_channelid";
	public static final String PUSH_UNBIND_USER = NETSERVICEDOMIN + "/push/unbind_userid_channelid";
	public static final String PUSH_GET_CHANNEL_ID = NETSERVICEDOMIN + "/push/get_channel_id";
	public static final String PUSH_UNBIND_USER_SPECIAL = NETSERVICEDOMIN + "/push/unbind_user_channelid_for_special";


    public static final String ACTION_URL = TTUrlSwitch.SHAREURL + "/freedata/enter";
}
