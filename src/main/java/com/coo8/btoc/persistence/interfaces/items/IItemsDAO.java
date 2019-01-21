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
	//保存抢购和促销前的价格
	public int updateItemsQiangPrice(int id);
	//更改综合积分字段
	public int updateItemsSubTotalScore(int id,double subtotalscore);
	//更新是否新品
	public int updateItemMainPushById(BtoCItems items);
	//通过job停止主推商品
	public int updateMainPushByJob(BtoCItems items);
	//通过job停止新品
	public int updateIsNewByJob(int id);
	//更新是否新品
	public int updateItemIsNewById(BtoCItems items);
	
	//综合评分
	public int findItemsInStateShangCount();
	public List<BtoCItems> findItemsInStateShang(int pageNo,int pageSize);
	
	
	//通过itemid得到多价商品，用于以旧换新
	public List<BtoCItems> findYiJiuHuanXinByItemId(String itemid);
	//单独修改多价以旧换新的商品
	public int updateYiJiuStatusById(BtoCItems items);
	
	//根据商品id得到积分
	public int findItemsPointById(int id);
	
	//根据商品id修改商品限购数量
	public int updateLimitCountById(BtoCItems items);
	
	//根据商品id修改商品积分
	public int updatePointById(BtoCItems items);
	//通过定义id查找所有的商品的id，List
	public List<String> findItemsIdByDefinitionid(int definitionid);
	//修改所有商品的maininfo字段，当修改参数的名称或者参数排序的时候
	public void modifyItemsParameter(BtoCItems items);
	//按排序选择商品
	public PaginatedList<BtoCItems> findItemsByPriority(SelectConditions selconditions,String ASCDESC,int pageNo,int pageSize);
	//判断商品是否以旧换新
	public int findItemsIsTMById(int id);
	//按搜索条件导出商品
	public List<BtoCItems> findExportItemsByConditions(SelectConditions selconditions,List<Integer> catalogIdList);
	//按id导出商品
	public List<BtoCItems> findExportItemsByIdList(List<Integer> idList);
	//按brandid 查商品
	public List<String> findItemsBybrandid(int brandid);
	
	//按id查商品，用于前台(返回参数少)
	public BtoCItems findItemsToFrontById(int id);
	//按id查商品，用于前台
	public BtoCItems findFrontItemsById(int id);
	
