/**
 * @author tianyu-ds
 * @date 2014-2-20
 * @project_name topic_trunk
 * @company �����������޹�˾
 * @desc 
 */
package com.coo8.btoc.business.interfaces.rank;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.rank.FacetGroupItem;
import com.coo8.btoc.util.pages.PaginatedList;

import scala.util.parsing.combinator.testing.Str;

public interface IFacetgroupItemManager
{
	public FacetGroupItem getById(String id);
	
	public PaginatedList<FacetGroupItem> list(Map<String, Object> paramMap);
	
	public void add(FacetGroupItem facetGroupItem);

	public void delete(String id);
	
	public void update(FacetGroupItem facetGroupItem);
	
	public int changeShowState(Map<String, Object> paramMap);

	public List<FacetGroupItem> listAll(Map<String, Object> paramMap);
}
