package com.supermarket.utils;

import android.content.Context;
import android.text.TextUtils;

import com.finance.cache.ConfigurationCache;
import com.supermarket.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间工具类
 * @author lqsir
 *
 */
public class TimeUtils {
	public static class ProgramValueInfo {
		public int currentValue;
		public int maxValue;

        @Override
        public String toString() {
            return "ProgramValueInfo{" +
                    "currentValue=" + currentValue +
                    ", maxValue=" + maxValue +
                    '}';
        }
    }
	
	public enum TimeFormat {
		TimeFormat1("yyyy.MM.dd"),
		TimeFormat2("yyyy-MM-dd"),
		TimeFormat3("yyyyMMddHHmmss"),
		TimeFormat4("yyyy-MM-dd HH:mm:ss"),
		TimeFormat5("yy:MM:dd:HH:mm:ss"),
		TimeFormat6("yyyy:MM:dd"),
		TimeFormat7("MM月dd日"),
		TimeFormat8("HH:mm:ss");

		String format;
		
		TimeFormat(String value) {
			format = value;
		}
		
		public String getValue() {
			return format;
		}
	}
	
	/**
	 * 将给定的时间值转换成H:M:S，当小时为0时，只显示M:S
	 * @param time    要转换的时间值
	 * @return    H:M:S/M:S
	 */
	public static String converToHms(int time) {
		int h = 0, m = 0, s = 0;
		s = time % 60;
		time = time / 60;
		m = time % 60;
		h = time / 60;
		return h == 0 ? getDoubleNum(m) + ":" + getDoubleNum(s) 
				: getDoubleNum(h) + ":" + getDoubleNum(m) + ":" + getDoubleNum(s);
	}

	private static String getDoubleNum(int value) {
		return value > 9 ? value + "" : (value == 0 ? "00" : "0" + value);
	}
	
	public static int getTotalTime(String st) {
		int totalTime = 0;
		String[] times = st.split(":");
		for (int i = times.length; i > 0; i--) {
			totalTime += Integer.valueOf(times[i - 1]) * Math.pow(60, times.length - i);
		}
		
		return totalTime;
	}
	
