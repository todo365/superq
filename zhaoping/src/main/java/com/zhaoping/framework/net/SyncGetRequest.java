/**
 * 
 */
package com.zhaoping.framework.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zhaoping.framework.serialization.JsonUtil;

/**
 * @author hongxiao.shou
 *
 */
public final class SyncGetRequest {
	private static Logger logger = Logger.getLogger(SyncGetRequest.class);

	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param queryString
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @param pretty
	 *            是否美化
	 * @return 返回请求响应的HTML
	 */
	public static <E> E doGet(Class<E> clazz, String url, String queryString,
			String charset, boolean pretty) {
		// 创建HttpClient实例
		HttpClient httpclient = new DefaultHttpClient();
		// 创建Get方法实例
		E object = null;
		HttpGet httpgets = null;
		try {
			httpgets = new HttpGet(url);
			// "http://127.0.0.1/testhttp.php?username=yonghuming");
			HttpResponse response = httpclient.execute(httpgets);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instreams = entity.getContent();
					object = JsonUtil.toObject(
							convertStreamToString(instreams), clazz);
				}
				// System.out.println("Do something");
				// System.out.println(str);
				// Do not need the rest
				httpgets.abort();

			}
		} catch (Exception oe) {
			logger.error(oe.getMessage());
		}
		return object;
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
