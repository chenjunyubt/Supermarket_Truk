/**
 * 
 */
package com.finance.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.finance.core.TTApplication;

/**
 * @author liqiang
 * @data 2014年9月13日
 *
 */
public class PreferencesCache {
    private static SharedPreferences sp = null;
    private static SharedPreferences accountPre = null;
    private static SharedPreferences global = null;
    private static SharedPreferences favoritePre = null;
    private static SharedPreferences downloadPre = null;
    private static SharedPreferences playPre = null;

    private static final String PREFER_NAME = "tt_prefer";
    private static final String PREFER_ACCOUNT = "tt_account";
    private static final String PREFER_GLOBAL = "tt_global";
    private static final String PREFER_FAVORITE = "tt_Favorite";
    private static final String PREFER_DOWNLOAD = "tingting_setting";
    private static final String PREFER_PLAY = "tt_play";
    private static final int PREFER_MODE = Context.MODE_PRIVATE;
    
    public static SharedPreferences getConfigurationPrefer() {
    	if (sp == null) {
    		sp = TTApplication.getAppContext().getSharedPreferences(PREFER_NAME, PREFER_MODE);
    	}
    	
    	return sp;
    }
    
    public static SharedPreferences getAccountPrefer() {
    	if (accountPre == null) {
    		accountPre = TTApplication.getAppContext().getSharedPreferences(PREFER_ACCOUNT, PREFER_MODE);
    	}
    	
    	return accountPre;
    }
    
    public static SharedPreferences getDownloadPrefer() {
    	if (downloadPre == null) {
    		downloadPre = TTApplication.getAppContext().getSharedPreferences(PREFER_DOWNLOAD, PREFER_MODE);
    	}
    	
    	return downloadPre;
    }
    
    /**
     * 该缓存文件存储的标志都是能够根据服务器配置来清除
     * @return
     */
    public static SharedPreferences getGlobalPrefer() {
    	if (global == null) {
    		global = TTApplication.getAppContext().getSharedPreferences(PREFER_GLOBAL, PREFER_MODE);
    	}
    	
    	return global;
    }
    
    
    /**
     * 删除当前应用可变缓存
     */
    public static void clearGlobalPrefer() {
    	if (global == null)
    		return;
    	
    	global.edit().clear().commit();
    }
    
    /**
     * 该缓存文件是缓存收藏的状态
     */
    public static SharedPreferences getFavoritePrefer() {
    	if (favoritePre == null) {
    		favoritePre = TTApplication.getAppContext().getSharedPreferences(PREFER_FAVORITE, PREFER_MODE);
    	}
    	
    	return favoritePre;
    }

    /**
     * 该缓存文件是缓存播放相关数据
     * @return
     */
    public static SharedPreferences getPlayPrefer() {
        if (playPre == null) {
            playPre = TTApplication.getAppContext().getSharedPreferences(PREFER_PLAY, PREFER_MODE);
        }

        return playPre;
    }
}
