package com.supermarket.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.supermarket.R;
import com.supermarket.view.ViewWrapper;

/**
 * 
 * @author qinlingonng
 *
 * 2014年9月20日
 *
 */
public class Utils {
	/**
	 * PROGRAMDETAILINFO 节目信息页下载表识。MOREPROGRAMINFO 多节目信息页下载表识
	 */
	public final static int PROGRAMDETAILINFO = 0x010101;
	public final static int MOREPROGRAMINFO = 0x010102;
	
	public final static int DOWNLOADTYPE_0 = 0;   // 单个音频无专辑。用于表识在其它中进行显示
	public final static int DOWNLOADTYPE_1 = 1;   // 用于在节目信息页进行显示
	public final static int PRIVATETYPE = -1;   // 私人电台服务端无下载类型(专辑2，节目1，播客3)，自定义为-1区分。
	public final static int MORERADIOTYPE = -2;   // 多节目服务端无下载类型(专辑2，节目1，播客3)，自定义为-2区分。
	
	public final static int RAIOD_ALBUM_ID = -2;   // 单个音频没有专辑，专辑id为-2.
	
	/**界面参数错误**/
	public final static int DEFAULT_ERROR_VALUE = 0xFFFFFFFF;
	
	//搜索ListView布局类型 ,1为历史结果类型
	public final static int ITEM_TYPE_1 = 0x001000;
	//搜索ListView布局类型 ,2为历史记录清除按钮类型
	public final static int ITEM_TYPE_2 = 0x001001;
	//搜索ListView布局类型 ,3为订阅专辑入口搜索结果类型
	public final static int ITEM_TYPE_3 = 0x001002;
	//搜索ListView布局类型 ,4为电台入口搜索结果类型
	public final static int ITEM_TYPE_4 = 0x001003;
	//搜索ListView布局类型 ,5为音频搜索结果类型
	public final static int ITEM_TYPE_5 = 0x001004;
	//搜索界面类型入口,1为常规搜索入口,2为订阅专辑入口,3为电台入口,4为音频搜索入口
	public final static int SEARCH_TYPE_1 = 0x001100;
	public final static int SEARCH_TYPE_2 = 0x001101;
	public final static int SEARCH_TYPE_3 = 0x001102;
	public final static int SEARCH_TYPE_4 = 0x001103;
	//搜索界面类型入口参数key
	public static String KEY_SEARCH_ACTIVITY_TYPE = "key_search_activity_type";
	
	//选择界面类型入口,1为省市标记,2为国外标记,3为外语标记,4为方言标记,5为流派标记,6为播客地区选择标记,7为音频标记
	public final static int SELECT_TYPE_1 = 0x001110;
	public final static int SELECT_TYPE_2 = 0x001111;//1.9版本去掉国外标签
	public final static int SELECT_TYPE_3 = 0x001112;//1.9版本去掉国外标签
	public final static int SELECT_TYPE_4 = 0x001113;
	public final static int SELECT_TYPE_5 = 0x001114;
	public final static int SELECT_TYPE_6 = 0x001115;
	public final static int SELECT_TYPE_7 = 0x001116;
	/**区分进入页，是二级菜单 ，还是音乐电台key**/
	public final static int KEY_RADIO_ID = -2;
	public final static int KEY_HOME_ADAPTER = 1; //首页Fragment
	public final static int KEY_TWO_ADAPTER = 2; //二级菜单页Fragment
	public final static int KEY_MUSIC_RADIO_ADAPTER = 3;//音乐电台页Fragment
	public final static int KEY_HOME_BLOG = 4; //原创播客Fragment
	public final static int KEY_HOME_LIVE = 5; //正在直播Fragment
	/**选择界面类型入口参数key**/
	public static String KEY_SELECT_ACTIVITY_TYPE = "key_select_activity_type";
	/**选择界面类型titlevalue key**/
	public static String KEY_SELECT_ACTIVITY_TITLE_VALUE = "key_select_activity_title_value";
	/**类型传递 key**/
	public static String KEY_TYPE_TO_NEXT_VALUE = "key_type_to_next_value";
	//类型请求参数
	public static String EVENT_TYPE_ORDER_RECOMMEND = "recommend";
	public static String EVENT_TYPE_ORDER_NEW = "new";
	public static String EVENT_TYPE_ORDER_HOT = "hot";
	/** 特殊,表示当前状态：全部，需要走另一个请求分支   */
	public static String EVENT_TYPE_ORDER_ALL = "all";
	
