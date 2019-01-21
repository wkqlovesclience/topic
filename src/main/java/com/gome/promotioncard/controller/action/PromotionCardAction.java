package com.gome.promotioncard.controller.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.gome.utils.ExcelPoiUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.common.utils.StringUtil;
import com.coo8.topic.business.interfaces.ITitlesManager;
import com.coo8.topic.controller.action.BaseAction;
import com.coo8.topic.model.ImportLog;
import com.gome.promotioncard.manager.inter.IPromotionCardManager;
import com.gome.promotioncard.model.ErrorCard;
import com.gome.promotioncard.model.PromotionCard;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

@Namespace("/PromotionCard")
public class PromotionCardAction extends BaseAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8812797487670265999L;
	private static Logger logger = LoggerFactory.getLogger(PromotionCardAction.class);
	private Integer page_index=1;
	private Integer page_size=10;
	private static final Integer DELETE = 1;
	private static final Integer NO_DELETE = 0;
	private static final Integer EDITOR = 1;
	private static final Integer NO_EDITOR = 0;	
	protected PaginatedList<ImportLog> listImportLogs;
	protected PaginatedList<PromotionCard> promotionCardList;
	protected String id;
	protected java.lang.Integer isEditor = 3;
	protected String tags;
	protected com.gome.bg.interfaces.system.bean.PromotionCard baiduCard;
	private File words;
	private String wordsFileName;
	private IPromotionCardManager promotionCardManager;
	private ITitlesManager titlesManager;
	//编辑
	protected java.lang.String cardId;
	protected java.lang.String productId;
	protected java.lang.String skuId;
	protected java.lang.String cardContent = "";


	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/baiducard/list.jsp") })
	public String list() {
		logger.info("baiduCard list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if(null != id){search.put("productId", id);}
		if(isEditor != 3)	{		
		search.put("isEditor", isEditor);	
		search.put("isDelete", NO_DELETE);	
		}
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		logger.info("baiduCard list 查询数据参数：" + search);
		promotionCardList = promotionCardManager.findLikeByMap(search);		
		logger.info("baiduCard list end!");
		return "success";
	}
	
	@Action(value = "delete", 
			 results = { @Result(name = "success", type = "redirect", location = "/PromotionCard/list.action") })
	public String delete() {
		logger.info("promotionCard delete start!");
		if (tags != null && !"".equals(tags)) {
			Set<Integer> hs = this.spli(tags, ";");
			for (Integer tid : hs) {
			PromotionCard promotionCard = promotionCardManager.getById(tid);
			if(promotionCard != null){
				promotionCard.setIsDelete(DELETE);
				promotionCard.setUpdateDate(new java.util.Date());
				promotionCard.setModifier(getLoginUserName());
				promotionCardManager.update(promotionCard);
			}
			}
		}
		logger.info("promotionCard delete end!");
		return "success";
	}
	
	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/baiducard/create.jsp") })
	public String edit() {
		logger.info("promotionCard edit start!");
		PromotionCard promotionCard = promotionCardManager.getById(Integer.parseInt(id));
		cardId = id;
		skuId = promotionCard.getSkuId();
		productId = promotionCard.getProductId();
		cardContent = promotionCard.getContent();
		if(promotionCard.getIsEditor().equals(NO_EDITOR)) {
			cardContent = "";
		}
        baiduCard = promotionCardManager.getAllDataById(productId, skuId);
        if(baiduCard == null || StringUtils.isBlank(baiduCard.getContent())) {
            logger.info("++++++++++++++++id为"+cardId+"的商品库商品为空++++++++++");
        }
		return "success";
	}
	
	
	@Action(value = "save", results = { @Result(name = "success", type = "redirect", location = "/PromotionCard/list.action") })
	public String save() {
		String ida= getRequest().getParameter("id");
		String content = getRequest().getParameter("contents");
		String baiduCardContent = getRequest().getParameter("baiduCardContent");
		if(ida != null && content != null){
		PromotionCard promotionCard = promotionCardManager.getById(Integer.parseInt(ida)); 
		if(!isEmptyOrNot(content)){
        	promotionCard.setIsEditor(EDITOR);
			promotionCard.setContent(content);
        }else{
        	promotionCard.setIsEditor(NO_EDITOR);
        	promotionCard.setContent(baiduCardContent);
        }	
	        promotionCard.setModifier(getLoginUserName());
	        promotionCard.setUpdateDate(new java.util.Date());
	        promotionCardManager.update(promotionCard);
			logger.info("promotionCard edit end!");
		
		return "success";
		}else
			return "fail";
	}
	/**
	 * @desc 获取小写的英文拼音
	 * @return
	 */
	private String getPinyinLowercase(String chineseStr) {
		String result = Chinese2PinyinUtil.parseChinese(chineseStr);
		return result.toLowerCase();
	}
	
	@Action(value = "log", results = { @Result(name = "success", location = "/jsp/baiducard/importFile.jsp") })
	public String logList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", page_index);
		paramMap.put("pageSize", page_size);
		listImportLogs = promotionCardManager.listLog(paramMap);
		return "success";
	}
	
	/**
	 * @param
	 * @desc 生成未成功导入商品专题的excel文件
	 */
	@Action(value = "errorwords")
	public void generateFailDataFile() {
		String logid = getStringParam("logid");
		String uploader = getStringParam("uploader");
		logger.info("logid为"+logid+"++++===="+uploader);
		if(!isEmpty(uploader)){
			uploader = StringUtil.urlDecode(uploader, "utf-8");
			uploader = StringUtil.urlDecode(uploader, "utf-8");
			uploader = getPinyinLowercase(uploader);
		}else {
			uploader ="admin";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		String format = formatter.format(new Date());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logId", logid);
		List<ErrorCard> errorCards = promotionCardManager.listDownLog(paramMap);
		if (errorCards != null) {
			try {
				String randomChar = getRandomChar(6);
				String fileName = uploader + format + randomChar +".xls";
				getResponse().setContentType("application/vnd.ms-excel");
				getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName);
				OutputStream os = getResponse().getOutputStream();

				WritableWorkbook workBook = Workbook.createWorkbook(os);

				WritableSheet sheet = workBook.createSheet("sheet1", 0);
			
				Label one = new Label(0, 0, "商品ID");
				Label two = new Label(1, 0, "skuId");				
				Label three = new Label(2, 0, "错误原因");	
				sheet.addCell(one);
				sheet.addCell(two);
				sheet.addCell(three);								
				for (int i = 0; i < errorCards.size(); i++) {
					ErrorCard errorCard = errorCards.get(i);

					WritableCellFormat errorFormat = new WritableCellFormat();
					errorFormat.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.RED);
					errorFormat.setBackground(jxl.format.Colour.GRAY_25);								
					Label ProductId = new Label(0, i + 1, errorCard.getProductId());
					Label skuIda = new Label(1, i + 1, errorCard.getSkuId());
				String errorString = "无效的商品ID";
				 if(errorCard.getType() == 2){
					 errorString = "skuId与商品ID不匹配";					
				 }else if(errorCard.getType() == 3){
					 errorString = "上传信息已存在";	
				 }	
				 Label errormeg = new Label(2, i + 1, errorString);	
					sheet.addCell(ProductId);
					sheet.addCell(skuIda);
					sheet.addCell(errormeg);
				}
				workBook.write();
				getResponse().flushBuffer();
				workBook.close();
				os.flush();
				os.close();
			} catch (IOException|RowsExceededException e) {
				logger.error(e.getMessage(), e);
			}  catch (WriteException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @desc 导入商品专题操作
	 * @return
	 * @throws ParseException
	 */
	@Action(value = "import", results = {
			@Result(name = "success", type = "redirect", location = "/PromotionCard/log.action") })
	public String importPromotionCard() throws ParseException {
		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
		String createTime = String.format("%tF %<tT", System.currentTimeMillis());
		String lowercaseName = getPinyinLowercase(getLoginUserName());
		lowercaseName = "test";
		String uploadTime = createTime.replaceAll(":", "-");
		uploadTime = uploadTime.replaceAll(" ", "_");
		String filePath = configration + uploadTime + "_" + lowercaseName + wordsFileName.substring(wordsFileName.lastIndexOf(".") + 1);
		File destFile = new File(filePath);      
		try {
			FileUtils.copyFile(words, destFile);
			List<String[]> readExcel = ExcelPoiUtils.readExcel(destFile);
			logger.info("excel读取条数为"+readExcel.size());
			List<PromotionCard> PromotionCards = new ArrayList<PromotionCard>();
			List<ErrorCard> ErrorCards = new ArrayList<ErrorCard>();
			ImportLog importLog = new ImportLog();
			for (int i = 0; i < readExcel.size(); i++) {
				Date date = new java.util.Date();
				String[] strings = readExcel.get(i);
				List<String> strs = new ArrayList<String>();
				for(int j = 0; j < 2; j++){
					String contents = strings[j];
					strs.add(contents);
				}
				String productIda = strs.get(0).replaceAll("[^0-9a-zA-Z]", "");
				String skuIda = strs.get(1).replaceAll("[^0-9a-zA-Z]", "");
				logger.info("baiDuCard import,productIda"+productIda+",skuIda"+skuIda);
				//校验商品id
				int type = 0; //上传数据是否正确判断位，0  正确、1  商品Id错误、2 skuId错误。
				if(productIda.isEmpty()){
					type = 1;
				}else{
					String productName = titlesManager.checkItemName(productIda);   //判断商品Id是否有效
					if (productName.isEmpty()){
						type = 1;
					}else if(!promotionCardManager.checkSkuId(productIda,skuIda)){
						type = 2;
					}
				}
				if(type == 0 && promotionCardManager.getByskuId(skuIda) != null) {
					logger.info("skuid为" + skuIda + "的商品已存在");
					type = 3;
				}
				if (type != 0) {		//上传数据错误加入错误列表
					ErrorCard errorCard = new ErrorCard();
					errorCard.setProductId(productIda);
					errorCard.setSkuId(skuIda);
					errorCard.setType(type);
					errorCard.setCreateTime(new java.util.Date());
					errorCard.setCreator(getLoginUserName());
					ErrorCards.add(errorCard);
				} else {
					PromotionCard promotionCard = new PromotionCard();
						promotionCard.setProductId(productIda);
						promotionCard.setSkuId(skuIda);
						promotionCard.setCreateDate(new java.util.Date());
						promotionCard.setUpdateDate(new java.util.Date());
						promotionCard.setCreator(getLoginUserName());
						promotionCard.setIsDelete(NO_DELETE);
						promotionCard.setIsEditor(NO_EDITOR);
						String content = promotionCardManager.getAllDataById(productIda, skuIda).getContent();
						promotionCard.setContent(content);
						promotionCardManager.save(promotionCard);
					PromotionCards.add(promotionCard);
				}
			}
			importLog.setCreator(getLoginUserName());
			importLog.setTotalCount(PromotionCards.size() + ErrorCards.size());
			importLog.setFailCount(ErrorCards.size());
			importLog.setCreateTime(createTime);
			importLog.setFileUrl("./errorwords.action?date=" + createTime);
			String logId = promotionCardManager.addLog(importLog); // 生成日志数据
			if (!ErrorCards.isEmpty()) {
				promotionCardManager.addError(ErrorCards,logId); // 添加失败数据
			}
			} catch (IOException e1) {
			e1.printStackTrace();
		}
		return SUCCESS;
	}



//	@Action(value = "import", results = {
//			@Result(name = "success", type = "redirect", location = "/PromotionCard/log.action") })
//	public String importPromotionCard() throws ParseException {
//		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
//		String createTime = String.format("%tF %<tT", System.currentTimeMillis());
//
//		String lowercaseName = getPinyinLowercase(getLoginUserName());
//		lowercaseName = "test";
//		String uploadTime = createTime.replaceAll(":", "-");
//		uploadTime = uploadTime.replaceAll(" ", "_");
//		String filePath = configration + uploadTime + "_" + lowercaseName + ".xls";
//		File destFile = new File(filePath);
//		try {
//			FileUtils.copyFile(words, destFile);
//
//			List<PromotionCard> PromotionCards = new ArrayList<PromotionCard>();
//			List<ErrorCard> ErrorCards = new ArrayList<ErrorCard>();
//
//			Workbook book = null;
//			ImportLog importLog = new ImportLog();
//			try {
//				book = Workbook.getWorkbook(destFile);
//				Sheet sheet = book.getSheet(0);
//				int rowCount = sheet.getRows();
//				for (int i = 0; i < rowCount; i++) {
//					List<String> strs = new ArrayList<String>();
//					for(int j = 0; j < 2; j++){
//						Cell cell = sheet.getCell(j, i);
//						String contents = cell.getContents();
//						strs.add(contents);
//					}
//					String productIda = strs.get(0);
//					String skuIda = strs.get(1);
//					//校验商品id
//					int type = 0; //上传数据是否正确判断位，0  正确、1  商品Id错误、2 skuId错误。
//					if(productIda.isEmpty()){
//						type = 1;
//					}else{
//						String productName = titlesManager.checkItemName(productIda);   //判断商品Id是否有效
//						if (productName.isEmpty()){
//							type = 1;
//						}else if(!promotionCardManager.checkSkuId(productIda,skuIda)){
//							type = 2;
//						}
//					}
//
//					if(type == 0 && promotionCardManager.getByskuId(skuIda) != null){
//						logger.info("skuid为"+skuIda+"的商品已存在");
//						type = 3;
//					}
//
//					if (type != 0) {		//上传数据错误加入错误列表
//						ErrorCard errorCard = new ErrorCard();
//						errorCard.setProductId(productIda);
//						errorCard.setSkuId(skuIda);
//						errorCard.setType(type);
//						errorCard.setCreateTime(new java.util.Date());
//						errorCard.setCreator(getLoginUserName());
//						ErrorCards.add(errorCard);
//					} else {
//						PromotionCard promotionCard = new PromotionCard();
//						promotionCard.setProductId(productIda);
//						promotionCard.setSkuId(skuIda);
//						promotionCard.setCreateDate(new java.util.Date());
//						promotionCard.setUpdateDate(new java.util.Date());
//						promotionCard.setCreator(getLoginUserName());
//						promotionCard.setIsDelete(NO_DELETE);
//						promotionCard.setIsEditor(NO_EDITOR);
//						String content = promotionCardManager.getAllDataById(productIda, skuIda).getContent();
//						promotionCard.setContent(content);
//						promotionCardManager.save(promotionCard);
//						PromotionCards.add(promotionCard);
//					}
//				}
//				importLog.setCreator(getLoginUserName());
//				importLog.setTotalCount(PromotionCards.size() + ErrorCards.size());
//				importLog.setFailCount(ErrorCards.size());
//				importLog.setCreateTime(createTime);
//				importLog.setFileUrl("./errorwords.action?date=" + createTime);
//				String logId = promotionCardManager.addLog(importLog); // 生成日志数据
//				if (!ErrorCards.isEmpty()) {
//					promotionCardManager.addError(ErrorCards,logId); // 添加失败数据
//				}
//			} catch (BiffException e) {
//				logger.error(e.getMessage(), e);
//			} finally {
//				if (book != null) {
//					book.close();
//				}
//			}
//
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		}
//		return SUCCESS;
//	}

	
	public boolean isEmptyOrNot(String param) {
		if (param == null || "".equals(param) || "".equals(param.trim())) {
			return true;
		}
		return false;
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
	
	
	public java.lang.String getTags() {
		return tags;
	}

	public void setTags(java.lang.String tags) {
		this.tags = tags;
	}

	public Integer getPage_index() {
		return page_index;
	}

	public void setPage_index(Integer page_index) {
		this.page_index = page_index;
	}
	
	

	public PaginatedList<PromotionCard> getPromotionCardList() {
		return promotionCardList;
	}

	public void setPromotionCardList(PaginatedList<PromotionCard> promotionCardList) {
		this.promotionCardList = promotionCardList;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public PaginatedList<ImportLog> getListImportLogs() {
		return listImportLogs;
	}

	public void setListImportLogs(PaginatedList<ImportLog> listImportLogs) {
		this.listImportLogs = listImportLogs;
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

	public IPromotionCardManager getPromotionCardManager() {
		return promotionCardManager;
	}

	public void setPromotionCardManager(IPromotionCardManager promotionCardManager) {
		this.promotionCardManager = promotionCardManager;
	}

	public ITitlesManager getTitlesManager() {
		return titlesManager;
	}

	public void setTitlesManager(ITitlesManager titlesManager) {
		this.titlesManager = titlesManager;
	}

	public java.lang.String getProductId() {
		return productId;
	}

	public void setProductId(java.lang.String productId) {
		this.productId = productId;
	}

	public java.lang.String getSkuId() {
		return skuId;
	}

	public void setSkuId(java.lang.String skuId) {
		this.skuId = skuId;
	}

	public java.lang.String getCardContent() {
		return cardContent;
	}

	public void setCardContent(java.lang.String cardContent) {
		this.cardContent = cardContent;
	}

	public com.gome.bg.interfaces.system.bean.PromotionCard getBaiduCard() {
		return baiduCard;
	}

	public void setBaiduCard(com.gome.bg.interfaces.system.bean.PromotionCard baiduCard) {
		this.baiduCard = baiduCard;
	}

	public java.lang.String getCardId() {
		return cardId;
	}

	public void setCardId(java.lang.String cardId) {
		this.cardId = cardId;
	}

	public java.lang.Integer getIsEditor() {
		return isEditor;
	}

	public void setIsEditor(java.lang.Integer isEditor) {
		this.isEditor = isEditor;
	}

}
