package com.coo8.btoc.controller.action.admin.html;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/html")
public class StaticHtmlUpdateRequestAction extends StaticHtmlBaseAction {

	private static final long serialVersionUID = 1703191423543611074L;
	
	@Action(value="updateRequest", results={
			@Result(name=SUCCESS, location="/jsp/admin/html/admin_static_html_update.jsp", type="dispatcher"),
			@Result(name=INPUT, location="list.action", type="redirect")
	})
	
	@Override
	public String execute() {
		
		if(isUnavailableId())
			return INPUT;
		catalogList = btocCatalogManager.getBigCatalogToList();
		
		html = staticHtmlManager.selectById(html.getId());
		
		return SUCCESS;
	}
	
	
}