	/**播客传递 key**/
	public static String KEY_BOKE_TO_NEXT_VALUE = "key_boke_to_next_value";
	/**播客界面显示传递 key**/
	public static String KEY_BOKE_FOR_TYPE_TO_NEXT_VALUE = "key_boke_for_type_to_next_value";
	/**主播传递 key**/
	public static String KEY_ANCHOR_TO_NEXT_VALUE = "key_anchor_to_next_value";
	/**私人电台传递 key**/
	public static String KEY_PRIVATE_RADIO_TO_NEXT_VALUE = "key_private_radio_to_next_value";
	
	/**本地fm地区定位广播action**/
	public static String NATIVE_AREA_BROADCASTRECEIVER_ACTION = "native_area_broadcastreceiver_action";
	/**地区选择定位广播action**/
	public static String CHOOSE_AREA_BROADCASTRECEIVER_ACTION = "choose_area_broadcastreceiver_action";
	/**地区list参数key**/
	public static String KEY_FM_AREA_LIST_RESULT = "key_fm_area_list_result";
	/**选择地区返回的resultCode key**/
	public final static int KEY_FM_AREA_RESULT = 0x101101;

	/**网络，电视 key**/
	public static String KEY_TITLE_NAME	 = "title_name";

	/**传递中央类型 key**/
	public static String KEY_CNTER_AREA_VALUE = "key_cnter_area_value";
	/**选择地区后返回的地区id  key**/
	public static String KEY_FM_AREA_RESULT_ID = "key_fm_area_result_id";
	/**选择地区后返回的地区name  key**/
	public static String KEY_FM_AREA_RESULT_NAME = "key_fm_area_result_name";
	/**传递全部省市的值 key**/
	public static String KEY_ALL_LEVEL_VALUE_TO_NEXT = "key_all_level_value_to_next";
	/**传递单个省市的值 key**/
	public static String KEY_ONE_LEVEL_VALUE_TO_NEXT = "key_one_level_value_to_next";
	/**传递单个私人电台的值 id key**/
	public static String KEY_ONE_PRIVATE_RADIO_ID_TO_NEXT = "key_one_private_radio_id_to_next";
	/**传递音频id集合 key**/
	public static String KEY_ONE_PRIVATE_RADIO_VOD_ID_LIST_TO_NEXT = "key_one_private_radio_vod_id_list_to_next";
	/**传递单个节目的值 id key**/
	public static String KEY_ONE_PRIVATE_RADIO_PROGRAM_ID_TO_NEXT = "key_one_private_radio_program_id_to_next";
	/**收集电台传递到选择界面的item值 key,也用于回传的时候做参数使用**/
	public static String KEY_SELECT_VALUE_TO_NEXT = "key_select_value_to_next";
	/**收集电台传递到选择界面的类型type值 key**/
	public static String KEY_TYPE_VALUE_TO_NEXT = "key_type_value_to_next";
	/**添加或者编辑收集电台的key**/
	public static String KEY_COLLECTIONRADIODETAIL_TYPE = "key_collectionradiodetail_type";
	/**频率编辑key**/
	public static String KEY_EDITPINLV = "key_editpinlv";
	/**私人电台跳转前台&&展示页&&添加节目页userFmId值key**/
	public static String KEY_PRIVATE_RADIO_FM_ID = "key_private_radio_fm_id";
	/**私人电台跳转前台&&展示页&&添加节目页userId值key**/
	public static String KEY_PRIVATE_RADIO_USER_ID = "key_private_radio_user_id";
	/**私人电台防止添加到原来节目的Key**/
	public static String KEY_FM_REPEAT_PROGRAM_ID = "key_fm_repeat_program_id";
	/**私人电台跳转添加节目页userFmName值key**/
	public static String KEY_PRIVATE_RADIO_FM_NAME = "key_private_radio_fm_name";
	/**私人电台跳转节目前台&&展示页userFmId值key**/
	public static String KEY_PRIVATE_RADIO_FOR_PROGRAMFM_ID = "key_private_radio_for_programfm_id";
	/**私人电台跳转节目前台&&展示页userId值key**/
	public static String KEY_PRIVATE_RADIO_FOR_PERSON_ID = "key_private_radio_for_person_id";
	/**私人电台的节目跳转前台&&展示页FmRpogramId值key**/
	public static String KEY_PRIVATE_RADIO_FM_PROGRAM_ID = "key_private_radio_fm_program_id";
	/**私人电台跳转前台&&展示页type key**/
	public static String KEY_PRIVATE_RADIO_PAGE_TYPE = "key_private_radio_page_type";
	/**节目跳转前台&&展示页type key**/
	public static String KEY_PRIVATE_RADIO_PROGRAM_PAGE_TYPE = "key_private_radio_program_page_type";
	/**自己节目前台页**/
	public final static int PROGRAM_ACTIVITY_TYPE_MYSELF = 0x0601;
	/**他人节目展示页**/
	public final static int PROGRAM_ACTIVITY_TYPE_VISITOR = 0x0600;
	/**私人电台跳转前台&&展示页是否需要返回值 key**/
	public static String KEY_PRIVATE_RADIO_PAGE_IS_ALLOW_RESULT = "key_private_radio_page_is_allow_result";
	/**添加音频到私人电台  key**/
	public static String KEY_ADD_VOD_TO_PRIVATE_RADIO_VALUE = "key_add_vod_to_private_radio_value";
	/**添加音频到节目  key**/
	public static String KEY_ADD_VOD_TO_PROGRAM_VALUE = "key_add_vod_to_program_value";
	/**添加音频到节目 界面类型 key**/
	public static String KEY_ADD_VOD_TO_PROGRAM_TYPE = "key_add_vod_to_program_type";
	/**添加音频到私人电台 返回区分路径**/
	public static String KEY_ADD_VOD_TO_PRIVATE_RADIO_RESULT = "key_add_vod_to_private_radio_result";
	/**添加音频到私人电台 区分由简易电台或者节目添加,true 简易电台 false 节目**/
	public static String KEY_ADD_VOD_FROM = "key_add_vod_from";
	/**添加音频到私人电台 简易电台或者节目的id**/
	public static String KEY_ADD_VOD_FROM_ID = "KEY_ADD_VOD_FROM_ID";
	/**查看他人私人电台时 他人ID key**/
	public static String KEY_SHOW_OTHER_PRIVATE_RADIO_USER_ID = "key_show_other_private_radio_user_id";
	/**查看他人私人电台时 他人name key**/
	public static String KEY_SHOW_OTHER_PRIVATE_RADIO_USER_NAME = "key_show_other_private_radio_user_name";
	/**私人电台列表展示页类型 key**/
	public static String KEY_PRIVATE_RADIO_LIST_TYPE_VALUE = "key_private_radio_list_type_value";
	
