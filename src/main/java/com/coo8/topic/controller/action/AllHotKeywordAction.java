package com.coo8.topic.controller.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.IAllHotKeywordManager;
import com.coo8.topic.model.AllHotKeyword;

@Namespace("/AllHotKeyword")
public class AllHotKeywordAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5252066488007792053L;

	private static Logger logger = LoggerFactory.getLogger(AllHotKeywordAction.class);

	private IAllHotKeywordManager allHotKeywordManager;

	protected Integer pageNumber = 1;
	protected Integer page_size = 10;


	protected PaginatedList<AllHotKeyword> listAllHotword;

	/**
	 * @desc »»¥ ¡–±Ì“≥
	 * @return success
	 */
	@Action(value = "listAllHotword", results = { @Result(name = "success", location = "/jsp/allHotword/list.jsp") })
	public String listAllHotword() {
		logger.info("listAllHotword start!");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", pageNumber);
		paramMap.put("pageSize", page_size);
		String qtitlePram = getStringParam("title");
		if (qtitlePram != null) {
			paramMap.put("title", decode(qtitlePram));
		} else {
			paramMap.put("title", qtitlePram);
		}

		paramMap.put("createTime", getStringParam("createTime"));

		listAllHotword = allHotKeywordManager.listAllHotKeywordPage(paramMap);

		logger.info("listAllHotword end!");
		return SUCCESS;
	}

	public boolean isEmptyOrNot(String param) {
		if (param == null || "".equals(param) || "".equals(param.trim())) {
			return true;
		}
		return false;
	}

	public IAllHotKeywordManager getAllHotKeywordManager() {
		return allHotKeywordManager;
	}

	public void setAllHotKeywordManager(IAllHotKeywordManager allHotKeywordManager) {
		this.allHotKeywordManager = allHotKeywordManager;
	}

	public PaginatedList<AllHotKeyword> getListAllHotword() {
		return listAllHotword;
	}

	public void setListAllHotword(PaginatedList<AllHotKeyword> listAllHotword) {
		this.listAllHotword = listAllHotword;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

}
