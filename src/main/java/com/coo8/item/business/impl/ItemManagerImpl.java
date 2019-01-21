package com.coo8.item.business.impl;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.item.business.interfaces.IItemManager;
import com.coo8.item.model.ItemCategory;
import com.coo8.item.model.ItemFloor;
import com.coo8.item.persistence.interfaces.IItemDAO;

/**
 * 
 *
 * @author jiaziwei
 */

public class ItemManagerImpl implements IItemManager {

	private IItemDAO itemDAO;

	@Transactional
	@Override
	public String saveFloor(ItemFloor itemFloor) {
		return itemDAO.saveFloor(itemFloor);
	}

	@Transactional
	@Override
	public int updateFloor(ItemFloor itemFloor) {
		return itemDAO.updateFloor(itemFloor);
	}

	@Transactional
	@Override
	public int deleteFloor(Integer id) {
		itemDAO.deleteCategoryByFloorId(id);
		return itemDAO.deleteFloor(id);
	}

	@Transactional(readOnly = true)
	@Override
	public ItemFloor getById(Integer id) {
		return itemDAO.getById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public PaginatedList<ItemFloor> findByMap(Map<String, Object> search) {
		return itemDAO.findByMap(search);
	}
	/*
	 * 根据楼层id 查询分类
	 */ 
	@Override
	public PaginatedList<ItemCategory> selectAllCategoryByfloorid(Map<String, Object> search) {
		 return itemDAO.selectAllCategoryByfloorid(search);
	} 
	/*
	 *根据分类id 查询分类信息
	 */
	@Override
	public ItemCategory selectCategoryById(Integer id) {
	 return itemDAO.selectCategoryById(id);
	} 
	/*
	 * 添加分类管理
	 */
	@Override
	public int save(ItemCategory entity) {
		 return itemDAO.save(entity);
	} 
	/*
	 * 删除分类内容
	 */
	@Override
	public int deleteByCategoryById(Integer id) {
		 return itemDAO.deleteByCategoryById(id);
	} 
	/*
	 * 修改分类内容
	 */
	@Override
	public int updateItemCategoryById(ItemCategory entity) {
	 return itemDAO.updateItemCategoryById(entity);
	}
	public com.coo8.item.persistence.interfaces.IItemDAO getItemDAO() {
		return itemDAO;
	}
	public void setItemDAO(com.coo8.item.persistence.interfaces.IItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	
}
