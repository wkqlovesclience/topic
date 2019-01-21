package com.coo8.topic.business.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.catalog.Category;
import com.coo8.btoc.model.catalog.CategoryBrandBase;
import com.coo8.btoc.model.items.BtoCItems;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.AladdinKeywords;
import com.coo8.topic.model.AladdinKeywordsRef;

public interface IAladdinKeywordsManager {
	
	public AladdinKeywords getById(java.lang.Integer id);

	public int save(AladdinKeywords entity);
	
	public int deleteById(java.lang.Integer id);
	
	public int update(AladdinKeywords entity);
	
	public PaginatedList<AladdinKeywords> findLikeByMap(Map<String, Object> search);
	
	public List<AladdinKeywords> findPage(Map<String, Object> search);
	
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
	
	/**
	 *  �����������ҷ�����ʶ�Ӧ����ƷƷ����Ϣ�����������Ʒ�ƹ����� 
	 * @param search
	 * @return
	 */
	public List<AladdinKeywordsRef> findAllKeywordsRef(
			Map<String, Object> search);
	
	/**
	 *  ����IDɾ�� �������Ӧ����ƷƷ����Ϣ  �����������Ʒ�ƹ����� 
	 * @param id
	 */
	public int deleteKeywordsRefById(Integer id);
	
	/**
	 *  ������ƷƷ��ID������Ʒ��
	 * @param catalogArr
	 * @return
	 */
	public List<CategoryBrandBase> getCatalogListByIdMap(Integer[] catalogArr);
	
	/**
	 * ͨ��ATG�ӿڻ����Ʒ����
	 * @param ids һ������
	 * @return
	 */
	public List<Category> getATGCatalogListByIds(String ids);
}
