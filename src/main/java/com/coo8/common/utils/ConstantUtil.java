package com.coo8.common.utils;

import com.coo8.common.diamond.PropertiesUtils;

public class ConstantUtil {
	
	/**
	 * 库巴站点
	 */
	public static final String SITE_FLAG_COO8;
	/**
	 * 国美站点
	 */
	public static final String SITE_FLAG_GOME;
	
	/**
	 *文件发布类型 发布到线上
	 */
	public static final int PUBLISH_FILE_TYPE_ONLINE  ; 
	/**
	 * 文件发布类型 发布到测试路径
	 */
	public static final int PUBLISH_FILE_TYPE_FORTEST ; 
	
	/**
	 * 库巴网站访问链接	
	 */
	public static final String DOMAIN_COO8_BASEURL; 
	/**
	 * 库巴专题访问链接
	 */
	public static final String DOMAIN_COO8_TITLE_BASEURL;
	/**
	 * 库巴文章访问链接	
	 */
	public static final String DOMAIN_COO8_NEWS_BASEURL;
	/**
	 * 国美网站访问链接
	 */
	public static final String DOMAIN_GOME_BASEURL;	
	
	/**
	 * 手机端国美网站访问链接
	 */
	public static final String DOMAIN_WAP_GOME_BASEURL;	
	
	/**
	 * 国美专题访问链接
	 */
	public static final String DOMAIN_GOME_TITLE_BASEURL;
	/**
	 * 库巴文章访问链接
	 */
	public static final String DOMAIN_GOME_NEWS_BASEURL;
	/**
	 * 测试地址标示 默认 /test
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
