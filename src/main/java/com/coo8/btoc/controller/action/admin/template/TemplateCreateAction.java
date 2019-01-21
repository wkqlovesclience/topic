package com.coo8.btoc.controller.action.admin.template;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;


@Namespace("/admin/template")
public class TemplateCreateAction extends TemplateBaseAction {

	private static final long serialVersionUID = -5249894855689578713L;
	
	
	@Action(value="create", results={
			@Result(name=SUCCESS, location="list.action", type="redirect")
	})
	
	@Override
	public String execute() {
		String username = getLoginUserName();
		String site = getTopicSite();
		
		if(null != checkExistSite()){
			return checkExistSite();
		}
		template.setCreator(username);
		//…Ë÷√’æµ„
		template.setSite(site);
		templateManager.createTemplate(template);
		
		return SUCCESS;
	}
	
	
}
