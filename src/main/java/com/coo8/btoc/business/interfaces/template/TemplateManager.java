package com.coo8.btoc.business.interfaces.template;

import java.util.List;

import com.coo8.btoc.model.template.CatalogTemplate;
import com.coo8.btoc.model.template.Template;

public interface TemplateManager {

	void createTemplate(Template template);

	void updateTemplate(Template template);
	void enableTemplate(Template template);
	void disableTemplate(Template template);
	
	void applyTemplate(Integer id, String updator);
	
	/**
	 * 查看当前挂载的模板
	 * @param id
	 * @return
	 */
	Template getTemplateById(Integer id);
	
	/**
	 * 修改模板的时候使用
	 * @param id
	 * @return
	 */
	Template getUpdateTemplateById(Integer id);
	
	List<Template> queryTemplates(Template template);
	
	void updateLoadTemplate(CatalogTemplate catalogTemplate);
	void deleteCatalogTemplate(CatalogTemplate catalogTemplate);
	CatalogTemplate selectCatalogTemplateByCidAndType(CatalogTemplate ct);
}
