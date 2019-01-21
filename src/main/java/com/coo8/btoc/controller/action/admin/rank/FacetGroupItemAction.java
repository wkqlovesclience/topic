/**
 * @author tianyu-ds
 * @date 2014-2-23
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 
 */
package com.coo8.btoc.controller.action.admin.rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.business.interfaces.rank.IFacetgroupItemManager;
import com.coo8.btoc.model.rank.FacetGroupItem;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.controller.action.BaseAction;

@Namespace("/FacetGroupItem")
public class FacetGroupItemAction extends BaseAction
{
	private static final long serialVersionUID = -8249659127941292491L;
	private  static Logger logger = LoggerFactory.getLogger(FacetGroupItemAction.class);
	private IFacetgroupItemManager facetGroupItemManager;
	protected PaginatedList<FacetGroupItem> facetGroupItemList;

	protected Integer pageNumber = 1;
	protected Integer page_size = 10;

	private Integer qid;
	private Integer qparent_id;
	private String qcatid, qvalue, qcreate_time, qis_show;

	public Map<String, Object> queryParamMap()
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("pageNumber", pageNumber);
		paramMap.put("pageSize", page_size);
		if (qid != null)
		{
			paramMap.put("qid", qid);
		}
		if (qcatid != null && !"".equals(qcatid))
		{
			paramMap.put("qcatid", qcatid);
		}
		if (qvalue != null && !"".equals(qvalue))
		{
			paramMap.put("qvalue", qvalue);
		}
		if (qparent_id != null)
		{
			paramMap.put("qparent_id", qparent_id);
		}
		if (qcreate_time != null && !"".equals(qcreate_time))
		{
			paramMap.put("qcreate_time", qcreate_time);
		}
		if (qis_show != null && !"".equals(qis_show))
		{
			paramMap.put("qis_show", qis_show);
		}

