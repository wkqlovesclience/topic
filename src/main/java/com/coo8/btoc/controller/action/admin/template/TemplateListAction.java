package com.coo8.btoc.controller.action.admin.template;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/admin/template")
public class TemplateListAction extends TemplateBaseAction {

	private static final long serialVersionUID = -3027707303448569540L;
	
	private Integer type;
	private Integer catalogId;
	
	@Action(value="list",
			results={
			@Result(name=SUCCESS, location="/jsp/admin/template/admin_template_list.jsp", type="dispatcher"),
			@Result(name="QXBZ",location="/jsp/admin/popedom/error.jsp"),
			@Result(name="userInexistence",location="/jsp/admin/index.jsp")
	})
	
	@Override
	public String execute() {
		checkPage();
		//ÉèÖÃÕ¾µã
		template.setSite(getTopicSite());
		templateList = templateManager.queryTemplates(template);
		
		return SUCCESS;
	}
	
	public boolean isLoadRequest() {
		return type != null && type >= 0 && catalogId != null && catalogId > 0;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
	
	
}
