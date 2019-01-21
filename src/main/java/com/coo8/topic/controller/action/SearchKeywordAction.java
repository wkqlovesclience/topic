package com.coo8.topic.controller.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.business.interfaces.queue.QueueManager;
import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.common.utils.HttpClientUtil;
import com.coo8.common.utils.StringUtil;
import com.coo8.topic.business.interfaces.IAllHotKeywordManager;
import com.coo8.topic.business.interfaces.IHotSearchwordManager;
import com.coo8.topic.business.interfaces.ITitlesManager;
import com.coo8.topic.model.AllHotKeyword;
import com.coo8.topic.model.ErrorHotSearchword;
import com.coo8.topic.model.HotSearchword;
import com.coo8.topic.model.ImportSearchLog;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;

/**
 * ������
 * 
 * @author fanqingxia
 *
 */
@Namespace("/SearchKeyword")
public class SearchKeywordAction extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger(SearchKeywordAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -1303279695685146197L;
	private IHotSearchwordManager hotSearchwordManager;

	private IAllHotKeywordManager allHotKeywordManager;

	private ITitlesManager titlesManager;

	private QueueManager queueManager;

	protected Integer pageNumber = 1;
	protected Integer page_size = 10;

	protected PaginatedList<HotSearchword> listHotword;
	protected PaginatedList<ImportSearchLog> listImportSearchLogs;

	private File words;
	private String wordsFileName;
	private String fileName;

	private String qid, qtitle, qcreator, qcreateTime, qchecked;

	// ������Ҫ��������Ϣ
	private static final int hotsearch_rtype = PropertiesUtils.getIntValueByKey("hotsearch_rtype");// ��Ʒ���а�����ҳ��������
	private static final int hotsearch_templateId = PropertiesUtils.getIntValueByKey("hotsearch_templateId");// ���а�����ҳģ��Id

	/**
	 * @desc ���뵽�༭������ȴ�ҳ�棬���ȴʲ���
	 * @return
	 */
	@Action(value = "editSearchword", results = { @Result(name = "success", location = "/jsp/searchkey/import.jsp") })
	public String editSearchword() {
		logger.info("editSearchword start!");
		Integer id = this.getIntParam("id");

		logger.info("editSearchword �ȴ�id��" + id);

		if (id != null) {
			HotSearchword hotSearchword = hotSearchwordManager.getById(id);
			if (hotSearchword != null) {
				getRequest().setAttribute("hotSearchword", hotSearchword);
			}
		}

		logger.info("editSearchword end!");

		return SUCCESS;
	}

	/**
	 * @desc �ȴ��б�ҳ
	 * @return success
	 */
	@Action(value = "listSearchwords", results = { @Result(name = "success", location = "/jsp/searchkey/list.jsp") })
	public String listSearchwords() {
		logger.info("listSearchwords start!");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", pageNumber);
		paramMap.put("pageSize", page_size);
		paramMap.put("qid", getStringParam("qid"));
		String qtitlePram = getStringParam("qtitle");
		if (qtitlePram != null) {
			qtitle = decode(qtitlePram);
			paramMap.put("qtitle", decode(qtitlePram));
		} else {
			paramMap.put("qtitle", qtitlePram);
		}
		String qcreatorParm = getStringParam("qcreator");
		if (qcreatorParm != null) {
			qcreator = decode(qcreatorParm);
			paramMap.put("qcreator", decode(qcreatorParm));
		} else {
			paramMap.put("qcreator", qcreatorParm);
		}
		String qcreateTime1 = getStringParam("qcreateTime");
		String qcreateEndTime = getStringParam("qcreateEndTime");

		if (qcreateTime1 != null && !"".equals(qcreateTime1)) {
			qcreateTime1 = qcreateTime1 + " 00:00:00";
		}
		if (qcreateEndTime != null && !"".equals(qcreateEndTime)) {
			qcreateEndTime = qcreateEndTime + " 23:59:59";
		}
		paramMap.put("qcreateTime", qcreateTime1);
		paramMap.put("qcreateEndTime", qcreateEndTime);
		paramMap.put("qchecked", getStringParam("qchecked"));
		paramMap.put("isSearched", getStringParam("isSearched"));
		paramMap.put("qsite", getTopicSite());

		logger.info("listSearchwords ��ѯ�ȴʲ���:" + paramMap);

		listHotword = hotSearchwordManager.list(paramMap);

		if (listHotword != null && !listHotword.isEmpty()) {
			for (HotSearchword hotSearchword : listHotword) {
				String createTime = hotSearchword.getCreateTime();
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
					String createTimeYMD = new SimpleDateFormat("yyyyMMdd").format(date);
					hotSearchword.setCreateTimeYMD(createTimeYMD);
				} catch (ParseException e) {
					logger.error("listSearchwords �쳣��" + e.getMessage(),e);
				}

			}
		}

		logger.info("listSearchwords end!");
		return SUCCESS;
	}

	/**
	 * @desc �޸��ȴʲ���
	 * @return
	 */
	@Action(value = "updateSearchword", results = {
			@Result(name = "success", type = "redirect", location = "/SearchKeyword/listSearchwords.action?pageNumber=${pageNumber}"),
			@Result(name = "false", location = "/jsp/searchkey/import.jsp") })
	public String updateSearchword() {
		logger.info("updateSearchword start!");
		Integer id = this.getIntParam("id");
		if (id != null) {
			HotSearchword hotSearchword = hotSearchwordManager.getById(id);
			if (hotSearchword != null) {
				String title = getStringParam("title");
				String tag = getStringParam("tag");

				Map<String, Object> searchMap = new HashMap<String, Object>();
				searchMap.put("qtitle", title);
				searchMap.put("qsite", "gome");

				List<HotSearchword> hotSearchList = this.hotSearchwordManager.listHotSearch(searchMap);

				if (hotSearchList != null && !hotSearchList.isEmpty()) {
					for (HotSearchword hotword : hotSearchList) {
						int searchId = hotword.getId();
						if (id != searchId) {

							getRequest().setAttribute("hotSearchword", hotSearchword);
							return "false";
						}
					}
				}

				String modifier = getLoginUserName();
				hotSearchword.setTitle(title);
				hotSearchword.setTag(tag);
				hotSearchword.setModifier(modifier);
				hotSearchword.setChecked(1);

				hotSearchwordManager.update(hotSearchword);


				dealPublishProcess(id);
			}
		}

		logger.info("updateSearchword end!");
		return SUCCESS;
	}

	/**
	 * @desc �����ȴʲ���
	 * @return
	 */
	@Action(value = "publishSearchwords")
	public void publishSearchwords() {
		logger.info("publishSearchwords start");

		Integer id = this.getIntParam("id");
		String result = "";
		if (id != null) {
			HotSearchword hotSearchword = hotSearchwordManager.getById(id);
			if (hotSearchword == null) {
				result = "noHotkeyword";
			} else {
				hotSearchwordManager.publish(hotSearchword);

				dealPublishProcess(id);

				logger.info("publishSearchwords �ȴ�id��" + id);
				result = "success";
			}
		} else {
			result = "emptyParam";
		}
		writeAjaxStr(result);

		logger.info("publishSearchwords end");
	}

	private void dealPublishProcess(int rfid) {
		queueManager.publish(rfid, hotsearch_templateId, 0, hotsearch_rtype, 2);

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

	/**
	 * @desc ���������ȴ�
	 */
	@Action(value = "publishBatchwords", results = {
			@Result(name = "success", type = "redirect", location = "/SearchKeyword/listSearchwords.action") })
	public void publishBatchwords() {
		logger.info("publishBatchwords start!");
		List<Integer> hs = this.getIntListParam("ids");
		if (!hs.isEmpty()) {
			for (Integer hoketId : hs) {
				HotSearchword hWord = hotSearchwordManager.getById(hoketId);
				hotSearchwordManager.publish(hWord);
				dealPublishProcess(hoketId);
				logger.info("publishBatchwords �ȴ�id!" + hoketId);

			}

			writeAjaxStr("success");

			logger.info("publishBatchwords end!");
		}
	}

	/**
	 * @desc ��id���ַ����ȴ�
	 * @return
	 */
	@Action(value = "scopePublishWords")
	public void scopePublishWords() {
		logger.info("scopePublishWords start!");
		Integer minId = this.getIntParam("minId");
		Integer maxId = this.getIntParam("maxId");

		logger.info("scopePublishWords minId:" + minId + ",maxId:" + maxId);

		List<HotSearchword> hotSearchwordsList = null;
		if (minId != null && maxId != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("minId", minId);
			paramMap.put("maxId", maxId);

			hotSearchwordsList = hotSearchwordManager.getByRangeId(paramMap);
		}
		if (hotSearchwordsList != null && !hotSearchwordsList.isEmpty()) {
			for (HotSearchword hotSearchword : hotSearchwordsList) {
				Integer hotSearchId = hotSearchword.getId();

				hotSearchwordManager.publish(hotSearchword);
				dealPublishProcess(hotSearchId);
				logger.info("scopePublishWords �ȴ�id:" + hotSearchId);

			}
		}

		writeAjaxStr("success");

		logger.info("scopePublishWords end!");

	}

	/**
	 * @desc ȫ�������ȴ�
	 */
	@Action(value = "publishAllBatchwords", results = {
			@Result(name = "success", type = "redirect", location = "/SearchKeyword/listSearchwords.action") })
	public void publishAllBatchwords() {
		List<HotSearchword> hotSearchList = hotSearchwordManager.listAll(new HashMap<String, Object>());
		if (!hotSearchList.isEmpty()) {
			for (HotSearchword hoke : hotSearchList) {
				hotSearchwordManager.publish(hoke);
				dealPublishProcess(hoke.getId());

			}
		}
	}

	/**
	 * @desc ɾ���ȴʲ���
	 * @return
	 */
	@Action(value = "deleteSearchwords", results = {
			@Result(name = "success", type = "redirect", location = "/SearchKeyword/listSearchwords.action") })
	public String deleteSearchwords() {
		List<Integer> ids = this.getIntListParam("ids");

		if (ids != null) {
			hotSearchwordManager.delete(ids);
		}
		return SUCCESS;
	}

	/**
	 * @param
	 * @desc ����δ�ɹ������ȴʵ�excel�ļ�
	 */
	@Action(value = "errorwords")
	public void generateFailDataFile() {
		logger.info("errorwords start!");

		String createTime = getStringParam("date");
		String logid = getStringParam("logid");

		String uploader = getStringParam("uploader");
		uploader = StringUtil.urlDecode(uploader, "utf-8");
		uploader = StringUtil.urlDecode(uploader, "utf-8");
		uploader = getPinyinLowercase(uploader);
		String uploadTime = createTime.replaceAll(":", "-");
		uploadTime = uploadTime.replaceAll(" ", "_");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("createTime", createTime);
		paramMap.put("logid", logid);
		List<ErrorHotSearchword> errorHotKeywords = hotSearchwordManager.listDownLog(paramMap);
		if (errorHotKeywords != null) {
			try {

				String fileNameNew = uploader + uploadTime
						+ String.format("%tY-%<tm-%<td_%<tH_%<tM_%<tS", System.currentTimeMillis()) + ".xls";

				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-disposition", "attachment; filename=" + fileNameNew);
				OutputStream os = getResponse().getOutputStream();

				WritableWorkbook workBook = Workbook.createWorkbook(os);

				WritableSheet sheet = workBook.createSheet("sheet1", 0);
				Label titleName = new Label(0, 0, "�ȴʱ���");
				sheet.addCell(titleName);

				Label tagName = new Label(1, 0, "�ȴʷִ�");
				sheet.addCell(tagName);

				Label reasonName = new Label(2, 0, "�ϴ�ʧ��ԭ��");
				sheet.addCell(reasonName);

				for (int i = 0; i < errorHotKeywords.size(); i++) {
					ErrorHotSearchword errorHotKeyword = errorHotKeywords.get(i);
					Label title = new Label(0, i + 1, errorHotKeyword.getTitle()); // �ȴʱ���
					sheet.addCell(title);

					Label tag = new Label(1, i + 1, errorHotKeyword.getTag()); // �ȴʷִ�
					sheet.addCell(tag);

					Label reason = null; // ����
					String reasonStr = null;
					Integer type = errorHotKeyword.getType();
					if (type == 1) {
						reasonStr = "�ȴ��ظ�";
					} else if (type == 2) {
						reasonStr = "�ȴ�Ϊ��";
					} else if (type == 3) {
						reasonStr = "û���������";
					} else if (type == 4) {
						reasonStr = "�ȴ����ܹؼ��ʿ����ظ�";
					}
					reason = new Label(2, i + 1, reasonStr);// ʧ��ԭ��
					sheet.addCell(reason);

				}
				workBook.write();
				getResponse().flushBuffer();
				workBook.close();
				os.flush();
				os.close();
			} catch (IOException e) {
				logger.error("errorwords IOException�쳣��" + e.getMessage(),e);
			} catch (RowsExceededException e) {
				logger.error("errorwords RowsExceededException�쳣��" + e.getMessage(),e);
			} catch (WriteException e) {
				logger.error("errorwords WriteException�쳣��" + e.getMessage(),e);
			}
		}

		logger.info("errorwords end!");
	}

	@Action(value = "log", results = { @Result(name = "success", location = "/jsp/searchkey/importFile.jsp") })
	public String logList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", pageNumber);
		paramMap.put("pageSize", page_size);
		paramMap.put("qsite", getTopicSite());
		listImportSearchLogs = hotSearchwordManager.listLog(paramMap);

		return SUCCESS;
	}

	/**
	 * @desc �����ȴʲ���
	 * @return
	 */
	@Action(value = "importSearchwords", results = {
			@Result(name = "success", type = "redirect", location = "/SearchKeyword/log.action") })
	public String importSearchwords() {
		Long startTime = System.currentTimeMillis();

		logger.info("importSearchwords satart!");

		String configration = (String) ReloadableConfig.getProperty("HOTWORD_GOME_PATH");
		String createTime = String.format("%tF %<tT", System.currentTimeMillis());

		logger.info("importSearchwords  �����ļ�����" + fileName);

		String lowercaseName = getPinyinLowercase(getLoginUserName());
		String uploadTime = createTime.replaceAll(":", "-");
		uploadTime = uploadTime.replaceAll(" ", "_");
		String filePath = configration + uploadTime + "_" + lowercaseName + ".xls";
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(words, destFile);

			logger.info("importSearchwords destPath��" + filePath);
			// ����ȴ��б�
			List<HotSearchword> hotwordsAdd = new ArrayList<HotSearchword>();
			List<ErrorHotSearchword> errorHotWords = new ArrayList<ErrorHotSearchword>();

			Workbook book = null;
			ImportSearchLog importLog = new ImportSearchLog();
			try {
				book = Workbook.getWorkbook(destFile);
				Sheet sheet = book.getSheet(0);
				int rowCount = sheet.getRows();
				int colFlag = 0;
				for (int i = 0; i < rowCount; i++) {
					Cell[] cells = sheet.getRow(i);
					String[] defaultStrs = { "", "", "" };
					colFlag = 0;
					for (Cell c : cells) {
						defaultStrs[colFlag++] = c.getContents();
					}

					String title = null; // �����ȴʱ���
					String tag = null;

					int type = 0;

					title = defaultStrs[0]; // �����ȴʱ���
					tag = defaultStrs[1];// �����ȴʷִ�

					if (isEmptyOrNot(title)) {
						type = 2;
					}

					/**
					 * У�鵼����ȴ��Ƿ��ظ�
					 * 
					 */

					Map<String, Object> searchMap = new HashMap<String, Object>();
					searchMap.put("qtitle", title);
					searchMap.put("qsite", "gome");

					List<HotSearchword> hotSearchList = this.hotSearchwordManager.listHotSearch(searchMap);

					// type Ϊ1���ȴ��ظ� Ϊ2���ȴ�Ϊ�� 3��û���������
					if (hotSearchList != null && !hotSearchList.isEmpty()) {
						type = 1;
					} else {
						Map<String, Object> prams = new HashMap<String, Object>();
						prams.put("title", title);
						prams.put("site", "gome");
						List<AllHotKeyword> allHotKeywordList = this.allHotKeywordManager.selectAllHotKeyword(prams);

						if (allHotKeywordList != null && !allHotKeywordList.isEmpty()) {
							type = 4;
						}
					}

					/**
					 * У�鵼����ȴ���û���������
					 * 
					 */

					// Boolean flag = checkIsSearched(title);
					// logger.info("У�鵼����ȴ�:" + title);
					// if (!flag) {
					// type = 3;
					// }

					if (type == 0) {

						HotSearchword hotSearchword = new HotSearchword();
						hotSearchword.setTitle(title);
						hotSearchword.setTag(tag);
						hotSearchword.setCreator(getLoginUserName());
						hotSearchword.setCreateTime(createTime);
						hotSearchword.setModifier(getLoginUserName());
						hotSearchword.setIsSearched(1);

						hotwordsAdd.add(hotSearchword);

					} else {

						ErrorHotSearchword errorHotSearchword = new ErrorHotSearchword();
						errorHotSearchword.setTitle(title);
						errorHotSearchword.setCreator(getLoginUserName());
						errorHotSearchword.setCreateTime(createTime);
						errorHotSearchword.setType(type);
						errorHotSearchword.setTag(tag);

						errorHotWords.add(errorHotSearchword);

					}

				}

				if (!hotwordsAdd.isEmpty()) {
					hotSearchwordManager.add(hotwordsAdd);// ����ȴ�����

				}

				if (!errorHotWords.isEmpty()) {
					hotSearchwordManager.addErrorWords(errorHotWords); // ���ʧ���ȴ�
				}

				importLog.setCreator(getLoginUserName());
				importLog.setTotalCount(hotwordsAdd.size() + errorHotWords.size());
				importLog.setFailCount(errorHotWords.size());
				importLog.setCreateTime(createTime);
				importLog.setFileUrl("./errorwords.action?date=" + createTime);
				importLog.setFileName(fileName);

				hotSearchwordManager.addLog(importLog); // ������־����
			} catch (Exception e) {
				logger.error("importSearchwords �����ȴ��쳣��" + e.getMessage(),e);
			} finally {
				if (book != null) {
					book.close();
				}
			}
		} catch (IOException e) {
			logger.error("importSearchwords �����ȴ��쳣��" + e.getMessage(),e);
		}
		Long endTime = System.currentTimeMillis();
		logger.info("importSearchwords ��ʱ" + (endTime - startTime));

		logger.info("importSearchwords end!");
		return SUCCESS;
	}

	/**
	 * ��ѯ��ӵ��ȴ��Ƿ����������
	 * 
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public Boolean checkIsSearched(String title) {
		Boolean flag = true;
		Integer totalCount = 0;

		String params = "{\"instock\":0,\"question\":" + "\"" + title + "\"" + ",\"XSearch\":true}";

		try {
			String paramJson = URLEncoder.encode(params, "UTF-8");
			String requestUrl = PropertiesUtils.getStringValueByKey("HOTSEARCH_SEARCH_API") + paramJson;

			logger.info("SearchKeywordAction checkIsSearched requestUrl:" + requestUrl);

			String searchInfo = HttpClientUtil.getJsonValue(requestUrl);

			JSONObject productsInfoObject = HttpClientUtil.getMyJsonObject(requestUrl, searchInfo);
			if (productsInfoObject != null && !productsInfoObject.isEmpty()) {
				String pageBar = productsInfoObject.getString("pageBar");
				if (pageBar != null) {
					JSONObject pageBarJson = JSONObject.fromObject(pageBar);

					totalCount = pageBarJson.getInt("totalCount");

					if (totalCount == 0) {
						flag = false;
					}
				}

			} else {
				flag = false;
			}
		} catch (Exception e) {
			logger.error("�����ȴ���û����������쳣:" + e.getMessage(),e);

			flag = false;
		}

		return flag;
	}

	/**
	 * @desc ����excel�ȴ��ļ�
	 * @return
	 */
	@Action(value = "outSearchwords")
	public void outSearchwords() {
		logger.info("outSearchwords start!");
		Integer minId = this.getIntParam("minId");
		Integer maxId = this.getIntParam("maxId");

		if (minId != null && maxId != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("minId", minId);
			paramMap.put("maxId", maxId);

			List<HotSearchword> hotSearchwords = hotSearchwordManager.getByRangeId(paramMap);
			if (hotSearchwords != null) {
				try {
					String fileNameNew = "hotSearchwords_"
							+ String.format("%tY-%<tm-%<td_%<tH_%<tM_%<tS", System.currentTimeMillis()) + ".xls";

					getResponse().setContentType("application/vnd.ms-excel");
					getResponse().setHeader("Content-disposition", "attachment; filename=" + fileNameNew);
					OutputStream os = getResponse().getOutputStream();

					WritableWorkbook workBook = Workbook.createWorkbook(os);

					WritableSheet sheet = workBook.createSheet("sheet1", 0);
					Label titleId = new Label(0, 0, "�ȴ�ID");
					sheet.addCell(titleId);

					Label titleName = new Label(1, 0, "�ȴʱ���");
					sheet.addCell(titleName);

					Label dateLabel = new Label(2, 0, "���/�޸�ʱ��");
					sheet.addCell(dateLabel);

					Label operator = new Label(3, 0, "������/�޸���");
					sheet.addCell(operator);

					Label publishLabel = new Label(4, 0, "����");
					sheet.addCell(publishLabel);

					Label tagLabel = new Label(5, 0, "�ִ�");
					sheet.addCell(tagLabel);

					Label searchLabel = new Label(6, 0, "�������");
					sheet.addCell(searchLabel);
					Label urlLabel = new Label(7, 0, "�ȴ�URL");
					sheet.addCell(urlLabel);

					for (int i = 0; i < hotSearchwords.size(); i++) {
						HotSearchword hotSearchword = hotSearchwords.get(i);
						Number id = new Number(0, i + 1, hotSearchword.getId()); // ����ȴ�ID
						sheet.addCell(id);

						Label title = new Label(1, i + 1, hotSearchword.getTitle()); // �ȴ�����
						sheet.addCell(title);

						Label addModify = null; // ���/�޸�ʱ��
						String dateStr = hotSearchword.getCreateTime() + "/" + hotSearchword.getUpdateTime();
						addModify = new Label(2, i + 1, dateStr);
						sheet.addCell(addModify);

						Label creatorModifier = null; // ������/�޸���
						creatorModifier = new Label(3, i + 1,
								hotSearchword.getCreator() + "/" + hotSearchword.getModifier());
						sheet.addCell(creatorModifier);

						Label pubState = null; // ����
						String pubStr = hotSearchword.getChecked() == 1 ? "�ѷ���" : "δ����";
						pubState = new Label(4, i + 1, pubStr);
						sheet.addCell(pubState);

						Label tag = null; // �ִ�
						String tagStr = hotSearchword.getTag();
						tag = new Label(5, i + 1, tagStr);
						sheet.addCell(tag);

						Label searchState = null; // �������
						String searchStr = hotSearchword.getIsSearched() == 1 ? "��" : "��";
						searchState = new Label(6, i + 1, searchStr);
						sheet.addCell(searchState);

						Label searchUrl = null; // �������
						String createTime = hotSearchword.getCreateTime();
						String createTimeYMD = null;
						try {
							Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
							createTimeYMD = new SimpleDateFormat("yyyyMMdd").format(date);
						} catch (ParseException e) {
							logger.info("outSearchwords ת�����ڸ�ʽ�쳣:" + e.getMessage(), e);
						}

						String urlStr = "/hotwords/" + createTimeYMD + "/" + hotSearchword.getId() + "/";
						searchUrl = new Label(7, i + 1, urlStr);
						sheet.addCell(searchUrl);
					}
					workBook.write();
					getResponse().flushBuffer();
					workBook.close();
					os.flush();
					os.close();
				} catch (IOException e) {
					logger.info("outSearchwords IOException�쳣��" + e.getMessage(),e);
				} catch (RowsExceededException e) {
					logger.info("outSearchwords RowsExceededException�쳣��" + e.getMessage(),e);
				} catch (WriteException e) {
					logger.info("outSearchwords WriteException�쳣��" + e.getMessage(),e);
				}
			}
		}

		logger.info("outSearchwords end!");

	}

	/**
	 * ��ѯ����ȴ�
	 */
	@Action(value = "initReatedHotWords")
	public void initReatedHotWords() {
		logger.info("initReatedHotWords Start!");

		String result = "success";
		try {
			hotSearchwordManager.initReatedHotWords();
		} catch (Exception e) {
			result = "error";
			logger.error("initReatedHotWords ����ȴʳ�ʼ���쳣��" + e.getMessage(),e);
		}

		writeAjaxStr(result);

		logger.info("initReatedHotWords End!");
	}

	/**
	 * �ж��Ƿ�Ϊ�գ�trueΪ�գ�falseΪ��������
	 */
	public boolean isEmptyOrNot(Integer param) {
		if (param == null) {
			return true;
		}
		return false;
	}

	public boolean isEmptyOrNot(String param) {
		if (param == null || "".equals(param) || "".equals(param.trim())) {
			return true;
		}
		return false;
	}

	public boolean isEmptyOrNot(Object param) {
		if (param == null || "".equals(param.toString())) {
			return true;
		}
		return false;
	}

	/**
	 * @desc �����ȴ��ļ�����ȴʲ���
	 * @return
	 */
	@Action(value = "soptions", results = { @Result(name = "success", location = "/jsp/hotkey/tagoptions.jsp") })
	public String tag() {

		return SUCCESS;
	}

	/**
	 * @desc �����ȴ��ļ�����ȴʲ���
	 * @return
	 */
	@Action(value = "check")
	public void check() {
		String title = this.getStringParam("title");
		String hotId = this.getStringParam("id");
		int id = Integer.parseInt(hotId);

		String result = "Success";

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("qtitle", title);
		searchMap.put("qsite", "gome");

		List<HotSearchword> hotSearchList = this.hotSearchwordManager.listHotSearch(searchMap);

		if (hotSearchList != null && !hotSearchList.isEmpty()) {
			for (HotSearchword hotSearchword : hotSearchList) {
				int searchId = hotSearchword.getId();
				if (id != searchId) {
					result = "File";
				}
			}
		}

		writeAjaxStr(result);
	}

	/**
	 * @desc ��ȡСд��Ӣ��ƴ��
	 * @return
	 */
	private String getPinyinLowercase(String chineseStr) {
		String result = Chinese2PinyinUtil.parseChinese(chineseStr);
		return result.toLowerCase();
	}

	public IHotSearchwordManager getHotSearchwordManager() {
		return hotSearchwordManager;
	}

	public void setHotSearchwordManager(IHotSearchwordManager hotSearchwordManager) {
		this.hotSearchwordManager = hotSearchwordManager;
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

	public PaginatedList<HotSearchword> getListHotword() {
		return listHotword;
	}

	public void setListHotword(PaginatedList<HotSearchword> listHotword) {
		this.listHotword = listHotword;
	}

	public PaginatedList<ImportSearchLog> getListImportLogs() {
		return listImportSearchLogs;
	}

	public void setListImportLogs(PaginatedList<ImportSearchLog> listImportSearchLogs) {
		this.listImportSearchLogs = listImportSearchLogs;
	}

	public ITitlesManager getTitlesManager() {
		return titlesManager;
	}

	public void setTitlesManager(ITitlesManager titlesManager) {
		this.titlesManager = titlesManager;
	}

	public File getWords() {
		return words;
	}

	public void setWords(File words) {
		this.words = words;
	}

	public String getWordsFileName() {
		return wordsFileName;
	}

	public void setWordsFileName(String wordsFileName) {
		this.wordsFileName = wordsFileName;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQtitle() {
		return qtitle;
	}

	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
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

	public String getQchecked() {
		return qchecked;
	}

	public void setQchecked(String qchecked) {
		this.qchecked = qchecked;
	}

	public QueueManager getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(QueueManager queueManager) {
		this.queueManager = queueManager;
	}

	public IAllHotKeywordManager getAllHotKeywordManager() {
		return allHotKeywordManager;
	}

	public void setAllHotKeywordManager(IAllHotKeywordManager allHotKeywordManager) {
		this.allHotKeywordManager = allHotKeywordManager;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
