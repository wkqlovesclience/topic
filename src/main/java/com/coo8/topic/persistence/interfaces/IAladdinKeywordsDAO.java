package com.coo8.topic.persistence.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AladdinKeywords;
import com.coo8.topic.model.AladdinKeywordsRef;
import com.coo8.topic.model.Keywords;

public interface IAladdinKeywordsDAO {
	
	public AladdinKeywords getById(java.lang.Integer id);

	public int save(AladdinKeywords entity);
	
	public int deleteById(java.lang.Integer id);
	
	public int update(AladdinKeywords entity);

	public PaginatedList<AladdinKeywords> findLikeByMap(
			Map<String, Object> search);

	public List<AladdinKeywords> findPage(Map<String, Object> search);

	public List<AladdinKeywordsRef> findAllKeywordsRef(
			Map<String, Object> search);

	public int deleteKeywordsRefById(Integer id);

	public int saveKeywordsRef(AladdinKeywordsRef keywordsRef);
	
	

}
