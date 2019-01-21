package com.coo8.item.persistence.interfaces;

 
import java.util.Map;

 
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.item.model.ItemCategory; 
import com.coo8.item.model.ItemFloor;
/**
 * 
 *
 * @author jiaziwei
 */
public interface IItemDAO {
	
	public String saveFloor(ItemFloor itemFloor);
	
    public int updateFloor(ItemFloor itemFloor);
	
	public int deleteFloor(Integer id);
	
	public ItemFloor getById(Integer id);
	
	public PaginatedList<ItemFloor> findByMap(Map<String, Object> search);
	
	public int deleteCategoryByFloorId(Integer floorId); 

	/*
	 * 根据楼层id 查询分类
	 */
	PaginatedList<ItemCategory> selectAllCategoryByfloorid(Map<String, Object> search);
	/*
	 * 根据分类id 查询分类信息
	 */
	ItemCategory selectCategoryById(Integer id);
	/*
	 * 添加分类管理
	 */
	int save(ItemCategory entity);
	/*
	 * 删除分类内容
	 */
	int deleteByCategoryById(Integer id);
	/*
	 * 修改分类内容
	 */
	int updateItemCategoryById(ItemCategory entity);
}
