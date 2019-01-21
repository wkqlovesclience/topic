/*
 * 文件名： IBtoCCatalogManager.java
 * 
 * 创建日期： 2010-4-29
 *
 * Copyright(C) 2010, by wangyan.
 *
 * 原始作者: wangyan
 *
 */
package com.coo8.btoc.business.interfaces.catalog;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.catalog.BtoCCatalog;
import com.coo8.btoc.model.catalog.BtoCSeries;
import com.coo8.btoc.model.catalog.CategoryBrandBase;
import com.coo8.btoc.model.catalog.Color;
import com.coo8.btoc.util.pages.PaginatedArrayList;


/**
 * 分类业务接口
 *
 * @author wangyan
 *
 * @version $Revision$
 *
 * @since 2010-4-29
 */
public interface IBtoCCatalogManager {

	//新增分类信息
    int insertCatalog(BtoCCatalog catalog);
    //修改分类信息
    int updateCatalog(BtoCCatalog catalog);
    //根据分类id删除分类
    int deleteCatalog(int catalogId);
    //得到分类
    BtoCCatalog getCatalog(int catalogId);
    //根据分类名称查询分类 （like）
    PaginatedArrayList<CategoryBrandBase> findCatalogByName(String name,int pageNo,int pageSize);
    //得到下一级状态为启用的分类
    List<BtoCCatalog> findCatalogChildBycid(int catalogId);
    //得到下一级所有的分类
    List<CategoryBrandBase> findCatalogChildBycidNoStatus(int catalogId);
    //得到所有父级的分类
    List<BtoCCatalog> findCatalogFathersBycid(int catalogId);
    //得到分类的上一级
    BtoCCatalog getCatalogFahter(int catalogId);
    //更新优先级
    int updateCatalogPriority(int catalogId,int priority,String updater);
    //更新状态
    int updateCatalogStatus(int catalogId,int status,String updater);

    //添加系列
    void insertSeries(BtoCSeries series);
    //修改系列
    void updateSeries(BtoCSeries series);
    //查找系列列表
    List<BtoCSeries> findSeries(int catalogId);
    //删除系列
    void deleteSeries(BtoCSeries series);
    //通过catalogid和seriesname得到系列
    BtoCSeries getSeriesByCidSname(BtoCSeries series);
    public List<BtoCCatalog> findCatalogList(Map<String,Object> search);
    //查找颜色
    /**
     * 查找颜色列表
     * */
    public List<Color> findColor(int catalogId);
    /**
     * 添加颜色
     * */
    public void insertColor(Color color) ;
    
    /**
     *修改颜色
     * */
    public void updateColor(Color color) ;
    
    /**
     * 查找颜色
     * */
    public Color findColorByid(int colorId) ;
    
    /**
     * 删除颜色
     * */
    public void deleteColor(Color color) ;

    /**
     * 分类移动
     * @param id 待移动的分类
     * @param oldFid 移动到的分类
     * @param newFid 
     * @param username 操作者
     */
    public void moveCatalog(int id,int oldFid, int newFid, String username) ;

    /**
     * 查询所有小类
     */
     public List<BtoCCatalog> selectSmallCatalogs();
     
     /**
 	 * 根据父类id与商家id查询下级所有数据
 	 * 
 	 * @return 返回 BtoCCatalog集合
 	 * 
 	 */
 	public List<BtoCCatalog> getCatalogByfidAndCompanyid(int fid,int companyid) ;
 	/**
 	 * 停用品牌分类
 	 * @param fatherid 
 	 * @param id
 	 * @param type
 	 * @param updater
 	 * @return
 	 */
    public int updateCatalogStatusForStop(int fatherid, int id, int type, String updater);
    
    /**
     * 品牌查询
     * @param fid 分类Id
     * @param name 查询条件
     * @param pageIndex 第几页
     * @param pageSize 每页大小
     * @return 分页类
     */
	PaginatedArrayList<CategoryBrandBase> findBrandByName(int fid, String name, int pageIndex, int pageSize);
	List<CategoryBrandBase> findBrandBycidNoStatus(int fid);
	
	/**
	 * 查询分类品牌
	 * @param id 品牌id
	 * @return
	 */
	CategoryBrandBase getCatalogBrand(int id);
	
	/**
	 * 更新品牌
	 * @param btoccatalog
	 */
	public void updateBrand(CategoryBrandBase btoccatalog);
	
	/**
	 * 更新品牌状态
	 * @param fid 分类ID
	 * @param id
	 * @param status
	 * @param updater
	 */
	public void updateBrandStatus(int fid, int id, int status, String updater);
	
	/**
	 * 添加品牌
	 * @param btoccatalog
	 * @return
	 */
	public int insertBrand(CategoryBrandBase btoccatalog);
	
	/**
	 * 修改品牌状态
	 * @param fid
	 * @param catalogId
	 * @param priority
	 * @param string
	 */
	public void updateBrandPriority(int fid, int catalogId, int priority, String string);
	
	/**
	 * 更新品牌图片
	 * @param url
	 * @param brandid
	 * @param updater
	 */
	public void updateBrandPicture(String url, String brandid, String updater);
	
