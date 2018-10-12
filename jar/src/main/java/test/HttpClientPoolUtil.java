package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class HttpClientPoolUtil {
	private static final String charset = "UTF-8";

	private final static int SOCKET_TIMEOUT = 20000;
	private final static int CONNECTION_TIMEOUT = 20000;
	private final static int MAX_CONN_PRE_HOST = 20;
	private final static int MAX_CONN = 50;
	private final static HttpConnectionManager httpConnectionManager;

	/**
	 * 初始化
	 */
	static {
		httpConnectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = httpConnectionManager.getParams();
		params.setConnectionTimeout(CONNECTION_TIMEOUT);
		params.setSoTimeout(SOCKET_TIMEOUT);
		params.setDefaultMaxConnectionsPerHost(MAX_CONN_PRE_HOST);
		params.setMaxTotalConnections(MAX_CONN);
	}

	/**
	 * get请求方式
	 * @param url
	 * @param params
	 * @param charSets
	 * @return
	 */
	public static String doGet(String url, Map<String, String> params, String charSets) {
		String charsetStr = charSets;
		if (null == charSets) {
			charsetStr = charset;
		}

		HttpClient httpClient = null;
		GetMethod getMethod = null;
		String result = null;
		StringBuffer sbLine = null;
		BufferedReader reader = null;
		try {
			if (null == url || "".equals(url)) {
				return null;
			}
			httpClient = new HttpClient(httpConnectionManager);
			if (null != params && params.size() > 0) {
				StringBuffer sb = new StringBuffer();
				Iterator<String> iter = params.keySet().iterator();
				int i = 0;
				while (iter.hasNext()) {
					String key = iter.next();
					if (i == 0) {
						sb.append("?");
					} else {
						sb.append("&");
					}
					sb.append(key).append("=").append(URLEncoder.encode(params.get(key), "utf-8"));
					i++;
				}
				url += sb.toString();
			}

			getMethod = new GetMethod(url);
			getMethod.setRequestHeader("charset", charsetStr);

			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {

				reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(), charsetStr));// ���ص���Ϣ
				sbLine = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					sbLine.append(str);
				}
				result = sbLine.toString();
			} else {
				System.out.println(statusCode);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (null != getMethod) {
				getMethod.releaseConnection();
			}
		}
		return result;
	}
	
	/**
	 * post 请求方式
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, Map<String, String> params) {
		HttpClient httpClient = null;
		PostMethod postMethod = null;
		String result = null;
		StringBuffer sbLine = null;
		BufferedReader reader = null;
		try {
			if (null == url || "".equals(url)) {
				return null;
			}
			httpClient = new HttpClient(httpConnectionManager);
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
			if (null != params && params.size() > 0) {
				Iterator<String> iter = params.keySet().iterator();
				NameValuePair[] arr = new NameValuePair[params.size()];
				int i = 0;
				while (iter.hasNext()) {
					String key = iter.next();
					arr[i] = new NameValuePair(key, params.get(key));
					i++;
				}
				postMethod.setRequestBody(arr);
			}

			int statusCode = httpClient.executeMethod(postMethod);//服务器返回的状态
			if (statusCode == HttpStatus.SC_OK) {
				reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream()));//返回的信息
				sbLine = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					sbLine.append(str);
				}
				result = sbLine.toString();
			} else {
				System.out.println("doPost通讯失败,通信状态 " + statusCode+",url="+url);
			}
		} catch (Exception e) {
			System.out.println("HttpClientUtil异常原因为：" + e.getMessage());
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (null != postMethod) {
				postMethod.releaseConnection();
			}
		}
		return result;
	}

}
