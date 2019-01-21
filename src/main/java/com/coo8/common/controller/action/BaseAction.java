/*
 * �ļ����� BaseAction.java
 * 
 * �������ڣ� 2010-3-16
 *
 * Copyright(C) 2010, by xiaozhi.
 *
 * ԭʼ����: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.coo8.common.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ����ACTION�Ļ���
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
	 * �õ�HttpRequest
	 * 
	 * @return ��õ�HttpRequest
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * �õ�HttpSession
	 * 
	 * @return ��õ�HttpSession
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * �õ�HttpResponse
	 * 
	 * @return ��õ�HttpResponse
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * �õ�������·�������磺/mylearningii
	 * 
	 * @return ������·��
	 */
	public String getContext() {
		return this.getRequest().getContextPath();
	}

	/**
	 * �õ�����ȫ·�������磺http://127.0.0.1:8080/mylearningii
	 * 
	 * @return ����ȫ·��
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
	 * �õ�Ӧ�õľ��Ե�ַ�����磺D:\Eclipse\workspace\CourseCreater\web\
	 * 
	 * @return Ӧ�õľ��Ե�ַ
	 */
	public String getRealPath() {
		return ServletActionContext.getServletContext().getRealPath("/");
	}

	/**
	 * ����û�����ʵIP
	 * 
	 * @return �û�����ʵIP
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
	 * ��������referer
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
	 * ��¼����վ��Ϊ���վ��
	 */
	public void setCoo8TopicSite(){
		getSession().setAttribute(LOGIN_GOME_COO8_SITE_FLAG, SITE_FLAG_COO8);
	}
	/**
	 * ��¼����վ��Ϊ����վ��
	 */
	public void setGomeTopicSite(){
		getSession().setAttribute(LOGIN_GOME_COO8_SITE_FLAG, SITE_FLAG_GOME);
	}
	/**
	 * ��õ�ǰר������վ��
	 * @return
	 */
	public String getTopicSite(){
		return SITE_FLAG_GOME;
	}
}
