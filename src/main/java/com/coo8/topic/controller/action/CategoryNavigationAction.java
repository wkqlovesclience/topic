package com.coo8.topic.controller.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.coo8.topic.model.CategoryNavigation;
import com.coo8.topic.model.CategoryNavigationFirst;
import com.coo8.topic.model.CategoryNavigationThird;
import com.gome.common.web.context.LoginContext;
import com.gome.stage.bean.category.Category;

@Namespace("/CategoryNavigation")
public class CategoryNavigationAction extends CategoryNavigationBaseAction {

	private static final long serialVersionUID = 5955271619849439543L;

	private static Logger logger = LoggerFactory.getLogger(CategoryNavigationAction.class);

	/**
	 * 品类分组列表
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/categoryNavigation/list.jsp") })
	public String list() {
		logger.info("CategoryNavigation list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if (null != names && !"".equals(names)) {
			search.put("names", names);
		}
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		categoryList = categoryNavigationManager.findLikeByMap(search);
		logger.info("CategoryNavigation list end!");
		return SUCCESS;
	}

	/**
	 * 添加/修改品类
	 */
	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/categoryNavigation/create.jsp") })
	public String edit() {
		logger.info("CategoryNavigation edit start!");
		if (id != null && !"".equals(String.valueOf(id))) {
			category = categoryNavigationManager.getById(id);
		} else {
			category = new CategoryNavigation();
			category.setStatus(1);
		}
		logger.info("CategoryNavigation edit end!");
		return SUCCESS;
	}
	
	/**
	 * 校验品类名称是否重复
	 */
	@Action(value = "checkNames")
	public void checkNames() {
		CategoryNavigation cn = categoryNavigationManager.getByName(names, id);
		if(cn != null){
			writeAjaxStr("1");
		}else{
			writeAjaxStr("0");
		}
	}
	
	/**
	 * 校验品类下的一级分类是否重复
	 */
	@Action(value = "checkFirstCatId")
	public void checkFirstCatId() {
		CategoryNavigationFirst cn = categoryNavigationManager.getByFirstCatId(groupId, catId, firstId);
		if(cn != null){
			writeAjaxStr("1");
		}else{
			writeAjaxStr("0");
		}
	}
	
	/**
	 * 校验三级分类补充是否重复
	 */
	@Action(value = "checkThirdCategory")
	public void checkThirdCategory() {
		CategoryNavigationThird cn = categoryNavigationManager.getByThirdCatId(groupId, catId, subCatId, extendName, thirdId);
		if(cn != null){
			writeAjaxStr("1");
		}else{
			writeAjaxStr("0");
		}
	}
	
