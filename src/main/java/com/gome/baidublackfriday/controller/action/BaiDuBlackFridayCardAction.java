package com.gome.baidublackfriday.controller.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.common.utils.StringUtil;
import com.coo8.topic.business.interfaces.ITitlesManager;
import com.coo8.topic.controller.action.BaseAction;
import com.gome.baidublackfriday.manager.infer.BaiDuBlackFridayManager;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCard;
import com.gome.baidublackfriday.model.BaiDuBlackFridayErrorCard;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCardImportLog;
import com.gome.erm.gomecxf.NEW;
import com.gome.promotioncard.model.PromotionCard;
import com.gome.stage.item.PriceInfo;
import com.gome.utils.ExcelPoiUtils;


/**
 * @author JIANGCHENG QRX
 * @version 1.0
 * @since 1.0
 */

@Namespace("/BaiDuBlackFridayCard")
public class BaiDuBlackFridayCardAction extends BaseAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8812797487670265999L;
	private static Logger logger = LoggerFactory.getLogger(BaiDuBlackFridayCardAction.class);
	private Integer page_index=1;
	private Integer page_size=10;
	private static final Integer DELETE = 1;
	private static final Integer NO_DELETE = 0;
	private static final Integer EDITOR = 1;
	private static final Integer NO_EDITOR = 0;	
	private static final Integer INVALID = 1;
	private static final Integer NO_INVALID = 0;
	protected PaginatedList<BaiDuBlackFridayCardImportLog> listImportLogs;
	protected PaginatedList<PromotionCard> promotionCardList;
	protected PaginatedList<BaiDuBlackFridayCard> baiDuBlackFridayCardlist;
	protected String id;
	protected java.lang.Integer isEditor = 3;
	protected java.lang.Integer isInvalid = 3;
	

	protected String tags;
	protected com.gome.bg.interfaces.system.bean.PromotionCard baiduCard;
	protected PriceInfo priceInfo;
	private File words;
	private String wordsFileName;
	private BaiDuBlackFridayManager baiDuBlackFridayManager;
	private ITitlesManager titlesManager;
	private final String PRODUCT_TYPE ="数码、电器、服装、箱包、美妆、轻奢、运动、图书、其他";
	private final String TAG ="优品直降、限时秒杀、五折优惠、0元抢购、买赠专区、满减专区";
	//编辑
	protected String cardId;
	protected String productId;
	protected String skuId;
	protected String productType;
	protected String tag;
	protected String originPrice;
	protected String salePrice;
	protected Integer createNum;
	protected String customUrl;
	protected String cardContent = "";
	protected String fileType;

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/baidublackfridaycard/list.jsp") })
	public String list() {
		logger.info("baidublackfridaycard list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if(null != id){search.put("skuId", id);}
		if(isEditor != 3)	{		
		search.put("isEditor", isEditor);	
		}
		if(isInvalid != 3)	{		
			search.put("isInvalid", isInvalid);	
		}
		search.put("isDelete", NO_DELETE);
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		logger.info("baiduCard list 查询数据参数：" + search);
		baiDuBlackFridayCardlist = baiDuBlackFridayManager.findLikeByMap(search);		
		logger.info("baidublackfridaycard list end!123");
		return "success";
	}
	
	@Action(value = "delete", 
			 results = { @Result(name = "success", type = "redirect", location = "/BaiDuBlackFridayCard/list.action") })
	public String delete() {
		logger.info("baidublackfridaycard delete start!");
		if (!StringUtils.isBlank(tags)) {
			Set<Integer> hs = this.spli(tags, ";");
			//批量删除
			for (Integer tid : hs) {
				BaiDuBlackFridayCard baiDuBlackFridayCard = baiDuBlackFridayManager.getById(tid);
				if(baiDuBlackFridayCard != null){
					baiDuBlackFridayCard.setIsDelete(DELETE);
					baiDuBlackFridayCard.setIsInvalid(INVALID);
					baiDuBlackFridayCard.setUpdateDate(new Date());
					baiDuBlackFridayCard.setModifier(getLoginUserName());
					baiDuBlackFridayManager.update(baiDuBlackFridayCard);
				}
			}
		}
		logger.info("baidublackfridaycard delete end!");
		return "success";
	}
	
	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/baidublackfridaycard/create.jsp") })
	public String edit() {
		String areaCode = PropertiesUtils.getStringValueByKey("beijing_areaCode","11010000");
		logger.info("promotionCard edit start!");
		BaiDuBlackFridayCard baiDuBlackFridayCard = baiDuBlackFridayManager.getById(Integer.parseInt(id));
		cardId = id;
		skuId = baiDuBlackFridayCard.getSkuId();
		productId = baiDuBlackFridayCard.getProductId();
		productType = baiDuBlackFridayCard.getType();
		tag = baiDuBlackFridayCard.getTag();
		isInvalid = baiDuBlackFridayCard.getIsInvalid();
		if(null != baiDuBlackFridayCard.getCustomUrl()) {
			originPrice = baiDuBlackFridayCard.getOriginPrice().toString();
			salePrice = baiDuBlackFridayCard.getSalePrice().toString();
			customUrl = baiDuBlackFridayCard.getCustomUrl();
		}
		createNum = baiDuBlackFridayCard.getCreateNum();
        baiduCard = baiDuBlackFridayManager.getAllDataById(productId, skuId);
        priceInfo = baiDuBlackFridayManager.getPriceInfo(skuId,areaCode);
		return "success";
	}
	
	/**
	 * 编辑后保存   可编辑 是否生效  商品类型  商品打折信息   ？？？  未完成  页面只是展示  没有传数据
	 */
	@Action(value = "save", results = { @Result(name = "success", type = "redirect", location = "/BaiDuBlackFridayCard/list.action") })
	public String save() {
		String ida= getRequest().getParameter("id");
		String productType= getRequest().getParameter("productType");
		String tag= getRequest().getParameter("tag");
		String isInvalid= getRequest().getParameter("isInvalid");
		String customUrl= getRequest().getParameter("customUrl");
		String salePrice= getRequest().getParameter("salePrice");
		String originPrice= getRequest().getParameter("originPrice");
		logger.info("customUrl为"+customUrl+"   salePrice"+salePrice+"     originPrice"+originPrice);
		if(ida != null){
			BaiDuBlackFridayCard baiDuBlackFridayCard = baiDuBlackFridayManager.getById(Integer.parseInt(ida));
			if(null !=baiDuBlackFridayCard) {
				//页面和数据库相同的话 就没有修改
				if(getIsChange(baiDuBlackFridayCard.getType(),productType) || getIsChange(baiDuBlackFridayCard.getTag(),tag) || getIsChange(String.valueOf(baiDuBlackFridayCard.getIsInvalid()),isInvalid)
				   || getIsChange(baiDuBlackFridayCard.getCustomUrl(),customUrl) || getIsChange(String.valueOf(baiDuBlackFridayCard.getSalePrice()),salePrice) || getIsChange(String.valueOf(baiDuBlackFridayCard.getOriginPrice()),originPrice)) {
					baiDuBlackFridayCard.setModifier(getLoginUserName());
					baiDuBlackFridayCard.setUpdateDate(new java.util.Date());
					baiDuBlackFridayCard.setType(productType);
					baiDuBlackFridayCard.setTag(tag);
					baiDuBlackFridayCard.setIsInvalid(Integer.valueOf(isInvalid));
					baiDuBlackFridayCard.setIsEditor(EDITOR);
					if(StringUtils.isNotBlank(customUrl)) {
						baiDuBlackFridayCard.setCustomUrl(customUrl);
						baiDuBlackFridayCard.setSalePrice(new BigDecimal(salePrice));
						baiDuBlackFridayCard.setOriginPrice(new BigDecimal(originPrice));
					}else {
						baiDuBlackFridayCard.setCustomUrl(null);
						baiDuBlackFridayCard.setSalePrice(null);
						baiDuBlackFridayCard.setOriginPrice(null);
					}
					logger.info("baiDuBlackFridayCard edit+++==="+baiDuBlackFridayCard.toString());
					baiDuBlackFridayManager.update(baiDuBlackFridayCard);
					logger.info("baiDuBlackFridayCard edit end!");
					return "success";
			   }else 
				    return "success";
			}
		}
			return "fail";
	}

	


	//批量导入页面
	@Action(value = "log", results = { @Result(name = "success", location = "/jsp/baidublackfridaycard/importFile.jsp") } )
	public String logList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", page_index);
		paramMap.put("pageSize", page_size);
		listImportLogs = baiDuBlackFridayManager.listLog(paramMap);
		return "success";
	}
	
	/**
	 * @param
	 * @desc 生成未成功导入商品专题的excel文件
	 */
