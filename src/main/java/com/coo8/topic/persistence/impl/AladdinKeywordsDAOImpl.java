package com.coo8.topic.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AladdinKeywords;
import com.coo8.topic.model.AladdinKeywordsRef;
import com.coo8.topic.model.Keywords;
import com.coo8.topic.model.News;
import com.coo8.topic.persistence.interfaces.IAladdinKeywordsDAO;

public class AladdinKeywordsDAOImpl extends SqlMapClientDaoSupport implements IAladdinKeywordsDAO {

	@Override
	public AladdinKeywords getById(Integer id) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("id", id);
		List<AladdinKeywords> ret = this.getSqlMapClientTemplate().queryForList("Aladdin.findLikeByMap", search);
		if(!ret.isEmpty()){
			return ret.get(0);
		}
		return null;
	}

	@Override
	public int save(AladdinKeywords entity) {
		Object o = this.getSqlMapClientTemplate().insert("Aladdin.save",entity);
		if(null != o){
			return Integer.valueOf(o.toString());
		}else{
			return -1;
		}
	}

	@Override
	public int deleteById(Integer id) {
		return this.getSqlMapClientTemplate().delete("Aladdin.deleteById",id);

	}

	@Override
	public int update(AladdinKeywords entity) {
		return this.getSqlMapClientTemplate(). update("Aladdin.update",entity);
	}

	@Override
	public PaginatedList<AladdinKeywords> findLikeByMap(
			Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("Aladdin.findLikeByMap.count", search);
		if(o==null)
			return null;
		PaginatedList<AladdinKeywords> paginatedArrayList = new PaginatedArrayList<AladdinKeywords>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<AladdinKeywords> list = this.getSqlMapClientTemplate().queryForList("Aladdin.findLikeByMap", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}

	@Override
	public List<AladdinKeywords> findPage(Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList("Aladdin.findLikeByMap",search);
	}

	@Override
	public List<AladdinKeywordsRef> findAllKeywordsRef(
			Map<String, Object> search) {
		return this.getSqlMapClientTemplate().queryForList("Aladdin.findAllKeywordsRef",search);
	}

	@Override
	public int deleteKeywordsRefById(Integer id) {
		return this.getSqlMapClientTemplate().delete("Aladdin.deleteKeywordsRefById",id);
	}

	@Override
	public int saveKeywordsRef(AladdinKeywordsRef keywordsRef) {
		int ret = -1;
		Object o =  this.getSqlMapClientTemplate().insert("Aladdin.saveKeywordsRef",keywordsRef);
		if(null != o ){
			ret = Integer.valueOf(o.toString());
		}
		return ret;
	}

}
