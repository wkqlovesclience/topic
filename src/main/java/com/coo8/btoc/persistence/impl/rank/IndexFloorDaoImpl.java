package com.coo8.btoc.persistence.impl.rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
 
import com.coo8.btoc.model.rank.IndexFloor;
import com.coo8.btoc.persistence.interfaces.rank.IIndexFloorDAO;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
@SuppressWarnings("unchecked")
public class IndexFloorDaoImpl extends SqlMapClientDaoSupport  implements IIndexFloorDAO{

	/*
	 * 获取所有首页楼层分类(non-Javadoc)
	 * @see com.coo8.btoc.persistence.interfaces.rank.IHomeFloorDAO#selectAllHomeFloor(java.util.Map)
	 */
	@Override
	public PaginatedList<IndexFloor> selectAllIndexFloor(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"IndexFloor.findPage.count", paramMap);
		if (o == null) return null;

		PaginatedList<IndexFloor> paginatedArrayList = new PaginatedArrayList<IndexFloor>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

	
		List<IndexFloor> list = this.getSqlMapClientTemplate().queryForList(
				"IndexFloor.findPage.list", paramMap, paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}
	/*
	 * 添加首页楼层
	 */
	@Override
	public int save(IndexFloor entity) {
		Object o = this.getSqlMapClientTemplate().insert("IndexFloor.insert",entity);
		if(null != o){
			return Integer.valueOf(o.toString());
		}else{
			return -1;
		}
	}
	/*
	 * 删除首页楼层
	 */
	@Override
	public int deleteByIndexFloorId(Integer id) {
		return this.getSqlMapClientTemplate().delete("IndexFloor.delete",id);

	}
	/*
	 * 修改首页楼层
	 */
	@Override
	public int updateIndexFloorById(IndexFloor entity) {
		return this.getSqlMapClientTemplate(). update("IndexFloor.update",entity);
	}
	/*
	 *根据首页楼层id查询详细信息 
	 */
	@Override
	public IndexFloor selectIndexFloorById(Integer id) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("id", id);
		List<IndexFloor> ret = this.getSqlMapClientTemplate().queryForList("IndexFloor.getIndexFloorById", search);
		if(null != ret && !ret.isEmpty()){
			return ret.get(0);
		}
		return null;
	}        
	
}
