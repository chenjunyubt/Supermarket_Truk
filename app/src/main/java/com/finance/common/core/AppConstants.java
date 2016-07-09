package com.finance.common.core;

/**
 * Created by lqsir on 2016/4/6.
 */
public class AppConstants {
    /**
     * 收藏成功
     */
    public static final int FAVORITESUCCESS = 0x2001;
    /**
     * 收藏失败
     */
    public static final int FAVORITEFAIL = 0x2002;
    /**
     * 取消收藏成功
     */
    public static final int CANCELFAVORITESUCCESS = 0x2003;
    /**
     * 取消收藏失败
     */
    public static final int CANCELFAVORITEFAIL = 0x2004;

    //预约成功
    public static final int ORDER_SUCCESS = 0x3001;
    //取消预约
    public static final int ORDER_CANCEL_SUCCESS = 0x3002;

    /**
     * 固定标签编号
     * 100：'内容水平差', 101：'音质不佳', 102：'信号不稳定', 103：'屏蔽此专辑', 104：'屏蔽此专题', 105：'不喜欢'
     */
    public static final int UNLIKE_TYPE_CON = 100;
    public static final int UNLIKE_TYPE_VOD = 101;
    public static final int UNLIKE_TYPE_WIF = 102;
    public static final int UNLIKE_TYPE_ALBUM = 103;
    public static final int UNLIKE_TYPE_SPECIAL = 104;
    public static final int UNLIKE_TYPE_UNL = 105;


    public static final int PLAY_OR_PAUSE_STATUS = 0x2100;//播放器调用播放，控制播放暂停

}
