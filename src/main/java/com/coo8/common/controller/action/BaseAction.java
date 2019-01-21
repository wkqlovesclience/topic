/*
 * 文件名： BaseAction.java
 * 
 * 创建日期： 2010-3-16
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.common.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 所有ACTION的基类
 *
 * @version $Revision$
 *
 */
public abstract class BaseAction extends ActionSupport{

	private static final long serialVersionUID = -7957586334286020630L;
	
	private static final String SITE_FLAG_COO8 = "coo8";
	private static final String SITE_FLAG_GOME = "gome";
	private static final String LOGIN_GOME_COO8_SITE_FLAG = "login_gome_coo8_site_flag";

	/**
	 * 得到HttpRequest
	 * 
	 * @return 获得的HttpRequest
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 得到HttpSession
	 * 
	 * @return 获得的HttpSession
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 得到HttpResponse
	 * 
	 * @return 获得的HttpResponse
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 得到上下文路径，例如：/mylearningii
	 * 
	 * @return 上下文路径
	 */
	public String getContext() {
		return this.getRequest().getContextPath();
	}

	/**
	 * 得到服务全路径，例如：http://127.0.0.1:8080/mylearningii
	 * 
	 * @return 服务全路径
	 */
	public String getBasePath() {
		String path = this.getRequest().getContextPath();
		String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName();

		if ( this.getRequest().getServerPort() != 80 ) {
			basePath += ":" + this.getRequest().getServerPort() + path;
		} else {
			basePath += path;
		}

		return basePath;
	}
	
	/**
	 * 得到应用的绝对地址，例如：D:\Eclipse\workspace\CourseCreater\web\
	 * 
	 * @return 应用的绝对地址
	 */
	public String getRealPath() {
		return ServletActionContext.getServletContext().getRealPath("/");
	}

	/**
	 * 获得用户的真实IP
	 * 
	 * @return 用户的真实IP
	 */
	public String getRealIP() {
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getRemoteAddr();
		}
		
		return ip;
	}
	
	/**
	 * 获得请求的referer
	 * 
	 * @return referer
	 */
	public String getReferer() {
		String referer = getRequest().getHeader("referer");
		if (referer != null && !referer.equalsIgnoreCase("")) {
			referer = referer.substring(0, referer.lastIndexOf("/")+1);
		} else {
			referer = "";
		}
		
		return referer;
	}
	
	/**
	 * 登录设置站点为库巴站点
	 */
	public void setCoo8TopicSite(){
		getSession().setAttribute(LOGIN_GOME_COO8_SITE_FLAG, SITE_FLAG_COO8);
	}
	/**
	 * 登录设置站点为国美站点
	 */
	public void setGomeTopicSite(){
		getSession().setAttribute(LOGIN_GOME_COO8_SITE_FLAG, SITE_FLAG_GOME);
	}
	/**
	 * 获得当前专题所在站点
	 * @return
	 */
	public String getTopicSite(){
		return SITE_FLAG_GOME;
	}
}
