package com.supermarket.response;

import com.google.gson.annotations.Expose;


public class UploadFaceResponse extends BaseResponse {
	@Expose
	public Face data;
	
	public class Face{
		@Expose
		public String path;
		@Expose
		public String url;
		@Override
		public String toString() {
			return "Face [path=" + path + ", url=" + url + "]";
		}
	}
}
