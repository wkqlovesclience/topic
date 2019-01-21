package com.coo8.topic.controller.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.topic.business.interfaces.IHotKeywordManager;
import com.coo8.topic.business.interfaces.IHotkeyIndexManager;
import com.coo8.topic.model.HotKeyword;

@Namespace("/HotkeyIndex")
public class HotkeyIndexAction extends HotkeyIndexBaseAction {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(HotkeyIndexAction.class);

	private IHotkeyIndexManager hotkeyIndexManager;
	private IHotKeywordManager hotKeywordManager;

	public IHotKeywordManager getHotKeywordManager() {
		return hotKeywordManager;
	}

	public void setHotKeywordManager(IHotKeywordManager hotKeywordManager) {
		this.hotKeywordManager = hotKeywordManager;
	}

	public IHotkeyIndexManager getHotkeyIndexManager() {
		return hotkeyIndexManager;
	}

	public void setHotkeyIndexManager(IHotkeyIndexManager hotkeyIndexManager) {
		this.hotkeyIndexManager = hotkeyIndexManager;
	}

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/hotkeyIndex/list.jsp") })
	public String list() {
		logger.info("HotkeyIndex list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if (hotkeyIndexEntity != null) {
			String index = hotkeyIndexEntity.getHotkeyIndex();
			Integer hotkeyNo = hotkeyIndexEntity.getHotkeyId();
			if (null != index && !"".equals(index)) {
				search.put("hotkeyIndex", index.trim());
			}
			if (null != hotkeyNo && !"".equals(hotkeyNo.toString())) {
				search.put("hotkeyId", hotkeyNo);
			}
		}
		search.put("source", 0);// ֻ��ѯ�ֶ����
		search.put("site", getTopicSite());
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		search.put("sortColumns", "UPDATETIME DESC");

		logger.info("HotkeyIndex list ��ѯ��������������" + search);
		hotkeyIndexList = hotkeyIndexManager.findHotkeyIndexByMap(search);
		logger.info("HotkeyIndex list end!");
		return SUCCESS;
	}

	@Action(value = "add", results = {
			@Result(name = "success", type = "redirect", location = "/HotkeyIndex/list.action") })
	public String add() {
		logger.info("HotkeyIndex add start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		String pinyin = Chinese2PinyinUtil.parseChinese(hotkeyIndexEntity.getHotkeyTitle());
		String operator = getLoginUserName(); // (String)
		hotkeyIndexEntity.setPinyin(pinyin);
		hotkeyIndexEntity.setSource(0);
		hotkeyIndexEntity.setOperator(operator);
		hotkeyIndexEntity.setStatus(1);
		hotkeyIndexEntity.setSite(getTopicSite());

		hotkeyIndexManager.insert(hotkeyIndexEntity);

		HotKeyword updateWords = hotKeywordManager.getById(hotkeyIndexEntity.getHotkeyId());
		updateWords.setIndexState("Y");
		hotKeywordManager.update(updateWords);
		logger.info("HotkeyIndex add end!");
		return SUCCESS;
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/hotkeyIndex/update.jsp") })
	public String edit() {
		logger.info("HotkeyIndex edit start!");
		hotkeyIndexEntity = hotkeyIndexManager.getById(id);
		logger.info("HotkeyIndex edit �޸���������id��" + id);
		logger.info("HotkeyIndex edit end!");
		return SUCCESS;
	}

	@Action(value = "update", results = {
			@Result(name = "success", type = "redirect", location = "/HotkeyIndex/list.action") })
	public String update() {
		logger.info("HotkeyIndex update start!");
		String currentUserName = getLoginUserName();// (String)
		logger.info("HotkeyIndex update �޸����������ˣ�" + currentUserName);
		hotkeyIndexEntity.setOperator(currentUserName);
		hotkeyIndexManager.update(hotkeyIndexEntity);
		logger.info("HotkeyIndex update end!");
		return SUCCESS;
	}

	@Action(value = "deleteBatch", results = {
			@Result(name = "success", type = "redirect", location = "/HotkeyIndex/list.action") })
	public String deleteBatch() {
		logger.info("HotkeyIndex deleteBatch start!");
		String paramIds = getRequest().getParameter("paramIds");
		logger.info("HotkeyIndex deleteBatch ����ɾ����������id" + paramIds);
		String[] ids = paramIds.split(";");
		for (int i = 0; i < ids.length; i++) {
			hotkeyIndexManager.delete(Integer.valueOf(ids[i]));
		}
		logger.info("HotkeyIndex deleteBatch end!");
		return SUCCESS;
	}

	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/HotkeyIndex/list.action") })
	public String delete() {
		logger.info("HotkeyIndex delete start!");
		hotkeyIndexManager.delete(id);
		logger.info("HotkeyIndex delete ɾ����������id��" + id);
		logger.info("HotkeyIndex delete end!");
		return SUCCESS;
	}

	@Action(value = "getTitleName")
	public void getTitleName() {
		logger.info("HotkeyIndex getTitleName start!");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotkeyId", hotkeyId);
		queryMap.put("site", getTopicSite());

		Integer hotkeywordCheck = hotkeyIndexManager.getSpecificHotkeyCheck(queryMap);
		if (hotkeywordCheck == null) {
			writeAjaxStr("ERROR�����ѱ�Ų����ڣ���ȷ�Ϻ�������");
			return;
		} else if (hotkeywordCheck == 0) {
			writeAjaxStr("ERROR������������δ�������뷢�������������");
			return;
		}

		queryMap.put("source", 0); // ֻ�����ֶ����
		int hotkeyIndexCount = hotkeyIndexManager.getSpecificHotkeyIndexCount(queryMap);
		if (hotkeyIndexCount != 0) {
			writeAjaxStr("ERROR�������Ѵ��Ѿ���ӹ������ˣ������ظ����");
		} else {
			String hotkeyTitleName = hotkeyIndexManager.getHotkeyTitleName(hotkeyId);
			writeAjaxStr(hotkeyTitleName);
		}
		logger.info("HotkeyIndex getTitleName end!");
	}

	/**
	 * ��������ר����ҳ
	 */
	@Action(value = "publishHotkeyHomePage")
	public void publishHotkeyHomePage() {
		logger.info("HotkeyIndex publishHotkeyHomePage start!");
		int result = hotkeyIndexManager.publishHotkeyHomePage();
		writeAjaxStr(String.valueOf(result));
		logger.info("HotkeyIndex publishHotkeyHomePage �����" + result);
		logger.info("HotkeyIndex publishHotkeyHomePage end!");
	}

	/**
	 * �ֻ��˷�������ר����ҳ
	 */
	@Action(value = "publishMobileHotkeyHomePage")
	public void publishMobileHotkeyHomePage() {
		logger.info("HotkeyIndex publishMobileHotkeyHomePage start!");
		int result = hotkeyIndexManager.publishMobileHotkeyHomePage();
		writeAjaxStr(String.valueOf(result));
		logger.info("HotkeyIndex publishMobileHotkeyHomePage �����" + result);
		logger.info("HotkeyIndex publishMobileHotkeyHomePage end!");
	}

	/**
	 * ��������ר���б�ҳ:��ҳ
	 */
	@Action(value = "publishAllHotkeyListPage")
	public void publishAllHotkeyListPage() {
		logger.info("HotkeyIndex publishAllHotkeyListPage start!");
		int result = hotkeyIndexManager.publishAllHotkeyListPage();
		writeAjaxStr(String.valueOf(result));
		logger.info("HotkeyIndex publishAllHotkeyListPage �����" + result);
		logger.info("HotkeyIndex publishAllHotkeyListPage end!");
	}
}
