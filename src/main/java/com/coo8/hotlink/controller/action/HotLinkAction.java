package com.coo8.hotlink.controller.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.hotlink.model.HotLink;


@Namespace("/HotLink")
public class HotLinkAction extends HotLinkBaseAction {
	 private  static Logger logger = LoggerFactory.getLogger(HotLinkAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -7000678345431263566L;
	
	private int command = 0; //页面保存操作中新增与修改标志
	
	private String tags = "";
	

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}


	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}


	/**
	 * @return the command
	 */
	public int getCommand() {
		return command;
	}


	/**
	 * @param command the command to set
	 */
	public void setCommand(int command) {
		this.command = command;
	}


	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/hotlink/list.jsp") })
	public String list() {
	  Map<String, Object> search = new HashMap<String, Object>();
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		search.put("hotLinkId", getStringParam("hotLinkId"));
		search.put("hotLinkName", getStringParam("hotLinkName"));
		search.put("moduleId", getStringParam("moduleId"));
		search.put("hotLinkModuleType", getStringParam("hotLinkModuleType"));
		hotLinkList = hotLinkManager.findPaginatedHotLinkByMap(search);
		return "success";
	}
	

	@Action(value = "create", results = { @Result(name = "success", location = "/jsp/hotlink/create.jsp") })
	public String create() {
		String value = getRequest().getParameter("pageNumber");
		getRequest().setAttribute("pageNumber", value);
		return "success";
	}
	
	@SuppressWarnings("deprecation")
	@Action(value = "save", results = { @Result(name = "success", type = "redirect", location = "/HotLink/list.action")})
	public String save() {
		String username = getLoginUserName();
		if(hotLink != null){
			HotLink hot = hotLinkManager.getHotLinkById(hotLink.getId());
			if(command == COMMAND_EDIT){
				hotLink.setUpdateUser(username);
				
			}else if(command == COMMAND_CREATE){
				hotLink.setCreateUser(username);
			}
			if(hotLink.getId() != null){
				hotLink.setUpdateDate(new java.util.Date());
				hotLink.setModuleId(hot.getModuleId());
				hotLink.setModuleType(hot.getModuleType());
				hotLinkManager.update(hotLink);
			}else{
				String moduleId = getRequest().getParameter("moduleId");
				String moduleType = getRequest().getParameter("moduleType");
				hotLink.setModuleId(Integer.valueOf(moduleId));
				hotLink.setModuleType(Integer.valueOf(moduleType));
				hotLink.setCreateDate(new java.util.Date());
				hotLinkManager.save(hotLink);
			}
		}
		return "success";
	}
	
	
	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/hotlink/create.jsp") })
	public String edit() {
		command  = COMMAND_EDIT; //设置文件保存方式
		if (hotLink.getId() != null) {
			this.hotLink = hotLinkManager.getHotLinkById(hotLink.getId());
		}
		return "success";
	}


	@Action(value = "checkChongFu")
	public void checkChongFu() {
		HotLink entity = new HotLink();
		 String hotName = this.getRequest().getParameter("hotName");
		 try {
			 hotName = java.net.URLDecoder.decode(new String(hotName.getBytes("GBK"),"UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		 String pcUrl = this.getRequest().getParameter("pcUrl");
		 entity.setHotName(hotName);
		 entity.setPcUrl(pcUrl);
		 HotLink hotlink = hotLinkManager.getByNamePcUrl(entity);
		if(hotlink == null){
			writeAjaxStr("yes");
		}else{
			writeAjaxStr("no");
		}
	}
	
	
	
	@Action(value = "delete", results = { @Result(name = "success", type = "redirect", location = "/HotLink/list.action")})
	public String delete() {
		if (tags != null && !"".equals(tags)) {
			Set<Integer> hs = this.spli(tags, ";");
			for (Integer tid : hs) {
				hotLinkManager.delete(tid);
			}
		}
		return "success";
	}
	
	public Set<Integer> spli(String str, String sign) {
		String s[] = str.split(sign);
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < s.length; i++) {
			if (!"".equals(s[i])) {
				try {
					ids.add(Integer.parseInt(s[i]));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		HashSet<Integer> hs = new HashSet<Integer>();
		if (!ids.isEmpty()) {
			hs.addAll(ids);
		}
		return hs;
	}
	
}
