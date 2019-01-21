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
 * 搜索词
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

	// 发布需要的配置信息
	private static final int hotsearch_rtype = PropertiesUtils.getIntValueByKey("hotsearch_rtype");// 商品排行榜详情页引用类型
	private static final int hotsearch_templateId = PropertiesUtils.getIntValueByKey("hotsearch_templateId");// 排行榜详情页模板Id

	/**
	 * @desc 进入到编辑或添加热词页面，对热词操作
	 * @return
	 */
	@Action(value = "editSearchword", results = { @Result(name = "success", location = "/jsp/searchkey/import.jsp") })
	public String editSearchword() {
		logger.info("editSearchword start!");
		Integer id = this.getIntParam("id");

		logger.info("editSearchword 热词id：" + id);

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
	 * @desc 热词列表页
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

		logger.info("listSearchwords 查询热词参数:" + paramMap);

		listHotword = hotSearchwordManager.list(paramMap);

		if (listHotword != null && !listHotword.isEmpty()) {
			for (HotSearchword hotSearchword : listHotword) {
				String createTime = hotSearchword.getCreateTime();
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
					String createTimeYMD = new SimpleDateFormat("yyyyMMdd").format(date);
					hotSearchword.setCreateTimeYMD(createTimeYMD);
				} catch (ParseException e) {
					logger.error("listSearchwords 异常：" + e.getMessage(),e);
				}

			}
		}

		logger.info("listSearchwords end!");
		return SUCCESS;
	}

	/**
	 * @desc 修改热词操作
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
	 * @desc 发布热词操作
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

				logger.info("publishSearchwords 热词id：" + id);
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
	 * @desc 批量发布热词
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
				logger.info("publishBatchwords 热词id!" + hoketId);

			}

			writeAjaxStr("success");

			logger.info("publishBatchwords end!");
		}
	}

	/**
	 * @desc 按id部分发布热词
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
				logger.info("scopePublishWords 热词id:" + hotSearchId);

			}
		}

		writeAjaxStr("success");

		logger.info("scopePublishWords end!");

	}

	/**
	 * @desc 全部发布热词
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
	 * @desc 删除热词操作
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
	 * @desc 生成未成功导入热词的excel文件
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
				Label titleName = new Label(0, 0, "热词标题");
				sheet.addCell(titleName);

				Label tagName = new Label(1, 0, "热词分词");
				sheet.addCell(tagName);

				Label reasonName = new Label(2, 0, "上传失败原因");
				sheet.addCell(reasonName);

				for (int i = 0; i < errorHotKeywords.size(); i++) {
					ErrorHotSearchword errorHotKeyword = errorHotKeywords.get(i);
					Label title = new Label(0, i + 1, errorHotKeyword.getTitle()); // 热词标题
					sheet.addCell(title);

					Label tag = new Label(1, i + 1, errorHotKeyword.getTag()); // 热词分词
					sheet.addCell(tag);

					Label reason = null; // 发布
					String reasonStr = null;
					Integer type = errorHotKeyword.getType();
					if (type == 1) {
						reasonStr = "热词重复";
					} else if (type == 2) {
						reasonStr = "热词为空";
					} else if (type == 3) {
						reasonStr = "没有搜索结果";
					} else if (type == 4) {
						reasonStr = "热词在总关键词库中重复";
					}
					reason = new Label(2, i + 1, reasonStr);// 失败原因
					sheet.addCell(reason);

				}
				workBook.write();
				getResponse().flushBuffer();
				workBook.close();
				os.flush();
				os.close();
			} catch (IOException e) {
				logger.error("errorwords IOException异常：" + e.getMessage(),e);
			} catch (RowsExceededException e) {
				logger.error("errorwords RowsExceededException异常：" + e.getMessage(),e);
			} catch (WriteException e) {
				logger.error("errorwords WriteException异常：" + e.getMessage(),e);
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
	 * @desc 导入热词操作
	 * @return
	 */
	@Action(value = "importSearchwords", results = {
			@Result(name = "success", type = "redirect", location = "/SearchKeyword/log.action") })
	public String importSearchwords() {
		Long startTime = System.currentTimeMillis();

		logger.info("importSearchwords satart!");

		String configration = (String) ReloadableConfig.getProperty("HOTWORD_GOME_PATH");
		String createTime = String.format("%tF %<tT", System.currentTimeMillis());

		logger.info("importSearchwords  导入文件名：" + fileName);

		String lowercaseName = getPinyinLowercase(getLoginUserName());
		String uploadTime = createTime.replaceAll(":", "-");
		uploadTime = uploadTime.replaceAll(" ", "_");
		String filePath = configration + uploadTime + "_" + lowercaseName + ".xls";
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(words, destFile);

			logger.info("importSearchwords destPath：" + filePath);
			// 添加热词列表
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

					String title = null; // 导入热词标题
					String tag = null;

					int type = 0;

					title = defaultStrs[0]; // 导入热词标题
					tag = defaultStrs[1];// 导入热词分词

					if (isEmptyOrNot(title)) {
						type = 2;
					}

					/**
					 * 校验导入的热词是否重复
					 * 
					 */

					Map<String, Object> searchMap = new HashMap<String, Object>();
					searchMap.put("qtitle", title);
					searchMap.put("qsite", "gome");

					List<HotSearchword> hotSearchList = this.hotSearchwordManager.listHotSearch(searchMap);

					// type 为1：热词重复 为2：热词为空 3：没有搜索结果
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
					 * 校验导入的热词有没有搜索结果
					 * 
					 */

					// Boolean flag = checkIsSearched(title);
					// logger.info("校验导入的热词:" + title);
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
					hotSearchwordManager.add(hotwordsAdd);// 添加热词数据

				}

				if (!errorHotWords.isEmpty()) {
					hotSearchwordManager.addErrorWords(errorHotWords); // 添加失败热词
				}

				importLog.setCreator(getLoginUserName());
				importLog.setTotalCount(hotwordsAdd.size() + errorHotWords.size());
				importLog.setFailCount(errorHotWords.size());
				importLog.setCreateTime(createTime);
				importLog.setFileUrl("./errorwords.action?date=" + createTime);
				importLog.setFileName(fileName);

				hotSearchwordManager.addLog(importLog); // 生成日志数据
			} catch (Exception e) {
				logger.error("importSearchwords 导入热词异常：" + e.getMessage(),e);
			} finally {
				if (book != null) {
					book.close();
				}
			}
		} catch (IOException e) {
			logger.error("importSearchwords 导入热词异常：" + e.getMessage(),e);
		}
		Long endTime = System.currentTimeMillis();
		logger.info("importSearchwords 耗时" + (endTime - startTime));

		logger.info("importSearchwords end!");
		return SUCCESS;
	}

	/**
	 * 查询添加的热词是否搜索到结果
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
			logger.error("检验热词有没有搜索结果异常:" + e.getMessage(),e);

			flag = false;
		}

		return flag;
	}

	/**
	 * @desc 导出excel热词文件
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
					Label titleId = new Label(0, 0, "热词ID");
					sheet.addCell(titleId);

					Label titleName = new Label(1, 0, "热词标题");
					sheet.addCell(titleName);

					Label dateLabel = new Label(2, 0, "添加/修改时间");
					sheet.addCell(dateLabel);

					Label operator = new Label(3, 0, "创建者/修改者");
					sheet.addCell(operator);

					Label publishLabel = new Label(4, 0, "发布");
					sheet.addCell(publishLabel);

					Label tagLabel = new Label(5, 0, "分词");
					sheet.addCell(tagLabel);

					Label searchLabel = new Label(6, 0, "搜索结果");
					sheet.addCell(searchLabel);
					Label urlLabel = new Label(7, 0, "热词URL");
					sheet.addCell(urlLabel);

					for (int i = 0; i < hotSearchwords.size(); i++) {
						HotSearchword hotSearchword = hotSearchwords.get(i);
						Number id = new Number(0, i + 1, hotSearchword.getId()); // 添加热词ID
						sheet.addCell(id);

						Label title = new Label(1, i + 1, hotSearchword.getTitle()); // 热词名称
						sheet.addCell(title);

						Label addModify = null; // 添加/修改时间
						String dateStr = hotSearchword.getCreateTime() + "/" + hotSearchword.getUpdateTime();
						addModify = new Label(2, i + 1, dateStr);
						sheet.addCell(addModify);

						Label creatorModifier = null; // 创建者/修改者
						creatorModifier = new Label(3, i + 1,
								hotSearchword.getCreator() + "/" + hotSearchword.getModifier());
						sheet.addCell(creatorModifier);

						Label pubState = null; // 发布
						String pubStr = hotSearchword.getChecked() == 1 ? "已发布" : "未发布";
						pubState = new Label(4, i + 1, pubStr);
						sheet.addCell(pubState);

						Label tag = null; // 分词
						String tagStr = hotSearchword.getTag();
						tag = new Label(5, i + 1, tagStr);
						sheet.addCell(tag);

						Label searchState = null; // 搜索结果
						String searchStr = hotSearchword.getIsSearched() == 1 ? "有" : "无";
						searchState = new Label(6, i + 1, searchStr);
						sheet.addCell(searchState);

						Label searchUrl = null; // 搜索结果
						String createTime = hotSearchword.getCreateTime();
						String createTimeYMD = null;
						try {
							Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
							createTimeYMD = new SimpleDateFormat("yyyyMMdd").format(date);
						} catch (ParseException e) {
							logger.info("outSearchwords 转换日期格式异常:" + e.getMessage(), e);
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
					logger.info("outSearchwords IOException异常：" + e.getMessage(),e);
				} catch (RowsExceededException e) {
					logger.info("outSearchwords RowsExceededException异常：" + e.getMessage(),e);
				} catch (WriteException e) {
					logger.info("outSearchwords WriteException异常：" + e.getMessage(),e);
				}
			}
		}

		logger.info("outSearchwords end!");

	}

	/**
	 * 查询相关热词
	 */
	@Action(value = "initReatedHotWords")
	public void initReatedHotWords() {
		logger.info("initReatedHotWords Start!");

		String result = "success";
		try {
			hotSearchwordManager.initReatedHotWords();
		} catch (Exception e) {
			result = "error";
			logger.error("initReatedHotWords 相关热词初始化异常：" + e.getMessage(),e);
		}

		writeAjaxStr(result);

		logger.info("initReatedHotWords End!");
	}

	/**
	 * 判断是否为空，true为空，false为存在内容
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
	 * @desc 导入热词文件添加热词操作
	 * @return
	 */
	@Action(value = "soptions", results = { @Result(name = "success", location = "/jsp/hotkey/tagoptions.jsp") })
	public String tag() {

		return SUCCESS;
	}

	/**
	 * @desc 导入热词文件添加热词操作
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
	 * @desc 获取小写的英文拼音
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
