/* 
 *   WWW.COO8.COM  
 */

package com.coo8.topic.controller.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.common.utils.StringUtil;
import com.coo8.topic.model.AnchorKeywords;
import com.coo8.topic.model.ErrorAnchorKeyword;
import com.coo8.topic.model.ImportLog;

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

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

@Namespace("/AnchorKeywords")
public class AnchorAction extends AnchorBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8433038808273181413L;

	private static Logger logger = LoggerFactory.getLogger(AnchorAction.class);
	private int command = 0; // 页面保存操作中新增与修改标志
	private String tags = "";
	protected PaginatedList<ImportLog> listImportLogs;
	private File words;

	/**
	 * @return the command
	 */
	public int getCommand() {
		return command;
	}

	/**
	 * @param command
	 *            the command to set
	 */
	public void setCommand(int command) {
		this.command = command;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

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

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/anchor/list.jsp") })
	public String list() {
		logger.info("AnchorKeywords list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		search.put("anchorId", getStringParam("anchorId"));
		search.put("anchorName", getStringParam("anchorName"));
		logger.info("AnchorKeywords list 查询参数：" + search);
		anchorKeywordsList = anchorKeywordsManager.findLikeByMap(search);

		logger.info("AnchorKeywords list end!");
		return "success";
	}

	@Action(value = "listAll")
	public String listAll() {
		logger.info("AnchorKeywords listAll start!");
		List<AnchorKeywords> anchorKeywords = anchorKeywordsManager.findAllAnchorKeywordsList();
		logger.info("AnchorKeywords listAll end!");

		return "success";
	}

	@Action(value = "create", results = { @Result(name = "success", location = "/jsp/anchor/create.jsp") })
	public String create() {
		logger.info("AnchorKeywords create start!");
		logger.info("AnchorKeywords create end!");
		return "success";
	}

	@Action(value = "save", results = {
			@Result(name = "success", type = "redirect", location = "/AnchorKeywords/list.action") })
	public String save() {
		logger.info("AnchorKeywords save start!");
		String username = getLoginUserName();
		if (akeywords != null) {
			AnchorKeywords anKeyword = anchorKeywordsManager.getById(akeywords.getId());
			if (command == COMMAND_EDIT) {
				anKeyword.setUpdateUser(username);

			} else if (command == COMMAND_CREATE) {
				akeywords.setCreateUser(username);
			}
			if (akeywords.getId() != null) {
				akeywords.setCreateDate(anKeyword.getCreateDate());
				akeywords.setUpdateDate(new java.util.Date());
				anchorKeywordsManager.update(akeywords);
				logger.info("AnchorKeywords save 更新成功！");
			} else {
				akeywords.setCreateDate(new java.util.Date());
				anchorKeywordsManager.save(akeywords);

				logger.info("AnchorKeywords save 保存成功！");
			}
		}
		logger.info("AnchorKeywords save end!");
		return "success";
	}

	@Action(value = "checkChongFu")
	public void checkChongFu() {
		logger.info("AnchorKeywords checkChongFu start!");
		AnchorKeywords entity = new AnchorKeywords();
		String keyName = this.getRequest().getParameter("keyName");
		try {
			keyName = java.net.URLDecoder.decode(new String(keyName.getBytes("GBK"), "UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("AnchorKeywords checkChongFu 异常：" + e.getMessage(),e);
		}
		String pcUrl = this.getRequest().getParameter("pcUrl");
		entity.setKeyName(keyName);
		entity.setPcUrl(pcUrl);
		AnchorKeywords anchorCf = anchorKeywordsManager.getByNamePcUrl(entity);
		if (anchorCf == null) {
			writeAjaxStr("yes");
		} else {
			writeAjaxStr("no");
		}

		logger.info("AnchorKeywords checkChongFu end!");
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/anchor/create.jsp") })
	public String edit() {
		logger.info("AnchorKeywords edit start!");
		command = COMMAND_EDIT; // 设置文件保存方式

		logger.info("AnchorKeywords edit 文件保存方式：" + command);
		if (akeywords.getId() != null) {
			this.akeywords = anchorKeywordsManager.getById(akeywords.getId());
		}
		logger.info("AnchorKeywords edit end!");
		return "success";
	}

	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/AnchorKeywords/list.action") })
	public String delete() {
		logger.info("AnchorKeywords delete start!");
		if (tags != null && !"".equals(tags)) {
			Set<Integer> hs = this.spli(tags, ";");
			for (Integer tid : hs) {
				anchorKeywordsManager.deleteById(tid);
				logger.info("AnchorKeywords delete 删除数据id：" + tid);
			}
		}
		logger.info("AnchorKeywords delete end!");
		return "success";
	}

	@Action(value = "deleteAll", results = {
			@Result(name = "success", type = "redirect", location = "/AnchorKeywords/list.action") })
	public String deleteAll() {
		logger.info("AnchorKeywords deleteAll start!");
		anchorKeywordsManager.deleteAll();
		logger.info("AnchorKeywords deleteAll end!");
		return "success";
	}

	@Action(value = "toExcel")
	public void toExcel() {
		logger.info("AnchorKeywords toExcel start!");
		List<AnchorKeywords> list = new ArrayList<AnchorKeywords>();
		Map<String, Object> search = new HashMap<String, Object>();

		String idBegin = this.getRequest().getParameter("idBegin");
		String idEnd = this.getRequest().getParameter("idEnd");
		if (null != idBegin && !"".equals(idBegin)) {
			search.put("idBegin", Integer.valueOf(idBegin));
		}
		if (null != idEnd && !"".equals(idEnd)) {
			search.put("idEnd", Integer.valueOf(idEnd));
		}
		search.put("sortColumns", "id desc");
		list = anchorKeywordsManager.findListLikeByMap(search);

		String[] titlename = { "编号", "关键词名称", "WEB链接", "WAP链接", "频率", "添加/修改时间", "创建/修改者" };

		WritableWorkbook workbook = null;
		OutputStream os = null;
		// 获取结果集
		try {
			// 定义生成excel文件的类型和名称
			String fileName = "关键词列表";
			fileName = new String(fileName.getBytes("gbk"), "ISO-8859-1");
			HttpServletResponse response = getResponse();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
			os = response.getOutputStream();

			workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("关键词列表", 0);

			sheet.setPageSetup(PageOrientation.LANDSCAPE);

			sheet.setColumnView(0, 10);// 第一列，宽度
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 30);
			sheet.setColumnView(4, 30);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 12);
			sheet.setColumnView(7, 12);
			// 设置行高
			sheet.setRowView(0, 600, false);

			// 设置字体
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
			titleFont.setColour(jxl.format.Colour.RED);
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 11);

			// 用于标题
			WritableCellFormat wcf_title = new WritableCellFormat(titleFont);
			wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
			wcf_title.setAlignment(Alignment.CENTRE); // 水平对齐
			wcf_title.setWrap(true); // 是否换行
			wcf_title.setBackground(jxl.format.Colour.YELLOW);// 设置背景颜色

			// 用于正文中文
			WritableCellFormat wcf_main = new WritableCellFormat(normalFont);
			wcf_main.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_main.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
			wcf_main.setAlignment(Alignment.CENTRE); // 水平对齐
			wcf_main.setWrap(true); // 是否换行

			for (int i = 0; i < titlename.length; i++) {
				jxl.write.Label labelCFC = new jxl.write.Label(i, 0, titlename[i], wcf_title);
				sheet.addCell(labelCFC);
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for (int i = 0; i < list.size(); i++) {
				AnchorKeywords achKeywords = (AnchorKeywords) list.get(i);
				int j = 0;
				Label label = null;

				label = new jxl.write.Label(j++, i + 1, String.valueOf(achKeywords.getId()), wcf_main);
				sheet.addCell(label);

				label = new jxl.write.Label(j++, i + 1, achKeywords.getKeyName(), wcf_main);
				sheet.addCell(label);

				label = new jxl.write.Label(j++, i + 1, achKeywords.getPcUrl(), wcf_main);
				sheet.addCell(label);

				label = new jxl.write.Label(j++, i + 1, achKeywords.getWapUrl(), wcf_main);
				sheet.addCell(label);

				label = new jxl.write.Label(j++, i + 1, String.valueOf(achKeywords.getRate()), wcf_main);
				sheet.addCell(label);

				String createtime = "";
				String updatetime = "";
				if (achKeywords.getCreateDate() != null) {
					createtime = dateFormat.format(achKeywords.getCreateDate());
				}
				if (achKeywords.getUpdateDate() != null) {
					updatetime = dateFormat.format(achKeywords.getUpdateDate());
				}
				label = new jxl.write.Label(j++, i + 1, createtime + "/" + updatetime, wcf_main);
				sheet.addCell(label);
				String creator = (achKeywords.getCreateUser() != null && !"null".equals(achKeywords.getCreateUser()))
						? achKeywords.getCreateUser() : "";
				String modifier = (achKeywords.getUpdateUser() != null && !"null".equals(achKeywords.getUpdateUser()))
						? achKeywords.getUpdateUser() : "";
				label = new jxl.write.Label(j++, i + 1, creator + "/" + modifier, wcf_main);
				sheet.addCell(label);
			}
			workbook.write();
			response.flushBuffer();
			workbook.close();
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			logger.error("AnchorKeywords toExcel FileNotFoundException异常：" + e.getMessage(),e);
		} catch (IOException e) {
			logger.error("AnchorKeywords toExcel IOException异常：" + e.getMessage(),e);
		} catch (RowsExceededException e) {
			logger.error("AnchorKeywords toExcel RowsExceededException异常：" + e.getMessage(),e);
		} catch (WriteException e) {
			logger.error("AnchorKeywords toExcel WriteException异常：" + e.getMessage(),e);
		} finally {
			if (null != workbook) {
				try {
					workbook.close();
				} catch (WriteException e) {
					logger.error("AnchorKeywords toExcel WriteException异常：" + e.getMessage(),e);
				} catch (IOException e) {
					logger.error("AnchorKeywords toExcel IOException异常：" + e.getMessage(),e);
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("AnchorKeywords toExcel IOException异常：" + e.getMessage(),e);
				}
			}
		}
		logger.info("AnchorKeywords toExcel end!");
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

	public boolean isEmptyOrNot(String param) {
		if (param == null || "".equals(param) || "".equals(param.trim())) {
			return true;
		}
		return false;
	}

	public boolean isNotNum(String param) {
		if (param == null || "".equals(param) || "".equals(param.trim())) {
			return true;
		}

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(param.trim());
		if (isNum.matches()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @desc 获取小写的英文拼音
	 * @return
	 */
	private String getPinyinLowercase(String chineseStr) {
		String result = Chinese2PinyinUtil.parseChinese(chineseStr);
		return result.toLowerCase();
	}

	@Action(value = "log", results = { @Result(name = "success", location = "/jsp/anchor/importFile.jsp") })
	public String logList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", page_index);
		paramMap.put("pageSize", page_size);
		listImportLogs = anchorKeywordsManager.listLog(paramMap);

		return SUCCESS;
	}

	/**
	 * @param
	 * @desc 生成未成功导入热词的excel文件
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
		List<ErrorAnchorKeyword> errorAnthorKeywords = anchorKeywordsManager.listDownLog(paramMap);
		if (errorAnthorKeywords != null) {
			try {
				String fileName = uploader + uploadTime + ".xls";
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName);
				OutputStream os = getResponse().getOutputStream();

				WritableWorkbook workBook = Workbook.createWorkbook(os);

				WritableSheet sheet = workBook.createSheet("sheet1", 0);
				Label one = new Label(0, 0, "关键词名称");
				Label two = new Label(1, 0, "WEB链接");
				Label three = new Label(2, 0, "WAP链接");
				Label four = new Label(3, 0, "频率");
				Label five = new Label(4, 0, "优先级");
				Label six = new Label(5, 0, "错误提示");
				sheet.addCell(one);
				sheet.addCell(two);
				sheet.addCell(three);
				sheet.addCell(four);
				sheet.addCell(five);
				sheet.addCell(six);
				StringBuilder str = new StringBuilder();
				for (int i = 0; i < errorAnthorKeywords.size(); i++) {
					ErrorAnchorKeyword errorAnthorKeyword = errorAnthorKeywords.get(i);

					WritableCellFormat errorFormat = new WritableCellFormat();
					errorFormat.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.RED);
					errorFormat.setBackground(jxl.format.Colour.GRAY_25);
					Integer type = errorAnthorKeyword.getType();
					type = type == null ? 0 : type;

					Label keyName = new Label(0, i + 1, errorAnthorKeyword.getKeyName());
					Label pcUrl = new Label(1, i + 1, errorAnthorKeyword.getPcUrl());
					Label rate = new Label(3, i + 1, errorAnthorKeyword.getRate().toString());
					Label wapUrl = new Label(2, i + 1, errorAnthorKeyword.getWapUrl());
					Label youxianji = new Label(4, i + 1, errorAnthorKeyword.getYouxianji().toString());
					if(i>0){
					str.delete(0, str.length());
					}
					
					sheet.addCell(keyName);
					sheet.addCell(pcUrl);
					sheet.addCell(rate);
					sheet.addCell(wapUrl);
					sheet.addCell(youxianji);
					
					
					if (type == 1) {
						keyName = new Label(0, i + 1, errorAnthorKeyword.getKeyName(), errorFormat);
						str.append("关键词名称为空，或者关键词重复 ;");
					} else if (type == 2) {
						pcUrl = new Label(1, i + 1, errorAnthorKeyword.getPcUrl(), errorFormat);
						str.append("pcUrl为空，或者不包含gome.com.cn;");
					} else if (type == 3) {
						rate = new Label(3, i + 1, errorAnthorKeyword.getRate().toString(), errorFormat);
						str.append("利率必须0-3之间正整数;");
					}else if (type == 4) {
						youxianji = new Label(4, i + 1, errorAnthorKeyword.getYouxianji().toString(), errorFormat);
						str.append("优先级必须是正整数;");
					}
					Label msg  = new Label(5, i + 1,str.toString(), errorFormat);
					sheet.addCell(msg);
				}
				workBook.write();
				getResponse().flushBuffer();
				workBook.close();
				os.flush();
				os.close();
			} catch (IOException | RowsExceededException e) {
				logger.error(e.getMessage(), e);
			}  catch (WriteException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @desc 导入关键词操作
	 * @return
	 * @throws ParseException
	 */
	@Action(value = "import", results = {
			@Result(name = "success", type = "redirect", location = "/AnchorKeywords/log.action") })
	public String importHotwords() throws ParseException {
		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
		String createTime = String.format("%tF %<tT", System.currentTimeMillis());

		String lowercaseName = getPinyinLowercase(getLoginUserName());
		String uploadTime = createTime.replaceAll(":", "-");
		uploadTime = uploadTime.replaceAll(" ", "_");
		String filePath = configration + uploadTime + "_" + lowercaseName + ".xls";
		File destFile = new File(filePath);

		try {
			FileUtils.copyFile(words, destFile);

			List<AnchorKeywords> anchorKeywords = new ArrayList<AnchorKeywords>();
			List<ErrorAnchorKeyword> errorAnchorWords = new ArrayList<ErrorAnchorKeyword>();

			Workbook book = null;
			ImportLog importLog = new ImportLog();
			try {
				book = Workbook.getWorkbook(destFile);
				Sheet sheet = book.getSheet(0);
				int rowCount = sheet.getRows();
				int colFlag = 0;
				for (int i = 0; i < rowCount; i++) {
					Cell[] cells = sheet.getRow(i);
					String[] defaultStrs = { "", "", "", "","" };
					colFlag = 0;
					for (Cell c : cells) {
						defaultStrs[colFlag++] = c.getContents();
					}
					String keyName = defaultStrs[0];// 关键词名称
					String pcUrl = defaultStrs[1];// WEB链接
					String wapUrl = defaultStrs[2];// WAP链接
					String rate = defaultStrs[3];// 频率
					String youxianji = defaultStrs[4];
					int type = 0;
					if (isEmptyOrNot(keyName)) {
						type = 1;
					} else if (isEmptyOrNot(pcUrl)) {
						type = 2;
					} else if (!pcUrl.contains("gome.com.cn")) {
						type = 2;
					}else if (isEmptyOrNot(rate)) {
						type = 3;
					}else if(isEmptyOrNot(youxianji)){
						type = 4;
					}
					if (keyName != null && pcUrl != null) {
						AnchorKeywords entity = new AnchorKeywords();
						entity.setKeyName(keyName);
						entity.setPcUrl(pcUrl);
						AnchorKeywords anchorCf = anchorKeywordsManager.getByNamePcUrl(entity);
						if (anchorCf != null) {
							type = 1;
						}
					}
					if (rate != null) {
						if (isNotNum(rate)) {
							type = 3;
						}
						if (Integer.valueOf(rate) > 3 || Integer.valueOf(rate) < 0) {
							type = 3;
						}
					}
					
					
					if (youxianji != null) {
						if (isNotNum(youxianji)) {
							type = 4;
						}
						if (Integer.valueOf(youxianji) < 0) {
							type = 4;
						}
					}
					
					
					if (type != 0) {
						ErrorAnchorKeyword errorAnchorKeyword = new ErrorAnchorKeyword();
						errorAnchorKeyword.setKeyName(keyName);
						errorAnchorKeyword.setPcUrl(pcUrl);
						errorAnchorKeyword.setWapUrl(wapUrl);
						errorAnchorKeyword.setRate(Integer.valueOf(rate));
						errorAnchorKeyword.setCreateDate(new java.util.Date());
						errorAnchorKeyword.setCreateUser(getLoginUserName());
						errorAnchorKeyword.setType(type);
						errorAnchorKeyword.setYouxianji(Integer.valueOf(youxianji));
						errorAnchorWords.add(errorAnchorKeyword);
					} else {
						AnchorKeywords anKeywords = new AnchorKeywords();
						anKeywords.setKeyName(keyName);
						anKeywords.setPcUrl(pcUrl);
						anKeywords.setWapUrl(wapUrl);
						anKeywords.setRate(Integer.valueOf(rate));
						anKeywords.setCreateDate(new java.util.Date());
						anKeywords.setCreateUser(getLoginUserName());
						anKeywords.setYouxianji(Integer.valueOf(youxianji));
						anchorKeywords.add(anKeywords);
					}

				}

				if (!anchorKeywords.isEmpty()) {
					for (AnchorKeywords entity : anchorKeywords) {
						AnchorKeywords anchorCf = anchorKeywordsManager.getByNamePcUrl(entity);
						if (anchorCf == null) {
							anchorKeywordsManager.save(entity);
						}
					}
				}

				if (!errorAnchorWords.isEmpty()) {
					anchorKeywordsManager.addErrorWords(errorAnchorWords); // 添加失败热词
				}

				importLog.setCreator(getLoginUserName());
				importLog.setTotalCount(anchorKeywords.size() + errorAnchorWords.size());
				importLog.setFailCount(errorAnchorWords.size());
				importLog.setCreateTime(createTime);
				importLog.setFileUrl("./errorwords.action?date=" + createTime);

				anchorKeywordsManager.addLog(importLog); // 生成日志数据
			} catch (BiffException e) {
				logger.error(e.getMessage(), e);
			} finally {
				if (book != null) {
					book.close();
				}
			}

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return SUCCESS;
	}
}
