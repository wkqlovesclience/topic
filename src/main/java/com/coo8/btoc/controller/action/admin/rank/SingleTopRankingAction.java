package com.coo8.btoc.controller.action.admin.rank;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.business.interfaces.queue.QueueManager;
import com.coo8.btoc.business.interfaces.rank.ICategoryManager;
import com.coo8.btoc.business.interfaces.rank.ISingleTopRankingManager;
import com.coo8.btoc.model.rank.SingleTopRanking;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.topic.controller.action.BaseAction;

import net.sf.json.JSONObject;

@Namespace("/SingleTopRanking")
public class SingleTopRankingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(SingleTopRankingAction.class);

	private ISingleTopRankingManager singleTopRankingManager;

	private ICategoryManager categoryManager;

	private QueueManager queueManager;

	private Integer id;
	private String name;
	private String url;
	private Integer sort;
	private Integer parent;
	private Integer isShow;

	private Integer page_index = 1;
	private Integer page_size = 20;

	private SingleTopRanking ranking;
	private PaginatedList<SingleTopRanking> rankingList;

	// 发布需要的配置信息
	private static final int rank_home_rfid = PropertiesUtils.getIntValueByKey("rank_home_rfid");// 商品排行榜首页引用Id
	private static final int rank_home_rtype = PropertiesUtils.getIntValueByKey("rank_home_rtype");// 商品排行榜首页引用类型
	private static final int rank_home_templateId = PropertiesUtils.getIntValueByKey("rank_home_templateId");// 排行榜首页模板Id

	private static final int waprank_home_rfid = PropertiesUtils.getIntValueByKey("waprank_home_rfid");
	private static final int waprank_home_templateId = PropertiesUtils.getIntValueByKey("waprank_home_templateId");
	private static final int waprank_home_rtype = PropertiesUtils.getIntValueByKey("waprank_home_rtype");

	public Map<String, Object> queryParamMap() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (id != null) {
			paramMap.put("id", id);
		}
		if (name != null && !"".equals(name.trim())) {
			paramMap.put("name", name);
		}
		if (parent != null) {
			paramMap.put("parent", parent);
		}
		if (isShow != null) {
			paramMap.put("isShow", isShow);
		}
		return paramMap;
	}

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/rank/topRankingList.jsp") })
	public String list() {
		logger.info("SingleTopRanking list start!");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", page_index);
		paramMap.put("pageSize", page_size);
		paramMap.put("parent", 0);
		paramMap.put("sortColumns", "sortNum asc,createTime asc");

		logger.info("SingleTopRanking list 查询数据参数:" + paramMap);
		rankingList = singleTopRankingManager.getListByWheres(paramMap);

		boolean unHandleQueueFlag = categoryManager.isHasUnhandleQueueData();
		getRequest().setAttribute("unHandleQueueFlag", unHandleQueueFlag);

		logger.info("SingleTopRanking list 返回页面数据unHandleQueueFlag:" + unHandleQueueFlag);

		logger.info("SingleTopRanking list end!");
		return SUCCESS;
	}

	@Action(value = "subList", results = { @Result(name = "success", location = "/jsp/rank/singleTopRankingList.jsp") })
	public String subList() {
		logger.info("SingleTopRanking subList start!");
		Map<String, Object> paramMap = queryParamMap();
		paramMap.put("pageNumber", page_index);
		paramMap.put("pageSize", page_size);
		paramMap.put("sortColumns", " sortNum asc,createTime asc ");
		logger.info("SingleTopRanking subList 查询数据参数:" + paramMap);
		rankingList = singleTopRankingManager.getListByWheres(paramMap);

		SingleTopRanking tempRank = singleTopRankingManager.getById(parent);
		String parentName = tempRank.getName();
		getRequest().setAttribute("parentId", parent);
		getRequest().setAttribute("parentName", parentName);
		logger.info("SingleTopRanking subList 返回页面数据:parentId:" + parent + ",parentName" + parentName);

		logger.info("SingleTopRanking subList end!");
		return SUCCESS;
	}

	@Action(value = "add")
	public void add() {
		int result = -1;
		if (ranking == null) {
			writeAjaxStr(String.valueOf(result));
		}
		String operateType = getRequest().getParameter("operateType");
		String source = getRequest().getParameter("source");
		if ("1".equals(operateType)) {
			ranking.setCreator(getLoginUserName());
			if (!"subList".equals(source)) {
				ranking.setParent(0);
			}
			ranking.setIsShow(1);
			result = singleTopRankingManager.insert(ranking);
		} else if ("0".equals(operateType)) {
			ranking.setUpdator(getLoginUserName());
			result = singleTopRankingManager.update(ranking);
		}
		writeAjaxStr(String.valueOf(result));
	}

	@Action(value = "getRankingInfoById")
	public void getRankingInfoById() {
		if (id == null) {
			writeAjaxStr("NoParam");
		}
		ranking = singleTopRankingManager.getById(id);
		if (ranking == null) {
			writeAjaxStr("NoValue");
		}
		writeAjaxStr(JSONObject.fromObject(ranking).toString());
	}

	@Action(value = "delete")
	public void delete() {
		if (id == null) {
			writeAjaxStr("N");
			return;
		}
		singleTopRankingManager.delete(id);
		singleTopRankingManager.batchDeleteChildren(id);
		writeAjaxStr("Y");
	}

	@Action(value = "deleteItem")
	public void deleteItem() {
		if (id == null) {
			writeAjaxStr("N");
			return;
		}
		singleTopRankingManager.delete(id);
		writeAjaxStr("Y");
	}

	@Action(value = "batchDelete")
	public void batchDelete() {
		String ids = getRequest().getParameter("ids");
		if (ids == null || "".equals(ids.trim())) {
			writeAjaxStr("N");
			return;
		}
		String[] strIds = ids.split(",");
		for (String singleId : strIds) {
			singleTopRankingManager.delete(Integer.valueOf(singleId));
		}
		writeAjaxStr("Y");
	}

	@Action(value = "batchUpdate")
	public void batchUpdate() {
		String sendInfo = getRequest().getParameter("sendInfo");
		if (sendInfo == null || "".equals(sendInfo.trim())) {
			writeAjaxStr("N");
			return;
		}
		String[] strItems = sendInfo.split(";");
		for (String singleItem : strItems) {
			String[] items = singleItem.split(",");
			SingleTopRanking rankings = new SingleTopRanking();
			Integer curId = Integer.valueOf(items[0]);
			String curName = items[1];
			String curURL = items[2];
			Integer curSort = Integer.valueOf(items[3]);
			rankings.setId(curId);
			rankings.setName(curName);
			rankings.setUrl(curURL);
			rankings.setSort(curSort);
			rankings.setUpdator(getLoginUserName());

			singleTopRankingManager.update(rankings);
		}
		writeAjaxStr("Y");
	}

	@Action(value = "changeState")
	public void changeState() {
		if (id == null || isShow == null) {
			writeAjaxStr("N");
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isShow", isShow);
		map.put("id", id);
		singleTopRankingManager.changeState(map);
		writeAjaxStr("Y");
	}

	@Action(value = "isHasChildren")
	public void isHasChildren() {
		if (id == null) {
			writeAjaxStr("N");
			return;
		}
		int result = singleTopRankingManager.isHasChildren(id);
		if (result > 0) {
			writeAjaxStr("Y");
		} else {
			writeAjaxStr("N");
		}
	}

	/**
	 * 网页榜排行榜首页发布
	 */
	@Action(value = "publishRankingHomePage")
	public void publishRankingHomePage() {
		logger.info("SingleTopRanking publishRankingHomePage start!");
		queueManager.publish(rank_home_rfid, rank_home_templateId, 0, rank_home_rtype, 3);

		logger.info("SingleTopRanking publishRankingHomePage rank_home_rfid:" + rank_home_rfid
				+ ",rank_home_templateId:" + rank_home_templateId + ",rank_home_rtype:" + rank_home_rtype);

		logger.info("SingleTopRanking publishRankingHomePage end!");
		writeAjaxStr("Y");
	}

	/**
	 * 手机版排行榜首页发布
	 */
	@Action(value = "wapPublishRankingHomePage")
	public void wapPublishRankingHomePage() {
		logger.info("SingleTopRanking wapPublishRankingHomePage start!");
		queueManager.publish(waprank_home_rfid, waprank_home_templateId, 0, waprank_home_rtype, 3);

		logger.info("SingleTopRanking wapPublishRankingHomePage waprank_home_rfid:" + waprank_home_rfid
				+ ",waprank_home_templateId:" + waprank_home_templateId + ",waprank_home_rtype:" + waprank_home_rtype);

		logger.info("SingleTopRanking wapPublishRankingHomePage end!");
		writeAjaxStr("Y");
	}

	public ISingleTopRankingManager getSingleTopRankingManager() {
		return singleTopRankingManager;
	}

	public void setSingleTopRankingManager(ISingleTopRankingManager singleTopRankingManager) {
		this.singleTopRankingManager = singleTopRankingManager;
	}

	public ICategoryManager getCategoryManager() {
		return categoryManager;
	}

	public void setCategoryManager(ICategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	public QueueManager getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(QueueManager queueManager) {
		this.queueManager = queueManager;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
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

	public SingleTopRanking getRanking() {
		return ranking;
	}

	public void setRanking(SingleTopRanking ranking) {
		this.ranking = ranking;
	}

	public PaginatedList<SingleTopRanking> getRankingList() {
		return rankingList;
	}

	public void setRankingList(PaginatedList<SingleTopRanking> rankingList) {
		this.rankingList = rankingList;
	}

}