	/**跳转是否需要返回值**/
	public static String KEY_NEED_RESULT = "key_need_result";
	
	/**传送私人电台音频是否执行了修改信息的操作，反馈回之前界面 result key**/
	public static int KEY_PROGRAM_SELECT_INFO_CHANGE_BACK = 0x1520;
	//私人电台列表展示页类型入口,1为查看他人私人电台标记,2为播放器跳转添加音频行为标记,3为操作自己的私人电台列表标记,4为批量添加音频到的私人电台列表标记,5为创建节目到私人电台列表标记
	/**查看他人私人电台标记**/
	public final static int PRIVATE_RADIO_LIST_TYPE_1 = 0x011110;
	/**播放器跳转添加音频行为标记**/
	public final static int PRIVATE_RADIO_LIST_TYPE_2 = 0x011111;
	/**操作自己的私人电台列表标记**/
	public final static int PRIVATE_RADIO_LIST_TYPE_3 = 0x011112;
	/**批量添加音频到的私人电台列表标记**/
	public final static int PRIVATE_RADIO_LIST_TYPE_4 = 0x011113;
	/**创建节目到私人电台列表标记**/
	public final static int PRIVATE_RADIO_LIST_TYPE_5 = 0x011114;
	
	/**传送私人电台对象到创建&管理界面key**/
	public static String KEY_DETAIL_PRIVATE_RADIO_TO_CONTROL = "key_detail_private_radio_to_control";
	/**传送私人电台对象到简介界面key**/
	public static String KEY_DETAIL_PRIVATE_RADIO_TO_INFO_SHOW = "key_detail_private_radio_to_info_show";
	/**传送私人电台转化多电台key**/
	public static String KEY_DETAIL_PRIVATE_RADIO_TO_CHANGE = "key_detail_private_radio_to_change";
	/**判断是创建&管理(私人电台或者节目)key**/
	public static String KEY_CREATE_OR_CONTROL_PRIVATE_RADIO_OR_PROGRAM = "key_create_or_control_private_radio_or_program";
	/**创建私人电台标识**/
	public final static int PRIVATE_RADIO_CREATE = 0x014110;
	/**管理私人电台标识**/
	public final static int PRIVATE_RADIO_CONTROL = 0x014116;
	/**创建节目标识**/
	public final static int PRIVATE_RADIO_PROGRAM_CREATE = 0x015110;
	/**管理节目标识**/
	public final static int PRIVATE_RADIO_PROGRAM_CONTROL = 0x015116;
	
