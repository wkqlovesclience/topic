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
	//保存抢购和促销前的价格
	public int updateItemsQiangPrice(int id);
	//更改综合积分字段
	public int updateItemsSubTotalScore(int id,double subtotalscore);
	//更新是否新品
	public int updateItemMainPushById(BtoCItems items);
	//通过job停止主推商品
	public int updateMainPushByJob(BtoCItems items);
	//删除分公司主推的job
	public void deleteBranchCompanyMainPush(int id,String branchpush);
	//通过job停止新品
	public int updateIsNewByJob(int id);
	//更新是否新品
	public int updateItemIsNewById(BtoCItems items,Date endDate);
	
	//删除单个地区的多价
	public int deleteMulPriceById(int id,String updater);
	//综合评分
	public int findItemsInStateShangCount();
	public List<BtoCItems> findItemsInStateShang(int pageNo,int pageSize);
	
	//根据商品id得到积分
	public int findItemsPointById(int id);
	
	//根据商品id修改商品限购数量
	public int updateLimitCountById(BtoCItems items);
	
	//根据商品id修改商品积分
	public int updatePointById(BtoCItems items);
	//修改所有商品的maininfo字段，当修改参数的名称或者参数排序的时候
	public void modifyItemsParameter(List<BtoCDefinition> list,String updater);
	
	
	//查询移动商品，包含一品多价的
	// 按排序选择商品
	public PaginatedList<BtoCItems> findItemsByPriority(
			SelectConditions selconditions, String ASCDESC, int pageNo,
			int pageSize);

	// 判断商品是否以旧换新
	public int findItemsIsTMById(int id);

	// 按搜索条件导出商品
	public List<BtoCItems> findExportItemsByConditions(
			SelectConditions selconditions, List<Integer> catalogIdList);

	// 按id导出商品
	public List<BtoCItems> findExportItemsByIdList(List<Integer> idList);

	// 按brandid 查商品
	public String findItemsBybrandid(int brandid);

	// 按id查商品，用于前台(返回参数少)
	public BtoCItems findItemsToFrontById(int id);

	// 按id查商品，用于前台
	public BtoCItems findFrontItemsById(int id);
	
