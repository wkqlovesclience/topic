package com.coo8.btoc.controller.action.admin.html;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.coo8.btoc.model.html.StaticHtml;

@Namespace("/admin/html")
public class StaticHtmlListAction extends StaticHtmlBaseAction {

	private static final long serialVersionUID = -4249556104699934268L;
	@Action(value="list", results={
			@Result(name=SUCCESS, location="/jsp/admin/html/admin_static_html_list.jsp", type="dispatcher"),
			@Result(name="QXBZ",location="/jsp/admin/popedom/error.jsp"),
			@Result(name="userInexistence",location="/jsp/admin/index.jsp")
	})
	
	@Override
	public String execute() {
		checkPage();
		
		htmlList = staticHtmlManager.queryHtmls(html);
		
		return SUCCESS;
	}
	
	protected void checkPage() {
		if (html == null)
			html = new StaticHtml();
		
		if (html.getPageIndex() == null || html.getPageIndex() < 1)
			html.setPageIndex(1);
		
		if (html.getPageSize() == null || html.getPageSize() < 1)
			html.setPageSize(30);
	}

}
