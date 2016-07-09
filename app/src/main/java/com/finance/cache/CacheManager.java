package com.finance.cache;

import android.content.Context;

public class CacheManager {
    private DBStringCache dbscache;
    public CacheManager(Context context){
        dbscache = new DBStringCache(context);
    }
    
    /**
     * 请求的缓存数据是否有效
     * true 数据库中有请求的缓存数据 ，且缓存未过期
     * false 数据库中没有请求对应的缓存数据，或缓存数据过期
     */
    public boolean requestisavailable(String url, String page){
        return dbscache.cacheIsAvailable(url, page, 5 * 60);
    }
    
    public String getCache(String url, String page){
        return dbscache.getCache(url, page);
    }
    
    public void putCache(String url, String page, String content) {
    	dbscache.putCache(url, page, content);
    }
	    
    public void clearToCache(Context context){
    	dbscache.clearToCache(context);
    }
}