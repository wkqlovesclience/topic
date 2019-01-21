package com.coo8.topic.controller.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

@Namespace("/KeywordsRel")
public class KeywordsRelAction extends KeywordsRelBaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(KeywordsRelAction.class);

	protected static final String DEFAULT_SORT_COLUMNS = null;

	// forward paths
	protected static final String QUERY_JSP = "/web/KeywordsRel/query.jsp";
	protected static final String LIST_JSP = "/web/KeywordsRel/list.jsp";
	protected static final String CREATE_JSP = "/web/KeywordsRel/create.jsp";
	protected static final String EDIT_JSP = "/web/KeywordsRel/edit.jsp";
	protected static final String SHOW_JSP = "/web/KeywordsRel/show.jsp";
	// redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/web/KeywordsRel/list.do";

	@Action(value = "list", results = { @Result(name = "success", location = "/") })
	public String list() {
		logger.info("KeywordsRel list start!");
		logger.info("KeywordsRel list end!");
		return "success";
	}

	@Action(value = "show", results = { @Result(name = "success", location = "/") })
	public String show() {
		logger.info("KeywordsRel show start!");
		logger.info("KeywordsRel show end!");
		return "success";
	}

	@Action(value = "create", results = { @Result(name = "success", location = "/") })
	public String create() {
		logger.info("KeywordsRel create start!");
		logger.info("KeywordsRel create end!");
		return "success";
	}

	@Action(value = "save", results = { @Result(name = "success", location = "/") })
	public String save() {
		logger.info("KeywordsRel save start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		keywordsRelManager.save(keywordsRel);
		logger.info("KeywordsRel save end!");
		return "success";
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/") })
	public String edit() {
		logger.info("KeywordsRel edit start!");
		logger.info("KeywordsRel edit end!");
		return "success";
	}

	@Action(value = "update", results = { @Result(name = "success", location = "/") })
	public String update() {
		logger.info("KeywordsRel update start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		keywordsRelManager.update(this.keywordsRel);
		logger.info("KeywordsRel update end!");
		return "success";
	}

	@Action(value = "delete", results = { @Result(name = "success", location = "/") })
	public String delete() {
		logger.info("KeywordsRel delete start!");
		logger.info("KeywordsRel delete end!");
		
		return LIST_ACTION;
	}

}
