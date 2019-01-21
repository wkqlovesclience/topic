/*
 * �ļ����� Global.java
 * 
 * �������ڣ� 2010-4-19
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * ԭʼ����: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.btoc;



import org.springframework.context.ApplicationContext;

/**
 * ����ϵͳ��ȫ�ֱ���
 * 
 */
public class Global {

	public static final int SF_ID = 31;
	
	public static final int EMS_ID = 30;
	/**
	 * Spring��ApplicationContext��ʹ�õ�ʱ��Ҫ�Լ����á�
	 */
	public static  final ApplicationContext _ctx = null;
	public static final int pageSize = 8;

	public static final int dealerPageSize = 15;
	
	/**
	 * ǰ̨������SESSION�е��û�
	 */
	public static final String USER_SESSION_NAME = "userInfo";
	
	/**
	 * ����Ա��̨������SESSION�е��û�
	 */
	public static final String ADMIN_SESSION_NAME = "adminInfo";
	
	/**
	 * ��ǰ��¼����Ա������˾ID
	 */
	public static final String ADMIN_SESSION_COMP_ID = "admin_session_comp_id";
	
	/**
	 * ��ǰ��¼����Ա��������ID����
	 */
	public static final String ADMIN_SESSION_CITY_IDS = "admin_session_city_ids";
	
	/**
	 * �̼Һ�̨������SESSION�е��û�
	 */
	public static final String DEALER_SESSION_NAME = "dealerInfo";

	public static final String LOING_COOKIE_NAME = "user.btoc.coo8.com";

	public static final String ADMIN_COOKIE_NAME = "admin.btoc.coo8.com";
	
	public static final String MAIL_REGEX = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	
	private Global() {
	  }

}
