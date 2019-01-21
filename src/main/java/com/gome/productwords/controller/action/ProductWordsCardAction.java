package com.gome.productwords.controller.action;


import com.alibaba.fastjson.JSON;
import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.common.utils.StringUtil;
import com.coo8.topic.controller.action.BaseAction;
import com.gome.productwords.manager.infer.ProductWordsManager;
import com.gome.productwords.model.ProductWordsCard;
import com.gome.productwords.model.ProductWordsCardImportLog;
import com.gome.productwords.model.ProductWordsErrorCard;
import com.gome.utils.ExcelPoiUtils;
import com.gome.utils.ImportProductWordsUtils;
import com.opensymphony.xwork2.ActionContext;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author wangkeqiang
 * @version 1.0
 * @since 1.0
 */

@Namespace("/ProductWordsCard")
public class ProductWordsCardAction extends BaseAction  {

	private static final long serialVersionUID = -7443217160596570313L;
	private static Logger logger = LoggerFactory.getLogger(ProductWordsCardAction.class);
	private Integer page_index=1;
	private Integer page_size=10;
	private static final Integer DELETE = 1;
	private static final Integer NO_DELETE = 0;
	private static final Integer EDITOR = 1;
	private static final Integer NO_EDITOR = 0;
	private static final Integer INVALID = 1;
	private static final Integer NO_INVALID = 0;
	private static final Integer IS_ENABLED = 1;
	private static final Integer DIS_ENABLED = 1;
	protected PaginatedList<ProductWordsCardImportLog> listImportLogs;
	protected PaginatedList<ProductWordsCard> productWordsCardList;

	protected ProductWordsCard productWordsCardForValueStack;

	protected String id;
	protected Integer isEditor = 3;
	protected Integer isInvalid = 3;
	protected String tags;
	private File words;
	private String wordsFileName;
	private ProductWordsManager productWordsManager;
	//编辑
	protected String productWordsBase;//商品词词根
	protected String productWordsName;//商品词词名
	protected String cardId;
	protected String fileType;
	//商品词状态，这儿的status用处主要是为了jsp页面的条件判断，与上面的isEditor、isInvalid类似
	private Integer status = 3;

	//业务需要jsp页面展示有效个数和无效个数
	private Integer isEnableCount;
	private Integer notEnableCount;


	private String qcreateTime;

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/productwordscard/list.jsp") })
	public String list() {
		logger.info("productwordscard list start!"+productWordsName);
		Map<String, Object> search = new HashMap<String, Object>();
		if(null != productWordsName){
			try {
				productWordsName = URLDecoder.decode(productWordsName,"UTF-8");
				logger.info("转编码后的productWordsName为："+productWordsName);
			}catch (Exception e){
				logger.info("decoder出错了");
			}

			search.put("productWordsName", productWordsName);
			logger.info("productWordsName:  "+ productWordsName);
		}
		if(isInvalid != 3)	{
			search.put("isInvalid", isInvalid);
		}

		if(status!=3){
			search.put("isEnable",status);
		}

		search.put("pageSize", page_size);
		search.put("isDelete", NO_DELETE);
		search.put("pageNumber", page_index);
		search.put("qcreateTime",qcreateTime);

		logger.info("productWords list 查询数据参数：" + search);
		productWordsCardList = productWordsManager.findLikeByMap(search);

		isEnableCount = productWordsManager.getCountIsEnable();
		notEnableCount = productWordsManager.getCountNotEnable();

		logger.info("-------------------"+productWordsCardList.size()+"----------------------");
		logger.info("productwordscard list end!");
		return "success";

	}

	@Action(value = "delete", results = { @Result(name = "success", type = "redirect", location = "/ProductWordsCard/list.action") })
	public String delete() {
		logger.info("productwordscard delete start!");
		if (!StringUtils.isBlank(tags)) {
			Set<Integer> hs = this.spli(tags, ";");
			//批量删除
			for (Integer tid : hs) {
				ProductWordsCard productWordsCard = productWordsManager.getById(tid);
				if(productWordsCard != null){
					productWordsCard.setIsDelete(DELETE);
					productWordsCard.setIsInvalid(INVALID);
					productWordsCard.setUpdateDate(new Date());
					productWordsCard.setModifier(getLoginUserName());
					productWordsManager.update(productWordsCard);
				}
			}
		}
		logger.info("productwordscard delete end!");
		return "success";
	}



