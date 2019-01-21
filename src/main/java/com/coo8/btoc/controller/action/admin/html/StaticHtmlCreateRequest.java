package com.coo8.btoc.controller.action.admin.html;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/html")
public class StaticHtmlCreateRequest extends StaticHtmlBaseAction {

	private static final long serialVersionUID = 5048017578721431746L;
	
	@Action(value="staticHtmlCreateRequest", results={
			@Result(name=SUCCESS, location="/jsp/admin/html/admin_static_html_create.jsp")
	})
	
	@Override
	public String execute() {
		
		catalogList = btocCatalogManager.getBigCatalogToList();
		
		return SUCCESS;
	}
}
