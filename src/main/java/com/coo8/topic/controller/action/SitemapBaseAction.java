package com.coo8.topic.controller.action;

import java.util.List;

import com.coo8.topic.business.interfaces.ISitemapManager;
import com.coo8.topic.model.sitemap.SeoSiteMap;
import com.coo8.topic.model.sitemap.SeoSiteMapBase;
import com.coo8.topic.model.sitemap.SeoSiteMapUpload;

public class SitemapBaseAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	protected ISitemapManager sitemapManager;
	
	protected SeoSiteMap sitemap;
	protected SeoSiteMapUpload sitemapUpload;
	protected SeoSiteMapBase sitemapBase;
	
	protected List<SeoSiteMap> sitemapList;
	protected List<SeoSiteMapUpload> sitemapUploadList;
	protected List<SeoSiteMapBase> siteBaseInfoList;
	protected List<SeoSiteMapBase> searchBaseInfoList;
	protected List<SeoSiteMapBase> typeBaseInfoList;
	
	protected List<SeoSiteMapBase> baseInfoList;
	
	//field for Retrieval
	protected Integer id;
	protected String name;
	protected String urlCatalog;
	protected String status;
	protected String site;
	
	//field for Upload
	protected String uploadUser;
	protected String filename;
	
	//field for baseInfo
	protected String keyword;
	protected Integer type;
	protected Integer isDelete;
	
	//field for page
	protected Integer page_index = 1;
	protected Integer page_size  =10;
	
	public ISitemapManager getSitemapManager() {
		return sitemapManager;
	}
	public void setSitemapManager(ISitemapManager sitemapManager) {
		this.sitemapManager = sitemapManager;
	}
	public SeoSiteMap getSitemap() {
		return sitemap;
	}
	public void setSitemap(SeoSiteMap sitemap) {
		this.sitemap = sitemap;
	}
	public SeoSiteMapUpload getSitemapUpload() {
		return sitemapUpload;
	}
	public void setSitemapUpload(SeoSiteMapUpload sitemapUpload) {
		this.sitemapUpload = sitemapUpload;
	}
	public SeoSiteMapBase getSitemapBase() {
		return sitemapBase;
	}
	public void setSitemapBase(SeoSiteMapBase sitemapBase) {
		this.sitemapBase = sitemapBase;
	}
	public List<SeoSiteMap> getSitemapList() {
		return sitemapList;
	}
	public void setSitemapList(List<SeoSiteMap> sitemapList) {
		this.sitemapList = sitemapList;
	}
	public List<SeoSiteMapUpload> getSitemapUploadList() {
		return sitemapUploadList;
	}
	public void setSitemapUploadList(List<SeoSiteMapUpload> sitemapUploadList) {
		this.sitemapUploadList = sitemapUploadList;
	}
	public List<SeoSiteMapBase> getSiteBaseInfoList() {
		return siteBaseInfoList;
	}
	public void setSiteBaseInfoList(List<SeoSiteMapBase> siteBaseInfoList) {
		this.siteBaseInfoList = siteBaseInfoList;
	}
	public List<SeoSiteMapBase> getSearchBaseInfoList() {
		return searchBaseInfoList;
	}
	public void setSearchBaseInfoList(List<SeoSiteMapBase> searchBaseInfoList) {
		this.searchBaseInfoList = searchBaseInfoList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlCatalog() {
		return urlCatalog;
	}
	public void setUrlCatalog(String urlCatalog) {
		this.urlCatalog = urlCatalog;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public Integer getPage_index() {
		return page_index;
	}
	public void setPage_index(Integer page_index) {
		this.page_index = page_index;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
	
	public List<SeoSiteMapBase> getBaseInfoList() {
		return baseInfoList;
	}
	public void setBaseInfoList(List<SeoSiteMapBase> baseInfoList) {
		this.baseInfoList = baseInfoList;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public List<SeoSiteMapBase> getTypeBaseInfoList() {
		return typeBaseInfoList;
	}
	public void setTypeBaseInfoList(List<SeoSiteMapBase> typeBaseInfoList) {
		this.typeBaseInfoList = typeBaseInfoList;
	}
	
}
