package com.coo8.btoc.persistence.interfaces.items;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
import com.coo8.btoc.model.attribute.Attributeenumvalue;
import com.coo8.btoc.model.catalog.BtoCCatalog;
import com.coo8.btoc.model.catalog.CategoryBrandBase;
import com.coo8.btoc.model.items.BtoCItems;
import com.coo8.btoc.model.items.BtocItemDesc;
import com.coo8.btoc.model.items.ItemQueryParam;
import com.coo8.btoc.model.items.SelectConditions;
import com.coo8.btoc.model.itemsfitting.ItemsFitting;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AladdinKeywords;

public interface IItemsDAO {
	//���������ʹ���ǰ�ļ۸�
	public int updateItemsQiangPrice(int id);
	//�����ۺϻ����ֶ�
	public int updateItemsSubTotalScore(int id,double subtotalscore);
	//�����Ƿ���Ʒ
	public int updateItemMainPushById(BtoCItems items);
	//ͨ��jobֹͣ������Ʒ
	public int updateMainPushByJob(BtoCItems items);
	//ͨ��jobֹͣ��Ʒ
	public int updateIsNewByJob(int id);
	//�����Ƿ���Ʒ
	public int updateItemIsNewById(BtoCItems items);
	
	//�ۺ�����
	public int findItemsInStateShangCount();
	public List<BtoCItems> findItemsInStateShang(int pageNo,int pageSize);
	
	
	//ͨ��itemid�õ������Ʒ�������Ծɻ���
	public List<BtoCItems> findYiJiuHuanXinByItemId(String itemid);
	//�����޸Ķ���Ծɻ��µ���Ʒ
	public int updateYiJiuStatusById(BtoCItems items);
	
	//������Ʒid�õ�����
	public int findItemsPointById(int id);
	
	//������Ʒid�޸���Ʒ�޹�����
	public int updateLimitCountById(BtoCItems items);
	
	//������Ʒid�޸���Ʒ����
	public int updatePointById(BtoCItems items);
	//ͨ������id�������е���Ʒ��id��List
	public List<String> findItemsIdByDefinitionid(int definitionid);
	//�޸�������Ʒ��maininfo�ֶΣ����޸Ĳ��������ƻ��߲��������ʱ��
	public void modifyItemsParameter(BtoCItems items);
	//������ѡ����Ʒ
	public PaginatedList<BtoCItems> findItemsByPriority(SelectConditions selconditions,String ASCDESC,int pageNo,int pageSize);
	//�ж���Ʒ�Ƿ��Ծɻ���
	public int findItemsIsTMById(int id);
	//����������������Ʒ
	public List<BtoCItems> findExportItemsByConditions(SelectConditions selconditions,List<Integer> catalogIdList);
	//��id������Ʒ
	public List<BtoCItems> findExportItemsByIdList(List<Integer> idList);
	//��brandid ����Ʒ
	public List<String> findItemsBybrandid(int brandid);
	
	//��id����Ʒ������ǰ̨(���ز�����)
	public BtoCItems findItemsToFrontById(int id);
	//��id����Ʒ������ǰ̨
	public BtoCItems findFrontItemsById(int id);
	
//  �� itemid ��ѯԭ��Ʒ specialStatus&8=0
	public BtoCItems findAllItemsByPid(String itemid);
	
	//��itemid��productname��updater ����Ʒ
	public PaginatedList<BtoCItems> findItemsByKeywords(String itemid,String productname,String operator,int pageNo,int pageSize, int id);
	
	//����̬���֣�value ��ѯ��Ʒ
	public PaginatedList<BtoCItems> findScatByName(String definitionname,String scatname,String scatvalue,int pageNo,int pageSize);
	//���۸������ѯ��Ʒ
	public PaginatedList<BtoCItems> findScatByPrice(int catalogid,double minprice,double maxprice,int pageNo,int pageSize);
	
