/*
 * �ļ����� IAttributeDAO.java
 * 
 * �������ڣ� 2010-4-28
 *
 * Copyright(C) 2010, by wangyan.
 *
 * ԭʼ����: wangyan
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
 * ����dao
 *
 * @author wangyan
 *
 * @version $Revision$
 *
 * @since 2010-4-28
 */
public interface IAttributeDAO {

	/**
	 * �������ƺͲ����߲�ѯ
	 * */
    PaginatedList<Attribute> findAttributeByNameOper(String cataname, String name,String operator,int pageNo,int pageSize);
    
    /**
	 * �����������Ƶõ��������б�����like��
	 * */
    public List<Attributeenumvalue> findAttriEnumByAttName(String name) ;
    /**
	 * ����name�õ�����
	 * */
    
    public Attribute getAttribute(String name) ;
    /**
	 * �õ����Է���
	 * */
    public List<Attributegroup> findAttributegroup(Attributegroup attributegroup) ;
    
    /**
	 *ɾ�� attributegroup ����
	 */
    public int deleteAttributeGroupByDefinitionid(long definitionid);
    
    /**
	 *��������
	 */
    public int insertAttribute(Attribute attribute);
    
    /**
	 *�޸�����
	 */
    public int updateAttribute(Attribute attribute);
    
    /**
	 *ɾ������
	 */
    public int deleteAttribute(String attributeName);
    
    /**
	 *��������ѡ����
	 */
    public void insertAttributeEnumValue(Attributeenumvalue enumvalue);

    /**
	 *��������ѡ����
	 */
    public void updateAttributeEnumValue(Attributeenumvalue enumvalue);
    
    /**
	 *
	 */
    public void setAttributeEnumImageisNull(Attributeenumvalue enumvalue);
    
    /**
	 *ɾ������ѡ����
	 */
    public void deleteAttributeEnumValue(Attributeenumvalue enumvalue);
    
    /**
	 *ɾ������ѡ����
	 */
    public void deleteAttributeEnumValueByAttrname(String attributename);
    
    /**
	 *�õ�����ֵ��Դ
	 */
    public List<Attribute> findAttributeByFromwhere(String fromwhere);
    
    /**
	 *��attribute�в��Ҳ������Լ���fromwhere
	 */
    public List<String> findOtherAttribueFromwhere(String attributename);

    /**
	 *add attributegroup ����
	 */
    public int insertAttributeGroup(Attributegroup attributegroup);

    /**
	 *edit attributegroup ����
	 */
    public int updateAttributeGroup(Attributegroup attributegroup);

    /**
	 *���� id ɾ�� attributegroup ����
	 */
    public int deleteAttributeGroupById(long id);
    
    /**
	 *��ѯ�Ƿ��Ѿ�������Ϊ��Ʒ��������
	 */
    public Attributegroup getGift(Attributegroup attributegroup);

	public Attribute getAttributeById(int id);

	public List<Attributeenumvalue> getAttriEnumByAttId(int id);

	/**
	 * �õ���������
	 * @param id ��ƷID
	 * @return
	 */
	public List<Attribute> getAttributeByItemId(int id);

	/**
	 * �õ���������ֵ
	 * @param id ��ƷID
	 * @return
	 */
	public List<Attributeenumvalue> getAttriEnumByItemid(int id);

	/**
	 * �õ���Ʒ��Ӧ�Ķ�̬����
	 * @param itemid
	 * @return
	 */
	public String getTableNameByItemid(int itemid);

	/**
	 * �õ���Ʒ���Լ�����ֵ
	 * @param tableName
	 * @param itemid
	 * @return
	 */
	public Map getItemValue(String tableName, int itemid);

	/**
	 * �õ�������Ʒ���Լ�����ֵ
	 * @param id
	 * @param btocitems 
	 * @return
	 */
	public List<SpecialAttribute> getSpecialAttributeByItemid(int id, BtoCItems btocitems);