	/**传送私人电台&节目(创建&管理)界面的风格标签到风格选择界面key**/
	public static String KEY_PRIVATE_RADIO_STYLE_TO_NEXT = "key_private_radio_style_to_next";
	/**传送私人电台管理界面的类型标签到类型选择界面key**/
	public static String KEY_PRIVATE_RADIO_TYLE_TO_NEXT = "key_private_radio_tyle_to_next";
	/**传送私人电台音频是否执行了添加或者删除的操作，反馈回之前界面 result key**/
	public static int KEY_PRIVATE_RADIO_VOD_ADD_BACK = 0x1125;
	/**传送私人电台音频是否执行了修改信息的操作，反馈回之前界面 result key**/
	public static int KEY_PRIVATE_RADIO_INFO_CHANGE_BACK = 0x1129;
	/**传送私人电台节目是否执行了修改信息的操作，反馈回之前界面 result key**/
	public static int KEY_PRIVATE_RADIO_PROGRAM_INFO_CHANGE_BACK = 0x1131;
	/**传送私人电台节目是否执行了增加的操作，反馈回之前界面 result key**/
	public static int KEY_PRIVATE_RADIO_PROGRAM_COUNT_CHANGE_BACK = 0x1133;
	
	/**自己私人电台前台页**/
	public final static int ACTIVITY_TYPE_MYSELF = 1;
	/**他人私人电台展示页**/
	public final static int ACTIVITY_TYPE_VISITOR = 0;
	
	/**批量添加Type的Key**/
	public static String KEY_BULK_ADD_VOD = "key_bulk_add_vod";
	/**批量添加来自简易电台Type**/
	public final static int BULK_ADD_VOD_BY_RADIO = 0x0050;
	/**批量添加来自节目Type**/
	public final static int BULK_ADD_VOD_BY_PROGRAM = 0x0055;
	/**批量添加来自简易电台 的Key**/
	public static String KEY_BULK_ADD_VOD_BY_RADIO = "key_bulk_add_vod_by_radio";
	/**批量添加来自简易电台 的Key**/
	public static String KEY_BULK_ADD_VOD_BY_PROGRAM = "key_bulk_add_vod_by_program";
	
