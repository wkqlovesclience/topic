package com.coo8.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpClientUtil {
	
	private  static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static final int CONNECTION_TIMEOUT = 5 * 1000;
	private static final int REQUEST_TIMEOUT = 5 * 1000;

	/**
	 * 
	 * ��ȡ httpҳ�沢���� httpҳ������
	 * 
	 * @param url
	 * @return
	 */
	public static String readHttpPage(String url) {
		String retStr = "";

		HttpClient httpClient = new HttpClient();
		// �������ӳ�ʱʱ�� 30��
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30 * 1000);
		// ���ö�ȡ��ʱʱ�� 60��
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(60 * 1000);
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("Connection", "close");
		getMethod.getParams().setParameter(// ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
				HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			logger.info("############ readHttpPage requestUrl:" + url + ";statusCode:" + statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet()));
				StringBuilder sb = new StringBuilder();
				String resTemp = "";
				while ((resTemp = in.readLine()) != null) {
					sb.append(resTemp);
				}
				retStr = sb.toString();
					in.close();
			}
		} catch (HttpException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			getMethod.releaseConnection();
		}
		return retStr;
	}

	/**
	 * ���� JSON����
	 * 
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String, Object>> parseJson(String jsonString) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		if (null == jsonString || "".equals(jsonString.trim())) {
			return result;
		}
		try {

			JSONArray productArr = JSONArray.fromObject(jsonString);

			for (int i = 0; i < productArr.size(); i++) {
				JSONObject jsonObj = productArr.getJSONObject(i);

				Map<String, Object> nodemap = new HashMap<String, Object>();

				for (Iterator<?> iter = jsonObj.keys(); iter.hasNext();) {

					String key = (String) iter.next();

					nodemap.put(key, jsonObj.get(key));
				}

				result.add(nodemap);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	/**
	 * ���� ATG�ӿ�URL����json Map�б�
	 * 
	 * @param atgUrl
	 * @return
	 */
	public static List<Map<String, Object>> getJsonMapListByATGUrl(String atgUrl) {
		String jsonString = readHttpPage(atgUrl);

		return parseJson(jsonString);
	}

	public static String getJsonValue(String urlStr) throws Exception {

		HttpClient httpClient = new HttpClient();
	
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
		
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(REQUEST_TIMEOUT);
		GetMethod get = new GetMethod(urlStr);

		get.setRequestHeader("Connection", "close");
		get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		String resultJson = "{}";
		try {
			// ִ��get
			int statusCode = httpClient.executeMethod(get);
			if (statusCode == HttpStatus.SC_OK) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(get.getResponseBodyAsStream(), get.getResponseCharSet()));
				StringBuilder sb = new StringBuilder();
				String resTemp = "";
				while ((resTemp = in.readLine()) != null) {
					sb.append(resTemp);
				}
				resultJson = sb.toString();
				logger.info("HttpClientUtil_getJsonValue: requestUrl=" + urlStr + ", statusCode=" + statusCode
						+ ", result_length=" + resultJson.length());
			} else {
				int retryStatusCode = 0;
				for (int i = 3; i > 0; i--) {
					retryStatusCode = httpClient.executeMethod(get);
					if (retryStatusCode != HttpStatus.SC_OK) {
						continue;
					}
					BufferedReader in = new BufferedReader(
							new InputStreamReader(get.getResponseBodyAsStream(), get.getResponseCharSet()));
					StringBuilder sb = new StringBuilder();
					String resTemp = "";
					while ((resTemp = in.readLine()) != null) {
						sb.append(resTemp);
					}
					resultJson = sb.toString();
					break;
				}
				logger.info("HttpClientUtil_getJsonValue: URL=" + urlStr + ", statusCode=" + retryStatusCode
						+ ", result_length=" + resultJson.length());
			}
		} catch (HttpException e) {
			String errStr = "��ӿ�ͨѶ�����쳣��ͨѶʧ�ܣ����Ժ����ԡ�URL:" + urlStr;
			throw new HttpException(errStr, e);
		} catch (IOException e) {
			String errStr = "��ӿ�ͨѶ�����쳣��ͨѶʧ�ܣ����Ժ����ԡ�URL:" + urlStr;
			throw new IOException(errStr, e);
		} catch (IllegalArgumentException e) {
			String errStr = "�ӿڷ����ĵ���ĳ��ֵ�����ϸ�ʽ�����磺CUSTIDӦΪ����ʱ��Ϊ�ա�URL:" + urlStr;
			throw new IllegalArgumentException(errStr, e);
		} finally {
			get.releaseConnection();
		}

		return resultJson;
	}

	/**
	 * ��׽JSONObject.fromObject�쳣
	 * 
	 * @param args
	 */
	public static JSONObject getMyJsonObject(String httpURL, String strJson) {
		JSONObject resultJson = null;
		try {
			resultJson = JSONObject.fromObject(strJson);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		return resultJson;
	}
	
	public static boolean testUrl(String webUrl) {  
	     if(webUrl.isEmpty()){
	    	 return false;
	     }   
	     if(webUrl.contains("https:")){
	    	 webUrl = webUrl.replace("https:", "http:");
	     }
		 try {  
	            // ���ô����Ƿ�Ӧ���Զ�ִ�� HTTP�ض�����Ӧ����Ϊ 3xx �����󣩡�  
	            HttpURLConnection.setFollowRedirects(true);  
	            // ��URL�����õ�Զ�̶��������  
	            HttpURLConnection conn = (HttpURLConnection) new URL(webUrl).openConnection();  
	            // ����URL����ķ�����GET POST HEAD OPTIONS PUT DELETE TRACE  
	            // ���Ϸ���֮һ�ǺϷ��ģ�����ȡ����Э������ơ�  
	            conn.setRequestMethod("GET");  
	            // ��HTTP��Ӧ��Ϣ��ȡ״̬��  
	            logger.info("��֤���ӣ�"+webUrl+"����code��"+conn.getResponseCode());
	            return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);  
	        } catch (Exception e) {  
	        	logger.info("��֤�������ӣ�"+webUrl+"������Ϣ��"+e.getMessage());
	            return false;  
	        }  
	    }  
}
