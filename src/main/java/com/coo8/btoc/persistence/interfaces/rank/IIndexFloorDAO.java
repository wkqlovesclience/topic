package com.coo8.btoc.persistence.interfaces.rank;

import java.util.Map;

import com.coo8.btoc.model.rank.IndexFloor;
import com.coo8.btoc.util.pages.PaginatedList;


/**
 * @author 王福富
 * @date 2017-6-2
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc
 */
public interface IIndexFloorDAO {
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
