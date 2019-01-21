/**
 * @author tianyu-ds
 * @date 2013-9-26
 * @project_name topic_trunk
 * @company 国美在线有限公司
 * @desc 热词的控制层类
 */
package com.coo8.topic.controller.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.common.utils.HotWordsUtil;
import com.coo8.common.utils.HttpClientUtil;
import com.coo8.common.utils.StringUtil;
import com.coo8.topic.business.interfaces.IAllHotKeywordManager;
import com.coo8.topic.business.interfaces.IHotKeywordManager;
import com.coo8.topic.business.interfaces.IHotSearchwordManager;
import com.coo8.topic.business.interfaces.ITagManager;
import com.coo8.topic.business.interfaces.ITitlesManager;
import com.coo8.topic.model.AllHotKeyword;
import com.coo8.topic.model.ErrorHotKeyword;
import com.coo8.topic.model.HotKeyword;
import com.coo8.topic.model.ImportLog;
import com.coo8.topic.model.Tag;

import gcache.clients.gedis.RoundRobinJedisPool;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

@Namespace("/HotKeyword")
public class HotKeywordAction extends BaseAction {
	private static final long serialVersionUID = -1000773680090547253L;
	private static Logger logger = LoggerFactory.getLogger(HotKeywordAction.class);

	private IHotKeywordManager hotKeywordManager;
	private ITagManager tagManager;
	private ITitlesManager titlesManager;
	@Autowired
	private IHotSearchwordManager hotSearchwordManager;

	private IAllHotKeywordManager allHotKeywordManager;

	protected Integer pageNumber = 1;
	protected Integer page_size = 10;

	protected PaginatedList<HotKeyword> listHotword;
	protected PaginatedList<ImportLog> listImportLogs;

	private File words;
	private String wordsFileName;

	private String qid, qtitle, qcreator, qcreateTime, qchecked;

	/**
	 * @desc 进入到编辑或添加热词页面，对热词操作
	 * @return
	 */
	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/hotkey/import.jsp") })
	public String edit() {
		logger.info("HotKeyword edit start!");
		Integer id = this.getIntParam("id");

		logger.info("HotKeyword edit 热门搜索词id" + id);
		// 获取一级标签列表
		List<Tag> tags = tagManager.getAllFirstTag();
		getRequest().setAttribute("tags", tags);

		if (id != null) {
			HotKeyword hotKeyword = hotKeywordManager.getById(id);
			if (hotKeyword != null) {
				getRequest().setAttribute("hotKeyword", hotKeyword);
			}
		}
		logger.info("HotKeyword edit end!");
		return SUCCESS;
	}

	/**
	 * @desc 热词列表页
	 * @return success
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/hotkey/list.jsp") })
	public String list() {
		logger.info("HotKeyword list start!");
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
		paramMap.put("qcreateTime", getStringParam("qcreateTime"));
		paramMap.put("qchecked", getStringParam("qchecked"));
		paramMap.put("qfirstTagId", getStringParam("qfirstTagId"));
		paramMap.put("qsecondTagId", getStringParam("qsecondTagId"));
		paramMap.put("qsite", getTopicSite());

		List<Tag> tags = tagManager.getAllFirstTag();
		getRequest().setAttribute("tags", tags);
		logger.info("HotKeyword list 查询热门搜索词参数：" + paramMap);

		listHotword = hotKeywordManager.list(paramMap);

		logger.info("HotKeyword list end!");

		return SUCCESS;
	}
	
	/**
	 * @desc 热词任务列表页
	 * @return success
	 */
	@Action(value = "listTask", results = { @Result(name = "success", location = "/jsp/hotkey/listTask.jsp") })
	public String listTask() {
		logger.info("HotKeyword listTask start!");
		Jedis j = null;
		try{
			j = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_HOTWORDS_DUBBO_IP,
					HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}finally{
			if(null != j){
				j.close();
			}	
		}
		
		if(null != j){
			String numSat = j.get("cutedId");
			String numEnd = j.get("cutNum");
			getRequest().setAttribute("numSat", numSat);
			getRequest().setAttribute("numEnd", numEnd);
		}

		return SUCCESS;
	}
	
