/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.controller.action;

import java.io.*;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.coo8.topic.model.*;
import com.gome.productwords.model.ProductWordsCard;
import com.gome.stage.interfaces.item.IGomeProductService;
import com.gome.utils.ExcelPoiUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.persistence.interfaces.items.IItemsDAO;
import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.common.utils.ConstantUtil;
import com.coo8.common.utils.StringUtil;
import com.coo8.topic.business.interfaces.IAllHotKeywordManager;
import com.coo8.topic.business.interfaces.IKeywordsManager;
import com.coo8.topic.business.interfaces.IKeywordsRelManager;
import com.coo8.topic.business.interfaces.INewsManager;
import com.coo8.topic.business.interfaces.ITitleGoodsManager;
import com.coo8.topic.labelart.service.inter.LabelManager;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.PageOrientation;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.util.parsing.combinator.testing.Str;

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

@Namespace("/Titles")
public class TitlesAction extends TitlesBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8812797487670265999L;
	private final String SITE = "gome";
	private static Logger logger = LoggerFactory.getLogger(TitlesAction.class);
	private String keys;
	private String errMsg = "";
	private String errMsg2 = "";
	private IItemsDAO itemDAO;
	private String tags = "";
	private String[] keyss;
	private String[] keyssUrl;
	private String gids = "";
	private int command = 0; // ҳ�汣��������������޸ı�־
	private int forwards = 0; // ҳ����ת���� 0 ��ת��ר��ҳ�棬1��ת���¼�ר��ҳ��
	private String titleBaseUrl = "";
	private String titleTestBaseUrl = "";
	protected IKeywordsManager keywordsManager;
	protected IKeywordsRelManager keywordsRelManager;
	protected ITitleGoodsManager titleGoodsManager;
	protected INewsManager newsManager;
	protected LabelManager labelManager;
	Map<String, String> urlMap = new HashMap<String, String>();
	List<Keywords> urlMapKey = new ArrayList<Keywords>();

	private IAllHotKeywordManager allHotKeywordManager;
	
	protected PaginatedList<ImportLog> listImportLogs;
	private File words;
    private String fileType;
	
	/**
	 * @return the listImportLogs
	 */
	public PaginatedList<ImportLog> getListImportLogs() {
		return listImportLogs;
	}

	/**
	 * @param listImportLogs
	 *            the listImportLogs to set
	 */
	public void setListImportLogs(PaginatedList<ImportLog> listImportLogs) {
		this.listImportLogs = listImportLogs;
	}
	
	/**
	 * @return the words
	 */
	public File getWords() {
		return words;
	}

	/**
	 * @param words
	 *            the words to set
	 */
	public void setWords(File words) {
		this.words = words;
	}
	
	public Map<String, String> getUrlMap() {
		return urlMap;
	}

	public void setUrlMap(Map<String, String> urlMap) {
		this.urlMap = urlMap;
	}

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/title/list.jsp") })
	public String list() {
		logger.info("Titles list start!");
		Map<String, Object> search = new HashMap<String, Object>();

		if (conditvalue != null && !"".equals(conditvalue)) {
			conditvalue = decode(conditvalue);
			search.put(conditkey, conditvalue);
		}
		if (null != titles && titles.getCreateTime() != null && !"".equals(titles.getCreateTime().toString())) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			tempTime = sf.format(titles.getCreateTime());
			search.put("tempTime", tempTime);
		}
		if (tempStat == STATUS_PUBLISH) {
			search.put("publicStat", "N");
		} else if (tempStat == STATUS_EDIT) {
			search.put("publicStat", "Y");
			search.put("editStat", "Y");
		}
		if (creator != null && !"".equals(creator)) {
			creator = decode(creator);
			search.put("creator", creator);
		}
		search.put("site", getTopicSite());
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		logger.info("Titles list ��ѯ���ݲ�����" + search);
		titleList = titlesManager.findLikeByMap(search);
		// ����ר���ǩ
		setTitleTag(titleList);
		// ����ר�����URL
		setTitleUrl();

		logger.info("Titles list end!");
		return "success";
	}

	/**
	 * ����ר�ⷢ���Լ�Ԥ��URL
	 */
	private void setTitleUrl() {
		String site = getTopicSite();
		if (site.equals(ConstantUtil.SITE_FLAG_COO8)) {
			this.titleBaseUrl = ConstantUtil.DOMAIN_COO8_BASEURL + ConstantUtil.DOMAIN_COO8_TITLE_BASEURL + "/";
			this.titleTestBaseUrl = ConstantUtil.DOMAIN_COO8_BASEURL + ConstantUtil.DOMAIN_COO8_TITLE_BASEURL
					+ ConstantUtil.DOMAIN_TEST_BASEURL + "/";
			;
		} else {
			this.titleBaseUrl = ConstantUtil.DOMAIN_GOME_BASEURL + "/";
					}

	}

	@Action(value = "listConditTitles", results = {
			@Result(name = "success", location = "/jsp/title/listAllTitles.jsp") })
	public String listConditTitles() {
		logger.info("Titles listConditTitles start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if (conditvalue != null && !"".equals(conditvalue)) {
			search.put(conditkey, conditvalue);
		}
		if (null != titles && titles.getCreateTime() != null && !"".equals(String.valueOf(titles.getCreateTime()))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			tempTime = sf.format(titles.getCreateTime());
			search.put("tempTime", tempTime);
		}
		if (tempStat == 1) {
			search.put("publicStat", "N");
		} else if (tempStat == 2) {
			search.put("editStat", "Y");
		}
		if (creator != null && !"".equals(creator)) {
			search.put("creator", creator);
		}
		search.put("site", getTopicSite());
		search.put("pageNumber", page_index);
		search.put("pageSize", 30);
		search.put("sortColumns", "create_Time desc");
		logger.info("Titles listConditTitles ��ѯ���ݲ�����" + search);
		titleList = titlesManager.findDropsListByMap(search);
		// ����ר���ǩ
		setTitleTag(titleList);
		logger.info("Titles listConditTitles end!");
		return "success";
	}

	/**
	 * ����ר���ǩ
	 * 
	 * @param titleList
	 */
	private void setTitleTag(PaginatedList<Titles> titleList) {
		// ��ʾ��ǩ
		if (titleList != null && !titleList.isEmpty()) {
			Map<String, Object> searchKey = new HashMap<String, Object>();
			List<Integer> ids = getTitleIdsByList(titleList);
			if (null != ids) {
				searchKey.put("ids", ids);
			}
			searchKey.put("site", getTopicSite());
			searchKey.put("types", TYPES_TAGS);
			List<Keywords> keyList = keywordsManager.findAllTab(searchKey);
			Map<Integer, String> mapkey = new HashMap<Integer, String>();
			if (keyList != null && !keyList.isEmpty()) {
				for (Keywords k : keyList) {
					Integer titleId = k.getTitleId();
					String tag = k.getNames();
					if (mapkey.containsKey(titleId)) {
						tag += "," + mapkey.get(titleId);
					}
					mapkey.put(titleId, tag);
				}
			}
			for (int i = 0; i < titleList.size(); i++) {
				Titles t = (Titles) titleList.get(i);
				if (mapkey.get(t.getId()) != null) {
					titleList.get(i).setTags(mapkey.get(t.getId()));
				}
			}

		}
	}

	private List<Integer> getTitleIdsByList(List<Titles> titleList) {
		List<Integer> ids = new ArrayList<Integer>();
		if (null != titleList && !titleList.isEmpty()) {
			for (Titles titles : titleList) {
				ids.add(titles.getId());
			}
		}
		return ids;
	}

	@Action(value = "create", results = { @Result(name = "success", location = "/jsp/title/create.jsp") })
	public String create() {
		logger.info("Titles create start!");
		logger.info("Titles create end!");
		return "success";
	}

	@Action(value = "checkPaths")
	public void checkPaths() {
		logger.info("Titles checkPaths start!");
		String str = "";
		if (titles != null) {
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("paths", titles.getPaths());
			search.put("site", getTopicSite());
			logger.info("Titles checkPaths ��ѯ���ݲ�����" + search);
			List l = titlesManager.findListByMap(search);
			if (l.isEmpty()) {
				str = "yes";
			}
		}
		writeAjaxStr(str);
		logger.info("Titles checkPaths end!");
	}

	@Action(value = "checkTitleName")
	public void checkTitleName() {
		logger.info("Titles checkTitleName start!");
		String str = this.getRequest().getParameter("titlesNames");
		String id = this.getRequest().getParameter("titleId");
		logger.info("Titles checkTitleName titlesNames:" + str + ",titleId:" + id);
		if (str != null) {
			try {
				str = URLDecoder.decode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			}
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("title", str);
			search.put("site", getTopicSite());
			List l = titlesManager.findListByMap(search);

			if (l.isEmpty()) {
				writeAjaxStr("yes");
			} else if (l.size() == 1) {
				if (id != null && !"".equals(id) && id.equals(((Titles) l.get(0)).getId().toString())) { // �޸�ʱ��
					writeAjaxStr("yes");
				}
			}
			/**
			 * У��ר�����ܿ����Ƿ����
			 */
			List<AllHotKeyword> allHotKeywordList = this.allHotKeywordManager.selectAllHotKeyword(search);

			if (allHotKeywordList != null && !allHotKeywordList.isEmpty()) {
				writeAjaxStr("no");
			}
		}
		writeAjaxStr(str);
		logger.info("Titles checkTitleName end!");
	}

	@Action(value = "checkGoodsId")
	public void checkGoodsId() {
		logger.info("Titles checkGoodsId start!");
		String str = "";
		if (titles != null) {

			String desc = this.checkitem(titles.getGoodsId());

			if (null != desc && !"".equals(desc)) {
				str = desc.replaceAll("^jianjie\\(\"", "").replaceAll("\"\\)$", "");
			} else {
				str = "noGoods";
			}
		}
		writeAjaxStr(str);
		logger.info("Titles checkGoodsId end!");
	}

	@Action(value = "checkGoodsExists")
	public void checkGoodsExists() {
		logger.info("Titles checkGoodsExists start!");
		String str = "";
		String desc = "";
		if (titles != null) {
			String productName = titlesManager.checkItemName(titles.getGoodsId());
			if (null != productName && !"".equals(productName.trim())) {
				str = productName;
				// �жϸ���ƷID�Ƿ�ռ��
				desc = titlesManager.getATGItemDescByGoodId(titles.getGoodsId());
			} else {
				str = "noGoods";
			}

		}
		if (!"exist".equals(str) && !"noGoods".equals(str)) {
			JSONObject rest = new JSONObject();
			rest.put("productname", str);
			rest.put("productdesc", desc);
			writeAjaxStr(rest.toString());
		} else {
			writeAjaxStr(str);
		}
		logger.info("Titles checkGoodsExists end!");

	}

	@Action(value = "checkKeywords")
	public void checkKeywords() {
		logger.info("Titles checkKeywords start!");
		if (keys != null && !"".equals(keys)) {
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("types", TYPES_KEYWORDS);
			search.put("names", keys.trim());
			search.put("site", getTopicSite());
			List l = keywordsManager.findAll(search);
			if (l.isEmpty()) {
				writeAjaxStr("yes");
			}
		}
		if (tags != null && !"".equals(tags)) {
			String s[] = tags.split(",");
			for (int i = 0; i < s.length; i++) {
				Map<String, Object> search = new HashMap<String, Object>();
				search.put("types", TYPES_TAGS);
				search.put("names", s[i].trim());
				search.put("site", getTopicSite());
				List l = keywordsManager.findAll(search);
				if (!l.isEmpty()) {
					writeAjaxStr("no");
				}
			}

		}
		if (keyss != null || !"undefined".equals(keyss[0])) {
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("types", TYPES_LABEL);
			search.put("names", keyss[0].trim());
			search.put("site", getTopicSite());
			List l = keywordsManager.findAll(search);
			if (l.isEmpty()) {
				writeAjaxStr("yes");
			}
		}
		writeAjaxStr("no");
		logger.info("Titles checkKeywords end!");
	}

	@Action(value = "deleteKey")
	public void deleteKey() {
		logger.info("Titles deleteKey start!");
		String idString = this.getRequest().getParameter("idsString");
		logger.info("Titles deleteKey ids:" + idString);
		String ids[] = idString.split(";");
		for (int i = 0; i < ids.length; i++) {
			keywordsManager.deleteById(Integer.valueOf(ids[i]));
		}
		writeAjaxStr("yes");
		logger.info("Titles deleteKey end!");
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

	public Set<String> spli2(String str, String sign) {
		String s[] = str.split(sign);
		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < s.length; i++) {
			if (!"".equals(s[i])) {
				try {
					ids.add(String.valueOf(s[i]));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		HashSet<String> hs = new HashSet<String>();
		if (!ids.isEmpty()) {
			hs.addAll(ids);
		}
		return hs;
	}

	public void upd(Integer tid, String str) {
		int keywordsId = 0;
		String site = getTopicSite();
		String username = getLoginUserName();
		/*
		 * �ؼ��ֱ���
		 */
		if (keys != null && !"".equals(keys)) {
			Keywords k = new Keywords();
			k.setSite(site);// ���ùؼ�������վ��
			k.setTypes(TYPES_KEYWORDS);
			k.setNames(keys);
			k.setCreateTime(new java.util.Date());
			k.setCreator(username);
			keywordsId = this.keywordsManager.save(k);
			KeywordsRel kr = new KeywordsRel();
			kr.setKeywords(keywordsId);
			kr.setTitleId(tid);
			keywordsRelManager.save(kr);
		}
		/*
		 * ��ǩ�������
		 */
		if (tags != null && !"".equals(tags.trim())) {
			String s[] = tags.trim().replace("��", ",").split(",");
			for (int i = 0; i < s.length; i++) {
				if (!"".equals(s[i])) {
					Map<String, Object> search = new HashMap<String, Object>();
					search.put("site", site);
					search.put("types", TYPES_TAGS);
					search.put("names", s[i]);
					List<?> l = keywordsManager.findAll(search);
					if (l.isEmpty()) {
						Keywords k = new Keywords();
						k.setSite(site);// ���ùؼ�������վ��
						k.setTypes(TYPES_TAGS);
						k.setNames(s[i]);
						k.setCreateTime(new java.util.Date());
						k.setCreator(username);
						keywordsId = this.keywordsManager.save(k);
					} else {
						Keywords k = (Keywords) l.get(0);
						keywordsId = k.getId();
					}
					KeywordsRel kr = new KeywordsRel();
					kr.setKeywords(keywordsId);
					kr.setTitleId(tid);
					keywordsRelManager.save(kr);

				}
			}
		}
		/*
		 * �����Ʒ�������
		 */
		if (gids != null && !"".equals(gids.trim())) {
			Set<String> hs = this.spli2(gids.trim().replace("��", ","), ",");
			for (String gids1 : hs) {// �ж���Ʒ�Ƿ���ڣ�
				if (null != gids1 && !"".equals(gids1)) {
					TitleGoods tg = new TitleGoods();
					tg.setGoodsId(gids1.trim());
					tg.setTitleId(tid);
					tg.setGoodsNo(gids1);

					titleGoodsManager.save(tg);
				}
			}
		}
		/*
		 * �������������
		 */
		if (keyss != null && keyssUrl != null) {
			for (int i = 0; i < keyss.length; i++) {
				if (!"".equals(keyss[i]) || !"".equals(keyssUrl[i])) {
					Keywords k = new Keywords();
					k.setSite(getTopicSite());// ���ùؼ�������վ��
					k.setTypes(TYPES_LABEL);
					k.setNames(keyss[i]);
					k.setUrl(keyssUrl[i]);
					k.setCreateTime(new java.util.Date());
					k.setCreator(username);
					keywordsId = this.keywordsManager.save(k);
					KeywordsRel kr = new KeywordsRel();
					kr.setKeywords(keywordsId);
					kr.setTitleId(tid);
					keywordsRelManager.save(kr);
				}
			}
		}
	}

	@Action(value = "save", results = { @Result(name = "success", type = "redirect", location = "/Titles/list.action"),
			@Result(name = "listConditTitles", type = "redirect", location = "/Titles/listConditTitles.action") })
	public String save() {
		logger.info("Titles save start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		String titlesId = "0";
		String site = getTopicSite();
		String username = getLoginUserName();
		String sources = getRequest().getParameter("editorValue");

		if (titles != null) {
			// ����ר������վ��
			titles.setSite(site);
			// �����Ʒ�����
			Titles t = titlesManager.getById(titles.getId());
			if (command == COMMAND_EDIT) {
				titles.setModifier(username);
				if (null != t && null != t.getPublicStat() && "Y".equals(t.getPublicStat())) {
					titles.setEditStat("Y"); // �����޸�״̬Ϊ�޸Ĺ�
				}
			} else if (command == COMMAND_CREATE) {
				titles.setCreator(username);
			}
			if (sources != null && !"".equals(sources)) {
				titles.setSources(sources);
			}
			if (titles.getId() != null) {
				Map<String, Object> search = new HashMap<String, Object>();
				search.put("titleId", titles.getId());
				search.put("site", site);
				List<KeywordsRel> lkr = keywordsRelManager.findAll(search);
				if (!lkr.isEmpty()) {
					for (KeywordsRel kr : lkr) {
						keywordsRelManager.deleteById(kr.getId());
					}
				}
				List<TitleGoods> ltg = titleGoodsManager.findAll(search);
				if (!ltg.isEmpty()) {
					for (TitleGoods tg : ltg) {
						titleGoodsManager.deleteById(tg.getId());
					}
				}

				titles.setCreateTime(t.getCreateTime());
				titles.setUpdateTime(new java.util.Date());
				titlesManager.update(titles);
				String s = "";
				if (titles.getGoodsId() != null) {
					s = titlesManager.checkItemName(titles.getGoodsId());
				}
				this.upd(titles.getId(), s);

				// ȥ��Ԥ������
			} else {
				if (titles.getPaths() == null || "".equals(titles.getPaths())) {
					StringBuilder path = new StringBuilder();
					path.append("topic/");
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String dateNowStr = sdf.format(d);
					// 20160223
					Integer maxId = titlesManager.getMaxId(site) + 1;
					path.append(dateNowStr).append("/" + maxId);
					titles.setPaths(path.toString());
				}
				titles.setCreateTime(new java.util.Date());
				titles.setCheckStat("N");
				titles.setPublicStat("N");
				titles.setEditStat("N");
				if (titles.getGoodsId() != null && !"".equals(titles.getGoodsId())) {
					String s = titlesManager.checkItemName(titles.getGoodsId());
					if (s != null && !"".equals(s)) {
						titlesId = titlesManager.save(titles);
						this.upd(Integer.parseInt(titlesId), s);

					}
				}
			}
			if (forwards == 1 && !"".equals(String.valueOf(titles.getId()))){
				GoodsDrops drops = new GoodsDrops();
				drops.setTitleId(titles.getId());
				drops.setGoodsId(t.getGoodsId());
				if (!(titles.getGoodsId()).equals(t.getGoodsId())) {
					titlesManager.deleteDropsByObj(drops);
				}
			}
		}
		logger.info("Titles save forwards:" + forwards);
		if (forwards == 1) {
			return "listConditTitles";
		}
		logger.info("Titles save end!");
		return "success";
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/title/create.jsp") })
	public String edit() {
		logger.info("Titles edit start!");
		command = COMMAND_EDIT; // �����ļ����淽ʽ
		logger.info("Titles edit command:" + command);
		if (titles.getId() != null) {
			this.titles = titlesManager.getById(titles.getId());
			// �ؼ���,��ǩ
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("titleId", titles.getId());
			List<KeywordsRel> lkr = keywordsRelManager.findAll(search);
			List<Keywords> lk = new ArrayList<Keywords>();
			if (null != lkr && !lkr.isEmpty()) {
				for (KeywordsRel kr : lkr) {
					lk.add(keywordsManager.getById(kr.getKeywords()));
				}
				if (!lk.isEmpty()) {
					for (int i = 0; i < lk.size(); i++) {
						Keywords k = lk.get(i);
						if (k != null) {
							if (k.getTypes().equals(TYPES_KEYWORDS)) {
								this.keys = k.getNames();
							}
							if (k.getTypes().equals(TYPES_TAGS)) {
								this.tags = this.tags + k.getNames() + ",";
							}
							if (k.getTypes().equals(TYPES_LABEL)) {
								urlMapKey.add(k);
								urlMap.put(k.getNames(), k.getUrl());
							}
						}
					}
				}
			}
			// ������ƷID
			List<TitleGoods> ltg = titleGoodsManager.findAll(search);
			if (!ltg.isEmpty()) {
				for (TitleGoods tg : ltg) {
					this.gids = this.gids + tg.getGoodsId() + ",";
				}
			}
		}
		logger.info("Titles edit end!");
		return "success";
	}

	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/Titles/list.action") })
	public String delete() {
		logger.info("Titles delete start!");
		if (tags != null && !"".equals(tags)) {
			Set<Integer> hs = this.spli(tags, ";");
			for (Integer tid : hs) {
				Map<String, Object> search = new HashMap<String, Object>();
				search.put("titleId", tid);
				List<KeywordsRel> lkr = keywordsRelManager.findAll(search);
				if (!lkr.isEmpty()) {
					for (KeywordsRel kr : lkr) {
						keywordsRelManager.deleteById(kr.getId());
					}
				}
				List<TitleGoods> ltg = titleGoodsManager.findAll(search);
				if (!ltg.isEmpty()) {
					for (TitleGoods tg : ltg) {
						titleGoodsManager.deleteById(tg.getId());
					}
				}
				logger.info("Titles delete ɾ������id��" + id);
				titlesManager.deleteById(tid);
			}
		}
		logger.info("Titles delete end!");
		return "success";
	}

	@Action(value = "deletePublish", results = {
			@Result(name = "success", type = "redirect", location = "/Titles/list.action") })
	public String deletePublish() {
		logger.info("Titles deletePublish start!");
		titlesManager.deletePublish();
		logger.info("Titles deletePublish end!");
		return "success";
	}

	@Action(value = "changePath", results = {
			@Result(name = "success", type = "redirect", location = "/Titles/list.action") })
	public String changePath() {
		logger.info("Titles changePath start!");

		titlesManager.changePath();
		logger.info("Titles changePath end!");
		return "success";
	}

	/**
	 * ר�ⷢ��
	 */
	@Action(value = "publicTitle", results = { @Result(name = "success", type = "redirect", location = "/Titles/list.action") })
	public String publicTitle() {
		logger.info("Titles publicTitle start!");
		String site = getTopicSite();
		// �ж�tags��if����Ϊ�û�ѡ��һ�����߶���ר������
		String titleIds = this.getRequest().getParameter("tags");
		if (titleIds != null && !"".equals(titleIds)) {
			logger.info("����ר�ⷢ����ʼ");
			Set<Integer> hs = this.spli(titleIds, ";");
			for (Integer tid : hs) {
                Titles t = new Titles();
                t = this.titlesManager.getById(tid);

                logger.info("����������ר������Ϊ��"+t);

				logger.info("����ר���е�һ��ר��id��"+tid);
                TitleIndex titleIndexByTitleId = titlesManager.getTitleIndexByTitleId(tid);
                if (titleIndexByTitleId != null){
                    titleIndexByTitleId.setStatus(1);
                    titlesManager.updateTitleIndex(titleIndexByTitleId);
				}else {
                    String s = titlesManager.insertTitleIndex(t);
                    logger.info("����titleIndex�Ľ����"+s);
                }

				t.setPublicStat("Y");
				t.setCheckStat("Y");
				t.setEditStat("Y");
				t.setIndexStat("Y");
				if (t.getPaths() == null || "".equals(t.getPaths())) {
					StringBuilder path = new StringBuilder();
					path.append("topic/").append(t.getId());
					t.setPaths(path.toString());
				}
				t.setCreateTime(t.getCreateTime());
				t.setUpdateTime(new java.util.Date());
				titlesManager.update(t);
			}
		}
		// �ж�titles��if����Ϊ�û��Ե�����Ʒר����淢����ť�Ĳ������
		if (titles != null) {
			if (titles.getId() != null) {
				if (titlesManager.getTitleIndexByTitleId(titles.getId())==null){
					logger.info("����ר�ⷢ����ʼ������������ר��id"+titles.getId());
					Titles t = new Titles();
					t = this.titlesManager.getById(titles.getId());
                    TitleIndex titleIndexByTitleId = titlesManager.getTitleIndexByTitleId(titles.getId());
                    if (titleIndexByTitleId != null){
                        titleIndexByTitleId.setStatus(1);
                        titlesManager.updateTitleIndex(titleIndexByTitleId);
                    }else {
                        String s = titlesManager.insertTitleIndex(t);
                        logger.info("����titleIndex�Ľ����"+s);
                    }
					t.setPublicStat("Y");
					t.setCheckStat("Y");
					t.setEditStat("Y");
					t.setIndexStat("Y");
					if (t.getPaths() == null && "".equals(t.getPaths())) {
						StringBuilder path = new StringBuilder();
						path.append("topic/").append(t.getId());
						t.setPaths(path.toString());
					}
					t.setCreateTime(t.getCreateTime());
					t.setUpdateTime(new java.util.Date());
					titlesManager.update(t);
				}
			}
		}
		logger.info("Titles publicTitle end!");
		return "success";
	}

	/**
	 * ����ר�ⷢ��
	 */
	@Action(value = "publicWholeTitle")
	public void publicWholeTitle() {
		logger.info("Titles publicWholeTitle start!");
		String site = getTopicSite();
		int i = 9;
		// �õ����е�ר��
		List<Titles> titleList = new ArrayList<Titles>();
		titleList = this.titlesManager.findAllTitlesList();
		// ѭ������ÿ��ר��
		for (Titles t : titleList) {
			Titles ti = new Titles();
			// �õ�����ר��
			ti = this.titlesManager.getById(t.getId());
			// ��������
			ti.setPublicStat("Y");
			ti.setCheckStat("Y");
			ti.setEditStat("N");
			if (ti.getPaths() != null && !"".equals(ti.getPaths())) {
			} else {
				ti.setPaths(ti.getId().toString());
			}
			// �޸�
			this.titlesManager.update(ti);
			// ����ר��
			i = titlesManager.publishTitle(ti.getId(), site);
			titlesManager.publishWapTitle(titles.getId()); //����Wap��ר�����·���
			// ��������״̬Ϊ����״̬
			updateNewsStatusByTitle(ti.getId());
		}
		writeAjaxStr(Integer.toString(i));
		logger.info("Titles publicWholeTitle end!");
	}

	/**
	 * ����ר����������µ�״̬
	 * 
	 * @param titleId
	 */
	private void updateNewsStatusByTitle(Integer titleId) {
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("titleId", titleId);
		List<News> newsList = newsManager.findListByMap(search);
		if (!newsList.isEmpty()) {
			for (News news : newsList) {
				news.setStates("Y"); // ����״̬Ϊ����״̬				
				newsManager.update(news);
				List<com.coo8.topic.labelart.modal.Label> labelList = labelManager.getLabelbyNewsId(news.getId());
				if(!labelList.isEmpty()){
					for(com.coo8.topic.labelart.modal.Label label : labelList){
						labelManager.publicLabelPage(label.getId().toString());
						label.setStates("Y");
						labelManager.update(label);
					}
				}
			}
		}
	}

	/**
	 * ����ר����ҳ
	 */
	@Action(value = "publishTitleHomePage")
	public void publicTitleHomePage() {
		logger.info("Titles publishTitleHomePage start!");
		String site = getTopicSite();
		int i = 9;
		i = titlesManager.publicTitleHomePage(site);
		writeAjaxStr(Integer.toString(i));
		logger.info("Titles publishTitleHomePage end!");
	}
	
	/**
	 * ����Wapר����ҳ
	 */
	@Action(value = "publishWapTitleHomePage")
	public void publicWapTitleHomePage() {
		logger.info("Titles publishWapTitleHomePage start!");
		int i = 9;
		i = titlesManager.publicWapTitleHomePage();
		writeAjaxStr(i + "");
		logger.info("Titles publishWapTitleHomePage end!");
	}

	/**
	 * ��������ר���б�ҳ--��ҳ
	 */
	@Action(value = "publishAllTitleListPage")
	public void publishAllTitleListPage() {
		logger.info("Titles publishAllTitleListPage start!");
		String site = getTopicSite();
		int i = 9;
		i = titlesManager.publishAllTitleListPage(site);
		writeAjaxStr(Integer.toString(i));
		logger.info("Titles publishAllTitleListPage end!");
	}

	/**
	 * ������������ҳ--��ҳ
	 */
	@Action(value = "publishAllNewsListPage")
	public void publicAllTitleListPage() {
		logger.info("Titles publishAllNewsListPage start!");
		String site = getTopicSite();
		int i = 9;
		i = titlesManager.publishAllNewsListPage(site);
		writeAjaxStr(Integer.toString(i));
		logger.info("Titles publishAllNewsListPage end!");
	}

	@Action(value = "toPartPublish" ,results = { @Result(name = "success", type = "redirect", location = "/Titles/list.action") })
	public String toPartPublish() {
		logger.info("Titles toPartPublish start!");
		int i = 9;
		String idBegin = this.getRequest().getParameter("idBegin");
		String idEnd = this.getRequest().getParameter("idEnd");
		logger.info("Titles toPartPublish idBegin:" + idBegin + ",idEnd" + idEnd);
		Integer start = 0;
		Integer end = 0;
		if (null != idBegin && !"".equals(idBegin)) {
			start = Integer.valueOf(idBegin);
		}
		if (null != idEnd && !"".equals(idEnd)) {
			end = Integer.valueOf(idEnd);
		}
//		String site = getTopicSite();
		for (int j = start; j < end + 1; j++) {
			Titles t = new Titles();
			t = this.titlesManager.getById(j);
			if (t != null) {
				if (titlesManager.getTitleIndexByTitleId(t.getId())!=null){
					continue;
				}
				String s = titlesManager.insertTitleIndex(t);
				logger.info("����titleIndex�Ľ���ǣ�"+s);
				t.setPublicStat("Y");
				t.setCheckStat("Y");
				t.setEditStat("Y");
				t.setIndexStat("Y");
				if (t.getPaths() != null && !"".equals(t.getPaths())) {
				} else {
					StringBuilder path = new StringBuilder();
					path.append("topic/").append(t.getId());
					t.setPaths(path.toString());
				}
				t.setCreateTime(t.getCreateTime());
				t.setUpdateTime(new java.util.Date());
				logger.info("�������µ�ר�⣺"+t);
				this.titlesManager.update(t);
			}
		}
		logger.info("Titles toPartPublish end!");
		return "success";
	}

	@Action(value = "toExcel")
	public void toExcel() {
		logger.info("Titles toExcel start!");
		List<Titles> list = new ArrayList<Titles>();
		Map<String, Object> search = new HashMap<String, Object>();

		String idBegin = this.getRequest().getParameter("idBegin");
		String idEnd = this.getRequest().getParameter("idEnd");
		if (null != idBegin && !"".equals(idBegin)) {
			search.put("idBegin", Integer.valueOf(idBegin));
		}
		if (null != idEnd && !"".equals(idEnd)) {
			search.put("idEnd", Integer.valueOf(idEnd));
		}
		String site = getTopicSite();
		search.put("site", site);
		search.put("sortColumns", "id desc");
		list = titlesManager.findListLikeByMap(search);
		Map<String, Object> searchKey = new HashMap<String, Object>();
		searchKey.put("types", "2");
		List<Keywords> keyList = keywordsManager.findAll(searchKey);
		HashMap mapkey = new HashMap();
		if (!keyList.isEmpty()) {
			for (Keywords k : keyList) {
				mapkey.put(k.getId(), k);
			}
		}
		Map<String, Object> search2 = new HashMap<String, Object>();
		List<KeywordsRel> lkr = keywordsRelManager.findAll(search2);
		HashMap mapkeyResult = new HashMap();
		if (!lkr.isEmpty()) {
			for (KeywordsRel kr : lkr) {
				if (mapkey.get(kr.getKeywords()) != null) {
					Keywords k = (Keywords) mapkey.get(kr.getKeywords());
					if (mapkeyResult.get(kr.getTitleId()) != null) {
						mapkeyResult.put(kr.getTitleId(), mapkeyResult.get(kr.getTitleId()) + "," + k.getNames());
					} else {
						mapkeyResult.put(kr.getTitleId(), k.getNames());
					}
				}
			}
		}
		// ��ʾ��ǩ
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				Titles t = (Titles) o;
				if (mapkeyResult.get(t.getId()) != null) {
					list.get(i).setTags(mapkeyResult.get(t.getId()).toString());
				}
			}
		}

		String[] titlename = { "���", "ר������", "��ַ", "��ǩ", "���/�޸�ʱ��", "����/�޸���", "����/���", "��������", "վ��" };

		WritableWorkbook workbook = null;
		OutputStream os = null;
		// ��ȡ�����
		try {
			// ��������excel�ļ������ͺ�����
			String fileName = "��Ʒר���б�";
			fileName = new String(fileName.getBytes("gbk"), "ISO-8859-1");
			HttpServletResponse response = getResponse();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
			os = response.getOutputStream();

			workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("��Ʒר���б�", 0);

			sheet.setPageSetup(PageOrientation.LANDSCAPE);

			sheet.setColumnView(0, 10);// ��һ�У����
			sheet.setColumnView(1, 40);
			sheet.setColumnView(2, 50);
			sheet.setColumnView(3, 30);
			sheet.setColumnView(4, 30);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 12);
			sheet.setColumnView(7, 12);
			// �����и�
			sheet.setRowView(0, 600, false);

			// ��������
			WritableFont titleFont = new WritableFont(WritableFont.createFont("����"), 12, WritableFont.BOLD);
			titleFont.setColour(jxl.format.Colour.RED);
			WritableFont normalFont = new WritableFont(WritableFont.createFont("����"), 11);

			// ���ڱ���
			WritableCellFormat wcf_title = new WritableCellFormat(titleFont);
			wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN); // ����
			wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE); // ��ֱ����
			wcf_title.setAlignment(Alignment.CENTRE); // ˮƽ����
			wcf_title.setWrap(true); // �Ƿ���
			wcf_title.setBackground(jxl.format.Colour.YELLOW);// ���ñ�����ɫ

			// ������������
			WritableCellFormat wcf_main = new WritableCellFormat(normalFont);
			wcf_main.setBorder(Border.ALL, BorderLineStyle.THIN); // ����
			wcf_main.setVerticalAlignment(VerticalAlignment.CENTRE); // ��ֱ����
			wcf_main.setAlignment(Alignment.CENTRE); // ˮƽ����
			wcf_main.setWrap(true); // �Ƿ���

			for (int i = 0; i < titlename.length; i++) {
				jxl.write.Label labelCFC = new jxl.write.Label(i, 0, titlename[i], wcf_title);
				sheet.addCell(labelCFC);
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for (int i = 0; i < list.size(); i++) {
				Titles title = (Titles) list.get(i);
				int j = 0;              
				Label label = null;

				label = new jxl.write.Label(j++, i + 1, String.valueOf(title.getId()), wcf_main);
				sheet.addCell(label);

				label = new jxl.write.Label(j++, i + 1, title.getTitle(), wcf_main);
				sheet.addCell(label);
				String url = "";
				String headUrl = "";
				String siteName = "����";
				if (site.equals(ConstantUtil.SITE_FLAG_COO8)) {
					headUrl = ConstantUtil.DOMAIN_COO8_BASEURL + ConstantUtil.DOMAIN_COO8_TITLE_BASEURL + "/";
					siteName = "���";
				} else {
					headUrl = ConstantUtil.DOMAIN_GOME_BASEURL  + "/";
				}
				if (null != title.getPaths() && !"".equals(title.getPaths())) {
					url = headUrl + title.getPaths() + "/";
				} else {
					url = headUrl + title.getId() + "/";
				}
				label = new jxl.write.Label(j++, i + 1, url, wcf_main);
				sheet.addCell(label);

				label = new jxl.write.Label(j++, i + 1, title.getTags(), wcf_main);
				sheet.addCell(label);
				String createtime = "";
				String updatetime = "";
				if (title.getCreateTime() != null) {
					createtime = dateFormat.format(title.getCreateTime());
				}
				if (title.getUpdateTime() != null) {
					updatetime = dateFormat.format(title.getUpdateTime());
				}
				label = new jxl.write.Label(j++, i + 1, createtime + "/" + updatetime, wcf_main);
				sheet.addCell(label);
				String creator = (title.getCreator() != null && !"null".equals(title.getCreator())) ? title.getCreator()
						: "";
				String modifier = (title.getModifier() != null && !"null".equals(title.getModifier()))
						? title.getModifier() : "";
				label = new jxl.write.Label(j++, i + 1, creator + "/" + modifier, wcf_main);
				sheet.addCell(label);
				if (null != title.getPublicStat() && "Y".equals(title.getPublicStat())) {
					label = new jxl.write.Label(j++, i + 1, "��", wcf_main);
				} else {
					label = new jxl.write.Label(j++, i + 1, "��", wcf_main);
				}
				sheet.addCell(label);
				label = new jxl.write.Label(j++, i + 1, String.valueOf(title.getNewscount()), wcf_main);
				sheet.addCell(label);
				label = new jxl.write.Label(j++, i + 1, String.valueOf(siteName), wcf_main);
				sheet.addCell(label);
			}
			workbook.write();
			response.flushBuffer();
			workbook.close();
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			logger.error("Titles toExcel FileNotFoundException�쳣��" + e.getMessage(),e);
		} catch (IOException e) {
			logger.error("Titles toExcel IOException�쳣��" + e.getMessage(),e);
		} catch (RowsExceededException e) {
			logger.error("Titles toExcel RowsExceededException�쳣��" + e.getMessage(),e);
		} catch (WriteException e) {
			logger.error("Titles toExcel WriteException�쳣��" + e.getMessage(),e);
		} finally {
			if (null != workbook) {
				try {
					workbook.close();
				} catch (WriteException e) {
					logger.error("Titles toExcel WriteException�쳣��" + e.getMessage(),e);
				} catch (IOException e) {
					logger.error("Titles toExcel IOException�쳣��" + e.getMessage(),e);
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("Titles toExcel IOException�쳣��" + e.getMessage(),e);
				}
			}
		}
		logger.info("Titles toExcel end!");
	}

	/**
	 * r * �����¼���Ʒ�б�
	 */
	@Action(value = "createTitlesList", results = {
			@Result(name = "success", type = "redirect", location = "/Titles/listConditTitles.action") })
	public String createTitlesList() {
		logger.info("Titles createTitlesList start!");

		List<Integer> goodsIdList = null;
		List<Titles> titlesList = null;
		int status = 4;// ��ѯ�¼ܵ���ƷID�б�
		goodsIdList = titlesManager.findGoodsListByInt(status); // ����¼���Ʒ
		titlesList = titlesManager.findAllTitlesList(); // �������ר�⣬ֻ����titleId ,
		/*
		 * ���¼ܵ�ר��������
		 */
		titlesManager.deleteAllDrops(); // ��ձ����� t_goods_drops
		if (!goodsIdList.isEmpty() && !titlesList.isEmpty()) {
			List<GoodsDrops> dropsList = new ArrayList<GoodsDrops>();
			GoodsDrops drops = null;
			for (Titles titles : titlesList) {
				if (goodsIdList.contains(Integer.valueOf(titles.getGoodsId()))) {
					drops = new GoodsDrops(titles.getId(), titles.getGoodsId(), "Y");
					dropsList.add(drops);
				}
			}
			if (!dropsList.isEmpty()) {
				titlesManager.saveDrops(dropsList);
			}
		}
		logger.info("Titles createTitlesList end!");
		return SUCCESS;
	}

	public String checkitem(String id) {
		return titlesManager.checkItemByATG(id);
	}

	public IItemsDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(IItemsDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String[] getKeyss() {
		return keyss;
	}

	public void setKeyss(String[] keyss) {
		this.keyss = keyss;
	}

	public String getGids() {
		return gids;
	}

	public void setGids(String gids) {
		this.gids = gids;
	}

	public IKeywordsManager getKeywordsManager() {
		return keywordsManager;
	}

	public void setKeywordsManager(IKeywordsManager keywordsManager) {
		this.keywordsManager = keywordsManager;
	}

	public IKeywordsRelManager getKeywordsRelManager() {
		return keywordsRelManager;
	}

	public void setKeywordsRelManager(IKeywordsRelManager keywordsRelManager) {
		this.keywordsRelManager = keywordsRelManager;
	}

	public ITitleGoodsManager getTitleGoodsManager() {
		return titleGoodsManager;
	}

	public void setTitleGoodsManager(ITitleGoodsManager titleGoodsManager) {
		this.titleGoodsManager = titleGoodsManager;
	}

	public String[] getKeyssUrl() {
		return keyssUrl;
	}

	public void setKeyssUrl(String[] keyssUrl) {
		this.keyssUrl = keyssUrl;
	}

	public String getErrMsg2() {
		return errMsg2;
	}

	public void setErrMsg2(String errMsg2) {
		this.errMsg2 = errMsg2;
	}

	public List<Keywords> getUrlMapKey() {
		return urlMapKey;
	}

	public void setUrlMapKey(List<Keywords> urlMapKey) {
		this.urlMapKey = urlMapKey;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public int getForwards() {
		return forwards;
	}

	public void setForwards(int forwards) {
		this.forwards = forwards;
	}

	public IAllHotKeywordManager getAllHotKeywordManager() {
		return allHotKeywordManager;
	}

	public void setAllHotKeywordManager(IAllHotKeywordManager allHotKeywordManager) {
		this.allHotKeywordManager = allHotKeywordManager;
	}

	/**
	 * ר��վ��ѡ��
	 * 
	 * @return
	 */
	@Action(value = "chooseTopics", results = { @Result(name = "success", location = "/jsp/admin/common/framework.jsp"),
			@Result(name = "error", location = "/jsp/index/chooseTopicSiteError.jsp") })
	public String chooseTopics() {
		String select = this.getRequest().getParameter("select");
		if (null != select) {
			if (select.equals(ConstantUtil.SITE_FLAG_GOME)) {
				setGomeTopicSite();
				return "success";
			} else if (select.equals(ConstantUtil.SITE_FLAG_COO8)) {
				setCoo8TopicSite();
				return "success";
			}
		}
		return "error";
	}

	/**
	 * ר�������б�ҳ
	 * 
	 * @return
	 */
	@Action(value = "listTitleIndex", results = {
			@Result(name = "success", location = "/jsp/index/listTitleIndex.jsp") })
	public String listTitleIndex() {

		Map<String, Object> search = new HashMap<String, Object>();
		if (null != titleIndex) {
			String cindex = titleIndex.getCindex();
			Integer titleId = titleIndex.getTitleId();
			logger.info("����ʱ���Ĳ���:"+titleIndex);
			if (null != cindex && !"".equals(cindex)) {
				search.put("cindex", cindex);
			}
			if (null != titleId && !"".equals(titleId.toString())) {
				search.put("titleId", titleId);
			}
		}
//		search.put("source", 0); // ֻ��ѯ�ֶ����
		search.put("site", getTopicSite());
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		search.put("sortColumns", "UPDATETIME DESC");

		titleIndexList = titlesManager.findTitleIndexByMap(search);
		return SUCCESS;
	}

	/**
	 * ר�������޸�
	 */
	@Action(value = "titleIndexEdit", results = { @Result(name = "success", location = "/jsp/index/update.jsp") })
	public String titleIndexEdit() {
		int id = Integer.parseInt((getRequest().getParameter("id")));
		titleIndex = titlesManager.getTitleIndexById(id);
		return "success";
	}

	@Action(value = "titleIndexUpdate", results = {
			@Result(name = "success", type = "redirect", location = "/Titles/listTitleIndex.action") })
	public String titleIndexUpdate() {
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		// ��ǰ�û��޸�Ϊ������
		String currentUserName = getLoginUserName();// (String)
		titleIndex.setOperator(currentUserName);

		titlesManager.updateTitleIndex(titleIndex);
		return "success";
	}

	/**
	 * ר������ɾ��
	 */
	@Action(value = "titleIndexDelete", results = {
			@Result(name = "success", type = "redirect", location = "/Titles/listTitleIndex.action") })
	public String titleIndexDelete() {
		String paramIds = getRequest().getParameter("paramIds");
		String[] ids = paramIds.split(";");
		for (int i = 0; i < ids.length; i++) {
			titlesManager.deleteTitleIndex(Integer.valueOf(ids[i]));
		}
		return "success";
	}

	/**
	 * ר���������
	 */
	@Action(value = "titleIndexAdd", results = {
			@Result(name = "success", type = "redirect", location = "/Titles/listTitleIndex.action") })
	public String titleIndexAdd() {
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		String pinyin = Chinese2PinyinUtil.parseChinese(titleIndex.getTitle());
		String operator = getLoginUserName();// (String)

		titleIndex.setPinyin(pinyin);
		titleIndex.setSource(0);
		titleIndex.setOperator(operator);
		titleIndex.setStatus(1);
		titleIndex.setSite(getTopicSite());
		titlesManager.insertTitleIndex(titleIndex);

		Titles updateIndexStateTitles = titlesManager.getById(titleIndex.getTitleId());
		updateIndexStateTitles.setIndexStat("Y");
		titlesManager.update(updateIndexStateTitles);

		return "success";
	}

	@Action(value = "getTitleName")
	public void getTitleName() {
		int titleId = Integer.parseInt(getRequest().getParameter("titleId"));

		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", titleId);
		queryMap.put("site", getTopicSite());

		List<Titles> isTitleIdExist = titlesManager.findListByMap(queryMap);

		if (isTitleIdExist.isEmpty()) {
			writeAjaxStr("ERROR��ר���Ų����ڣ���ȷ�Ϻ�������");
			return;
		}

		// ��t_title_index��ר����ΪtitleId������t_titles��ר����ΪId
		queryMap.remove("id");
		queryMap.put("titleId", titleId);
		queryMap.put("source", 0); // ֻ�����ֶ����

		int selectCount = titlesManager.isAddRepeat(queryMap);
		if (selectCount != 0) {
			writeAjaxStr("ERROR����ר���Ѿ���ӹ������ˣ������ظ����");
		} else {
			String titleName = titlesManager.getById(titleId).getTitle();
			writeAjaxStr(titleName);
		}
	}

	public String getTitleBaseUrl() {
		return titleBaseUrl;
	}

	public void setTitleBaseUrl(String titleBaseUrl) {
		this.titleBaseUrl = titleBaseUrl;
	}

	public String getTitleTestBaseUrl() {
		return titleTestBaseUrl;
	}

	public void setTitleTestBaseUrl(String titleTestBaseUrl) {
		this.titleTestBaseUrl = titleTestBaseUrl;
	}

	public INewsManager getNewsManager() {
		return newsManager;
	}

	public void setNewsManager(INewsManager newsManager) {
		this.newsManager = newsManager;
	}
	
	
	public LabelManager getLabelManager() {
		return labelManager;
	}

	public void setLabelManager(LabelManager labelManager) {
		this.labelManager = labelManager;
	}

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

	
	
	
	
	/**
	 * @desc ��ȡСд��Ӣ��ƴ��
	 * @return
	 */
	private String getPinyinLowercase(String chineseStr) {
		String result = Chinese2PinyinUtil.parseChinese(chineseStr);
		return result.toLowerCase();
	}
	
	@Action(value = "log", results = { @Result(name = "success", location = "/jsp/title/importFile.jsp") })
	public String logList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", page_index);
		paramMap.put("pageSize", page_size);
		listImportLogs = titlesManager.listLog(paramMap);

		return SUCCESS;
	}
	
	/**
	 * @param
	 * @desc ����δ�ɹ�������Ʒר���excel�ļ�
	 */
	@Action(value = "errorwords")
	public void generateFailDataFile() {
		String createTime = getStringParam("date");
		String logid = getStringParam("logid");
		String uploader = getStringParam("uploader");
		uploader = StringUtil.urlDecode(uploader, "utf-8");
		uploader = StringUtil.urlDecode(uploader, "utf-8");
		uploader = getPinyinLowercase(uploader);
		String uploadTime = createTime.replaceAll(":", "-");
		uploadTime = uploadTime.replaceAll(" ", "_");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = formatter.parse(createTime);
			createTime = formatter.format(date);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("createTime", createTime);
		paramMap.put("logid", logid);
		List<ErrorTitles> errorTitless = titlesManager.listDownLog(paramMap);
		if (errorTitless != null) {
			try {
				String fileName = uploader + uploadTime + ".xls";
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName);
				OutputStream os = getResponse().getOutputStream();
				WritableWorkbook workBook = Workbook.createWorkbook(os);
				WritableSheet sheet = workBook.createSheet("sheet1", 0);
				Label one = new Label(0, 0, "��ƷID");
				Label two = new Label(1, 0, "skuId");
				Label three = new Label(2, 0, "����");
				Label four = new Label(3, 0, "�ؼ���");
				Label five = new Label(4, 0, "��ǩ");
				Label six = new Label(5, 0, "������Ʒ");
				Label seven = new Label(6, 0, "����ԭ��");	
				sheet.addCell(one);
				sheet.addCell(two);
				sheet.addCell(three);
				sheet.addCell(four);
				sheet.addCell(five);
				sheet.addCell(six);
				sheet.addCell(seven);
				for (int i = 0; i < errorTitless.size(); i++) {
					ErrorTitles errorTitles = errorTitless.get(i);
					WritableCellFormat errorFormat = new WritableCellFormat();
					errorFormat.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.RED);
					errorFormat.setBackground(jxl.format.Colour.GRAY_25);
					Integer type = errorTitles.getType();
					type = type == null ? 0 : type;					
					Label goodsId = new Label(0, i + 1, errorTitles.getGoodsId());
					Label skuId = new Label(1, i + 1, errorTitles.getSkuId());
					Label title = new Label(2, i + 1, errorTitles.getTitle());
					Label keys1 = new Label(3, i + 1, errorTitles.getKeys());
					Label tags1 = new Label(4, i + 1, errorTitles.getTags());
					Label gids1 = new Label(5, i + 1, errorTitles.getGids());
					Label errormeg = new Label(6, i + 1, "ר������Ч����Ʒ��С��2��");
				   if (type == 2) {
					   errormeg = new Label(6, i + 1, "ר�����ظ�");
					} 
					sheet.addCell(goodsId);
					sheet.addCell(skuId);
					sheet.addCell(title);
					sheet.addCell(keys1);
					sheet.addCell(tags1);
					sheet.addCell(gids1);
					sheet.addCell(errormeg);
				}
				workBook.write();
				getResponse().flushBuffer();
				workBook.close();
				os.flush();
				os.close();
			} catch (IOException|RowsExceededException  e) {
				logger.error(e.getMessage(), e);
			} catch (WriteException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @desc ������Ʒר�����
	 * @return
	 * @throws ParseException
	 */
	@Action(value = "import")
	public void importTitles() {
		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
		String createTime = String.format("%tF %<tT", System.currentTimeMillis());

        if(StringUtils.isBlank(fileType)) {
            fileType = "";
        }
		String lowercaseName = getPinyinLowercase(getLoginUserName());
		lowercaseName = "test";
		String uploadTime = createTime.replaceAll(":", "-");
		uploadTime = uploadTime.replaceAll(" ", "_");
		String filePath = configration + uploadTime + "_" + lowercaseName + fileType;
		File destFile = new File(filePath);      
		try {
		    logger.info("ר��Excel����ʼ");
			FileUtils.copyFile(words, destFile);
			List<Titles> titles = new ArrayList<Titles>();
			List<ErrorTitles> errorTitles = new ArrayList<ErrorTitles>();
			ImportLog importLog = new ImportLog();
			try {
				List<String[]> readExcel = ExcelPoiUtils.readExcel(destFile);
				for (int i = 0; i < readExcel.size(); i++) {
					String[] strs = readExcel.get(i);
					//�ϴ���ר����
					String titleName = strs[0].replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
					logger.info("ר����Ϊ��"+titleName);
					String skuIdString = null;
					String productIdString = null;
					String productNameString = null;
					List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();
					int type = 0;
					List<Titles> titlesList = new ArrayList<>();
					titlesList = titlesManager.getByTitleName(titleName);
					if (!titlesList.isEmpty()){
						//ר�����ظ�
						type = 2;
						logger.info("ר�����ظ�");
					} else{
						dataList = titlesManager.checkIsInvalid(titleName);
						if (dataList==null || dataList.isEmpty()){
							//ר������Ч
							type = 1;
							logger.info("ר������Ч");
						}
					}
					//��ȡpath
					String site = getTopicSite();

					if (type != 0) {
						ErrorTitles errorTitle = new ErrorTitles();
						errorTitle.setTitle(titleName);
						errorTitle.setCreateDate(new java.util.Date());
						errorTitle.setCreateUser(getLoginUserName());
						errorTitle.setType(type);
						errorTitles.add(errorTitle);
					} else {
						skuIdString = (String) dataList.get(0).get("skuId");
						productIdString = (String) dataList.get(0).get("productId");
						productNameString = (String) dataList.get(0).get("productName");
						dataList.remove(0);
						Titles titless = new Titles();
						titless.setTitle(titleName);
						titless.setSite(site);
						titless.setCreator(getLoginUserName());
						titless.setCreateTime(new java.util.Date());
						titless.setCheckStat("N");
						titless.setPublicStat("N");
						titless.setEditStat("N");	
						titless.setSources("<p><br /></p>");
						titless.setSkuId(skuIdString);
						titless.setGoodsId(productIdString);
						titless.setGoodsName(productNameString);
						titlesManager.save(titless);
						titles.add(titless);
						if (skuIdString != null && !"".equals(skuIdString.trim())) {
							for (Map<String, Object> objectMap : dataList) {
								TitleGoods tg = new TitleGoods();
								tg.setGoodsId(((String) objectMap.get("productId")).trim());
								tg.setTitleId(titlesManager.getMaxId(SITE));
								tg.setGoodsNo(((String) objectMap.get("skuId")).trim());
								titleGoodsManager.save(tg);
							}
						}
					}

				}
				if (!errorTitles.isEmpty()) {
					titlesManager.addErrorWords(errorTitles); // ���ʧ��ר��
				}
				importLog.setCreator(getLoginUserName());
				importLog.setTotalCount(titles.size() + errorTitles.size());
				importLog.setFailCount(errorTitles.size());
				importLog.setCreateTime(createTime);
				importLog.setFileUrl("./errorwords.action?date=" + createTime);

				titlesManager.addLog(importLog); // ������־����
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			logger.info("ȫ����Ʒר�����ϴ�����");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			String s = JSON.toJSONString("������ɣ���ˢ��ҳ��鿴�ϴ����");
			PrintWriter writer = response.getWriter();
			writer.println(s);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}


	public List<TitleInvalidData> titleInvalidPaginatedList = new ArrayList<TitleInvalidData>();

	@Action(value = "listTitleInvalid", results = { @Result(name = "success", location = "/jsp/title/titleInvalid.jsp") })
	public String listTitleInvalid(){
		logger.info("TitleInvalid list start!");

		List<String> createTimes = titlesManager.getTitleInvalidDate();

		for (String createTime : createTimes) {
			TitleInvalidData titleInvalidData = new TitleInvalidData();
			int titleInvalidInDateCount = titlesManager.getTitleInvalidInDateCount(createTime);
			logger.info("ʱ���"+createTime+"����Чר����Ϊ��"+titleInvalidInDateCount);
			titleInvalidData.setCount(titleInvalidInDateCount);
			titleInvalidData.setCreateTime(createTime);
			titleInvalidPaginatedList.add(titleInvalidData);
		}
		return "success";

	}

	@Action(value = "downloadTitleInvalid")
	public void downloadTitleInvalid() {
		String createTime = getStringParam("createTime");
		List<TitleInvalid> titleInvalids = titlesManager.getTitleInvalidInDate(createTime);
		if (titleInvalids != null) {
			try {
				String fileName = "TitleInvalid"
						+ String.format("%tY-%<tm-%<td_%<tH_%<tM_%<tS", System.currentTimeMillis()) + ".xls";
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName);
				OutputStream os = getResponse().getOutputStream();

				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet("ʧ����Ʒר����Ϣ");
				HSSFRow row = sheet.createRow(0);
				HSSFCellStyle style = wb.createCellStyle();
				style.setFillForegroundColor(HSSFColor.RED.index);
				HSSFFont font = wb.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				//��������
				font.setFontName("����");
				style.setFont(font);
				row.createCell(0).setCellValue("ID");
				row.createCell(1).setCellValue("ר��URL");
				row.createCell(2).setCellValue("ר��ID");
				row.createCell(3).setCellValue("ר����");
				row.createCell(4).setCellValue("���ʱ��");
				row.createCell(5).setCellValue("ʧ��ԭ��");
				for (int i = 0; i < titleInvalids.size(); i++) {
					TitleInvalid titleInvalid = titleInvalids.get(i);
					HSSFRow rowData = sheet.createRow(i+1);
					rowData.createCell(0).setCellValue(titleInvalid.getId());
					rowData.createCell(1).setCellValue(titleInvalid.getUrl());
					rowData.createCell(2).setCellValue(titleInvalid.getTitleId());
					rowData.createCell(3).setCellValue(titleInvalid.getTitleName());
					rowData.createCell(4).setCellValue(titleInvalid.getCreateTime());
					rowData.createCell(5).setCellValue("ר��������Ʒ");
				}
				wb.write(os);
				getResponse().flushBuffer();
				wb.close();
				os.flush();
				os.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@Action(value = "viewOnLine")
	public void viewOnLine(){
		String createTime = getStringParam("createTime");
		List<TitleInvalid> titleInvalids = titlesManager.getTitleInvalidInDate(createTime);
		StringBuilder stringBuilder = new StringBuilder();
		for (TitleInvalid titleInvalid : titleInvalids) {
			stringBuilder.append(titleInvalid.getUrl());
			stringBuilder.append("      ");

		}

		try {
			PrintWriter writer = getResponse().getWriter();
			writer.println(stringBuilder.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}



	public List<TitleInvalidData> getTitleInvalidPaginatedList() {
		return titleInvalidPaginatedList;
	}

	public void setTitleInvalidPaginatedList(List<TitleInvalidData> titleInvalidPaginatedList) {
		this.titleInvalidPaginatedList = titleInvalidPaginatedList;
	}



	
	public boolean isEmptyOrNot(String param) {
		if (param == null || "".equals(param) || "".equals(param.trim())) {
			return true;
		}
		return false;
	}
}
