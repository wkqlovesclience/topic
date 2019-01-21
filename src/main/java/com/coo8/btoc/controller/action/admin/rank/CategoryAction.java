/**
 * @author tianyu-ds
 * @date 2014-2-16
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 商品排行榜类型管理
 */
package com.coo8.btoc.controller.action.admin.rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.aegis.type.java5.XmlAttribute;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.velocity.tools.config.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coo8.btoc.business.interfaces.queue.QueueManager;
import com.coo8.btoc.business.interfaces.rank.ICategoryManager;
import com.coo8.btoc.business.interfaces.rank.IFacetgroupItemManager;
import com.coo8.btoc.business.interfaces.rank.IFacetgroupManager;
import com.coo8.btoc.model.rank.Category;
import com.coo8.btoc.model.rank.FacetGroup;
import com.coo8.btoc.model.rank.FacetGroupItem;
import com.coo8.btoc.persistence.interfaces.rank.ICategoryDAO;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.common.utils.HttpClientUtil;
import com.coo8.topic.controller.action.BaseAction;
import com.gome.stage.interfaces.whale.ICategoryDBService;
import com.gome.stage.interfaces.whale.IProductInfoService;
import com.gome.stage.item.ProductBrandBean;
import com.gome.tapho.interfaces.spec.IProdSpecService;

import scala.collection.parallel.ParIterableLike.Forall;

@Namespace("/Category")
public class CategoryAction extends BaseAction {
	private static final long serialVersionUID = 780661548528042039L;
	private static Logger logger = LoggerFactory.getLogger(CategoryAction.class);

	private ICategoryManager categoryManager;
	private ICategoryDAO categoryDAO;
	private QueueManager queueManager;
	@Autowired
	private IFacetgroupItemManager facetGroupItemManager;
	@Autowired
	private IFacetgroupManager facetgroupManager;
	@Autowired
	private ICategoryDBService categoryDBService;
	@Autowired
	private IProductInfoService productInfoService;
	@Autowired
	private IProdSpecService prodSpecService;

	protected PaginatedList<Category> categoryList, categorys;

	protected Integer pageNumber = 1;
	protected Integer page_size = 10;

	protected Integer qparent_pageNumber = 1;

	protected String qcid, qid, qname, qis_show, qcreate_time, qcreator, qispublish, qparent_id;
	private JSONArray brandArray;

	// 发布需要的配置信息
	private static final int detail_rtype = PropertiesUtils.getIntValueByKey("rank_detail_rtype");// 商品排行榜详情页引用类型
	private static final int filter_rtyoe = PropertiesUtils.getIntValueByKey("rank_filter_rtype");// 商品排行榜筛选页引用类型

    private static final int brand_rtyoe = PropertiesUtils.getIntValueByKey("rank_brand_rtype");// 排行榜品牌排行榜页引用类型
	private static final int detail_templateId = PropertiesUtils.getIntValueByKey("rank_detail_templateId");// 排行榜详情页模板Id
	private static final int filter_templateId = PropertiesUtils.getIntValueByKey("rank_filter_templateId");// 排行榜筛选页模板Id
	private static final int brand_templateId = PropertiesUtils.getIntValueByKey("rank_brand_templateId");// 排行榜品牌排行榜页模板Id
	
	private static final int wap_detail_rtype = PropertiesUtils.getIntValueByKey("waprank_detail_rtype");// 移动商品排行榜详情页引用类型
	private static final int wap_filter_rtyoe = PropertiesUtils.getIntValueByKey("waprank_filter_rtype");// 移动商品排行榜筛选页引用类型
	private static final int wap_detail_templateId = PropertiesUtils.getIntValueByKey("waprank_detail_templateId");// 移动排行榜详情页模板Id
	private static final int wap_filter_templateId = PropertiesUtils.getIntValueByKey("waprank_filter_templateId");// 移动排行榜筛选页模板Id

