package com.coo8.common.utils;

import com.coo8.common.diamond.PropertiesUtils;

public class ConstantUtil {
	
	/**
	 * ���վ��
	 */
	public static final String SITE_FLAG_COO8;
	/**
	 * ����վ��
	 */
	public static final String SITE_FLAG_GOME;
	
	/**
	 *�ļ��������� ����������
	 */
	public static final int PUBLISH_FILE_TYPE_ONLINE  ; 
	/**
	 * �ļ��������� ����������·��
	 */
	public static final int PUBLISH_FILE_TYPE_FORTEST ; 
	
	/**
	 * �����վ��������	
	 */
	public static final String DOMAIN_COO8_BASEURL; 
	/**
	 * ���ר���������
	 */
	public static final String DOMAIN_COO8_TITLE_BASEURL;
	/**
	 * ������·�������	
	 */
	public static final String DOMAIN_COO8_NEWS_BASEURL;
	/**
	 * ������վ��������
	 */
	public static final String DOMAIN_GOME_BASEURL;	
	
	/**
	 * �ֻ��˹�����վ��������
	 */
	public static final String DOMAIN_WAP_GOME_BASEURL;	
	
	/**
	 * ����ר���������
	 */
	public static final String DOMAIN_GOME_TITLE_BASEURL;
	/**
	 * ������·�������
	 */
	public static final String DOMAIN_GOME_NEWS_BASEURL;
	/**
	 * ���Ե�ַ��ʾ Ĭ�� /test
	 */
	public static final String DOMAIN_TEST_BASEURL ;
	
	private ConstantUtil(){}
	
	static{
		
		SITE_FLAG_COO8 = "coo8";
		SITE_FLAG_GOME = "gome";
		
		PUBLISH_FILE_TYPE_ONLINE  = 0;
		PUBLISH_FILE_TYPE_FORTEST = 7;
		
		DOMAIN_COO8_BASEURL 	  = PropertiesUtils.getStringValueByKey("DOMAIN_COO8_BASEURL");
		DOMAIN_COO8_TITLE_BASEURL = PropertiesUtils.getStringValueByKey("DOMAIN_COO8_TITLE_BASEURL");
		DOMAIN_COO8_NEWS_BASEURL  = PropertiesUtils.getStringValueByKey("DOMAIN_COO8_NEWS_BASEURL");
		
		DOMAIN_GOME_BASEURL 	  =  PropertiesUtils.getStringValueByKey("DOMAIN_GOME_BASEURL","//www.gome.com.cn");
		DOMAIN_WAP_GOME_BASEURL   =  PropertiesUtils.getStringValueByKey("DOMAIN_WAP_GOME_BASEURL");
		DOMAIN_GOME_TITLE_BASEURL =  PropertiesUtils.getStringValueByKey("DOMAIN_GOME_TITLE_BASEURL","/topic");
		DOMAIN_GOME_NEWS_BASEURL  =  PropertiesUtils.getStringValueByKey("DOMAIN_GOME_NEWS_BASEURL");
		
		DOMAIN_TEST_BASEURL = PropertiesUtils.getStringValueByKey("DOMAIN_TEST_BASEURL") ;
	}
}