	/**
	 * @desc 进入到编辑热词任务页面，对热词任务操作
	 * @return
	 */
	@Action(value = "editTask", results = { @Result(name = "success", location = "/jsp/hotkey/editTask.jsp") })
	public String editTask() {
		logger.info("HotKeywordTask edit start!");
		Jedis j = null;
		try{
			j = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_HOTWORDS_DUBBO_IP,
					HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}finally{
			if(null != j){
				j.close();
			}	
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qchecked", 1);
		List<Integer> hotSearchList = hotSearchwordManager.listLimit(paramMap);
		
		if(null != j){
			String numSat = j.get("cutedId");
			String numEnd = j.get("cutNum");
			
			if(StringUtils.isBlank(numSat) || StringUtils.isBlank(numEnd)){
				logger.info("editTask 重置内存数字 初始化默认 1,5000");
				j.set("cutedId", "0");
				j.set("cutNum", "5000");
				numSat = "0";
				numEnd = "5000";
			}
			
			getRequest().setAttribute("numSat", numSat);
			getRequest().setAttribute("numEnd", numEnd);
			getRequest().setAttribute("num", hotSearchList.size());
			}
		return SUCCESS;
	}
	
	/**
	 * @desc 修改热词操作
	 * @return
	 */
	@Action(value = "updateHotWordTask", results = {
	@Result(name = "success", type = "redirect", location = "/HotKeyword/listTask.action") })
	public String updateHotWordTask() {
		logger.info("HotKeywordTask update start!");
		String numSat = this.getStringParam("numSat");
		String numEnd = this.getStringParam("numEnd");
		
		Jedis j = null;
		try{
			j = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_HOTWORDS_DUBBO_IP,
					HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}finally{
			if(null != j){
				j.close();
			}	
		}
		
		if(null != j){
			j.set("cutedId", numSat);
			j.set("cutNum", numEnd);
		}
		
		logger.info("HotKeywordTask update end!");
		return SUCCESS;
	}
	
	/**
	 * @desc 修改热词操作
	 * @return
	 */
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirect", location = "/HotKeyword/list.action") })
	public String update() {
		logger.info("HotKeyword update start!");
		Integer id = this.getIntParam("id");
		logger.info("HotKeyword update 修改热门搜索词id：" + id);
		if (id != null) {
			HotKeyword hotKeyword = hotKeywordManager.getById(id);
			if (hotKeyword != null) {
				Integer firstTagId = getIntParam("firstTagId");
				Integer secondTagId = getIntParam("secondTagId");
				String title = getStringParam("title");
				String productId = getStringParam("goodsId");

				hotKeyword.setTitle(title);
				hotKeyword.setFirstTagId(firstTagId);
				hotKeyword.setSecondTagId(secondTagId);
				hotKeyword.setProductId(productId);
				hotKeyword.setModifier(getLoginUserName());

				hotKeywordManager.update(hotKeyword);
			}
		}
		hotKeywordManager.publishGomeHotWordsTest(id);
		logger.info("HotKeyword update end!");
		return SUCCESS;
	}

	/**
	 * @desc 发布热词操作
	 * @return
	 */
	@Action(value = "publish")
	public void publish() {
		logger.info("HotKeyword publish start!");
		Integer id = this.getIntParam("id");

		logger.info("HotKeyword publish 发布热门搜索词id：" + id);
		String result = "";
		if (id != null) {
			HotKeyword hotKeyword = hotKeywordManager.getById(id);
			if (hotKeyword == null) {
				result = "noHotkeyword";
			} else if (hotKeyword.getSecondTagName() == null || hotKeyword.getFirstTagName() == null) {
				result = "noTag";
			} else {
				hotKeywordManager.publish(hotKeyword);
				hotKeywordManager.publishGomeHotWords(id);
				result = "success";
			}
		} else {
			result = "emptyParam";
		}
		writeAjaxStr(result);
		logger.info("HotKeyword publish end!");
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
	@Action(value = "publishBatch", results = {
			@Result(name = "success", type = "redirect", location = "/HotKeyword/list.action") })
	public void publishBatch() {
		logger.info("HotKeyword publishBatch start!");
		List<Integer> hs = this.getIntListParam("ids");
		if (!hs.isEmpty()) {
			String errorResult = "error:";
			for (Integer hoketId : hs) {
				HotKeyword hWord = hotKeywordManager.getById(hoketId);
				if (hWord.getFirstTagName() == null || hWord.getSecondTagName() == null) {
					errorResult += hoketId + ",";
				} else {
					hotKeywordManager.publish(hWord);
					hotKeywordManager.publishGomeHotWords(hoketId);
					logger.info("HotKeyword publishBatch 发布热门搜索词id：" + hoketId);
				}
			}
			if (!"error:".equals(errorResult)) {
				errorResult = errorResult.substring(0, errorResult.length() - 1);
				writeAjaxStr(errorResult);
			} else {
				writeAjaxStr("success");
			}
		}
		logger.info("HotKeyword publishBatch end!");
	}

	/**
	 * @desc 手机端批量发布热词
	 */
	@Action(value = "publishWapBatch", results = {
			@Result(name = "success", type = "redirect", location = "/HotKeyword/list.action") })
	public void publishWapBatch() {
		logger.info("HotKeyword publishWapBatch start!");
		List<Integer> hs = this.getIntListParam("ids");
		if (!hs.isEmpty()) {
			String errorResult = "error:";
			for (Integer hoketId : hs) {
				HotKeyword hWord = hotKeywordManager.getById(hoketId);
				if (hWord.getFirstTagName() == null || hWord.getSecondTagName() == null) {
					errorResult += hoketId + ",";
				} else {
					hotKeywordManager.publish(hWord);
					hotKeywordManager.publishWapGomeHotWords(hoketId);
					logger.info("HotKeyword publishWapBatch 移动热门搜索词批量发布id：" + hoketId);
				}
			}
			if (!"error:".equals(errorResult)) {
				errorResult = errorResult.substring(0, errorResult.length() - 1);
				writeAjaxStr(errorResult);
			} else {
				writeAjaxStr("success");
			}
		}
		logger.info("HotKeyword publishWapBatch end!");
	}

	/**
	 * @desc 全部发布热词
	 */
	@Action(value = "publishAllBatch", results = {
			@Result(name = "success", type = "redirect", location = "/HotKeyword/list.action") })
	public void publishAllBatch() {
		logger.info("HotKeyword publishAllBatch start!");
		List<HotKeyword> hotKeyList = hotKeywordManager.listAll(new HashMap<String, Object>());
		if (!hotKeyList.isEmpty()) {
			String errorResult = "error:";
			for (HotKeyword hoke : hotKeyList) {
				if (hoke.getFirstTagName() == null || hoke.getSecondTagName() == null) {
					errorResult += hoke.getId() + ",";
				} else {
					hotKeywordManager.publish(hoke);
					hotKeywordManager.publishGomeHotWords(hoke.getId());
				}
			}
			if (!"error:".equals(errorResult)) {
				errorResult = errorResult.substring(0, errorResult.length() - 1);
				writeAjaxStr(errorResult);
			} else {
				writeAjaxStr("success");
			}
		}
		logger.info("HotKeyword publishAllBatch end!");
	}

	/**
	 * @desc 修改热词操作
	 * @return
	 */
	@Action(value = "add", results = {
			@Result(name = "success", type = "redirect", location = "/HotKeyword/list.action") })
	public String add() {
		logger.info("HotKeyword add start!");
		Integer firstTagId = getIntParam("firstTagId");
		Integer secondTagId = getIntParam("secondTagId");
		String title = getStringParam("title");
		String productId = getStringParam("goodsId");

		String createTime = String.format("%tF %<tT", System.currentTimeMillis());

		List<HotKeyword> hotKeyWords = new ArrayList<HotKeyword>();
		HotKeyword hotKeyword = new HotKeyword();
		hotKeyword.setTitle(title);
		hotKeyword.setFirstTagId(firstTagId == null ? 0 : firstTagId);
		hotKeyword.setSecondTagId(secondTagId == null ? 0 : secondTagId);
		hotKeyword.setProductId(productId);
		hotKeyword.setCreator(getLoginUserName());
		hotKeyword.setCreateTime(createTime);
		hotKeyword.setModifier(getLoginUserName());
		hotKeyWords.add(hotKeyword);

		int startTestId = hotKeywordManager.getLastedInsertId();

		hotKeywordManager.add(hotKeyWords);

		for (int i = 1; i <= hotKeyWords.size(); i++) {
			int tempId = startTestId + i;
			hotKeywordManager.publishGomeHotWordsTest(tempId);
		}
		logger.info("HotKeyword add end!");
		return SUCCESS;
	}

	/**
	 * @desc 删除热词操作
	 * @return
	 */
	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/HotKeyword/list.action") })
	public String delete() {
		logger.info("HotKeyword delete start!");
		List<Integer> ids = this.getIntListParam("ids");

		if (ids != null) {
			hotKeywordManager.delete(ids);
		}
		logger.info("HotKeyword delete end!");
		return SUCCESS;
	}

	/**
	 * @param
	 * @desc 生成未成功导入热词的excel文件
	 */
	@Action(value = "errorwords")
	public void generateFailDataFile() {
		logger.info("HotKeyword errorwords start!");
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
		List<ErrorHotKeyword> errorHotKeywords = hotKeywordManager.listDownLog(paramMap);
		if (errorHotKeywords != null) {
			try {
				String fileName = uploader + uploadTime + ".xls";
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName);
				OutputStream os = getResponse().getOutputStream();

				WritableWorkbook workBook = Workbook.createWorkbook(os);

				WritableSheet sheet = workBook.createSheet("sheet1", 0);

				Label titleName = new Label(0, 0, "热词标题");
				sheet.addCell(titleName);

				Label tagName = new Label(1, 0, "热词分词");
				sheet.addCell(tagName);

				Label typeName = new Label(2, 0, "上传失败类型");
				sheet.addCell(typeName);

				Label reasonName = new Label(3, 0, "上传失败原因");
				sheet.addCell(reasonName);

				for (int i = 0; i < errorHotKeywords.size(); i++) {
					ErrorHotKeyword errorHotKeyword = errorHotKeywords.get(i);
					Label title = new Label(0, i + 1, errorHotKeyword.getTitle()); // 热词标题
					sheet.addCell(title);

					Label tag = new Label(1, i + 1, errorHotKeyword.getTagName()); // 热词分词
					sheet.addCell(tag);

					Label reason = null; // 发布
					String reasonStr = null;
					Integer type = errorHotKeyword.getType();
					Number id = new Number(2, i + 1, type); // 失败类型
					sheet.addCell(id);
					if (type == 1) {
						reasonStr = "prudectId为空";
					} else if (type == 2) {
						reasonStr = "热词为空";
					} else if (type == 3) {
						reasonStr = "分词为空";
					} else if (type == 4) {
						reasonStr = "热词重复";
					} else if (type == 5) {
						reasonStr = "热词在总关键词库中重复";
					} else if (type == 6) {
						reasonStr = "没有搜索结果";
					}

					reason = new Label(3, i + 1, reasonStr);// 失败原因
					sheet.addCell(reason);

				}
				workBook.write();
				getResponse().flushBuffer();
				workBook.close();
				os.flush();
				os.close();
			} catch (IOException e) {
				logger.error("HotKeyword errorwords IOException异常：" + e.getMessage(),e);
			} catch (RowsExceededException e) {
				logger.error("HotKeyword errorwords RowsExceededException异常：" + e.getMessage(),e);
			} catch (WriteException e) {
				logger.error("HotKeyword errorwords WriteException异常：" + e.getMessage(),e);
			}
		}

		logger.info("HotKeyword errorwords end!");
	}

	@Action(value = "log", results = { @Result(name = "success", location = "/jsp/hotkey/importFile.jsp") })
	public String logList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", pageNumber);
		paramMap.put("pageSize", page_size);
		paramMap.put("qsite", getTopicSite());
		listImportLogs = hotKeywordManager.listLog(paramMap);

		return SUCCESS;
	}

