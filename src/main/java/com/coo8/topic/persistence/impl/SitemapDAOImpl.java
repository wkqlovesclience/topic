package com.coo8.topic.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.model.sitemap.SeoSiteMap;
import com.coo8.topic.model.sitemap.SeoSiteMapBase;
import com.coo8.topic.model.sitemap.SeoSiteMapUpload;
import com.coo8.topic.persistence.interfaces.ISitemapDAO;

public class SitemapDAOImpl extends SqlMapClientDaoSupport implements ISitemapDAO {

	private String getNameSpace(){
		return "Sitemap";
	}
	
	@Override
	public SeoSiteMap getSitemapById(Integer id) {
		return (SeoSiteMap) getSqlMapClientTemplate().queryForObject(getNameSpace()+".getSitemapById", id);
	}

	@Override
	public int querySitemapTotalCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(getNameSpace()+".querySitemapTotalCount", map);
	}

	@Override
	public PaginatedList<SeoSiteMap> querySitemapForList(Map<String, Object> map) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(getNameSpace()+".querySitemapTotalCount", map);
		PaginatedList<SeoSiteMap> paginatedArrayList = new PaginatedArrayList<SeoSiteMap>(count, (Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));
		@SuppressWarnings("unchecked")
		List<SeoSiteMap> list = getSqlMapClientTemplate().queryForList(getNameSpace()+".querySitemapForList",map,paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if(list != null){
			paginatedArrayList.addAll(list);
		}
		return paginatedArrayList;
	}

	
	@Override
	public int insertSitemap(SeoSiteMap sitemap) {
		return (Integer) getSqlMapClientTemplate().insert(getNameSpace()+".insertSitemap", sitemap);
	}

	@Override
	public int updateSitemap(SeoSiteMap sitemap) {
		return getSqlMapClientTemplate().update(getNameSpace()+".updateSitemap", sitemap);
	}

	@Override
	public int deleteSitemap(Integer id) {
		return getSqlMapClientTemplate().update(getNameSpace()+".deleteSitemap", id);
	}

	@Override
	public int changeSitemapStatus(Map<String, Object> map) {
		 return getSqlMapClientTemplate().update(getNameSpace()+".changeSitemapStatus", map);
	}

	@Override
	public SeoSiteMapUpload getSiteMapUploadById(Integer id) {
		return (SeoSiteMapUpload) getSqlMapClientTemplate().queryForObject(getNameSpace()+".getSiteMapUploadById", id);
	}

	@Override
	public int quyeryUplaodTotalCount(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(getNameSpace()+".quyeryUplaodTotalCount",map);
	}

	@Override
	public PaginatedList<SeoSiteMapUpload> queryUploadForList(Map<String, Object> map) {
		int count = quyeryUplaodTotalCount(map);
		PaginatedList<SeoSiteMapUpload> paginatedArrayList = new PaginatedArrayList<SeoSiteMapUpload>(count, (Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));
		@SuppressWarnings("unchecked")
		List<SeoSiteMapUpload> list = getSqlMapClientTemplate().queryForList(getNameSpace()+".queryUploadForList",map,paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if(list != null){
			paginatedArrayList.addAll(list);
		}
		return paginatedArrayList;
	}

	@Override
	public int insertUpload(SeoSiteMapUpload upload) {
		return (Integer) getSqlMapClientTemplate().insert(getNameSpace()+".insertUpload", upload);
	}

	@Override
	public int deleteUplaod(Integer id) {
		return getSqlMapClientTemplate().update(getNameSpace()+".deleteUplaod", id);
	}

	@Override
	public SeoSiteMapBase getBaseInfoById(Integer id) {
		return (SeoSiteMapBase) getSqlMapClientTemplate().queryForObject(getNameSpace()+".getBaseInfoById", id);
	}

	@Override
	public int queryBaseInfoTotalCount(Map<String, Object> map) {
		return  (Integer) getSqlMapClientTemplate().queryForObject(getNameSpace()+".queryBaseInfoTotalCount", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<SeoSiteMapBase> queryBaseInfoForList(Map<String, Object> map) {
		int count = queryBaseInfoTotalCount(map);
		PaginatedList<SeoSiteMapBase> paginatedArrayList = new PaginatedArrayList<SeoSiteMapBase>(count, (Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));
		List<SeoSiteMapBase> list = getSqlMapClientTemplate().queryForList(getNameSpace()+".queryBaseInfoForList",map,paginatedArrayList.getStartPos(), paginatedArrayList.getPageSize());
		if(list != null){
			paginatedArrayList.addAll(list);
		}
		return paginatedArrayList;
	}

	@Override
	public int insertBaseInfo(SeoSiteMapBase baseInfo) {
		return (Integer) getSqlMapClientTemplate().insert(getNameSpace()+".insertBaseInfo", baseInfo);
	}

	@Override
	public int updateBaseInfo(SeoSiteMapBase baseInfo) {
		return getSqlMapClientTemplate().update(getNameSpace()+".updateBaseInfo", baseInfo);
	}

	@Override
	public int deleteBaseInfo(Integer id) {
		return getSqlMapClientTemplate().delete(getNameSpace()+".deleteBaseInfo", id);
	}

	@Override
	public SeoSiteMapBase getBaseInfoByKeyword(String keyword) {
		return (SeoSiteMapBase) getSqlMapClientTemplate().queryForObject(getNameSpace()+".getBaseInfoByKeyword", keyword);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SeoSiteMapBase> queryBaseInfoList(Map<String, Object> map) {
		return  getSqlMapClientTemplate().queryForList(getNameSpace()+".queryBaseInfoForList",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SeoSiteMap> querySitemapByWheres(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList(getNameSpace()+".querySitemapForList",map);
	}

	@Override
	public int updateMapId(Map<String, Object> map) {
		return getSqlMapClientTemplate().update(getNameSpace()+".updateMapId", map);
	}
	
}
