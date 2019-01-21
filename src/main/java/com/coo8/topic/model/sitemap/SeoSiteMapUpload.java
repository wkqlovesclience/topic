package com.coo8.topic.model.sitemap;

import java.io.Serializable;
import java.util.Date;

public class SeoSiteMapUpload implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		//编号
	private String filename;	//文件名称
	private String path;		//访问路径
	private String uploadUser;	//上传者
	private Date uploadTime;	//上传时间
	private String expand;		//扩展字段
	private Integer isDelete;	//是否删除
	private String site;		//站点
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getExpand() {
		return expand;
	}
	public void setExpand(String expand) {
		this.expand = expand;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
}
