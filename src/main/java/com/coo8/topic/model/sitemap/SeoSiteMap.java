package com.coo8.topic.model.sitemap;

import java.io.Serializable;
import java.util.Date;

public class SeoSiteMap implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;			//���
	private String name;			//����
	private String urlCatalog;		//URL��ӦĿ¼
	private Integer count;			//����xml�ļ�����
	private String creator;		//������
	private String updator;		//�޸���
	private Date createDate;		//����ʱ��
	private Date updateDate;		//�޸�ʱ��
	private String expand;			//��չ�ֶ�
	private Integer isDelete;		//�Ƿ�ɾ��
	private String status;			//��ǰ״̬
	private String site;			//����վ��
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlCatalog() {
		return urlCatalog;
	}
	public void setUrlCatalog(String urlCatalog) {
		this.urlCatalog = urlCatalog;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getExpand() {
		return expand;
	}
	public void setExpand(String expand) {
		this.expand = expand;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
}
