/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.controller.action;

import com.coo8.topic.business.interfaces.ITitleGoodsManager;
import com.coo8.topic.model.TitleGoods;

/**
 * @author  JIANGCHENG
 * @version 1.0
 * @since 1.0
 */


public class TitleGoodsBaseAction extends BaseAction {
	//Ĭ�϶�������,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/web/TitleGoods/query.jsp";
	protected static final String LIST_JSP= "/web/TitleGoods/list.jsp";
	protected static final String CREATE_JSP = "/web/TitleGoods/create.jsp";
	protected static final String EDIT_JSP = "/web/TitleGoods/edit.jsp";
	protected static final String SHOW_JSP = "/web/TitleGoods/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/web/TitleGoods/list.do";
	
	protected ITitleGoodsManager titleGoodsManager;
	
	protected Integer page_index;
	
	protected Integer page_size;

	protected TitleGoods titleGoods;

	private java.lang.Integer id;
	private java.lang.Integer goodsId;
	private java.lang.String goodsNo;
	private java.lang.Integer titleId;

		 
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
		 
	public void setGoodsId(java.lang.Integer value) {
		this.goodsId = value;
	}
	
	public java.lang.Integer getGoodsId() {
		return this.goodsId;
	}
		 
	public void setGoodsNo(java.lang.String value) {
		this.goodsNo = value;
	}
	
	public java.lang.String getGoodsNo() {
		return this.goodsNo;
	}
		 
	public void setTitleId(java.lang.Integer value) {
		this.titleId = value;
	}
	
	public java.lang.Integer getTitleId() {
		return this.titleId;
	}


}
