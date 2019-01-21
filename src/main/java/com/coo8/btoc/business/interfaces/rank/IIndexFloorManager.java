package com.coo8.btoc.business.interfaces.rank;

import java.util.Map;
import com.coo8.btoc.model.rank.IndexFloor;
import com.coo8.btoc.util.pages.PaginatedList;

public interface IIndexFloorManager {
	/*
	 * 查询所有首页楼层分类
	 */
	PaginatedList<IndexFloor> selectAllIndexFloor(Map<String, Object> search);

	/*
	 * 根据分类id 查询分类信息
	 */
	IndexFloor selectIndexFloorById(Integer id);

	/*
	 * 添加楼层分类信息
	 */
	int save(IndexFloor entity);

	/*
	 * 删除楼层分类信息
	 */
	int deleteByIndexFloorId(Integer id);

	/*
	 * 修改楼层分类信息
	 */
	int updateIndexFloorById(IndexFloor entity);
}
