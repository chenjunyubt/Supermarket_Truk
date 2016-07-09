/**
 * 
 */
package com.supermarket.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;

import com.finance.common.utils.ScreenUtils;
import com.finance.core.TTApplication;
import com.supermarket.R;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liqiang
 * @data 2014年9月27日
 *
 */
public class ImageUtils {
	//闪屏广告位图片尺寸
	public final static int SCREENWIDTH_480 = 480;
	public final static int SCREENWIDTH_720 = 720;
	public final static int SCREENWIDTH_1080 = 1080;
	public final static int SCREENHEIGHT_660 = 660;
	public final static int SCREENHEIGHT_1062 = 1062;
	public final static int SCREENHEIGHT_1595 = 1595;
	
	enum EnumImageType {
		NormalImage,
		PlayImage
	}
	
	/**
	 * 普通专辑图片
	 * @param url
	 * @return
	 */
	public static String getNormalAlbumUrl(String url) {
		return getImageUrlForAlbum(url, EnumImageType.NormalImage);
	}
	
	/**
	 * 播放器专辑图片
	 * @param url
	 * @return
	 */
	public static String getPlayAlbumUrl(String url) {
		return getImageUrlForAlbum(url, EnumImageType.PlayImage);
	}
	
	/**
	 * 主持人头像
	 * @param url
	 * @return
	 */
	public static String getImageUrlForPresenter(String url) {
		return getUrlForPresenter(url);
	}
	
	/**
	 * 专题
	 * @param url
	 * @param isPerson
	 * @return
	 */
	public static String getImageUrlForSpecialTopics(String url,boolean isPerson){

		if (TextUtils.isEmpty(url)) {
			return "";
		} 
		
		int width = ScreenUtils.getScreenWidth();
		
		int index = 0;
		StringBuilder sb = new StringBuilder();
		if ((index = url.toLowerCase().indexOf(".jpg")) != -1 
				|| (index =url.toLowerCase().indexOf(".gif")) != -1 
				|| (index = url.toLowerCase().indexOf(".png")) != -1) {
			sb.append(url.substring(0, index));
		}else{
			sb.append(url);
		}
		
		if(isPerson){
			if (width <= 480) {
				sb.append("_220x276");
			} else if (width > 480 && width <= 720) {
				sb.append("_330x424");
			} else {
				sb.append("_496x620");
			}
		}else{
			if (width <= 480) {
				sb.append("_480x192");
			} else if (width > 480 && width <= 720) {
				sb.append("_720x290");
			} else {
				sb.append("_1080x434");
			}
		}
		if(index != -1){
			try {
				sb.append(url.substring(index));
			} catch (Exception e) {
				return "";
			}
		}
		return sb.toString();
	
	}
	
	static String getImageUrlForAlbum(String url, EnumImageType type) {
		if (TextUtils.isEmpty(url)) {
			return "";
		} 
		
		int width = TTApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.album_width);
		
