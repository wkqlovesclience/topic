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

import com.coo8.btoc.business.interfaces.rank.IFacetgroupManager;
import com.coo8.btoc.model.rank.FacetGroup;
import com.coo8.btoc.persistence.interfaces.rank.IFacetGroupDao;
import com.coo8.btoc.util.pages.PaginatedList;

public class FacetGroupManagerImpl implements IFacetgroupManager
{
	private IFacetGroupDao facetGroupDao;

	@Override
	public FacetGroup getById(String id)
	{
		return facetGroupDao.getById(id);
	}

	@Override
	public PaginatedList<FacetGroup> list(Map<String, Object> paramMap)
	{
		return facetGroupDao.list(paramMap);
	}
	@Override
	public List<FacetGroup> facetGrouplist(Map<String, Object> paramMap)
	{
		return facetGroupDao.facetGrouplist(paramMap);
	}

	@Override
	public void add(FacetGroup facetGroup)
	{
		facetGroupDao.add(facetGroup);
	}

	@Override
	public void delete(List<String> ids)
	{
		facetGroupDao.delete(ids);
	}

	@Override
	public void update(FacetGroup facetGroup)
	{
		facetGroupDao.update(facetGroup);
	}

	public IFacetGroupDao getFacetGroupDao()
	{
		return facetGroupDao;
	}

	public void setFacetGroupDao(IFacetGroupDao facetGroupDao)
	{
		this.facetGroupDao = facetGroupDao;
	}

	@Override
	public int changeShowState(Map<String, Object> paramMap) {
		return facetGroupDao.changeShowState(paramMap);
	}

}