	/**
	 * 保存品类
	 */
	@Action(value = "save", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/list.action") })
	public String save() {
		logger.info("CategoryNavigation save start!");
		int result;
		if (null != category) {
			if (null != category.getId()) {// 修改操作
				result = categoryNavigationManager.update(category);
				if(result > 0){
					logger.info("CategoryNavigation save 更新成功！");
				}else{
					logger.info("CategoryNavigation save 更新失败！");
				}
			} else { // 新增操作
				String operator = getLoginUserName();
				logger.info("save operator:" + operator);
				category.setCreatedName(operator);
				result = categoryNavigationManager.save(category);
				if(result > 0){
					logger.info("CategoryNavigation save 新增成功！");
				}else{
					logger.info("CategoryNavigation save 新增失败！");
				}
			}
		}
		logger.info("CategoryNavigation save end!");
		return SUCCESS;
	} 
	
	/**
	 * 删除品类
	 */
	@Action(value = "delete", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/list.action") })
	public String delete() {
		logger.info("CategoryNavigation delete start!");
		if (ids != null && !"".equals(ids)) {
			logger.info("CategoryNavigation delete ids为：" + ids);
			String[] strs = ids.split(";");
			for (String tid : strs) {
				categoryNavigationManager.deleteById(Integer.valueOf(tid));
			}
		}
		if (id != null && !"".equals(String.valueOf(id))) {
			categoryNavigationManager.deleteById(id);
		}
		logger.info("CategoryNavigation delete end!");
		return SUCCESS;
	}
	
	/**
	 * 获取所有的一级分类
	 */
	@Action(value = "getFirstCategories")
	public void getFirstCategories() {
		List<Category> firstCategoryList = categoryNavigationManager.getFirstCategories();
		JSONArray jsonArry = JSONArray.fromObject(firstCategoryList);
		writeAjaxStr(jsonArry.toString());
	}
	
	/**
	 * 根据品类获取品类下配置的一级分类
	 */
	@Action(value = "getFirstCategoryByGroupId")
	public void getFirstCategoryByGroupId() {
		List<Category> firstCategoryList = categoryNavigationManager.getFirstCategoryByGroupId(groupId);
		JSONArray jsonArry = JSONArray.fromObject(firstCategoryList);
		writeAjaxStr(jsonArry.toString());
	}
	
	/**
	 * 根据一级分类获取所有的二级分类
	 */
	@Action(value = "getSecondCategories")
	public void getSecondCategories() {
		List<Category> secondCategoryList = categoryNavigationManager.getSecondCategories(catId);
		JSONArray jsonArry = JSONArray.fromObject(secondCategoryList);
		writeAjaxStr(jsonArry.toString());
	}
	
	/**
	 * 上传图片
	 */
	@Action(value = "upload")
	public void upload() {
		Map<String, String> result = categoryNavigationManager.upload(file, fileFileName, fileContentType, getLoginUserName());
		logger.info("result:" + JSON.toJSONString(result));
		JSONArray jsonArry = JSONArray.fromObject(result);
		writeAjaxStr(jsonArry.toString());
	}
	
	/**
	 * 发布
	 */
	@Action(value = "publish")
	public void publish() {
		categoryNavigationManager.publish();
		writeAjaxStr("success");
	}
	
	/**
	 * 一级分类列表
	 */
	@Action(value = "firstList", results = { @Result(name = "success", location = "/jsp/categoryNavigation/list_first.jsp") })
	public String firstList() {
		logger.info("CategoryNavigation first list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if (null != catId && !"".equals(catId)) {
			search.put("catId", catId);
		}
		search.put("groupId", groupId);
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		categoryListFirst = categoryNavigationManager.findLikeFirstByMap(search);
		categoryNavigationManager.getCategoryNames(categoryListFirst);
		logger.info("CategoryNavigation first list end!");
		return SUCCESS;
	}

	/**
	 * 添加/修改一级分类
	 */
	@Action(value = "firstEdit", results = { @Result(name = "success", location = "/jsp/categoryNavigation/create_first.jsp") })
	public String firstEdit() {
		logger.info("CategoryNavigation first edit start!");
		if (firstId != null && !"".equals(String.valueOf(firstId))) {
			categoryFirst = categoryNavigationManager.getFirstById(firstId);
		} else {
			categoryFirst = new CategoryNavigationFirst();
			categoryFirst.setStatus(1);
			categoryFirst.setGroupId(groupId);
		}
		logger.info("CategoryNavigation first edit end!");
		return SUCCESS;
	}
	
	/**
	 * 保存一级分类
	 */
	@Action(value = "firstSave", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/firstList.action", 
			params = {"groupId", "%{categoryFirst.getGroupId()}"})})
	public String firstSave() {
		logger.info("CategoryNavigation first save start!");
		int result;
		if (null != categoryFirst) {
			if (null != categoryFirst.getId()) {// 修改操作
				result = categoryNavigationManager.updateFirst(categoryFirst);
				if(result > 0){
					logger.info("CategoryNavigation first save 更新成功！");
				}else{
					logger.info("CategoryNavigation first save 更新失败！");
				}
			} else { // 新增操作
				String operator = "";
				//获得用户信息
				LoginContext context = LoginContext.getLoginContext();
				if(null != context){
					operator = context.getPin();
				}
				logger.info("firstSave operator:" + JSON.toJSONString(context));
				categoryFirst.setCreatedName(operator);
				result = categoryNavigationManager.saveFirst(categoryFirst);
				if(result > 0){
					logger.info("CategoryNavigation first save 新增成功！");
				}else{
					logger.info("CategoryNavigation first save 新增失败！");
				}
			}
		}
		logger.info("CategoryNavigation first save end!");
		return SUCCESS;
	} 
	
