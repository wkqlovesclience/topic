package com.coo8.btoc.business.interfaces.items;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.attribute.Attributeenumvalue;
import com.coo8.btoc.model.attribute.SpecialAttribute;
import com.coo8.btoc.model.definition.BtoCDefinition;
import com.coo8.btoc.model.items.BtoCItems;
import com.coo8.btoc.model.items.ItemQueryParam;
import com.coo8.btoc.model.items.SelectConditions;
import com.coo8.btoc.model.itemsfitting.ItemsFitting;
import com.coo8.btoc.util.pages.PaginatedList;

public interface IItemsManager {
	//���������ʹ���ǰ�ļ۸�
	public int updateItemsQiangPrice(int id);
	//�����ۺϻ����ֶ�
	public int updateItemsSubTotalScore(int id,double subtotalscore);
	//�����Ƿ���Ʒ
	public int updateItemMainPushById(BtoCItems items);
	//ͨ��jobֹͣ������Ʒ
	public int updateMainPushByJob(BtoCItems items);
	//ɾ���ֹ�˾���Ƶ�job
	public void deleteBranchCompanyMainPush(int id,String branchpush);
	//ͨ��jobֹͣ��Ʒ
	public int updateIsNewByJob(int id);
	//�����Ƿ���Ʒ
	public int updateItemIsNewById(BtoCItems items,Date endDate);
	
	//ɾ�����������Ķ��
	public int deleteMulPriceById(int id,String updater);
	//�ۺ�����
	public int findItemsInStateShangCount();
	public List<BtoCItems> findItemsInStateShang(int pageNo,int pageSize);
	
	//������Ʒid�õ�����
	public int findItemsPointById(int id);
	
	//������Ʒid�޸���Ʒ�޹�����
	public int updateLimitCountById(BtoCItems items);
	
	//������Ʒid�޸���Ʒ����
	public int updatePointById(BtoCItems items);
	//�޸�������Ʒ��maininfo�ֶΣ����޸Ĳ��������ƻ��߲��������ʱ��
	public void modifyItemsParameter(List<BtoCDefinition> list,String updater);
	
	
	//��ѯ�ƶ���Ʒ������һƷ��۵�
	// ������ѡ����Ʒ
	public PaginatedList<BtoCItems> findItemsByPriority(
			SelectConditions selconditions, String ASCDESC, int pageNo,
			int pageSize);

	// �ж���Ʒ�Ƿ��Ծɻ���
	public int findItemsIsTMById(int id);

	// ����������������Ʒ
	public List<BtoCItems> findExportItemsByConditions(
			SelectConditions selconditions, List<Integer> catalogIdList);

	// ��id������Ʒ
	public List<BtoCItems> findExportItemsByIdList(List<Integer> idList);

	// ��brandid ����Ʒ
	public String findItemsBybrandid(int brandid);

	// ��id����Ʒ������ǰ̨(���ز�����)
	public BtoCItems findItemsToFrontById(int id);

	// ��id����Ʒ������ǰ̨
	public BtoCItems findFrontItemsById(int id);
	
//  �� itemid ��ѯԭ��Ʒ specialStatus&8=0
	public BtoCItems findAllItemsByPid(String itemid); 

	// ��itemid��productname��updater ����Ʒ
	public PaginatedList<BtoCItems> findItemsByKeywords(String itemid,
			String productname, String operator, int pageNo, int pageSize, int id);

	// ����̬���֣�value ��ѯ��Ʒ
	public PaginatedList<BtoCItems> findScatByName(String definitionname,
			String scatname, String scatvalue, int pageNo, int pageSize);

	// ���۸������ѯ��Ʒ
	public PaginatedList<BtoCItems> findScatByPrice(int catalogid,
			double minprice, double maxprice, int pageNo, int pageSize);

	// ���ݴ����ID�����з�����Ʒ
	public PaginatedList<BtoCItems> findItemsByBigCatId(
			List<Integer> catalogIdList, int pageNo, int pageSize, String showstatus);

	// ��name����������
	public List<BtoCItems> findItemsByName(String name);

	// ��ѯ��̬���������
	public Object findParamColumnsValue(BtoCItems btocitems);

	// ��ѯ�������Ʒ
	public PaginatedList<BtoCItems> findFitConditions(String productname,
			int catalogid, int brandid, int pageNo, int pageSize);

	// ���ݾ���Ĳ�ѯ��������ѯ�������Ʒ�������з�ҳ
	public PaginatedList<BtoCItems> findItemsByConditions(
			SelectConditions selconditions, int pageNo, int pageSize, String showstatus);

	// ��ѯ���е���Ʒ�����з�ҳ
	public PaginatedList<BtoCItems> findAllItems(int pageNo, int pageSize, String showstatus);