	//��name����������
	public List<BtoCItems> findItemsByName(String name);
	//��ѯ��̬���������
	public Object findParamColumnsValue(BtoCItems btocitems);
	//��ѯ��̬���������,������������Ʒ����
	public Object findItemsParameter(String itemid,String definitionname);
	//��ѯ�������Ʒ
	public PaginatedList<BtoCItems> findFitConditions(String productname,int catalogid,int brandid,int pageNo,int pageSize);
	//���ݾ���Ĳ�ѯ��������ѯ�������Ʒ�������з�ҳ
	public PaginatedList<BtoCItems> findItemsByConditions(SelectConditions selconditions,int pageNo,int pageSize,String showstatus);
	//��ѯ���е���Ʒ�����з�ҳ
	public PaginatedList<BtoCItems> findAllItems(int pageNo,int pageSize, String showstatus);
	
	public List<String> findItemsByCatalog(List<Integer> catalogIdList,int catalogid,int brandid);
	//������Ʒ��ź���Ʒ�ͺŲ�ѯitem
	public PaginatedList<BtoCItems> findItemsByBrand(String itemid,String name,int catalogid,int brandid,int pageNo,int pageSize);
	//���ݴ����ID�����з�����Ʒ
	public PaginatedList<BtoCItems> findItemsByBigCatId(List<Integer> catalogIdList,int pageNo,int pageSize, String showstatus);
	//��ѯС�����µ�������Ʒ
	public List<BtoCItems> findItemsBycatalogid(int catalogid,int brandid);
	//��ѯ�ƶ�����Ʒ
	public BtoCItems findItemsMoveItemsById(int id);
	
	//��ѯ��Ʒ��������
	public BtoCItems findItemsAllColumnsById(int id);
	//  �� id ��ѯ��Ʒ
	public BtoCItems findAllItemsById(int id);
	
	// �� status ��ѯ��ƷID�б�
	public List<Integer> findAllItemsByStatus(int status);
    //  �� itemid ��ѯ��Ʒ
	public List<BtoCItems> findAllItemsByItemId(String itemid);
    
	//  �� name ��ѯ��Ʒ
	public List<BtoCItems> findAllItemsByName(String name);
	
    //  �� id��ѯ��Ʒ�۸�
	public BtoCItems findPriceById(int itemid);
    //  ��ѯ��Ʒ���
	public List<BtoCItems> findPeiByStatus(List fittingIdList);
	
	
	//������Ʒ
	public int insert(BtoCItems items);
	
	//���븴�ƶ�̬�������
	public int insertCopyAttributes(int id,String definitionname,Map mapValue);
	
	//�����Ʒ���ԣ���̬��
	public int insertItemsAttributes(BtoCItems items,List<Attributeenumvalue> attrivalueList);
	//�޸���Ʒ����
	public int updateItemsMoneyback(BtoCItems items);
	//�޸���Ʒ�۸�
	public int updateOriginalprice(BtoCItems items);
	//�޸���Ʒ�û�����
	public int updateItemsByUsergrade(int id,float usergrade);
	
	//�޸���Ʒ�û�����,����ӵģ�����÷ʹ��
	public int updateItemsByUsergrade(String itemid,float usergrade);
	
	//�޸���Ʒ�����۸�
	public int updateSnatchbuyPrice	(int id ,double originalprice,double moneyback ,int showpic);
	//�޸���Ʒ��������̬��
	public int updateItemsAttributes(BtoCItems items,List<Attributeenumvalue> attrivalueList);
	//�޸���Ʒ����
	public int updateParam(BtoCItems items);
	//�޸���Ʒ
	public int update(BtoCItems items);
	//�����޸���Ʒ�Ĵ�����
	public int updatePriceGiftById(BtoCItems items);
	//�޸���Ʒ�۸�
	public int updatePrice(BtoCItems items);
	//�޸���Ʒ״̬
	public int updateStatus(BtoCItems items);
	//�޸���Ʒ����
	public int updateGift(BtoCItems items);
	//��id�޸���Ʒ����
	public int updatePriorityById(String updater,Date updatetime,int id,int priority);
	//�޸���Ʒ����
	public int updatePriority(String updater,Date updatetime,int downid,int downprio);
	//�޸���Ʒ�ķ���
	public int updateItemsCatalog(BtoCItems items);
	//�޸���Ʒ����
	public int updateTemplate(int itemid);
	// �޸���Ʒmaininfo
	public int updateMaininfo(BtoCItems items);
	//���뵽��̬�������
	public int insertDynamicTable(int id,String itemid,String definitionname);
	
