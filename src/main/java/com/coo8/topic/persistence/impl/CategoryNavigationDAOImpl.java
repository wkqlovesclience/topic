package com.coo8.topic.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.CategoryNavigation;
import com.coo8.topic.model.CategoryNavigationFirst;
import com.coo8.topic.model.CategoryNavigationThird;
import com.coo8.topic.persistence.interfaces.ICategoryNavigationDAO;

public class CategoryNavigationDAOImpl extends SqlMapClientDaoSupport implements ICategoryNavigationDAO {

	@Override
	public CategoryNavigation getById(Integer id) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("id", id);
		List<CategoryNavigation> ret = this.getSqlMapClientTemplate().queryForList("CategoryNavigation.getById", search);
		if(null != ret && !ret.isEmpty()){
			return ret.get(0);
		}
		return null;
	}
	
	@Override
	public CategoryNavigation getByName(String names, Integer id) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("id", id);
		search.put("names", names);
		List<CategoryNavigation> ret = this.getSqlMapClientTemplate().queryForList("CategoryNavigation.getByName", search);
		if(null != ret && !ret.isEmpty()){
			return ret.get(0);
		}
		return null;
	}

	@Override
	public int save(CategoryNavigation entity) {
		Object o = this.getSqlMapClientTemplate().insert("CategoryNavigation.save",entity);
		if(null != o){
			return Integer.valueOf(o.toString());
		}else{
			return -1;
		}
	}

	@Override
	public void deleteById(Integer id) {
		this.getSqlMapClientTemplate().delete("CategoryNavigation.deleteById",id);
	}
	
	@Override
	public void deleteFirstByGroupId(Integer id) {
		this.getSqlMapClientTemplate().delete("CategoryNavigation.deleteFirstByGroupId",id);
	}
	
	@Override
	public void deleteThirdByGroupId(Integer id) {
		this.getSqlMapClientTemplate().delete("CategoryNavigation.deleteThirdByGroupId",id);
	}
	
	@Override
	public int update(CategoryNavigation entity) {
		return this.getSqlMapClientTemplate(). update("CategoryNavigation.update",entity);
	}

	@Override
	public PaginatedList<CategoryNavigation> findLikeByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("CategoryNavigation.findLikeByMap.count", search);
		if(o==null)
			return null;
		PaginatedList<CategoryNavigation> paginatedArrayList = new PaginatedArrayList<CategoryNavigation>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<CategoryNavigation> list = this.getSqlMapClientTemplate().queryForList("CategoryNavigation.findLikeByMap", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList;
	}

	@Override
	public PaginatedList<CategoryNavigationFirst> findLikeFirstByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("CategoryNavigation.findLikeFirstByMap.count", search);
		if(o==null)
			return null;
		PaginatedList<CategoryNavigationFirst> paginatedArrayList = new PaginatedArrayList<CategoryNavigationFirst>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<CategoryNavigationFirst> list = this.getSqlMapClientTemplate().queryForList("CategoryNavigation.findLikeFirstByMap", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList;
	}
	
	@Override
	public PaginatedList<CategoryNavigationThird> findLikeThirdByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("CategoryNavigation.findLikeThirdByMap.count", search);
		if(o==null)
			return null;
		PaginatedList<CategoryNavigationThird> paginatedArrayList = new PaginatedArrayList<CategoryNavigationThird>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<CategoryNavigationThird> list = this.getSqlMapClientTemplate().queryForList("CategoryNavigation.findLikeThirdByMap", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList;
	}
	
	@Override
	public CategoryNavigationFirst getFirstById(Integer id) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("id", id);
		List<CategoryNavigationFirst> ret = this.getSqlMapClientTemplate().queryForList("CategoryNavigation.getFirstById", search);
		if(null != ret && !ret.isEmpty()){
			return ret.get(0);
		}
		return null;
	}
	
	@Override
	public CategoryNavigationThird getThirdById(Integer id) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("id", id);
		List<CategoryNavigationThird> ret = this.getSqlMapClientTemplate().queryForList("CategoryNavigation.getThirdById", search);
		if(null != ret && !ret.isEmpty()){
			return ret.get(0);
		}
		return null;
	}
	
	@Override
	public int saveFirst(CategoryNavigationFirst entity) {
		Object o = this.getSqlMapClientTemplate().insert("CategoryNavigation.saveFirst",entity);
		if(null != o){
			return Integer.valueOf(o.toString());
		}else{
			return -1;
		}
	}

	@Override
	public void deleteFirstById(Integer id) {
		this.getSqlMapClientTemplate().delete("CategoryNavigation.deleteFirstById",id);
	}

	@Override
	public void deleteThirdByFirstId(Integer groupId, String firstId) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("groupId", groupId);
		search.put("firstId", firstId);
		this.getSqlMapClientTemplate().delete("CategoryNavigation.deleteThirdByFirstId",search);
	}
	
	@Override
	public int updateFirst(CategoryNavigationFirst entity) {
		return this.getSqlMapClientTemplate(). update("CategoryNavigation.updateFirst",entity);
	}
	
	@Override
	public CategoryNavigationFirst getByFirstCatId(Integer groupId, String catId, Integer firstId) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("groupId", groupId);
		search.put("catId", catId);
		search.put("id", firstId);
		List<CategoryNavigationFirst> ret = this.getSqlMapClientTemplate().queryForList("CategoryNavigation.getByFirstCatId", search);
		if(null != ret && !ret.isEmpty()){
			return ret.get(0);
		}
		return null;
	}
	
	@Override
	public int saveThird(CategoryNavigationThird entity) {
		Object o = this.getSqlMapClientTemplate().insert("CategoryNavigation.saveThird",entity);
		if(null != o){
			return Integer.valueOf(o.toString());
		}else{
			return -1;
		}
	}

	@Override
	public void deleteThirdById(Integer id) {
		this.getSqlMapClientTemplate().delete("CategoryNavigation.deleteThirdById",id);
	}

	@Override
	public int updateThird(CategoryNavigationThird entity) {
		return this.getSqlMapClientTemplate(). update("CategoryNavigation.updateThird",entity);
	}
	
	@Override
	public List<CategoryNavigationFirst> getFirstCategoryByGroupId(Integer groupId) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("groupId", groupId);
		return this.getSqlMapClientTemplate().queryForList("CategoryNavigation.getFirstCategoryByGroupId", search);
	}
	
	@Override
	public CategoryNavigationThird getByThirdCatId(Integer groupId,
			String catId, String subCatId, String extendName, Integer thirdId) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("id", thirdId);
		search.put("groupId", groupId);
		search.put("firstId", catId);
		search.put("secondId", subCatId);
		search.put("extendName", extendName);
		List<CategoryNavigationThird> ret = this.getSqlMapClientTemplate().queryForList("CategoryNavigation.getByThirdCatId", search);
		if(null != ret && !ret.isEmpty()){
			return ret.get(0);
		}
		return null;
	}
}
