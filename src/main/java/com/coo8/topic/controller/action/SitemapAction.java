package com.coo8.topic.controller.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.file.FileUtils;
import com.coo8.topic.model.sitemap.SeoSiteMapBase;
import com.coo8.topic.model.sitemap.SeoSiteMapUpload;

@Namespace("/Sitemap")
public class SitemapAction extends SitemapBaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(SitemapAction.class);

	// field for uploadFile
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private String uploadUrl;
	private Integer nid;

	// SiteMap BaseInfo Type
	private static final String BASE_TYPE_SITE = "1";
	private static final String BASE_TYPE_SEARCH_ENGINE = "2";

	private boolean isEmpty(Object obj) {
		if (obj == null || "".equals(obj.toString())) {
			return true;
		}
		return false;
	}

	private boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	private void doGetSiteList() {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("type", BASE_TYPE_SITE);
		searchMap.put("isDelete", 0);
		siteBaseInfoList = sitemapManager.queryBaseInfoList(searchMap);
	}

	private void doGetSearchEngineList() {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("type", BASE_TYPE_SEARCH_ENGINE);
		searchMap.put("isDelete", 0);
		searchBaseInfoList = sitemapManager.queryBaseInfoList(searchMap);
	}

	/*
	 * 网站地图列表
	 */
	private Map<String, Object> getSitemapConditionMap() {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("site", getTopicSite());
		if (isNotEmpty(id)) {
			searchMap.put("id", id);
		}
		if (isNotEmpty(name)) {
			searchMap.put("name", name);
		}
		if (isNotEmpty(urlCatalog)) {
			searchMap.put("urlCatalog", urlCatalog);
		}
		if (isNotEmpty(status)) {
			searchMap.put("status", status);
		}
		searchMap.put("pageNumber", page_index);
		searchMap.put("pageSize", page_size);
		searchMap.put("sortColumns", "id asc");
		return searchMap;
	}

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/sitemap/list.jsp") })
	public String list() {
		logger.info("Sitemap list start!");
		Map<String, Object> searchMap = getSitemapConditionMap();
		logger.info("Sitemap list 查询数据参数：" + searchMap);
		sitemapList = sitemapManager.querySitemapForList(searchMap);
		doGetSearchEngineList();
		logger.info("Sitemap list end!");
		return SUCCESS;

	}

	@Action(value = "add", results = { @Result(name = "success", location = "/jsp/sitemap/add.jsp") })
	public String add() {
		logger.info("Sitemap add start!");
		doGetSiteList();
		logger.info("Sitemap add end!");
		return SUCCESS;
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/sitemap/edit.jsp") })
	public String edit() {
		logger.info("Sitemap edit start!");

		logger.info("Sitemap edit 修改数据id：" + id);
		if (isEmpty(id)) {
			return null;
		}
		doGetSiteList();
		sitemap = sitemapManager.getSitemapById(id);
		logger.info("Sitemap edit end!");
		return SUCCESS;
	}

	@Action(value = "insert", results = {
			@Result(name = "success", type = "redirect", location = "/Sitemap/list.action") })
	public String insert() {
		logger.info("Sitemap insert start!");
		if (isEmpty(sitemap)) {
			return null;
		}
		String creator = getLoginUserName();
		sitemap.setCreator(creator);
		sitemap.setCreateDate(new Date());
		sitemap.setCount(0);
		sitemap.setIsDelete(0);
		sitemapManager.insertSitemap(sitemap);
		logger.info("Sitemap insert end!");
		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", type = "redirect", location = "/Sitemap/list.action") })
	public String save() {
		logger.info("Sitemap save start!");
		if (isEmpty(sitemap)) {
			return null;
		}
		String updator = getLoginUserName();// (String)
		sitemap.setUpdator(updator);
		sitemap.setUpdateDate(new Date());
		sitemapManager.updateSitemap(sitemap);
		logger.info("Sitemap save end!");
		return SUCCESS;
	}

	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/Sitemap/list.action") })
	public String delete() {
		logger.info("Sitemap delete start!");
		logger.info("Sitemap delete 删除数据id：" + id);
		if (isEmpty(id)) {
			return null;
		}
		sitemapManager.deleteSitemap(id);
		logger.info("Sitemap delete end!");
		return SUCCESS;
	}

	@Action(value = "deleteBatch", results = {
			@Result(name = "success", type = "redirect", location = "/Sitemap/list.action") })
	public String deleteBatch() {
		logger.info("Sitemap deleteBatch start!");

		String ids = getRequest().getParameter("paramIds");
		logger.info("Sitemap deleteBatch 批量删除数据id：" + ids);
		if (isEmpty(ids)) {
			return null;
		}
		String[] paramIds = ids.split(";");
		for (String paramId : paramIds) {
			sitemapManager.deleteSitemap(Integer.valueOf(paramId));
		}
		logger.info("Sitemap deleteBatch end!");
		return SUCCESS;
	}

	@Action(value = "exportData")
	public void exportData() {
		logger.info("Sitemap exportData start!");
		String searchId = getRequest().getParameter("searchId");
		logger.info("Sitemap exportData searchId:" + searchId);
		if (isEmpty(searchId)) {
			return;
		}
		String fileName = "sitemap";
		SeoSiteMapBase tempBaseInfo = sitemapManager.getBaseInfoById(Integer.valueOf(searchId));
		String keyword = tempBaseInfo.getKeyword();
		fileName += "-" + keyword;

		logger.info("Sitemap exportData fileName:" + fileName);
		sitemapManager.exportSitemapExcel(fileName, keyword, getTopicSite(), getResponse());
		logger.info("Sitemap exportData end!");
	}

	/**
	 * 网站地图基础信息
	 */
	private Map<String, Object> getBaseInfoQueryCondition() {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (isNotEmpty(id)) {
			searchMap.put("id", id);
		}
		if (isNotEmpty(name)) {
			searchMap.put("name", name);
		}
		if (isNotEmpty(keyword)) {
			searchMap.put("keyword", keyword);
		}
		if (isNotEmpty(type)) {
			searchMap.put("type", type);
		}
		if (isNotEmpty(isDelete)) {
			searchMap.put("isDelete", isDelete);
		}
		searchMap.put("pageNumber", page_index);
		searchMap.put("pageSize", page_size);
		searchMap.put("sortColumns", "id asc");
		return searchMap;
	}

	private void doGetTypeBaseInfoList() {
		Map<String, Object> typeMap = new HashMap<String, Object>();
		typeMap.put("type", 0);
		typeMap.put("isDelete", 0);
		typeBaseInfoList = sitemapManager.queryBaseInfoList(typeMap);
	}

	@Action(value = "baseInfoList", results = { @Result(name = "success", location = "/jsp/sitemap/baseInfoList.jsp") })
	public String baseInfoList() {
		logger.info("Sitemap baseInfoList start!");
		Map<String, Object> searchMap = getBaseInfoQueryCondition();
		baseInfoList = sitemapManager.queryBaseInfoForList(searchMap);
		doGetTypeBaseInfoList();
		logger.info("Sitemap baseInfoList end!");
		return SUCCESS;
	}

	@Action(value = "baseInfoAdd", results = { @Result(name = "success", location = "/jsp/sitemap/baseInfoAdd.jsp") })
	public String baseinfoAdd() {
		logger.info("Sitemap baseInfoAdd start!");
		doGetTypeBaseInfoList();
		logger.info("Sitemap baseInfoAdd end!");
		return SUCCESS;
	}

	@Action(value = "baseInfoEdit", results = { @Result(name = "success", location = "/jsp/sitemap/baseInfoEdit.jsp") })
	public String baseInfoEdit() {
		logger.info("Sitemap baseInfoEdit start!");
		doGetTypeBaseInfoList();
		logger.info("Sitemap baseInfoEdit 修改数据id：" + id);
		if (isEmpty(id)) {
			return null;
		}
		sitemapBase = sitemapManager.getBaseInfoById(id);
		logger.info("Sitemap baseInfoEdit end!");
		return SUCCESS;
	}

	@Action(value = "baseInfoInsert")
	public String baseinfoInsert() {
		logger.info("Sitemap baseinfoInsert start!");
		int result = -1;
		sitemapBase.setIsDelete(0);
		result = sitemapManager.insertBaseInfo(sitemapBase);
		if (result > 0) {
			writeAjaxStr("success");
		} else {
			writeAjaxStr("error");
		}
		logger.info("Sitemap baseinfoInsert end!");
		return null;
	}

	@Action(value = "baseInfoSave")
	public String baseinfoSave() {
		logger.info("Sitemap baseInfoSave start!");
		int result = -1;
		if (isEmpty(sitemapBase)) {
			return null;
		}
		result = sitemapManager.updateBaseInfo(sitemapBase);
		if (result > 0) {
			writeAjaxStr("success");
		} else {
			writeAjaxStr("error");
		}
		logger.info("Sitemap baseInfoSave end!");
		return null;
	}

	@Action(value = "vaidateKeyword")
	public void vaidateKeyword() {
		logger.info("Sitemap vaidateKeyword start!");
		if (isEmpty(keyword)) {
			writeAjaxStr("noparam");
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		int count = sitemapManager.queryBaseInfoTotalCount(map);
		logger.info("Sitemap vaidateKeyword end!");
		if (count > 0) {
			writeAjaxStr("error");
			return;
		} else {
			writeAjaxStr("success");
			return;
		}

	}

	@Action(value = "deleteBatchBaseInfo", results = {
			@Result(name = "success", type = "redirect", location = "/Sitemap/baseInfoList.action") })
	public String deleteBatchBaseInfo() {
		logger.info("Sitemap deleteBatchBaseInfo start!");
		String ids = getRequest().getParameter("paramIds");
		logger.info("Sitemap deleteBatchBaseInfo 批量上传id：" + ids);
		if (isEmpty(ids)) {
			return null;
		}
		String[] paramIds = ids.split(";");
		for (String paramId : paramIds) {
			sitemapManager.deleteBaseInfo(Integer.valueOf(paramId));
		}
		logger.info("Sitemap deleteBatchBaseInfo end!");
		return SUCCESS;
	}

	@Action(value = "deleteBaseInfo", results = {
			@Result(name = "success", type = "redirect", location = "/Sitemap/baseInfoList.action") })
	public String deleteBaseInfo() {
		logger.info("Sitemap deleteBaseInfo start!");
		logger.info("Sitemap deleteBaseInfo 删除数据id：" + id);
		if (isEmpty(id)) {
			return null;
		}
		sitemapManager.deleteBaseInfo(id);
		logger.info("Sitemap deleteBaseInfo end!");
		return SUCCESS;
	}

	/**
	 * 网站地图：手动上传
	 */
	private Map<String, Object> getUploadQueryCondition() {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (isNotEmpty(id)) {
			searchMap.put("id", id);
		}
		if (isNotEmpty(filename)) {
			searchMap.put("filename", filename);
		}
		if (isNotEmpty(uploadUser)) {
			uploadUser = decode(uploadUser);
			searchMap.put("uploadUser", uploadUser);
		}
		searchMap.put("pageNumber", page_index);
		searchMap.put("pageSize", page_size);
		searchMap.put("sortColumns", "id desc");
		return searchMap;
	}

	@Action(value = "uploadList", results = { @Result(name = "success", location = "/jsp/sitemap/uploadList.jsp") })
	public String uploadList() {
		logger.info("Sitemap uploadList start!");
		Map<String, Object> searchMap = getUploadQueryCondition();
		logger.info("Sitemap uploadList 查询数据参数：" + searchMap);
		sitemapUploadList = sitemapManager.queryUploadForList(searchMap);
		logger.info("Sitemap uploadList end!");
		return SUCCESS;
	}

	@Action(value = "doUpload", results = { @Result(name = "success", location = "/jsp/sitemap/upload.jsp") })
	public String doUpload() {
		logger.info("Sitemap doUpload start!");
		logger.info("Sitemap doUpload end!");
		return SUCCESS;
	}

	@Action(value = "deleteUpload", results = {
			@Result(name = "success", type = "redirect", location = "/Sitemap/uploadList.action") })
	public String deleteUpload() {
		logger.info("Sitemap deleteUpload start!");
		logger.info("Sitemap deleteUpload id:" + id);
		if (isEmpty(id)) {
			return null;
		}
		sitemapManager.deleteUplaod(id);

		String gomeBasePath = ReloadableConfig.getProperty("gomeSitemapDir", "/app/gome_html") + "/map/";
		logger.info("Sitemap deleteUpload gomeBasePath:" + gomeBasePath);
		SeoSiteMapUpload ssmUpload = sitemapManager.getSiteMapUploadById(id);
		File deleteFile = new File(gomeBasePath + ssmUpload.getFilename());
		if (deleteFile.exists()) {
			deleteFile.delete();
		}
		logger.info("Sitemap deleteUpload end!");

		return SUCCESS;
	}

	@Action(value = "deleteBatchUpload", results = {
			@Result(name = "success", type = "redirect", location = "/Sitemap/uploadList.action") })
	public String deleteBatchUpload() {
		logger.info("Sitemap deleteBatchUpload start!");
		String ids = getRequest().getParameter("paramIds");
		logger.info("Sitemap deleteBatchUpload ids:" + ids);
		if (isEmpty(ids)) {
			return null;
		}

		String gomeBasePath = ReloadableConfig.getProperty("gomeSitemapDir", "/app/gome_html") + "/map/";
		logger.info("Sitemap deleteBatchUpload gomeBasePath:" + gomeBasePath);
		String[] paramIds = ids.split(";");
		for (String paramId : paramIds) {
			Integer intParamId = Integer.valueOf(paramId);
			sitemapManager.deleteUplaod(intParamId);
			SeoSiteMapUpload ssmUpload = sitemapManager.getSiteMapUploadById(intParamId);
			File deleteFile = new File(gomeBasePath + ssmUpload.getFilename());
			if (deleteFile.exists()) {
				deleteFile.delete();
			}
		}
		logger.info("Sitemap deleteBatchUpload end!");
		return SUCCESS;
	}

	@Action(value = "uploadXML", results = {
			@Result(name = "success", type = "redirect", location = "/Sitemap/uploadList.action") })
	public String uploadXML() {
		logger.info("Sitemap uploadXML start!");
		if (isEmpty(upload)) {
			return null;
		}
		String gomeBaseURL = ReloadableConfig.getProperty("GOME_BASE_URL", "http://www.gome.com.cn/");
		logger.info("Sitemap uploadXML gomeBaseURL:" + gomeBaseURL);
		String accessURL = gomeBaseURL + "map/" + uploadFileName;
		logger.info("Sitemap uploadXML accessURL:" + accessURL);

		String gomeBasePath = ReloadableConfig.getProperty("gomeSitemapDir", "/app/gome_html") + "/map/";
		logger.info("Sitemap uploadXML gomeBasePath:" + gomeBasePath);
		File copyFile = new File(gomeBasePath + uploadFileName);

		FileUtils.copyFile(upload, copyFile);

		SeoSiteMapUpload ssmUpload = new SeoSiteMapUpload();
		ssmUpload.setFilename(uploadFileName);
		ssmUpload.setIsDelete(0);
		ssmUpload.setUploadTime(new Date());
		ssmUpload.setPath(accessURL);
		ssmUpload.setSite(getTopicSite());
		ssmUpload.setUploadUser(getLoginUserName());
		sitemapManager.insertUpload(ssmUpload);

		logger.info("Sitemap uploadXML end!");
		return SUCCESS;
	}

	@Action(value = "sql", results = { @Result(name = "success", location = "/jsp/sitemap/sql.jsp") })
	public String sql() {
		logger.info("Sitemap sql start!");
		logger.info("Sitemap sql end!");
		return SUCCESS;
	}

	@Action(value = "doSql", results = {
			@Result(name = "success", type = "redirect", location = "/Sitemap/list.action") })
	public String doSql() {
		logger.info("Sitemap doSql start!");
		int uid = id;
		logger.info("Sitemap doSql uid:" + uid);
		if (null == id || null == nid)
			return SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("Sitemap doSql ==============================================================================");
		map.put("uid", id);
		map.put("nid", nid);
		logger.info("Sitemap doSql uid:" + uid + ",nid" + nid);
		sitemapManager.updateMapId(map);
		logger.info("Sitemap doSql ==============================================================================");
		logger.info("Sitemap doSql end!");
		return SUCCESS;
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

}
