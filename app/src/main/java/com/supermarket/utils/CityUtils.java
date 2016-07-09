package com.supermarket.utils;

import android.content.Context;

import com.supermarket.R;

public class CityUtils {

	/**
	 * 截取城市名称  去掉市/县
	 */
	public static String interceptStrToCity(Context context, String area) {
		if (area != null && !area.equals("")) {
			if (area.endsWith(context.getResources().getString(
					R.string.discover_native_area_name_end_value_shi))
					|| area.endsWith(context.getResources().getString(
							R.string.discover_native_area_name_end_value_xian))) {
				area = area.substring(0, area.length() - 1);
			}
		}
		return area;
	}
	
	/**
	 * 截取城市台  （北京台） 去掉台 获取到城市名称
	 * @param area
	 * @param taiStr
	 * @return
	 */
	public static String interceptStrToCity4Tai(String area,String taiStr) {
		if (area != null && !area.equals("")) {
			if (area.endsWith(taiStr)) {
				area = area.substring(0, area.length() - 1);
			}
		}
		return area;
	}
}
