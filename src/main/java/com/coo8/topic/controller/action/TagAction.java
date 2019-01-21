/**
 * @author tianyu-ds
 * @date 2013-9-30
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 热词的标签添加 
 */
package com.coo8.topic.controller.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.business.interfaces.ITagManager;
import com.coo8.topic.model.Tag;

import net.sf.json.JSONObject;

@Namespace("/Tag")
public class TagAction extends BaseAction {
	private static final long serialVersionUID = 7392464771632462538L;
	private static Logger logger = LoggerFactory.getLogger(TagAction.class);

	private ITagManager tagManager;

	protected Integer pageNumber = 1;

	protected Integer page_size = 10;

	protected PaginatedList<Tag> listTag, subTags;

	private String qtagname, qcreator, qcreateTime;

	/**
	 * @desc 进入到编辑或添加标签页面，对标签操作
	 * @return
	 */
	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/tag/taglist.jsp"),
			@Result(name = "input", location = "/jsp/tag/subtaglist.jsp") })
	public String edit() {
		logger.info("Tag edit start!");
		Integer id = this.getIntParam("id");
		logger.info("Tag edit id:" + id);

		if (id != null) {
			Tag tag = tagManager.getById(id);
			if (tag != null) {
				getRequest().setAttribute("tag", tag);
			}
		}

		String act = getStringParam("act");
		logger.info("Tag edit act:" + act);
		if ("subtag".equals(act)) {
			List<Tag> tags = tagManager.getByParentId(0);
			getRequest().setAttribute("firstTags", tags);
			return INPUT;
		}

		logger.info("Tag edit end!");
		return SUCCESS;
	}

	/**
	 * @desc 标签列表
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/tag/taglist.jsp"),
			@Result(name = "input", location = "/jsp/tag/subtaglist.jsp") })
	public String list() {
		logger.info("Tag list start!");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", pageNumber);
		paramMap.put("pageSize", page_size);
		paramMap.put("qtagname", getStringParam("qtagname"));
		paramMap.put("qcreator", getStringParam("qcreator"));
		paramMap.put("qcreateTime", getStringParam("qcreateTime"));
		paramMap.put("qsite", getTopicSite());

		String act = getStringParam("act");
		logger.info("Tag list act:" + act);
		if ("subtag".equals(act)) {
			Integer parentId = this.getIntParam("parent_id");
			Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("qparentId", 0);
			List<Tag> firstTags = tagManager.getAllFirstTag();
			getRequest().setAttribute("firstTags", firstTags);

			if (parentId == null) {
				subTags = tagManager.getChildren(paramMap); // 获取所有的子标签
			} else {
				paramMap.put("qparentId", parentId);
				subTags = tagManager.getByParentId(paramMap); // 根据parantId获取子标签
			}

			return INPUT;
		}

		listTag = tagManager.list(paramMap);

		getRequest().setAttribute("listTag", listTag);
		logger.info("Tag list end!");

		return SUCCESS;
	}

	/**
	 * @desc 进入到编辑或添加标签页面，对标签操作
	 * @return
	 */
	@Action(value = "update", results = { @Result(name = "success", type = "redirect", location = "/Tag/list.action"),
			@Result(name = "input", type = "redirect", location = "/Tag/list.action") })
	public String update() {
		logger.info("Tag update start!");
		Integer id = this.getIntParam("id");

		logger.info("Tag update id:" + id);

		if (null != id) {
			String tagName = this.getStringParam("tagname");

			Tag tag = tagManager.getById(id);
			tag.setTagName(tagName);
			tag.setModifier(getLoginUserName());

			tagManager.update(tag);
		}

		String act = getStringParam("act");
		logger.info("Tag update act:" + act);
		if ("subtag".equals(act)) {
			return INPUT;
		}
		logger.info("Tag update end!");
		return SUCCESS;
	}

	/**
	 * @desc 删除标签操作
	 * @return
	 */
	@Action(value = "delete")
	public void delete() {
		logger.info("Tag delete start!");
		List<Integer> ids = this.getIntListParam("ids");
		String act = getStringParam("act");

		String result = "error";
		JSONObject resultJosn = new JSONObject();
		if (!ids.isEmpty()) {
			result = "emptyParam";
			resultJosn.put("result", result);
			resultJosn.put("url", "/Tag/list.action?act=" + (act == null ? "" : act));
		} else if ("subtag".equals(act)) {
			String subtagResult = "subtag:";
			int productCount = 0;
			for (Integer valueId : ids) {
				productCount = tagManager.productCountOfSubtag(valueId);
				if (productCount > 0) {
					subtagResult += valueId + ",";
				}
			}
			if (!"subtag:".equals(subtagResult)) {
				subtagResult = subtagResult.substring(0, subtagResult.lastIndexOf(","));
				resultJosn.put("result", subtagResult);
			} else {
				resultJosn.put("result", "success");
				tagManager.delete(ids);
			}
			resultJosn.put("url", "/Tag/list.action?act=subtag");
		} else {
			String tagResult = "firsttag:";
			int subtagCount = 0;
			for (Integer valueId : ids) {
				subtagCount = tagManager.subtagCountOfFirstTag(valueId);
				if (subtagCount > 0) {
					tagResult += valueId + ",";
				}
			}
			if (!"firsttag:".equals(tagResult)) {
				tagResult = tagResult.substring(0, tagResult.lastIndexOf(","));
				resultJosn.put("result", tagResult);
			} else {
				resultJosn.put("result", "success");
				tagManager.delete(ids);
			}
			resultJosn.put("url", "/Tag/list.action");
		}
		logger.info("Tag delete resultJosn:" + resultJosn.toString());
		writeAjaxStr(resultJosn.toString());
		logger.info("Tag delete end!");
	}

	/**
	 * @desc 添加标签操作
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/jsp/tag/taglist.jsp"),
			@Result(name = "input", location = "/jsp/tag/subtaglist.jsp") })
	public String add() {
		logger.info("Tag add start!");
		String tagName = this.getStringParam("tagname");
		Integer parentId = this.getIntParam("parent_id");

		Tag tag = new Tag();
		tag.setTagName(tagName);
		tag.setParentId(parentId == null ? 0 : parentId);
		tag.setCreator(getLoginUserName());
		tag.setModifier(getLoginUserName());

		tagManager.add(tag);

		String act = getStringParam("act");
		logger.info("Tag add tagName:" + tagName + ",parentId" + parentId + ",act" + act);
		if ("subtag".equals(act)) {
			return INPUT;
		}
		logger.info("Tag add end!");
		return SUCCESS;
	}

	public ITagManager getTagManager() {
		return tagManager;
	}

	public void setTagManager(ITagManager tagManager) {
		this.tagManager = tagManager;
	}

	public String getQtagname() {
		return qtagname;
	}

	public void setQtagname(String qtagname) {
		this.qtagname = qtagname;
	}

	public String getQcreator() {
		return qcreator;
	}

	public void setQcreator(String qcreator) {
		this.qcreator = qcreator;
	}

	public String getQcreateTime() {
		return qcreateTime;
	}

	public void setQcreateTime(String qcreateTime) {
		this.qcreateTime = qcreateTime;
	}

	public PaginatedList<Tag> getListTag() {
		return listTag;
	}

	public void setListTag(PaginatedList<Tag> listTag) {
		this.listTag = listTag;
	}

	public PaginatedList<Tag> getSubTags() {
		return subTags;
	}

	public void setSubTags(PaginatedList<Tag> subTags) {
		this.subTags = subTags;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
}
