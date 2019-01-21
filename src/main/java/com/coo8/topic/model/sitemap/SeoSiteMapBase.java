package com.coo8.topic.model.sitemap;

import java.io.Serializable;

public class SeoSiteMapBase implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;		//���
	private String name;		//����
	private String keyword;	//�ؼ���
	private Integer type;		//���ͣ�1��վ�㣬2����������
	private String expand;		//��չ�ֶ�,String
	private Integer remain;	//��չ�ֶ�,int
	private String remark;		//��ע
	private Integer isDelete;	//�Ƿ�ɾ��
	
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getExpand() {
		return expand;
	}
	public void setExpand(String expand) {
		this.expand = expand;
	}
	public Integer getRemain() {
		return remain;
	}
	public void setRemain(Integer remain) {
		this.remain = remain;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
}
