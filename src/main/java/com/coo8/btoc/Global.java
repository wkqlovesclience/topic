/*
 * 文件名： Global.java
 * 
 * 创建日期： 2010-4-19
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.btoc;



import org.springframework.context.ApplicationContext;

/**
 * 整个系统的全局变量
 * 
 */
public class Global {

	public static final int SF_ID = 31;
	
	public static final int EMS_ID = 30;
	/**
	 * Spring的ApplicationContext，使用的时候要自己设置。
	 */
	public static  final ApplicationContext _ctx = null;
	public static final int pageSize = 8;

	public static final int dealerPageSize = 15;
	
	/**
	 * 前台保存在SESSION中的用户
	 */
	public static final String USER_SESSION_NAME = "userInfo";
	
	/**
	 * 管理员后台保存在SESSION中的用户
	 */
	public static final String ADMIN_SESSION_NAME = "adminInfo";
	
	/**
	 * 当前登录管理员所属公司ID
	 */
	public static final String ADMIN_SESSION_COMP_ID = "admin_session_comp_id";
	
	/**
	 * 当前登录管理员所属城市ID集合
	 */
	public static final String ADMIN_SESSION_CITY_IDS = "admin_session_city_ids";
	
	/**
	 * 商家后台保存在SESSION中的用户
	 */
	public static final String DEALER_SESSION_NAME = "dealerInfo";

	public static final String LOING_COOKIE_NAME = "user.btoc.coo8.com";

	public static final String ADMIN_COOKIE_NAME = "admin.btoc.coo8.com";
	
	public static final String MAIL_REGEX = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	
	private Global() {
	  }

}
