package com.supermarket.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;

//import com.android.camera.CropImage;

/**
 * 截图工具类
 * @author chenjunyu
 *
 */
public class CropImageUtils {

	/**
	 * 截取图片
	 * @param context
	 * @param uri
	 */
	/*public static void onCropImage(Context context,Uri uri,int requestCode,int width,int height){
		Intent cropIntent = new Intent(context, CropImage.class);
		
		cropIntent.setDataAndType(uri, "image*//*");
		cropIntent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		cropIntent.putExtra("aspectX", 1);
		cropIntent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		cropIntent.putExtra("outputX", width);
		cropIntent.putExtra("outputY", height);
		cropIntent.putExtra("return-data", true);
		//截图边线颜色
		cropIntent.putExtra("outlineColor", Color.parseColor("#1fa7cd"));
		cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		((Activity) context).startActivityForResult(cropIntent, requestCode);

	}*/
}