	@Action(value = "putForward", results = { @Result(name = "success", type = "redirect", location = "/ProductWordsCard/list.action") })
	public String putForward() {
		logger.info("productwordscard putForward start!");
		if (!StringUtils.isBlank(tags)) {
			Set<Integer> hs = this.spli(tags, ";");
			//批量发布
			for (Integer tid : hs) {
				ProductWordsCard productWordsCard = productWordsManager.getById(tid);
				logger.info("  "+productWordsCard+"  ");
				if(productWordsCard != null){
					productWordsCard.setIsInvalid(INVALID);
					productWordsCard.setUpdateDate(new Date());
					productWordsCard.setModifier(getLoginUserName());
					productWordsManager.update(productWordsCard);
				}
			}
		}
		logger.info("productwordscard putForward end!");
		return "success";
	}








	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/productwordscard/create.jsp") })
	public String edit() {
		logger.info("promotionCard edit start!");
		productWordsCardForValueStack = productWordsManager.getById(Integer.parseInt(id));
		cardId = id;
		productWordsBase = productWordsCardForValueStack.getProductWordsBase();
		isInvalid = productWordsCardForValueStack.getIsInvalid();
		return "success";
	}

	/**
	 * 编辑后保存   可编辑 是否生效  商品类型  商品打折信息   ？？？  未完成  页面只是展示  没有传数据
	 */
	@Action(value = "save", results = { @Result(name = "success", type = "redirect", location = "/ProductWordsCard/list.action") })
	public String save() {
		String ida= getRequest().getParameter("id");
		String updater = getLoginUserName();
		if(ida != null){
			ProductWordsCard productWordsCard = productWordsManager.getById(Integer.parseInt(ida));
			if(null !=productWordsCard) {
				String productWordsBaseUpdate = getRequest().getParameter("productWordsBase");

				productWordsCard.setProductWordsBase(productWordsBaseUpdate);
				productWordsCard.setIsEditor(EDITOR);
				productWordsCard.setUpdateDate(new Date());
				productWordsCard.setModifier(updater);
				productWordsManager.update(productWordsCard);
				return "success";
			}
		}
		return "fail";
	}