	/**
	 * �����������
	 * @param spa
	 * @return
	 */
	public int insertSpecialAttribute(SpecialAttribute spa);

	/**
	 * ɾ����������
	 * @param id ��Ʒid
	 */
	public void deleteSpecialAttribute(int id);

	/**
	 * ɾ��������������
	 * @param id ��Ʒid
	 */
	public void deleteSpecialAttributePriority(int id);

	/**
	 * ���������������
	 * @param id
	 * @param priority
	 */
	public void insertSpecialPriority(int id, Map<BtoCDefinitionattribute, Set> priority);

	/**
	 * �ƶ�������Ʒ����
	 * @param item
	 * @param definitionid2 
	 * @param attrname
	 * @param attrvalue
	 * @param ud �ƶ�ֵ
	 */
	public void moveSpecialAttributePriority(BtoCItems item, int definitionid2,
			String attrname, String attrvalue, int ud);

	/**
	 * ɾ������ֵ
	 * @param itemid
	 * @param attrname
	 * @param attrvalue
	 */
	public void deleteSpecialVal(int itemid, String attrname, String attrvalue);

	/**
	 * �õ�������Ʒ����
	 * @param id
	 * @return List<SpecialAttribute>
	 */
	public List<SpecialAttribute> getSpecialItemAttribute(int id);

	/**
	 * �õ���һ������������ID
	 * @return
	 */
	public int getSpecialNextProductid();

	/**
	 * �õ�������Ʒ��ID��productid��
	 * @param id ��ƷID
	 * @return
	 */
	public int getSpecialProductByItemid(int id);

	/**
	 * �õ��������������Ʒ
	 * @param id ��ƷID
	 * @return
	 */
	public List querySpecialAttrByItemid(int id);

	/**
	 * �õ��������������Ʒ����
	 * @param id ��ƷID
	 * @return
	 */
	public List querySpecialPriByItemid(int id);

	/**
	 * ��ѯ�Ѿ����ڵ���ƷID
	 * @return
	 */
	public List<Integer> findExsitSpecialItemid();

	/**
	 * �õ���Ʒ��ͼ��50*50
	 * @param itemids
	 * @return map<id:url>
	 */
	public Map<Integer, String> getItemMainPicByIds(List<String> itemids);

	/**
	 * ��ѯ����
	 * @param attribute
	 * @param pageIndex
	 * @param pagesize
	 * @return
	 */
	public PaginatedList<Attribute> findAttributeByNameOper(Attribute attribute,
			int pageIndex, int pagesize);

	/**
	 * �õ�����������������
	 * @param ids
	 * @return cat1,cat2,..
	 */
	public List<Attribute> getAttributeCataNames(Integer[] ids);

	/**
	 * ɾ���������������Ʒ
	 * @param id ��ƷID
	 */
	public void deleteAllSpecialAttr(int id);

	/**
	 * ��� special������Ʒ״̬����ۡ�������Ʒ
	 * @param siids
	 */
	void updateSpecialAttributeItem(List<String> siids);

	/**
	 * �޸���ʾ״̬��������еĶ�ۺ�������Ʒֻ��һ��ǰ̨��ʾ�����޸�showStatus
	 * @param siids
	 */
	void updateShowStatus(List<String> siids);

	/**
	 * ȡ�����оɵ�������ƷspecialStatus���ڶ�λ��Ϊ0��
	 * @param itemid
	 */
	void updateOldSpecialAttributeItem(int itemid);

	/**
	 * ����ԭ��ƷΪ�ն�ҳ��ʾ
	 * @param specialitemids
	 */
	void updateDefaultShowSpecialItem(List<String> specialitemids);

	/**
	 * �õ��ն�ҳ��ʾ����Ʒitemid��id�������Ʒ���Ķ�Ӧ��ϵ
	 * @param productid
	 * @return
	 */
	Map<String, Integer> getEndPageShowItemId(int productid);

	/**
	 * ͬ�����ݣ�����������Ʒʱ������ж������޸��ն�ҳ��ʾ���ԣ�
	 * @param oldid
	 * @param newid
	 */
	void updateSpecialAttributeItemid(String oldid, Integer newid);

