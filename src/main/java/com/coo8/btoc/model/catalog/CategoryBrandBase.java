package com.coo8.btoc.model.catalog;

import com.coo8.btoc.model.BaseModel;

/**
 * @author:duanzuocai
 * @email:duanzuocai@yahoo.com.cn
 * @DateTime:2011-3-18
 * @Description: 分类和品牌基类
 */
public class CategoryBrandBase extends BaseModel{
	/**	UID */
	private static final long serialVersionUID = 4424955458695804703L;
	protected int id;			// 编号
	protected String name;		// 分类/品牌名称
	protected String enname;	// 英文名称
	protected int fatherid;		// 父分类 ID
	protected String picurl;	// 图片URL
	protected int priority;		// 排序值
	protected int status;		// 状态
	protected int productcount;	// 产品数量
	protected int type;			// 类型，品牌没有type,默认为0（但是页面只能接收成0）1大分类 2小分类 3品牌
	protected String updater;	// 更新者
	protected int definitionid;	// 定义ID
	protected String urlcode;	// 页面地扯
	protected String catname;	// 所属分类名称
	protected int level;		// 权重
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public int getFatherid() {
		return fatherid;
	}
	public void setFatherid(int fatherid) {
		this.fatherid = fatherid;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getProductcount() {
		return productcount;
	}
	public void setProductcount(int productcount) {
		this.productcount = productcount;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public int getDefinitionid() {
		return definitionid;
	}
	public void setDefinitionid(int definitionid) {
		this.definitionid = definitionid;
	}
	public String getUrlcode() {
		return urlcode;
	}
	public void setUrlcode(String urlcode) {
		this.urlcode = urlcode;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}
