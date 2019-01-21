/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.controller.action;

import java.io.IOException;
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

import com.coo8.btoc.util.StringUtils;
import com.coo8.topic.model.Toptitle;

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

@Namespace("/Toptitle")
public class ToptitleAction extends ToptitleBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4965571508968562841L;
	private static Logger logger = LoggerFactory.getLogger(ToptitleAction.class);
	protected static final String DEFAULT_SORT_COLUMNS = null;

	public void publishToptitle() {
		try {
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("site", getTopicSite());
			search.put("sortColumns", "createTime desc");
			search.put("pageNumber", pageNumber);
			search.put("pageSize", page_size);
			search.put("status", "1");
			List<Toptitle> ts = toptitleManager.findByMapLike(search);
			String content = "";
			content += "<div class='pop-brand' id='pop-brand'><em class='pop-brand-icon' id='pop-brand-icon'>&gt;</em><div class='pop-brand-con' id='pop-brand-con'><ul class='pop-brand-list clearfix'>";
			for (Toptitle t : ts) {
				content += "<li><b class='micon'></b><a href='" + t.getUrls() + "' target='_blank' title='"
						+ t.getNames() + "' >" + t.getNames() + "</a></li>";
			}
			content += "</ul></div></div>";
			com.coo8.btoc.util.file.FileUtils.fileWrite("/app/publish/catalog/main.html", content, false);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/toptitle/list.jsp") })
	public String list() {
		logger.info("Toptitle list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if (names != null && !"".equals(names)) {
			search.put("names", names);
		}
		search.put("site", getTopicSite());
		search.put("pageNumber", pageNumber);
		search.put("pageSize", page_size);
		search.put("sortColumns", "createTime desc");
		logger.info("Toptitle list 查询数据参数：" + search);
		listtop = toptitleManager.findByMapLike(search);
		logger.info("Toptitle list end!");
		return "success";
	}

	@Action(value = "show", results = { @Result(name = "success", location = "/") })
	public String show() {
		logger.info("Toptitle show start!");
		logger.info("Toptitle show end!");
		return "success";
	}

	@Action(value = "create", results = { @Result(name = "success", location = "/") })
	public String create() {
		logger.info("Toptitle create start!");
		logger.info("Toptitle create end!");
		return "success";
	}

	@Action(value = "save", results = {
			@Result(name = "success", type = "redirect", location = "/Toptitle/list.action") })
	public String save() {
		logger.info("Toptitle save start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		toptitle.setCreator(getLoginUserName());
		
		toptitle.setSite(getTopicSite());
		toptitle.setStatus("0");
		toptitleManager.save(toptitle);
		logger.info("Toptitle save end!");
		return "success";
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/") })
	public String edit() {
		logger.info("Toptitle edit start!");
		logger.info("Toptitle edit end!");
		return "success";
	}

	@Action(value = "update", results = { @Result(name = "success", location = "/") })
	public String update() {
		logger.info("Toptitle update start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		toptitleManager.update(this.toptitle);
		logger.info("Toptitle update end!");
		return "success";
	}

	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/Toptitle/list.action") })
	public String delete() {
		logger.info("Toptitle delete start!");
		String idString = this.getRequest().getParameter("idsString");
		logger.info("Toptitle delete 删除数据ids：" + idString);
		String ids[] = idString.split(";");
		for (int i = 0; i < ids.length; i++) {
			toptitleManager.deleteById(StringUtils.parseInt(ids[i]));
		}
		logger.info("Toptitle delete end!");
		return "success";
	}

	@Action(value = "publics", results = {
			@Result(name = "success", type = "redirect", location = "/Toptitle/list.action") })
	public String publics() {
		logger.info("Toptitle publics start!");
		String idString = this.getRequest().getParameter("idsString");
		logger.info("Toptitle publics idString:" + idString);
		String ids[] = idString.split(";");
		for (int i = 0; i < ids.length; i++) {
			Toptitle toptitles = toptitleManager.getById(StringUtils.parseInt(ids[i]));
			if (toptitles != null && toptitles.getId() > 0) {
				toptitles.setStatus("1");
				toptitleManager.update(toptitles);
			}
		}
		publishToptitle();
		logger.info("Toptitle publics end!");
		return "success";
	}

	@Action(value = "stops", results = {
			@Result(name = "success", type = "redirect", location = "/Toptitle/list.action") })
	public String stops() {
		logger.info("Toptitle stops start!");
		String idString = this.getRequest().getParameter("idsString");
		logger.info("Toptitle stops idString:" + idString);
		String ids[] = idString.split(";");
		for (int i = 0; i < ids.length; i++) {
			Toptitle toptitles = toptitleManager.getById(StringUtils.parseInt(ids[i]));
			if (toptitles != null && toptitles.getId() > 0) {
				toptitles.setStatus("0");
				toptitleManager.update(toptitles);
			}
		}
		publishToptitle();
		logger.info("Toptitle stops end!");
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
