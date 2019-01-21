package com.coo8.topic.persistence.interfaces;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.sitemap.SeoSiteMap;
import com.coo8.topic.model.sitemap.SeoSiteMapBase;
import com.coo8.topic.model.sitemap.SeoSiteMapUpload;

public interface ISitemapDAO {
	
	/**
	 * ��վ��ͼ��Ĳ���
	 */
	public SeoSiteMap getSitemapById(Integer id);
	
	public int querySitemapTotalCount(Map<String, Object> map);
	
	public PaginatedList<SeoSiteMap> querySitemapForList(Map<String, Object> map);
	
	public int insertSitemap(SeoSiteMap sitemap);
	
	public int updateSitemap(SeoSiteMap sitemap);
	
	public int deleteSitemap(Integer id);
	
	public int changeSitemapStatus(Map<String, Object> map);
	
	public List<SeoSiteMap> querySitemapByWheres(Map<String, Object> map);
	
	/**
	 * �ֶ��ϴ��б�Ĳ���
	 */
	public SeoSiteMapUpload getSiteMapUploadById(Integer id);
	
	public int quyeryUplaodTotalCount(Map<String, Object> map);
	
	public PaginatedList<SeoSiteMapUpload> queryUploadForList(Map<String, Object> map);
	
	public int insertUpload(SeoSiteMapUpload upload);
	
	public int deleteUplaod(Integer id);
	
	/**
	 * ������Ϣ��������վ�㡢�����������
	 */
	public SeoSiteMapBase getBaseInfoById(Integer id);
	public SeoSiteMapBase getBaseInfoByKeyword(String keyword);
	
	public int queryBaseInfoTotalCount(Map<String, Object> map);
	public PaginatedList<SeoSiteMapBase> queryBaseInfoForList(Map<String, Object> map);
	public List<SeoSiteMapBase> queryBaseInfoList(Map<String, Object> map);
	
	public int insertBaseInfo(SeoSiteMapBase baseInfo);
	
	public int updateBaseInfo(SeoSiteMapBase baseInfo);
	
	public int deleteBaseInfo(Integer id);
	
	public int updateMapId(Map<String, Object> map);
	
}
