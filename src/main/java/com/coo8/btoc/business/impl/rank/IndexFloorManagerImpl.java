/**
 * 
 */
package com.coo8.btoc.business.impl.rank;

import java.util.Map;  
import com.coo8.btoc.business.interfaces.rank.IIndexFloorManager;
import com.coo8.btoc.model.rank.IndexFloor;
import com.coo8.btoc.persistence.interfaces.rank.IIndexFloorDAO;
import com.coo8.btoc.util.pages.PaginatedList;

/**
 * @author wangfufu
 *
 */
public class IndexFloorManagerImpl implements IIndexFloorManager {
	
	private IIndexFloorDAO indexFloorDao;

	 
	@Override
	public PaginatedList<IndexFloor> selectAllIndexFloor(
			Map<String, Object> search) {
		return indexFloorDao.selectAllIndexFloor(search);
	}
   /*
    * 根据id查询信息 
    */
	@Override
	public IndexFloor selectIndexFloorById(Integer id) {
		return indexFloorDao.selectIndexFloorById(id);
	}

	/*
	 * 添加楼层分类信息
	 */
	@Override
	public int save(IndexFloor entity) {
		return indexFloorDao.save(entity);
	}

	/*
	 * 删除楼层分类信息
	 */
	@Override
	public int deleteByIndexFloorId(Integer id) {
		return indexFloorDao.deleteByIndexFloorId(id);
	}

	/*
	 * 修改楼层分类信息
	 */
	@Override
	public int updateIndexFloorById(IndexFloor entity) {
		return indexFloorDao.updateIndexFloorById(entity);
	}
	
	public IIndexFloorDAO getIndexFloorDao() {
		return indexFloorDao;
	}
	public void setIndexFloorDao(IIndexFloorDAO indexFloorDao) {
		this.indexFloorDao = indexFloorDao;
	}
	 
	
}