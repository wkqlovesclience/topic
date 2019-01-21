/*
 * 文件名： CommonAction.java
 * 
 * 创建日期： 2010-4-16
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.btoc.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.Global;
import com.coo8.btoc.config.ReloadableConfig;
import com.coo8.btoc.util.StringUtil;
import com.coo8.im.system.user.model.User;
import com.coo8.topic.controller.action.BaseAction;
import com.gome.common.web.context.LoginContext;
import com.mysql.jdbc.StringUtils;

/**
 * 所有ACTION的基类，提供操作Session对象的方法。
 * 
 * @version $Revision$
 * 
 */
public abstract class CommonAction extends BaseAction {
	
	public static final String JSON = "json";
	public static final String HTML = "html";
	private  static Logger logger = LoggerFactory.getLogger(CommonAction.class);
	private static final long serialVersionUID = -5289611895238514280L;

	private static String APP_DOMAIN;
	private static String CART_DOMAIN;

	static {
		APP_DOMAIN = ReloadableConfig.getProperty("app_domain", "www.coo8.com");
	}
	static {
		CART_DOMAIN = ReloadableConfig.getProperty("cart_domain", "buy.coo8.com");
	}
	
	public String getAppDomain(){
		return APP_DOMAIN;
	}
	public String getCartDomain(){
		return CART_DOMAIN;
	}
	
	protected String dataType;
	
	protected boolean isSpecifyDataType() {
		return !StringUtil.isNullorBlank(dataType);
	}
	
	/**
	 * 得到UserSessionObj
	 * 
	 * @return 获得的UserSessionObj
	 */
	public String getUserSessionInfo() {
		return (String) ServletActionContext.getRequest().getSession()
				.getAttribute("username");
	}

	/**
	 * 保存UserSessionObj到用户的Session中，并同步OnlineUserMap。
	 * 
	 * @param sessionObj
	 *            要保存的UserSessionObj
	 */
	public void setUserSessionInfo(String username) {
		ServletActionContext.getRequest().getSession().setAttribute("username",
				username);
	}

	/**
	 * 清除用户的Session中的UserSessionObj。
	 * 
	 */
	public void clearUserSessionInfo() {
		ServletActionContext.getRequest().getSession().setAttribute("username",
				null);
	}

	/**
	 * 后台得到UserSessionObj
	 * 
	 * @return 获得的UserSessionObj
	 */
	public User getAdminSessionInfo() {
		return (User) ServletActionContext.getRequest().getSession()
				.getAttribute("admin");
	}

	/**
	 * 后台保存UserSessionObj到用户的Session中，并同步OnlineUserMap。
	 * 
	 * @param sessionObj
	 *            要保存的UserSessionObj
	 */
	public void setAdminSessionInfo(User userInfo) {
		ServletActionContext.getRequest().getSession().setAttribute(
				"admin", userInfo);
	}

	/**
	 * 后台清除用户的Session中的UserSessionObj。
	 * 
	 */
	public void clearAdminSessionInfo() {
		ServletActionContext.getRequest().getSession().setAttribute(
				"admin", null);
		getSession().invalidate();
	}


	/**
	 * 加入值到cookies里
	 * 
	 * @param key
	 *            键
	 * 
	 * @param value
	 *            值
	 */
	public void addCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setSecure(true);
		cookie.setPath("/");// 这个要设置
		// 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
		cookie.setMaxAge(365 * 24 * 60 * 60);
		this.getResponse().addCookie(cookie);
	}

	public void addCookie(String key, String value, int time) {
		Cookie cookie = new Cookie(key, value);
		cookie.setSecure(true);
		cookie.setPath("/");// 这个要设置
		// 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
		cookie.setMaxAge(time);
		this.getResponse().addCookie(cookie);
	}

	public void addCookie(String key, String value, String domain) {
		Cookie cookie = new Cookie(key, value);
		cookie.setSecure(true);
		cookie.setPath("/");// 这个要设置
		// 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
		cookie.setMaxAge(365 * 24 * 60 * 60);
		cookie.setDomain(domain);
		this.getResponse().addCookie(cookie);
	}

	/**
	 * 根据键获取对应的cookie对象
	 * 
	 * @param key
	 *            对应的key
	 * 
	 * @return key对应的cookie值
	 */
	public Cookie getCookie(String key) {
		Cookie[] cookies = this.getRequest().getCookies();
		if (cookies == null || cookies.length < 1)
			return null;
		for (Cookie temp : cookies) {
			if (temp.getName().equals(key)) {
				return temp;
			}
		}
		return null;
	}
	

	/**
	 * 获得cookie对应的值
	 * 
	 * @param key
	 *            对应的键
	 * 
	 * @return 返回值
	 */
	public String getCookieValue(String key) {
		Cookie cookie = this.getCookie(key);
		if (cookie != null) {
			try {
				return URLDecoder.decode(cookie.getValue(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}

	public void clearCookie(String key) {
		Cookie cookie = new Cookie(key, null);
		cookie.setSecure(true);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		this.getResponse().addCookie(cookie);
	}
	
	
	/**
	 * 获取管理员后台操作员姓名
	 * 
	 * @return
	 */
	protected String getAdminSessionOperator() {		
		LoginContext context = LoginContext.getLoginContext();
		String userName  = "";
		if(null != context){
			userName = context.getPin();
		}
		return userName;
	}
	
	/**
	 * 将公司ID保存到session中
	 * @param comp_id
	 */
	protected void setAdminSessionCOMPID(String comp_id) {
		 getSession().setAttribute(Global.ADMIN_SESSION_COMP_ID, comp_id);
	}
	
	/**
	 * 获取session中公司ID
	 * @return
	 */
	protected String getAdminSessionCOMPID() {
		Object obj = getSession().getAttribute(Global.ADMIN_SESSION_COMP_ID);
		return obj == null ? "" :obj.toString();
	}
	
	/**
	 * 将城市ID集合字符串保存到session中
	 * @param city_ids
	 */
	protected void setAdminSessionCITYIDS(String city_ids) {
		 getSession().setAttribute(Global.ADMIN_SESSION_CITY_IDS, city_ids);
	}
	
	/**
	 * 获取session中城市ID集合字符串
	 * @return
	 */
	protected String getAdminSessionCITYIDS() {
		Object obj = getSession().getAttribute(Global.ADMIN_SESSION_CITY_IDS);
		return obj == null ? "" :obj.toString();
	}
	

	protected void setLastQueryString() {
		String requesturl = this.getRequest().getQueryString();
		this.getSession().setAttribute("lasturl", requesturl);
	}

	protected String getLastQueryString() {
		Object lastRequesturl = this.getSession().getAttribute("lasturl");
		if (lastRequesturl != null)
			return (String) lastRequesturl;
		return "";
	}

	protected void removeLastQueryString() {
		this.getSession().removeAttribute("lasturl");
	}
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Override
	protected void writeAjaxStr(String str){
		HttpServletResponse response = this.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		try {
			PrintWriter out = response.getWriter();
			out.write(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public boolean isControlCenter() {
		if (StringUtils.isNullOrEmpty(getAdminSessionCOMPID())) return false;
		
		return getAdminSessionCOMPID().equals(ReloadableConfig.getProperty("header_company_id", ""));
	}
}
