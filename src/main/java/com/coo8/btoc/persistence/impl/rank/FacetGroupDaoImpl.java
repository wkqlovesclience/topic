/**
 * @author tianyu-ds
 * @date 2014-2-20
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 
 */
package com.coo8.btoc.persistence.impl.rank;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.rank.FacetGroup;
import com.coo8.btoc.persistence.interfaces.rank.IFacetGroupDao;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.ibatis.sqlmap.client.SqlMapExecutor;

@SuppressWarnings("unchecked")
public class FacetGroupDaoImpl extends SqlMapClientDaoSupport implements
		IFacetGroupDao
{
	@Override
	public FacetGroup getById(String id)
	{
		return (FacetGroup) getSqlMapClientTemplate().queryForObject(
				"FacetGroup.getById", id);
	}

	@Override
	public PaginatedList<FacetGroup> list(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"FacetGroup.count", paramMap);
		if (o == null) return null;

		PaginatedList<FacetGroup> paginatedArrayList = new PaginatedArrayList<FacetGroup>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<FacetGroup> list = this.getSqlMapClientTemplate().queryForList(
				"FacetGroup.list", paramMap, paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}
	@Override
	public List<FacetGroup> facetGrouplist(Map<String, Object> paramMap){
		return this.getSqlMapClientTemplate().queryForList(
				"FacetGroup.list",paramMap);
	}
	@Override
	public void add(FacetGroup facetGroup)
	{
		getSqlMapClientTemplate().insert("FacetGroup.add", facetGroup);
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
					executor.update("FacetGroup.updateIsShow", id);
				}
				return executor.executeBatch();
			}
		});
	}

	@Override
	public void update(FacetGroup facetGroup)
	{
		getSqlMapClientTemplate().update("FacetGroup.update", facetGroup);
	}

	@Override
	public int changeShowState(Map<String, Object> paramMap) {
		return getSqlMapClientTemplate().update("FacetGroup.changeShowState", paramMap);
	}

}
