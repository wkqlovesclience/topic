/**
 * 
 */
package com.coo8.btoc.persistence.interfaces.rank;

import java.util.Map;

import com.coo8.btoc.model.rank.GroupHotLinks;
import com.coo8.btoc.util.pages.PaginatedList;

/**
 * @author wangfufu
 *
 */
public interface IGroupHotLinksDAO {
	/*
	 * 查询排行榜热门链接
	 */
	PaginatedList<GroupHotLinks> selectAllGroupHotLinks(Map<String, Object> search);

	/*
	 * 根据分类id 查询
	 */
	GroupHotLinks selectGroupHotLinksById(Integer id);

	/*
	 * 添加热门链接
	 */
	int save(GroupHotLinks entity);

	/*
	 * 删除热门链接信息
	 */
	int deleteByGroupHotLinksId(Integer id);

	/*
	 * 修改热门信息
	 */
	int updateGroupHotLinksById(GroupHotLinks entity);
}
