package com.coo8.btoc.controller.action.admin.html;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/html")
public class StaticHtmlUpdateAction extends StaticHtmlBaseAction {

	private static final long serialVersionUID = -8141615323261699250L;
	
	@Action(value="update", results={
			@Result(name=SUCCESS, location="list.action", type="redirect")
	})
	
	@Override
	public String execute() {
		if(isUnavailableId() || verifyPath())
			return SUCCESS;
		
		html.setUpdator(getAdminSessionOperator());
		staticHtmlManager.updateHtml(html);

		return SUCCESS;
	}
}
