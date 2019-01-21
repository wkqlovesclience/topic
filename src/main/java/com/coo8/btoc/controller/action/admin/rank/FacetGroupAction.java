/**
 * @author tianyu-ds
 * @date 2014-2-20
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

import com.coo8.btoc.business.interfaces.rank.IFacetgroupManager;
import com.coo8.btoc.model.rank.FacetGroup;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.controller.action.BaseAction;

@Namespace("/FacetGroup")
public class FacetGroupAction extends BaseAction
{
    private static final long serialVersionUID = -88523128450370321L;
	private  static Logger logger = LoggerFactory.getLogger(FacetGroupAction.class);
    private IFacetgroupManager facetGroupManager;
    protected PaginatedList<FacetGroup> facetGroupList;

    protected Integer pageNumber = 1;
    protected Integer page_size = 10;

    private String qgroupid, qcategoryid, qdisplayname, qcreate_time, qis_show;

    public Map<String, Object> queryParamMap()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("pageNumber", pageNumber);
        paramMap.put("pageSize", page_size);

        if (qgroupid != null && !"".equals(qgroupid))
        {
            paramMap.put("qgroupid", qgroupid);
        }
        if (qcategoryid != null && !"".equals(qcategoryid))
        {
            paramMap.put("qcategoryid", qcategoryid);
        }
        if (qdisplayname != null && !"".equals(qdisplayname))
        {
            paramMap.put("qdisplayname", qdisplayname);
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
    { @Result(name = "success", location = "/jsp/rank/facetGroupEdit.jsp") })
    public String edit()
    {
        String id = getStringParam("id");
        if (id != null)
        {
            FacetGroup facetGroup = facetGroupManager.getById(id);
            if (facetGroup != null)
            {
                getRequest().setAttribute("facetGroup", facetGroup);
            }
        }else{
            FacetGroup facetGroup = new  FacetGroup();
            String catId = getRequest().getParameter("qcategoryid");
            facetGroup.setCategoryId(catId);
            getRequest().setAttribute("facetGroup", facetGroup);
        }
        return SUCCESS;
    }

    @Action(value = "check")
    public void check()
    {
        String id = getStringParam("id");
        if (id != null)
        {
            FacetGroup facetGroup = facetGroupManager.getById(id);
            if (facetGroup == null)
            {
                writeAjaxStr("success");
            }
            else
            {
                writeAjaxStr("fail");
            }
        }
    }

    @Action(value = "list", results =
	{ @Result(name = "success", location = "/jsp/rank/facetGroupList.jsp") })
	public String list()
	{
		Map<String, Object> paramMap = queryParamMap();
		
		facetGroupList = facetGroupManager.list(paramMap);

        return SUCCESS;
    }

    @Action(value = "add", results =
    { @Result(name = "success", type = "redirect", location = "/FacetGroup/list.action?qcategoryid=${qcategoryid}") })
    public String add()
    {
        String groupId = getStringParam("group_id");
        String categoryId = getStringParam("category_id");
        String displayName = getStringParam("groupname");
        Integer weight = getIntParam("weight");
        Integer type = getIntParam("type");
        String facetId = getStringParam("facet_id");
        Integer isShow = getIntParam("is_show");

        FacetGroup facetGroup = new FacetGroup();
        facetGroup.setGroupId(groupId);
        facetGroup.setCategoryId(categoryId);
        facetGroup.setDisplayName(displayName);
        facetGroup.setWeight(weight);
        facetGroup.setType(type);
        facetGroup.setFacetid(facetId);
        facetGroup.setIsshow(isShow);
        facetGroup.setCreator(getLoginUserName());
        facetGroup.setUpdator(getLoginUserName());

        facetGroupManager.add(facetGroup);

        return SUCCESS;
    }

    @Action(value = "update", results =
    { @Result(name = "success", type = "redirect", location = "/FacetGroup/list.action?pageNumber=${pageNumber}&qcategoryid=${qcategoryid}") })
    public String update()
    {
        String id = getStringParam("id");
        if (id != null)
        {
            FacetGroup facetGroup = facetGroupManager.getById(id);
            if (facetGroup != null)
            {
                String groupId = getStringParam("group_id");
                String categoryId = getStringParam("category_id");
                String displayName = getStringParam("groupname");
                Integer weight = getIntParam("weight");
                Integer type = getIntParam("type");
                String facetId = getStringParam("facet_id");
                Integer isShow = getIntParam("is_show");

                facetGroup.setGroupId(groupId);
                facetGroup.setCategoryId(categoryId);
                facetGroup.setDisplayName(displayName);
                facetGroup.setWeight(weight);
                facetGroup.setType(type);
                facetGroup.setFacetid(facetId);
                facetGroup.setIsshow(isShow);
                facetGroup.setUpdator(getLoginUserName());

                facetGroupManager.update(facetGroup);
            }
        }
        return SUCCESS;
    }

    @Action(value = "delete", results =
    { @Result(name = "success", type = "redirect", location = "/FacetGroup/list.action") })
    public String delete()
    {
        List<String> ids = getStringListParam("ids");
        if (ids != null && !ids.isEmpty())
        {
            facetGroupManager.delete(ids);
        }
        return SUCCESS;
    }

    @Action(value = "changeShowState", results =
        { @Result(name = "success", type = "redirect", location = "/FacetGroup/list.action") })
    public String changeShowState(){
        List<String> ids = getStringListParam("ids");
        String type = getStringParam("type");
        if (ids != null && !ids.isEmpty() && type !=null && !type.isEmpty())
        {
            String groupIds = "";
            for(String id:ids){
                groupIds += "'" + id +"',";
            }
            groupIds = groupIds.substring(0, groupIds.lastIndexOf(","));
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("ids", groupIds);
            paramMap.put("isShow", type);
            facetGroupManager.changeShowState(paramMap);
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
        } catch (Exception e)
        {
        	logger.error(e.getMessage(), e);
        	return ids;
        }
    }

    public IFacetgroupManager getFacetGroupManager()
    {
        return facetGroupManager;
    }

    public void setFacetGroupManager(IFacetgroupManager facetGroupManager)
    {
        this.facetGroupManager = facetGroupManager;
    }

    public PaginatedList<FacetGroup> getFacetGroupList()
    {
        return facetGroupList;
    }

    public void setFacetGroupList(PaginatedList<FacetGroup> facetGroupList)
    {
        this.facetGroupList = facetGroupList;
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

    public String getQgroupid()
    {
        return qgroupid;
    }

    public void setQgroupid(String qgroupid)
    {
        this.qgroupid = qgroupid;
    }

    public String getQcategoryid()
    {
        return qcategoryid;
    }

    public void setQcategoryid(String qcategoryid)
    {
        this.qcategoryid = qcategoryid;
    }

    public String getQdisplayname()
    {
        return qdisplayname;
    }

    public void setQdisplayname(String qdisplayname)
    {
        this.qdisplayname = qdisplayname;
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
