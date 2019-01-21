package com.coo8.topic.persistence.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.CategoryNavigation;
import com.coo8.topic.model.CategoryNavigationFirst;
import com.coo8.topic.model.CategoryNavigationThird;

public interface ICategoryNavigationDAO {
	
	public CategoryNavigation getById(Integer id);
	
	public CategoryNavigation getByName(String names, Integer id);

	public int save(CategoryNavigation entity);
	
	public void deleteById(Integer id);
	
	public void deleteFirstByGroupId(Integer id);
	
	public void deleteThirdByGroupId(Integer id);
	
	public int update(CategoryNavigation entity);

	public PaginatedList<CategoryNavigation> findLikeByMap(Map<String, Object> search);
	
	public PaginatedList<CategoryNavigationFirst> findLikeFirstByMap(Map<String, Object> search);
	
	public PaginatedList<CategoryNavigationThird> findLikeThirdByMap(Map<String, Object> search);
	
	public CategoryNavigationFirst getFirstById(Integer id);
	
	public CategoryNavigationThird getThirdById(Integer id);
	
	public List<CategoryNavigationFirst> getFirstCategoryByGroupId(Integer groupId);
	
	public int saveFirst(CategoryNavigationFirst entity);
	
	public void deleteFirstById(Integer id);
	
	public void deleteThirdByFirstId(Integer groupId, String firstId);
	
	public int updateFirst(CategoryNavigationFirst entity);
	
	public CategoryNavigationFirst getByFirstCatId(Integer groupId, String catId, Integer firstId);
	
	public CategoryNavigationThird getByThirdCatId(Integer groupId, String catId, String subCatId, String extendName, Integer thirdId);
	
	public int saveThird(CategoryNavigationThird entity);
	
	public void deleteThirdById(Integer id);
	
	public int updateThird(CategoryNavigationThird entity);

}