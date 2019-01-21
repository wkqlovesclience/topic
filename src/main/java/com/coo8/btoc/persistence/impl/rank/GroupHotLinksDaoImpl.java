/**
 * 
 */
package com.coo8.btoc.persistence.impl.rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.rank.GroupHotLinks;
import com.coo8.btoc.persistence.interfaces.rank.IGroupHotLinksDAO;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;

/**
 * @author wangfufu
 *
 */
public class GroupHotLinksDaoImpl  extends SqlMapClientDaoSupport  implements IGroupHotLinksDAO{
	/*
	 * 获取所有首页楼层分类(non-Javadoc)
	 * @see com.coo8.btoc.persistence.interfaces.rank.IHomeFloorDAO#selectAllHomeFloor(java.util.Map)
	 */
	@Override
	public PaginatedList<GroupHotLinks> selectAllGroupHotLinks(Map<String, Object> paramMap)
	{
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"GroupHotLinks.findPage.count", paramMap);
		if (o == null) return null;

		PaginatedList<GroupHotLinks> paginatedArrayList = new PaginatedArrayList<GroupHotLinks>(
				Integer.parseInt(o.toString()),
				(Integer) paramMap.get("pageNumber"),
				(Integer) paramMap.get("pageSize"));

	
		@SuppressWarnings("unchecked")
		List<GroupHotLinks> list = this.getSqlMapClientTemplate().queryForList(
				"GroupHotLinks.findPage.list", paramMap, paginatedArrayList.getStartPos(),
				paginatedArrayList.getPageSize());

		if (list != null) paginatedArrayList.addAll(list);

		return paginatedArrayList;
	}
	/*
	 * 添加首页楼层
	 */
	@Override
	public int save(GroupHotLinks entity) {
		Object o = this.getSqlMapClientTemplate().insert("GroupHotLinks.insert",entity);
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
	public int deleteByGroupHotLinksId(Integer id) {
		return this.getSqlMapClientTemplate().delete("GroupHotLinks.delete",id);

	}
	/*
	 * 修改首页楼层
	 */
	@Override
	public int updateGroupHotLinksById(GroupHotLinks entity) {
		return this.getSqlMapClientTemplate(). update("GroupHotLinks.update",entity);
	}
	/*
	 *根据首页楼层id查询详细信息 
	 */
	@Override
	public GroupHotLinks selectGroupHotLinksById(Integer id) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("ID", id);
		List<GroupHotLinks> ret = this.getSqlMapClientTemplate().queryForList("GroupHotLinks.getgrouphotlinksById", search);
		if(null != ret && !ret.isEmpty()){
			return ret.get(0);
		}
		return null;
	}        
	
}
