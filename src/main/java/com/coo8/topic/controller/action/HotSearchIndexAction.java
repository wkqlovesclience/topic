package com.coo8.topic.controller.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.business.interfaces.queue.QueueManager;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.topic.business.interfaces.IHotSearchIndexManager;
import com.coo8.topic.business.interfaces.IHotSearchwordManager;
import com.coo8.topic.model.HotSearchword;

@Namespace("/HotSearchIndex")
public class HotSearchIndexAction extends HotSearchIndexBaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(HotSearchIndexAction.class);

	private IHotSearchIndexManager hotSearchIndexManager;
	private IHotSearchwordManager hotSearchwordManager;

	private QueueManager queueManager;

	// ������Ҫ��������Ϣ
	private static final int hot_home_rfid = PropertiesUtils.getIntValueByKey("hot_home_rfid");// �ȴ�������ҳ����Id
	private static final int hot_home_rtype = PropertiesUtils.getIntValueByKey("hot_home_rtype");// �ȴ�������ҳ��������
	private static final int hot_home_templateId = PropertiesUtils.getIntValueByKey("hot_home_templateId");// �ȴ�������ҳģ��Id

	private static final int hot_list_rfid = PropertiesUtils.getIntValueByKey("hot_list_rfid");
	private static final int hot_list_templateId = PropertiesUtils.getIntValueByKey("hot_list_templateId");
	private static final int hot_list_rtype = PropertiesUtils.getIntValueByKey("hot_list_rtype");

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/hotSearchIndex/list.jsp") })
	public String list() {
		logger.info("HotSearchIndex list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if (hotSearchIndexEntity != null) {
			String index = hotSearchIndexEntity.getHotSearchIndex();
			Integer hotSearchNo = hotSearchIndexEntity.getHotSearchId();
			if (null != index && !"".equals(index)) {
				search.put("hotSearchIndex", index.trim());
			}
			if (null != hotSearchNo && !"".equals(hotSearchNo.toString())) {
				search.put("hotSearchId", hotSearchNo);
			}
		}
		search.put("source", 0);// ֻ��ѯ�ֶ����
		search.put("site", getTopicSite());
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		search.put("sortColumns", "UPDATETIME DESC");
		logger.info("HotSearchIndex list ��ѯ������" + search);

		hotSearchIndexList = hotSearchIndexManager.findHotSearchIndexByMap(search);
		logger.info("HotSearchIndex list end!");
		return SUCCESS;
	}

	@Action(value = "add", results = {
			@Result(name = "success", type = "redirect", location = "/HotSearchIndex/list.action") })
	public String add() {
		logger.info("HotSearchIndex add start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		String pinyin = Chinese2PinyinUtil.parseChinese(hotSearchIndexEntity.getHotSearchTitle());
		String operator = getLoginUserName();
		hotSearchIndexEntity.setPinyin(pinyin);
		hotSearchIndexEntity.setSource(0);
		hotSearchIndexEntity.setOperator(operator);
		hotSearchIndexEntity.setStatus(1);
		hotSearchIndexEntity.setSite(getTopicSite());

		hotSearchIndexManager.insert(hotSearchIndexEntity);

		HotSearchword updateWords = hotSearchwordManager.getById(hotSearchIndexEntity.getHotSearchId());
		updateWords.setIndexState("Y");
		hotSearchwordManager.update(updateWords);
		logger.info("HotSearchIndex add end!");
		return SUCCESS;
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/hotSearchIndex/update.jsp") })
	public String edit() {
		logger.info("HotSearchIndex edit start!");
		hotSearchIndexEntity = hotSearchIndexManager.getById(id);
		logger.info("HotSearchIndex edit �޸��ȴ�����id��" + id);
		logger.info("HotSearchIndex edit end!");
		return SUCCESS;
	}

	@Action(value = "update", results = {
			@Result(name = "success", type = "redirect", location = "/HotSearchIndex/list.action") })
	public String update() {
		logger.info("HotSearchIndex update start!");
		String currentUserName = getLoginUserName();
		hotSearchIndexEntity.setOperator(currentUserName);
		hotSearchIndexManager.update(hotSearchIndexEntity);
		logger.info("HotSearchIndex update end!");
		return SUCCESS;
	}

	@Action(value = "deleteBatch", results = {
			@Result(name = "success", type = "redirect", location = "/HotSearchIndex/list.action") })
	public String deleteBatch() {
		logger.info("HotSearchIndex deleteBatch start!");
		String paramIds = getRequest().getParameter("paramIds");
		logger.info("HotSearchIndex deleteBatch �ȴ�����id��" + paramIds);
		String[] ids = paramIds.split(";");
		for (int i = 0; i < ids.length; i++) {
			hotSearchIndexManager.delete(Integer.valueOf(ids[i]));
		}
		logger.info("HotSearchIndex deleteBatch end!");
		return SUCCESS;
	}

	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/HotSearchIndex/list.action") })
	public String delete() {
		logger.info("HotSearchIndex delete start!");
		hotSearchIndexManager.delete(id);
		logger.info("HotSearchIndex delete �ȴ�����id" + id);
		logger.info("HotSearchIndex delete end!");
		return SUCCESS;
	}

	@Action(value = "getTitleName")
	public void getTitleName() {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotSearchId", hotSearchId);
		queryMap.put("site", getTopicSite());

		Integer hotSearchwordCheck = hotSearchIndexManager.getSpecificHotSearchCheck(queryMap);
		if (hotSearchwordCheck == null) {
			writeAjaxStr("ERROR�����ѱ�Ų����ڣ���ȷ�Ϻ�������");
			return;
		} else if (hotSearchwordCheck == 0) {
			writeAjaxStr("ERROR������������δ�������뷢�������������");
			return;
		}

		queryMap.put("source", 0); // ֻ�����ֶ����
		int hotSearchIndexCount = hotSearchIndexManager.getSpecificHotSearchIndexCount(queryMap);
		if (hotSearchIndexCount != 0) {
			writeAjaxStr("ERROR�������Ѵ��Ѿ���ӹ������ˣ������ظ����");
		} else {
			String hotSearchTitleName = hotSearchIndexManager.getHotSearchTitleName(hotSearchId);
			writeAjaxStr(hotSearchTitleName);
		}
	}

	/**
	 * �����ȴ�ר����ҳ
	 */
	@Action(value = "publishHotSearchHomePage")
	public void publishHotSearchHomePage() {

		logger.info("HotSearchIndex publishHotSearchHomePage start!");
		dealPublishProcess(hot_home_rfid, hot_home_templateId, hot_home_rtype);
		logger.info("HotSearchIndex publishHotSearchHomePage hot_home_rfid:" + hot_home_rfid + ",hot_home_templateId"
				+ hot_home_templateId + ",hot_home_rtype" + hot_home_rtype);
		dealPublishProcess(hot_list_rfid, hot_list_templateId, hot_list_rtype);
		logger.info("HotSearchIndex publishHotSearchHomePage hot_list_rfid:" + hot_list_rfid + ",hot_list_templateId"
				+ hot_list_templateId + ",hot_list_rtype" + hot_list_rtype);
		int result = 0;
		writeAjaxStr(String.valueOf(result));
		logger.info("HotSearchIndex publishHotSearchHomePage end!");
	}

	private void dealPublishProcess(int rfid, int templateId, int rtype) {
		queueManager.publish(rfid, templateId, 0, rtype, 3);

	}

	/**
	 * �ֻ��˷�������ר����ҳ
	 */
	@Action(value = "publishMobileHotSearchHomePage")
	public void publishMobileHotSearchHomePage() {
		logger.info("HotSearchIndex publishMobileHotSearchHomePage start!");
		int result = hotSearchIndexManager.publishMobileHotSearchHomePage();

		writeAjaxStr(String.valueOf(result));
		logger.info("HotSearchIndex publishMobileHotSearchHomePage resutl:" + result);
		logger.info("HotSearchIndex publishMobileHotSearchHomePage end!");
	}

	/**
	 * ��������ר���б�ҳ:��ҳ
	 */
	@Action(value = "publishAllHotSearchListPage")
	public void publishAllHotSearchListPage() {
		logger.info("HotSearchIndex publishAllHotSearchListPage start!");
		int result = hotSearchIndexManager.publishAllHotSearchListPage();
		writeAjaxStr(String.valueOf(result));
		logger.info("HotSearchIndex publishAllHotSearchListPage result:" + result);
		logger.info("HotSearchIndex publishAllHotSearchListPage end!");
	}

	public QueueManager getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(QueueManager queueManager) {
		this.queueManager = queueManager;
	}

	public IHotSearchwordManager getHotSearchwordManager() {
		return hotSearchwordManager;
	}

	public void setHotSearchwordManager(IHotSearchwordManager hotSearchwordManager) {
		this.hotSearchwordManager = hotSearchwordManager;
	}

	public IHotSearchIndexManager getHotSearchIndexManager() {
		return hotSearchIndexManager;
	}

	public void setHotSearchIndexManager(IHotSearchIndexManager hotSearchIndexManager) {
		this.hotSearchIndexManager = hotSearchIndexManager;
	}

}
