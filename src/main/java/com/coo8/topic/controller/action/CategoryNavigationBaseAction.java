package com.coo8.topic.controller.action;

import java.io.File;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.ICategoryNavigationManager;
import com.coo8.topic.model.CategoryNavigation;
import com.coo8.topic.model.CategoryNavigationFirst;
import com.coo8.topic.model.CategoryNavigationThird;

public class CategoryNavigationBaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	protected ICategoryNavigationManager categoryNavigationManager;
	protected Integer page_index = 1;
	protected Integer page_size = 10;

	//品类分组相关参数
	protected PaginatedList<CategoryNavigation> categoryList;
	protected CategoryNavigation category;
	protected Integer id;
	protected String ids;
	protected String names;
	protected File file;
	protected String fileFileName;
	protected String fileContentType;
	
	//一级分类相关参数
	protected PaginatedList<CategoryNavigationFirst> categoryListFirst;
	protected CategoryNavigationFirst categoryFirst;
	protected Integer groupId;
	protected String catId;
	protected Integer firstId;
	protected String firstIds;
	
	//分类扩充相关参数
	protected PaginatedList<CategoryNavigationThird> categoryListThird;
	protected CategoryNavigationThird categoryThird;
	protected String extendName;
	protected Integer thirdId;
	protected String thirdIds;
	protected String subCatId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public ICategoryNavigationManager getCategoryNavigationManager() {
		return categoryNavigationManager;
	}

	public void setCategoryNavigationManager(ICategoryNavigationManager categoryNavigationManager) {
		this.categoryNavigationManager = categoryNavigationManager;
	}

	public PaginatedList<CategoryNavigation> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(PaginatedList<CategoryNavigation> categoryList) {
		this.categoryList = categoryList;
	}

	public Integer getPage_index() {
		return page_index;
	}

	public void setPage_index(Integer page_index) {
		this.page_index = page_index;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public CategoryNavigation getCategory() {
		return category;
	}

	public void setCategory(CategoryNavigation category) {
		this.category = category;
	}

	public PaginatedList<CategoryNavigationFirst> getCategoryListFirst() {
		return categoryListFirst;
	}

	public void setCategoryListFirst(
			PaginatedList<CategoryNavigationFirst> categoryListFirst) {
		this.categoryListFirst = categoryListFirst;
	}

	public CategoryNavigationFirst getCategoryFirst() {
		return categoryFirst;
	}

	public void setCategoryFirst(CategoryNavigationFirst categoryFirst) {
		this.categoryFirst = categoryFirst;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public Integer getFirstId() {
		return firstId;
	}

	public void setFirstId(Integer firstId) {
		this.firstId = firstId;
	}

	public String getFirstIds() {
		return firstIds;
	}

	public void setFirstIds(String firstIds) {
		this.firstIds = firstIds;
	}

	public PaginatedList<CategoryNavigationThird> getCategoryListThird() {
		return categoryListThird;
	}

	public void setCategoryListThird(
			PaginatedList<CategoryNavigationThird> categoryListThird) {
		this.categoryListThird = categoryListThird;
	}

	public CategoryNavigationThird getCategoryThird() {
		return categoryThird;
	}

	public void setCategoryThird(CategoryNavigationThird categoryThird) {
		this.categoryThird = categoryThird;
	}

	public String getExtendName() {
		return extendName;
	}

	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

	public Integer getThirdId() {
		return thirdId;
	}

	public void setThirdId(Integer thirdId) {
		this.thirdId = thirdId;
	}

	public String getThirdIds() {
		return thirdIds;
	}

	public void setThirdIds(String thirdIds) {
		this.thirdIds = thirdIds;
	}

	public String getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(String subCatId) {
		this.subCatId = subCatId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

}
