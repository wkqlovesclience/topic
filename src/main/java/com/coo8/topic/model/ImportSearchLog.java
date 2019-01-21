/**
 * 
 */
package com.coo8.topic.model;

import java.io.Serializable;

/**
 * @author fanqingxia
 *
 */
public class ImportSearchLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5117382075537716855L;

	private Integer id;
	private String creator;
	private String createTime;
	private String fileUrl;
	private Integer totalCount;
	private Integer failCount;
	private String site;
	private Integer downStatus;
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Integer getDownStatus() {
		return downStatus;
	}

	public void setDownStatus(Integer downStatus) {
		this.downStatus = downStatus;
	}

}
