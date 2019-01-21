/*
 * 文件名： ListPageTag.java
 * 
 * 创建日期： May 10, 2010
 *
 * Copyright(C) 2010, by caowei.
 *
 * 原始作者: <a href="mailto:caowei@staff.chinabyte.com">caowei</a>
 *
 */
package com.coo8.btoc.util.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.coo8.btoc.util.pages.PaginatedList;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 分页标签
 * 
 * @author <a href="mailto:caowei@staff.chinabyte.com">caowei</a>
 * 
 * @version $Revision$
 * 
 * @since May 10, 2010
 */
public class ListPageTag extends ComponentTagSupport {
	private static final long serialVersionUID = 4211686570083784871L;
	private String name = PaginatedList.COMMONLIST_TAG_KEY;
	private String style = "html";
	private String target = "_self";
	private HttpServletRequest request;
	private Integer systemId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Component getBean(ValueStack valueStack,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (systemId == null) {
			systemId = 1;
		}
		ListPage listPage = new ListPage(valueStack);
		listPage.setRequest(httpServletRequest);
		listPage.setName(name);
		listPage.setStyle(style);
		listPage.setTarget(target);
		listPage.setSystemId(systemId);
		return listPage;
	}

	/**
	 * @return 返回 systemId。
	 */
	public Integer getSystemId() {
		return systemId;
	}

	/**
	 * @param systemId
	 *            要设置的 systemId。
	 */
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

}
