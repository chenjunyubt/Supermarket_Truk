package com.fx.scrootcorpactivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.camera.CropImage;
import com.android.camera.cropimage.R;

public class MainActivity extends Activity {
	private ImageView coverView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		coverView = (ImageView) findViewById(R.id.image);
		
		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				/**
				 * 下面这句话，与其它方式写是一样的效果，如果： intent.setData(MediaStore.Images
				 * .Media.EXTERNAL_CONTENT_URI); intent.setType(""image/*");设置数据类型
				 * 
				 */
				intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						"image/*");
				startActivityForResult(intent, 1);
			}
		});
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		/**
		 * 下面这句话，与其它方式写是一样的效果，如果： intent.setData(MediaStore.Images
		 * .Media.EXTERNAL_CONTENT_URI); intent.setType(""image/*");设置数据类型
		 * 
		 */
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				"image/*");
		startActivityForResult(intent, 1);
	}
	
	private void goTocropImage(Intent data){
		Uri uri = data.getData();
		
		Intent cropIntent = new Intent(this, CropImage.class);
		
		cropIntent.setDataAndType(uri, "image/*");  
		cropIntent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		cropIntent.putExtra("aspectX", 1);
		cropIntent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		cropIntent.putExtra("outputX", 500);
		cropIntent.putExtra("outputY", 500);
		cropIntent.putExtra("return-data", true);
		cropIntent.putExtra("outlineColor", Color.parseColor("#FF0000"));
		cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(cropIntent, 2);

	}
	
	private void showCropImage(Intent data){
		Bitmap mPhoto = null;
			Uri imageFileUri = data.getData();
			if (imageFileUri != null) {//解决三星手机或其它手机截图时超过160以上的为大图情况 通过uri获取
				mPhoto = changeUri4Bitmap(this, imageFileUri);
			}else{
				//mPhoto = extras.getParcelable("data");
			}
			if(mPhoto != null){
				int width = mPhoto.getWidth();
				int height = mPhoto.getHeight();
				Log.e("---","---width="+width +"--height="+height);
				if(width > 200 && height > 200)
					goTocropImage(data);
				else
				//File mFlie = new File(getExternalCacheDir().toString()+face_Image);
				//mFlie.mkdir();
				//face_filePath = getExternalCacheDir().toString()+face_Image+"/face_01.jpg";
				//FileOutputStream mFOS = new FileOutputStream(face_filePath);
				//mPhoto.compress(Bitmap.CompressFormat.JPEG, 90, mFOS);// (0 - 100)压缩文件 
				mPhoto =	ThumbnailUtils.extractThumbnail(mPhoto, 500, 500);
				coverView.setImageBitmap(mPhoto);
				//iscoverChange = true;
			}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (data != null) {
				//goTocropImage(data);
				showCropImage(data);
			}
		}else if(requestCode == 2 && resultCode == RESULT_OK){
			if (data == null)
				return;
				Bitmap mPhoto = null;
				Bundle extras = data.getExtras();
				if (extras != null) {
					Uri imageFileUri = data.getData();
					if (imageFileUri != null) {//解决三星手机或其它手机截图时超过160以上的为大图情况 通过uri获取
						mPhoto = changeUri4Bitmap(this, imageFileUri);
					}else{
						mPhoto = extras.getParcelable("data");
					}
					if(mPhoto != null){
						int width = mPhoto.getWidth();
						int height = mPhoto.getHeight();
						Log.e("---","---width="+width +"--height="+height);
						//File mFlie = new File(getExternalCacheDir().toString()+face_Image);
						//mFlie.mkdir();
						//face_filePath = getExternalCacheDir().toString()+face_Image+"/face_01.jpg";
						//FileOutputStream mFOS = new FileOutputStream(face_filePath);
						//mPhoto.compress(Bitmap.CompressFormat.JPEG, 90, mFOS);// (0 - 100)压缩文件 
						coverView.setImageBitmap(mPhoto);
						//iscoverChange = true;
					}
				}
		}else{
			if(null == data)
				return;
			Bitmap bitmap = changeUri4Bitmap(this,data.getData());
			Bitmap bmp = ThumbnailUtils.extractThumbnail(bitmap, 500, 500);/* zoomImg(bitmap, 200, 200); comp(bitmap);*/
			Log.e("------------",  "----width = "+bmp.getWidth() +"-----height = "+bmp.getHeight());
			coverView.setImageBitmap(bmp);
		}
	}
	
	private Bitmap comp(Bitmap image) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();		
		image.compress(Bitmap.CompressFormat.PNG, 100, baos);
		if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出	
			baos.reset();//重置baos即清空baos
			image.compress(Bitmap.CompressFormat.PNG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 1080f;//这里设置高度为800f
		float ww = 1920f;//这里设置宽度为480f
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置缩放比例
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		bitmap =ThumbnailUtils.extractThumbnail(bitmap, 500, 500);
		//bitmap = zoomImg(bitmap,200,200);
		return bitmap;//压缩好比例大小后再进行质量压缩
	}
	
	/**
	 *  处理图片 
	 * @param bm 所要转换的bitmap
	 * @param newWidth新的宽
	 * @param newHeight新的高  
	 * @return 指定宽高的bitmap
	 */
	 public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){  
	    // 获得图片的宽高  
	    int width = bm.getWidth();  
	    int height = bm.getHeight();  
	    // 计算缩放比例  
	    float scaleWidth = ((float) newWidth) / width;  
	    float scaleHeight = ((float) newHeight) / height;  
	    // 取得想要缩放的matrix参数  
	    Matrix matrix = new Matrix();  
	    matrix.postScale(scaleWidth, scaleHeight);  
	    // 得到新的图片  
	    Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);  
	    return newbm;  
	} 
	
	private Bitmap compressImageFromFile(Uri uri) {
		Bitmap bitmap = null;
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;//只读边,不读内容
		//Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		try{
		 bitmap = BitmapFactory.decodeStream(getContentResolver()
				.openInputStream(uri), null, newOpts);

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = 800f;//
		float ww = 480f;//
		int be = 1;
		if (w > h && w > ww) {
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置采样率
		
		newOpts.inPreferredConfig = Config.ARGB_8888;//该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
		
		//bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		 bitmap = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(uri), null, newOpts);
//		return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
									//其实是无效的,大家尽管尝试
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
		return bitmap;
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

}
