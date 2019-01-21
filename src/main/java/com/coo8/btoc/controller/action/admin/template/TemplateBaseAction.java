package com.coo8.btoc.controller.action.admin.template;

import java.util.List;

import com.coo8.btoc.business.interfaces.block.BlockManager;
import com.coo8.btoc.business.interfaces.template.TemplateManager;
import com.coo8.btoc.controller.action.CommonAction;
import com.coo8.btoc.model.template.Template;

public class TemplateBaseAction extends CommonAction {

	private static final long serialVersionUID = -2803346777569872742L;
	
	protected TemplateManager templateManager;
	protected BlockManager blockManager;
	
	protected Template template;
	protected List<Template> templateList;
	
	protected void checkPage() {
		if (template == null)
			template = new Template();
		
		if (template.getPageIndex() == null || template.getPageIndex() < 1)
			template.setPageIndex(1);
		
		if (template.getPageSize() == null || template.getPageSize() < 1)
			template.setPageSize(30);
	}

	
	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
	
	public List<Template> getTemplateList() {
		return templateList;
	}

	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}

	public void setBlockManager(BlockManager blockManager) {
		this.blockManager = blockManager;
	}
	
}