//  按 itemid 查询原商品 specialStatus&8=0
	public BtoCItems findAllItemsByPid(String itemid); 

	// 按itemid，productname，updater 查商品
	public PaginatedList<BtoCItems> findItemsByKeywords(String itemid,
			String productname, String operator, int pageNo, int pageSize, int id);

	// 按动态名字，value 查询商品
	public PaginatedList<BtoCItems> findScatByName(String definitionname,
			String scatname, String scatvalue, int pageNo, int pageSize);

	// 按价格区间查询商品
	public PaginatedList<BtoCItems> findScatByPrice(int catalogid,
			double minprice, double maxprice, int pageNo, int pageSize);

	// 根据大分类ID查所有分类商品
	public PaginatedList<BtoCItems> findItemsByBigCatId(
			List<Integer> catalogIdList, int pageNo, int pageSize, String showstatus);

	// 按name查所有属性
	public List<BtoCItems> findItemsByName(String name);

	// 查询动态表里的数据
	public Object findParamColumnsValue(BtoCItems btocitems);

	// 查询配件的商品
	public PaginatedList<BtoCItems> findFitConditions(String productname,
			int catalogid, int brandid, int pageNo, int pageSize);

	// 根据具体的查询条件，查询具体的商品，并进行分页
	public PaginatedList<BtoCItems> findItemsByConditions(
			SelectConditions selconditions, int pageNo, int pageSize, String showstatus);

	// 查询所有的商品，进行分页
	public PaginatedList<BtoCItems> findAllItems(int pageNo, int pageSize, String showstatus);

	// 查询商品
	public List<String> findItemsByCatalog(List<Integer> catalogIdList,
			int catalogid, int brandid);

	// 根据商品编号和商品型号查询item
	public PaginatedList<BtoCItems> findItemsByBrand(String itemid,
			String name, int catalogid, int brandid, int pageNo, int pageSize);

	// 移动分类的所有商品
	public int updateItemsByCatalog(int oldcatid, int newcatid, int brandid,
			String operator);

	// 查询移动的商品
	public BtoCItems findItemsMoveItemsById(int id);

	// 查询商品的所有列
	public BtoCItems findItemsAllColumnsById(int id);

	// 按 id 查询商品
	public BtoCItems findAllItemsById(int id);

	// 按 itemid 查询商品
	public List<BtoCItems> findAllItemsByItemId(String itemid);

	// 按 name 查询商品
	public List<BtoCItems> findAllItemsByName(String name);

	// 按 id查询商品价格
	public BtoCItems findPriceById(int itemid);

	// 查询商品配件
	public List<BtoCItems> findPeiByStatus(List fittingIdList);

	// 插入商品
	public int insert(BtoCItems items);

	// 插入复制动态表的数据
	public int insertCopyAttributes(int id, String definitionname, Map mapValue);

	// 添加商品属性（动态表）
	public int insertItemsAttributes(BtoCItems items,
			List<Attributeenumvalue> attrivalueList);

	// 修改商品参数（动态表）
	public int updateItemsAttributes(BtoCItems items,
			List<Attributeenumvalue> attrivalueList);

	// 修改商品返现
	public int updateItemsMoneyback(BtoCItems items);

	// 修改商品价格
	public int updateOriginalprice(BtoCItems items);

	// 修改商品用户评分
	public int updateItemsByUsergrade(int id, float usergrade);
	
	// 新添加的修改商品用户评分，刘俊梅使用
	public int updateItemsByUsergrade(String itemid, float usergrade);

	// 修改商品抢购价格
	public int updateSnatchbuyPrice(BtoCItems items);

	// 修改商品参数
	public int updateParam(BtoCItems items);

	// 修改商品
	public int update(BtoCItems items);

	// 单独修改商品的促销语
	public int updatePriceGiftById(int id,String updater,String strstatus);

	// 修改商品价格
	public int updatePrice(BtoCItems items);

	// 修改商品状态
	public int updateStatus(BtoCItems items);

	// 修改商品促销
	public int updateGift(BtoCItems items);	

	// 按id修改商品排序
	public int updatePriorityById(String updater, Date updatetime, int id,
			int priority);

	// 修改商品排序
	public int updatePriority(String updater, Date updatetime, int downid,
			int downprio);

	// 修改商品的分类
	public int updateItemsCatalog(BtoCItems items);

	// 修改商品发布
	public int updateTemplate(int itemid);

	// 插入到动态表的数据
	public int insertDynamicTable(int id, String itemid, String definitionname);
	
	//插入到动态表的数据,移动商品时使用
	public int insertMoveItemsValue(String sql);
	
	// 删除动态表的数据
	public int deleteItemsAttributes(int id, String definitionname);
	
	//删除动态表的数据，并返回动态表的数据
	public Object deleteAttributesByItemid(String itemid,String definitionname);

	// 删除商品
	public int delete(int id);

	/**
	 * dshh add 查询推荐商品.
	 */
	public List<BtoCItems> getRecommended();

	List<BtoCItems> getItemsById(String ids);

	void applyPublishAllProduct(int startnum, int pagesize);

	int queryOnlineItemcount();

	void applyPublishProductPage(int startnum, int pagesize);

	/**
	 * 添加多价商品
	 * @param itemid
	 * @param provinceid
	 * @param cityid
	 * @param price 该地区的价格
	 * @param province
	 * @param city
	 */
	public int insertManyPriceItem(int itemid, int provinceid, int cityid,
			double price, String province, String city);

	/**
	 * 检查多价商品是否已经存在
	 * @param itemid
	 * @param provinceid
	 * @param cityid
	 * @return
	 */
	public boolean isExsitManyPriceItem(int itemid, int provinceid, int cityid);

	/**
	 * 查询多价商品
	 * @param itemid
	 * @return
	 */
	public List<BtoCItems> getManyPriceItems(int itemid);

	/**
	 * 修改多价商品价格
	 * @param itemid
	 * @param price
	 * @return
	 */
	public boolean updateManyPriceItemPrice(int itemid, double price);

	/**
	 * 设置列表页显示
	 * @param itemid
	 * @return
	 */
	public int updateShowManyPriceItem(int itemid);

	/**
	 * 设置终端页显示
	 * @param itemid
	 * @return
	 */
	public int updateShowSpecialItem(int itemid);
	
	/**
	 * 查询多价商品
	 * @param itemid
	 * @return
	 */
	public List<BtoCItems> getManyPriceItemsForInterface(String itemid);

	/**
	 * 修改商品状态：包含多价商品
	 * @param id 原商品id
	 * @param sub shang:上架，xia:下架
	 * @param updater
	 * @return
	 */
	public int updateAllManyPriceItemsStatus(int id, String sub, String updater);
	
	public BtoCItems getSaleinfoAndMoneybackByIid(int id);

	/**
	 * 取消多价（原商品状态不修改）
	 * @param id
	 * @param updater
	 * @return
	 */
	public int updateEscManyPriceProduct(int id, String updater);

	/**
	 * 得到商品以及多价商品
	 * @param ids
	 * @return
	 */
	public List<Integer> getShelvesItemAndManyPriceItemsByIds(String[] ids);

	/**
	 * 检查是否有关联商品
	 * @param ids
	 * @return
	 */
	public boolean isSpecialAttrItem(String[] ids);

	/**
	 * 发布多价商品
	 * @param id
	 * @return
	 */
	public String updatePublishManyPriceItem(int id);

	/**
	 * 修改动态表
	 * 还原原商品为普通商品
	 * @param ids 原商品或多价商品都可以
	 */
	public void updateTheDynamicTable(String[] ids);

	/**
	 * 停用商品
	 * @param arrayitems id字符串, 逗号分开
	 * @param adminSessionOperator
	 * @return
	 */
	public int updateItemToStop(String arrayitems, String adminSessionOperator);

	/**
	 * 启用商品时更新商品状态
	 * @param id
	 * @param nowstatus
	 * @param oldstatus
	 */
	public void updateItemStatus(int id, int nowstatus, int oldstatus);

	/**
	 * 更新商品
	 * @param btocitems
	 */
	public void updateParamNoStatus(BtoCItems btocitems);

	/**
	 * 更新动态表status状态
	 * @param olditem
	 * @param i 1：是前台显示
	 */
	public void updateTheDynamicTableStatus(BtoCItems olditem, int i);

	/**
	 * 重新检查更新商品（多价、关联）的促销状态
	 * @param id 商品id
	 */
	public void updatePromotionalGoodsStatus(int id);

	/**
	 * 查询包含特殊属性的商品
	 * @param id 商品id
	 * @param key 商品id/itemid/商品名称
	 * @param pageNum 查询第几页
	 * @param maxresult 每页显示数量
	 * @return List<BtoCItems> pcDates
	 */
	public List<BtoCItems> searchSpecialItem(Map<String, Object> args);

	/**
	 * 查询包含特殊属性的商品的数量
	 * @param id 商品id
	 * @param key 商品id/itemid/商品名称
	 * @param pageNum 查询第几页
	 * @param maxresult 每页显示数量
	 * @return List<BtoCItems> pcDates
	 */
	public int countSearchSpecialItem(Map<String, Object> args);

	/**
	 * 得到商品的特殊属性级属性值
	 * @param pcDates
	 * @param args 
	 * @return
	 */
	public Map<String, String> getItemSpecialAttrValMap(List<BtoCItems> pcDates, Map<String, Object> args);

	/**
	 * 得到特殊属性列
	 * @param args 
	 * @return
	 */
	public List<String> getSpecialAttrColomn(Map<String, Object> args);

	/**
	 * 查询关联了属性的商品
	 * @param id
	 * @param mainProperty 
	 * @return
	 */
	public List<BtoCItems> getSpecialItem(int id, List<SpecialAttribute> mainProperty);

	public List<BtoCItems> mobilePrice();

	/**
	 * 修改配件的状态
	 * @param fit
	 */
	public void modifyStatus(ItemsFitting fit);
	
	
	List<BtoCItems> queryCommonItems(ItemQueryParam param);
	/**
	 * 根据配件Id查询配件表
	 * @param presentId0
	 * @return
	 */
	public ItemsFitting findFitByItemid(Integer presentId0);

}
