package com.coo8.btoc.controller.action.admin.template;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/template")
public class UpdateTemplateRequestAction extends TemplateBaseAction {

	private static final long serialVersionUID = -2869810288704824639L;
	
	private Integer id ;
	
	@Action(value="updateRequest", results={
			@Result(name=SUCCESS, location="/jsp/admin/template/admin_template_update.jsp", type="dispatcher"),
			@Result(name=INPUT, location="list.action", type="redirect")
	})
	@Override
	public String execute() {
		if (isUnavailableId())
			return INPUT;
		
		template = templateManager.getUpdateTemplateById(id);
		
		if (template == null)
			return INPUT;
		
		return SUCCESS;
	}
	
	private boolean isUnavailableId() {
		return id == null || id < 1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
