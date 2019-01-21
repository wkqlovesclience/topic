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
	 * �鿴��ǰ���ص�ģ��
	 * @param id
	 * @return
	 */
	Template getTemplateById(Integer id);
	
	/**
	 * �޸�ģ���ʱ��ʹ��
	 * @param id
	 * @return
	 */
	Template getUpdateTemplateById(Integer id);
	
	List<Template> queryTemplates(Template template);
	
	void updateLoadTemplate(CatalogTemplate catalogTemplate);
	void deleteCatalogTemplate(CatalogTemplate catalogTemplate);
	CatalogTemplate selectCatalogTemplateByCidAndType(CatalogTemplate ct);
}
