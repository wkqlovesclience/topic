/**
 * @author tianyu-ds
 * @date 2014-2-16
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 
 */
package com.coo8.btoc.business.impl.rank;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.business.interfaces.rank.ICategoryManager;
import com.coo8.btoc.model.rank.Category;
import com.coo8.btoc.persistence.interfaces.rank.ICategoryDAO;
import com.coo8.btoc.util.pages.PaginatedList;

public class CategoryManagerImpl implements ICategoryManager
{
	private ICategoryDAO categoryDAO;

	@Override
	public Category getById(String id)
	{
		return categoryDAO.getById(id);
	}
	
	@Override
	public Integer getByParentId(String parentId)
	{
		return categoryDAO.getByParentId(parentId);
	}

	@Override
	public PaginatedList<Category> list(Map<String, Object> paramMap)
	{
		return categoryDAO.list(paramMap);
	}
	
	@Override
	public List<Category> listAll(Map<String, Object> paramMap){
		return categoryDAO.listAll(paramMap);
	}
	

	@Override
	public void add(Category category)
	{
		categoryDAO.add(category);
	}

	@Override
	public void delete(List<String> ids)
	{
		categoryDAO.delete(ids);
	}

	@Override
	public void update(Category category)
	{
		categoryDAO.update(category);
	}
	
	@Override
	public Category validateCategory(String categoryName)
	{
		return categoryDAO.validateCategory(categoryName);
	}

	public ICategoryDAO getCategoryDAO()
	{
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO)
	{
		this.categoryDAO = categoryDAO;
	}

	@Override
	public boolean isHasUnhandleQueueData() {
		int bolckNum = categoryDAO.getUnhandleNumberOfBlockQueue();
		int productNum = categoryDAO.getUnhandleNumberOfProductQueue();
		if(bolckNum == 0 && productNum == 0){
			return false;
		}
		return true;
	}

	@Override
	public int changeFirstCatShowState(Map<String, Object> paramMap) {
		return categoryDAO.changeFirstCatShowState(paramMap);
	}

	@Override
	public Category getByCid(Integer cid) {
		return categoryDAO.getByCid(cid);
	}
}
