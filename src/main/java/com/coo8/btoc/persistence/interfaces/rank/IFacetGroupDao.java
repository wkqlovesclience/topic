/**
 * @author tianyu-ds
 * @date 2014-2-20
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 
 */
package com.coo8.btoc.persistence.interfaces.rank;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.rank.FacetGroup;
import com.coo8.btoc.util.pages.PaginatedList;

public interface IFacetGroupDao
{
	public FacetGroup getById(String id);

	public PaginatedList<FacetGroup> list(Map<String, Object> paramMap);

	public void add(FacetGroup facetGroup);

	public void delete(List<String> ids);

	public void update(FacetGroup facetGroup);
	
	public int changeShowState(Map<String, Object> paramMap);

	public List<FacetGroup> facetGrouplist(Map<String, Object> paramMap);
}
