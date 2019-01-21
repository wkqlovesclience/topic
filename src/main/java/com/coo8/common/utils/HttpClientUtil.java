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
	 * ï¿½ï¿½È¡ httpÒ³ï¿½æ²¢ï¿½ï¿½ï¿½ï¿½ httpÒ³ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	 * 
	 * @param url
	 * @return
	 */
	public static String readHttpPage(String url) {
		String retStr = "";

		HttpClient httpClient = new HttpClient();
		// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ó³ï¿½Ê±Ê±ï¿½ï¿½ 30ï¿½ï¿½
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30 * 1000);
		// ï¿½ï¿½ï¿½Ã¶ï¿½È¡ï¿½ï¿½Ê±Ê±ï¿½ï¿½ 60ï¿½ï¿½
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(60 * 1000);
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("Connection", "close");
		getMethod.getParams().setParameter(// Ê¹ï¿½ï¿½ÏµÍ³ï¿½á¹©ï¿½ï¿½Ä¬ï¿½ÏµÄ»Ö¸ï¿½ï¿½ï¿½ï¿½ï¿½
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
	 * ï¿½ï¿½ï¿½ï¿½ JSONï¿½ï¿½ï¿½ï¿½
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
	 * ï¿½ï¿½ï¿½ï¿½ ATGï¿½Ó¿ï¿½URLï¿½ï¿½ï¿½ï¿½json Mapï¿½Ð±ï¿½
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
			// Ö´ï¿½ï¿½get
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
			String errStr = "ï¿½ï¿½Ó¿ï¿½Í¨Ñ¶ï¿½ï¿½ï¿½ï¿½ï¿½ì³£ï¿½ï¿½Í¨Ñ¶Ê§ï¿½Ü£ï¿½ï¿½ï¿½ï¿½Ôºï¿½ï¿½ï¿½ï¿½Ô¡ï¿½URL:" + urlStr;
			throw new HttpException(errStr, e);
		} catch (IOException e) {
			String errStr = "ï¿½ï¿½Ó¿ï¿½Í¨Ñ¶ï¿½ï¿½ï¿½ï¿½ï¿½ì³£ï¿½ï¿½Í¨Ñ¶Ê§ï¿½Ü£ï¿½ï¿½ï¿½ï¿½Ôºï¿½ï¿½ï¿½ï¿½Ô¡ï¿½URL:" + urlStr;
			throw new IOException(errStr, e);
		} catch (IllegalArgumentException e) {
			String errStr = "ï¿½Ó¿Ú·ï¿½ï¿½ï¿½ï¿½Äµï¿½ï¿½ï¿½Ä³ï¿½ï¿½Öµï¿½ï¿½ï¿½ï¿½ï¿½Ï¸ï¿½Ê½ï¿½ï¿½ï¿½ï¿½ï¿½ç£ºCUSTIDÓ¦Îªï¿½ï¿½ï¿½ï¿½Ê±ï¿½ï¿½Îªï¿½Õ¡ï¿½URL:" + urlStr;
			throw new IllegalArgumentException(errStr, e);
		} finally {
			get.releaseConnection();
		}

		return resultJson;
	}

	/**
	 * ï¿½ï¿½×½JSONObject.fromObjectï¿½ì³£
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
	            // ÉèÖÃ´ËÀàÊÇ·ñÓ¦¸Ã×Ô¶¯Ö´ÐÐ HTTPÖØ¶¨Ïò£¨ÏìÓ¦´úÂëÎª 3xx µÄÇëÇó£©¡£  
	            HttpURLConnection.setFollowRedirects(true);  
	            // µ½URLËùÒýÓÃµÄÔ¶³Ì¶ÔÏóµÄÁ¬½Ó  
	            HttpURLConnection conn = (HttpURLConnection) new URL(webUrl).openConnection();  
	            // ÉèÖÃURLÇëÇóµÄ·½·¨£¬GET POST HEAD OPTIONS PUT DELETE TRACE  
	            // ÒÔÉÏ·½·¨Ö®Ò»ÊÇºÏ·¨µÄ£¬¾ßÌåÈ¡¾öÓÚÐ­ÒéµÄÏÞÖÆ¡£  
	            conn.setRequestMethod("GET");  
	            // ´ÓHTTPÏìÓ¦ÏûÏ¢»ñÈ¡×´Ì¬Âë  
	            logger.info("ÑéÖ¤Á´½Ó£º"+webUrl+"·µ»Øcode£º"+conn.getResponseCode());
	            return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);  
	        } catch (Exception e) {  
	        	logger.info("ÑéÖ¤³ö´íÁ´½Ó£º"+webUrl+"´íÎóÐÅÏ¢£º"+e.getMessage());
	            return false;  
	        }  
	    }  
}
