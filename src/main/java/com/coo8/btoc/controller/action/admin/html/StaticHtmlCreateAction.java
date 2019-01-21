package com.coo8.btoc.controller.action.admin.html;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/html")
public class StaticHtmlCreateAction extends StaticHtmlBaseAction {

	private static final long serialVersionUID = 1711710598879602175L;
	
	@Action(value="create", results={
			@Result(name=SUCCESS, location="list.action", type="redirect"),
			@Result(name=INPUT, location="list.action", type="redirect")
	})
	
	@Override
	public String execute() {
		if(verifyPath())
			return SUCCESS;
		
		html.setCreator(getAdminSessionOperator());
		
		staticHtmlManager.createHtml(html);
		
		return SUCCESS;
	}
	
	
}