	/**
	 * 返回收集电台管理界面时区分是哪种选择的返回
	 */
	//国家
	public final static int RESULTCODE_COUNTRY = 0x000001;
	//语言
	public final static int RESULTCODE_LANGUAGE = 0x000002;
	//类型
	public final static int RESULTCODE_TYPE = 0x000003;
	//电台频率
	public final static int RESULTCODE_PINLV = 0x000004;
	
	/**添加音频界面标识**/
	public final static String KEY_TO_ADD_VOD_TO_RADIO_OR_PROGRAM = "key_to_add_vod_to_radio_or_program";
	/**添加音频界面由私人电台进来的标志位**/		
	public final static int TYPE_WITH_RADIO = 0x0500;
	/**添加音频界面由节目进来的标志位**/	
	public final static int TYPE_WITH_PROGRAM = 0x0501;

	/**私人电台删除resultcode标志位**/
	public final static int RESULTCODE_DELETE_PRIVATE_RADIO= 0x000111;
	/**节目删除resultcode标志位**/
	public final static int RESULTCODE_DELETE_PROGRAM= 0x000911;
	/**传送主播界面加载类型到主播界面key**/
	public static String KEY_ANCHOR_TYLE_TO_NEXT = "key_anchor_tyle_to_next";
	
	/**主播界面一般加载类型**/
	public final static int ANCHOR_TYLE_LIST_TYPE_1 = 0x010110;
	/**主播界面按区域加载类型**/
	public final static int ANCHOR_TYLE_LIST_TYPE_2 = 0x010111;
	/**主播界面按频率加载类型**/
	public final static int ANCHOR_TYLE_LIST_TYPE_3 = 0x010112;
	/**电台音频数**/
	public final static String PRIVATE_RADIO_AUDIO_NUM = "audio_num";
	/**电台节目数**/
	public final static String PRIVATE_RADIO_PROGRAM_NUM = "program_num";
	/**电台名**/
	public final static String PRIVATE_RADIO_CHANGE_INFO_NAME = "change_info_name";
	/**电台封面**/
	public final static String PRIVATE_RADIO_CHANGE_INFO_COVER = "change_info_cover";
	/**电台或者节目更改标识**/
	public final static String PRIVATE_RADIO_CHANGE_PROGRAM_OR_VOD = "change_program_or_vod";
	/**电台或者节目更改长度标识**/
	public final static String PRIVATE_RADIO_CHANGE_PROGRAM_OR_VOD_SIZE = "change_program_or_vod_size";
	/**电台锁定标识**/
	public final static String PRIVATE_RADIO_CHANGE_INFO_ACL = "change_info_acl";
	/**电台样式**/
	public final static String PRIVATE_RADIO_CHANGE_INFO_FM_TYPE = "change_info_fm_type";
	/**电台节目**/
	public final static String PRIVATE_RADIO_PROGRAM_CHANGE_INFO_NAME = "program_change_info_name";
	/**节目封面**/
	public final static String PRIVATE_RADIO_PROGRAM_CHANGE_INFO_COVER = "private_radio_program_change_info_cover";
	/**节目锁定标识**/
	public final static String PRIVATE_RADIO_PROGRAM_CHANGE_INFO_ACL = "private_radio_program_change_info_acl";
	/**节目音频数**/
	public final static String PRIVATE_RADIO_PROGRAM_AUDIO_NUM = "private_radio_program_audio_num";
	
	/**音频搜索是添加到节目或者电台,0为添加到节目，1为添加到电台**/
	public static String KEY_ONE_PRIVATE_RADIO_OR_PROGRAM_TO_NEXT = "key_one_private_radio_or_program_to_next";
	
