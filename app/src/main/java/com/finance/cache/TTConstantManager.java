/**
 * 
 */
package com.finance.cache;

/**
 * @author liqiang
 * @data 2014年8月21日
 * 
 */
public class TTConstantManager {
	private boolean isShowErrorDialog = false;
	private static TTConstantManager manager;
	private int product_id;
	private int scid;
	private String playlist_id;

	
	/**
	 * 订阅是否改变
	 */
	private boolean isChangeSubscribe = false;
	/**
	 * 收藏是否改变
	 */
	private boolean isChangeFavorite = false;
	
	private TTConstantManager() {

	}

	public synchronized static TTConstantManager getInstance() {
		if (manager == null)
			manager = new TTConstantManager();

		return manager;
	}

	public boolean isShowErrorDialog() {
		return isShowErrorDialog;
	}

	public void setShowErrorDialog(boolean isShowErrorDialog) {
		this.isShowErrorDialog = isShowErrorDialog;
	}

	public int getProductId() {
		return product_id;
	}

	public void setProductId(int product_id) {
		this.product_id = product_id;
	}

	public int getScid() {
		return scid;
	}

	public void setScid(int scid) {
		this.scid = scid;
	}

	public String getPlaylistId() {
		return playlist_id;
	}

	public void setPlaylistId(String playlist_id) {
		this.playlist_id = playlist_id;
	}



	public boolean isChangeSubscribe() {
		return isChangeSubscribe;
	}

	public void setChangeSubscribe(boolean isChangeSubscribe) {
		this.isChangeSubscribe = isChangeSubscribe;
	}

	public boolean isChangeFavorite() {
		return isChangeFavorite;
	}

	public void setChangeFavorite(boolean isChangeFavorite) {
		this.isChangeFavorite = isChangeFavorite;
	}
}