	// ��ѯ��Ʒ
	public List<String> findItemsByCatalog(List<Integer> catalogIdList,
			int catalogid, int brandid);

	// ������Ʒ��ź���Ʒ�ͺŲ�ѯitem
	public PaginatedList<BtoCItems> findItemsByBrand(String itemid,
			String name, int catalogid, int brandid, int pageNo, int pageSize);

	// �ƶ������������Ʒ
	public int updateItemsByCatalog(int oldcatid, int newcatid, int brandid,
			String operator);

	// ��ѯ�ƶ�����Ʒ
	public BtoCItems findItemsMoveItemsById(int id);

	// ��ѯ��Ʒ��������
	public BtoCItems findItemsAllColumnsById(int id);

	// �� id ��ѯ��Ʒ
	public BtoCItems findAllItemsById(int id);

	// �� itemid ��ѯ��Ʒ
	public List<BtoCItems> findAllItemsByItemId(String itemid);

	// �� name ��ѯ��Ʒ
	public List<BtoCItems> findAllItemsByName(String name);

	// �� id��ѯ��Ʒ�۸�
	public BtoCItems findPriceById(int itemid);

	// ��ѯ��Ʒ���
	public List<BtoCItems> findPeiByStatus(List fittingIdList);

	// ������Ʒ
	public int insert(BtoCItems items);

	// ���븴�ƶ�̬�������
	public int insertCopyAttributes(int id, String definitionname, Map mapValue);

	// �����Ʒ���ԣ���̬��
	public int insertItemsAttributes(BtoCItems items,
			List<Attributeenumvalue> attrivalueList);

	// �޸���Ʒ��������̬��
	public int updateItemsAttributes(BtoCItems items,
			List<Attributeenumvalue> attrivalueList);

	// �޸���Ʒ����
	public int updateItemsMoneyback(BtoCItems items);

	// �޸���Ʒ�۸�
	public int updateOriginalprice(BtoCItems items);

	// �޸���Ʒ�û�����
	public int updateItemsByUsergrade(int id, float usergrade);
	
	// ����ӵ��޸���Ʒ�û����֣�����÷ʹ��
	public int updateItemsByUsergrade(String itemid, float usergrade);

	// �޸���Ʒ�����۸�
	public int updateSnatchbuyPrice(BtoCItems items);

	// �޸���Ʒ����
	public int updateParam(BtoCItems items);

	// �޸���Ʒ
	public int update(BtoCItems items);

	// �����޸���Ʒ�Ĵ�����
	public int updatePriceGiftById(int id,String updater,String strstatus);

	// �޸���Ʒ�۸�
	public int updatePrice(BtoCItems items);

	// �޸���Ʒ״̬
	public int updateStatus(BtoCItems items);

	// �޸���Ʒ����
	public int updateGift(BtoCItems items);	

	// ��id�޸���Ʒ����
	public int updatePriorityById(String updater, Date updatetime, int id,
			int priority);

	// �޸���Ʒ����
	public int updatePriority(String updater, Date updatetime, int downid,
			int downprio);

	// �޸���Ʒ�ķ���
	public int updateItemsCatalog(BtoCItems items);

	// �޸���Ʒ����
	public int updateTemplate(int itemid);

	// ���뵽��̬�������
	public int insertDynamicTable(int id, String itemid, String definitionname);
	
	//���뵽��̬�������,�ƶ���Ʒʱʹ��
	public int insertMoveItemsValue(String sql);
	
	// ɾ����̬�������
	public int deleteItemsAttributes(int id, String definitionname);
	
	//ɾ����̬������ݣ������ض�̬�������
	public Object deleteAttributesByItemid(String itemid,String definitionname);

	// ɾ����Ʒ
	public int delete(int id);

	/**
	 * dshh add ��ѯ�Ƽ���Ʒ.
	 */
	public List<BtoCItems> getRecommended();

	List<BtoCItems> getItemsById(String ids);

	void applyPublishAllProduct(int startnum, int pagesize);

	int queryOnlineItemcount();

	void applyPublishProductPage(int startnum, int pagesize);

	/**
	 * ��Ӷ����Ʒ
	 * @param itemid
	 * @param provinceid
	 * @param cityid
	 * @param price �õ����ļ۸�
	 * @param province
	 * @param city
	 */
	public int insertManyPriceItem(int itemid, int provinceid, int cityid,
			double price, String province, String city);

	/**
	 * �������Ʒ�Ƿ��Ѿ�����
	 * @param itemid
	 * @param provinceid
	 * @param cityid
	 * @return
	 */
	public boolean isExsitManyPriceItem(int itemid, int provinceid, int cityid);