    /**
     * 根据提供的格式返回对应的时间字符串
     * @param format {@link TimeFormat}
     * @return
     */
    public static String getTimeForSpecialFormat(TimeFormat format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getValue(), Locale.CHINA);
        return sdf.format(Calendar.getInstance().getTime());
    }
    
    public static String getTimeForSpecialFormat(TimeFormat format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getValue(), Locale.CHINA);
        return sdf.format(date);
    }
    
    /**
     * 给定的日期是否是今天
     * @param date 给定的日期
     * @return true 表示今天 flase 表示非今天
     * @author lqsir
     */
	public static boolean isTodayForDate(String date) {
		return getTimeForSpecialFormat(TimeFormat.TimeFormat2).equals(date);
	}
	
	/**
	 * 比较给定的2个时间值是否相等
	 * @param value
	 * @param date
	 * @return
	 */
	public static boolean isEqualsForDates(long value, String date) {
		return getTimeForFormatAndDate(TimeFormat.TimeFormat2, value).equals(date);
	}

    
    /**
     * 以给定的格式、给定的时间值来返回相应结果
     * @param value 当前时间秒值
     * @return 返回当前秒值的年月日
     * @author lqsir
     */
    public static String getTimeForFormatAndDate(TimeFormat format, long value) {
    	if (value == 0l)
    		return "";
    	
        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(value * 1000);
        return getTimeForSpecialFormat(format, c.getTime());
    }
    

    /**
     * 返回当前时间点的播出状态
     * 0->正在直播
     * 1-> 未播出
     * -1-> 已播出
     * @param st 开始时间
     * @param et 结束时间
     * @return 返回当前时间点的播出状态
     */
    public static int isPlayStatus(String st, String et) {
        if (TextUtils.isEmpty(st) || TextUtils.isEmpty(et)) {
            return -1;
        }

		SimpleDateFormat sdf = new SimpleDateFormat(TimeFormat.TimeFormat5.getValue(), Locale.CHINA);
		long startTime;
		long endTime;
		Date date;
		try {
			date = sdf.parse(timeToStr(st));
			startTime = date.getTime();
			date = sdf.parse(timeToStr(et));
			endTime = date.getTime();
			long currentTime = System.currentTimeMillis();
			if (currentTime >= startTime && currentTime <= endTime) {
				return 0;
			} else if (startTime > currentTime) {
				return 1;
			} else if (endTime < currentTime) {
				return -1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}
	

	/**
	 * 根据相关规则返回结果
	 * @param con
	 * @param value  秒值
	 * @return
	 */
	public static String getMinuteHour(Context con, long value) {
		long currentTime = System.currentTimeMillis();
		long time = (currentTime - value * 1000) / 1000;
		if (time < 5 * 60) {//5分钟内，显示为刚刚
			return  con.getResources().getString(R.string.just_now);
		} else if (time >= 5 * 60 && time < 60 * 60) {//1小时内，显示为XX分钟前
			return time / 60 + con.getResources().getString(R.string.date_minute);
		} else if (time >= 60 * 60 && time < 24 * 60 * 60) {//24小时内，显示为XX小时前
			return time / (60 * 60) + con.getResources().getString(R.string.date_hour);
		} else {//超过24小时显示日期XXXX.XX.XX
			return getTimeForFormatAndDate(TimeFormat.TimeFormat1, value);
		}
	}
	
	/**
	 * 判断当前日期是星期几
	 * @param context
	 * @param pTime 要判断的时间
	 * @param subscript 当前时间在list里的下标
	 * @return dayForWeek 判断结果
	 * @throws Exception 发生异常
	 */
	public static String dayForWeek(Context context,String pTime,int subscript) throws Exception {
		SimpleDateFormat  format = new SimpleDateFormat(TimeFormat.TimeFormat2.getValue(), Locale.CHINA);
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if(c.get(Calendar.DAY_OF_WEEK) == 1){
			dayForWeek = 7;
		}else{
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		if (subscript == 0) {
			return context.getString(R.string.today);
		} else if (subscript == 1) {
			return context.getString(R.string.yesterday);
		}

		switch (dayForWeek) {
		case 1:
			return context.getString(R.string.monday, pTime.replace("-", "."));
		case 2:
			return context.getString(R.string.tuesday, pTime.replace("-", "."));
		case 3:
			return context.getString(R.string.wednesday, pTime.replace("-", "."));
		case 4:
			return context.getString(R.string.thursday, pTime.replace("-", "."));
		case 5:
			return context.getString(R.string.friday, pTime.replace("-", "."));
		case 6:
			return context.getString(R.string.saturday, pTime.replace("-", "."));
		case 7:
			return context.getString(R.string.sunday,pTime.replace("-", "."));
		}
		return "";
	}
	
	/**
	 * 得到当前月，日,与星期几。下标0为月，1为日，2为星期
	 */
	public static final String[] getCurrentDayOfWeek(Context context) {
		String[] date = new String[3];
		Calendar c = Calendar.getInstance();  
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));  
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份  
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码  
        date[0]=mMonth;
        date[1]=mDay;
		int val = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
		String strDay = null;
		switch (val) {
		case 0:
			strDay = context.getString(R.string.sunday2);
			break;
		case 1:
			strDay = context.getString(R.string.monday2);
			break;
		case 2:
			strDay = context.getString(R.string.tuesday2);
			break;
		case 3:
			strDay = context.getString(R.string.wednesday2);
			break;
		case 4:
			strDay = context.getString(R.string.thursday2);
			break;
		case 5:
			strDay = context.getString(R.string.friday2);
			break;
		case 6:
			strDay = context.getString(R.string.saturday2);
			break;
		}
		date[2]=strDay;
		return date;
	}

    /**
     * 返回当前小时+分钟
     * @return
     */
	public static final String getTimeByCalendar() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 小时
		int minute = cal.get(Calendar.MINUTE);// 分
		
		return hour + ":" + getDoubleNum(minute);
	}
	
	
	public static String millisToString(long millis) {
		String value = "00:00:00";

		if (millis <= 0)
			return value;

		millis = java.lang.Math.abs(millis);

		millis /= 1000;
		int sec = (int) (millis % 60);
		millis /= 60;
		int min = (int) (millis % 60);
		millis /= 60;
		int hours = (int) millis;

		String time;
		DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		format.applyPattern("00");

		time = format.format(hours) + ":" + format.format(min) + ":" + format.format(sec);

		return time;
	}
	
	/**
	 * 显示倒计时，只有分+秒
	 * @param millis
	 * @return
	 */
	public static String countdownString(long millis) {
		String value = "00:00";
		boolean negative = millis <= 0;

		if (negative)
			return value;

		millis = java.lang.Math.abs(millis);

		millis /= 1000;
		int sec = (int) (millis % 60);
		millis /= 60;
		int min = (int) (millis % 60);
		millis /= 60;
		int hours = (int) millis;
		min = hours * 60 + min;
		
		String time;
		DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		format.applyPattern("00");

		time = format.format(min) + ":" + format.format(sec);

		return time;
	}
	
	public static ProgramValueInfo getLiveProgramValue(String st, String et) {
		ProgramValueInfo info = new ProgramValueInfo();
		SimpleDateFormat sdf = new SimpleDateFormat(TimeFormat.TimeFormat5.getValue(), Locale.US);
		long startTime;
		long endTime;
		Date date;
		try {
			date = sdf.parse(timeToStr(st));
			startTime = date.getTime();
			date = sdf.parse(timeToStr(et));
			endTime = date.getTime();
			long currentTime = System.currentTimeMillis();
			info.maxValue = (int) (endTime - startTime);
			info.currentValue = (int) (currentTime - startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return info;
	}

	private static String timeToStr(String st) {
		return getTimeForSpecialFormat(TimeFormat.TimeFormat6) + ":" + st + ":00";
	}

	public static long getMillis(String str) {
		String[] tt = str.split(":");
		int h = Integer.valueOf(tt[0]);
		int s = Integer.valueOf(tt[1]);
		
		return (h * 3600 + s *60) * 1000;
	}

	public static long getMillis2(String str) {
		if(!TextUtils.isEmpty(str)){
			String[] tt = str.split(":");
			int h = 0;
			int m = 0;
			int s = 0;
			if (tt.length == 2) {
				h = Integer.valueOf(tt[0]);
				m = Integer.valueOf(tt[1]);
			} else {
				h = Integer.valueOf(tt[0]);
				m = Integer.valueOf(tt[1]);
				s = Integer.valueOf(tt[2]);
			}
			return (h * 3600 + m * 60 + s);
		}
		return 0;
	}

	/**
	 * 得到当天到当前时刻的秒数
	 * @return
	 */
	public static int getCurrentSecondForDay() {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		System.out.println(hour + ":" + minute + ":" + second);
		return hour * 3600 + minute * 60 + second;
	}

    /**
     * 隔半点，与整点功能刷新数据功能。
     * @return true 表示隔半点，或整点了。可以删除要地数据。  false相反 不可以删除本地数据。
     */
	public static boolean isClearData(){
        long currentTime = (int) System.currentTimeMillis();
        long saveTime = ConfigurationCache.getRefreshTime();
        if (saveTime < currentTime) {
            return true;
        }
        return false;
	}

    /**
     * 设置下一次半点，整点时间。
     */
    public static void setRefreshTime(){
        long currentTime = (int) System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        int minute = cal.get(Calendar.MINUTE);// 分
        int second = cal.get(Calendar.SECOND);// 秒
        long timeb = getTime(minute) * 60 * 1000 + getTime(second) * 1000;//获取毫秒
        ConfigurationCache.setRefreshTime(currentTime + timeb);
    }

    /**
     * 返回当时分如5分 返回 25分    如39分 返回 21分。
     * @param time
     * @return
     */
    private static int getTime(int time){
        int timea = 0;
        if (time < 30) {
            timea = 30 - time;
        } else if (time >= 30) {
            timea = 60 - time;
        }
        return timea;
    }

	/**
	 * 时间戳 转为 小时
	 * @param serverTime
	 * @return
	 */
	public static int timeStampToHour(String serverTime){
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		Date date = new Date(Long.parseLong(serverTime + "000"));
		return Integer.parseInt(sdf.format(date));
	}


	/***
	 *  判断是否 在本周内
	 * @param time
	 * @return
	 */
	public static boolean isThisWeek(String time) {
		if(TextUtils.isEmpty(time))
			return false;
		Calendar calendar = Calendar.getInstance();
		int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		if (paramWeek == currentWeek) {
			return true;
		}
		return false;
	}

	/**
	 * 获取月日
	 * @param dateTime
	 * @return
	 */
	public static String getMonthDay(String dateTime){
		if(TextUtils.isEmpty(dateTime))
			return "";

		SimpleDateFormat sdff = new SimpleDateFormat(TimeFormat.TimeFormat4.getValue(), Locale.CHINA);
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
		Date d = new Date();
		try {
			d = sdff.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(d);
	}

	/**
	 * 获取小时 分钟 HH:MM
	 * @param dateTime
	 * @return
	 */
	public static String getHourM(String dateTime){
		if(TextUtils.isEmpty(dateTime))
		 return "";
		SimpleDateFormat sdff = new SimpleDateFormat(TimeFormat.TimeFormat4.getValue(), Locale.CHINA);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			Date date = sdff.parse(dateTime);
			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}


	/**
	 * 传入时间日期  返回 时分秒
	 * @param dateTime 2016-05-07 12:00:23
	 * @return
	 */
	public static String getHMSTime(String dateTime){
		if(TextUtils.isEmpty(dateTime))
			return "";
		SimpleDateFormat sdff = new SimpleDateFormat(TimeFormat.TimeFormat4.getValue(), Locale.CHINA);
		SimpleDateFormat  format = new SimpleDateFormat(TimeFormat.TimeFormat8.getValue(), Locale.CHINA);
		try {
			Date date = sdff.parse(dateTime);
			return format.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 传入时间日期  返回  日期
	 * @param dateTime 2016-05-07 12:00:23
	 * @return
	 */
	public static String getDate(String dateTime){
		if(TextUtils.isEmpty(dateTime))
			return "";
		SimpleDateFormat sdff = new SimpleDateFormat(TimeFormat.TimeFormat4.getValue(), Locale.CHINA);
		SimpleDateFormat  format = new SimpleDateFormat(TimeFormat.TimeFormat2.getValue(), Locale.CHINA);
		try {
			Date date = sdff.parse(dateTime);
			return format.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/***
	 * 判断开始 时间 是否已经处理 开始播放状态 了
	 * @param dateTime 2016-05-07 12:00:23
	 * @return
	 */
	public static boolean isStartPlay(String dateTime){
		if(TextUtils.isEmpty(dateTime))
			return false;
		SimpleDateFormat sdff = new SimpleDateFormat(TimeFormat.TimeFormat4.getValue(), Locale.CHINA);
		try {
			Date date = sdff.parse(dateTime);
			long current = System.currentTimeMillis();
			return current >= date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

    /**
     * 预约提醒用到。
     * 给二个日期，判断参数一日期在参数二的前面，后面。过滤掉过期数据，如果在前面，表示过期。
     *
     * @param date1 活动结束日期 时间
     * @param date2 当前日期 时间
     * @return 返回值 -1，1，0 。用于数据按日期时间排序，进行提醒。
     */
    public static int isOverDue(String date1,String date2){
		int overdue = 0;
		if(!TextUtils.isEmpty(date1.trim()) && !TextUtils.isEmpty(date2.trim())){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d1 = sdf.parse(date1);
				Date d2 = sdf.parse(date2);
				return d1.compareTo(d2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return overdue;
	}

	/**
	 * 返回给定日期时间的秒值
	 * @param dataTime
	 * @return
	 */
	public static long getTime(String dataTime){
		long seconValue = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = sdf.parse(dataTime);
			seconValue = d1.getTime();
			d1.getSeconds();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return seconValue;
	}


	/**
	 * 获取当前时间日期
	 * @return yyyy-MM-dd HH:mm:ss格式日期
	 */
	public static String getCurrentDateTime(){
		long l = System.currentTimeMillis();
		Date date = new Date(l);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * 两个日期相差几天
	 * @param sdate
	 * @return
	 */
	public static int friendly_time(String sdate) {
		Date time = toDate(sdate);
		if(time == null) {
			return 0;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		String curDate = formatter.format(new java.util.Date());// 获取当前时间
		Date cur =  toDate(curDate);
		//Calendar cal = Calendar.getInstance();

		long lt = time.getTime()/86400000;
		//long ct = cal.getTimeInMillis()/86400000;
		long ct = cur.getTime()/86400000;
		int days = (int)(lt - ct);
		return days;
	}

	/**
	 * 将字符串转位日期类型
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater2.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy.MM.dd");
		}
	};
}
