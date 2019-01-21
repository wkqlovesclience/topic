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

import com.coo8.btoc.model.rank.FacetGroupItem;
import com.coo8.btoc.persistence.interfaces.rank.IFacetGroupItemDao;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.ibatis.sqlmap.client.SqlMapExecutor;

@SuppressWarnings("unchecked")
public class FacetGroupItemDaoImpl extends SqlMapClientDaoSupport implements
		IFacetGroupItemDao
{
	@Override
	public FacetGroupItem getById(String id)
	{
		return (FacetGroupItem) getSqlMapClientTemplate().queryForObject(
				"FacetGroupItem.getById", id);
	}

	@Override
	public PaginatedList<FacetGroupItem> list(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"FacetGroupItem.count", paramMap);
		if (o == null) return null;

		PaginatedList<FacetGroupItem> paginatedArrayList = new PaginatedArrayList<FacetGroupItem>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

		List<FacetGroupItem> list = this.getSqlMapClientTemplate()
				.queryForList("FacetGroupItem.list", paramMap,
						paginatedArrayList.getStartPos(),
						paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}
	@Override
	public List<FacetGroupItem> listAll(Map<String, Object> paramMap){
		return this.getSqlMapClientTemplate()
		.queryForList("FacetGroupItem.list", paramMap);
	}
	@Override
	public void add(FacetGroupItem facetGroupItem)
	{
		getSqlMapClientTemplate().insert("FacetGroupItem.add", facetGroupItem);
	}

	@Override
	public void delete(String id){
		getSqlMapClientTemplate().delete("FacetGroupItem.delete",id);
	}
	@Override
	public void update(FacetGroupItem facetGroupItem)
	{
		getSqlMapClientTemplate().update("FacetGroupItem.update",
				facetGroupItem);
	}

	@Override
	public int changeShowState(Map<String, Object> paramMap) {
		return getSqlMapClientTemplate().update("FacetGroupItem.changeShowState",paramMap);
	}
}
