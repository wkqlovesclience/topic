/* 
 *   WWW.COO8.COM  
 */

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

@Namespace("/TitleGoods")
public class TitleGoodsAction extends TitleGoodsBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2287193321040441195L;
	private static Logger logger = LoggerFactory.getLogger(TitleGoodsAction.class);

	protected static final String DEFAULT_SORT_COLUMNS = null;

	// forward paths
	protected static final String QUERY_JSP = "/web/TitleGoods/query.jsp";
	protected static final String LIST_JSP = "/web/TitleGoods/list.jsp";
	protected static final String CREATE_JSP = "/web/TitleGoods/create.jsp";
	protected static final String EDIT_JSP = "/web/TitleGoods/edit.jsp";
	protected static final String SHOW_JSP = "/web/TitleGoods/show.jsp";
	// redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/web/TitleGoods/list.do";

	@Action(value = "list", results = { @Result(name = "success", location = "/") })
	public String list() {
		logger.info("TitleGoods list start!");
		logger.info("TitleGoods list end!");
		return "success";
	}

	@Action(value = "show", results = { @Result(name = "success", location = "/") })
	public String show() {
		logger.info("TitleGoods show start!");
		logger.info("TitleGoods show end!");
		return "success";
	}

	@Action(value = "create", results = { @Result(name = "success", location = "/") })
	public String create() {
		logger.info("TitleGoods create start!");
		logger.info("TitleGoods create end!");
		return "success";
	}

	@Action(value = "save", results = { @Result(name = "success", location = "/") })
	public String save() {
		logger.info("TitleGoods save start!");
		if (null != checkExistSite()) {
			logger.info("TitleGoods save end!");
			return checkExistSite();
		}
		titleGoodsManager.save(titleGoods);
		logger.info("TitleGoods save end!");
		return "success";
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/") })
	public String edit() {
		logger.info("TitleGoods edit start!");
		logger.info("TitleGoods edit end!");
		return "success";
	}

	@Action(value = "update", results = { @Result(name = "success", location = "/") })
	public String update() {
		logger.info("TitleGoods update start!");
		if (null != checkExistSite()) {
			logger.info("TitleGoods update end!");
			return checkExistSite();
		}
		titleGoodsManager.update(this.titleGoods);
		logger.info("TitleGoods update end!");
		return "success";
	}

	@Action(value = "delete", results = { @Result(name = "success", location = "/") })
	public String delete() {
	
		logger.info("TitleGoods delete start!");
		logger.info("TitleGoods delete end!");
		return LIST_ACTION;
	}

}
