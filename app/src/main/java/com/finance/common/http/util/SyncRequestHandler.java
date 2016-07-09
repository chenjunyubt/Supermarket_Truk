package com.finance.common.http.util;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

import com.finance.common.http.entityhandler.EntityHandler;
import com.finance.common.http.entityhandler.FileEntityHandler;
import com.finance.common.http.entityhandler.StringEntityHandler;
import com.supermarket.utils.TTLog;

public class SyncRequestHandler {

	private final AbstractHttpClient client;
	private final HttpContext context;
	private EntityHandler entityHandler;

	public SyncRequestHandler(AbstractHttpClient client, HttpContext context, String charset) {
		this.client = client;
		this.context = context;

		entityHandler = new StringEntityHandler(charset);
	}

	public SyncRequestHandler(AbstractHttpClient client, HttpContext context, File target) {
		this.client = client;
		this.context = context;

		entityHandler = new FileEntityHandler(target);
	}

	public Object sendRequest(HttpUriRequest... params) throws IOException {
		IOException cause = null;
		try {
			TTLog.e("makeRequestWithRetries : before " + System.currentTimeMillis());
			HttpResponse response = client.execute(params[0], context);
			TTLog.e("makeRequestWithRetries : after " + System.currentTimeMillis());
			return entityHandler.handleEntity(response.getEntity());
		} catch (Exception e) {
			cause = new IOException("Exception " + e.getMessage());
		}
		
		if (cause != null)
			throw cause;
		else
			throw new IOException("Unkown Exception");
	}

}
