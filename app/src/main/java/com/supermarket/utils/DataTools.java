package com.supermarket.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.finance.common.utils.DensityUtils;
import com.supermarket.R;

import java.text.DecimalFormat;

public class DataTools {

    /**
	 * 将给定的数字按照一定的规则进行转换输出<br>
	 * 规则如下：<br>
	 * 长度大于4位，单位(万)，小数点后留1位<br>
	 * 
	 * 长度大于8位，单位(亿)，小数点后留1位<br>
	 */
	public static String getNum(int value) {
		if (value  < (int) Math.pow(10, 4)) {
			return String.valueOf(value);
		} else if (value < (int) Math.pow(10, 8)) {
			return value / 10000 + "." + (value % 10000) / 1000 + "万";
		} else {
			return value / 100000000 + "." + (value % 100000000) / 10000000 + "亿";
		}
	}
	
	/**
	 * 设置控件的颜色值
	 * @param view 指定的控件
	 * @param color 指定的颜色
	 * @param str 指定的内容
	 * @param start 改变颜色开始的位置
	 * @param end 改变颜色结束的位置
	 * @param textSize 改变颜色内容的大小
	 */
	public static void setColorForStirngId(TextView view, int color, String str, int start, int end, int textSize) {
		SpannableStringBuilder style = new SpannableStringBuilder(str);
		style.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		if (0 != textSize) {
			style.setSpan(new AbsoluteSizeSpan(textSize), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		}
		view.setText(style);
	}
	
	
	/**
	 * 检查当前域名是否为应用规则内域名
	 * @param value
	 * @return
	 */
	public static boolean isValidForHost(String value) {
		if (TextUtils.isEmpty(value)) {
			return false;
		}
		
		if (value.indexOf(".dev.ting-ting.cn") != -1
				|| value.indexOf(".test.ting-ting.cn") != -1
				|| value.indexOf(".tingtingfm.com") != -1) {
			return true;
		}
		
		return false;
	}

	/**
	 * 二级频道 的收藏 & 订阅设置的值
	 * @param mContext
	 * @return
	 */
	public static int getPaddingLeft(Context mContext){
		return DensityUtils.dp2px(mContext, mContext.getResources().getDimension(R.dimen.dip_3));
	}
	public static int getPaddingTop(Context mContext){
		return DensityUtils.dp2px(mContext, mContext.getResources().getDimension(R.dimen.dip_1));
	}

    /**
     * 判断播放地址是否为空，过滤前缀
     * @param url
     * @return 空为true
     */
    public static boolean isEmptyForUrl(String url) {
        if (TextUtils.isEmpty(url))
            return true;

        if (url.equals("m3u8live:") || url.equals("m3u8:")) {
            return true;
        }

        return false;
    }


    /**
     * 根据指定的规则拼接字符串,返回拼接好的字符串
     * @param title 频率名
     * @param frequency 频率呼号
     * @return 返回拼接好的字符串
     */
    public static String joinStr(String title, String frequency) {
        if (!TextUtils.isEmpty(title)) {
            if (!TextUtils.isEmpty(frequency)
                    && !title.startsWith(frequency)
                    && !title.endsWith(frequency)) {
                title += frequency;
            }
        }

        return title;
    }

    /**
     * 将字符串转换成有效数字
     * @param value 字符串
     * @return 返回有效数字
     */
    public static int stringToint(String value) {
        int temp = 0;
        try {
            temp = Integer.valueOf(value);
        } catch (NumberFormatException e) {
        }

        return temp;
    }

	/**
	 * 保留两位小数
	 * @param num
	 * @return
	 */
	public static String getDecimalFormat(double num){
		DecimalFormat format = new DecimalFormat("#.00");
		return getFrequency(format.format(num));
	}

	/**
	 * 悦驾精灵 调频 进制 0.05 大于5进1显示0，小于5显示5
	 * @param strf
	 * @return
	 */
	public static String getFrequency(String strf){
		try{
			String[] sd = strf.split("\\.");

			int f1 =  Integer.parseInt(sd[0]);
			int f2 = Integer.parseInt(sd[1].substring(0,1));
			int f3 = Integer.parseInt(sd[1].substring(1));

			if(f3 == 5){

			}else if(f3 == 0){

			}else if(f3>5){
				f3 = 0;
				f2 = f2+1;
				if(f2 == 10){
					f2 = 0;
					f1 = f1 + 1;
				}
			}else if(f3 < 5){
				f3 = 5;
			}
			return f1 +"."+f2+""+f3;
		}catch(Exception e){
		}
		return "108.00";
	}
}
