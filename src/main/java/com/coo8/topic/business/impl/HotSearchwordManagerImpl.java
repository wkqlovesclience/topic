package com.coo8.topic.business.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.utils.HotWordsUtil;
import com.coo8.topic.business.interfaces.IHotSearchwordManager;
import com.coo8.topic.model.ErrorHotSearchword;
import com.coo8.topic.model.HotSearchword;
import com.coo8.topic.model.HotSearchwordVo;
import com.coo8.topic.model.ImportSearchLog;
import com.coo8.topic.persistence.interfaces.IHotSearchwordDAO;
import com.gome.stage.bean.category.Category;
import com.gome.stage.interfaces.whale.ICategoryDBService;
import com.gome.stage.interfaces.whale.IProductInfoService;
import com.gome.stage.item.BrandItem;

import gcache.clients.gedis.RoundRobinJedisPool;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class HotSearchwordManagerImpl implements IHotSearchwordManager {

	private static Logger logger = LoggerFactory.getLogger(HotSearchwordManagerImpl.class);
	private IHotSearchwordDAO hotSearchwordDAO;

	// ������Ϣservice(��ѯһ��������������������Ϣ)
	private ICategoryDBService categoryDBService;

	private IProductInfoService productInfoService;

	@Transactional
	@Override
	public void add(List<HotSearchword> hotSearchword) {

		hotSearchwordDAO.add(hotSearchword);

	}

	@Transactional
	@Override
	public void addLog(ImportSearchLog importSearchLog) {

		hotSearchwordDAO.addLog(importSearchLog);
	}

	@Transactional
	@Override
	public void delete(List<Integer> ids) {

		hotSearchwordDAO.delete(ids);
	}

	@Transactional
	@Override
	public void addErrorWords(List<ErrorHotSearchword> errorHotSearchWords) {

		hotSearchwordDAO.addErrorWords(errorHotSearchWords);
	}

	@Override
	public HotSearchword getById(Integer id) {
		return hotSearchwordDAO.getById(id);
	}

	@Override
	public List<HotSearchword> getByRangeId(Map<String, Object> paramMap) {
		return hotSearchwordDAO.getByRangeId(paramMap);
	}

	@Override
	public PaginatedList<HotSearchword> list(Map<String, Object> paramMap) {
		return hotSearchwordDAO.list(paramMap);
	}

	@Override
	public List<HotSearchword> listAll(Map<String, Object> paramMap) {
		return hotSearchwordDAO.listAll(paramMap);
	}
	
	@Override
	public List<Integer> listLimit(Map<String, Object> paramMap) {
		return hotSearchwordDAO.listLimit(paramMap);
	}

	@Override
	public List<HotSearchword> listHotSearch(Map<String, Object> paramMap) {
		return hotSearchwordDAO.listHotSearch(paramMap);
	}

	@Override
	public List<ErrorHotSearchword> listDownLog(Map<String, Object> paramMap) {
		return hotSearchwordDAO.listDownLog(paramMap);
	}

	@Override
	public PaginatedList<ImportSearchLog> listLog(Map<String, Object> paramMap) {
		return hotSearchwordDAO.listLog(paramMap);
	}

	@Override
	public Integer count(Map<String, Object> paramMap) {
		return hotSearchwordDAO.count(paramMap);
	}

	@Transactional
	@Override
	public void update(HotSearchword HotSearchword) {
		hotSearchwordDAO.update(HotSearchword);

	}

	@Transactional
	@Override
	public void publish(HotSearchword HotSearchword) {

		hotSearchwordDAO.publish(HotSearchword);

	}

	@Override
	public int getLastedInsertId() {
		return hotSearchwordDAO.getLastedInsertId();
	}

	public IHotSearchwordDAO getHotSearchwordDAO() {
		return hotSearchwordDAO;
	}

	public void setHotSearchwordDAO(IHotSearchwordDAO hotSearchwordDAO) {
		this.hotSearchwordDAO = hotSearchwordDAO;
	}

	@Override
	public void initReatedHotWords() throws Exception {
		logger.info("initReatedHotWords Start!");

		// һ������
		String siteId = "homeStoreRootCategory";
		List<Category> firstCategoryList = categoryDBService.getFirstCategoriesBySiteId(siteId);

		// ��������
		Map<String, List<Category>> subCategoryMap = new HashMap<String, List<Category>>();
		List<Category> subCategoryAllList = getSubCategory(firstCategoryList, subCategoryMap);
		// ���������µ�Ʒ��
		Map<String, List<Category>> thridCategoryMap = new HashMap<String, List<Category>>();
		List<Category> thirdCategoryAllList = getSubCategory(subCategoryAllList, thridCategoryMap);

		logger.info("initReatedHotWords����ȡ�������������Ϊ��" + thirdCategoryAllList.size());
		// ��ȡ���е�Ʒ��
		List<BrandItem> brandItemList = getIndexBrand();

		logger.info("initReatedHotWords����ȡƷ�Ƶ�����Ϊ��" + brandItemList.size());

		Jedis j = null;

		if (brandItemList != null && !brandItemList.isEmpty()) {
			int brandCount = 0;
			j = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_HOTWORDS_DUBBO_IP,
					HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);
			try {
				for (BrandItem brandItem : brandItemList) {
					String brandParam = brandItem.getBrandName();

					String paramJson = URLEncoder.encode(brandParam, "UTF-8");
					String keyBrand = paramJson.toLowerCase();

					Map<String, Object> searchBrand = new HashMap<String, Object>();
					searchBrand.put("title", brandParam);

					JSONArray jsonArray;

					jsonArray = getHotRelatedKeywords(searchBrand);
					if (brandCount % 100 == 0) {
						if (jsonArray != null) {

							j.set(keyBrand, jsonArray.toString());

						}
						j.close();

						j = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_HOTWORDS_DUBBO_IP,
								HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);
					} else {
						if (jsonArray != null) {

							j.set(keyBrand, jsonArray.toString());
						}
					}

					brandCount++;

				}
			} catch (Exception e) {
				throw e;
			} finally {
				j.close();
			}

		}

		if (thirdCategoryAllList != null && !thirdCategoryAllList.isEmpty()) {

			int cateCount = 0;

			j = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_HOTWORDS_DUBBO_IP,
					HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);

			try {
				for (Category category : thirdCategoryAllList) {
					String cateName = category.getCategoryName();

					String paramJson = URLEncoder.encode(cateName, "UTF-8");
					String keyCate = paramJson.toLowerCase();

					Map<String, Object> searchCate = new HashMap<String, Object>();
					searchCate.put("title", cateName);

					JSONArray jsonArray = getHotRelatedKeywords(searchCate);

					if (cateCount % 100 == 0) {
						if (jsonArray != null) {

							j.set(keyCate, jsonArray.toString());

						}
						j.close();
						j = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_HOTWORDS_DUBBO_IP,
								HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);
					} else {
						if (jsonArray != null) {

							j.set(keyCate, jsonArray.toString());

						}
					}

					cateCount++;

				}
			} catch (Exception e) {
				throw e;
			} finally {
				j.close();
			}

		}

		logger.info("initReatedHotWords End!");
	}

	/**
	 * ��ȡA-Z 0-9����Ʒ��
	 * 
	 * @return
	 */
	private List<BrandItem> getIndexBrand() {

		List<BrandItem> brandItems = productInfoService.getBrandsByCatId("homeStoreRootCategory",
				"coo8StoreRootCategory");

		return brandItems;

	}

	/**
	 * ��ѯ��������������
	 * 
	 * @param cateList
	 * @param categoryMap
	 * @return
	 */
	private List<Category> getSubCategory(List<Category> cateList, Map<String, List<Category>> categoryMap) {
		List<Category> nextCategoryAllList = new ArrayList<Category>();
		if (cateList != null && !cateList.isEmpty()) {
			for (Category category : cateList) {
				String categoryId = category.getCategoryId();
				List<Category> subCategoryList = categoryDBService.getSubCategoryInfoByCategoryId(categoryId);

				nextCategoryAllList.addAll(subCategoryList);
				categoryMap.put(categoryId, subCategoryList);

			}
		}

		return nextCategoryAllList;
	}

	// ��������ҳ�������������
	private JSONArray getHotRelatedKeywords(Map<String, Object> search) throws Exception {
		JSONArray jsonArray = null;

		search.put("site", "gome");

		List<HotSearchword> hWords = getHotkeyRelatedKeywords(search);
		String hotWordsBaseUrl = HotWordsUtil.HOT_WORDS_BASE_URL;

		if (hWords != null && !hWords.isEmpty()) {
			List<HotSearchwordVo> hWordsVo = new ArrayList<HotSearchwordVo>();

			for (HotSearchword hotSearchword : hWords) {
				HotSearchwordVo hotSearchwordVo = new HotSearchwordVo();
				String createTime = hotSearchword.getCreateTime();
				Integer id = hotSearchword.getId();

				String hotwordsUrl = hotWordsBaseUrl + createTime + "/" + id + "/";
				hotSearchword.setHotwordsUrl(hotwordsUrl);

				hotSearchwordVo.setHotwordsUrl(hotwordsUrl);
				hotSearchwordVo.setTitle(hotSearchword.getTitle());

				hWordsVo.add(hotSearchwordVo);

			}

			jsonArray = JSONArray.fromObject(hWordsVo);
		}

		return jsonArray;
	}

	/**
	 * ��ѯ������Ѵ�
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List<HotSearchword> getHotkeyRelatedKeywords(Map<String, Object> map) {

		// ����Ʒ�����ƻ�������������ģ����ѯ������
		List<HotSearchword> wordsList = this.hotSearchwordDAO.getHotkeyRelatedKeywords(map);

		int hotWordSize = 0;
		String tags[] = null;
		if (wordsList != null && !wordsList.isEmpty()) {
			hotWordSize = wordsList.size();

			// �����ѯ����طִʸ���С��10���͸��ݵ�һ�������ʼ��ִ�ģ����ѯ
			if (hotWordSize < 10) {
				HotSearchword hotSearchWord = wordsList.get(0);
				String tag = hotSearchWord.getTag();
				String title = hotSearchWord.getTitle();
				if (tag != "" && tag != null) {
					tags = tag.split(",");
				}

				Map<String, Object> search = new HashMap<String, Object>();
				search.put("title", title);
				search.put("site", "gome");
				if (tags != null) {
					for (int i = 0; i < tags.length; i++) {
						search.put("tag" + i, tags[i]);
					}
				}

				wordsList = this.hotSearchwordDAO.getHotkeyRelatedKeywords(map);
				hotWordSize = wordsList.size();

			}
			
			if (hotWordSize > 10) {
				wordsList = wordsList.subList(0, 10);
			}

		}
		

		return wordsList;
	}

	public ICategoryDBService getCategoryDBService() {
		return categoryDBService;
	}

	public void setCategoryDBService(ICategoryDBService categoryDBService) {
		this.categoryDBService = categoryDBService;
	}

	public IProductInfoService getProductInfoService() {
		return productInfoService;
	}

	public void setProductInfoService(IProductInfoService productInfoService) {
		this.productInfoService = productInfoService;
	}


}