	//���뵽��̬�������,�ƶ���Ʒʱʹ��
	public int insertMoveItemsValue(String sql);
	
	//ɾ����̬�������
	public int deleteItemsAttributes(int id,String definitionname);
	
	//ɾ����̬������ݣ������ض�̬�������
	public Object deleteAttributesByItemid(String itemid,String definitionname);
	//ɾ����Ʒ
	public int delete(int id);
	//��Ӷ����Ʒ
	public int insertManyPriceItem(BtoCItems item);
	/**
	 * dshh add
	 * ��ѯ�Ƽ���Ʒ.
	 * @return
	 */
	public List<BtoCItems> getRecommended (Map map);
	
	List<BtoCItems> getItemsByIds(String ids);
	
	void publishAllProduct(int startnum , int pagesize);
	int queryOnlineItemCount();
	List<Integer> queryOnlineItemId(int startnum , int pagesize);
	
	/**
	 * �������Ʒ�Ƿ����
	 * @param itemid
	 * @param provinceid
	 * @param cityid
	 */
	public boolean isExsitManyPriceItem(int itemid, int provinceid, int cityid);
	
	/**
	 * ��ѯ�����Ʒ��������ԭ��Ʒ��
	 * @param itemid ����ID
	 * @return
	 */
	public List<BtoCItems> getManyPriceItems(int itemid);
	
	/**
	 * �޸���Ʒ��ʾ״̬
	 * @param itemid ����ID
	 */
	public void updateItemShowStatus(int itemid);
	
	/**
	 * �޸Ķ����Ʒ�۸�
	 * @param itemid
	 * @param price
	 * @return
	 */
	public boolean updateManyPriceItemPrice(int itemid, double price);
	
	/**
	 * �����б�ҳ��ʾ
	 * @param itemid
	 * @return 1�ɹ���-1ʧ�ܣ�0δ�ϼ�
	 */
	public int updateShowManyPriceItem(int itemid, List<Integer> ids);
	
	/**
	 * �����ն�ҳ��ʾ
	 * @param itemid
	 * @return
	 */
	public boolean updateShowSpecialItem(int itemid);
	
	/**
	 * ��ѯ�����Ʒ�ն�ҳ��ʾ����������һ��״̬Ϊ1��
	 * @param itemid
	 * @return
	 */
	public int getEndPageShowNum(int itemid);
	
	/**
	 * ��ѯ����б�ҳǰ̨��ʾ����
	 * @param itemid
	 * @return
	 */
	public int getListPageShowNum(int itemid);
	
	/**
	 * ȡ���ն�ҳǰ̨��ʾ
	 * @param itemid
	 */
	public void escEndPageShow(int itemid);
	
	/**
	 * ͬ���ն�ҳ��ʾ��ƷID��������Ʒ��
	 * @param itemid
	 */
	public void associatedShowSpecialItemid(int itemid);
	
	/**
	 * ͬ���ն�ҳ��ʾ��ƷID��������Ʒ����Ӷ��ʱ��
	 * @param newid
	 * @param oldid
	 */
	public void synchronizationDataToSpecial(int newid, int oldid);
	
	/**
	 * ��ѯ�����Ʒ
	 * @param itemid ����ID
	 * @return
	 */
	public List<BtoCItems> getManyPriceItemsForInterface(String itemid);
	
