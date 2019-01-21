package com.coo8.btoc.persistence.impl.template;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.template.CatalogTemplate;
import com.coo8.btoc.model.template.Template;
import com.coo8.btoc.model.template.TemplateHistory;
import com.coo8.btoc.persistence.interfaces.template.TemplateDao;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;

public class TemplateDaoImpl extends SqlMapClientDaoSupport implements
	TemplateDao {

	@Override
	public void insertTemplate(Template template) {
		getSqlMapClientTemplate().insert("Template.insertTemplate", template);
	}
	
	@Override
	public void updateTemplate(Template template) {
		getSqlMapClientTemplate().update("Template.updateTemplate", template);
	}

	@Override
	public void applyTemplate(Template template) {
		getSqlMapClientTemplate().update("Template.applyTemplate", template);	
	}

	@Override
	public Template getTemplateById(Integer id) {
		return (Template) getSqlMapClientTemplate().
			queryForObject("Template.getTemplateById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Template> queryTemplates(Template template) {
		int count = queryTemplateCount(template);
		if (count <= 0)
			return PaginatedArrayList.emptyList();
		
		PaginatedList<Template> pageList = new PaginatedArrayList<Template>
			(count, template.getPageIndex(), template.getPageSize()); 
		
		List<Template> temp = getSqlMapClientTemplate()
			.queryForList("Template.queryTemplates", template, 
					pageList.getStartPos(), template.getPageSize());;
		
		pageList.addAll(temp);
		
		return pageList;
	}

	private int queryTemplateCount(Template template) {
		Integer count = (Integer) getSqlMapClientTemplate()
			.queryForObject("Template.queryTemplateCount", template);
		
		return count == null ? 0 : count.intValue();
	}
	
	@Override
	public void updateTemplateHistory(TemplateHistory history) {
		getSqlMapClientTemplate().update(
				"Template.updateTemplateHistory", history);
	}

	@Override
	public TemplateHistory queryLastTemplateHistory(Integer id) {
		return (TemplateHistory) getSqlMapClientTemplate()
			.queryForObject("Template.queryTemplateLastHistory", id);
	}

	@Override
	public void insertTemplateHistory(Template history) {
		getSqlMapClientTemplate()
			.insert("Template.insertTemplateHistory", history);
	}

	@Override
	public void insertCatalogTemplate(CatalogTemplate catalogTemplate) {
		getSqlMapClientTemplate()
			.insert("Template.insertCatalogTemplate", catalogTemplate);

	}
	
	@Override
	public void deleteCatalogTemplate(CatalogTemplate catalogTemplate) {
		getSqlMapClientTemplate()
			.delete("Template.deleleCatalogTemplate", catalogTemplate);

	}
	
	@Override
	public void updateCatalogTemplate(CatalogTemplate catalogTemplate) {
		getSqlMapClientTemplate()
			.update("Template.updateCatalogTemplate", catalogTemplate);

	}
	
	@Override
	public CatalogTemplate selectCatalogTemplateByCidAndType(
		CatalogTemplate catalogTemplate) {
		
		return (CatalogTemplate) getSqlMapClientTemplate()
			.queryForObject("Template.selectCatalogTemplateByCatalogIdAndType",
							catalogTemplate);

	}
}