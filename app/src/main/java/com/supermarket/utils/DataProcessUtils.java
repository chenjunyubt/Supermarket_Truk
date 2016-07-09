package com.supermarket.utils;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;

import com.finance.cache.CacheManager;
import com.finance.common.http.HttpUtils;
import com.finance.core.TTApplication;
import com.supermarket.response.ErrorCodeResp;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class DataProcessUtils {
	private static final String TAG = "TTFM/ProcessUtils >>> ";
    static Gson sGson = new Gson();
    static CacheManager cacheManager = new CacheManager(TTApplication.getAppContext());
    
    /**
     * 服务器异常
     * @author lqsir
     *
     */
	public static class ServerException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public ServerException(String msg) {
			super(msg);
		}
	}
	
	/**
	 * 数据异常
	 * @author lqsir
	 *
	 */
    public static class DataException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public DataException(ErrorCodeResp error, String msg) {
            super(msg);
            mError = error;
        }
        
        public ErrorCodeResp mError;
    }	

	@SuppressWarnings("unchecked")
	private static <T> T fromJson(String resp, Class<?> classOfT)
			throws ServerException, DataException, IOException {
		JsonObject elem = sGson.fromJson(resp, JsonObject.class);
		
    		if (!elem.has("errno")) {
                throw new ServerException(elem.toString());
    		} else if (elem.get("errno").getAsInt() != 0) {
    		    throw new DataException(sGson.fromJson(elem, ErrorCodeResp.class), elem.toString());
    		}
    		return (T) sGson.fromJson(elem, classOfT);
	}
	
	public static <T> T postJson(String url, Object params, Class<?> classOfT)
			throws ServerException, IOException {
		return DataProcessUtils.fromJson(postSyncString(url, params), classOfT);
	}
	
	public static <T> T getJson(String url, Object params, Class<?> classOfT)
			throws ServerException, IOException {
		return DataProcessUtils.fromJson(HttpUtils.getSync(url, params), classOfT);
	}
	
	public static String getCustomSyncString (String url, Object params) 
			throws IOException, ServerException, DataException, JSONException {
		return HttpUtils.getSync(url, params);
	}

	private static String postSyncString(String url, Object params) throws IOException {
		Map<String, String> m = new Gson().typeToType(params,
				new TypeToken<Map<String, String>>() {
				}.getType());
		
		boolean isCache = false;
		String page = null;
		//1.检查是否开启缓存
		if (m.containsKey("isCache")) {
			isCache = m.get("isCache").equals("false") ? false : true;
			page = m.get("page");
			if (isCache && page != null) {
				//2.直接从缓存获取数据
				if (cacheManager.requestisavailable(url, page)) {
					return cacheManager.getCache(url, page);
				}
			}
		}
		
		//3.直接从网络获取数据
		String content = HttpUtils.postSync(url, null, m);
		TTLog.e(System.currentTimeMillis() + "");
		TTLog.d(content);
		if (isCache && page != null) {
			cacheManager.putCache(url, page, content);
		}
		return content;
	}



}
