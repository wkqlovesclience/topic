/*
 * �ļ����� IBtoCCatalogManager.java
 * 
 * �������ڣ� 2010-4-29
 *
 * Copyright(C) 2010, by wangyan.
 *
 * ԭʼ����: wangyan
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
 * ����ҵ��ӿ�
 *
 * @author wangyan
 *
 * @version $Revision$
 *
 * @since 2010-4-29
 */
public interface IBtoCCatalogManager {

	//����������Ϣ
    int insertCatalog(BtoCCatalog catalog);
    //�޸ķ�����Ϣ
    int updateCatalog(BtoCCatalog catalog);
    //���ݷ���idɾ������
    int deleteCatalog(int catalogId);
    //�õ�����
    BtoCCatalog getCatalog(int catalogId);
    //���ݷ������Ʋ�ѯ���� ��like��
    PaginatedArrayList<CategoryBrandBase> findCatalogByName(String name,int pageNo,int pageSize);
    //�õ���һ��״̬Ϊ���õķ���
    List<BtoCCatalog> findCatalogChildBycid(int catalogId);
    //�õ���һ�����еķ���
    List<CategoryBrandBase> findCatalogChildBycidNoStatus(int catalogId);
    //�õ����и����ķ���
    List<BtoCCatalog> findCatalogFathersBycid(int catalogId);
    //�õ��������һ��
    BtoCCatalog getCatalogFahter(int catalogId);
    //�������ȼ�
    int updateCatalogPriority(int catalogId,int priority,String updater);
    //����״̬
    int updateCatalogStatus(int catalogId,int status,String updater);

    //���ϵ��
    void insertSeries(BtoCSeries series);
    //�޸�ϵ��
    void updateSeries(BtoCSeries series);
    //����ϵ���б�
    List<BtoCSeries> findSeries(int catalogId);
    //ɾ��ϵ��
    void deleteSeries(BtoCSeries series);
    //ͨ��catalogid��seriesname�õ�ϵ��
    BtoCSeries getSeriesByCidSname(BtoCSeries series);
    public List<BtoCCatalog> findCatalogList(Map<String,Object> search);
    //������ɫ
    /**
     * ������ɫ�б�
     * */
    public List<Color> findColor(int catalogId);
    /**
     * �����ɫ
     * */
    public void insertColor(Color color) ;
    
    /**
     *�޸���ɫ
     * */
    public void updateColor(Color color) ;
    
    /**
     * ������ɫ
     * */
    public Color findColorByid(int colorId) ;
    
    /**
     * ɾ����ɫ
     * */
    public void deleteColor(Color color) ;

    /**
     * �����ƶ�
     * @param id ���ƶ��ķ���
     * @param oldFid �ƶ����ķ���
     * @param newFid 
     * @param username ������
     */
    public void moveCatalog(int id,int oldFid, int newFid, String username) ;

    /**
     * ��ѯ����С��
     */
     public List<BtoCCatalog> selectSmallCatalogs();
     
     /**
 	 * ���ݸ���id���̼�id��ѯ�¼���������
 	 * 
 	 * @return ���� BtoCCatalog����
 	 * 
 	 */
 	public List<BtoCCatalog> getCatalogByfidAndCompanyid(int fid,int companyid) ;
 	/**
 	 * ͣ��Ʒ�Ʒ���
 	 * @param fatherid 
 	 * @param id
 	 * @param type
 	 * @param updater
 	 * @return
 	 */
    public int updateCatalogStatusForStop(int fatherid, int id, int type, String updater);
    
    /**
     * Ʒ�Ʋ�ѯ
     * @param fid ����Id
     * @param name ��ѯ����
     * @param pageIndex �ڼ�ҳ
     * @param pageSize ÿҳ��С
     * @return ��ҳ��
     */
	PaginatedArrayList<CategoryBrandBase> findBrandByName(int fid, String name, int pageIndex, int pageSize);
	List<CategoryBrandBase> findBrandBycidNoStatus(int fid);
	
	/**
	 * ��ѯ����Ʒ��
	 * @param id Ʒ��id
	 * @return
	 */
	CategoryBrandBase getCatalogBrand(int id);
	
	/**
	 * ����Ʒ��
	 * @param btoccatalog
	 */
	public void updateBrand(CategoryBrandBase btoccatalog);
	
	/**
	 * ����Ʒ��״̬
	 * @param fid ����ID
	 * @param id
	 * @param status
	 * @param updater
	 */
	public void updateBrandStatus(int fid, int id, int status, String updater);
	
	/**
	 * ���Ʒ��
	 * @param btoccatalog
	 * @return
	 */
	public int insertBrand(CategoryBrandBase btoccatalog);
	
	/**
	 * �޸�Ʒ��״̬
	 * @param fid
	 * @param catalogId
	 * @param priority
	 * @param string
	 */
	public void updateBrandPriority(int fid, int catalogId, int priority, String string);
	
	/**
	 * ����Ʒ��ͼƬ
	 * @param url
	 * @param brandid
	 * @param updater
	 */
	public void updateBrandPicture(String url, String brandid, String updater);
	
	/**
	 * ͨ�������ID�õ�����С���ࡢƷ��
	 * @param bigCatalogId �����IDͨ��С����ID�õ�Ʒ��


	 * @return JSON����{bigId:{smileId:(brandid,...),...}}
	 */
	public String getCatalogAndBrandByFid(int bigCatalogId);
	
