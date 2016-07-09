package com.finance.common.http.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.supermarket.utils.DeviceUtils;

public class AjaxParams {
	private static final String TAG = "TTFM/AjaxParams >>> "; 
    private static String ENCODING = "UTF-8";

    protected ConcurrentHashMap<String, String> urlParams;
    protected ConcurrentHashMap<String, FileWrapper> fileParams;

    public AjaxParams() {
        init();
    }

    public AjaxParams(Map<String, String> source) {
        init();

        if( source == null ) {
        	return ;
        }
        
        for(Map.Entry<String, String> entry : source.entrySet()) {
        	if (!(entry.getKey().equals("isCache") || entry.getKey().equals("invalidTime"))) {
        		put(entry.getKey(), entry.getValue());
        	}
        }
        
        put("api_sign", getSecreteToken(urlParams));
    }
    
    /**
     * 对字符做处理，URLEncoder.encode对*不做转义，对空格做+号转义，*需要替换成%2A, +号需要替换成%20
     * Uri.encode *需要替换成%2A
     * @param value
     * @return
     */
    public String dispatchStr(String value) {
    	return value.replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    public AjaxParams(String key, String value) {
        init();
        put(key, value);
    }

    public AjaxParams(Object... keysAndValues) {
      init();
      int len = keysAndValues.length;
      if (len % 2 != 0)
        throw new IllegalArgumentException("Supplied arguments must be even");
      for (int i = 0; i < len; i += 2) {
        String key = String.valueOf(keysAndValues[i]);
        String val = String.valueOf(keysAndValues[i + 1]);
        put(key, val);
      }
    }
    
    private String getSecreteToken(ConcurrentHashMap<String, String> maps) {
    	String result = "";
    	try {
	    	Map<String, String> posts = new ConcurrentHashMap<String, String>(maps);
	        List<String> keys = new ArrayList<String>();
	        for (String str : posts.keySet()) {
	            keys.add(str);
	        }
	        Collections.sort(keys);
	
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < keys.size(); i++) {
	            sb.append(URLEncoder.encode(keys.get(i), ENCODING));
	            sb.append("=");
	            sb.append(URLEncoder.encode(posts.get(keys.get(i)), ENCODING));
	            sb.append("&");
	        }
	        
	        result = sb.toString().substring(0, sb.toString().length() -1); 
	        result = dispatchStr(result);
	        result += "_" + "bw(*ez$@]a.bokLi";
	        Log.d("secrete key %1s", result + "/" + DeviceUtils.encryption(result));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        return DeviceUtils.encryption(result);
    }

    public void put(String key, String value){
        if(key != null && value != null) {
            urlParams.put(key, value);
        }
    }

    public void put(String key, File file) throws FileNotFoundException {
        put(key, new FileInputStream(file), file.getName());
    }

    public void put(String key, InputStream stream) {
        put(key, stream, null);
    }

    public void put(String key, InputStream stream, String fileName) {
        put(key, stream, fileName, null);
    }

    /**
     * ���� inputStream ��������
     * @param key the key name for the new param.
     * @param stream the input stream to add.
     * @param fileName the name of the file.
     * @param contentType the content type of the file, eg. application/json
     */
    public void put(String key, InputStream stream, String fileName, String contentType) {
        if (key != null && stream != null) {
            fileParams.put(key, new FileWrapper(stream, fileName, contentType));
        }
    }

    public void remove(String key){
        urlParams.remove(key);
        fileParams.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if(result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        for(ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
            if(result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append("FILE");
        }

        return result.toString();
    }

   /**
     * Returns an HttpEntity containing all request parameters
     */
    public HttpEntity getEntity() {
        HttpEntity entity = null;

        if(!fileParams.isEmpty()) {
            MultipartEntity multipartEntity = new MultipartEntity();

            // Add string params
            for(ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
                multipartEntity.addPart(entry.getKey(), entry.getValue());
            }

            // Add file params
            int currentIndex = 0;
            int lastIndex = fileParams.entrySet().size() - 1;
            for(ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
                FileWrapper file = entry.getValue();
                if(file.inputStream != null) {
                    boolean isLast = currentIndex == lastIndex;
                    if(file.contentType != null) {
                        multipartEntity.addPart(entry.getKey(), file.getFileName(), file.inputStream, file.contentType, isLast);
                    } else {
                        multipartEntity.addPart(entry.getKey(), file.getFileName(), file.inputStream, isLast);
                    }
                }
                currentIndex++;
            }

            entity = multipartEntity;
        } else {
            try {
            	StringEntity entity2 = new StringEntity(getParamString());
            	entity2.setContentType("application/x-www-form-urlencoded");
            	entity = entity2;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return entity;
    }

    private void init(){
        urlParams = new ConcurrentHashMap<String, String>();
        fileParams = new ConcurrentHashMap<String, FileWrapper>();
    }

    protected List<BasicNameValuePair> getParamsList() {
        List<BasicNameValuePair> lparams = new LinkedList<BasicNameValuePair>();

        for(ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            lparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        return lparams;
    }

    public String getParamString() {
    	return dispatchStr(URLEncodedUtils.format(getParamsList(), ENCODING));
    }

    private static class FileWrapper {
        public InputStream inputStream;
        public String fileName;
        public String contentType;

        public FileWrapper(InputStream inputStream, String fileName, String contentType) {
            this.inputStream = inputStream;
            this.fileName = fileName;
            this.contentType = contentType;
        }

        public String getFileName() {
            if (fileName != null) {
                return fileName;
            } else {
                return "nofilename";
            }
        }
    }
}