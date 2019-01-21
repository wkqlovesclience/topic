package com.gome.utils;

import com.coo8.common.diamond.PropertiesUtils;
import com.gome.architect.idgnrt.IDGenerator;

/**
 * id自主生成器
 * @author yangjunjie-ds
 *
 */

public class IDGeneratorUtils {	
	private static String idGeneratorAddr=PropertiesUtils.getStringValueByKey("idGenerator.zookeeper.address",
			"10.112.179.149:2181,10.112.179.150:2181,10.112.179.151:2181");
	private static String tableKey ="kubatopic.expenditure.id";
	private IDGeneratorUtils(){};
	private volatile static IDGenerator instance = null;
	
	public static IDGenerator getInstance(){	
		if(instance == null) {
			synchronized (IDGenerator.class) { 
				if (instance == null) {
					instance = new IDGenerator(idGeneratorAddr,tableKey);
					
					instance.init();//初始化
				}
			}
		}
		return instance;
	}
	
	public static void destroy(){
		instance.destroy();
	}
}
