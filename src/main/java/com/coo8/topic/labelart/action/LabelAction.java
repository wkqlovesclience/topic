/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.labelart.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.common.utils.ConstantUtil;
import com.coo8.topic.labelart.modal.Label;
import com.coo8.topic.model.Titles;

@Namespace("/Label")
public class LabelAction extends LabelBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2637610698913610934L;


	private static Logger log = LoggerFactory.getLogger(LabelAction.class);
	
	
	private String baseUrl;
	
	//ҳ��
	@Action(value = "create", results = { @Result(name = "success", location = "/jsp/label/create.jsp") })
	public String create() {
		log.info("Lable create start!");
		log.info("Label create end!");
		return "success";
	}
	
	//�б�
	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/label/label_manage.jsp") })
	public String list() {
		log.info("Label list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if (names != null && !"".equals(names)) {
			search.put("names", names);
		}
		search.put("site", getTopicSite());
		search.put("pageNumber", pageNumber);
		search.put("pageSize", page_size);
		search.put("type", 2);
		search.put("sortColumns", "create_Time desc");

		log.info("Label list ��ѯ������" + search);

		listtags = labelManager.findPageByMapLike(search);
		String site = getTopicSite();
		if (site.equals(ConstantUtil.SITE_FLAG_COO8)) {
			baseUrl = ConstantUtil.DOMAIN_COO8_BASEURL + ConstantUtil.DOMAIN_COO8_TITLE_BASEURL + "/";
		} else {
			baseUrl = ConstantUtil.DOMAIN_GOME_BASEURL + ConstantUtil.DOMAIN_GOME_TITLE_BASEURL + "/";
		}

		log.info("Label list baseUrl:" + baseUrl);
		log.info("Label list end!");
		return "success";
	}
	
	//��Ҷ������б�
		@Action(value = "search_list", results = { @Result(name = "success", location = "/jsp/label/everySearch.jsp") })
		public String search_list() {
			log.info("Label list start!");
			Map<String, Object> search = new HashMap<String, Object>();
			if (names != null && !"".equals(names)) {
				search.put("names", names);
			}
			search.put("site", getTopicSite());
			search.put("pageNumber", pageNumber);
			search.put("pageSize", page_size);
			search.put("type", 2);
			search.put("sortColumns", "create_Time desc");
			search.put("everySearch", "Y");

			log.info("Label list ��ѯ������" + search);

			listtags = labelManager.findPageByMapLike(search);
			String site = getTopicSite();
			if (site.equals(ConstantUtil.SITE_FLAG_COO8)) {
				baseUrl = ConstantUtil.DOMAIN_COO8_BASEURL + ConstantUtil.DOMAIN_COO8_TITLE_BASEURL + "/";
			} else {
				baseUrl = ConstantUtil.DOMAIN_GOME_BASEURL + ConstantUtil.DOMAIN_GOME_TITLE_BASEURL + "/";
			}

			log.info("Label list baseUrl:" + baseUrl);
			log.info("Label list end!");
			return "success";
		}
	
	//ɾ��
	@Action(value = "delete", results = {
	@Result(name = "success", type = "redirect", location = "/Label/list.action") })
	public String delete() {
		log.info("Label delete start!");
		String idString = this.getRequest().getParameter("idsString");
		log.info("Label delete ɾ������id��" + idString);
		String ids[] = idString.split(";");
		for (int i = 0; i < ids.length; i++) {
			labelManager.deleteById(Integer.valueOf(ids[i]));
		}
		log.info("Label delete end!");
		return "success";
	}
	
	//��Ҷ�����ɾ��
		@Action(value = "search_delete", results = {
		@Result(name = "success", type = "redirect", location = "/Label/search_list.action") })
		public String search_delete() {
			log.info("Label delete start!");
			String idString = this.getRequest().getParameter("idsString");
			log.info("Label delete ɾ������id��" + idString);
			String ids[] = idString.split(";");
			for (int i = 0; i < ids.length; i++) {
				Label temp = labelManager.getById(Integer.valueOf(ids[i]));
				if(null != temp){
				temp.setUpdateTime(new Date());
				temp.setEverySearch("N");
				String username = getLoginUserName();
				if (null != username && !"".equals(username)) {
					temp.setModifier(username);
				}
				labelManager.update(temp);
				}
			}
			log.info("Label delete end!");
			return "success";
		}
	
	//����
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirect", location = "/Label/list.action") })
	public String update() throws UnsupportedEncodingException {
		log.info("Label update start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		if (label != null) {
			Label temp = labelManager.getById(label.getId());
			label.setNames(new String(label.getNames()));
			label.setUpdateTime(new Date());
			label.setTypes(temp.getTypes());
			label.setId(temp.getId());

			String username = getLoginUserName();
			if (null != username && !"".equals(username)) {
				label.setModifier(username);
			}

			labelManager.update(label);
		}
		log.info("Label update end!");
		return "success";
	}
	
	//��Ҷ����Ѹ���
		@Action(value = "search_update", results = { @Result(name = "success", location = "/jsp/label/everySearch.jsp")})
		public String search_update() throws UnsupportedEncodingException {
			log.info("Label update start!");
			String ids = this.getRequest().getParameter("ids");
			if (ids != null) {
				Label temp = labelManager.getById(Integer.valueOf(ids));
				if(null == temp){
					writeAjaxStr("one");
					return "success";
				}
				if("Y".equals(temp.getEverySearch())){
					writeAjaxStr("two");
					return "success";
				}
				if("N".equals(temp.getStates())){
					writeAjaxStr("three");
					return "success";
				}
				temp.setUpdateTime(new Date());
				temp.setTypes(temp.getTypes());
				temp.setEverySearch("Y");
				String username = getLoginUserName();
				if (null != username && !"".equals(username)) {
					temp.setModifier(username);
				}

				labelManager.update(temp);
				writeAjaxStr("ok");
			}
			log.info("Label update end!");
			return "success";
		}
	
	
	/**
	 * ��ǩά��ҳ ��������
	 */
	@Action(value = "publishLabelHomePage")
	public void publishLabelHomePage() {
		log.info("Label publishLabelHomePage start!");
		String site = getTopicSite();
		int i = 9;
		int j = 9;
		i = labelManager.publicLabelHomePage(site);
		j = labelManager.publicLabelListPage(site);
		writeAjaxStr(i + "," + j + "");
		log.info("Label publishLabelHomePage end!");
	}
	
	//��ǩά��ҳ -add
	@Action(value = "labelIndexAdd", results = {
	@Result(name = "success", type = "redirect", location = "/Label/listLabelIndex.action") })
	public String labelIndexAdd() {
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		String pinyin = Chinese2PinyinUtil.parseChinese(labelIndex.getTitle());
		String operator = getLoginUserName();

		labelIndex.setPinyin(pinyin);
		labelIndex.setSource(0);
		labelIndex.setOperator(operator);
		labelIndex.setStatus(1);
		labelIndex.setSite(getTopicSite());
		labelManager.insertLabelIndex(labelIndex);

		return "success";
	}
	
	//��ǩά��ҳ - �б�ҳ
	@Action(value = "listLabelIndex", results = {
	@Result(name = "success", location = "/jsp/label/listLabelIndex.jsp") })
	public String listTitleIndex() {
		Map<String, Object> search = new HashMap<String, Object>();
		if (null != labelIndex) {
			String cindex = labelIndex.getCindex();
			Integer titleId = labelIndex.getLabelId();
			if (null != cindex && !"".equals(cindex)) {
				search.put("cindex", cindex);
			}
			if (null != titleId && !"".equals(titleId.toString())) {
				search.put("labelId", titleId);
			}
		}
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		search.put("sortColumns", "UPDATETIME DESC");

		labelIndexList = labelManager.findLabelIndexByMap(search);
		return SUCCESS;
	}
	
	//��ǩά��ҳ - ���ݱ�� ��ѯ��ǩ
	@Action(value = "getLabelName")
	public void getLabelName() {
		int titleId = Integer.parseInt(getRequest().getParameter("titleId"));

		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", titleId);
		queryMap.put("site", getTopicSite());

		List<Label> isTitleIdExist = labelManager.findListByMap(queryMap);

		if (isTitleIdExist.isEmpty()) {
			writeAjaxStr("ERROR����Ų����ڣ���ȷ�Ϻ�������");
			return;
		}

		// ��t_label_index��ר����ΪlabelId������t_label_art��ר����ΪId
		queryMap.remove("id");
		queryMap.put("labelId", titleId);
		queryMap.put("source", 0); // ֻ�����ֶ����

		int selectCount = labelManager.isAddRepeat(queryMap);
		if (selectCount != 0) {
			writeAjaxStr("ERROR���ı�ǩ�Ѿ���ӹ������ˣ������ظ����");
		} else {
			String titleName = labelManager.getById(titleId).getNames();
			writeAjaxStr(titleName);
		}
	}
	
	/**
	 * ���±�ǩ�����޸�ҳ��
	 */
	@Action(value = "labelIndexEdit", results = { @Result(name = "success", location = "/jsp/label/update.jsp") })
	public String labelIndexEdit() {
		int id = Integer.parseInt(getRequest().getParameter("id"));
		labelIndex = labelManager.getLabelIndexById(id);
		return "success";
	}
	
	/**
	 * ���±�ǩ�����޸ķ���
	 * @return
	 */
	@Action(value = "labelIndexUpdate", results = {
	@Result(name = "success", type = "redirect", location = "/Label/listLabelIndex.action") })
	public String labelIndexUpdate() {
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		String currentUserName = getLoginUserName();
		labelIndex.setOperator(currentUserName);
		labelManager.updateLabelIndex(labelIndex);
		return "success";
	}
	
}
