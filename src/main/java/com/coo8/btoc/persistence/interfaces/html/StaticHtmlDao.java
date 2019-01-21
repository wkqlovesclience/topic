package com.coo8.btoc.persistence.interfaces.html;

import java.util.List;

import com.coo8.btoc.model.html.StaticHtml;

public interface StaticHtmlDao {
	void insertHtml(StaticHtml html);
	
	void updateHtml(StaticHtml html);
	
	int updateStaticPublish(int id);
	
	int updateCatalogPublish(int id, int staticId);
	
	void deleteStaticHtml(StaticHtml html);
	
	List<StaticHtml> queryHtmls(StaticHtml html);
	
	StaticHtml selectHtmlById(Integer id);

}
