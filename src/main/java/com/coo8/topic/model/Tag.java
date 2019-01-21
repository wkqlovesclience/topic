/**
 * @author tianyu-ds
 * @date 2013-9-30
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 标签类
 */
package com.coo8.topic.model;

import java.io.Serializable;

public class Tag implements Serializable
{
	private static final long serialVersionUID = 8797585308999501952L;

	private Integer id;

	private Integer parentId;
	
	private String parentName;
	
	private String tagName;

	private Integer subtagCount;

	private String createTime;

	private String updateTime;

	private String modifier;
	
	private String creator;
	
	private String site;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getParentId()
	{
		return parentId;
	}

	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public String getTagName()
	{
		return tagName;
	}

	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}

	public Integer getSubtagCount()
	{
		return subtagCount;
	}

	public void setSubtagCount(Integer subtagCount)
	{
		this.subtagCount = subtagCount;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(String updateTime)
	{
		this.updateTime = updateTime;
	}

	public String getModifier()
	{
		return modifier;
	}

	public void setModifier(String modifier)
	{
		this.modifier = modifier;
	}

	public String getCreator()
	{
		return creator;
	}

	public void setCreator(String creator)
	{
		this.creator = creator;
	}

	public String getSite()
	{
		return site;
	}

	public void setSite(String site)
	{
		this.site = site;
	}
}
