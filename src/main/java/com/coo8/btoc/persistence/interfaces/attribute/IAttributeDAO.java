/*
 * 文件名： IAttributeDAO.java
 * 
 * 创建日期： 2010-4-28
 *
 * Copyright(C) 2010, by wangyan.
 *
 * 原始作者: wangyan
 *
 */
package com.coo8.btoc.persistence.interfaces.attribute;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coo8.btoc.model.attribute.Attribute;
import com.coo8.btoc.model.attribute.Attributeenumvalue;
import com.coo8.btoc.model.attribute.Attributegroup;
import com.coo8.btoc.model.attribute.SpecialAttribute;
import com.coo8.btoc.model.definition.BtoCDefinitionattribute;
import com.coo8.btoc.model.items.BtoCItems;
import com.coo8.btoc.util.pages.PaginatedList;


/**
 * 属性dao
 *
 * @author wangyan
 *
 * @version $Revision$
 *
 * @since 2010-4-28
 */
public interface IAttributeDAO {

	/**
	 * 根据名称和操作者查询
	 * */
    PaginatedList<Attribute> findAttributeByNameOper(String cataname, String name,String operator,int pageNo,int pageSize);
    
    /**
	 * 根据属性名称得到属性条列表（不用like）
	 * */
    public List<Attributeenumvalue> findAttriEnumByAttName(String name) ;
    /**
	 * 根据name得到属性
	 * */
    
    public Attribute getAttribute(String name) ;
    /**
	 * 得到属性分组
	 * */
    public List<Attributegroup> findAttributegroup(Attributegroup attributegroup) ;
    
    /**
	 *删除 attributegroup 数据
	 */
    public int deleteAttributeGroupByDefinitionid(long definitionid);
    
    /**
	 *新增属性
	 */
    public int insertAttribute(Attribute attribute);
    
    /**
	 *修改属性
	 */
    public int updateAttribute(Attribute attribute);
    
    /**
	 *删除属性
	 */
    public int deleteAttribute(String attributeName);
    
    /**
	 *插入属性选择项
	 */
    public void insertAttributeEnumValue(Attributeenumvalue enumvalue);

    /**
	 *更新属性选择项
	 */
    public void updateAttributeEnumValue(Attributeenumvalue enumvalue);
    
    /**
	 *
	 */
    public void setAttributeEnumImageisNull(Attributeenumvalue enumvalue);
    
    /**
	 *删除属性选择项
	 */
    public void deleteAttributeEnumValue(Attributeenumvalue enumvalue);
    
    /**
	 *删除属性选择项
	 */
    public void deleteAttributeEnumValueByAttrname(String attributename);
    
    /**
	 *得到属性值来源
	 */
    public List<Attribute> findAttributeByFromwhere(String fromwhere);
    
    /**
	 *从attribute中查找不包含自己的fromwhere
	 */
    public List<String> findOtherAttribueFromwhere(String attributename);

    /**
	 *add attributegroup 数据
	 */
    public int insertAttributeGroup(Attributegroup attributegroup);

    /**
	 *edit attributegroup 数据
	 */
    public int updateAttributeGroup(Attributegroup attributegroup);

    /**
	 *根据 id 删除 attributegroup 数据
	 */
    public int deleteAttributeGroupById(long id);
    
    /**
	 *查询是否已经有类型为礼品的属性组
	 */
    public Attributegroup getGift(Attributegroup attributegroup);

	public Attribute getAttributeById(int id);

	public List<Attributeenumvalue> getAttriEnumByAttId(int id);

	/**
	 * 得到所有属性
	 * @param id 商品ID
	 * @return
	 */
	public List<Attribute> getAttributeByItemId(int id);

	/**
	 * 得到所有属性值
	 * @param id 商品ID
	 * @return
	 */
	public List<Attributeenumvalue> getAttriEnumByItemid(int id);

	/**
	 * 得到商品对应的动态表名
	 * @param itemid
	 * @return
	 */
	public String getTableNameByItemid(int itemid);