//	@Action(value = "errorwords")
//	public void generateFailDataFile() {
//		String logid = getStringParam("logid");
//		logger.info("该日志的id是"+logid);
//		String creator = getStringParam("creator");
//		if(!isEmpty(creator)){
//			creator = StringUtil.urlDecode(creator, "utf-8");
//			creator = StringUtil.urlDecode(creator, "utf-8");
//			creator = getPinyinLowercase(creator);
//		}else {
//			creator ="admin";
//		}
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
//		String format = formatter.format(new Date());
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("logId", logid);
//		List<BaiDuBlackFridayErrorCard> baiDuBlackFridayErrorCards = baiDuBlackFridayManager.listDownLog(paramMap);
//		if (baiDuBlackFridayErrorCards != null) {
//			try {
//				String randomChar = getRandomChar(6);
//				String fileName = creator + format + randomChar + ".xls";
//				getResponse().setContentType("application/vnd.ms-excel");
//				getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName);
//				OutputStream os = getResponse().getOutputStream();
//
//				WritableWorkbook workBook = Workbook.createWorkbook(os);
//
//				WritableSheet sheet = workBook.createSheet("sheet1", 0);
//			
//				Label one = new Label(0, 0, "商品ID");
//				Label two = new Label(1, 0, "skuId");
//				Label three = new Label(2, 0, "商品类别");
//				Label four = new Label(3, 0, "打折信息");
//				Label five = new Label(4, 0, "错误原因");	
//				sheet.addCell(one);
//				sheet.addCell(two);
//				sheet.addCell(three);
//				sheet.addCell(four);
//				sheet.addCell(five);
//				logger.info("该日志的错误数量是"+baiDuBlackFridayErrorCards.size());
//				for (int i = 0; i < baiDuBlackFridayErrorCards.size(); i++) {
//					BaiDuBlackFridayErrorCard baiDuBlackFridayErrorCard = baiDuBlackFridayErrorCards.get(i);
//
//					WritableCellFormat errorFormat = new WritableCellFormat();
//					errorFormat.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.RED);
//					errorFormat.setBackground(jxl.format.Colour.GRAY_25);								
//					Label ProductId = new Label(0, i + 1, baiDuBlackFridayErrorCard.getProductId());
//					Label skuIda = new Label(1, i + 1, baiDuBlackFridayErrorCard.getSkuId());
//					Label productType = new Label(2, i + 1, baiDuBlackFridayErrorCard.getProductType());
//					Label tag = new Label(3, i + 1, baiDuBlackFridayErrorCard.getTag());
//				 String errorString = "无效的商品ID";
//				 if(baiDuBlackFridayErrorCard.getType() == 2){
//					 errorString = "skuId与商品ID不匹配";					
//				 }else if(baiDuBlackFridayErrorCard.getType() == 3){
//					 errorString = "商品类别无效，请填入"+PRODUCT_TYPE;	
//				 }else if(baiDuBlackFridayErrorCard.getType() == 4){
//					 errorString = "打折信息无效，请填入"+TAG;	
//				 }
//				 Label errormeg = new Label(4, i + 1, errorString);	
//					sheet.addCell(ProductId);
//					sheet.addCell(skuIda);
//					sheet.addCell(productType);
//					sheet.addCell(tag);
//					sheet.addCell(errormeg);
//				}
//				workBook.write();
//				getResponse().flushBuffer();
//				workBook.close();
//				os.flush();
//				os.close();
//			} catch (IOException|RowsExceededException e) {
//				logger.error(e.getMessage(), e);
//			}  catch (WriteException e) {
//				logger.error(e.getMessage(), e);
//			}
//		}
//	}
	
	
	/**
	 * @param
	 * @desc 生成未成功导入商品专题的excel文件
	 */
	@Action(value = "errorwords")
	public void generateFailDataFile() {
		String logid = getStringParam("logid");
		logger.info("该日志的id是"+logid);
		String creator = getStringParam("creator");
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
		List<BaiDuBlackFridayErrorCard> baiDuBlackFridayErrorCards = baiDuBlackFridayManager.listDownLog(paramMap);
		if (baiDuBlackFridayErrorCards != null) {
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
		            row2.createCell(0).setCellValue("商品ID");  
		            row2.createCell(1).setCellValue("skuId");      
		            row2.createCell(2).setCellValue("商品类别");
		            row2.createCell(3).setCellValue("打折信息"); 
		            row2.createCell(4).setCellValue("错误原因");
		            
				logger.info("该日志的错误数量是"+baiDuBlackFridayErrorCards.size());
				for (int i = 0; i < baiDuBlackFridayErrorCards.size(); i++) {
					BaiDuBlackFridayErrorCard baiDuBlackFridayErrorCard = baiDuBlackFridayErrorCards.get(i);

					//在sheet里创建第二行  
				    HSSFRow rowX=sheet.createRow(i+2);      
			            //创建单元格并设置单元格内容  
					    rowX.createCell(0).setCellValue(baiDuBlackFridayErrorCard.getProductId());  
					    rowX.createCell(1).setCellValue(baiDuBlackFridayErrorCard.getSkuId());      
					    rowX.createCell(2).setCellValue( baiDuBlackFridayErrorCard.getProductType());
					    rowX.createCell(3).setCellValue( baiDuBlackFridayErrorCard.getTag()); 
				 String errorString = "无效的商品ID";
				 if(baiDuBlackFridayErrorCard.getType() == 2){
					 errorString = "skuId与商品ID不匹配";					
				 }else if(baiDuBlackFridayErrorCard.getType() == 3){
					 errorString = "商品类别无效，请填入"+PRODUCT_TYPE;	
				 }else if(baiDuBlackFridayErrorCard.getType() == 4){
					 errorString = "打折信息无效，请填入"+TAG;	
				 }
				 rowX.createCell(4).setCellValue(errorString);	
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
	 * @desc 生成未成功导入商品专题的excel文件
	 */
	@Action(value = "download")
	public void downloadLocal() {
		// 下载本地文件
        String fileName = "baiduBlackFriday.xlsx"; // 文件的默认保存名 /app/coo8_html/gome_html/anchorwords_upload/anchorword/
        String urlDir = PropertiesUtils.getStringValueByKey("baiDuBlackFridayCardUrlDir","/app/coo8_html/gome_html/anchorwords_upload/baidublackfriday/");
        // 读到流中                                                                                                             /app/coo8_html/gome_html/map/baidublackfriday/ 
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
//	@Action(value = "import", results = {
//			@Result(name = "success", type = "redirect", location = "/BaiDuBlackFridayCard/log.action") })
//	public String importPromotionCard() throws ParseException {
//		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
//		String createTime = String.format("%tF %<tT", System.currentTimeMillis());
//		HashSet<String > productTypes = new HashSet<>();
//		//数码、电器、服装、箱包、美妆、轻奢、运动、图书、其他
//		productTypes.add("数码");
//		productTypes.add("电器");
//		productTypes.add("服装");
//		productTypes.add("箱包");
//		productTypes.add("美妆");
//		productTypes.add("轻奢");
//		productTypes.add("运动");
//		productTypes.add("图书");
//		productTypes.add("其他");
//		
//		HashSet<String > tags = new HashSet<>();
//		//优品直降、限时秒杀、五折优惠、0元抢购、买赠专区、满减专区
//		tags.add("优品直降");
//		tags.add("限时秒杀");
//		tags.add("五折优惠");
//		tags.add("0元抢购");
//		tags.add("买赠专区");
//		tags.add("满减专区");
//
////		String lowercaseName = getPinyinLowercase(getLoginUserName());
////		lowercaseName = "baidublackfriday";
////		String uploadTime = createTime.replaceAll(":", "-");
////		uploadTime = uploadTime.replaceAll(" ", "_");
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
//		String format = formatter.format(new Date());
//		String filePath = configration + format + "_" + "baidublackfriday" + ".xls";
//		File destFile = new File(filePath);      
//		try {
//			FileUtils.copyFile(words, destFile);
//
//			List<BaiDuBlackFridayCard> baiDuBlackFridayCards = new ArrayList<BaiDuBlackFridayCard>();
//			List<BaiDuBlackFridayErrorCard> baiDuBlackFridayErrorCards = new ArrayList<BaiDuBlackFridayErrorCard>();
//
//			Workbook book = null;
//			BaiDuBlackFridayCardImportLog importLog = new BaiDuBlackFridayCardImportLog();
//			try {
//				book = Workbook.getWorkbook(destFile);	
//				Sheet sheet = book.getSheet(0);
//				int rowCount = sheet.getRows();
//				for (int i = 0; i < rowCount; i++) {
//					Date date = new java.util.Date();
//					List<String> strs = new ArrayList<String>();
//					for(int j = 0; j < 4; j++){
//						Cell cell = sheet.getCell(j, i);
//						String contents = cell.getContents();
//						strs.add(contents);
//					}
//					String productIda = strs.get(0).replaceAll("\\s*", "");
//					String skuIda = strs.get(1).replaceAll("\\s*", "");
//					String productType = strs.get(2).replaceAll("\\s*", "");
//					String tag = strs.get(3).replaceAll("\\s*", "");
//					logger.info("baiDuBlackFridayCard import,productIda"+productIda+",skuIda"+skuIda+",productType"+productType+",tag"+tag);
//					//校验商品id
//					int type = 0; //上传数据是否正确判断位，0  正确、1  商品Id错误、2 skuId错误、3 商品分类无效、4 打折信息无效
//					if(productIda.isEmpty()){
//						type = 1;
//					}else{
//						String productName = titlesManager.checkItemName(productIda);   //判断商品Id是否有效
//						if (productName.isEmpty()){
//							type = 1;
//						}else if(!baiDuBlackFridayManager.checkSkuId(productIda,skuIda)){
//							type = 2;
//						}
//					}
//					
//					if(!productTypes.contains(productType)) {
//						type = 3;
//					}
//					if(!tags.contains(tag)) {
//						type = 4;
//					}
//					//查看erm黑五商品库有没有该商品信息  有的话  覆盖tag type字段
//					if(type == 0){
//						BaiDuBlackFridayCard baiDuBlackFridayCard = baiDuBlackFridayManager.getByskuId(skuIda);
//						if(baiDuBlackFridayCard !=null) {
//							baiDuBlackFridayCard.setProductId(productIda);
//							baiDuBlackFridayCard.setSkuId(skuIda);
//							baiDuBlackFridayCard.setUpdateDate(date);
//							baiDuBlackFridayCard.setCreator(getLoginUserName());	
//							baiDuBlackFridayCard.setIsDelete(NO_DELETE);
//							baiDuBlackFridayCard.setIsEditor(NO_EDITOR);
//							baiDuBlackFridayCard.setTag(tag);
//							baiDuBlackFridayCard.setType(productType);
//							baiDuBlackFridayCard.setIsInvalid(NO_INVALID);
//							baiDuBlackFridayManager.save(baiDuBlackFridayCard);									
//							baiDuBlackFridayCards.add(baiDuBlackFridayCard);
//						}else {
//							baiDuBlackFridayCard = new BaiDuBlackFridayCard();						
//							baiDuBlackFridayCard.setProductId(productIda);
//							baiDuBlackFridayCard.setSkuId(skuIda);						
//							baiDuBlackFridayCard.setCreateDate(new java.util.Date());
//							baiDuBlackFridayCard.setUpdateDate(new java.util.Date());
//							baiDuBlackFridayCard.setCreator(getLoginUserName());	
//							baiDuBlackFridayCard.setIsDelete(NO_DELETE);
//							baiDuBlackFridayCard.setIsEditor(NO_EDITOR);
//							baiDuBlackFridayCard.setIsInvalid(NO_INVALID);
//							baiDuBlackFridayCard.setTag(tag);
//							baiDuBlackFridayCard.setType(productType);
//							baiDuBlackFridayManager.save(baiDuBlackFridayCard);									
//							baiDuBlackFridayCards.add(baiDuBlackFridayCard);									
//						}
//					}else {//上传数据错误加入错误列表
//						BaiDuBlackFridayErrorCard errorCard = new BaiDuBlackFridayErrorCard();
//						errorCard.setProductId(productIda);
//						errorCard.setSkuId(skuIda);	
//						errorCard.setType(type);
//						errorCard.setTag(tag);
//						errorCard.setProductType(productType);
//						errorCard.setCreateTime(date);
//						errorCard.setCreator(getLoginUserName());	
//						baiDuBlackFridayErrorCards.add(errorCard);
//					}
//				}	
//				importLog.setCreator(getLoginUserName());
//				importLog.setTotalCount(baiDuBlackFridayCards.size() + baiDuBlackFridayErrorCards.size());
//				importLog.setFailCount(baiDuBlackFridayErrorCards.size());
//				importLog.setCreateTime(createTime);
//				Integer  importLogId = baiDuBlackFridayManager.addLog(importLog); // 生成日志数据
//				
//				if (!baiDuBlackFridayErrorCards.isEmpty()) {
//					baiDuBlackFridayManager.addError(baiDuBlackFridayErrorCards,importLogId); // 添加失败数据
//				}
//
//			} catch (BiffException e) {
//				logger.error(e.getMessage(), e);
//			} finally {
//				if (book != null) {
//					book.close();
//				}
//			}
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		}
//		return SUCCESS;
//	}
	/**
	 * @desc 导入商品专题操作
	 * @return
	 * @throws ParseException
	 */
	@Action(value = "import", results = {
			@Result(name = "success", type = "redirect", location = "/BaiDuBlackFridayCard/log.action") })
	public String importPromotionCard123() throws ParseException {
		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
		String createTime = String.format("%tF %<tT", System.currentTimeMillis());
		HashSet<String > productTypes = new HashSet<>();
		//数码、电器、服装、箱包、美妆、轻奢、运动、图书、其他
		productTypes.add("数码");
		productTypes.add("电器");
		productTypes.add("服装");
		productTypes.add("箱包");
		productTypes.add("美妆");
		productTypes.add("轻奢");
		productTypes.add("运动");
		productTypes.add("图书");
		productTypes.add("其他");
		
		HashSet<String > tags = new HashSet<>();
		//优品直降、限时秒杀、五折优惠、0元抢购、买赠专区、满减专区
		tags.add("优品直降");
		tags.add("限时秒杀");
		tags.add("五折优惠");
		tags.add("0元抢购");
		tags.add("买赠专区");
		tags.add("满减专区");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		String format = formatter.format(new Date());
		if(StringUtils.isBlank(fileType)) {
			fileType = "";
		}
		String filePath = configration + format + "_" + "baidublackfriday" + fileType;
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(words, destFile);
			List<String[]> readExcel = ExcelPoiUtils.readExcel(destFile);
			logger.info("excel读取条数为"+readExcel.size());
			List<BaiDuBlackFridayCard> baiDuBlackFridayCards = new ArrayList<BaiDuBlackFridayCard>();
			List<BaiDuBlackFridayErrorCard> baiDuBlackFridayErrorCards = new ArrayList<BaiDuBlackFridayErrorCard>();
			BaiDuBlackFridayCardImportLog importLog = new BaiDuBlackFridayCardImportLog();
				for (int i = 0; i < readExcel.size(); i++) {
					Date date = new java.util.Date();
					String[] strings = readExcel.get(i);
					List<String> strs = new ArrayList<String>();
					for(int j = 0; j < 4; j++){
						String contents = strings[j];
						strs.add(contents);
					}
					String productIda = strs.get(0).replaceAll("[^0-9a-zA-Z]", "");
					String skuIda = strs.get(1).replaceAll("[^0-9a-zA-Z]", "");
					String productType = strs.get(2).replaceAll("[^\\u4e00-\\u9fa5\\w]", "");
					String tag = strs.get(3).replaceAll("[^\\u4e00-\\u9fa5\\w]", "");
					logger.info("baiDuBlackFridayCard import,productIda"+productIda+",skuIda"+skuIda+",productType"+productType+",tag"+tag);
					//校验商品id
					int type = 0; //上传数据是否正确判断位，0  正确、1  商品Id错误、2 skuId错误、3 商品分类无效、4 打折信息无效
					if(productIda.isEmpty()){
						type = 1;
					}else{
						String productName = titlesManager.checkItemName(productIda);   //判断商品Id是否有效
						if (productName.isEmpty()){
							type = 1;
						}else if(!baiDuBlackFridayManager.checkSkuId(productIda,skuIda)){
							type = 2;
						}
					}
					
					if(!productTypes.contains(productType)) {
						type = 3;
					}
					if(!tags.contains(tag)) {
						type = 4;
					}
					//查看erm黑五商品库有没有该商品信息  有的话  覆盖tag type字段
					if(type == 0){
						BaiDuBlackFridayCard baiDuBlackFridayCard = baiDuBlackFridayManager.getByskuId(skuIda);
						if(baiDuBlackFridayCard !=null) {
							baiDuBlackFridayCard.setProductId(productIda);
							baiDuBlackFridayCard.setSkuId(skuIda);
							baiDuBlackFridayCard.setUpdateDate(date);
							baiDuBlackFridayCard.setCreator(getLoginUserName());	
							baiDuBlackFridayCard.setIsDelete(NO_DELETE);
							if(StringUtils.isNotBlank(baiDuBlackFridayCard.getCustomUrl())) {
								baiDuBlackFridayCard.setIsEditor(EDITOR);
							}else {
								baiDuBlackFridayCard.setIsEditor(NO_EDITOR);
							}
							baiDuBlackFridayCard.setTag(tag);
							baiDuBlackFridayCard.setType(productType);
							baiDuBlackFridayCard.setIsInvalid(NO_INVALID);
							baiDuBlackFridayManager.save(baiDuBlackFridayCard);									
							baiDuBlackFridayCards.add(baiDuBlackFridayCard);
						}else {
							baiDuBlackFridayCard = new BaiDuBlackFridayCard();						
							baiDuBlackFridayCard.setProductId(productIda);
							baiDuBlackFridayCard.setSkuId(skuIda);						
							baiDuBlackFridayCard.setCreateDate(new java.util.Date());
							baiDuBlackFridayCard.setUpdateDate(new java.util.Date());
							baiDuBlackFridayCard.setCreator(getLoginUserName());	
							baiDuBlackFridayCard.setIsDelete(NO_DELETE);
							baiDuBlackFridayCard.setIsEditor(NO_EDITOR);
							baiDuBlackFridayCard.setIsInvalid(NO_INVALID);
							baiDuBlackFridayCard.setTag(tag);
							baiDuBlackFridayCard.setType(productType);
							baiDuBlackFridayManager.save(baiDuBlackFridayCard);									
							baiDuBlackFridayCards.add(baiDuBlackFridayCard);									
						}
					}else {//上传数据错误加入错误列表
						BaiDuBlackFridayErrorCard errorCard = new BaiDuBlackFridayErrorCard();
						errorCard.setProductId(productIda);
						errorCard.setSkuId(skuIda);	
						errorCard.setType(type);
						errorCard.setTag(tag);
						errorCard.setProductType(productType);
						errorCard.setCreateTime(date);
						errorCard.setCreator(getLoginUserName());
						baiDuBlackFridayErrorCards.add(errorCard);
					}
				}	
				importLog.setCreator(getLoginUserName());
				importLog.setTotalCount(baiDuBlackFridayCards.size() + baiDuBlackFridayErrorCards.size());
				importLog.setFailCount(baiDuBlackFridayErrorCards.size());
				importLog.setCreateTime(createTime);
				Integer  importLogId = baiDuBlackFridayManager.addLog(importLog); // 生成日志数据
				if (!baiDuBlackFridayErrorCards.isEmpty()) {
					baiDuBlackFridayManager.addError(baiDuBlackFridayErrorCards,importLogId); // 添加失败数据
				}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return SUCCESS;
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

	public BaiDuBlackFridayManager getBaiDuBlackFridayManager() {
		return baiDuBlackFridayManager;
	}

	public void setBaiDuBlackFridayManager(BaiDuBlackFridayManager baiDuBlackFridayManager) {
		this.baiDuBlackFridayManager = baiDuBlackFridayManager;
	}

	public PaginatedList<BaiDuBlackFridayCardImportLog> getListImportLogs() {
		return listImportLogs;
	}

	public void setListImportLogs(PaginatedList<BaiDuBlackFridayCardImportLog> listImportLogs) {
		this.listImportLogs = listImportLogs;
	}

	public PaginatedList<BaiDuBlackFridayCard> getBaiDuBlackFridayCardlist() {
		return baiDuBlackFridayCardlist;
	}

	public void setBaiDuBlackFridayCardlist(PaginatedList<BaiDuBlackFridayCard> baiDuBlackFridayCardlist) {
		this.baiDuBlackFridayCardlist = baiDuBlackFridayCardlist;
	}

	public java.lang.Integer getIsInvalid() {
		return isInvalid;
	}

	public void setIsInvalid(java.lang.Integer isInvalid) {
		this.isInvalid = isInvalid;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public PriceInfo getPriceInfo() {
		return priceInfo;
	}

	public void setPriceInfo(PriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}

	public String getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(String originPrice) {
		this.originPrice = originPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getCreateNum() {
		return createNum;
	}

	public void setCreateNum(Integer createNum) {
		this.createNum = createNum;
	}

	public String getCustomUrl() {
		return customUrl;
	}

	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	
	
		
}
