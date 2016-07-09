package com.finance.common.http;

import com.finance.common.http.util.AjaxCallBack;
import com.finance.common.http.util.AjaxParams;
import com.finance.common.http.util.RetryHandler;
import com.finance.common.http.util.SyncRequestHandler;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class ASyncHttpClient {

	private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8 * 1024; // 8KB
	private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
	private static final String ENCODING_GZIP = "gzip";

	private static int maxConnections = 5;

	private static int socketTimeout = 30 * 1000;

	private static int maxRetries = 1;

	private final DefaultHttpClient httpClient;
	private final HttpContext httpContext;
	private String charset = "UTF-8";
	private final Map<String, String> clientHeaderMap;

	public ASyncHttpClient() {
		BasicHttpParams httpParams = new BasicHttpParams();
		ConnManagerParams.setTimeout(httpParams, socketTimeout);
		ConnManagerParams.setMaxConnectionsPerRoute(httpParams,
				new ConnPerRouteBean(maxConnections));
		ConnManagerParams.setMaxTotalConnections(httpParams, maxConnections);

		HttpConnectionParams.setSoTimeout(httpParams, socketTimeout);
		HttpConnectionParams.setConnectionTimeout(httpParams, socketTimeout);
		HttpConnectionParams.setTcpNoDelay(httpParams, true);
		HttpConnectionParams.setSocketBufferSize(httpParams,
				DEFAULT_SOCKET_BUFFER_SIZE);

		HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);

		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory
				.getSocketFactory(), 443));
		ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(
				httpParams, schemeRegistry);

		httpContext = new SyncBasicHttpContext(new BasicHttpContext());
		httpClient = new DefaultHttpClient(cm, httpParams);
		httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
			public void process(HttpRequest request, HttpContext context) {
				if (!request.containsHeader(HEADER_ACCEPT_ENCODING)) {
					request.addHeader(HEADER_ACCEPT_ENCODING, ENCODING_GZIP);
				}
				for (String header : clientHeaderMap.keySet()) {
					request.addHeader(header, clientHeaderMap.get(header));
				}
			}
		});

		httpClient.addResponseInterceptor(new HttpResponseInterceptor() {
			public void process(HttpResponse response, HttpContext context) {
				final HttpEntity entity = response.getEntity();
				if (entity == null) {
					return;
				}
				final Header encoding = entity.getContentEncoding();
				if (encoding != null) {
					for (HeaderElement element : encoding.getElements()) {
						if (element.getName().equalsIgnoreCase(ENCODING_GZIP)) {
							response.setEntity(new InflatingEntity(response
									.getEntity()));
							break;
						}
					}
				}
			}
		});
		
//		httpClient.setHttpRequestRetryHandler(new RetryHandler(maxRetries));

		clientHeaderMap = new HashMap<String, String>();
	}

	public HttpClient getHttpClient() {
		return this.httpClient;
	}

	public HttpContext getHttpContext() {
		return this.httpContext;
	}

	public void configCharset(String charSet) {
		if (charSet != null && charSet.trim().length() != 0) {
			this.charset = charSet;
		}
	}

	public void configCookieStore(CookieStore cookieStore) {
	    httpClient.setCookieStore(cookieStore);
		/*httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);*/
	}

	public void configUserAgent(String userAgent) {
		HttpProtocolParams.setUserAgent(this.httpClient.getParams(), userAgent);
	}

	public void configTimeout(int timeout) {
		final HttpParams httpParams = this.httpClient.getParams();
		ConnManagerParams.setTimeout(httpParams, timeout);
		HttpConnectionParams.setSoTimeout(httpParams, timeout);
		HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
	}

	public void configSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
		Scheme scheme = new Scheme("https", sslSocketFactory, 443);
		this.httpClient.getConnectionManager().getSchemeRegistry()
				.register(scheme);
	}

	public void configRequestExecutionRetryCount(int count) {
		this.httpClient.setHttpRequestRetryHandler(new RetryHandler(count));
	}

	public void addHeader(String header, String value) {
		clientHeaderMap.put(header, value);
	}

	public Object getSync(String url) throws IOException {
		return getSync(url, null);
	}

	public Object getSync(String url, AjaxParams params) throws IOException {
		HttpUriRequest request = new HttpGet(getUrlWithQueryString(url, params));
		return sendSyncRequest(httpClient, httpContext, request, null);
	}

	public Object getSync(String url, Header[] headers, AjaxParams params) throws IOException {
		HttpUriRequest request = new HttpGet(getUrlWithQueryString(url, params));
		if (headers != null)
			request.setHeaders(headers);
		return sendSyncRequest(httpClient, httpContext, request, null);
	}

	public Object postSync(String url) throws IOException {
		return postSync(url, null);
	}

	public Object postSync(String url, AjaxParams params) throws IOException {
		return postSync(url, paramsToEntity(params), null);
	}

	public Object postSync(String url, HttpEntity entity, String contentType) throws IOException {
		return sendSyncRequest(httpClient, httpContext,
				addEntityToRequestBase(new HttpPost(url), entity), contentType);
	}

	public Object postSync(String url, Header[] headers, AjaxParams params,
			String contentType) throws IOException {
		HttpEntityEnclosingRequestBase request = new HttpPost(url);
		if (params != null)
			request.setEntity(paramsToEntity(params));
		if (headers != null)
			request.setHeaders(headers);
		return sendSyncRequest(httpClient, httpContext, request, contentType);
	}

	public Object postSync(String url, Header[] headers, HttpEntity entity,
			String contentType) throws IOException {
		HttpEntityEnclosingRequestBase request = addEntityToRequestBase(
				new HttpPost(url), entity);
		if (headers != null)
			request.setHeaders(headers);
		return sendSyncRequest(httpClient, httpContext, request, contentType);
	}

	public File downloadSync(String url, File target,
			AjaxCallBack<File> callback) throws IOException {
		return downloadSync(url, null, target, callback);
	}

	public File downloadSync(String url, AjaxParams params, File target,
			AjaxCallBack<File> callback) throws IOException {
		HttpUriRequest request = new HttpGet(getUrlWithQueryString(url, params));
		
		return (File) new SyncRequestHandler(httpClient, httpContext, target)
				.sendRequest(request);
	}

	protected Object sendSyncRequest(DefaultHttpClient client,
			HttpContext httpContext, HttpUriRequest uriRequest,
			String contentType) throws IOException {
		if (contentType != null) {
			uriRequest.addHeader("Content-Type", contentType);
		}
		return new SyncRequestHandler(client, httpContext, charset)
				.sendRequest(uriRequest);
	}

	public static String getUrlWithQueryString(String url, AjaxParams params) {
		if (params != null) {
			String paramString = params.getParamString();
			url += "?" + paramString;
		}
		
		return url;
	}

	private HttpEntity paramsToEntity(AjaxParams params) {
		HttpEntity entity = null;

		if (params != null) {
			entity = params.getEntity();
		}

		return entity;
	}

	private HttpEntityEnclosingRequestBase addEntityToRequestBase(
			HttpEntityEnclosingRequestBase requestBase, HttpEntity entity) {
		if (entity != null) {
			requestBase.setEntity(entity);
		}

		return requestBase;
	}

	private static class InflatingEntity extends HttpEntityWrapper {
		public InflatingEntity(HttpEntity wrapped) {
			super(wrapped);
		}

		@Override
		public InputStream getContent() throws IOException {
			return new GZIPInputStream(wrappedEntity.getContent());
		}

		@Override
		public long getContentLength() {
			return -1;
		}
	}
}
