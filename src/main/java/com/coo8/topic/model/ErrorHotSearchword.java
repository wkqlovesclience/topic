package com.coo8.topic.model;

import java.io.Serializable;

/**
 * 上传的错误热词
 * 
 * @author fanqingxia
 *
 */
public class ErrorHotSearchword implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6744864127222436302L;

	private Integer id;
	private String title;
	private String productId;
	private String tag;

	private String creator;
	private String createTime;
	private String site;
	private Integer type;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