	/**
	 * 通过大分类ID得到所有小分类、品牌
	 * @param bigCatalogId 大分类ID通过小分类ID得到品牌


	 * @return JSON串：{bigId:{smileId:(brandid,...),...}}
	 */
	public String getCatalogAndBrandByFid(int bigCatalogId);
	
	/**
	 * 通过大分类得到所有小分类
	 * @param bigCatalogId 大分类ID
	 * @return JSON 格式
	 */
	public String getSmallCatalogByFid(int bigCatalogId);
	
	/**
	 * 通过大分类得到所有小分类
	 * @param bigCatalogId 大分类ID
	 * @return List
	 */
	public List<BtoCCatalog> getSmallCatalogByFidToList(int bigCatalogId);
	
	/**
	 * 通过小分类ID得到品牌
	 * @param smallCatalogId 小分类ID
	 * @return JSON 格式
	 */
	public String getBrandByFid(int smallCatalogId);
	
	/**
	 * 通过小分类ID得到品牌
	 * @param smallCatalogId 小分类ID
	 * @return List<CategoryBrandBase>
	 */
	public List<CategoryBrandBase> getBrandByFidToList(int smallCatalogId);
	
	/**
	 * 得到所有大分类
	 * @return JSON 格式
	 */
	public String getBigCatalog();
	
	/**
	 * 得到所有大分类
	 * @return List<BtoCCatalog>
	 */
	public List<BtoCCatalog> getBigCatalogToList();
	
	/**
	 * 更新品牌服务
	 * @param brandId 品牌ID
	 * @param fid 分类ID
	 * @param brandServiceText 服务内容
	 */
	public int updateBrandService(int brandId, int fid, String brandServiceText);
	
	/**
	 * 获得品牌服务内容
	 * @param brandId 品牌id
	 * @param fid 分类id
	 * @return
	 */
	public String getBrandService(int brandId, int fid);
	
	/**
	 * 查询是否存在品牌
	 * @param fid 分类ID
	 * @param name
	 * @return boolean -1:不存在，1：已存在，2：当前分类下已存在
	 */
	public int checkBrandIsExisted(int fid, String name);
	
	/**
	 * 添加品牌关联
	 * @param brandId
	 * @param catalogId
	 * @return
	 */
	public int addBrand(int brandId, int catalogId);
	
	/**
	 * 得到品牌英文名称
	 * @param brandid
	 * @return
	 */
	public String getBrandEnName(int brandid);
	
	/**
	 * 移动品牌排序
	 * @param fid 分类ID（改中间表需要）
	 * @param id 品牌ID
	 * @param priority 当前排序
	 * @param ud 上移：上一级排序加ud, 下移：下一个排序加ud
	 * @param updater
	 * @return 成功1，失败0
	 */
	public int moveBrandPriority(int fid, int id, int priority, int ud, String updater);
	/**
	 * 移动分类排序
	 * @param fid
	 * @param id 品牌ID
	 * @param ud 上移：上一级排序加ud, 下移：下一个排序加ud
	 * @param priority 当前排序
	 * @param updater
	 * @return 成功1，失败0
	 */
	public int moveCatalogPriority(int fid, int id, int priority, int ud, String updater);
	
	/**
	 * 查询分类下的品牌
	 * @param fid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PaginatedArrayList<CategoryBrandBase> findCatalogByNameAndFid(int fid, int pageIndex, int pageSize);
	
	/**
	 * 得到所有品牌
	 * @return
	 */
	List<CategoryBrandBase> getAllBrands();
	
	/**
	 * 查询分类是否存在
	 * @param decode
	 * @return
	 */
	public boolean checkCatalogIsExisted(String name);
	
	/**
	 * 查询分类英文名是否存在
	 * @param decode
	 * @return
	 */
	public boolean checkCatlogEnName(String decode);
	
	/**
	 * 发布超市页
	 * @param id 大分类ID
	 */
	public void updatePublishCatalog(int id);
	
	/**
	 * 查询是否存在品牌或分类
	 * @param movedFatherId 新分类ID
	 * @param type 1：小分类，2：品牌
	 * @param ids
	 * @return 已经存在的分类或品牌名称
	 */
	public String isExistCataOrBrand(int movedFatherId, int type, int[] ids);
	BtoCCatalog getFrontCatalog(int catalogId);
	
	/**
	 * 是否列表页显示所有的特殊属性
	 * @param catalogId 分类ID
	 */
	void updateShowAllSpecialGoods(int catalogId);
	
	/**
	 * 修改分类中品牌的权重值
	 * @param catalogId
	 * @param brandId
	 * @param value
	 */
	void updateCatalogBrandLevel(int catalogId, int brandId, int value);
	
	/**
	 * 得到分类品牌的权重值
	 * @param catalogId
	 * @param brandId
	 * @return
	 */
	int getCatalogBrandLevel(int catalogId, int brandId);
	
	/**
	 * 切换显示规格状态
	 * @param catalogId
	 */
	void updateChangeShowItemSize(int catalogId);
}
