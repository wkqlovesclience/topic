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

import com.coo8.btoc.business.interfaces.rank.IIndexFloorManager;
import com.coo8.btoc.model.rank.IndexFloor;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.controller.action.BaseAction;

/**
 * @author wangfufu
 *
 */
@Namespace("/IndexFloor")
public class IndexFloorAction extends BaseAction {

	private static final long serialVersionUID = -2735013644095139314L;

	private static Logger logger = LoggerFactory
			.getLogger(IndexFloorAction.class);

	private IIndexFloorManager indexFloorManager;

	private PaginatedList<IndexFloor> IndexFloorlist;

	private IndexFloor indexFloor;

	private Integer pageNumber = 1;

	private Integer pageSize = 10;

	private String ids;

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/rank/indexfloorlist.jsp") })
	public String listFloors() {
		logger.info("IndexFloor list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("pageNumber", pageNumber);
		search.put("pageSize", pageSize);
		logger.info("Floor list ��ѯ������" + search);
		IndexFloorlist = indexFloorManager.selectAllIndexFloor(search);
		logger.info("IndexFloor list end!");
		return "success";
	}

	/*
	 * ��ӷ���
	 */
	@Action(value = "createIndexFloor", results = { @Result(name = "success", location = "/jsp/rank/addfloorlist.jsp") })
	public String createIndexFloor() {
		logger.info("IndexFloor create start!");
        this.indexFloor = null;
		logger.info("IndexFloor create end!");
		return "success";
	}
	/*
	 * �����޸���ҳ����
	 */
	@Action(value = "saveIndexFloor", results = { @Result(name = "success", type = "redirect", location = "/IndexFloor/list.action") })
	public String saveIndexFloor() {
		logger.info("IndexFloor save start!");
		if (indexFloor !=null && indexFloor.getId()!=null) { 
			Date curDate = new Date(System.currentTimeMillis());//��ȡ��ǰʱ�� 
			indexFloor.setUpdate_time(curDate);
			indexFloorManager.updateIndexFloorById(indexFloor); // ����
		} else {
			indexFloorManager.save(indexFloor); // ����
		}
		logger.info("IndexFloor save end!");
		return "success";
	} 
	/*
	 * ɾ����ҳ¥��id����
	 */
	@Action(value = "deleteIndexFloorByid", results = { @Result(name = "success", type = "redirect", location = "/IndexFloor/list.action") })
	public String deleteHomeFloorByid() {
		logger.info("IndexFloor delete start!");
		if (ids != null && !"".equals(ids)) {
			for (Integer id : splitIds(ids, ";")) {
				logger.info("Floor delete ɾ������id��" + id);
				indexFloorManager.deleteByIndexFloorId(id);
			}
		}
		logger.info("IndexFloor delete end!");
		return "success";
	}

	/*
	 * �鿴�޸���ҳ¥��id��Ϣ
	 */
	@Action(value = "editIndexFloor", results = { @Result(name = "success", location = "/jsp/rank/addfloorlist.jsp") })
	public String edit() {
		logger.info("IndexFloor edit start!");
		if (indexFloor != null && indexFloor.getId() > 0) {
			this.indexFloor = indexFloorManager.selectIndexFloorById(indexFloor.getId());
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
	
	public IIndexFloorManager getIndexFloorManager() {
		return indexFloorManager;
	}

	public void setIndexFloorManager(IIndexFloorManager indexFloorManager) {
		this.indexFloorManager = indexFloorManager;
	}

	public PaginatedList<IndexFloor> getIndexFloorlist() {
		return IndexFloorlist;
	}

	public void setIndexFloorlist(PaginatedList<IndexFloor> indexFloorlist) {
		IndexFloorlist = indexFloorlist;
	}

	public IndexFloor getIndexFloor() {
		return indexFloor;
	}

	public void setIndexFloor(IndexFloor indexFloor) {
		this.indexFloor = indexFloor;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

}