//  按 itemid 查询原商品 specialStatus&8=0
	public BtoCItems findAllItemsByPid(String itemid);
	
	//按itemid，productname，updater 查商品
	public PaginatedList<BtoCItems> findItemsByKeywords(String itemid,String productname,String operator,int pageNo,int pageSize, int id);
	
	//按动态名字，value 查询商品
	public PaginatedList<BtoCItems> findScatByName(String definitionname,String scatname,String scatvalue,int pageNo,int pageSize);
	//按价格区间查询商品
	public PaginatedList<BtoCItems> findScatByPrice(int catalogid,double minprice,double maxprice,int pageNo,int pageSize);
	
	//按name查所有属性
	public List<BtoCItems> findItemsByName(String name);
	//查询动态表里的数据
	public Object findParamColumnsValue(BtoCItems btocitems);
	//查询动态表里的数据,用于批量改商品参数
	public Object findItemsParameter(String itemid,String definitionname);
	//查询配件的商品
	public PaginatedList<BtoCItems> findFitConditions(String productname,int catalogid,int brandid,int pageNo,int pageSize);
	//根据具体的查询条件，查询具体的商品，并进行分页
	public PaginatedList<BtoCItems> findItemsByConditions(SelectConditions selconditions,int pageNo,int pageSize,String showstatus);
	//查询所有的商品，进行分页
	public PaginatedList<BtoCItems> findAllItems(int pageNo,int pageSize, String showstatus);
	
	public List<String> findItemsByCatalog(List<Integer> catalogIdList,int catalogid,int brandid);
	//根据商品编号和商品型号查询item
	public PaginatedList<BtoCItems> findItemsByBrand(String itemid,String name,int catalogid,int brandid,int pageNo,int pageSize);
	//根据大分类ID查所有分类商品
	public PaginatedList<BtoCItems> findItemsByBigCatId(List<Integer> catalogIdList,int pageNo,int pageSize, String showstatus);
	//查询小分类下的所有商品
	public List<BtoCItems> findItemsBycatalogid(int catalogid,int brandid);
	//查询移动的商品
	public BtoCItems findItemsMoveItemsById(int id);
	
	//查询商品的所有列
	public BtoCItems findItemsAllColumnsById(int id);
	//  按 id 查询商品
	public BtoCItems findAllItemsById(int id);
	
	// 按 status 查询商品ID列表
	public List<Integer> findAllItemsByStatus(int status);
    //  按 itemid 查询商品
	public List<BtoCItems> findAllItemsByItemId(String itemid);
    
	//  按 name 查询商品
	public List<BtoCItems> findAllItemsByName(String name);
	
    //  按 id查询商品价格
	public BtoCItems findPriceById(int itemid);
    //  查询商品配件
	public List<BtoCItems> findPeiByStatus(List fittingIdList);
	
	
	//插入商品
	public int insert(BtoCItems items);
	
	//插入复制动态表的数据
	public int insertCopyAttributes(int id,String definitionname,Map mapValue);
	
	//添加商品属性（动态表）
	public int insertItemsAttributes(BtoCItems items,List<Attributeenumvalue> attrivalueList);
	//修改商品返现
	public int updateItemsMoneyback(BtoCItems items);
	//修改商品价格
	public int updateOriginalprice(BtoCItems items);
	//修改商品用户评分
	public int updateItemsByUsergrade(int id,float usergrade);
	
	//修改商品用户评分,新添加的，刘俊梅使用
	public int updateItemsByUsergrade(String itemid,float usergrade);
	
	//修改商品抢购价格
	public int updateSnatchbuyPrice	(int id ,double originalprice,double moneyback ,int showpic);
	//修改商品参数（动态表）
	public int updateItemsAttributes(BtoCItems items,List<Attributeenumvalue> attrivalueList);
	//修改商品参数
	public int updateParam(BtoCItems items);
	//修改商品
	public int update(BtoCItems items);
	//单独修改商品的促销语
	public int updatePriceGiftById(BtoCItems items);
	//修改商品价格
	public int updatePrice(BtoCItems items);
	//修改商品状态
	public int updateStatus(BtoCItems items);
	//修改商品促销
	public int updateGift(BtoCItems items);
	//按id修改商品排序
	public int updatePriorityById(String updater,Date updatetime,int id,int priority);
	//修改商品排序
	public int updatePriority(String updater,Date updatetime,int downid,int downprio);
	//修改商品的分类
	public int updateItemsCatalog(BtoCItems items);
	//修改商品发布
	public int updateTemplate(int itemid);
	// 修改商品maininfo
	public int updateMaininfo(BtoCItems items);
	//插入到动态表的数据
	public int insertDynamicTable(int id,String itemid,String definitionname);
	
	//插入到动态表的数据,移动商品时使用
	public int insertMoveItemsValue(String sql);
	
	//删除动态表的数据
	public int deleteItemsAttributes(int id,String definitionname);
	
	//删除动态表的数据，并返回动态表的数据
	public Object deleteAttributesByItemid(String itemid,String definitionname);
	//删除商品
	public int delete(int id);
	//添加多价商品
	public int insertManyPriceItem(BtoCItems item);
	/**
	 * dshh add
	 * 查询推荐商品.
	 * @return
	 */
	public List<BtoCItems> getRecommended (Map map);
	
	List<BtoCItems> getItemsByIds(String ids);
	
	void publishAllProduct(int startnum , int pagesize);
	int queryOnlineItemCount();
	List<Integer> queryOnlineItemId(int startnum , int pagesize);
	
	/**
	 * 检查多价商品是否存在
	 * @param itemid
	 * @param provinceid
	 * @param cityid
	 */
	public boolean isExsitManyPriceItem(int itemid, int provinceid, int cityid);
	
	/**
	 * 查询多价商品（不包含原商品）
	 * @param itemid 自增ID
	 * @return
	 */
	public List<BtoCItems> getManyPriceItems(int itemid);
	
	/**
	 * 修改商品显示状态
	 * @param itemid 自增ID
	 */
	public void updateItemShowStatus(int itemid);
	
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
	 * @return 1成功，-1失败，0未上架
	 */
	public int updateShowManyPriceItem(int itemid, List<Integer> ids);
	
	/**
	 * 设置终端页显示
	 * @param itemid
	 * @return
	 */
	public boolean updateShowSpecialItem(int itemid);
	
	/**
	 * 查询多价商品终端页显示的数量（第一个状态为1）
	 * @param itemid
	 * @return
	 */
	public int getEndPageShowNum(int itemid);
	
	/**
	 * 查询多价列表页前台显示个数
	 * @param itemid
	 * @return
	 */
	public int getListPageShowNum(int itemid);
	
	/**
	 * 取消终端页前台显示
	 * @param itemid
	 */
	public void escEndPageShow(int itemid);
	
	/**
	 * 同步终端页显示商品ID到特殊商品表
	 * @param itemid
	 */
	public void associatedShowSpecialItemid(int itemid);
	
	/**
	 * 同步终端页显示商品ID到特殊商品表（添加多价时）
	 * @param newid
	 * @param oldid
	 */
	public void synchronizationDataToSpecial(int newid, int oldid);
	
	/**
	 * 查询多价商品
	 * @param itemid 自增ID
	 * @return
	 */
	public List<BtoCItems> getManyPriceItemsForInterface(String itemid);
	
	/**
	 * 停止所有原商品列表显示
	 * @param itemid
	 */
	public void updateOriginalItemShowStatus(int itemid);
	
	/**
	 * 关联多价商品id到动态表
	 * @param itemid 原id
	 * @param id 新id
	 */
	public void updateIidToDynamicTable(int itemid, int id);
	
	/**
	 * 统计原商品的多价商品数量
	 * @param itemid
	 * @return
	 */
	public int countManyPriceItemNum(int itemid);
	
	/**
	 * 得到所有商品及其多价商品
	 * @param id
	 * @return
	 */
	public List<BtoCItems> getItemAndManyPriceItems(int id);
	
	public BtoCItems getSaleinfoAndMoneybackByIid(int id);
	
	/**
	 * 设置终端页显示，并同步iid到特殊属性表
	 * @param itemid
	 */
	public void setEndPageShow(int itemid);
	
	/**
	 * 得到商品的定义name
	 * @param itemid
	 * @return
	 */
	public Object getItemCatalogId(int itemid);
	
	/**
	 * 取消所有关联商品的动态表前台显示
	 * @param maps
	 */
	public void escAllShowStatusToDynamicTable(Map<String, Object> maps);
	
	/**
	 * 设置当前多价商品ID到动态表
	 * @param args
	 */
	public void setShowStatusToDynamicTable(Map<String, Object> args);
	
	/**
	 * 设置新的多价id到动态表
	 * @param itemid
	 */
	public void setNewShowStatusItemidToDynamicTable(Map<String, Object> args);
	
	/**
	 * 取消所有，添加自己显示
	 * @param itemid
	 */
	public void escAllAndAddShowNowItem(int itemid);
	
	/**
	 * 更新列表页显示：停止原商品、添加终端页显示、并同步数据到特殊属性表
	 * @param itemid
	 */
	public void updateListPageItemsShow(int itemid);
	
	/**
	 *  所有关联、多价商品（上架的）
	 * @param itemid
	 * @return
	 */
	public List<Integer> getManyPriceAndSpecialAttrItemid(int itemid);
	
	/**
	 * 当前商品的上架的多价商品数量
	 * @param parseInt
	 * @return
	 */
	public int countShelvesManyPriceItemNum(int parseInt);
	
	/**
	 * 得到原商品及多价商品(上架的)
	 * @param itemid
	 * @return
	 */
	public List<Integer> getShelvesItemAndManyPriceItems(int itemid);
	
	/**
	 * 得到商品以及多价商品
	 * @param id
	 * @return
	 */
	public List<Integer> getShelvesItemAndManyPriceItemsByIds(String[] id);
	
	/**
	 * 检查是否有关联的商品
	 * @param ids
	 * @return
	 */
	public boolean isSpecialAttrItem(String[] ids);
	
	/**
	 * 得到当前商品在特殊属性中的productid
	 * @param id
	 * @return
	 */
	public int getProductidByItemid(int id);
	
	/**
	 * 得到当前商品的所有属性及属性值
	 * @param id
	 * @return
	 */
	public Map<String, Set> getSpecialAttributeValue(int id);
	
	/**
	 * 删除特殊属性
	 * @param id
	 */
	public void deleteSpecialAttributeBuItemid(int id);
	
	/**
	 * 得到特殊属性中删除当前后的关联商品数量
	 * @param productid
	 * @return
	 */
	public int countSpecialItems(int productid);
	
	/**
	 * 删除所有特殊属性
	 * @param productid
	 */
	public void deleteAllSpecialAttrributeByItemid(int productid);
	
	/**
	 * 删除当前商品在特殊属性中的关联（如果特性属性中只剩一个商品则全部删除）
	 * @param id
	 */
	public void deleteNowSpecialAttribute(int id);
	
	/**
	 * 发布商品（根据 status 来判断）
	 * @param id
	 */
	public void publishItem(BtoCItems item);
	
	/**
	 * 还原原商品为普通商品
	 * @param id 原商品或多价商品ID
	 */
	public void restoreTheDynamicTable(int id);
	
	/**
	 * 设置动态表前台显示
	 * @param item
	 */
	public void setShowStatusToDynamicTable(BtoCItems item);
	
	/**
	 * 更新商品的status
	 * @param id
	 * @param i 状态值
	 */
	public void updateItemStatus(int id, int i);
	
	/**
	 * 更新商品：不更新status
	 * @param btocitems
	 */
	public void updateParamNoStatus(BtoCItems btocitems);
	
	/**
	 * 修改动态表status
	 * @param args
	 */
	public void updateTheDynamicTableStatus(Map<String, Object> args);
	
	/**
	 * 得到上架的、促销商品（多价、关联）
	 * @param id
	 * @return
	 */
	public List<Integer> getPromotionalGoodsByManyPriceAndSpecialAttr(int id);
	
	/**
	 * 取消商品的促销状态
	 * @param ids 商品id's
	 */
	public void escPromotionalGoodsStatus(List<Integer> ids);
	
	/**
	 * 得到前台显示的itemid
	 * @param ids
	 * @return
	 */
	public int getShowStatusItem(List<Integer> ids);
	
	/**
	 * 添加商品促销状态
	 * @param showid
	 */
	public void setPromotionalGoodsStatusForShowItem(int showid);

	/**
	 * 查询包含特殊属性的商品
	 * @param args
	 * @return 
	 */
	public List<BtoCItems> searchSpecialItem(Map<String, Object> args);
	public List<BtoCItems> mobilePrice();
	/**
	 * 修改配件的 状态
	 * @param fit
	 */
	public void modifyStatus(ItemsFitting fit);
	
	/**
	 * 查询包含特殊属性的商品数量
	 * @param args
	 * @return 
	 */
	public int countSearchSpecialItem(Map<String, Object> args);

	/**
	 * 得到商品的特殊属性级属性值
	 * @param args
	 * @return
	 */
	public Map<String, String> getItemSpecialAttrValMap(Map<String, Object> args);

	/**
	 * 得到特殊属性列
	 * @param args
	 * @return
	 */
	public List<String> getSpecialAttrColomn(Map<String, Object> args);

	/**
	 * 查询关联了属性的商品
	 * @param id
	 * @return
	 */
	public List<BtoCItems> getSpecialItem(int id);
	
	List<BtoCItems> queryCommonItems(ItemQueryParam param);
	/**
	 * 根据配件ID查询配件表
	 * @param presentId0
	 * @return
	 */
	public ItemsFitting findFitByItemid(Integer presentId0);
	
	/**
	 * 获得产品分类
	 * @param catalogId
	 * @return
	 */
	public List<CategoryBrandBase> getCatalogListById(Integer catalogId);
	/**
	 * 根据分类ID获得商品---只取1个
	 * @param catalogId
	 * @return
	 */
	public List<BtoCItems> getItemListByCid(Integer catalogId);
	
	public List<CategoryBrandBase> getCatalogListByIdMap(Integer[] catalogArr);
	
	/**
	 * 商品描述信息数据迁移，从之前的BTOC_ITEM 表迁移 到BTOC_DESC
	 */
	public BtocItemDesc findItemDescById(Integer id);
	
}