	/**
	 * 得到商品属性及属性值
	 * @param tableName
	 * @param itemid
	 * @return
	 */
	public Map getItemValue(String tableName, int itemid);

	/**
	 * 得到特殊商品属性及属性值
	 * @param id
	 * @param btocitems 
	 * @return
	 */
	public List<SpecialAttribute> getSpecialAttributeByItemid(int id, BtoCItems btocitems);

	/**
	 * 添加特殊属性
	 * @param spa
	 * @return
	 */
	public int insertSpecialAttribute(SpecialAttribute spa);

	/**
	 * 删除特殊属性
	 * @param id 商品id
	 */
	public void deleteSpecialAttribute(int id);

	/**
	 * 删除特殊属性排序
	 * @param id 商品id
	 */
	public void deleteSpecialAttributePriority(int id);

	/**
	 * 添加特殊属性排序
	 * @param id
	 * @param priority
	 */
	public void insertSpecialPriority(int id, Map<BtoCDefinitionattribute, Set> priority);

	/**
	 * 移动特殊商品排序
	 * @param item
	 * @param definitionid2 
	 * @param attrname
	 * @param attrvalue
	 * @param ud 移动值
	 */
	public void moveSpecialAttributePriority(BtoCItems item, int definitionid2,
			String attrname, String attrvalue, int ud);

	/**
	 * 删除属性值
	 * @param itemid
	 * @param attrname
	 * @param attrvalue
	 */
	public void deleteSpecialVal(int itemid, String attrname, String attrvalue);

	/**
	 * 得到特殊商品属性
	 * @param id
	 * @return List<SpecialAttribute>
	 */
	public List<SpecialAttribute> getSpecialItemAttribute(int id);

	/**
	 * 得到下一个特殊属性组ID
	 * @return
	 */
	public int getSpecialNextProductid();

	/**
	 * 得到特殊商品组ID（productid）
	 * @param id 商品ID
	 * @return
	 */
	public int getSpecialProductByItemid(int id);

	/**
	 * 得到相关特殊属性商品
	 * @param id 商品ID
	 * @return
	 */
	public List querySpecialAttrByItemid(int id);

	/**
	 * 得到相关特殊属性商品排序
	 * @param id 商品ID
	 * @return
	 */
	public List querySpecialPriByItemid(int id);

	/**
	 * 查询已经存在的商品ID
	 * @return
	 */
	public List<Integer> findExsitSpecialItemid();

	/**
	 * 得到商品主图：50*50
	 * @param itemids
	 * @return map<id:url>
	 */
	public Map<Integer, String> getItemMainPicByIds(List<String> itemids);

	/**
	 * 查询属性
	 * @param attribute
	 * @param pageIndex
	 * @param pagesize
	 * @return
	 */
	public PaginatedList<Attribute> findAttributeByNameOper(Attribute attribute,
			int pageIndex, int pagesize);

	/**
	 * 得到属性所属分类名称
	 * @param ids
	 * @return cat1,cat2,..
	 */
	public List<Attribute> getAttributeCataNames(Integer[] ids);

	/**
	 * 删除所有相关联的商品
	 * @param id 商品ID
	 */
	public void deleteAllSpecialAttr(int id);

	/**
	 * 添加 special特殊商品状态：多价、特殊商品
	 * @param siids
	 */
	void updateSpecialAttributeItem(List<String> siids);

	/**
	 * 修改显示状态：如果所有的多价和特殊商品只有一个前台显示，则不修改showStatus
	 * @param siids
	 */
	void updateShowStatus(List<String> siids);

	/**
	 * 取消所有旧的特殊商品specialStatus（第二位设为0）
	 * @param itemid
	 */
	void updateOldSpecialAttributeItem(int itemid);

	/**
	 * 设置原商品为终端页显示
	 * @param specialitemids
	 */
	void updateDefaultShowSpecialItem(List<String> specialitemids);

