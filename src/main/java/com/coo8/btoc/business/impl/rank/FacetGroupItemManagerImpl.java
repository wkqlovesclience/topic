/**
 * @author tianyu-ds
 * @date 2014-2-20
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 
 */
package com.coo8.btoc.business.impl.rank;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.business.interfaces.rank.IFacetgroupItemManager;
import com.coo8.btoc.model.rank.FacetGroupItem;
import com.coo8.btoc.persistence.interfaces.rank.IFacetGroupItemDao;
import com.coo8.btoc.util.pages.PaginatedList;

public class FacetGroupItemManagerImpl implements IFacetgroupItemManager
{
	private IFacetGroupItemDao facetGroupItemDao;

	@Override
	public FacetGroupItem getById(String id)
	{
		return facetGroupItemDao.getById(id);
	}

	@Override
	public PaginatedList<FacetGroupItem> list(Map<String, Object> paramMap)
	{
		return facetGroupItemDao.list(paramMap);
	}
	@Override
	public List<FacetGroupItem> listAll(Map<String, Object> paramMap)
	{
		return facetGroupItemDao.listAll(paramMap);
	}

	@Override
	public void add(FacetGroupItem facetGroupItem)
	{
		facetGroupItemDao.add(facetGroupItem);
	}

	@Override
	public void update(FacetGroupItem facetGroupItem)
	{
		facetGroupItemDao.update(facetGroupItem);
	}

	public IFacetGroupItemDao getFacetGroupItemDao()
	{
		return facetGroupItemDao;
	}

	public void setFacetGroupItemDao(IFacetGroupItemDao facetGroupItemDao)
	{
		this.facetGroupItemDao = facetGroupItemDao;
	}

	@Override
	public int changeShowState(Map<String, Object> paramMap) {
		return facetGroupItemDao.changeShowState(paramMap);
	}

	@Override
	public void delete(String id) {
		facetGroupItemDao.delete(id);
	}
}