	/**
	 * ֹͣ����ԭ��Ʒ�б���ʾ
	 * @param itemid
	 */
	public void updateOriginalItemShowStatus(int itemid);
	
	/**
	 * ���������Ʒid����̬��
	 * @param itemid ԭid
	 * @param id ��id
	 */
	public void updateIidToDynamicTable(int itemid, int id);
	
	/**
	 * ͳ��ԭ��Ʒ�Ķ����Ʒ����
	 * @param itemid
	 * @return
	 */
	public int countManyPriceItemNum(int itemid);
	
	/**
	 * �õ�������Ʒ��������Ʒ
	 * @param id
	 * @return
	 */
	public List<BtoCItems> getItemAndManyPriceItems(int id);
	
	public BtoCItems getSaleinfoAndMoneybackByIid(int id);
	
	/**
	 * �����ն�ҳ��ʾ����ͬ��iid���������Ա�
	 * @param itemid
	 */
	public void setEndPageShow(int itemid);
	
	/**
	 * �õ���Ʒ�Ķ���name
	 * @param itemid
	 * @return
	 */
	public Object getItemCatalogId(int itemid);
	
	/**
	 * ȡ�����й�����Ʒ�Ķ�̬��ǰ̨��ʾ
	 * @param maps
	 */
	public void escAllShowStatusToDynamicTable(Map<String, Object> maps);
	
	/**
	 * ���õ�ǰ�����ƷID����̬��
	 * @param args
	 */
	public void setShowStatusToDynamicTable(Map<String, Object> args);
	
	/**
	 * �����µĶ��id����̬��
	 * @param itemid
	 */
	public void setNewShowStatusItemidToDynamicTable(Map<String, Object> args);
	
	/**
	 * ȡ�����У�����Լ���ʾ
	 * @param itemid
	 */
	public void escAllAndAddShowNowItem(int itemid);
	
	/**
	 * �����б�ҳ��ʾ��ֹͣԭ��Ʒ������ն�ҳ��ʾ����ͬ�����ݵ��������Ա�
	 * @param itemid
	 */
	public void updateListPageItemsShow(int itemid);
	
	/**
	 *  ���й����������Ʒ���ϼܵģ�
	 * @param itemid
	 * @return
	 */
	public List<Integer> getManyPriceAndSpecialAttrItemid(int itemid);
	
	/**
	 * ��ǰ��Ʒ���ϼܵĶ����Ʒ����
	 * @param parseInt
	 * @return
	 */
	public int countShelvesManyPriceItemNum(int parseInt);
	
	/**
	 * �õ�ԭ��Ʒ�������Ʒ(�ϼܵ�)
	 * @param itemid
	 * @return
	 */
	public List<Integer> getShelvesItemAndManyPriceItems(int itemid);
	
	/**
	 * �õ���Ʒ�Լ������Ʒ
	 * @param id
	 * @return
	 */
	public List<Integer> getShelvesItemAndManyPriceItemsByIds(String[] id);
	
	/**
	 * ����Ƿ��й�������Ʒ
	 * @param ids
	 * @return
	 */
	public boolean isSpecialAttrItem(String[] ids);
	
	/**
	 * �õ���ǰ��Ʒ�����������е�productid
	 * @param id
	 * @return
	 */
	public int getProductidByItemid(int id);
	
	/**
	 * �õ���ǰ��Ʒ���������Լ�����ֵ
	 * @param id
	 * @return
	 */
	public Map<String, Set> getSpecialAttributeValue(int id);
	
	/**
	 * ɾ����������
	 * @param id
	 */
	public void deleteSpecialAttributeBuItemid(int id);
	
	/**
	 * �õ�����������ɾ����ǰ��Ĺ�����Ʒ����
	 * @param productid
	 * @return
	 */
	public int countSpecialItems(int productid);
	
	/**
	 * ɾ��������������
	 * @param productid
	 */
	public void deleteAllSpecialAttrributeByItemid(int productid);
	
