/**  
 * @Project: gome-gcc-common
 * @Title: DiamondClient.java
 * @Package: com.gome.bg.diamond
 * @Description:
 * @author: QIJJ 
 * @Copyright: 2015. All rights reserved.
 * @Version: v1.0   
 */
package com.coo8.common.diamond;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListenerAdapter;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;

/**
 * @ClassName PropertiesUtils
 */
public class PropertiesUtils {

	public static final DiamondManager diamondManager = null;
	static Properties properties;
	public static final String fileNameNew = ".diamond.domain";
	private  static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	private static final long TIME_OUT = 5000L;
	
	private static String diamondIpList;
	private static List<String> diamondIdgroupList;
	
	private static Set<String> notFoundKey = new HashSet<String>();
	
	private PropertiesUtils(){}

	public static Properties getProperties(List<String> diamondList) {
		diamondIdgroupList = diamondList;
		if (null == properties) {
			init();
		}
		return properties;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static Object getValueByKey(String key) {
		if (properties == null) {
			init();
		}
		Object value = properties.get(key);
		if (value == null) {
			notFoundKey.add(key);
			logger.error("****************从diamond " + key + " null***************");
		}
		return value;
	}
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapValueByKey(String key) {
		Map<String, Object> map = (Map<String, Object>) JSONObject.parse(getStringValueByKey(key));
		return map;
	}

	public static JSONObject getJsonObjectValueByKey(String key) {
		return JSONObject.parseObject(getStringValueByKey(key));
	}

	public static String getStringValueByKey(String key) {
		return (String) getValueByKey(key);
	}

	public static int getIntValueByKey(String key) {
		return Integer.parseInt((String) getValueByKey(key));
	}

	public static double getDoubleValueByKey(String key) {
		return Double.parseDouble((String) getValueByKey(key));
	}

	public static Long getLongValueByKey(String key) {
		return Long.parseLong((String) getValueByKey(key));
	}

	public static boolean getBooleanValueByKey(String key) {
		return Boolean.parseBoolean((String) (getValueByKey(key)));
	}

	public static String getStringValueByKey(String key, String defaultV) {
		Object value = getValueByKey(key);
		if (value == null) {
			return defaultV;
		}
		return (String) value;
	}

	public static int getIntValueByKey(String key, int defaultV) {
		Object value = getValueByKey(key);
		if (value == null) {
			return defaultV;
		}
		return Integer.parseInt((String) value);
	}

	public static double getDoubleValueByKey(String key, double defaultV) {
		Object value = getValueByKey(key);
		if (value == null) {
			return defaultV;
		}
		return Double.parseDouble((String) value);
	}

	public static Long getLongValueByKey(String key, Long defaultV) {
		Object value = getValueByKey(key);
		if (value == null) {
			return defaultV;
		}
		return Long.parseLong((String) value);
	}

	public static boolean getBooleanValueByKey(String key, boolean defaultV) {
		Object value = getValueByKey(key);
		if (value == null) {
			return defaultV;
		}
		return Boolean.parseBoolean((String) (value));
	}	
	/**
	 * init(dataId groupId )
	 * 
	 * @param diamondList
	 * @return void
	 * @author luantian
	 * @exception
	 * @since 1.0.0
	 */
	private static void init() {
		ManagerListenerAdapter diamondListener = new ManagerListenerAdapter() {
			@Override
			public void receiveConfigInfo(String configInfo) {
				putAndUpdateProperties(configInfo);
			}
		};
		FileConfigUtil fileConfigUtil = new FileConfigUtil(System.getProperty("user.home") + System.getProperty("file.separator") + fileNameNew);
		diamondIpList = fileConfigUtil.getValue();
		logger.info("diaond-->filePath:" + System.getProperty("user.home") + " change diamondIpList:" + diamondIpList);
		if (diamondIdgroupList != null && diamondIpList != null) {
			for (String str : diamondIdgroupList) {
				// dataid
				String dataId = "";
				String groupId = "";
				if (str.indexOf(":") > -1) {
					dataId = str.substring(0, str.indexOf(":"));
				}
				if (str.lastIndexOf(":") > -1) {
					groupId = str.substring(str.indexOf(":") + 1, str.lastIndexOf(":"));
				}
				if (!StringUtils.isEmpty(dataId) && !StringUtils.isEmpty(groupId)) {
					DefaultDiamondManager manager = new DefaultDiamondManager(dataId, groupId, diamondListener, diamondIpList);
					String configInfo = manager.getAvailableConfigureInfomation(TIME_OUT);
					logger.debug("diamond====" + configInfo);
					putAndUpdateProperties(configInfo);
				} else {
					logger.error("diamond properties error DataId:" + dataId + ",Group:" + groupId);
				}
			}
		} else {
			logger.error("diamond properties error:diamondBeanList is null or diamondIpList is null");
		}
	}

	/**
	 * 
	 * @param configInfo
	 */
	public static void putAndUpdateProperties(String configInfo) {
		if (!StringUtils.isEmpty(configInfo)) {
			if (properties == null) {
				properties = new Properties();
			}
			try {
				properties.load(new ByteArrayInputStream(configInfo.getBytes()));
			} catch (IOException e) {
				logger.error("diamond trans properties error" + e.getMessage(), e);
			}
		} else {
			logger.error("diamond null");
		}
	}

	public static String getDiamondIpList() {
		return diamondIpList;
	}

	public static void setDiamondIpList(String diamondIpList) {
		PropertiesUtils.diamondIpList = diamondIpList;
	}

	public static List<String> getDiamondIdgroupList() {
		return diamondIdgroupList;
	}

	public static void setDiamondIdgroupList(List<String> diamondIdgroupList) {
		PropertiesUtils.diamondIdgroupList = diamondIdgroupList;
	}
	
	
}
