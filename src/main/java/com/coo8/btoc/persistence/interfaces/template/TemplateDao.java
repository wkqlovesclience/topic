package com.coo8.btoc.persistence.interfaces.template;

import java.util.List;

import com.coo8.btoc.model.template.CatalogTemplate;
import com.coo8.btoc.model.template.Template;
import com.coo8.btoc.model.template.TemplateHistory;

public interface TemplateDao {

	void insertTemplate(Template tmeplate);
	void updateTemplate(Template template);
	void applyTemplate(Template template);
	
	Template getTemplateById(Integer id);
	List<Template> queryTemplates(Template template);
	TemplateHistory queryLastTemplateHistory(Integer id);
	
	void insertTemplateHistory(Template history);
	void updateTemplateHistory(TemplateHistory history);
	
	void insertCatalogTemplate(CatalogTemplate catalogTemplate);
	void updateCatalogTemplate(CatalogTemplate catalogTemplate);
	void deleteCatalogTemplate(CatalogTemplate catalogTemplate);
	CatalogTemplate selectCatalogTemplateByCidAndType(
		CatalogTemplate catalogTemplate
	);
}