		return paramMap;
	}

	@Action(value = "edit", results =
	{ @Result(name = "success", location = "/jsp/rank/facetGroupItemEdit.jsp") })
	public String edit()
	{
		String id = getStringParam("id");
		if (id != null)
		{
			FacetGroupItem facetGroupItem = facetGroupItemManager.getById(id);
			if (facetGroupItem != null)
			{
				getRequest().setAttribute("facetGroupItem", facetGroupItem);
			}
        } else {
            String catId = getRequest().getParameter("qcatid");
            String parentId = getRequest().getParameter("qparent_id");
            FacetGroupItem facetGroupItem = new FacetGroupItem();
            facetGroupItem.setCatId(catId);
            facetGroupItem.setParentId(parentId);
            getRequest().setAttribute("facetGroupItem", facetGroupItem);
		}
		return SUCCESS;
	}

	@Action(value = "check", results =
	{ @Result(name = "success", location = "/jsp/rank/result.jsp") })
	public String check()
	{
		String id = getStringParam("id");
		if (id != null)
		{
			FacetGroupItem facetGroupItem = facetGroupItemManager.getById(id);
			if (facetGroupItem != null)
			{
				getRequest().setAttribute("result", "success");
			}
			else
			{
				getRequest().setAttribute("result", "fail");
			}
		}
		return SUCCESS;
	}

	@Action(value = "list", results =
	{ @Result(name = "success", location = "/jsp/rank/facetGroupItemList.jsp") })
	public String list()
	{
		Map<String, Object> paramMap = queryParamMap();
		facetGroupItemList = facetGroupItemManager.list(paramMap);

		return SUCCESS;
	}

	@Action(value = "add", results =
 { @Result(name = "success", type = "redirect", location = "/FacetGroupItem/list.action?qcatid=${qcatid}&qparent_id=${qparent_id}") })
	public String add()
	{
		String catId = getStringParam("cat_id");
		String value = getStringParam("value");
		String code = getStringParam("code");
		Integer index = getIntParam("index");
		String faceGroupId = getStringParam("group_id");
		String facetId = getStringParam("facet_id");
		Integer isShow = getIntParam("is_show");

		FacetGroupItem facetGroupItem = new FacetGroupItem();
		facetGroupItem.setCatId(catId);
		facetGroupItem.setValue(value);
		facetGroupItem.setCode(code);
		facetGroupItem.setIndex(index);
		facetGroupItem.setParentId(faceGroupId);
		facetGroupItem.setFacetId(facetId);
		facetGroupItem.setIsshow(isShow);
		facetGroupItem.setCreator(getLoginUserName());
		facetGroupItem.setUpdator(getLoginUserName());

		facetGroupItemManager.add(facetGroupItem);

		return SUCCESS;
	}

	@Action(value = "update", results =
	{ @Result(name = "success", type = "redirect", location = "/FacetGroupItem/list.action?pageNumber=${pageNumber}&qcatid=${qcatid}&qparent_id=${qparent_id}") })
	public String update()
	{
		String id = getStringParam("id");
		if (id != null)
		{
			FacetGroupItem facetGroupItem = facetGroupItemManager.getById(id);
			if (facetGroupItem != null)
			{
				String catId = getStringParam("cat_id");
				String value = getStringParam("value");
				String code = getStringParam("code");
				Integer index = getIntParam("index");
				String faceGroupId = getStringParam("group_id");
				String facetId = getStringParam("facet_id");
				Integer isShow = getIntParam("is_show");

				facetGroupItem.setCatId(catId);
				facetGroupItem.setValue(value);
				facetGroupItem.setCode(code);
				facetGroupItem.setIndex(index);
				facetGroupItem.setParentId(faceGroupId);
				facetGroupItem.setFacetId(facetId);
				facetGroupItem.setIsshow(isShow);
				facetGroupItem.setUpdator(getLoginUserName());

				facetGroupItemManager.update(facetGroupItem);
			}
		}
		return SUCCESS;
	}

	@Action(value = "delete", results =
	{ @Result(name = "success", type = "redirect", location = "/FacetGroupItem/list.action") })
	public String delete()
	{
		List<String> ids = getStringListParam("ids");
		if (!ids.isEmpty() )
		{
		    for(String id:ids){
                facetGroupItemManager.delete(id);
            }
		}
		return SUCCESS;
	}
	
	@Action(value = "changeShowState", results =
		{ @Result(name = "success", type = "redirect", location = "/FacetGroupItem/list.action") })
	public String changeShowState(){
		List<String> ids = getStringListParam("ids");
		String type = getStringParam("type");
		if (!ids.isEmpty() && !type.isEmpty())
		{
			String groupIds = "";
			for(String id:ids){
				groupIds += "'" + id +"',";
			}
			groupIds = groupIds.substring(0, groupIds.lastIndexOf(","));
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("ids", groupIds);
			paramMap.put("isShow", type);
			facetGroupItemManager.changeShowState(paramMap);
		}
		return SUCCESS;
	}
	
	/**
	 * @param param
	 * @return
	 * @desc 获取字符串参数数组
	 */
	public List<String> getStringListParam(String param)
	{
		String value = getRequest().getParameter(param);
		List<String> ids = new ArrayList<String>();
		try
		{
			if (value != null)
			{				
				String[] params = value.split("[,;]");
				for (String id : params)
				{
					if (!("".equals(id.trim())))
					{
						ids.add(id.trim());
					}
				}
				return ids;
			}
			return ids;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ids;
		}
	}

	public IFacetgroupItemManager getFacetGroupItemManager()
	{
		return facetGroupItemManager;
	}

	public void setFacetGroupItemManager(
			IFacetgroupItemManager facetGroupItemManager)
	{
		this.facetGroupItemManager = facetGroupItemManager;
	}

	public PaginatedList<FacetGroupItem> getFacetGroupItemList()
	{
		return facetGroupItemList;
	}

	public void setFacetGroupItemList(
			PaginatedList<FacetGroupItem> facetGroupItemList)
	{
		this.facetGroupItemList = facetGroupItemList;
	}

	public Integer getPageNumber()
	{
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber)
	{
		this.pageNumber = pageNumber;
	}

	public Integer getPage_size()
	{
		return page_size;
	}

	public void setPage_size(Integer page_size)
	{
		this.page_size = page_size;
	}
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public String getQcatid()
	{
		return qcatid;
	}

	public void setQcatid(String qcatid)
	{
		this.qcatid = qcatid;
	}

	public String getQvalue()
	{
		return qvalue;
	}
	
	public Integer getQparent_id() {
		return qparent_id;
	}

	public void setQparent_id(Integer qparent_id) {
		this.qparent_id = qparent_id;
	}

	public void setQvalue(String qvalue)
	{
		this.qvalue = qvalue;
	}

	public String getQcreate_time()
	{
		return qcreate_time;
	}

	public void setQcreate_time(String qcreate_time)
	{
		this.qcreate_time = qcreate_time;
	}

	public String getQis_show()
	{
		return qis_show;
	}

	public void setQis_show(String qis_show)
	{
		this.qis_show = qis_show;
	}
}