	/**
	 * 删除一级分类
	 */
	@Action(value = "firstDelete", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/firstList.action",
			params = {"groupId", "%{groupId}"})})
	public String firstDelete() {
		logger.info("CategoryNavigation first delete start!");
		if (firstIds != null && !"".equals(firstIds)) {
			logger.info("CategoryNavigation first delete firstIds为：" + firstIds);
			String[] strs = firstIds.split(";");
			for (String tid : strs) {
				categoryFirst = categoryNavigationManager.getFirstById(Integer.valueOf(tid));
				categoryNavigationManager.deleteFirstById(Integer.valueOf(tid), groupId, categoryFirst.getCatId());
			}
		}
		if (firstId != null && !"".equals(String.valueOf(firstId))) {
			categoryFirst = categoryNavigationManager.getFirstById(firstId);
			categoryNavigationManager.deleteFirstById(firstId, groupId, categoryFirst.getCatId());
		}
		logger.info("CategoryNavigation first delete end!");
		return SUCCESS;
	}
	
	/**
	 * 分类扩充列表
	 */
	@Action(value = "thirdList", results = { @Result(name = "success", location = "/jsp/categoryNavigation/list_third.jsp") })
	public String thirdList() {
		logger.info("CategoryNavigation third list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if (null != extendName && !"".equals(extendName)) {
			search.put("extendName", extendName);
		}
		search.put("groupId", groupId);
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		categoryListThird = categoryNavigationManager.findLikeThirdByMap(search);
		categoryNavigationManager.getSecondCategoryName(categoryListThird);
		logger.info("CategoryNavigation third list end!");
		return SUCCESS;
	}
	
	/**
	 * 添加/修改分类扩充
	 */
	@Action(value = "thirdEdit", results = { @Result(name = "success", location = "/jsp/categoryNavigation/create_third.jsp") })
	public String thirdEdit() {
		logger.info("CategoryNavigation third edit start!");
		if (thirdId != null && !"".equals(String.valueOf(thirdId))) {
			categoryThird = categoryNavigationManager.getThirdById(thirdId);
		} else {
			categoryThird = new CategoryNavigationThird();
			categoryThird.setStatus(1);
			categoryThird.setMarkRed(0);
			categoryThird.setGroupId(groupId);
		}
		logger.info("CategoryNavigation third edit end!");
		return SUCCESS;
	}
	
	/**
	 * 保存分类扩充
	 */
	@Action(value = "thirdSave", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/thirdList.action", 
			params = {"groupId", "%{categoryThird.getGroupId()}"})})
	public String thirdSave() {
		logger.info("CategoryNavigation third save start!");
		int result;
		if (null != categoryThird) {
			if (null != categoryThird.getId()) {// 修改操作
				result = categoryNavigationManager.updateThird(categoryThird);
				if(result > 0){
					logger.info("CategoryNavigation third save 更新成功！");
				}else{
					logger.info("CategoryNavigation third save 更新失败！");
				}
			} else { // 新增操作
				categoryThird.setCreatedName(getLoginUserName());
				result = categoryNavigationManager.saveThird(categoryThird);
				if(result > 0){
					logger.info("CategoryNavigation third save 新增成功！");
				}else{
					logger.info("CategoryNavigation third save 新增失败！");
				}
			}
		}
		logger.info("CategoryNavigation third save end!");
		return SUCCESS;
	} 
	
	/**
	 * 删除分类扩充
	 */
	@Action(value = "thirdDelete", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/thirdList.action",
			params = {"groupId", "%{groupId}"})})
	public String thirdDelete() {
		logger.info("CategoryNavigation third delete start!");
		if (thirdIds != null && !"".equals(thirdIds)) {
			logger.info("CategoryNavigation third delete thirdIds为：" + thirdIds);
			String[] strs = thirdIds.split(";");
			for (String tid : strs) {
				categoryNavigationManager.deleteThirdById(Integer.valueOf(tid));
			}
		}
		if (thirdId != null && !"".equals(String.valueOf(thirdId))) {
			categoryNavigationManager.deleteThirdById(thirdId);
		}
		logger.info("CategoryNavigation third delete end!");
		return SUCCESS;
	}
}
