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
	 * Ʒ������б�
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
	 * ���/�޸�Ʒ��
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
	 * У��Ʒ�������Ƿ��ظ�
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
	 * У��Ʒ���µ�һ�������Ƿ��ظ�
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
	 * У���������ಹ���Ƿ��ظ�
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
	 * ����Ʒ��
	 */
	@Action(value = "save", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/list.action") })
	public String save() {
		logger.info("CategoryNavigation save start!");
		int result;
		if (null != category) {
			if (null != category.getId()) {// �޸Ĳ���
				result = categoryNavigationManager.update(category);
				if(result > 0){
					logger.info("CategoryNavigation save ���³ɹ���");
				}else{
					logger.info("CategoryNavigation save ����ʧ�ܣ�");
				}
			} else { // ��������
				String operator = getLoginUserName();
				logger.info("save operator:" + operator);
				category.setCreatedName(operator);
				result = categoryNavigationManager.save(category);
				if(result > 0){
					logger.info("CategoryNavigation save �����ɹ���");
				}else{
					logger.info("CategoryNavigation save ����ʧ�ܣ�");
				}
			}
		}
		logger.info("CategoryNavigation save end!");
		return SUCCESS;
	} 
	
	/**
	 * ɾ��Ʒ��
	 */
	@Action(value = "delete", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/list.action") })
	public String delete() {
		logger.info("CategoryNavigation delete start!");
		if (ids != null && !"".equals(ids)) {
			logger.info("CategoryNavigation delete idsΪ��" + ids);
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
	 * ��ȡ���е�һ������
	 */
	@Action(value = "getFirstCategories")
	public void getFirstCategories() {
		List<Category> firstCategoryList = categoryNavigationManager.getFirstCategories();
		JSONArray jsonArry = JSONArray.fromObject(firstCategoryList);
		writeAjaxStr(jsonArry.toString());
	}
	
	/**
	 * ����Ʒ���ȡƷ�������õ�һ������
	 */
	@Action(value = "getFirstCategoryByGroupId")
	public void getFirstCategoryByGroupId() {
		List<Category> firstCategoryList = categoryNavigationManager.getFirstCategoryByGroupId(groupId);
		JSONArray jsonArry = JSONArray.fromObject(firstCategoryList);
		writeAjaxStr(jsonArry.toString());
	}
	
	/**
	 * ����һ�������ȡ���еĶ�������
	 */
	@Action(value = "getSecondCategories")
	public void getSecondCategories() {
		List<Category> secondCategoryList = categoryNavigationManager.getSecondCategories(catId);
		JSONArray jsonArry = JSONArray.fromObject(secondCategoryList);
		writeAjaxStr(jsonArry.toString());
	}
	
	/**
	 * �ϴ�ͼƬ
	 */
	@Action(value = "upload")
	public void upload() {
		Map<String, String> result = categoryNavigationManager.upload(file, fileFileName, fileContentType, getLoginUserName());
		logger.info("result:" + JSON.toJSONString(result));
		JSONArray jsonArry = JSONArray.fromObject(result);
		writeAjaxStr(jsonArry.toString());
	}
	
	/**
	 * ����
	 */
	@Action(value = "publish")
	public void publish() {
		categoryNavigationManager.publish();
		writeAjaxStr("success");
	}
	
	/**
	 * һ�������б�
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
	 * ���/�޸�һ������
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
	 * ����һ������
	 */
	@Action(value = "firstSave", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/firstList.action", 
			params = {"groupId", "%{categoryFirst.getGroupId()}"})})
	public String firstSave() {
		logger.info("CategoryNavigation first save start!");
		int result;
		if (null != categoryFirst) {
			if (null != categoryFirst.getId()) {// �޸Ĳ���
				result = categoryNavigationManager.updateFirst(categoryFirst);
				if(result > 0){
					logger.info("CategoryNavigation first save ���³ɹ���");
				}else{
					logger.info("CategoryNavigation first save ����ʧ�ܣ�");
				}
			} else { // ��������
				String operator = "";
				//����û���Ϣ
				LoginContext context = LoginContext.getLoginContext();
				if(null != context){
					operator = context.getPin();
				}
				logger.info("firstSave operator:" + JSON.toJSONString(context));
				categoryFirst.setCreatedName(operator);
				result = categoryNavigationManager.saveFirst(categoryFirst);
				if(result > 0){
					logger.info("CategoryNavigation first save �����ɹ���");
				}else{
					logger.info("CategoryNavigation first save ����ʧ�ܣ�");
				}
			}
		}
		logger.info("CategoryNavigation first save end!");
		return SUCCESS;
	} 
	
	/**
	 * ɾ��һ������
	 */
	@Action(value = "firstDelete", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/firstList.action",
			params = {"groupId", "%{groupId}"})})
	public String firstDelete() {
		logger.info("CategoryNavigation first delete start!");
		if (firstIds != null && !"".equals(firstIds)) {
			logger.info("CategoryNavigation first delete firstIdsΪ��" + firstIds);
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
	 * ���������б�
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
	 * ���/�޸ķ�������
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
	 * �����������
	 */
	@Action(value = "thirdSave", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/thirdList.action", 
			params = {"groupId", "%{categoryThird.getGroupId()}"})})
	public String thirdSave() {
		logger.info("CategoryNavigation third save start!");
		int result;
		if (null != categoryThird) {
			if (null != categoryThird.getId()) {// �޸Ĳ���
				result = categoryNavigationManager.updateThird(categoryThird);
				if(result > 0){
					logger.info("CategoryNavigation third save ���³ɹ���");
				}else{
					logger.info("CategoryNavigation third save ����ʧ�ܣ�");
				}
			} else { // ��������
				categoryThird.setCreatedName(getLoginUserName());
				result = categoryNavigationManager.saveThird(categoryThird);
				if(result > 0){
					logger.info("CategoryNavigation third save �����ɹ���");
				}else{
					logger.info("CategoryNavigation third save ����ʧ�ܣ�");
				}
			}
		}
		logger.info("CategoryNavigation third save end!");
		return SUCCESS;
	} 
	
	/**
	 * ɾ����������
	 */
	@Action(value = "thirdDelete", results = {@Result(name = "success", type = "redirect", location = "/CategoryNavigation/thirdList.action",
			params = {"groupId", "%{groupId}"})})
	public String thirdDelete() {
		logger.info("CategoryNavigation third delete start!");
		if (thirdIds != null && !"".equals(thirdIds)) {
			logger.info("CategoryNavigation third delete thirdIdsΪ��" + thirdIds);
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
