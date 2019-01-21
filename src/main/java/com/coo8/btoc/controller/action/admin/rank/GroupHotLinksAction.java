/**
 * 
 */
package com.coo8.btoc.controller.action.admin.rank;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.business.interfaces.rank.IGroupHotLinksManager;
import com.coo8.btoc.model.rank.GroupHotLinks;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.controller.action.BaseAction;

/**
 * @author wangfufu
 *
 */
@Namespace("/GroupHotLinks")
public class GroupHotLinksAction extends BaseAction{

	private static final long serialVersionUID = -2735013644095139314L;

	private static Logger logger = LoggerFactory
			.getLogger(IndexFloorAction.class);

	private IGroupHotLinksManager groupHotlinksManager;

	private PaginatedList<GroupHotLinks> groupHotLinkslist;

	private GroupHotLinks groupHotLinks;

	private Integer pageNumber = 1;

	private Integer pageSize = 10;

	private String ids;

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/GroupHotLinks/list.jsp") })
	public String listFloors() {
		logger.info("GroupHotLinks list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		String groupid = getRequest().getParameter("groupid");
		String groupname = getRequest().getParameter("groupname");
		if (groupid != null && !"".equals(groupid))
		{
			search.put("groupId", groupid);
		}
		if (groupname != null && !"".equals(groupname))
		{
			search.put("groupName", groupname);
		} 
		
		search.put("pageNumber", pageNumber);
		search.put("pageSize", pageSize);
		logger.info("GroupHotLinks list 查询参数：" + search);
		groupHotLinkslist = groupHotlinksManager.selectAllGroupHotLinks(search);
		logger.info("GroupHotLinks list end!");
		return "success";
	}

	/*
	 * 添加分类
	 */
	@Action(value = "create", results = { @Result(name = "success", location = "/jsp/GroupHotLinks/create.jsp") })
	public String createIndexFloor() {
		logger.info("GroupHotLinks create start!");
        this.groupHotLinks = null;
		logger.info("GroupHotLinks create end!");
		return "success";
	}
	/*
	 * 保存修改首页分类
	 */
	@Action(value = "saveGroupHotLinks", results = { @Result(name = "success", type = "redirect", location = "/GroupHotLinks/list.action") })
	public String saveIndexFloor() {
		logger.info("GroupHotLinks save start!");
		if (groupHotLinks !=null && groupHotLinks.getID()!=null) { 
			Date curDate = new Date(System.currentTimeMillis());//获取当前时间 
			groupHotLinks.setUPDATE_TIME(curDate);
			groupHotlinksManager.updateGroupHotLinksById(groupHotLinks); // 更新
		} else {
			groupHotlinksManager.save(groupHotLinks); // 插入
		}
		logger.info("GroupHotLinks save end!");
		return "success";
	} 
	/*
	 * 删除首页楼层id数据
	 */
	@Action(value = "deleteIndexFloorByid", results = { @Result(name = "success", type = "redirect", location = "/GroupHotLinks/list.action") })
	public String deleteHomeFloorByid() {
		logger.info("GroupHotLinks delete start!");
		if (ids != null && !"".equals(ids)) {
			for (Integer id : splitIds(ids, ";")) {
				logger.info("GroupHotLinks delete 删除数据id：" + id);
				groupHotlinksManager.deleteByGroupHotLinksId(id);
			}
		}
		logger.info("GroupHotLinks delete end!");
		return "success";
	}

	/*
	 * 查看修改首页楼层id信息
	 */
	@Action(value = "editGroupHotLinks", results = { @Result(name = "success", location = "/jsp/GroupHotLinks/create.jsp") })
	public String edit() {
		logger.info("IndexFloor edit start!");
		if (groupHotLinks != null && groupHotLinks.getID() > 0) {
			this.groupHotLinks = groupHotlinksManager.selectGroupHotLinksById(groupHotLinks.getID());
		}
		logger.info("IndexFloor edit end!");
		return "success";
	}

	private HashSet<Integer> splitIds(String str, String sign) {
		String s[] = str.split(sign);
		List<Integer> newids = new ArrayList<Integer>();
		for (int i = 0; i < s.length; i++) {
			if (!"".equals(s[i])) {
				newids.add(Integer.parseInt(s[i]));
			}
		}
		HashSet<Integer> hs = new HashSet<Integer>();
		if (!newids.isEmpty()) {
			hs.addAll(newids);
		}
		return hs;
	}

	public IGroupHotLinksManager getGroupHotlinksManager() {
		return groupHotlinksManager;
	}

	public void setGroupHotlinksManager(IGroupHotLinksManager groupHotlinksManager) {
		this.groupHotlinksManager = groupHotlinksManager;
	}

	 

	public PaginatedList<GroupHotLinks> getGroupHotLinkslist() {
		return groupHotLinkslist;
	}

	public void setGroupHotLinkslist(PaginatedList<GroupHotLinks> groupHotLinkslist) {
		this.groupHotLinkslist = groupHotLinkslist;
	}
 

	public GroupHotLinks getGroupHotLinks() {
		return groupHotLinks;
	}

	public void setGroupHotLinks(GroupHotLinks groupHotLinks) {
		this.groupHotLinks = groupHotLinks;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	 

	
	
	
}
