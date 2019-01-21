package com.coo8.btoc.controller.action.admin.template;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/template")
public class TemplateUpdateAction extends TemplateBaseAction {

	private static final long serialVersionUID = -673354742362873509L;
	
	@Action(value="update", results={
			@Result(name=SUCCESS, location="list.action", type="redirect")
	})
	
	@Override
	public String execute() {
		if (template.getId() == null || template.getId() < 1)
			return SUCCESS;
		
		String username = getLoginUserName();
		
		template.setUpdator(username);
		
		templateManager.updateTemplate(template);
		
		return SUCCESS;
	}
}