	/**
	 * ͨ�������õ�����С����
	 * @param bigCatalogId �����ID
	 * @return JSON ��ʽ
	 */
	public String getSmallCatalogByFid(int bigCatalogId);
	
	/**
	 * ͨ�������õ�����С����
	 * @param bigCatalogId �����ID
	 * @return List
	 */
	public List<BtoCCatalog> getSmallCatalogByFidToList(int bigCatalogId);
	
	/**
	 * ͨ��С����ID�õ�Ʒ��
	 * @param smallCatalogId С����ID
	 * @return JSON ��ʽ
	 */
	public String getBrandByFid(int smallCatalogId);
	
	/**
	 * ͨ��С����ID�õ�Ʒ��
	 * @param smallCatalogId С����ID
	 * @return List<CategoryBrandBase>
	 */
	public List<CategoryBrandBase> getBrandByFidToList(int smallCatalogId);
	
	/**
	 * �õ����д����
	 * @return JSON ��ʽ
	 */
	public String getBigCatalog();
	
	/**
	 * �õ����д����
	 * @return List<BtoCCatalog>
	 */
	public List<BtoCCatalog> getBigCatalogToList();
	
	/**
	 * ����Ʒ�Ʒ���
	 * @param brandId Ʒ��ID
	 * @param fid ����ID
	 * @param brandServiceText ��������
	 */
	public int updateBrandService(int brandId, int fid, String brandServiceText);
	
	/**
	 * ���Ʒ�Ʒ�������
	 * @param brandId Ʒ��id
	 * @param fid ����id
	 * @return
	 */
	public String getBrandService(int brandId, int fid);
	
	/**
	 * ��ѯ�Ƿ����Ʒ��
	 * @param fid ����ID
	 * @param name
	 * @return boolean -1:�����ڣ�1���Ѵ��ڣ�2����ǰ�������Ѵ���
	 */
	public int checkBrandIsExisted(int fid, String name);
	
	/**
	 * ���Ʒ�ƹ���
	 * @param brandId
	 * @param catalogId
	 * @return
	 */
	public int addBrand(int brandId, int catalogId);
	
	/**
	 * �õ�Ʒ��Ӣ������
	 * @param brandid
	 * @return
	 */
	public String getBrandEnName(int brandid);
	
	/**
	 * �ƶ�Ʒ������
	 * @param fid ����ID�����м����Ҫ��
	 * @param id Ʒ��ID
	 * @param priority ��ǰ����
	 * @param ud ���ƣ���һ�������ud, ���ƣ���һ�������ud
	 * @param updater
	 * @return �ɹ�1��ʧ��0
	 */
	public int moveBrandPriority(int fid, int id, int priority, int ud, String updater);
	/**
	 * �ƶ���������
	 * @param fid
	 * @param id Ʒ��ID
	 * @param ud ���ƣ���һ�������ud, ���ƣ���һ�������ud
	 * @param priority ��ǰ����
	 * @param updater
	 * @return �ɹ�1��ʧ��0
	 */
	public int moveCatalogPriority(int fid, int id, int priority, int ud, String updater);
	
	/**
	 * ��ѯ�����µ�Ʒ��
	 * @param fid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PaginatedArrayList<CategoryBrandBase> findCatalogByNameAndFid(int fid, int pageIndex, int pageSize);
	
	/**
	 * �õ�����Ʒ��
	 * @return
	 */
	List<CategoryBrandBase> getAllBrands();
	
	/**
	 * ��ѯ�����Ƿ����
	 * @param decode
	 * @return
	 */
	public boolean checkCatalogIsExisted(String name);
	
	/**
	 * ��ѯ����Ӣ�����Ƿ����
	 * @param decode
	 * @return
	 */
	public boolean checkCatlogEnName(String decode);
	
	/**
	 * ��������ҳ
	 * @param id �����ID
	 */
	public void updatePublishCatalog(int id);
	
	/**
	 * ��ѯ�Ƿ����Ʒ�ƻ����
	 * @param movedFatherId �·���ID
	 * @param type 1��С���࣬2��Ʒ��
	 * @param ids
	 * @return �Ѿ����ڵķ����Ʒ������
	 */
	public String isExistCataOrBrand(int movedFatherId, int type, int[] ids);
	BtoCCatalog getFrontCatalog(int catalogId);
	
	/**
	 * �Ƿ��б�ҳ��ʾ���е���������
	 * @param catalogId ����ID
	 */
	void updateShowAllSpecialGoods(int catalogId);
	
	/**
	 * �޸ķ�����Ʒ�Ƶ�Ȩ��ֵ
	 * @param catalogId
	 * @param brandId
	 * @param value
	 */
	void updateCatalogBrandLevel(int catalogId, int brandId, int value);
	
	/**
	 * �õ�����Ʒ�Ƶ�Ȩ��ֵ
	 * @param catalogId
	 * @param brandId
	 * @return
	 */
	int getCatalogBrandLevel(int catalogId, int brandId);
	
	/**
	 * �л���ʾ���״̬
	 * @param catalogId
	 */
	void updateChangeShowItemSize(int catalogId);
}