	/**
	 * ��ѯ�����Ʒ
	 * @param itemid
	 * @return
	 */
	public List<BtoCItems> getManyPriceItems(int itemid);

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
	 * @return
	 */
	public int updateShowManyPriceItem(int itemid);

	/**
	 * �����ն�ҳ��ʾ
	 * @param itemid
	 * @return
	 */
	public int updateShowSpecialItem(int itemid);
	
	/**
	 * ��ѯ�����Ʒ
	 * @param itemid
	 * @return
	 */
	public List<BtoCItems> getManyPriceItemsForInterface(String itemid);

	/**
	 * �޸���Ʒ״̬�����������Ʒ
	 * @param id ԭ��Ʒid
	 * @param sub shang:�ϼܣ�xia:�¼�
	 * @param updater
	 * @return
	 */
	public int updateAllManyPriceItemsStatus(int id, String sub, String updater);
	
	public BtoCItems getSaleinfoAndMoneybackByIid(int id);

	/**
	 * ȡ����ۣ�ԭ��Ʒ״̬���޸ģ�
	 * @param id
	 * @param updater
	 * @return
	 */
	public int updateEscManyPriceProduct(int id, String updater);

	/**
	 * �õ���Ʒ�Լ������Ʒ
	 * @param ids
	 * @return
	 */
	public List<Integer> getShelvesItemAndManyPriceItemsByIds(String[] ids);

	/**
	 * ����Ƿ��й�����Ʒ
	 * @param ids
	 * @return
	 */
	public boolean isSpecialAttrItem(String[] ids);

	/**
	 * ���������Ʒ
	 * @param id
	 * @return
	 */
	public String updatePublishManyPriceItem(int id);

	/**
	 * �޸Ķ�̬��
	 * ��ԭԭ��ƷΪ��ͨ��Ʒ
	 * @param ids ԭ��Ʒ������Ʒ������
	 */
	public void updateTheDynamicTable(String[] ids);

	/**
	 * ͣ����Ʒ
	 * @param arrayitems id�ַ���, ���ŷֿ�
	 * @param adminSessionOperator
	 * @return
	 */
	public int updateItemToStop(String arrayitems, String adminSessionOperator);

	/**
	 * ������Ʒʱ������Ʒ״̬
	 * @param id
	 * @param nowstatus
	 * @param oldstatus
	 */
	public void updateItemStatus(int id, int nowstatus, int oldstatus);

	/**
	 * ������Ʒ
	 * @param btocitems
	 */
	public void updateParamNoStatus(BtoCItems btocitems);

	/**
	 * ���¶�̬��status״̬
	 * @param olditem
	 * @param i 1����ǰ̨��ʾ
	 */
	public void updateTheDynamicTableStatus(BtoCItems olditem, int i);

	/**
	 * ���¼�������Ʒ����ۡ��������Ĵ���״̬
	 * @param id ��Ʒid
	 */
	public void updatePromotionalGoodsStatus(int id);

	/**
	 * ��ѯ�����������Ե���Ʒ
	 * @param id ��Ʒid
	 * @param key ��Ʒid/itemid/��Ʒ����
	 * @param pageNum ��ѯ�ڼ�ҳ
	 * @param maxresult ÿҳ��ʾ����
	 * @return List<BtoCItems> pcDates
	 */
	public List<BtoCItems> searchSpecialItem(Map<String, Object> args);

	/**
	 * ��ѯ�����������Ե���Ʒ������
	 * @param id ��Ʒid
	 * @param key ��Ʒid/itemid/��Ʒ����
	 * @param pageNum ��ѯ�ڼ�ҳ
	 * @param maxresult ÿҳ��ʾ����
	 * @return List<BtoCItems> pcDates
	 */
	public int countSearchSpecialItem(Map<String, Object> args);

	/**
	 * �õ���Ʒ���������Լ�����ֵ
	 * @param pcDates
	 * @param args 
	 * @return
	 */
	public Map<String, String> getItemSpecialAttrValMap(List<BtoCItems> pcDates, Map<String, Object> args);

	/**
	 * �õ�����������
	 * @param args 
	 * @return
	 */
	public List<String> getSpecialAttrColomn(Map<String, Object> args);

	/**
	 * ��ѯ���������Ե���Ʒ
	 * @param id
	 * @param mainProperty 
	 * @return
	 */
	public List<BtoCItems> getSpecialItem(int id, List<SpecialAttribute> mainProperty);

	public List<BtoCItems> mobilePrice();

	/**
	 * �޸������״̬
	 * @param fit
	 */
	public void modifyStatus(ItemsFitting fit);
	
	
	List<BtoCItems> queryCommonItems(ItemQueryParam param);
	/**
	 * �������Id��ѯ�����
	 * @param presentId0
	 * @return
	 */
	public ItemsFitting findFitByItemid(Integer presentId0);

}