	/**
	 * ɾ����ǰ��Ʒ�����������еĹ������������������ֻʣһ����Ʒ��ȫ��ɾ����
	 * @param id
	 */
	public void deleteNowSpecialAttribute(int id);
	
	/**
	 * ������Ʒ������ status ���жϣ�
	 * @param id
	 */
	public void publishItem(BtoCItems item);
	
	/**
	 * ��ԭԭ��ƷΪ��ͨ��Ʒ
	 * @param id ԭ��Ʒ������ƷID
	 */
	public void restoreTheDynamicTable(int id);
	
	/**
	 * ���ö�̬��ǰ̨��ʾ
	 * @param item
	 */
	public void setShowStatusToDynamicTable(BtoCItems item);
	
	/**
	 * ������Ʒ��status
	 * @param id
	 * @param i ״ֵ̬
	 */
	public void updateItemStatus(int id, int i);
	
	/**
	 * ������Ʒ��������status
	 * @param btocitems
	 */
	public void updateParamNoStatus(BtoCItems btocitems);
	
	/**
	 * �޸Ķ�̬��status
	 * @param args
	 */
	public void updateTheDynamicTableStatus(Map<String, Object> args);
	
	/**
	 * �õ��ϼܵġ�������Ʒ����ۡ�������
	 * @param id
	 * @return
	 */
	public List<Integer> getPromotionalGoodsByManyPriceAndSpecialAttr(int id);
	
	/**
	 * ȡ����Ʒ�Ĵ���״̬
	 * @param ids ��Ʒid's
	 */
	public void escPromotionalGoodsStatus(List<Integer> ids);
	
	/**
	 * �õ�ǰ̨��ʾ��itemid
	 * @param ids
	 * @return
	 */
	public int getShowStatusItem(List<Integer> ids);
	
	/**
	 * �����Ʒ����״̬
	 * @param showid
	 */
	public void setPromotionalGoodsStatusForShowItem(int showid);

	/**
	 * ��ѯ�����������Ե���Ʒ
	 * @param args
	 * @return 
	 */
	public List<BtoCItems> searchSpecialItem(Map<String, Object> args);
	public List<BtoCItems> mobilePrice();
	/**
	 * �޸������ ״̬
	 * @param fit
	 */
	public void modifyStatus(ItemsFitting fit);
	
	/**
	 * ��ѯ�����������Ե���Ʒ����
	 * @param args
	 * @return 
	 */
	public int countSearchSpecialItem(Map<String, Object> args);

	/**
	 * �õ���Ʒ���������Լ�����ֵ
	 * @param args
	 * @return
	 */
	public Map<String, String> getItemSpecialAttrValMap(Map<String, Object> args);

	/**
	 * �õ�����������
	 * @param args
	 * @return
	 */
	public List<String> getSpecialAttrColomn(Map<String, Object> args);

	/**
	 * ��ѯ���������Ե���Ʒ
	 * @param id
	 * @return
	 */
	public List<BtoCItems> getSpecialItem(int id);
	
	List<BtoCItems> queryCommonItems(ItemQueryParam param);
	/**
	 * �������ID��ѯ�����
	 * @param presentId0
	 * @return
	 */
	public ItemsFitting findFitByItemid(Integer presentId0);
	
	/**
	 * ��ò�Ʒ����
	 * @param catalogId
	 * @return
	 */
	public List<CategoryBrandBase> getCatalogListById(Integer catalogId);
	/**
	 * ���ݷ���ID�����Ʒ---ֻȡ1��
	 * @param catalogId
	 * @return
	 */
	public List<BtoCItems> getItemListByCid(Integer catalogId);
	
	public List<CategoryBrandBase> getCatalogListByIdMap(Integer[] catalogArr);
	
	/**
	 * ��Ʒ������Ϣ����Ǩ�ƣ���֮ǰ��BTOC_ITEM ��Ǩ�� ��BTOC_DESC
	 */
	public BtocItemDesc findItemDescById(Integer id);
	
}
