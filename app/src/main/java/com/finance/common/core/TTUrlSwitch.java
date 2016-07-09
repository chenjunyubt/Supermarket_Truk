package com.finance.common.core;

import com.supermarket.utils.TTLog;

public class TTUrlSwitch {
	enum EnumDevPlatform {
		PF_DEV,
		PF_TEST,
		PF_W_TEST,
		PF_RELEASE;
	}
	
	public static String SERVERURL;
	public static String CHANNELURL;
	public static String H5URL;
	public static String SHAREURL;


	//摇一摇接口前缀
	public static String SHAKEURL;

	//进入摇奖主页H5页前缀
	public static String YAOPRIZEH5;

	//配置当前应用环境地址
	public final static EnumDevPlatform epf = EnumDevPlatform.PF_RELEASE;

    // 摇一摇 摇奖接口地址 ----------YAOPRIZEH5
    final static String YAOPRIZE_URL_DEV = "http://m.yao.dev.ting-ting.cn";// 开发环境
    final static String YAOPRIZE_URL_TEST = "http://m.yao.test.ting-ting.cn";// 内网测试
    final static String YAOPRIZE_URL_RELEASE = "http://m.yao.tingtingfm.com";// 正式环境

    // 摇一摇 API地址  ------- SHAKEURL
    final static String SHAKE_URL_DEV = "http://api.yao.dev.ting-ting.cn";// 开发环境
    final static String SHAKE_URL_TEST = "http://api.yao.test.ting-ting.cn";// 内网测试环境
    final static String SHAKE_URL_W_TEST = "http://182.92.153.163:8080";// 外网测试环境
    final static String SHAKE_URL_RELEASE = "http://api.yao.tingtingfm.com";// 外网正式环境


    // API地址
	final static String SERVER_URL_DEV = "http://api.dev.ting-ting.cn";// 开发环境
	final static String SERVER_URL_TEST = "http://api.test.ting-ting.cn";// 内网测试环境
	final static String SERVER_URL_W_TEST = "http://123.57.37.65:8082";// 外网测试环境
	final static String SERVER_URL_RELEASE = "http://api1.tingtingfm.com";// 外网正式环境

	// 渠道地址
	final static String CHANNEL_URL_DEV = "http://api.union.dev.ting-ting.cn";
	final static String CHANNEL_URL_TEST = CHANNEL_URL_DEV;
	final static String CHANNEL_URL_W_TEST = "http://api.union.tingtingfm.com";
	final static String CHANNEL_URL_RELEASE = "http://api.union.tingtingfm.com";

	// H5页面地址
	final static String H5_URL_DEV = "http://m.dev.ting-ting.cn/2015zb";
	final static String H5_URL_TEST = "http://m.test.ting-ting.cn/2015zb";
	final static String H5_URL_W_TEST = "http://m.tingtingfm.com/2015zb";
	final static String H5_URL_RELEASE = "http://m.tingtingfm.com/2015zb";

	// 分享地址

	final static String SHARE_URL_DEV = "http://m.dev.ting-ting.cn";
	final static String SHARE_URL_TEST = "http://m.test.ting-ting.cn";
	final static String SHARE_URL_W_TEST = "http://m.tingtingfm.com";
	final static String SHARE_URL_RELEASE = SHARE_URL_W_TEST;

	//初始化平台对应请求接口
	static {
		switch (epf) {
		case PF_DEV:
			SERVERURL = SERVER_URL_DEV;
			CHANNELURL = CHANNEL_URL_DEV;
			H5URL = H5_URL_DEV;
			SHAREURL = SHARE_URL_DEV;
            SHAKEURL = SHAKE_URL_DEV;
            YAOPRIZEH5 = YAOPRIZE_URL_DEV;
			TTLog.enableLogging();
			break;
		case PF_TEST:
			SERVERURL = SERVER_URL_TEST;
			CHANNELURL = CHANNEL_URL_TEST;
			H5URL = H5_URL_TEST;
			SHAREURL = SHARE_URL_TEST;
            SHAKEURL = SHAKE_URL_TEST;
            YAOPRIZEH5 = YAOPRIZE_URL_TEST;
			TTLog.enableLogging();
			break;
		case PF_W_TEST:
			SERVERURL = SERVER_URL_W_TEST;
			CHANNELURL = CHANNEL_URL_W_TEST;
			H5URL = H5_URL_W_TEST;
			SHAREURL = SHARE_URL_W_TEST;
            SHAKEURL = SHAKE_URL_W_TEST;
			TTLog.enableLogging();
			break;
		case PF_RELEASE:
		default:
			SERVERURL = SERVER_URL_RELEASE;
			CHANNELURL = CHANNEL_URL_RELEASE;
			H5URL = H5_URL_RELEASE;
			SHAREURL = SHARE_URL_RELEASE;
            SHAKEURL = SHAKE_URL_RELEASE;
            YAOPRIZEH5 = YAOPRIZE_URL_RELEASE;
			TTLog.disableLogging();
			break;
		}
	}
}
