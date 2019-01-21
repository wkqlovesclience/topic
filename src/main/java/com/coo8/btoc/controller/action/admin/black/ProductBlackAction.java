package com.coo8.btoc.controller.action.admin.black;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.business.interfaces.black.ProductBlackManager;
import com.coo8.btoc.controller.action.admin.rank.CategoryAction;
import com.coo8.btoc.model.black.ProductBlack;
import com.coo8.topic.controller.action.BaseAction;

@Namespace("/ProductBlack")
public class ProductBlackAction extends BaseAction {

	private static final long serialVersionUID = 1663411550628260599L;
	private static Logger logger = LoggerFactory.getLogger(CategoryAction.class);
	protected List<ProductBlack> productList;
	protected ProductBlackManager productBlackManager;

	public Map<String, Object> queryParamMap() {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		return paramMap;
	}

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/black/list.jsp") })
	public String list() {
		
		String proId= getStringParam("proId");
		
		logger.info("ProductBlack list start!");
		Map<String, Object> paramMap = this.queryParamMap();
		paramMap.put("proId", proId);
		productList = productBlackManager.getAllList(paramMap);

		logger.info("ProductBlack list end!");
		return SUCCESS;
	}

	@Action(value = "addBlack", results = {
			@Result(name = "success", type = "redirect", location = "/jsp/black/addBlack.jsp") })
	public String addBlack() {
		return SUCCESS;
	}

	@Action(value = "add", results = {
			@Result(name = "success", type = "redirect", location = "/ProductBlack/list.action") })
	public String add() {

		String proId = getStringParam("proId");

		ProductBlack black = new ProductBlack();
		black.setProId(proId);
		black.setCreateUser(getLoginUserName());
		black.setCreateDate(new Date());
		black.setUpdateUser(getLoginUserName());
		black.setUpdateDate(new Date());

		productBlackManager.add(black);

		return SUCCESS;
	}

	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/ProductBlack/list.action") })
	public String delete() {

		String proId = getStringParam("proId");
		productBlackManager.delete(proId);

		return SUCCESS;
	}

	public ProductBlackManager getProductBlackManager() {
		return productBlackManager;
	}

	public void setProductBlackManager(ProductBlackManager productBlackManager) {
		this.productBlackManager = productBlackManager;
	}

	public List<ProductBlack> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductBlack> productList) {
		this.productList = productList;

	}

}