	/**
	 * 得到终端页显示的商品itemid与id（多价商品）的对应关系
	 * @param productid
	 * @return
	 */
	Map<String, Integer> getEndPageShowItemId(int productid);

	/**
	 * 同步数据（关联特殊商品时，如果有多价则会修改终端页显示属性）
	 * @param oldid
	 * @param newid
	 */
	void updateSpecialAttributeItemid(String oldid, Integer newid);

	/**
	 * 得到多价商品终端显示的商品ID
	 * @param id 任意一个多价id
	 * @return
	 */
	int getEndPageShowItemIdByManypriceId(int id);

	/**
	 * 没有设置过列表页显示的id's
	 * @param id
	 */
	List<Integer> getAddOldSpecialAttributeItem(int id);

	/**
	 * 添加列表页显示（没有设置过列表页显示的）
	 * @param ids
	 */
	void addOldSpecialAttributeItem(List<Integer> ids);

	/**
	 * 同步数据到动态表（把列表页显示的同步）<br>
	 * 取消所有相关联的商品显示<br>
	 * 把主属性商品在动态表中显示：status=1
	 * @param specialitemids
	 * @param id 当前关联商品id
	 */
	void updateShowStatusToDynamicTable(List<String> specialitemids, int id);

	/**
	 * 同步列表页显示到动态表
	 * @param ids
	 */
	void addShowStatusToDynamicTable(int id);

	/**
	 * 检查是否有包含未上架的商品（多价和原商品之中至少有一个上架）
	 * @param specialitemids 原商品ID
	 */
	boolean checkIsHaveShelvesOfGoods(List<String> specialitemids);

	/**
	 * 发布所有关联上架的商品（包括原商品）
	 * @param specialitemids
	 */
	void releaseAllShelvesItems(List<String> specialitemids);

	/**
	 * 如果分类设置了显示所有特殊属性，则把所有关联商品设置列表页显示
	 * @param specialitemids
	 */
	public void updateIsShowAllSpecialGoods(List<String> specialitemids);

	/**
	 * 得到商品: defid-productid-itemid-attrid-attr-attrval-sgid
	 * @param substr
	 * @param item
	 * @return List<SpecialAttribute>
	 */
	List<SpecialAttribute> getSpecialItemAttrAndVal(String[] substr, BtoCItems item);

	/**
	 * 添加特殊商品值
	 * @param specialItemAttrVal
	 */
	void insertSpecialItem(List<SpecialAttribute> specialItemAttrVal);

	/**
	 * 得到特殊商品属性的属性值
	 * @param id
	 * @return
	 */
	Map<String, String> getASpecialItemAttribute(int id);

	/**
	 * 取消主属性
	 * @param objects
	 */
	void escSpecialStatus(Object[] objects);

	/**
	 * 添加主属性
	 * @param specialitemids
	 */
	void addSpecialStatus(Object[] specialitemids);

	/**
	 * 显示多个特殊属性：更新到动态表显示
	 * @param defattr 显示多个的属性：动态表列名
	 * @param item
	 * @param itemPId 
	 * @param specialitemids2 
	 */
	void doShowManySpecialItems(List<String> defattr, BtoCItems item, String itemPId, List<String> specialitemids2);

	/**
	 * 设置显示颜色的属性
	 * @param args
	 */
	void setSpecialAttributeShowColor(Map<String, Object> args);

	/**
	 * 得到所有特殊属性
	 * @param itemid
	 * @return
	 */
	List<SpecialAttribute> getAllSpecialItemAttribute(int itemid);

	/**
	 * 得到所有特殊属性和图片
	 * @param itemid
	 * @return
	 */
	List<SpecialAttribute> getAllSpecialItemAttributeAndImg6(int itemid);

	/**
	 * 得到所有特殊属性排序
	 * @param id
	 * @return
	 */
	List<SpecialAttribute> getAllSpecialItemAttributePriority(int id);

	List<String[]> guideData();

	List<SpecialAttribute> getSpecialAttributeByItemid(int id);


}