	/**
	 * �õ������Ʒ�ն���ʾ����ƷID
	 * @param id ����һ�����id
	 * @return
	 */
	int getEndPageShowItemIdByManypriceId(int id);

	/**
	 * û�����ù��б�ҳ��ʾ��id's
	 * @param id
	 */
	List<Integer> getAddOldSpecialAttributeItem(int id);

	/**
	 * ����б�ҳ��ʾ��û�����ù��б�ҳ��ʾ�ģ�
	 * @param ids
	 */
	void addOldSpecialAttributeItem(List<Integer> ids);

	/**
	 * ͬ�����ݵ���̬�����б�ҳ��ʾ��ͬ����<br>
	 * ȡ���������������Ʒ��ʾ<br>
	 * ����������Ʒ�ڶ�̬������ʾ��status=1
	 * @param specialitemids
	 * @param id ��ǰ������Ʒid
	 */
	void updateShowStatusToDynamicTable(List<String> specialitemids, int id);

	/**
	 * ͬ���б�ҳ��ʾ����̬��
	 * @param ids
	 */
	void addShowStatusToDynamicTable(int id);

	/**
	 * ����Ƿ��а���δ�ϼܵ���Ʒ����ۺ�ԭ��Ʒ֮��������һ���ϼܣ�
	 * @param specialitemids ԭ��ƷID
	 */
	boolean checkIsHaveShelvesOfGoods(List<String> specialitemids);

	/**
	 * �������й����ϼܵ���Ʒ������ԭ��Ʒ��
	 * @param specialitemids
	 */
	void releaseAllShelvesItems(List<String> specialitemids);

	/**
	 * ���������������ʾ�����������ԣ�������й�����Ʒ�����б�ҳ��ʾ
	 * @param specialitemids
	 */
	public void updateIsShowAllSpecialGoods(List<String> specialitemids);

	/**
	 * �õ���Ʒ: defid-productid-itemid-attrid-attr-attrval-sgid
	 * @param substr
	 * @param item
	 * @return List<SpecialAttribute>
	 */
	List<SpecialAttribute> getSpecialItemAttrAndVal(String[] substr, BtoCItems item);

	/**
	 * ���������Ʒֵ
	 * @param specialItemAttrVal
	 */
	void insertSpecialItem(List<SpecialAttribute> specialItemAttrVal);

	/**
	 * �õ�������Ʒ���Ե�����ֵ
	 * @param id
	 * @return
	 */
	Map<String, String> getASpecialItemAttribute(int id);

	/**
	 * ȡ��������
	 * @param objects
	 */
	void escSpecialStatus(Object[] objects);

	/**
	 * ���������
	 * @param specialitemids
	 */
	void addSpecialStatus(Object[] specialitemids);

	/**
	 * ��ʾ����������ԣ����µ���̬����ʾ
	 * @param defattr ��ʾ��������ԣ���̬������
	 * @param item
	 * @param itemPId 
	 * @param specialitemids2 
	 */
	void doShowManySpecialItems(List<String> defattr, BtoCItems item, String itemPId, List<String> specialitemids2);

	/**
	 * ������ʾ��ɫ������
	 * @param args
	 */
	void setSpecialAttributeShowColor(Map<String, Object> args);

	/**
	 * �õ�������������
	 * @param itemid
	 * @return
	 */
	List<SpecialAttribute> getAllSpecialItemAttribute(int itemid);

	/**
	 * �õ������������Ժ�ͼƬ
	 * @param itemid
	 * @return
	 */
	List<SpecialAttribute> getAllSpecialItemAttributeAndImg6(int itemid);

	/**
	 * �õ�����������������
	 * @param id
	 * @return
	 */
	List<SpecialAttribute> getAllSpecialItemAttributePriority(int id);

	List<String[]> guideData();

	List<SpecialAttribute> getSpecialAttributeByItemid(int id);


}