	/**
	 * @desc 导入热词操作
	 * @return
	 */
	@Action(value = "import", results = {
			@Result(name = "success", type = "redirect", location = "/HotKeyword/log.action") })
	public String importHotwords() {
		logger.info("HotKeyword import start!");
		String configration = (String) ReloadableConfig.getProperty("HOTWORD_GOME_PATH");
		String createTime = String.format("%tF %<tT", System.currentTimeMillis());

		String lowercaseName = getPinyinLowercase(getLoginUserName());
		String uploadTime = createTime.replaceAll(":", "-");
		uploadTime = uploadTime.replaceAll(" ", "_");
		String filePath = configration + uploadTime + "_" + lowercaseName + ".xls";
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(words, destFile);

			List<HotKeyword> hotKeywords = new ArrayList<HotKeyword>();
			List<ErrorHotKeyword> errorHotWords = new ArrayList<ErrorHotKeyword>();

			Workbook book = null;
			ImportLog importLog = new ImportLog();
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
					String productId = defaultStrs[0]; // 导入产品ID
					String title = defaultStrs[1]; // 导入热词标题
					String tagName = defaultStrs[2]; // 导入二级标签ID,名称

					int type = 0;
					String str = titlesManager.checkItemName(productId);
					Tag tag = this.getTag(tagName);
					if (null != str && !"".equals(str.trim())) {		
						if("exist".equals(str) ){
							type = 1;
						}						
					} else {
						type = 1;
					}					
					if (isEmptyOrNot(title)) {
						type = 2;
					} else if (isEmptyOrNot(tagName) || isEmptyOrNot(tag.getTagName())) {
						type = 3;
					}

					/**
					 * 校验导入的热词是否重复
					 * 
					 */

					Map<String, Object> searchMap = new HashMap<String, Object>();
					searchMap.put("qtitle", title);
					searchMap.put("qsite", "gome");

					List<HotKeyword> hotKeywordList = this.hotKeywordManager.selectHotKeywordlist(searchMap);

					// type 为1：热词重复 为2：热词为空 3：没有搜索结果
					if (hotKeywordList != null && !hotKeywordList.isEmpty()) {
						type = 4;
					} else {
						Map<String, Object> prams = new HashMap<String, Object>();
						prams.put("title", title);
						prams.put("site", "gome");
						List<AllHotKeyword> allHotKeywordList = this.allHotKeywordManager.selectAllHotKeyword(prams);

						if (allHotKeywordList != null && !allHotKeywordList.isEmpty()) {
							type = 5;
						}
					}

					if (type != 0) {
						ErrorHotKeyword errorHotKeyword = new ErrorHotKeyword();
						errorHotKeyword.setTitle(title);
						errorHotKeyword.setFirstTagId(tag.getParentId());
						errorHotKeyword.setSecondTagId(tag.getId());
						errorHotKeyword.setProductId(productId);
						errorHotKeyword.setCreator(getLoginUserName());
						errorHotKeyword.setCreateTime(createTime);
						errorHotKeyword.setTagName(tagName);
						errorHotKeyword.setType(type);

						errorHotWords.add(errorHotKeyword);
					} else {

						HotKeyword hotKeyword = new HotKeyword();
						hotKeyword.setTitle(title);
						hotKeyword.setFirstTagId(tag.getParentId());
						hotKeyword.setSecondTagId(tag.getId());
						hotKeyword.setProductId(productId);
						hotKeyword.setCreator(getLoginUserName());
						hotKeyword.setCreateTime(createTime);
						hotKeyword.setModifier(getLoginUserName());

						hotKeywords.add(hotKeyword);
					}

				}

