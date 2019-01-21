/*
 * �ļ����� ReloadableConfig.java
 * 
 * �������ڣ� 2010-5-22
 *
 * Cretead by <a href="mailto:songpp22@gmail.com">songpp</a>.
 *
 */
package com.coo8.btoc.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class ReloadableConfig {

	
	private  static Logger log = LoggerFactory.getLogger(ReloadableConfig.class);
	
	private ReloadableConfig(){}
	
	private static ClassPathResource resource ;
	
	private static String file = "/config.properties";
	
	private static PropertiesConfiguration prop ;
	
	private static Long fileLastModify = null;
	
	private static boolean init = false;
	
	@SuppressWarnings("unused")
	private static Long checkTimePoint = null;//����ʱ��㣬�������Ʋ�Ҫ���̫Ƶ��������û�����Զ����أ�û�õ��������
	
	static{
		try{
			init();
		}catch(Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public synchronized static void init() throws IOException, ConfigurationException{
		load();
		init = true;
	}
	
	public synchronized static boolean isInited(){
		return init;
	}
	
	private static  boolean fileChanged(long lastmod) {
		if (fileLastModify == null){
			fileLastModify = lastmod;
			return true;
		}

		return fileLastModify < lastmod;
	}
	public static void load() throws IOException, ConfigurationException{
		
		resource = new ClassPathResource(file);
		if(! fileChanged(resource.getFile().lastModified()))
		{
			log.info(file + " not changed loat modify at " + new Date(fileLastModify));
			return;
		}
		
		PropertiesConfiguration temp = new PropertiesConfiguration(resource.getPath());
		prop = temp;
		temp = null;
		fileLastModify = resource.getFile().lastModified();
		log.info("loaded : " + prop);
	}

	/**
	 * ���¼��������ļ�����������ļ�������޸�ʱ�䣬���޸Ķ����򲻼���
	 * 
	 * @return boolean
	 */
	public static void reload() {
		checkTimePoint = System.currentTimeMillis();
		ClassPathResource s = new ClassPathResource(file);
		try {
			if (fileLastModify != s.getFile().lastModified()) {
				load();
				log.info("���¼���[" + file + "]");
			}
		} catch (Exception e) {
			log.error("����[" + file + "]ʧ��.", e);
		}
	}
	public static String getProperty(String key,String defaultValue)
	{
		return prop.getString(key, defaultValue);
	}
	
	public static Integer getInt(String key,int defaultVal)
	{
		return prop.getInt(key, defaultVal);
	}
	
	public static Double getDouble(String key,Double defaultVal){
		return prop.getDouble(key, defaultVal);
	}

	@SuppressWarnings("unchecked")
	public static List<Object> getList(String key)
	{
		return prop.getList(key);
	}
	
	public static Object getProperty(String name){
		return prop.getProperty(name);
	}

	
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	

	public static String getUploadDir(){
		return prop.getString("other_upload_dir");
	}
	
	/**
	 * Main Test
	 * @param ar
	 */
	public static void main(String [] ar){
		try {
			ReloadableConfig.init();
		}
		catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
		}
	
	}

}
