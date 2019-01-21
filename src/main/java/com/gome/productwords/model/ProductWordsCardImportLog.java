package com.gome.productwords.model;



import java.io.Serializable;
import java.util.Date;
/**
 * create by wangkeqiang-ds
 * 商品词日志
 */
public class ProductWordsCardImportLog implements Serializable {

	private static final long serialVersionUID = 5746359346918403050L;
	private Integer id;
	private String creator;
	private Integer totalCount;
	private Integer failCount;
	private Integer downStatus;
	private String createTime;

	private String site;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public Integer getDownStatus() {
		return downStatus;
	}

	public void setDownStatus(Integer downStatus) {
		this.downStatus = downStatus;
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
}