	public Map<String, Object> queryParamMap() {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("pageNumber", pageNumber);
		paramMap.put("pageSize", page_size);

		if (qcid != null && !"".equals(qcid)) {
			paramMap.put("qcid", qcid);
		}
		if (qid != null && !"".equals(qid)) {
			paramMap.put("qid", qid);
		}
		if (qname != null && !"".equals(qname)) {
			paramMap.put("qname", qname);
		}
		if (qcreator != null && !"".equals(qcreator)) {
			paramMap.put("qcreator", qcreator);
		}
		if (qcreate_time != null && !"".equals(qcreate_time)) {
			paramMap.put("qcreate_time", qcreate_time);
		}
		if (qispublish != null && !"".equals(qispublish)) {
			paramMap.put("qispublish", qispublish);
		}
		if (qis_show != null && !"".equals(qis_show)) {
			paramMap.put("qis_show", qis_show);
		}
		return paramMap;
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/rank/edit.jsp"),
			@Result(name = "child", location = "/jsp/rank/childrenEdit.jsp") })
	public String edit() {
		logger.info("Category edit start!");
		String id = getStringParam("id");

		if (id != null) {
			Category category = categoryManager.getById(id);
			if (category != null) {
				getRequest().setAttribute("category", category);
			}
		}
		logger.info("Category edit id:" + id + ",qparent_id:" + qparent_id);
		if (qparent_id != null) {
			logger.info("Category edit end!");
			return "child";
		}

		logger.info("Category edit end!");
		return SUCCESS;
	}
	
	@Action(value = "updatequeue")
	public void updateQueue() {
		logger.info("Category updatequeue start!");
		categoryDAO.updateQuene();
		logger.info("Category updatequeue end!");
	}

	@Action(value = "categorys", results = { @Result(name = "success", location = "/jsp/rank/categorys.jsp") })
	public String categorys() {
		logger.info("Category categorys start!");
		Map<String, Object> paramMap = this.queryParamMap();
		String qisShow = getStringParam("qis_show");
		paramMap.put("qis_show", qisShow);
		paramMap.put("qparent_id", "homeStoreRootCategory");
		logger.info("Category categorys 查询数据参数：" + paramMap);
		categorys = categoryManager.list(paramMap);
		logger.info("Category categorys end!");
		return SUCCESS;
	}

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/rank/list.jsp") })
	public String list() {
		logger.info("Category list start!");
		Map<String, Object> paramMap = this.queryParamMap();
		paramMap.put("qparent_id", "homeStoreRootCategory");
		logger.info("Category list 查询数据参数：" + paramMap);
		categoryList = categoryManager.list(paramMap);
		logger.info("Category list end!");
		return SUCCESS;
	}
	
	@Action(value = "updateBrand",  results = {
			@Result(name = "success", type = "redirect", location = "/Category/list.action") })
	public String updateBrand() {
		logger.info("updateBrand start!");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("qparent_id", "homeStoreRootCategory");
		List<Category> firstCategoryList =  categoryManager.listAll(paramMap);
		logger.info("一级分类："+firstCategoryList.toString());
		List<Category> secondCategoryList = new ArrayList<Category>();
		List<FacetGroupItem> facetGroupItemList = new ArrayList<FacetGroupItem>();
		for(Category firstCategory : firstCategoryList){
			paramMap.put("qparent_id", firstCategory.getId());
			//获取三级分类
			secondCategoryList = categoryManager.listAll(paramMap);
			for(Category secondCategory : secondCategoryList){				
				paramMap.put("qcategoryid", secondCategory.getId());
				//获取三级分类下的分组
				List<FacetGroup> facetGroupList = facetgroupManager.facetGrouplist(paramMap);
				if(!facetGroupList.isEmpty()){
					//获取品牌分组的GroupId
					String qcatid =facetGroupList.get(0).getGroupId();
				    paramMap.put("qparent_id", qcatid);
				    paramMap.put("qcatid", secondCategory.getId());
				    //获取品牌分组下的品牌数据
					facetGroupItemList =  facetGroupItemManager.listAll(paramMap);
					//从接口获取三级下品牌接口数据brandMap
					List<String> catIdList  = new ArrayList<String>();
					Map<String, Object> brandMap = new HashMap<>();
			        catIdList.add(secondCategory.getId());
			        String dataStr;
					try {
						dataStr = HttpClientUtil.getJsonValue("http://apis.gome.com.cn/p/product/2/1/0/0/"+secondCategory.getId()+"/0/0/10/0/0?from=seo_search");
						JSONObject dataJson = JSONObject.parseObject(dataStr);
						brandArray = dataJson.getJSONObject("content").getJSONObject("facets").getJSONObject("brand").getJSONArray("items");
						for(int i = 0; i<brandArray.size() ; i++){
							String faceId = brandArray.getJSONObject(i).getString("id");
							String brandName = brandArray.getJSONObject(i).getString("value");					
							if(!faceId.isEmpty() && !brandName.isEmpty()){
								brandMap.put(faceId, brandName);
							}							
						}									
					} catch (Exception e) {
						logger.info("获取分类下品牌接口错误："+e.getMessage());
					}
			       if(!facetGroupItemList.isEmpty() && !brandMap.isEmpty()){
					   StringBuilder delStr = new StringBuilder();
			        	for(FacetGroupItem facetGroupItem : facetGroupItemList){
				        	if(brandMap.containsKey(facetGroupItem.getFacetId())){
				        		brandMap.remove(facetGroupItem.getFacetId());
				        	}else{
				        		facetGroupItemManager.delete(facetGroupItem.getId().toString());
				        		delStr.append(facetGroupItem.getFacetId()).append(":").append(facetGroupItem.getValue()).append(",");
				        	}
			        	}
					   logger.error("updateBrand delete categoryId="+secondCategory.getId()+" facetIds="+delStr);
			       }
			         if(!brandMap.isEmpty()){
						 StringBuilder addStr = new StringBuilder();
			        	 for(String key : brandMap.keySet()){
			        		 FacetGroupItem newFacetGroupItem = new FacetGroupItem();
			        		 newFacetGroupItem.setFacetId(key);
			        		 newFacetGroupItem.setValue(brandMap.get(key).toString());
			        		 newFacetGroupItem.setCatId(secondCategory.getId());
			        		 newFacetGroupItem.setIndex(3);
			        		 newFacetGroupItem.setParentId(qcatid);
			        		 newFacetGroupItem.setIsshow(0);  //新增默认不显示
			        		 newFacetGroupItem.setCreator(getLoginUserName());
			        		 newFacetGroupItem.setUpdator(getLoginUserName());
			        		 facetGroupItemManager.add(newFacetGroupItem);
							 addStr.append(key).append(":").append(brandMap.get(key)).append(",");
			        	 }
						 logger.error("updateBrand add categoryId="+secondCategory.getId()+" facetIds="+addStr);
			         }					
				}	 		    
			}			
		}
		logger.info("updateBrand end!");
		return SUCCESS;
	}

	
	@Action(value = "children", results = { @Result(name = "success", location = "/jsp/rank/children.jsp") })
	public String children() {
		logger.info("Category children start!");
		Map<String, Object> paramMap = this.queryParamMap();
		String qparentId = this.getStringParam("qparent_id");
		paramMap.put("qparent_id", qparentId);
		logger.info("Category children 查询数据参数：" + paramMap);
		categoryList = categoryManager.list(paramMap);
		logger.info("Category children end!");
		return SUCCESS;
	}

	@Action(value = "update", results = {
			@Result(name = "success", type = "redirect", location = "/Category/list.action?pageNumber=${pageNumber}") })
	public String update() {
		logger.info("Category update start!");
		String id = getStringParam("id");

		logger.info("Category update id:" + id);
		if (id != null) {
			Category category = categoryManager.getById(id);
			if (category != null) {
				String pid = getStringParam("cid");
				String parentId = getStringParam("parent_id");
				String categoryName = getStringParam("category_name");
				Integer sort = getIntParam("sort");
				Integer isShow = getIntParam("is_show");

				category.setId(pid);
				category.setParentId(parentId);
				category.setCategoryName(categoryName);
				category.setSort(sort);
				category.setIsShow(isShow);
				category.setUpdator(getLoginUserName());

				categoryManager.update(category);
			}
		}
		logger.info("Category update end!");
		return SUCCESS;
	}

	@Action(value = "childrenupdate")
	public void childrenUpdate() {
		String cid = getStringParam("cid");
		if (cid != null) {
			Category category = categoryManager.getById(cid);
			if (category != null) {
				String id = getStringParam("id");
				String parentId = getStringParam("parent_id");
				String categoryName = getStringParam("category_name");
				Integer sort = getIntParam("sort");
				Integer isShow = getIntParam("is_show");

				category.setId(id);
				category.setParentId(parentId);
				category.setCategoryName(categoryName);
				category.setSort(sort);
				category.setIsShow(isShow);
				category.setUpdator(getLoginUserName());

				categoryManager.update(category);
				writeAjaxStr("success");
			}
		}
	}

	@Action(value = "check")
	public void check() {
		String id = getStringParam("id");
		String categoryName = getStringParam("cname");
		String parentId = getStringParam("parent_id");

		if (categoryName != null) {
			Category category = categoryManager.validateCategory(categoryName);
			if (category != null) {
				writeAjaxStr("fail");
			} else {
				writeAjaxStr("success");
			}
		} else if (id != null) {
			Category category = categoryManager.getById(id);
			if (category == null) {
				writeAjaxStr("success");
			} else {
				writeAjaxStr("fail");
			}
		} else if (parentId != null) {
			Integer count = categoryManager.getByParentId(parentId);
			if (count > 0) {
				writeAjaxStr("fail");
			} else {
				writeAjaxStr("success");
			}
		}
	}

	@Action(value = "delete")
	public void delete() {
		List<String> ids = getStringListParam("ids");
		if (!ids.isEmpty()) {
			List<String> noIds = new ArrayList<String>();
			for (String id : ids) {
				Integer count = categoryManager.getByParentId(id);
				if (count > 0) {
					noIds.add(id);
				}
			}
			if (!noIds.isEmpty()) {
				writeAjaxStr(noIds.toString());
				return;
			} else {
				categoryManager.delete(ids);
				writeAjaxStr("success");
				return;
			}
		}

		writeAjaxStr("nodate");
	}

	@Action(value = "changeFirstCatShowState")
	public void changeFirstCatShowState() {
		List<String> ids = getStringListParam("ids");
		String type = getStringParam("type");
		if (!ids.isEmpty() && !type.isEmpty() ) {
			String catIds = "";
			for (String id : ids) {
				catIds += "'" + id + "',";
			}
			catIds = catIds.substring(0, catIds.lastIndexOf(","));

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("catIds", catIds);
			paramMap.put("isShow", type);

			categoryManager.changeFirstCatShowState(paramMap);
			writeAjaxStr("success");
		} else {
			writeAjaxStr("nodata");
		}
	}

	@Action(value = "add", results = {
			@Result(name = "success", type = "redirect", location = "/Category/list.action") })
	public String add() {
		String id = getStringParam("cid");
		String parentId = getStringParam("parent_id");
		String categoryName = getStringParam("category_name");
		Integer sort = getIntParam("sort");
		Integer isShow = getIntParam("is_show");

		Category category = new Category();
		category.setId(id);
		category.setParentId(parentId);
		category.setCategoryName(categoryName);
		category.setSort(sort);
		category.setIsShow(isShow);
		category.setCreator(getLoginUserName());
		category.setUpdator(getLoginUserName());
		if ("homeStoreRootCategory".equals(parentId)) {
			category.setLevel(1);
		} else {
			category.setLevel(3);
		}

		categoryManager.add(category);

		return SUCCESS;
	}

	@Action(value = "childrenadd", results = {
			@Result(name = "success", type = "redirect", location = "/Category/children.action") })
	public void categoryAdd() {
		String id = getStringParam("id");
		String parentId = getStringParam("parent_id");
		String categoryName = getStringParam("category_name");
		Integer sort = getIntParam("sort");
		Integer isShow = getIntParam("is_show");

		Category category = new Category();
		category.setId(id);
		category.setParentId(parentId);
		category.setCategoryName(categoryName);
		category.setSort(sort);
		category.setIsShow(isShow);
		category.setCreator(getLoginUserName());
		category.setUpdator(getLoginUserName());
		if ("homeStoreRootCategory".equals(parentId)) {
			category.setLevel(1);
		} else {
			category.setLevel(3);
		}

		categoryManager.add(category);
		writeAjaxStr("success");
	}

	@Action(value = "publish")
	public void publish() {
		String publishType = getRequest().getParameter("publishType");
		if (qid == null || "".equals(qid.trim())) {
			writeAjaxStr("N");
			return;
		}
		Category publishCategory = categoryManager.getById(qid);
		// 一级分类不能发布
		if ("homeStoreRootCategory".equals(publishCategory.getParentId())) {
			writeAjaxStr("N");
			return;
		}
		if (StringUtils.isNotBlank(publishType) && "1".equals(publishType)) {
			dealWapPublishProcess(publishCategory.getCid());
		} else {
			dealPublishProcess(publishCategory.getCid());
		}

		publishCategory.setIspublish(1);
		categoryManager.update(publishCategory);
		writeAjaxStr("Y");
	}

	private void dealPublishProcess(int rfid) {
		queueManager.publish(rfid, detail_templateId, 0, detail_rtype, 2);
		logger.info("Category dealPublishProcess rfid:" + rfid + ",detail_templateId:" + detail_templateId
				+ ",detail_rtype:" + detail_rtype);

		queueManager.publish(rfid, filter_templateId, 0, filter_rtyoe, 2);
		logger.info("Category dealPublishProcess rfid:" + rfid + ",filter_templateId:" + filter_templateId
				+ ",filter_rtyoe:" + filter_rtyoe);

        queueManager.publish(rfid, brand_templateId, 0, brand_rtyoe, 2);
        logger.info("Category dealPublishProcess rfid:" + rfid + ",brand_templateId:" + brand_templateId
            + ",brand_rtyoe:" + brand_rtyoe);

	}

	private void dealWapPublishProcess(int rfid) {
		queueManager.publish(rfid, wap_detail_templateId, 0, wap_detail_rtype, 2);
		logger.info("Category dealWapPublishProcess rfid:" + rfid + ",wap_detail_templateId:" + wap_detail_templateId
				+ ",wap_detail_rtype:" + wap_detail_rtype);

		queueManager.publish(rfid, wap_filter_templateId, 0, wap_filter_rtyoe, 2);
		logger.info("Category dealWapPublishProcess rfid:" + rfid + ",wap_filter_templateId:" + wap_filter_templateId
				+ ",wap_filter_rtyoe:" + wap_filter_rtyoe);
	}

	private void dealPublishPreviewProcess(int rfid) {

		queueManager.publish(rfid, detail_templateId, 7, detail_rtype, 2);

		logger.info("Category dealPublishPreviewProcess rfid:" + rfid + ",detail_templateId:" + detail_templateId
				+ ",detail_rtype:" + detail_rtype);

		queueManager.publish(rfid, filter_templateId, 7, filter_rtyoe, 2);

		logger.info("Category dealPublishPreviewProcess rfid:" + rfid + ",filter_templateId:" + filter_templateId
				+ ",filter_rtyoe:" + filter_rtyoe);
	}

	@Action(value = "publishBatch")
	public void publishBatch() {
		List<String> ids = getStringListParam("ids");
		if (!ids.isEmpty()) {
			for (String id : ids) {
				Category publishCategory = categoryManager.getById(id);
				dealPublishProcess(publishCategory.getCid());
				publishCategory.setIspublish(1);
				categoryManager.update(publishCategory);
			}
			writeAjaxStr("success");
			return;
		}

		writeAjaxStr("fail");
	}

	@Action(value = "publishAllBatch")
	public void publishAllBatch() {

		String publishType = getRequest().getParameter("publishType");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 设定所有父节点
		paramMap.put("qparent_id", "homeStoreRootCategory");
		// 获取所有的商品的一级分类
		List<Category> cateList = categoryManager.listAll(paramMap);
		if (!cateList.isEmpty()) {
			for (Category cat : cateList) {
				Map<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("qparent_id", cat.getId());
				List<Category> catechildenList = categoryManager.listAll(paramMap2);
				for (Category childenCat : catechildenList) {
					if (StringUtils.isNotBlank(publishType) && "1".equals(publishType)) {
						dealWapPublishProcess(childenCat.getCid());
					} else {
						dealPublishProcess(childenCat.getCid());
					}

					childenCat.setIspublish(1);
					categoryManager.update(childenCat);
				}
			}
			writeAjaxStr("success");
			return;
		}

		writeAjaxStr("fail");
	}

	@Action(value = "publishPreviewBatch")
	public void publishPreviewBatch() {
		List<String> ids = getStringListParam("ids");
		if (!ids.isEmpty() ) {
			for (String id : ids) {
				Category publishCategory = categoryManager.getById(id);
				dealPublishPreviewProcess(publishCategory.getCid());
			}
			writeAjaxStr("success");
			return;
		}

		writeAjaxStr("fail");
	}

	@Action(value = "basicCategoryInfo")
	public void basicCategoryInfo() {
		String searchId = getRequest().getParameter("searchId");
		String searchType = getRequest().getParameter("searchType");
		if (searchId == null || "".equals(searchId.trim()) || searchType == null || "".equals(searchType.trim())) {
			writeAjaxStr("noparam");
			return;
		}
		String result = "";
		if ("1".equals(searchType)) {
			Category curCategory = categoryManager.getByCid(Integer.valueOf(searchId));
			if (1 == curCategory.getLevel()) {
				result = "success:" + curCategory.getId() + "：" + curCategory.getCategoryName();
			} else if (3 == curCategory.getLevel()) {
				String parentId = curCategory.getParentId();
				Category parentCat = categoryManager.getById(parentId);
				result = "success:" + parentCat.getId() + "：" + parentCat.getCategoryName() + "&nbsp;->&nbsp;"
						+ curCategory.getId() + "：" + curCategory.getCategoryName();
			}
			writeAjaxStr(result);
			return;
		} else if ("2".equals(searchType)) {
			Category curCategory = categoryManager.getById(searchId);
			if (1 == curCategory.getLevel()) {
				result = "success:" + curCategory.getId() + "：" + curCategory.getCategoryName();
			} else if (3 == curCategory.getLevel()) {
				String parentId = curCategory.getParentId();
				Category parentCat = categoryManager.getById(parentId);
				result = "success:" + parentCat.getId() + "：" + parentCat.getCategoryName() + "&nbsp;->&nbsp;"
						+ searchId + "：" + curCategory.getCategoryName();
			}
			writeAjaxStr(result);
			return;
		} else {
			writeAjaxStr("noparam");
			return;
		}
	}

	/**
	 * @param param
	 * @return
	 * @desc 获取字符串参数数组
	 */
	public List<String> getStringListParam(String param) {
		String value = getRequest().getParameter(param);
		List<String> ids = new ArrayList<String>();
		try {
			if (value != null) {				
				String[] params = value.split("[,;]");
				for (String id : params) {
					if (!("".equals(id.trim()))) {
						ids.add(id.trim());
					}
				}
				return ids;
			}
			return ids;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ids;
		}
	}

	public ICategoryManager getCategoryManager() {
		return categoryManager;
	}

	public void setCategoryManager(ICategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public QueueManager getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(QueueManager queueManager) {
		this.queueManager = queueManager;
	}

	public PaginatedList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(PaginatedList<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getQparent_pageNumber() {
		return qparent_pageNumber;
	}

	public void setQparent_pageNumber(Integer qparent_pageNumber) {
		this.qparent_pageNumber = qparent_pageNumber;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public String getQcid() {
		return qcid;
	}

	public void setQcid(String qcid) {
		this.qcid = qcid;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public String getQis_show() {
		return qis_show;
	}

	public void setQis_show(String qis_show) {
		this.qis_show = qis_show;
	}

	public String getQcreate_time() {
		return qcreate_time;
	}

	public void setQcreate_time(String qcreate_time) {
		this.qcreate_time = qcreate_time;
	}

	public String getQcreator() {
		return qcreator;
	}

	public void setQcreator(String qcreator) {
		this.qcreator = qcreator;
	}

	public String getQparent_id() {
		return qparent_id;
	}

	public void setQparent_id(String qparent_id) {
		this.qparent_id = qparent_id;
	}

	public String getQispublish() {
		return qispublish;
	}

	public void setQispublish(String qispublish) {
		this.qispublish = qispublish;
	}

	public PaginatedList<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(PaginatedList<Category> categorys) {
		this.categorys = categorys;
	}
}
