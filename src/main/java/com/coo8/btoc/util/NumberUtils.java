package com.coo8.btoc.util;

import java.text.DecimalFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberUtils {
	private  static Logger logger = LoggerFactory.getLogger(NumberUtils.class);
	
	private NumberUtils(){}
	
	/**
	 * ��map������key������ת����int����
	 * @param map
	 * @param key
	 * @return ����д�Ļ���Ĭ�Ϸ���0
	 */
	public static int getInt(@SuppressWarnings("rawtypes") Map map, Object key) {
		try {
			return Integer.parseInt(map.get(key).toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}
	
	public static String format(double num) {
		DecimalFormat df = new DecimalFormat("#0.00");
		
		return df.format(num);
	}
	
	public static String format(float num) {
		DecimalFormat df = new DecimalFormat("#0.00");
		
		return df.format(num);
	}
}
