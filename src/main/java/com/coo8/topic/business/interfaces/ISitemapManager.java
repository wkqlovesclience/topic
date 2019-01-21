package com.coo8.topic.business.interfaces;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.sitemap.SeoSiteMap;
import com.coo8.topic.model.sitemap.SeoSiteMapBase;
import com.coo8.topic.model.sitemap.SeoSiteMapUpload;

public interface ISitemapManager {
	
	/**
	 * 导出Sitemap Excel
	 * @param fileName 文件名
	 * @param flag 		搜索引擎
	 * @param site     站点
	 * @param response 
	 */
	public void exportSitemapExcel(String fileName, String flag,String site, HttpServletResponse response);
	
	/**
	 * 网站地图表的操作
	 */
	public SeoSiteMap getSitemapById(Integer id);
	
	public int querySitemapTotalCount(Map<String, Object> map);
	
	public PaginatedList<SeoSiteMap> querySitemapForList(Map<String, Object> map);
	
	public int insertSitemap(SeoSiteMap sitemap);
	
	public int updateSitemap(SeoSiteMap sitemap);
	
	public int deleteSitemap(Integer id);
	
	public int changeSitemapStatus(Map<String, Object> map);
	
	/**
	 * 手动上传列表的操作
	 */
	public SeoSiteMapUpload getSiteMapUploadById(Integer id);
	
	public int quyeryUplaodTotalCount(Map<String, Object> map);
	
	public PaginatedList<SeoSiteMapUpload> queryUploadForList(Map<String, Object> map);
	
	public int insertUpload(SeoSiteMapUpload upload);
	
	public int deleteUplaod(Integer id);
	
	/**
	 * 基础信息管理：比如站点、搜索引擎管理
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
