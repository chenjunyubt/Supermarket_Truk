package com.finance.core;

import android.app.Application;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.StrictMode;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.supermarket.R;
import com.finance.cache.ConfigurationCache;
import com.finance.cache.Constants;
import com.finance.cache.TTConstantManager;
import com.finance.common.core.ThreeKeyConstants;
import com.finance.common.db.DBCategoryManager;
import com.finance.crash.CrashManager;
import com.supermarket.services.log.LogService;
import com.supermarket.ui.activity.base.ActivityStack;
import com.supermarket.utils.TTLog;
import com.supermarket.utils.Utils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class TTApplication extends Application {
	private static final String TAG = "TTFM/TTApplication";
	
	private static TTApplication instance = null;

	public  LocationClient mLocationClient;
	
	public  LocationListener mLocationListener;
	
	private  BDLocation mLocation;

	private boolean isUpdating;

   /* {
        PlatformConfig.setWeixin(ThreeKeyConstants.WXAppID, ThreeKeyConstants.WXAppSecret);
        PlatformConfig.setSinaWeibo(ThreeKeyConstants.SINAAppID, ThreeKeyConstants.SINASecret);
        PlatformConfig.setQQZone(ThreeKeyConstants.QQAppID, ThreeKeyConstants.QQAppSecret);
    }*/

	@Override
	public void onCreate() {
		super.onCreate();
		TTLog.i(TAG + "onCreate");
		//LeakCanary.install(this);
		instance = this;
		isUpdating = false;
		initChannel();// 调用该函数的位置不能够移动。
		//debug 模式时，收集异常错误日志并弹出对话框---
		CrashManager crashManager = CrashManager.getInstance();
		crashManager.init(instance);

		initImageLoader(this);
		startLocation();
		if (!ConfigurationCache.getFirstApp()) {
			ConfigurationCache.setFirstApp(true);
			ConfigurationCache.setAdvertPointStatus(true);
			ConfigurationCache.set4GPlayStatus(true);
			ConfigurationCache.setLockScreen(false);//by wucongcong
		}

        ConfigurationCache.setIValues(Constants.DMPLeftContent, -1);
        // Initialize the database soon enough to avoid any race condition and crash
		DBCategoryManager.getInstance(this);
        // Prepare cache folder constants

		if (!TTLog.getAbleLogging()) {
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectAll()
					.penaltyLog()
					.build());
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectAll()
					.penaltyLog()
					.penaltyDeathOnNetwork()
					.build());
		}
	}
	
	//百度定位初始化
	private void startLocation() {
		mLocationClient = new LocationClient(this.getApplicationContext());
		mLocationListener = new LocationListener();
		mLocationClient.registerLocationListener(mLocationListener);
		setLocationOption();
//		mLocationClient.start();
	}

	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);				//打开gps
		option.setCoorType("bd09ll");		//设置坐标类型
		option.setProdName("com.audio.tingting");
		option.setIsNeedAddress(true);//返回的定位结果包含地址信息
		option.setLocationMode(LocationMode.Hight_Accuracy);//Hight_Accuracy高精度、Battery_Saving低功耗、Device_Sensors仅设备(GPS)
		option.setTimeOut(1000);
		option.setScanSpan(500);	//设置定位模式，小于1秒则一次定位;大于等于1秒则定时定位
		mLocationClient.setLocOption(option);
	}
	
	//百度定位获取省信息
	public String getProvince(){
		return mLocation.getProvince();
	}
	
	//百度定位获取市信息
	public  String getCity(){
		return mLocation.getCity();
	}
	
	//百度定位获取详细信息
	public String getDistrict(){
		return mLocation.getDistrict();
	}
	
	//百度定位获取区信息
	public String getAddrStr(){
		return mLocation.getAddrStr();
	}
	
	//获取BDLocation
	public BDLocation getBDLocation(){
		
		return mLocation;
	}
	//返回百度定位服务
	public  LocationClient getLocationClient(){
		return mLocationClient;
	}

	public static Context getAppContext() {
		return instance;
	}
	
	public static Resources getAppResources() {
		if (instance == null)
			return null;
		return instance.getResources();
	}
	
	public void onLowMemory() {
		super.onLowMemory();
	}

	private static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.diskCacheSize(50 * 1024 * 1024)
				.writeDebugLogs()
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	
	/**
	 *初始化渠道
	 */
	private void initChannel() {
		int sourid = 0;
		try {
			ApplicationInfo appInfo;
			appInfo = this.getPackageManager().getApplicationInfo(
					getPackageName(), PackageManager.GET_META_DATA);
			sourid = appInfo.metaData.getInt("TINGTING_CHANNEL");
			TTConstantManager.getInstance().setScid(sourid);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 实现实位回调监听
	 */
	public class LocationListener implements BDLocationListener {


		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location != null){
				mLocation= location;
			}else{
				Toast.makeText(TTApplication.this.getApplicationContext(), R.string.discover_native_area_no_value, Toast.LENGTH_SHORT).show();
			}
			mLocationClient.stop();
			Intent intent = new Intent();
			intent.setAction(Utils.NATIVE_AREA_BROADCASTRECEIVER_ACTION);
			LocalBroadcastManager.getInstance(TTApplication.this).sendBroadcast(intent);
		}
	}
	
	public void finishActivity() {
        System.out.println("finishActivity >>>>>>>> ");
        Intent intent = new Intent("com.audio.tingting.DOWNLOADSERVICE");
        intent.setPackage("com.audio.tingting");
        stopService(intent);

        if (!TTLog.getAbleLogging()) {
            Intent logService = new Intent(instance, LogService.class);
            instance.stopService(logService);
        }

        ((KeyguardManager)this.getSystemService(Context.KEYGUARD_SERVICE))
                .newKeyguardLock("tingting").reenableKeyguard();

        ConfigurationCache.setTimerStartTime(0l);
        ConfigurationCache.setTimerTimeRangle(0);

        ActivityStack.getInstance().popAllActivityExcept();
//		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	public boolean isUpdating() {
        return isUpdating;
	}

	public void setUpdating(boolean isUpdating) {
		this.isUpdating = isUpdating;
	}
}
