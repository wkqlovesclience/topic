package com.coo8.topic.model.sitemap;

import java.io.Serializable;

public class SeoSiteMapBase implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;		//±àºÅ
	private String name;		//Ãû³Æ
	private String keyword;	//¹Ø¼ü×Ö
	private Integer type;		//ÀàÐÍ£¬1£ºÕ¾µã£¬2£ºËÑË÷ÒýÇæ
	private String expand;		//À©Õ¹×Ö¶Î,String
	private Integer remain;	//À©Õ¹×Ö¶Î,int
	private String remark;		//±¸×¢
	private Integer isDelete;	//ÊÇ·ñÉ¾³ý
	
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
