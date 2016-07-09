package com.finance.cache;

import com.supermarket.utils.PreferUtils;

public class FavoriteCache {

	/**
	 * 电台收藏
	 */

	public static void setFmFavorite(String favMap) {
		PreferUtils.putString(PreferencesCache.getFavoritePrefer(), Constants.FMFavBoo, favMap);
	}

	/**
	 * 得到电台的收藏状态
	 * 
	 * @return
	 */
	public static boolean getFmFavBoo() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.FMFavBoo).split("/");
		if (strs.length > 1) {
			return strs[1].equals(Isfaved) ? true : false;
		} else {
			return false;
		}

	}

	/**
	 * 得到电台的fmid
	 * 
	 * @return
	 */
	public static int getFmId() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.FMFavBoo).split("/");
		if (strs.length > 1) {
			return Integer.parseInt(strs[0]);
		} else {
			return 0;
		}
	}

	/**
	 * 设置私人电台收藏状态
	 * 
	 * @param favMap
	 */
	public static void setPrivateFmFavorite(String favMap) {
		PreferUtils.putString(PreferencesCache.getFavoritePrefer(), Constants.PrivateFMFavBoo, favMap);
	}

	/**
	 * 得到私人私人电台的收藏状态
	 * 
	 * @return
	 */
	public static boolean getPrivateFmFavBoo() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.PrivateFMFavBoo).split("/");
		if (strs.length > 1) {
			return strs[1].equals(Isfaved) ? true : false;
		} else {
			return false;
		}
	}

	/**
	 * 得到电台的fmid
	 * 
	 * @return
	 */
	public static int getprivatePmId() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.PrivateFMFavBoo).split("/");
		if (strs.length > 1) {
			return Integer.parseInt(strs[0]);
		} else {
			return 0;
		}
	}

	/**
	 * 设置音频收藏
	 */
	public static void setVoidFavorite(String favMap) {
		PreferUtils.putString(PreferencesCache.getFavoritePrefer(), Constants.VoidFavBoo, favMap);
	}

	/**
	 * 得到音频收藏状态
	 * 
	 * @return
	 */
	public static boolean getVoidFavBoo() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.VoidFavBoo).split("/");
		if (strs.length > 1) {
			return strs[1].equals(Isfaved) ? true : false;
		} else {
			return false;
		}

	}

	/**
	 * 得到电台的fmid
	 * 
	 * @return
	 */
	public static int getVoidId() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.VoidFavBoo).split("/");
		if (strs.length > 1) {
			return Integer.parseInt(strs[0]);
		} else {
			return 0;
		}

	}

	/**
	 * 设置私人电台节目收藏
	 */
	public static void setPrivateProgramFavorite(String favMap) {
		PreferUtils.putString(PreferencesCache.getFavoritePrefer(), Constants.PrivateProgramFavBoo, favMap);
	}

	/**
	 * 得私人电台节目收藏状态
	 * 
	 * @return
	 */
	public static boolean getPrivateProgramFavBoo() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.PrivateProgramFavBoo).split("/");
		if (strs.length > 1) {
			return strs[1].equals(Isfaved) ? true : false;
		} else {
			return false;
		}
	}

	/**
	 * 得到私人电台节目的id
	 * 
	 * @return
	 */
	public static int getPrivateProgramId() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.PrivateProgramFavBoo).split("/");
		if (strs.length > 1) {
			return Integer.parseInt(strs[0]);
		} else {
			return 0;
		}
	}

	/**
	 * 设置专辑收藏
	 */
	public static void setAlbumFavorite(String favmap) {
		PreferUtils.putString(PreferencesCache.getFavoritePrefer(), Constants.AlbumFavBoo, favmap);
	}

	/**
	 * 得专辑收藏状态
	 * 
	 * @return
	 */
	public static boolean getAlbumFavBoo() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.AlbumFavBoo).split("/");
		if (strs.length > 1) {
			return strs[1].equals(Isfaved) ? true : false;
		} else {
			return false;
		}
	}

	/**
	 * 得到专辑收藏id
	 * 
	 * @return
	 */
	public static int getAlbumId() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.AlbumFavBoo).split("/");
		if (strs.length > 1) {
			return Integer.parseInt(strs[0]);
		} else {
			return 0;
		}
	}
	
	/**
	 * 设置专题收藏
	 */
	public static void setSpecialFavorite(String favmap) {
		PreferUtils.putString(PreferencesCache.getFavoritePrefer(), Constants.SpecialFavBoo, favmap);
	}

	
	/**
	 * 得专题收藏状态
	 * 
	 * @return
	 */
	public static boolean getSpecialFavBoo() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.SpecialFavBoo).split("/");
		if (strs.length > 1) {
			return strs[1].equals(Isfaved) ? true : false;
		} else {
			return false;
		}
	}

	/**
	 * 得到专辑收藏id
	 * 
	 * @return
	 */
	public static int getSpecialId() {
		String[] strs = PreferUtils.getString(PreferencesCache.getFavoritePrefer(), Constants.SpecialFavBoo).split("/");
		if (strs.length > 1) {
			return Integer.parseInt(strs[0]);
		} else {
			return 0;
		}
	}
	
	

	public static String Isfaved = "1";
	public static String Isfav = "0";
}
