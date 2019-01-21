/**
 * @author tianyu-ds
 * @date 2014-2-16
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 
 */
package com.coo8.btoc.persistence.impl.rank;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.rank.Category;
import com.coo8.btoc.persistence.interfaces.rank.ICategoryDAO;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.ibatis.sqlmap.client.SqlMapExecutor;

@SuppressWarnings("unchecked")
public class CategoryDaoImpl extends SqlMapClientDaoSupport implements
		ICategoryDAO
{
	@Override
	public Category getById(String id)
	{
		return (Category) getSqlMapClientTemplate().queryForObject(
				"Category.getById", id);
	}

	@Override
	public void updateQuene()
	{
		getSqlMapClientTemplate().update("Category.updateBlockQueue");
		getSqlMapClientTemplate().update("Category.updateProductQueue");
	}

	@Override
	public Integer getByParentId(String parentId)
	{
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"Category.getByParentId", parentId);
	}

	@Override
	public Category validateCategory(String categoryName)
	{
		return (Category) getSqlMapClientTemplate().queryForObject(
				"Category.getByName", categoryName);
	}

	@Override
	public PaginatedList<Category> list(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"Category.count", paramMap);
		if (o == null) return null;

		PaginatedList<Category> paginatedArrayList = new PaginatedArrayList<Category>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<Category> list = this.getSqlMapClientTemplate().queryForList(
				"Category.list", paramMap, paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public List<Category> listAll(Map<String, Object> paramMap)
	{
		List<Category> arrayList = new ArrayList<Category>();
		
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"Category.count", paramMap);
		if (o == null) return arrayList;

	

		List<Category> list = this.getSqlMapClientTemplate().queryForList(
				"Category.list",paramMap);

		if (list != null) arrayList.addAll(list);

		return arrayList;
	}
	
	@Override
	public void add(Category category)
	{
		getSqlMapClientTemplate().insert("Category.add", category);
	}

	@Override
	public void delete(final List<String> ids)
	{
		getSqlMapClientTemplate().execute(new SqlMapClientCallback()
		{
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException
			{
				for (String id : ids)
				{
					executor.update("Category.updateIsShow", id);
				}
				return executor.executeBatch();
			}
		});
	}

	@Override
	public void update(Category category)
	{
		getSqlMapClientTemplate().update("Category.update", category);
	}

	@Override
	public int getUnhandleNumberOfBlockQueue() {
		return (Integer) getSqlMapClientTemplate().queryForObject("Category.getUnhandleNumberOfBlockQueue");
	}

	@Override
	public int getUnhandleNumberOfProductQueue() {
		return (Integer) getSqlMapClientTemplate().queryForObject("Category.getUnhandleNumberOfProductQueue");
	}

	@Override
	public int changeFirstCatShowState(Map<String, Object> paramMap) {
		return getSqlMapClientTemplate().update("Category.changeFirstCatShowState",paramMap);
	}

	@Override
	public Category getByCid(Integer cid) {
		return (Category) getSqlMapClientTemplate().queryForObject("Category.getByCid", cid);
	}

}