				if (!hotKeywords.isEmpty()) {
					hotKeywordManager.add(hotKeywords);// 添加热词数据

				}

				if (!errorHotWords.isEmpty()) {
					hotKeywordManager.addErrorWords(errorHotWords); // 添加失败热词
				}

				importLog.setCreator(getLoginUserName());
				importLog.setTotalCount(hotKeywords.size() + errorHotWords.size());
				importLog.setFailCount(errorHotWords.size());
				importLog.setCreateTime(createTime);
				importLog.setFileUrl("./errorwords.action?date=" + createTime);

				hotKeywordManager.addLog(importLog); // 生成日志数据
			} catch (BiffException e) {
				logger.error("HotKeyword import BiffException异常：" + e.getMessage(),e);
			} catch (Exception e) {
				logger.error("HotKeyword import Exception异常：" + e.getMessage(),e);
			} finally {
				if (book != null) {
					book.close();
				}
			}
		} catch (IOException e) {
			logger.error("HotKeyword import IOException异常：" + e.getMessage(),e);
		}

		logger.info("HotKeyword import end!");
		return SUCCESS;
	}

	/**
	 * @desc 导出excel热词文件
	 * @return
	 */
	@Action(value = "out")
	public void outImport() {
		logger.info("HotKeyword out start!");
		Integer minId = this.getIntParam("minId");
		Integer maxId = this.getIntParam("maxId");

		if (minId != null && maxId != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("minId", minId);
			paramMap.put("maxId", maxId);

			List<HotKeyword> hotKeywords = hotKeywordManager.getByRangeId(paramMap);
			if (hotKeywords != null) {
				try {
					String fileName = "hotkeywords_"
							+ String.format("%tY-%<tm-%<td_%<tH_%<tM_%<tS", System.currentTimeMillis()) + ".xls";

					getResponse().setContentType("application/vnd.ms-excel");
					getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName);
					OutputStream os = getResponse().getOutputStream();

					WritableWorkbook workBook = Workbook.createWorkbook(os);

					WritableSheet sheet = workBook.createSheet("sheet1", 0);
					Label titleId = new Label(0, 0, "热词ID");
					sheet.addCell(titleId);
					Label titleProductId = new Label(1, 0, "产品编号");
					sheet.addCell(titleProductId);
					Label titleName = new Label(2, 0, "热词标题");
					sheet.addCell(titleName);
					Label titleTagName = new Label(3, 0, "一级标签/二级标签");
					sheet.addCell(titleTagName);
					Label dateLabel = new Label(4, 0, "添加/修改时间");
					sheet.addCell(dateLabel);
					Label operator = new Label(5, 0, "创建者/修改者");
					sheet.addCell(operator);
					Label publishLabel = new Label(6, 0, "发布");
					sheet.addCell(publishLabel);

					for (int i = 0; i < hotKeywords.size(); i++) {
						HotKeyword hotKeyword = hotKeywords.get(i);
						Number id = new Number(0, i + 1, hotKeyword.getId()); // 添加热词ID
						sheet.addCell(id);

						Label productId = new Label(1, i + 1, hotKeyword.getProductId()); // 产品ID
						sheet.addCell(productId);

						Label title = new Label(2, i + 1, hotKeyword.getTitle()); // 热词名称
						sheet.addCell(title);

						Label tagName = null; // 一级标签/二级标签
						String tagContent = "";
						tagContent += isEmptyOrNot(hotKeyword.getFirstTagName()) ? "" : hotKeyword.getFirstTagName();
						tagContent += "/"
								+ (isEmptyOrNot(hotKeyword.getSecondTagName()) ? "" : hotKeyword.getSecondTagName());
						tagName = new Label(3, i + 1, tagContent);
						sheet.addCell(tagName);

						Label addModify = null; // 添加/修改时间
						String dateStr = hotKeyword.getCreateTime() + "/" + hotKeyword.getUpdateTime();
						addModify = new Label(4, i + 1, dateStr);
						sheet.addCell(addModify);

						Label creatorModifier = null; // 创建者/修改者
						creatorModifier = new Label(5, i + 1, hotKeyword.getCreator() + "/" + hotKeyword.getModifier());
						sheet.addCell(creatorModifier);

						Label pubState = null; // 发布
						String pubStr = hotKeyword.getChecked() == 1 ? "已发布" : "未发布";
						pubState = new Label(6, i + 1, pubStr);
						sheet.addCell(pubState);
					}
					workBook.write();
					getResponse().flushBuffer();
					workBook.close();
					os.flush();
					os.close();
				} catch (IOException e) {
					logger.error("HotKeyword out IOException异常：" + e.getMessage(),e);
				} catch (RowsExceededException e) {
					logger.error("HotKeyword out RowsExceededException异常：" + e.getMessage(),e);
				} catch (WriteException e) {
					logger.error("HotKeyword out WriteException异常：" + e.getMessage(),e);
				}
			}
		}

		logger.info("HotKeyword out end!");
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
		Integer parentId = getIntParam("parent_id");

		if (parentId != null) {
			List<Tag> subtags = tagManager.getByParentId(parentId);
			getRequest().setAttribute("subtags", subtags);
		}

		return SUCCESS;
	}

	/**
	 * @desc 查看标签名称是否存在
	 */
	private Tag getTag(String tagName) {
		Tag emptyTag = new Tag();
		if (tagName == null || "".equals(tagName.trim())) {
			return emptyTag;
		}
		Tag queryTag = tagManager.getTagFromExcel(tagName);
		if (queryTag != null) {
			return queryTag;
		}
		return emptyTag;
	}

	/**
	 * @desc 导入热词文件添加热词操作
	 * @return
	 */
	@Action(value = "check")
	public void check() {
		String productId = this.getStringParam("product_id");
		JSONObject demoJson = this.getJson(productId);
		writeAjaxStr(demoJson.toString());
	}

	/**
	 * @desc 根据产品ID来获取相关产品的json信息
	 * @param productId
	 * @return
	 */
	private JSONObject getJson(String productId) {
		String atgJspPageUrl = ReloadableConfig.getProperty("atgJspPageUrl",
				"http://www.gome.com.cn/coo8/data/productInterface.jsp?productId=");
		atgJspPageUrl += productId;
		String jsonStr = HttpClientUtil.readHttpPage(atgJspPageUrl);
		if (jsonStr == null || "".equals(jsonStr) || "".equals(jsonStr.trim())) {
			jsonStr = "{}";
		}
		JSONObject demoJson = JSONObject.fromObject(jsonStr);

		return demoJson;
	}

	/**
	 * @desc 获取小写的英文拼音
	 * @return
	 */
	private String getPinyinLowercase(String chineseStr) {
		String result = Chinese2PinyinUtil.parseChinese(chineseStr);
		return result.toLowerCase();
	}

	public IHotKeywordManager getHotKeywordManager() {
		return hotKeywordManager;
	}

	public void setHotKeywordManager(IHotKeywordManager hotKeywordManager) {
		this.hotKeywordManager = hotKeywordManager;
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

	public PaginatedList<HotKeyword> getListHotword() {
		return listHotword;
	}

	public void setListHotword(PaginatedList<HotKeyword> listHotword) {
		this.listHotword = listHotword;
	}

	public PaginatedList<ImportLog> getListImportLogs() {
		return listImportLogs;
	}

	public void setListImportLogs(PaginatedList<ImportLog> listImportLogs) {
		this.listImportLogs = listImportLogs;
	}

	public ITagManager getTagManager() {
		return tagManager;
	}

	public void setTagManager(ITagManager tagManager) {
		this.tagManager = tagManager;
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

	public IAllHotKeywordManager getAllHotKeywordManager() {
		return allHotKeywordManager;
	}

	public void setAllHotKeywordManager(IAllHotKeywordManager allHotKeywordManager) {
		this.allHotKeywordManager = allHotKeywordManager;
	}

}
