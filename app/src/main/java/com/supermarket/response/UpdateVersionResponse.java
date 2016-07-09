package com.supermarket.response;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * 版本升级对象
 */
public class UpdateVersionResponse extends BaseResponse {

	@Expose
	private UpdateResult data;

	public UpdateResult getData() {
		return data;
	}

	public class UpdateResult {
		@Expose
		private UpdateInfo new_versions;

		public UpdateInfo getNewVersion() {
			return new_versions;
		}
	}

	public static class UpdateInfo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4221047278594643606L;
		@Expose
		private String url;
		@Expose
		private String v;
		@Expose
		private int force;
		@Expose
		private String intro;

		public String getUrl() {
			return url;
		}

		public String getVersion() {
			return v;
		}

		public int getForce() {
			return force;
		}

		public String getIntro() {
			return intro;
		}

	}
}