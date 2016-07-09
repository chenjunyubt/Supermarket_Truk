package com.supermarket.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.finance.core.TTApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.supermarket.R;

public class DisplayImageOptionsUtils {
	private static volatile DisplayImageOptionsUtils mOptions = null;
	private DisplayImageOptions options;
	private DisplayImageOptions optionsAdvert;

	private DisplayImageOptionsUtils() {
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(TTApplication.getAppContext().getResources().getDrawable(R.drawable.album_default))
		.showImageForEmptyUri(TTApplication.getAppContext().getResources().getDrawable(R.drawable.album_default))
		.showImageOnFail(TTApplication.getAppContext().getResources().getDrawable(R.drawable.album_default))
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(0))
		.build();


		optionsAdvert = new DisplayImageOptions.Builder()
		.showImageOnLoading(TTApplication.getAppContext().getResources().getDrawable(R.drawable.album_default))
		.showImageForEmptyUri(TTApplication.getAppContext().getResources().getDrawable(R.drawable.album_default))
		.showImageOnFail(TTApplication.getAppContext().getResources().getDrawable(R.drawable.album_default))
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(0))
		.build();
	}
	
	public static DisplayImageOptionsUtils getInstance() {
		if (mOptions == null) {
            synchronized (DisplayImageOptionsUtils.class) {
                if (mOptions == null) {
                    mOptions = new DisplayImageOptionsUtils();
                }
            }
		}
		
		return mOptions;
	}
	
	/**
	 * 默认显示音频、专辑、频率的封面
	 * @param imageUrl
	 * @param view
	 */
	public void displayImage(String imageUrl, final ImageView view) {
        if (TextUtils.isEmpty(imageUrl)) {
            view.setImageResource(R.drawable.album_default);
        } else {
            ImageLoader.getInstance().displayImage(ImageUtils.getNormalAlbumUrl(imageUrl), view, options);
        }
	}


	/**
	 * 显示电台页封面
	 * @param imageUrl
	 * @param view
	 * @param isRounded true, false show normal bitmap
	 */
	public void displayRadioImage(String imageUrl, final ImageView view, boolean isRounded) {
		if (TextUtils.isEmpty(imageUrl)) {
			view.setImageResource(R.drawable.album_default);
		} else {
            ImageLoader.getInstance().displayImage(ImageUtils.getNormalAlbumUrl(imageUrl), view, options);
		}
	}

	/**
	 * 显示音频、专辑、频率的封面</br>
	 * URL处理过
	 * @param imageUrl 图片Url
	 * @param view 显示图片的控件
	 * @param isDispatchUrl 是否需要处理图片Url，true需要处理，false不需要处理
	 * @param listener 是否需要处理图片加载回调，不处理设置为null
	 */
	public void displayImage(String imageUrl, final ImageView view, boolean isDispatchUrl, ImageLoadingListener listener) {
		if (TextUtils.isEmpty(imageUrl)) {
			view.setImageResource(R.drawable.album_default);
		} else {
			String url = imageUrl;
			if (isDispatchUrl) {
				url = ImageUtils.getNormalAlbumUrl(imageUrl);
			}
			
			ImageLoader.getInstance().displayImage(url, view, options, listener);
		}
	}


	/**
	 * 推荐位图
	 * @param imageUrl
	 * @param view
	 */
	public void displayAdvertImage(String imageUrl, final ImageView view) {
		if (!TextUtils.isEmpty(imageUrl)) {
			ImageLoader.getInstance().displayImage(imageUrl, view, optionsAdvert);
		} else {
			view.setImageResource(R.drawable.album_default);
		}
	}


}