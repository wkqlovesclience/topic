/**
 * @author tianyu-ds
 * @date 2014-2-16
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 
 */
package com.coo8.btoc.persistence.interfaces.rank;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.rank.Category;
import com.coo8.btoc.util.pages.PaginatedList;

public interface ICategoryDAO
{
	public Category getById(String id);
	public Category getByCid(Integer cid);

	public PaginatedList<Category> list(Map<String, Object> paramMap);
	
	public List<Category> listAll(Map<String, Object> paramMap);

	public void add(Category category);

	public void delete(List<String> ids);

	public void update(Category category);
	
	public Category validateCategory(String categoryName);
	
	public Integer getByParentId(String parentId);
	
	public void updateQuene();
	
	public int getUnhandleNumberOfBlockQueue();
	public int getUnhandleNumberOfProductQueue();
	
	
	public int changeFirstCatShowState(Map<String,Object> paramMap);
}