	/**添加专辑音频到私人电台的专辑Id Key**/
	public static String KEY_ADD_VOD_BY_ALBUM_ID = "key_add_vod_by_album_id";
	/**添加专辑音频到私人电台的专辑节目Id Key**/
	public static String KEY_ADD_VOD_BY_PROGRAM_ALBUM_ID = "key_add_vod_by_program_album_id";
	/**添加专辑音频从私人电台或节目 Key**/
	public static String KEY_ADD_VOD_BY_ALBUM_FROM_PRIVATE_RADIO = "key_add_vod_by_album_from_private_radio";
	/**专辑音频所属专辑类型  Key**/
	public static String KEY_ADD_VOD_BY_ALBUM_TYPE = "key_add_vod_by_album_type";
	/**添加专辑音频从私人电台或节目 区分 ,true ：简易电台跳转 ,false 节目跳转**/
	public static String KEY_ADD_VOD_BY_ALBUM_FROM_PRIVATE_RADIO_OR_PROGRAM = "key_add_vod_by_album_from_private_radio_or_program";
	/**添加专辑音频从私人电台或节目 的对应电台或者节目ID**/
	public static String KEY_ADD_VOD_BY_ALBUM_FROM_ID= "key_add_vod_by_album_from_id";

	/**
	 * 隐藏软键盘
	 * @param activity
	 */
	public static void hideSoftKeyboard(Activity activity){
		InputMethodManager inputMethodManager =(InputMethodManager)activity.getApplicationContext().
				getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
	}
	/**
	 * 返回是否打开了GPS
	 * @param context
	 * @return
	 */
	public static boolean isOpenGPS(Context context) {
		boolean falg = false;
		try {
			LocationManager locationManager = (LocationManager) context
					.getSystemService(Context.LOCATION_SERVICE);
			// 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
			falg = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception e) {
			
		}
		
		return falg;
	}
	
	/**
	 * 满万，变成  12.45人   不满万正常输出
	 * @param mact
	 * @param times
	 * @return
	 */
	public static String number2TenThousand(Context mact,int times){
		if(times<10000){
			return times+"";
		}else{
			int vala = times/10000;
			int valb = times%10000;
			return vala+"."+valb+mact.getResources().getString(R.string.subscribe_ten_thousand);
		}
	}

	/**
     * 字符转ASC
     * 
     * @param st
     * @return
     */
    public static int getAsc(String st) {
    	int ascNum;
        byte[] gc = st.getBytes();
        ascNum = (int) gc[0];
        return ascNum;
    }
    
    //语音识别key -by tianhu
	public static final String APPKEY = "1393894870000223"; 
	public static final String SECRETKEY = "a60baf1aa8a629257881209769f58810";
	private static String speechVoice ="";
	private static MediaPlayer mPlayer;
	private static MediaRecorder mRecorder;
	public static String VoiceName ="/tingting_speech_voice1.wav";
	public static String getVoiceFilePath(Context context){
		 return StorageUtils.getCacheDirectory(context).toString() +"/voicefile";
	}
	//更新动画状态
	 public final static int UPDATA_ANIMATION = 0x101102;
	/**
	 * AsrEngine___VoiceText
	 */
	public static  String getVoiceText(){
		if(null != speechVoice)
			return speechVoice;
		return null;
	}
	
	public static void deleteAmrAddWavFile(Context context,int value) {
		File amrFile = new File(getVoiceFilePath(context)+Utils.VoiceName.replace(".wav", ".amr"));
		if(amrFile.exists()){
			amrFile.delete();
		}
		if(value == 1){
			File wavFile = new File(getVoiceFilePath(context)+Utils.VoiceName);
			if(wavFile.exists()){
				wavFile.delete();
			}
		}
	}
	
