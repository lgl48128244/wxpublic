package com.uflowertv.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	private static final Log log = LogFactory.getLog(HttpClientUtils.class);

	/**
	 * 连接超时
	 */
	private static final int CONNECT_TIMEOUT = 60000;

	/**
	 * 读取超时
	 */
	private static final int SOCKET_TIMEOUT = 60000;

	/**
	 * 连接中单一请求超时
	 */
	private static final int CONNECTION_REQUEST_TIMEOUT = 60000;

	private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = null;

	private static CloseableHttpClient closeableHttpClient = null;

	static {

		SSLContext sslcontext;
		try {
			sslcontext = SSLContext.getInstance("SSL");
			// 信任所有站点
			X509TrustManager x509TrustManager = new X509TrustManager() {

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}
			};
			sslcontext.init(null, new TrustManager[] { x509TrustManager }, new SecureRandom());
			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE);

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslConnectionSocketFactory).build();

			SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();

			MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();

			ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8).setMessageConstraints(messageConstraints).build();

			poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			poolingHttpClientConnectionManager.setDefaultConnectionConfig(connectionConfig);
			poolingHttpClientConnectionManager.setMaxTotal(400);
			poolingHttpClientConnectionManager.setDefaultMaxPerRoute(40);
			poolingHttpClientConnectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("localhost", 80)), 40);
			poolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);
			poolingHttpClientConnectionManager.setValidateAfterInactivity(1000);

			CookieStore cookieStore = new BasicCookieStore();

			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

			RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).setExpectContinueEnabled(true).setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();

			closeableHttpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).setDefaultCookieStore(cookieStore).setDefaultCredentialsProvider(credentialsProvider).setDefaultRequestConfig(defaultRequestConfig).setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).build();

			log.debug("构建HttpClient连接池完毕!");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		CloseableHttpResponse httpResponse = null;
		HttpGet get = new HttpGet(url);
		try {
			httpResponse = closeableHttpClient.execute(get);
			// 状态码=200
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
				return response;
			} else {
				log.error("Error " + httpResponse.getStatusLine().getStatusCode() + "!!!");
				return null;
			}
		} catch (ClientProtocolException e) {
			log.error("协议错误:" + e.getMessage());
			return null;
		} catch (IOException e) {
			log.error("IO错误:" + e.getMessage());
			return null;
		} finally {
			try {
				get.releaseConnection();
				httpResponse.close();
			} catch (IOException e) {
				log.error("IO错误:" + e.getMessage());
			}
		}
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 * @param object
	 * @return
	 */
	public static String post(String url, Object object) {
		CloseableHttpResponse httpResponse = null;
		List<Field> fields = null;
		HttpPost post = new HttpPost(url);
		try {
			fields = getFields(object);
			// 将fields装入表单
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			for (Field field : fields) {
				nameValuePairs.add(new BasicNameValuePair(field.getName(), field.get(object).toString()));
			}
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
			post.setEntity(urlEncodedFormEntity);
			httpResponse = closeableHttpClient.execute(post);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
				return response;
			} else {
				log.error("Error " + httpResponse.getStatusLine().getStatusCode() + "!!!");
				return null;
			}
		} catch (IllegalArgumentException e) {
			log.error("非法参数:" + e.getMessage());
			return null;
		} catch (IllegalAccessException e) {
			log.error("非法权限:" + e.getMessage());
			return null;
		} catch (UnsupportedEncodingException e) {
			log.error("不支持的编码格式:" + e.getMessage());
			return null;
		} catch (ClientProtocolException e) {
			log.error("协议错误:" + e.getMessage());
			return null;
		} catch (IOException e) {
			log.error("IO错误:" + e.getMessage());
			return null;
		} finally {
			// 释放连接
			try {
				post.releaseConnection();
				httpResponse.close();
			} catch (IOException e) {
				log.error("IO错误:" + e.getMessage());
			}
		}
	}

	public static String jaxRestful(String url, String message) {
		CloseableHttpResponse httpResponse = null;
		HttpPost post = new HttpPost(url);
		try {
			StringEntity stringEntity = new StringEntity(message, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			post.setEntity(stringEntity);
			httpResponse = closeableHttpClient.execute(post);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			} else {
				log.error("Error " + httpResponse.getStatusLine().getStatusCode() + "!!!");
				return null;
			}
		} catch (IllegalArgumentException e) {
			log.error("非法参数:" + e.getMessage());
			return null;
		} catch (UnsupportedEncodingException e) {
			log.error("不支持的编码格式:" + e.getMessage());
			return null;
		} catch (ClientProtocolException e) {
			log.error("协议错误:" + e.getMessage());
			return null;
		} catch (IOException e) {
			log.error("IO错误:" + e.getMessage());
			return null;
		} finally {
			// 释放连接
			try {
				post.releaseConnection();
				httpResponse.close();
			} catch (IOException e) {
				log.error("IO错误:" + e.getMessage());
			}
		}
	}


	public static String uploadFile(String url, Object object) {
		CloseableHttpResponse httpResponse = null;
		List<Field> fields = null;
		HttpPost post = new HttpPost(url);
		try {
			fields = getFields(object);
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			// file对象必须位于所有参数的最末端
			String fileParameters = null;
			File file = null;
			for (Field field : fields) {
				if (field.get(object) instanceof File) {
					fileParameters = field.getName();
					file = (File) field.get(object);
				} else {
					String name = field.getName();
					String value = (String) field.get(object);
					multipartEntityBuilder.addPart(name, new StringBody(value, ContentType.TEXT_PLAIN.withCharset("UTF-8")));
				}
			}
			multipartEntityBuilder.addPart(fileParameters, new FileBody(file));
			HttpEntity httpEntity = multipartEntityBuilder.build();
			post.setEntity(httpEntity);
			httpResponse = closeableHttpClient.execute(post);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
				return response;
			} else {
				log.error("Error " + httpResponse.getStatusLine().getStatusCode() + "!!!");
				return null;
			}
		} catch (IllegalArgumentException e) {
			log.error("非法参数:" + e.getMessage());
			return null;
		} catch (IllegalAccessException e) {
			log.error("非法权限:" + e.getMessage());
			return null;
		} catch (UnsupportedEncodingException e) {
			log.error("不支持的编码格式:" + e.getMessage());
			return null;
		} catch (ClientProtocolException e) {
			log.error("协议错误:" + e.getMessage());
			return null;
		} catch (IOException e) {
			log.error("IO错误:" + e.getMessage());
			return null;
		} finally {
			// 释放连接
			try {
				post.releaseConnection();
				httpResponse.close();
			} catch (IOException e) {
				log.error("IO错误:" + e.getMessage());
			}
		}
	}

	private static List<Field> getFields(Object object) {
		List<Field> declaredFields = new ArrayList<Field>();
		try {
			Class<?> clazz = object.getClass();
			Class<?> superClass = clazz.getSuperclass();

			Field[] fields1 = clazz.getDeclaredFields();
			for (Field field : fields1) {
				if (!field.isAccessible())
					field.setAccessible(true);
				if (field.get(object) != null)
					declaredFields.add(field);
			}

			Field[] fields2 = superClass.getDeclaredFields();
			for (Field field : fields2) {
				if (!field.isAccessible())
					field.setAccessible(true);
				if (field.get(object) != null)
					declaredFields.add(field);
			}

			if (declaredFields != null && declaredFields.size() > 0)
				return declaredFields;
			return null;
		} catch (IllegalArgumentException e) {
			log.error("非法参数:" + e.getMessage());
			return null;
		} catch (IllegalAccessException e) {
			log.error("非法权限:" + e.getMessage());
			return null;
		}
	}
}
