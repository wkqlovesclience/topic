package com.coo8.btoc.business.interfaces.resource;

import java.util.List;

import com.coo8.btoc.model.resource.Resource;
import com.coo8.btoc.model.resource.ResourceCatalog;

public interface ResourceManager {
	
	void createResource(Resource resource);
	void updateResource(Resource resource);
	void deleteResource(Integer id);
	
	Resource getResourceById(Integer id);
	List<Resource> queryResource(Resource resource);
	List<Resource> queryAllResource();
	
	/**
	 * ��������Ʒλ
	 * @param resourceCatalog
	 */
	void createResourceCatalog(ResourceCatalog resourceCatalog);
	
	/**
	 * ��ѯ��Ʒλ����
	 * @param resourceCatalog
	 * @return
	 */
	List<ResourceCatalog> queryResourceCatalog(ResourceCatalog resourceCatalog);
	/**
	 * ������Ʒλ
	 * @param resourceCatalog
	 */
	void updateResourceCatalog(ResourceCatalog resourceCatalog);
	
	/**
	 * ɾ����Ʒλ
	 * @param resourceCatalog
	 */
	void deleteResourceCatalog(ResourceCatalog resourceCatalog);
	
	/**
     * ��Ӽ���Ĭ����Ʒλ
     */
	void insertPositions(ResourceCatalog resourceCatalog,int num);
	/**
	 * ɾ��/���¹��ؾ�̬ҳʱ��ɾ����̬ҳ�µĲ�Ʒλ����������
	 * @param htmlId
	 */
	void deleteResourceCatalogByHtmlId(Integer htmlId);
	/**
	 * ����ĳ����Ʒ�Ĳ�Ʒλ������Ϣ
	 * @param productid
	 */
	void updateProductRedis(Integer productId);
	
	/**
	 * �ֹ�˾Ϊ��Ʒλ�����Ʒ����д����
	 * @param rs
	 */
	void saveOrUpdateBranchComp(ResourceCatalog resourceCatalog) throws Exception;
	/**
	 * �ر�/��ĳ����Ʒλ
	 * @param resourceCatalog
	 */
	void deleteBranchItems(ResourceCatalog resourceCatalog);
	/**
     * �ֹ�˾ɾ����Ʒλ�е���Ʒ��������д����
     */
	void deleteAndUpdateCache(ResourceCatalog resourceCatalog) throws Exception;
	
	
	/**
	 * ���Ʋ�Ʒλ
	 * @param resourceCatalog
	 * @throws Exception
	 */
	void updateMoveUpPosition(ResourceCatalog resourceCatalog) throws Exception;
	
	/**
	 * ���Ʋ�Ʒλ
	 * @param resourceCatalog
	 * @param dataNum
	 * @throws Exception
	 */
	void updateMoveDownPosition(ResourceCatalog resourceCatalog, Integer dataNum) throws Exception;
}