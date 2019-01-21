/**
 * @author tianyu-ds
 * @date 2013-9-26
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 热词的实体类
 */
package com.coo8.topic.model;

import java.io.Serializable;

public class HotKeyword implements Serializable
{
	private static final long serialVersionUID = -2403198329232557077L;

	private Integer id;

	private String title;

	private String productId;

	private Integer tagId;
	
	private Integer firstTagId;
	
	private String firstTagName;

	private Integer secondTagId;
	
	private String secondTagName;

	private String createTime;

	private String updateTime;

	private String modifier;
	
	private String creator;

	private Integer checked;
	
	private String serverUrl;
	
	private String onlineUrl;
	
	private String site;
	
	private String indexState;
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getProductId()
	{
		return productId;
	}

	public void setProductId(String productId)
	{
		this.productId = productId;
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

	public Integer getChecked()
	{
		return checked;
	}

	public void setChecked(Integer checked)
	{
		this.checked = checked;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Integer getTagId()
	{
		return tagId;
	}

	public void setTagId(Integer tagId)
	{
		this.tagId = tagId;
	}

	public String getFirstTagName()
	{
		return firstTagName;
	}

	public void setFirstTagName(String firstTagName)
	{
		this.firstTagName = firstTagName;
	}

	public String getSecondTagName()
	{
		return secondTagName;
	}

	public void setSecondTagName(String secondTagName)
	{
		this.secondTagName = secondTagName;
	}

	public Integer getFirstTagId()
	{
		return firstTagId;
	}

	public void setFirstTagId(Integer firstTagId)
	{
		this.firstTagId = firstTagId;
	}

	public Integer getSecondTagId()
	{
		return secondTagId;
	}

	public void setSecondTagId(Integer secondTagId)
	{
		this.secondTagId = secondTagId;
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

	public String getServerUrl()
	{
		return serverUrl;
	}

	public void setServerUrl(String serverUrl)
	{
		this.serverUrl = serverUrl;
	}

	public String getOnlineUrl()
	{
		return onlineUrl;
	}

	public void setOnlineUrl(String onlineUrl)
	{
		this.onlineUrl = onlineUrl;
	}

	public String getSite()
	{
		return site;
	}

	public void setSite(String site)
	{
		this.site = site;
	}

	public String getIndexState() {
		return indexState;
	}

	public void setIndexState(String indexState) {
		this.indexState = indexState;
	}
	
}