	/**
	 * 开启录音
	 * @param context
	 */
	public static void startRecord(Context context) {
		try {
			stopPlaying();
			File mFile = new File(getVoiceFilePath(context));
			mFile.mkdirs();
			mRecorder = new MediaRecorder();
			// 设置录音的声音来源
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			// 设置录制的声音的输出格式（必须在设置声音编码格式之前设置）
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);  
			// 设置MediaRecorder录制音频的编码为amr.貌似android就支持amr编码。   
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);  
			mRecorder.setOutputFile(getVoiceFilePath(context)+VoiceName.replace("wav", "amr"));
			mRecorder.prepare();
			// 开始录音
			mRecorder.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 停止录音 
	 */
	public static void stopRecord(){
		try {
			if (mRecorder != null) {
				mRecorder.stop();
				mRecorder.release();
				mRecorder = null;
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 录音播放
	 * @param mFileName
	 * @param basicHandler 
	 */
	public static void  startPlaying(String mFileName, final Handler basicHandler) { 
		try { 
			stopPlaying();
			mPlayer = new MediaPlayer(); 
            mPlayer.setDataSource(mFileName); 
            mPlayer.prepare(); 
            mPlayer.start(); 
            mPlayer.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					stopPlaying();
					basicHandler.sendEmptyMessage(UPDATA_ANIMATION);
				}
			});
        } catch (IOException e) { 
//            Log.e(LOG_TAG, "prepare() failed"); 
        } 
    } 
 
    /**
     * 停止播放
     */
    public static void stopPlaying() { 
    	if(null != mPlayer){
    		mPlayer.release(); 
    		mPlayer = null; 
    	}
    }
    /**添加音频到私人电台成功**/
    public final static int ADDVODINTOPRIVATERADIO_SUCCESS = 0x11000000; 
    /**添加音频到私人电台失败**/
    public final static int ADDVODINTOPRIVATERADIO_FAILED= 0x11000001; 
    /**删除私人电台音频成功**/
    public final static int DELETEVODINTOPRIVATERADIO_SUCCESS = 0x11000002; 
    /**删除私人电台音频失败**/
    public final static int DELETEVODINTOPRIVATERADIO_FAILED= 0x11000003; 
    /**添加音频到节目成功**/
    public final static int ADDVODINTOPRIVATERADIO_PROGRAM_SUCCESS = 0x12000000; 
    /**添加音频到节目失败**/
    public final static int ADDVODINTOPRIVATERADIO_PROGRAM_FAILED= 0x12000001; 
    /**删除节目音频成功**/
    public final static int DELETEVODINTOPRIVATERADIO_PROGRAM_SUCCESS = 0x12000002; 
    /**删除节目音频失败**/
    public final static int DELETEVODINTOPRIVATERADIO_PROGRAM_FAILED= 0x12000003; 



    public static void deleteCommentLetterVoice(Map<Integer, String> mVoiceRecord){
		if(mVoiceRecord.size()>0){
			Iterator iter = mVoiceRecord.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				File mFile = new File((String) entry.getValue());
				if(mFile.exists()){
					mFile.delete();
				}
			}
		}
    }
    
    public final static int EVENT_ANIMATION_END = 0x210011;
	
	public static void deployOpenTranslateAnimation(final View view, final float topYDelta,
			final float toYDelta, final Handler mHandler) {
		final int top = (int) (topYDelta + toYDelta);
		ViewWrapper wrapper = new ViewWrapper(view);
		ObjectAnimator animation = ObjectAnimator.ofFloat(wrapper, "top", top);
		animation.setDuration(800);
		animation.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
				params.setMargins(0, (int) top, 0, 0);
				view.setLayoutParams(params);
				view.requestLayout();
				Message msg = mHandler.obtainMessage();
				msg.what = EVENT_ANIMATION_END;
				mHandler.sendMessage(msg);
				super.onAnimationEnd(animation);
			}
		});
		animation.start();
//		return animation;
	}
	
	/**
	 * 播放次数
	 * @param num
	 * @return
	 */
	public static String dispatchPlayNum(int num) {
		if (num <= 100000) {
			return num + "";
		} else {
			int n = num / 10000;
			int m = (num % 10000) / 1000;
			return String.valueOf(n) + "." + String.valueOf(m) + "万";
		}
	}
}
