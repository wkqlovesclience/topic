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
	 * ����¥��id ��ѯ����
	 */
	PaginatedList<ItemCategory> selectAllCategoryByfloorid(Map<String, Object> search);
	/*
	 * ���ݷ���id ��ѯ������Ϣ
	 */
	ItemCategory selectCategoryById(Integer id);
	/*
	 * ��ӷ������
	 */
	int save(ItemCategory entity);
	/*
	 * ɾ����������
	 */
	int deleteByCategoryById(Integer id);
	/*
	 * �޸ķ�������
	 */
	int updateItemCategoryById(ItemCategory entity);
}
