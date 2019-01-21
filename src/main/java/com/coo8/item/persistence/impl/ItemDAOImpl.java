package com.coo8.item.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.item.model.ItemCategory;
import com.coo8.item.model.ItemFloor;
import com.coo8.item.persistence.interfaces.IItemDAO;
  
/**
 * 
 *
 * @author jiaziwei
 */
public class ItemDAOImpl extends SqlMapClientDaoSupport  implements IItemDAO {

	@Override
	public String saveFloor(ItemFloor itemFloor) {
		Object obj = getSqlMapClientTemplate().insert(
				"ItemFloor.insert", itemFloor);
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	@Override
	public int updateFloor(ItemFloor itemFloor) {
		return getSqlMapClientTemplate().update("ItemFloor.update",
				itemFloor);
	}

	@Override
	public int deleteFloor(Integer id) {
		return getSqlMapClientTemplate().delete("ItemFloor.delete", id);
	}

	@Override
	public ItemFloor getById(Integer id) {
		return (ItemFloor) getSqlMapClientTemplate().queryForObject(
				"ItemFloor.getById", id);
	}

	@Override
	public PaginatedList<ItemFloor> findByMap(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("ItemFloor.findPage.count", search);
		if(o==null)
			return null;
		PaginatedList<ItemFloor> paginatedArrayList = new PaginatedArrayList<ItemFloor>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<ItemFloor> list = this.getSqlMapClientTemplate().queryForList("ItemFloor.findPage", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	}

	@Override
	public int deleteCategoryByFloorId(Integer floorId) {
		return getSqlMapClientTemplate().delete("ItemCategory.deleteByFloorId", floorId);
	}
	/*
	 * ����¥��id ��ѯ����
	 */  
	//@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<ItemCategory> selectAllCategoryByfloorid(Map<String, Object> search) {
		Object o  =  this.getSqlMapClientTemplate().queryForObject("ItemCategory.count", search);
		if(o==null)
			return null;
		PaginatedList<ItemCategory> paginatedArrayList = new PaginatedArrayList<ItemCategory>(Integer.parseInt(o.toString()), (Integer)search.get("pageNumber"),(Integer)search.get("pageSize"));
		List<ItemCategory> list = this.getSqlMapClientTemplate().queryForList("ItemCategory.list", search, paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if (list != null) 
			paginatedArrayList.addAll(list);
		return paginatedArrayList; 
	} 
	/*
	 *���ݷ���id ��ѯ������Ϣ
	 */
	@Override
	public ItemCategory selectCategoryById(Integer id) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("id", id);
		List<ItemCategory> ret = this.getSqlMapClientTemplate().queryForList("ItemCategory.selectCategoryById", search);
		if(null != ret && !ret.isEmpty()){
			return ret.get(0);
		}
		return null;
	} 
	/*
	 * ��ӷ������
	 */
	@Override
	public int save(ItemCategory entity) {
		Object o = this.getSqlMapClientTemplate().insert("ItemCategory.insert",entity);
		if(null != o){
			return Integer.valueOf(o.toString());
		}else{
			return -1;
		}
	}

	/*
	 * ɾ����������
	 */
	@Override
	public int deleteByCategoryById(Integer id) {
		return this.getSqlMapClientTemplate().delete("ItemCategory.deleteById",id);

	}

	/*
	 * �޸ķ�������
	 */
	@Override
	public int updateItemCategoryById(ItemCategory entity) {
		return this.getSqlMapClientTemplate(). update("ItemCategory.update",entity);
	}
	
	

}
