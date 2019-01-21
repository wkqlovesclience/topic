package com.coo8.topic.business.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.topic.business.interfaces.ISitemapManager;
import com.coo8.topic.model.sitemap.SeoSiteMap;
import com.coo8.topic.model.sitemap.SeoSiteMapBase;
import com.coo8.topic.model.sitemap.SeoSiteMapUpload;
import com.coo8.topic.persistence.interfaces.ISitemapDAO;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class SitemapManagerImpl implements ISitemapManager {
	private ISitemapDAO sitemapDao;
	 private  static Logger logger = LoggerFactory.getLogger(SitemapManagerImpl.class);
	private static final int SEQUENCE_LENGTH = 5;

	private  int counter = 0;

	/**
	 * 导出Sitemap Excel
	 * 
	 * @param fileName
	 *            文件名
	 * @param flag
	 *            标记，关键字keyword，区分搜索引擎
	 * @param site
	 *            站点
	 * @param response
	 */
	@Override
	public void exportSitemapExcel(String fileName, String flag, String site, HttpServletResponse response) {
		WritableWorkbook workbook = null;
		OutputStream os = null;
		try {
			String fname = new String(fileName.getBytes("gbk"), "ISO-8859-1");

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=" + fname + ".csv");
			os = response.getOutputStream();
			workbook = Workbook.createWorkbook(os);

			// site domain
			SeoSiteMapBase siteMapBase = sitemapDao.getBaseInfoByKeyword(site);
			String currentSiteDomain = siteMapBase.getExpand() + "map/";

			List<SeoSiteMap> siteMapList = getCurrentSiteMap(site);
			for (SeoSiteMap item : siteMapList) {
				String prefixFileName = "";
				String sheetName = item.getName() + "(" + item.getUrlCatalog() + ")";
				if (item.getUrlCatalog().contains("mobile")) {
					String mobileSiteDomain = PropertiesUtils.getStringValueByKey("mobileSiteDomain","http://m.gome.com.cn/map/") + flag + "/";
					prefixFileName = mobileSiteDomain + item.getUrlCatalog() + "-" + flag + "-";
				} else {
					prefixFileName = currentSiteDomain + item.getUrlCatalog() + "-" + flag + "-";
				}
				Integer count = item.getCount();

				createSheet(workbook, sheetName, prefixFileName, count);
			}

			workbook.write();
			response.flushBuffer();
			os.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if(null != workbook){
					workbook.close();	
				}
			}  catch (IOException |WriteException e) {
				logger.error(e.getMessage(), e);
			}
			try {
				if(null != os){
					os.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * 获取sitemap集合
	 */
	private List<SeoSiteMap> getCurrentSiteMap(String site) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site", site);
		map.put("status", "Y");
		List<SeoSiteMap> siteMapList = sitemapDao.querySitemapByWheres(map);
		return siteMapList;
	}

	/**
	 * 添加 Sheet
	 * 
	 * @param workbook
	 *            工作簿
	 * @param sheetName
	 *            表单名称
	 * @param prefixFileName
	 *            文件名前缀
	 * @param fileCount
	 *            文件数量
	 */
	private void createSheet(WritableWorkbook workbook, String sheetName, String prefixFileName, Integer fileCount) {
		WritableSheet sheet = workbook.createSheet(sheetName, counter++);
		// 设置行高
		try {
			sheet.setRowView(0, 600, false);
			Label label = null;
			for (int i = 0; i < fileCount; i++) {
				String fileName = prefixFileName + generateSequence(i + 1) + ".xml";
				label = new jxl.write.Label(0, i, fileName);
				sheet.addCell(label);
			}
		}  catch (WriteException e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 生成文件名序列 100001、100002...
	 * 
	 * @param i
	 * @return
	 */
	private String generateSequence(int i) {
		return "1" + StringUtils.leftPad(String.valueOf(i), SEQUENCE_LENGTH, '0');
	}

	public ISitemapDAO getSitemapDao() {
		return sitemapDao;
	}

	public void setSitemapDao(ISitemapDAO sitemapDao) {
		this.sitemapDao = sitemapDao;
	}

	@Override
	public SeoSiteMap getSitemapById(Integer id) {
		return sitemapDao.getSitemapById(id);
	}

	@Override
	public int querySitemapTotalCount(Map<String, Object> map) {
		return sitemapDao.querySitemapTotalCount(map);
	}

	@Override
	public PaginatedList<SeoSiteMap> querySitemapForList(Map<String, Object> map) {
		return sitemapDao.querySitemapForList(map);
	}

	@Override
	public int insertSitemap(SeoSiteMap sitemap) {
		return sitemapDao.insertSitemap(sitemap);
	}

	@Override
	public int updateSitemap(SeoSiteMap sitemap) {
		return sitemapDao.updateSitemap(sitemap);
	}

	@Override
	public int deleteSitemap(Integer id) {
		return sitemapDao.deleteSitemap(id);
	}

	@Override
	public int changeSitemapStatus(Map<String, Object> map) {
		return sitemapDao.changeSitemapStatus(map);
	}

	@Override
	public SeoSiteMapUpload getSiteMapUploadById(Integer id) {
		return sitemapDao.getSiteMapUploadById(id);
	}

	@Override
	public int quyeryUplaodTotalCount(Map<String, Object> map) {
		return sitemapDao.quyeryUplaodTotalCount(map);
	}

	@Override
	public PaginatedList<SeoSiteMapUpload> queryUploadForList(Map<String, Object> map) {
		return sitemapDao.queryUploadForList(map);
	}

	@Override
	public int insertUpload(SeoSiteMapUpload upload) {
		return sitemapDao.insertUpload(upload);
	}

	@Override
	public int deleteUplaod(Integer id) {
		return sitemapDao.deleteUplaod(id);
	}

	@Override
	public SeoSiteMapBase getBaseInfoById(Integer id) {
		return sitemapDao.getBaseInfoById(id);
	}

	@Override
	public int queryBaseInfoTotalCount(Map<String, Object> map) {
		return sitemapDao.queryBaseInfoTotalCount(map);
	}

	@Override
	public PaginatedList<SeoSiteMapBase> queryBaseInfoForList(Map<String, Object> map) {
		return sitemapDao.queryBaseInfoForList(map);
	}

	@Override
	public int insertBaseInfo(SeoSiteMapBase baseInfo) {
		return sitemapDao.insertBaseInfo(baseInfo);
	}

	@Override
	public int updateBaseInfo(SeoSiteMapBase baseInfo) {
		return sitemapDao.updateBaseInfo(baseInfo);
	}

	@Override
	public int deleteBaseInfo(Integer id) {
		return sitemapDao.deleteBaseInfo(id);
	}

	@Override
	public SeoSiteMapBase getBaseInfoByKeyword(String keyword) {
		return sitemapDao.getBaseInfoByKeyword(keyword);
	}

	@Override
	public List<SeoSiteMapBase> queryBaseInfoList(Map<String, Object> map) {
		return sitemapDao.queryBaseInfoList(map);
	}

	@Override
	public int updateMapId(Map<String, Object> map) {
		return sitemapDao.updateMapId(map);
	}

}