		if (type == EnumImageType.PlayImage) {
			width = TTApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.album_play_width);
		}
		
		int index = 0;
		StringBuilder sb = new StringBuilder();
		if ((index = url.toLowerCase().indexOf(".jpg")) != -1 
				|| (index =url.toLowerCase().indexOf(".gif")) != -1 
				|| (index = url.toLowerCase().indexOf(".png")) != -1) {
			sb.append(url.substring(0, index));
		}else{
			sb.append(url);
		}
		
		if (width <= 150) {
			sb.append("_150x150");
		} else if (width > 150 && width <= 300) {
			sb.append("_300x300");
		} else {
			sb.append("_518x518");
		}
		if(index != -1){
			try {
				sb.append(url.substring(index));
			} catch (Exception e) {
				return "";
			}
		}
		return sb.toString();
	}
	
	static String getUrlForPresenter(String url) {
		if (TextUtils.isEmpty(url)) {
			return "";
		} 
		
		int width = 0;
		width = TTApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.album_width);
		
		int index = 0;
		StringBuilder sb = new StringBuilder();
		if ((index = url.toLowerCase().indexOf(".jpg")) != -1 
				|| (index =url.toLowerCase().indexOf(".gif")) != -1 
				|| (index = url.toLowerCase().indexOf(".png")) != -1) {
			sb.append(url.substring(0, index));
		}else{
			sb.append(url);
		}
		
		if (width <= 100) {
			sb.append("_100x100");
		} else if (width > 100 && width <= 150) {
			sb.append("_150x150");
		} else {
			sb.append("_276x276");
		}
		if(index != -1){
			sb.append(url.substring(index));
		}
		return sb.toString();
	}
	
	/**
	 * 把 Uri 转成 bitmap
	 * @param activity
	 * @param uri
	 * @return
	 */
	public static Bitmap changeUri4Bitmap(Activity activity, Uri uri) {
		Bitmap photo = null;
		// 解决三星手机或其它手机截图时超过160以上的为大图情况 通过uri获取
		int dw = activity.getWindowManager().getDefaultDisplay().getWidth();
		int dh = activity.getWindowManager().getDefaultDisplay().getHeight() / 2;

		BitmapFactory.Options factory = new BitmapFactory.Options();
		factory.inJustDecodeBounds = true; // 当为true时 允许查询图片不为
											// 图片像素分配内存
		try {
			photo = BitmapFactory.decodeStream(activity.getContentResolver()
					.openInputStream(uri), null, factory);
			int hRatio = (int) Math.ceil(factory.outHeight / (float) dh); // 图片是高度的几倍
			int wRatio = (int) Math.ceil(factory.outWidth / (float) dw); // 图片是宽度的几倍
			// 缩小到 1/ratio的尺寸和 1/ratio^2的像素
			if (hRatio > 1 || wRatio > 1) {
				if (hRatio > wRatio) {
					factory.inSampleSize = hRatio;
				} else
					factory.inSampleSize = wRatio;
			}
			factory.inJustDecodeBounds = false;
			photo = BitmapFactory.decodeStream(activity.getContentResolver()
					.openInputStream(uri), null, factory);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return photo;
	}
	
	
	/**
	 * 对spanableString进行正则判断，如果符合要求，则以图片代替
	 * @param context
	 * @param spannableString
	 * @param patten
	 * @param start
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NumberFormatException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void dealExpression(Context context,
			SpannableString spannableString, Pattern patten, int start)
			throws SecurityException, NoSuchFieldException,
			NumberFormatException, IllegalArgumentException,
			IllegalAccessException {
		Matcher matcher = patten.matcher(spannableString);
		while (matcher.find()) {
			String key = matcher.group();
			if (matcher.start() < start) {
				continue;
			}
			Field field = R.drawable.class.getDeclaredField(key);
			int resId = Integer.parseInt(field.get(null).toString()); // 通过上面匹配得到的字符串来生成图片资源id
			if (resId != 0) {
				Bitmap bitmap = BitmapFactory.decodeResource(
						context.getResources(), resId);
				ImageSpan imageSpan = new ImageSpan(bitmap); // 通过图片资源id来得到bitmap，用一个ImageSpan来包装
				int end = matcher.start() + key.length(); // 计算该图片名字的长度，也就是要替换的字符串的长度
				spannableString.setSpan(imageSpan, matcher.start(), end,
						Spannable.SPAN_INCLUSIVE_EXCLUSIVE); // 将该图片替换字符串中规定的位置中
				if (end < spannableString.length()) { // 如果整个字符串还未验证完，则继续。。
					dealExpression(context, spannableString, patten, end);
				}
				break;
			}
		}
	}
	
	   /**
     * 得到一个SpanableString对象，通过传入的字符串,并进行正则判断
     * @param context
     * @param str
     * @return
     */
    public static SpannableString getExpressionString(Context context,String str,String zhengze){
    	SpannableString spannableString = new SpannableString(str);
        Pattern sinaPatten = Pattern.compile(zhengze, Pattern.CASE_INSENSITIVE);		//通过传入的正则表达式来生成一个pattern
        try {
            dealExpression(context,spannableString, sinaPatten, 0);
        } catch (Exception e) {
        	//TODO
        }
        return spannableString;
    }
    
    /**
     * 释放 Bitmap 
     * @param bmp
     */
    public static void recycleBitmap(Bitmap bmp){
    	if(null != bmp && !bmp.isRecycled()){
    		bmp.recycle();
    		bmp = null;
    	}
    }
}
