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
package com.coo8.topic.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gome.common.web.context.LoginContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ����ACTION�Ļ���
 * 
 * @version $Revision$
 * 
 */
@Result(name = "chooseTopic", location = "/ChooseTopic.jsp")
public abstract class BaseAction extends ActionSupport
{
	private  static Logger logger = LoggerFactory.getLogger(BaseAction.class);
	private static final long serialVersionUID = -7957586334286020630L;
	private static final String SITE_FLAG_COO8 = "coo8";
	private static final String SITE_FLAG_GOME = "gome";
	private static final String LOGIN_GOME_COO8_SITE_FLAG = "login_gome_coo8_site_flag";
	private static final String CHOOSE_TOPIC = "chooseTopic";

	/**
	 * �õ�HttpRequest
	 * 
	 * @return ��õ�HttpRequest
	 */
	public HttpServletRequest getRequest()
	{
		return ServletActionContext.getRequest();
	}

	/**
	 * �õ�HttpSession
	 * 
	 * @return ��õ�HttpSession
	 */
	public HttpSession getSession()
	{
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * �õ�HttpResponse
	 * 
	 * @return ��õ�HttpResponse
	 */
	public HttpServletResponse getResponse()
	{
		return ServletActionContext.getResponse();
	}

	/**
	 * �õ�������·�������磺/mylearningii
	 * 
	 * @return ������·��
	 */
	public String getContext()
	{
		return this.getRequest().getContextPath();
	}

	/**
	 * �õ�����ȫ·�������磺http://127.0.0.1:8080/mylearningii
	 * 
	 * @return ����ȫ·��
	 */
	public String getBasePath()
	{
		String path = this.getRequest().getContextPath();
		String basePath = this.getRequest().getScheme() + "://"
				+ this.getRequest().getServerName();

		if (this.getRequest().getServerPort() != 80)
		{
			basePath += ":" + this.getRequest().getServerPort() + path;
		}
		else
		{
			basePath += path;
		}

		return basePath;
	}

	/**
	 * �õ�Ӧ�õľ��Ե�ַ�����磺D:\Eclipse\workspace\CourseCreater\web\
	 * 
	 * @return Ӧ�õľ��Ե�ַ
	 */
	public String getRealPath()
	{
		return ServletActionContext.getServletContext().getRealPath("/");
	}

	/**
	 * ����û�����ʵIP
	 * 
	 * @return �û�����ʵIP
	 */
	public String getRealIP()
	{
		String ip = getRequest().getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = getRequest().getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = getRequest().getRemoteAddr();
		}

		return ip;
	}

	/**
	 * ��������referer
	 * 
	 * @return referer
	 */
	public String getReferer()
	{
		String referer = getRequest().getHeader("referer");
		if (referer != null && !"".equalsIgnoreCase(referer))
		{
			referer = referer.substring(0, referer.lastIndexOf("/") + 1);
		}
		else
		{
			referer = "";
		}

		return referer;
	}

	protected void writeAjaxStr(String str)
	{
		HttpServletResponse response = this.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		try
		{
			PrintWriter out = response.getWriter();
			out.write(str);
			out.flush();
			out.close();
		}
		catch (IOException e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * ��¼����վ��Ϊ���վ��
	 */
	public void setCoo8TopicSite()
	{
		getSession().setAttribute(LOGIN_GOME_COO8_SITE_FLAG, SITE_FLAG_COO8);
	}

	/**
	 * ��¼����վ��Ϊ����վ��
	 */
	public void setGomeTopicSite()
	{
		getSession().setAttribute(LOGIN_GOME_COO8_SITE_FLAG, SITE_FLAG_GOME);
	}

	/**
	 * ��õ�ǰר������վ��
	 * 
	 * @return
	 */
	public String getTopicSite()
	{    
		return SITE_FLAG_GOME;
	}

	

	/**
	 * ��õ�ǰ��¼�û���
	 * 
	 * @return
	 */
	public String getLoginUserName()
	{
		LoginContext context = LoginContext.getLoginContext();
		String username = "";
		if (null != context) {
			username = context.getNick();
		}
		return username;
	}

	public String getRealUserName()
	{
		LoginContext context = LoginContext.getLoginContext();
		String username = "";
		if (null != context) {
			username = context.getPin();
		}
		return username;
	}
	public String checkExistSite()
	{
		String site = getTopicSite();
		if (null == site || "".equals(site)) { return CHOOSE_TOPIC; }
		return null;
	}

	/**
	 * @author tianyu-ds
	 * @date 2013-09-27
	 * @param param
	 * @return Integer
	 * @desc ��ȡ���Ͳ���
	 */
	public Integer getIntParam(String param)
	{
		String value = getRequest().getParameter(param);
		try
		{
			return Integer.parseInt(value.trim());
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * @author tianyu-ds
	 * @date 2013-09-27
	 * @param param
	 * @return String
	 * @desc ��ȡ�ַ�������
	 */
	public String getStringParam(String param)
	{
		String value = getRequest().getParameter(param);
		if (value == null || "".equals(value.trim())) { return null; }
		return value.trim();
	}
	
	/**
	 * @param param
	 * @return
	 * @desc ��ȡ���Ͳ�������
	 */
	public List<Integer> getIntListParam(String param)
	{
		String value = getRequest().getParameter(param);
		List<Integer> ids = new ArrayList<Integer>();
		try
		{
			if (value != null)
			{
				
				String[] params = value.split("[,;]");
				for (String id : params)
				{
					ids.add(Integer.parseInt(id.trim()));
				}
				return ids;
			}
			return ids;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ids;
		}
	}
	
	/**
	 * �Ը����ַ����� URL ����
	 * 
	 * @param value
	 *            String
	 * @return String
	 */
	public String decode(String value) {
		String result = "";
		if (!isEmpty(value)) {
			try {
				result = java.net.URLDecoder.decode(value, "UTF-8");
				result = java.net.URLDecoder.decode(result, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return result;
	}

	/**
	 * �Ը����ַ����� URL ����
	 * 
	 * @param value
	 *            String
	 * @return String
	 */
	public String encode(String value) {
		String result = "";
		if (!isEmpty(value)) {
			try {
				result = java.net.URLEncoder.encode(value, "UTF-8");
				result = java.net.URLEncoder.encode(result, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return result;
	}

	public boolean isEmpty(String value) {
		if (null == value || "".equals(value.trim()))
			return true;
		else
			return false;
	}
}
