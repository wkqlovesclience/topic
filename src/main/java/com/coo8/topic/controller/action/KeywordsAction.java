package com.coo8.topic.controller.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.common.utils.ConstantUtil;
import com.coo8.topic.model.Keywords;

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

@Namespace("/Keywords")
public class KeywordsAction extends KeywordsBaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(KeywordsAction.class);

	protected static final String DEFAULT_SORT_COLUMNS = null;

	// forward paths
	protected static final String QUERY_JSP = "/web/Keywords/query.jsp";
	protected static final String LIST_JSP = "/web/Keywords/list.jsp";
	protected static final String CREATE_JSP = "/web/Keywords/create.jsp";
	protected static final String EDIT_JSP = "/web/Keywords/edit.jsp";
	protected static final String SHOW_JSP = "/web/Keywords/show.jsp";
	// redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/web/Keywords/list.do";

	private String baseUrl;

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/model/tag_manage.jsp") })
	public String list() {
		logger.info("Keywords list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if (names != null && !"".equals(names)) {
			search.put("names", names);
		}
		search.put("site", getTopicSite());
		search.put("pageNumber", pageNumber);
		search.put("pageSize", page_size);
		search.put("types", 2);
		search.put("sortColumns", "create_Time desc");

		logger.info("Keywords list 查询参数：" + search);

		listtags = keywordsManager.findPageByMapLike(search);
		String site = getTopicSite();
		if (site.equals(ConstantUtil.SITE_FLAG_COO8)) {
			baseUrl = ConstantUtil.DOMAIN_COO8_BASEURL + ConstantUtil.DOMAIN_COO8_TITLE_BASEURL + "/";
		} else {
			baseUrl = ConstantUtil.DOMAIN_GOME_BASEURL + ConstantUtil.DOMAIN_GOME_TITLE_BASEURL + "/";
		}

		logger.info("Keywords list baseUrl:" + baseUrl);
		logger.info("Keywords list end!");
		return "success";
	}

	@Action(value = "show", results = { @Result(name = "success", location = "/") })
	public String show() {
		return "success";
	}

	@Action(value = "create", results = { @Result(name = "success", location = "/") })
	public String create() {
		return "success";
	}

	@Action(value = "save", results = { @Result(name = "success", location = "/") })
	public String save() {
		logger.info("Keywords save start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		keywordsManager.save(keywords);
		logger.info("Keywords save end!");
		return "success";
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/") })
	public String edit() {
		return "success";
	}

	@Action(value = "update", results = {
			@Result(name = "success", type = "redirect", location = "/Keywords/list.action") })
	public String update() throws UnsupportedEncodingException {
		logger.info("Keywords update start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		if (keywords != null) {
			Keywords temp = keywordsManager.getById(keywords.getId());
			keywords.setNames(new String(keywords.getNames()));
			keywords.setUpdateTime(new Date());
			keywords.setTypes(temp.getTypes());
			keywords.setId(temp.getId());

			String username = getLoginUserName();
			if (null != username && !"".equals(username)) {
				keywords.setModifier(username);
			}

			keywordsManager.update(keywords);
		}
		logger.info("Keywords update end!");
		return "success";
	}

	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/Keywords/list.action") })
	public String delete() {
		logger.info("Keywords delete start!");
		String idString = this.getRequest().getParameter("idsString");
		logger.info("Keywords delete 删除数据id：" + idString);
		String ids[] = idString.split(";");
		for (int i = 0; i < ids.length; i++) {
			keywordsManager.deleteById(Integer.valueOf(ids[i]));
		}
		logger.info("Keywords delete end!");
		return "success";
	}

	@Action(value = "deleteKey")
	public void deleteKey() {
		logger.info("Keywords deleteKey start!");
		String idString = this.getRequest().getParameter("idsString");
		logger.info("Keywords deleteKey 删除数据id：" + idString);
		String ids[] = idString.split(";");
		for (int i = 0; i < ids.length; i++) {
			keywordsManager.deleteById(Integer.valueOf(ids[i]));
		}
		writeAjaxStr("yes");
		logger.info("Keywords deleteKey end!");

	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
}
