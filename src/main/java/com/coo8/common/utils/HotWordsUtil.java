package com.coo8.common.utils;

import com.coo8.common.diamond.PropertiesUtils;

public class HotWordsUtil {
	
	private HotWordsUtil(){}

	// œ‡πÿ»»¥ dubbo ip≤‚ ‘
	public static final String REATED_HOTWORDS_DUBBO_IP = PropertiesUtils.getStringValueByKey("hotwords_dubbo_ip");
	
	public static final String REATED_RESOURCE_DUBBO_IP = PropertiesUtils.getStringValueByKey("resource_dubbo_ip");
	
	public static final String REATED_HOTWORDS_DUBBO_NODE = PropertiesUtils.getStringValueByKey("hotwords_dubbo_node");

	public static final String HOT_WORDS_BASE_URL = PropertiesUtils.getStringValueByKey("hot_words_base_url");

}
