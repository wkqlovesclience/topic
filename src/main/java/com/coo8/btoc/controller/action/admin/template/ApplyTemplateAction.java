package com.coo8.btoc.controller.action.admin.template;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/template")
public class ApplyTemplateAction extends TemplateBaseAction {

	private static final long serialVersionUID = 3135409347707705109L;
	
	@Action(value="apply", results={
			@Result(name=SUCCESS, location="list.action", 
					type="redirect")
	})
	
	@Override
	public String execute() {
		if (template == null || template.getId() == null || template.getId() < 0)
			return SUCCESS;
		
		String username = getLoginUserName();
		templateManager.applyTemplate(template.getId(), username);
		return SUCCESS;
	}
	
}
