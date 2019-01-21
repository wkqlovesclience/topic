package com.coo8.btoc.controller.action.admin.html;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.coo8.btoc.business.interfaces.template.TemplateManager;
import com.coo8.btoc.model.html.StaticHtml;
import com.coo8.btoc.model.template.CatalogTemplate;
import com.coo8.btoc.model.template.Template;

@Namespace("/admin/html")
public class LoadTemplateAction extends StaticHtmlBaseAction {

	private static final long serialVersionUID = -3535782618899119376L;
	
	private TemplateManager templateManager;
	
	@Action(value="loadTemplate", results={
			@Result(name=SUCCESS, location="list.action", type="redirect")
	})
	
	@Override
	public String execute() {
		if(isUnavailableId() || isUnavailableTemplateId())
			return SUCCESS;
		
		StaticHtml temp = staticHtmlManager.selectById(html.getId());
		if(temp == null) return SUCCESS;
		
		Template template = templateManager.getTemplateById(html.getTemplateId());
		if(template == null || template.isDisabled())
			return SUCCESS;
		
		html.setUpdator(getAdminSessionOperator());
		staticHtmlManager.updateHtmlandRedis(html);
		if(temp.isCatalogType()) {
			loadCatalogTypeHtmlTemplate(temp);
		} 
		//删除原静态页下的所有产品位
		return SUCCESS;
	}
	
	private void loadCatalogTypeHtmlTemplate(StaticHtml temp) {
		CatalogTemplate ct = new CatalogTemplate();
		ct.setCatalogId(temp.getRefId());
		ct.setTemplateId(html.getTemplateId());
		ct.setType(CatalogTemplate.BIG_CATALOG_TYPE);
		ct.setStatus(CatalogTemplate.ENABLED);
		ct.setCreator(getAdminSessionOperator());
		ct.setUpdator(getAdminSessionOperator());
		
		templateManager.updateLoadTemplate(ct);
	}
	
	
	
	private boolean isUnavailableTemplateId() {
		return html.getTemplateId() == null || html.getTemplateId() < 1;
	}

	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}
	
}