	//批量导入页面
	@Action(value = "log", results = { @Result(name = "success", location = "/jsp/productwordscard/importFile.jsp") } )
	public String logList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", page_index);
		paramMap.put("pageSize", page_size);
		listImportLogs = productWordsManager.listLog(paramMap);
		return "success";
	}



	/**
	 * @param
	 * @desc 生成未成功导入商品专题的excel文件
	 */
	@Action(value = "errorwords")
	public void generateFailDataFile() {
		String logid = getStringParam("logid");
		logger.info("该日志的id是"+logid);
		String creator = getStringParam("creator");
		logger.info("该日志的创建者是"+creator);
		if(!isEmpty(creator)){
			creator = StringUtil.urlDecode(creator, "utf-8");
			creator = StringUtil.urlDecode(creator, "utf-8");
			creator = getPinyinLowercase(creator);
		}else {
			creator ="admin";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		String format = formatter.format(new Date());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logId", logid);
		List<ProductWordsErrorCard> productWordsErrorCardList = productWordsManager.listDownLog(paramMap);
		if (productWordsErrorCardList != null) {
			try {
				String randomChar = getRandomChar(6);
				String fileName = creator + format + randomChar + ".xls";
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName);
				OutputStream os = getResponse().getOutputStream();

				//创建HSSFWorkbook对象(excel的文档对象)
				HSSFWorkbook wb = new HSSFWorkbook();
				//建立新的sheet对象（excel的表单）
				HSSFSheet sheet=wb.createSheet("错误信息");

				//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
				HSSFRow row1=sheet.createRow(0);
				//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
				HSSFCell cell=row1.createCell(0);
				//设置单元格内容
				cell.setCellValue("错误信息");
				//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));

				//在sheet里创建第二行
				HSSFRow row2=sheet.createRow(1);
				//创建单元格并设置单元格内容
				row2.createCell(0).setCellValue("商品词ID");
				row2.createCell(1).setCellValue("商品词词根");
				row2.createCell(2).setCellValue("商品词词名");
				row2.createCell(3).setCellValue("错误原因");

				logger.info("该日志的错误数量是"+productWordsErrorCardList.size());
				for (int i = 0; i < productWordsErrorCardList.size(); i++) {
					ProductWordsErrorCard productWordsError = productWordsErrorCardList.get(i);

					//在sheet里创建第二行
					HSSFRow rowX=sheet.createRow(i+2);
					//创建单元格并设置单元格内容
					rowX.createCell(0).setCellValue(productWordsError.getId());
					rowX.createCell(1).setCellValue(productWordsError.getProductWordsBase());
					rowX.createCell(2).setCellValue(productWordsError.getProductWordsName());
					String errorString = "";
					if(productWordsError.getType() == 1){
						errorString = "商品词重复";
					}
					rowX.createCell(3).setCellValue(errorString);
				}
				wb.write(os);
				getResponse().flushBuffer();
				os.flush();
				wb.close();
				os.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}



	/**
	 * @param
	 * @desc 根据begin和end条件生成商品专题的excel文件
	 */
	@Action(value = "out")
	public void generateDataFile() {

		logger.info("HotKeyword out start!");
		Integer minId = this.getIntParam("minId");
		Integer maxId = this.getIntParam("maxId");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (minId != null && maxId != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("minId", minId);
			paramMap.put("maxId", maxId);
			List<ProductWordsCard> productWordsCardsTemp = productWordsManager.getByRangeId(paramMap);
			if (productWordsCardsTemp != null) {
				try {
					String fileName = "productwords_"
							+ String.format("%tY-%<tm-%<td_%<tH_%<tM_%<tS", System.currentTimeMillis()) + ".xlsx";
					getResponse().setContentType("application/vnd.ms-excel");
					getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName);
					OutputStream os = getResponse().getOutputStream();


					XSSFWorkbook wb = new XSSFWorkbook();
					XSSFSheet sheet = wb.createSheet("商品词信息");
					XSSFRow row = sheet.createRow(0);
					XSSFCellStyle style = wb.createCellStyle();
					style.setFillForegroundColor(HSSFColor.RED.index);
					XSSFFont font = wb.createFont();
					font.setColor(IndexedColors.RED.getIndex());
					//设置字体
					font.setFontName("宋体");
					style.setFont(font);
					row.createCell(0).setCellValue("商品词ID");
					row.createCell(1).setCellValue("商品词词根");
					row.createCell(2).setCellValue("商品词词名");
					row.createCell(3).setCellValue("添加/修改时间");
					row.createCell(4).setCellValue("创建者/修改者");
					row.createCell(5).setCellValue("是否发布");
					row.createCell(6).setCellValue("是否有效");
					for (int i = 0; i < productWordsCardsTemp.size(); i++) {
						ProductWordsCard productWords = productWordsCardsTemp.get(i);
						XSSFRow rowData = sheet.createRow(i+1);
						rowData.createCell(0).setCellValue(productWords.getId());
						rowData.createCell(1).setCellValue(productWords.getProductWordsBase());
						rowData.createCell(2).setCellValue(productWords.getProductWordsName());

						String createTimes = formatter.format(productWords.getCreateDate());
						String updateTimes = null;
						if(productWords.getUpdateDate()!=null){
							updateTimes = formatter.format(productWords.getUpdateDate());
						}
						rowData.createCell(3).setCellValue(createTimes+"/"+updateTimes);
						rowData.createCell(4).setCellValue(productWords.getCreator()+"/"+productWords.getModifier());

						String pubStr = productWords.getIsInvalid() == 1 ? "已发布" : "未发布";
						rowData.createCell(5).setCellValue(pubStr);
						XSSFCell EnableCell = rowData.createCell(6);
						String enaStr = productWords.getIsEnable() == 1 ? "有效" : "无效";
						if(productWords.getIsEnable()!=1){
							EnableCell.setCellStyle(style);
						}
						EnableCell.setCellValue(enaStr);

					}
					wb.write(os);
					getResponse().flushBuffer();
					wb.close();
					os.flush();
					os.close();
				} catch (IOException e) {
					logger.error("productWordsCard out IOException异常：" + e.getMessage(),e);
				}
			}
		}
		logger.info("HotKeyword out end!");

	}





	/**
	 * @param
	 * @desc 生成未成功导入商品专题的excel文件
	 */
	@Action(value = "download")
	public void downloadLocal() {
		// 下载本地文件
		String fileName = "productwords.xlsx"; // 文件的默认保存名 /app/coo8_html/gome_html/anchorwords_upload/anchorword/
		//productWordsCardUrlDir是配置在diamond服务器上
		String urlDir = PropertiesUtils.getStringValueByKey("productWordsCardUrlDir","/app/coo8_html/gome_html/anchorwords_upload/productwords/");
		// 读到流中            /app/coo8_html/gome_html/map/productwords/
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(urlDir += fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}// 文件的存放路径
		// 设置输出的格式
		getResponse().reset();
		getResponse().setContentType("bin");
		getResponse().addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				getResponse().getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	/**
	 * @desc 导入商品专题操作
	 * @return
	 * @throws ParseException
	 */
	@Action(value = "import")
	public void importPromotionCard123() throws ParseException {
//		HttpSession session = ServletActionContext.getRequest().getSession();
		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
		String creator = getLoginUserName();
		logger.info(creator);
		String createTime = String.format("%tF %<tT", System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		String format = formatter.format(new Date());
		if(StringUtils.isBlank(fileType)) {
			fileType = "";
		}
		String filePath = configration + format + "_" + "productwords" + fileType;
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(words, destFile);
			List<String[]> readExcel = ExcelPoiUtils.readExcel(destFile);
			logger.info("excel读取条数为"+readExcel.size());
			List<ProductWordsCard> productWordsCards = new ArrayList<ProductWordsCard>();
			List<ProductWordsErrorCard> productWordsErrorCards = new ArrayList<ProductWordsErrorCard>();
			ProductWordsCardImportLog importLog = new ProductWordsCardImportLog();
			//存放26个大写英文字母
			List<String> letterList = new ArrayList<String>();
			for (int j = 0; j < 26; j++) {
				char c = (char)(65+j);
				String str = String.valueOf(c);
				letterList.add(str.toUpperCase());
			}
			for (int i = 0; i < readExcel.size(); i++) {
				Date date = new java.util.Date();
				String[] strings = readExcel.get(i);
				List<String> strs = new ArrayList<String>();
				for (int j = 0; j < 2; j++) {
					String contents = strings[j];
					strs.add(contents);
				}
				String productWordsBase = strs.get(0).replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");//至少匹配汉字数字字母
				String productWordsName = strs.get(1).replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");//至少匹配汉字数字字母
				if(StringUtils.isBlank(productWordsBase)||StringUtils.isBlank(productWordsName)){
					continue;
				}
				logger.info("productWordsCard import productWordsBase" + productWordsBase + ",productWordsName" + productWordsName);
				//校验商品词词名是否重复
				int type = 0; //上传数据是否正确判断位，0  正确、1  商品词重复
				ProductWordsCard productWord1 = productWordsManager.getByProductWordsName(productWordsName);
				if (productWord1 == null) {
					ProductWordsCard productWordsCardTemp = new ProductWordsCard();
					productWordsCardTemp.setProductWordsBase(productWordsBase);
					productWordsCardTemp.setProductWordsName(productWordsName);
					productWordsCardTemp.setCreator(creator);
					productWordsCardTemp.setCreateDate(date);
					productWordsCardTemp.setIsEditor(NO_EDITOR);
					productWordsCardTemp.setIsInvalid(NO_INVALID);//设置有效
					productWordsCardTemp.setIsDelete(NO_DELETE);
					String substring = Chinese2PinyinUtil.parseChinese(productWordsName).toUpperCase().substring(0, 1);
					if(letterList.contains(substring)){
						productWordsCardTemp.setBeginLetter(substring);
					}else {
						productWordsCardTemp.setBeginLetter("0-9");
					}
					productWordsManager.save(productWordsCardTemp);
					productWordsCards.add(productWordsCardTemp);
				} else {
					ProductWordsErrorCard errorCard = new ProductWordsErrorCard();
					errorCard.setProductWordsBase(productWordsBase);
					errorCard.setProductWordsName(productWordsName);
					type = 1;
					errorCard.setType(type);
					errorCard.setCreateTime(date);
					errorCard.setCreator(creator);
					productWordsErrorCards.add(errorCard);
				}
			}
			importLog.setCreator(creator);
			importLog.setTotalCount(productWordsCards.size() + productWordsErrorCards.size());
			importLog.setFailCount(productWordsErrorCards.size());
			importLog.setCreateTime(createTime);
			Integer  importLogId = productWordsManager.addLog(importLog); // 生成日志数据
			if (!productWordsErrorCards.isEmpty()) {
				productWordsManager.addError(productWordsErrorCards,importLogId); // 添加失败数据
			}
//			session.setAttribute("flag",true);

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			String s = JSON.toJSONString("处理完成，请刷新页面查看上传结果");
			PrintWriter writer = response.getWriter();
			writer.println(s);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}




/*
//	@Action(value = "import", results = {
//			@Result(name = "success", type = "redirect", location = "/ProductWordsCard/log.action") })
	@Action(value = "import")
	public void importPromotionCard123() throws IOException {
		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
		String creator = getLoginUserName();
		String createTime = String.format("%tF %<tT", System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		String format = formatter.format(new Date());
		if(StringUtils.isBlank(fileType)) {
			fileType = "";
		}
		String filePath = configration + format + "_" + "productwords" + fileType;
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(words, destFile);
			List<String[]> readExcel = ExcelPoiUtils.readExcel(destFile);
			logger.info("excel读取条数为" + readExcel.size());
			List<ProductWordsCard> productWordsCards = new ArrayList<ProductWordsCard>();
			List<ProductWordsErrorCard> productWordsErrorCards = new ArrayList<ProductWordsErrorCard>();
			ProductWordsCardImportLog importLog = new ProductWordsCardImportLog();
			//存放26个大写英文字母
			List<String> letterList = new ArrayList<String>();
			for (int j = 0; j < 26; j++) {
				char c = (char) (65 + j);
				String str = String.valueOf(c);
				letterList.add(str.toUpperCase());
			}
			for (int i = 0; i < readExcel.size(); i++) {
				Date date = new java.util.Date();
				String[] strings = readExcel.get(i);
				List<String> strs = new ArrayList<String>();
				for (int j = 0; j < 2; j++) {
					String contents = strings[j];
					strs.add(contents);
				}
				String productWordsBase = strs.get(0).replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");//至少匹配汉字数字字母
				String productWordsName = strs.get(1).replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");//至少匹配汉字数字字母
				if (StringUtils.isBlank(productWordsBase) || StringUtils.isBlank(productWordsName)) {
					continue;
				}
				logger.info("productWordsCard import productWordsBase" + productWordsBase + ",productWordsName" + productWordsName);
				//校验商品词词名是否重复
				int type = 0; //上传数据是否正确判断位，0  正确、1  商品词重复
				ProductWordsCard productWord1 = productWordsManager.getByProductWordsName(productWordsName);
				if (productWord1 == null) {
					ProductWordsCard productWordsCardTemp = new ProductWordsCard();
					productWordsCardTemp.setProductWordsBase(productWordsBase);
					productWordsCardTemp.setProductWordsName(productWordsName);
					productWordsCardTemp.setCreator(creator);
					productWordsCardTemp.setCreateDate(date);
					productWordsCardTemp.setIsEditor(NO_EDITOR);
					productWordsCardTemp.setIsInvalid(NO_INVALID);//设置有效
					productWordsCardTemp.setIsDelete(NO_DELETE);
					String substring = Chinese2PinyinUtil.parseChinese(productWordsName).toUpperCase().substring(0, 1);
					if (letterList.contains(substring)) {
						productWordsCardTemp.setBeginLetter(substring);
					} else {
						productWordsCardTemp.setBeginLetter("0-9");
					}
					productWordsManager.save(productWordsCardTemp);
					productWordsCards.add(productWordsCardTemp);
				} else {
					ProductWordsErrorCard errorCard = new ProductWordsErrorCard();
					errorCard.setProductWordsBase(productWordsBase);
					errorCard.setProductWordsName(productWordsName);
					type = 1;
					errorCard.setType(type);
					errorCard.setCreateTime(date);
					errorCard.setCreator(creator);
					productWordsErrorCards.add(errorCard);
				}
			}
			importLog.setCreator(creator);
			importLog.setTotalCount(productWordsCards.size() + productWordsErrorCards.size());
			importLog.setFailCount(productWordsErrorCards.size());
			importLog.setCreateTime(createTime);
			Integer importLogId = productWordsManager.addLog(importLog); // 生成日志数据
			if (!productWordsErrorCards.isEmpty()) {
				productWordsManager.addError(productWordsErrorCards, importLogId); // 添加失败数据
			}
		} catch (IOException e) {
			logger.info("Excel文件转存时发生异常： "+e.getMessage());
		}



	}*/









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

	public static String getRandomChar(int length) {            //生成随机字符串
		char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(chr[random.nextInt(36)]);
		}
		return buffer.toString();
	}


	/**
	 * @desc 获取小写的英文拼音
	 * @return
	 */
	private String getPinyinLowercase(String chineseStr) {
		String result = Chinese2PinyinUtil.parseChinese(chineseStr);
		return result.toLowerCase();
	}

	private boolean getIsChange(String origin, String update) {
		if(StringUtils.isNotBlank(origin)) {
			return !origin.equals(update);
		}else if(StringUtils.isNotBlank(update))
			return true;
		return false;
	}


	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getPage_index() {
		return page_index;
	}

	public void setPage_index(Integer page_index) {
		this.page_index = page_index;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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


	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Integer getIsEditor() {
		return isEditor;
	}

	public void setIsEditor(Integer isEditor) {
		this.isEditor = isEditor;
	}

	public PaginatedList<ProductWordsCardImportLog> getListImportLogs() {
		return listImportLogs;
	}

	public void setListImportLogs(PaginatedList<ProductWordsCardImportLog> listImportLogs) {
		this.listImportLogs = listImportLogs;
	}

	public PaginatedList<ProductWordsCard> getProductWordsCardlist() {
		return productWordsCardList;
	}


	public Integer getIsInvalid() {
		return isInvalid;
	}

	public void setIsInvalid(Integer isInvalid) {
		this.isInvalid = isInvalid;
	}



	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getProductWordsName() {
		return productWordsName;
	}

	public void setProductWordsName(String productWordsName) {
		this.productWordsName = productWordsName;
	}

	public String getProductWordsBase() {
		return productWordsBase;
	}

	public void setProductWordsBase(String productWordsBase) {
		this.productWordsBase = productWordsBase;
	}



	public PaginatedList<ProductWordsCard> getProductWordsCardList() {
		return productWordsCardList;
	}

	public void setProductWordsCardList(PaginatedList<ProductWordsCard> productWordsCardList) {
		this.productWordsCardList = productWordsCardList;
	}

	public ProductWordsManager getProductWordsManager() {
		return productWordsManager;
	}

	public void setProductWordsManager(ProductWordsManager productWordsManager) {
		this.productWordsManager = productWordsManager;
	}

	public ProductWordsCard getProductWordsCardForValueStack() {
		return productWordsCardForValueStack;
	}

	public void setProductWordsCardForValueStack(ProductWordsCard productWordsCardForValueStack) {
		this.productWordsCardForValueStack = productWordsCardForValueStack;
	}

	public String getQcreateTime() {
		return qcreateTime;
	}

	public void setQcreateTime(String qcreateTime) {
		this.qcreateTime = qcreateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public Integer getIsEnableCount() {
		return isEnableCount;
	}

	public void setIsEnableCount(Integer isEnableCount) {
		this.isEnableCount = isEnableCount;
	}

	public Integer getNotEnableCount() {
		return notEnableCount;
	}

	public void setNotEnableCount(Integer notEnableCount) {
		this.notEnableCount = notEnableCount;
	}
}





