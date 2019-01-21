/**
 * @author tianyu-ds
 * @date 2013-9-30
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 
 */
package com.coo8.topic.persistence.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.Tag;
import com.coo8.topic.persistence.interfaces.ITagDAO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

@SuppressWarnings("unchecked")
public class TagDAOImpl extends SqlMapClientDaoSupport implements ITagDAO
{
	@Override
	public List<Tag> all()
	{
		return this.getSqlMapClientTemplate().queryForList("Tag.all");
	}

	@Override
	public void add(Tag tag)
	{
		getSqlMapClientTemplate().insert("Tag.add", tag);
	}

	public void update(Tag tag)
	{
		getSqlMapClientTemplate().update("Tag.update", tag);
	}

	@Override
	public void delete(final List<Integer> ids)
	{
		if (ids != null)
		{
			getSqlMapClientTemplate().execute(new SqlMapClientCallback()
			{
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException
				{
					for (Integer id : ids)
					{
						executor.delete("Tag.delete", id);
						executor.delete("Tag.deleteByParentId", id);
					}
					return executor.executeBatch();
				}
			});
		}
	}

	@Override
	public Tag getById(Integer id)
	{
		return (Tag) getSqlMapClientTemplate()
				.queryForObject("Tag.getById", id);
	}

	@Override
	public PaginatedList<Tag> list(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject("Tag.count",
				paramMap);
		if (o == null) return null;

		PaginatedList<Tag> paginatedArrayList = new PaginatedArrayList<Tag>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<Tag> list = this.getSqlMapClientTemplate().queryForList(
				"Tag.list", paramMap, paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public Integer count(Map<String, Object> paramMap)
	{
		return null;
	}

	@Override
	public PaginatedList<Tag> getByParentId(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"Tag.countByParentId", paramMap);
		if (o == null) return null;

		PaginatedList<Tag> paginatedArrayList = new PaginatedArrayList<Tag>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<Tag> list = this.getSqlMapClientTemplate().queryForList(
				"Tag.getByParentId", paramMap,
				paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public List<Tag> getByParentId(Integer parentId)
	{
		List<Tag> list = this.getSqlMapClientTemplate().queryForList(
				"Tag.getByIntParentId", parentId);
		return list;
	}

	@Override
	public List<Tag> getAllFirstTag()
	{
		return this.getSqlMapClientTemplate()
				.queryForList("Tag.getAllFirstTag");
	}

	@Override
	public PaginatedList<Tag> getChildren(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"Tag.countChildren", paramMap);
		if (o == null) return null;

		PaginatedList<Tag> paginatedArrayList = new PaginatedArrayList<Tag>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));
		List<Tag> list = this.getSqlMapClientTemplate().queryForList(
				"Tag.getChildren", paramMap, paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}

	@Override
	public Integer productCountOfSubtag(Integer subtagId) {
		return (Integer) getSqlMapClientTemplate().queryForObject("Tag.productCountOfSubtag", subtagId);
	}

	@Override
	public Integer subtagCountOfFirstTag(Integer tagId) {
		return (Integer) getSqlMapClientTemplate().queryForObject("Tag.subtagCountOfFirstTag", tagId);
	}

	@Override
	public Tag getTagFromExcel(String tagName) {
		return (Tag) getSqlMapClientTemplate().queryForObject("Tag.getTagFromExcel", tagName);
	}
}
