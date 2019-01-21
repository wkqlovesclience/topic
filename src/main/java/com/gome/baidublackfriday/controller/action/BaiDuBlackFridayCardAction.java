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
	private final String PRODUCT_TYPE ="���롢��������װ���������ױ�����ݡ��˶���ͼ�顢����";
	private final String TAG ="��Ʒֱ������ʱ��ɱ�������Żݡ�0Ԫ����������ר��������ר��";
	//�༭
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
		logger.info("baiduCard list ��ѯ���ݲ�����" + search);
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
			//����ɾ��
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
	 * �༭�󱣴�   �ɱ༭ �Ƿ���Ч  ��Ʒ����  ��Ʒ������Ϣ   ������  δ���  ҳ��ֻ��չʾ  û�д�����
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
		logger.info("customUrlΪ"+customUrl+"   salePrice"+salePrice+"     originPrice"+originPrice);
		if(ida != null){
			BaiDuBlackFridayCard baiDuBlackFridayCard = baiDuBlackFridayManager.getById(Integer.parseInt(ida));
			if(null !=baiDuBlackFridayCard) {
				//ҳ������ݿ���ͬ�Ļ� ��û���޸�
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

	


	//��������ҳ��
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
	 * @desc ����δ�ɹ�������Ʒר���excel�ļ�
	 */
//	@Action(value = "errorwords")
//	public void generateFailDataFile() {
//		String logid = getStringParam("logid");
//		logger.info("����־��id��"+logid);
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
//				Label one = new Label(0, 0, "��ƷID");
//				Label two = new Label(1, 0, "skuId");
//				Label three = new Label(2, 0, "��Ʒ���");
//				Label four = new Label(3, 0, "������Ϣ");
//				Label five = new Label(4, 0, "����ԭ��");	
//				sheet.addCell(one);
//				sheet.addCell(two);
//				sheet.addCell(three);
//				sheet.addCell(four);
//				sheet.addCell(five);
//				logger.info("����־�Ĵ���������"+baiDuBlackFridayErrorCards.size());
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
//				 String errorString = "��Ч����ƷID";
//				 if(baiDuBlackFridayErrorCard.getType() == 2){
//					 errorString = "skuId����ƷID��ƥ��";					
//				 }else if(baiDuBlackFridayErrorCard.getType() == 3){
//					 errorString = "��Ʒ�����Ч��������"+PRODUCT_TYPE;	
//				 }else if(baiDuBlackFridayErrorCard.getType() == 4){
//					 errorString = "������Ϣ��Ч��������"+TAG;	
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
	 * @desc ����δ�ɹ�������Ʒר���excel�ļ�
	 */
	@Action(value = "errorwords")
	public void generateFailDataFile() {
		String logid = getStringParam("logid");
		logger.info("����־��id��"+logid);
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

			    //����HSSFWorkbook����(excel���ĵ�����)  
		        HSSFWorkbook wb = new HSSFWorkbook();  
			    //�����µ�sheet����excel�ı���  
			    HSSFSheet sheet=wb.createSheet("������Ϣ");
			
			    //��sheet�ﴴ����һ�У�����Ϊ������(excel����)��������0��65535֮����κ�һ��  
			    HSSFRow row1=sheet.createRow(0);  
			    //������Ԫ��excel�ĵ�Ԫ�񣬲���Ϊ��������������0��255֮����κ�һ��  
			    HSSFCell cell=row1.createCell(0);  
				    //���õ�Ԫ������  
				    cell.setCellValue("������Ϣ");  
				    //�ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������  
				    sheet.addMergedRegion(new CellRangeAddress(0,0,0,4)); 
			    
			    //��sheet�ﴴ���ڶ���  
			    HSSFRow row2=sheet.createRow(1);      
		            //������Ԫ�����õ�Ԫ������  
		            row2.createCell(0).setCellValue("��ƷID");  
		            row2.createCell(1).setCellValue("skuId");      
		            row2.createCell(2).setCellValue("��Ʒ���");
		            row2.createCell(3).setCellValue("������Ϣ"); 
		            row2.createCell(4).setCellValue("����ԭ��");
		            
				logger.info("����־�Ĵ���������"+baiDuBlackFridayErrorCards.size());
				for (int i = 0; i < baiDuBlackFridayErrorCards.size(); i++) {
					BaiDuBlackFridayErrorCard baiDuBlackFridayErrorCard = baiDuBlackFridayErrorCards.get(i);

					//��sheet�ﴴ���ڶ���  
				    HSSFRow rowX=sheet.createRow(i+2);      
			            //������Ԫ�����õ�Ԫ������  
					    rowX.createCell(0).setCellValue(baiDuBlackFridayErrorCard.getProductId());  
					    rowX.createCell(1).setCellValue(baiDuBlackFridayErrorCard.getSkuId());      
					    rowX.createCell(2).setCellValue( baiDuBlackFridayErrorCard.getProductType());
					    rowX.createCell(3).setCellValue( baiDuBlackFridayErrorCard.getTag()); 
				 String errorString = "��Ч����ƷID";
				 if(baiDuBlackFridayErrorCard.getType() == 2){
					 errorString = "skuId����ƷID��ƥ��";					
				 }else if(baiDuBlackFridayErrorCard.getType() == 3){
					 errorString = "��Ʒ�����Ч��������"+PRODUCT_TYPE;	
				 }else if(baiDuBlackFridayErrorCard.getType() == 4){
					 errorString = "������Ϣ��Ч��������"+TAG;	
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
	 * @desc ����δ�ɹ�������Ʒר���excel�ļ�
	 */
	@Action(value = "download")
	public void downloadLocal() {
		// ���ر����ļ�
        String fileName = "baiduBlackFriday.xlsx"; // �ļ���Ĭ�ϱ����� /app/coo8_html/gome_html/anchorwords_upload/anchorword/
        String urlDir = PropertiesUtils.getStringValueByKey("baiDuBlackFridayCardUrlDir","/app/coo8_html/gome_html/anchorwords_upload/baidublackfriday/");
        // ��������                                                                                                             /app/coo8_html/gome_html/map/baidublackfriday/ 
        InputStream inStream = null;
		try {
			inStream = new FileInputStream(urlDir += fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}// �ļ��Ĵ��·��
        // ��������ĸ�ʽ
        getResponse().reset();
        getResponse().setContentType("bin");
        getResponse().addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // ѭ��ȡ�����е�����
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
	 * @desc ������Ʒר�����
	 * @return
	 * @throws ParseException
	 */
//	@Action(value = "import", results = {
//			@Result(name = "success", type = "redirect", location = "/BaiDuBlackFridayCard/log.action") })
//	public String importPromotionCard() throws ParseException {
//		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
//		String createTime = String.format("%tF %<tT", System.currentTimeMillis());
//		HashSet<String > productTypes = new HashSet<>();
//		//���롢��������װ���������ױ�����ݡ��˶���ͼ�顢����
//		productTypes.add("����");
//		productTypes.add("����");
//		productTypes.add("��װ");
//		productTypes.add("���");
//		productTypes.add("��ױ");
//		productTypes.add("����");
//		productTypes.add("�˶�");
//		productTypes.add("ͼ��");
//		productTypes.add("����");
//		
//		HashSet<String > tags = new HashSet<>();
//		//��Ʒֱ������ʱ��ɱ�������Żݡ�0Ԫ����������ר��������ר��
//		tags.add("��Ʒֱ��");
//		tags.add("��ʱ��ɱ");
//		tags.add("�����Ż�");
//		tags.add("0Ԫ����");
//		tags.add("����ר��");
//		tags.add("����ר��");
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
//					//У����Ʒid
//					int type = 0; //�ϴ������Ƿ���ȷ�ж�λ��0  ��ȷ��1  ��ƷId����2 skuId����3 ��Ʒ������Ч��4 ������Ϣ��Ч
//					if(productIda.isEmpty()){
//						type = 1;
//					}else{
//						String productName = titlesManager.checkItemName(productIda);   //�ж���ƷId�Ƿ���Ч
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
//					//�鿴erm������Ʒ����û�и���Ʒ��Ϣ  �еĻ�  ����tag type�ֶ�
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
//					}else {//�ϴ����ݴ����������б�
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
//				Integer  importLogId = baiDuBlackFridayManager.addLog(importLog); // ������־����
//				
//				if (!baiDuBlackFridayErrorCards.isEmpty()) {
//					baiDuBlackFridayManager.addError(baiDuBlackFridayErrorCards,importLogId); // ���ʧ������
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
	 * @desc ������Ʒר�����
	 * @return
	 * @throws ParseException
	 */
	@Action(value = "import", results = {
			@Result(name = "success", type = "redirect", location = "/BaiDuBlackFridayCard/log.action") })
	public String importPromotionCard123() throws ParseException {
		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
		String createTime = String.format("%tF %<tT", System.currentTimeMillis());
		HashSet<String > productTypes = new HashSet<>();
		//���롢��������װ���������ױ�����ݡ��˶���ͼ�顢����
		productTypes.add("����");
		productTypes.add("����");
		productTypes.add("��װ");
		productTypes.add("���");
		productTypes.add("��ױ");
		productTypes.add("����");
		productTypes.add("�˶�");
		productTypes.add("ͼ��");
		productTypes.add("����");
		
		HashSet<String > tags = new HashSet<>();
		//��Ʒֱ������ʱ��ɱ�������Żݡ�0Ԫ����������ר��������ר��
		tags.add("��Ʒֱ��");
		tags.add("��ʱ��ɱ");
		tags.add("�����Ż�");
		tags.add("0Ԫ����");
		tags.add("����ר��");
		tags.add("����ר��");
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
			logger.info("excel��ȡ����Ϊ"+readExcel.size());
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
					//У����Ʒid
					int type = 0; //�ϴ������Ƿ���ȷ�ж�λ��0  ��ȷ��1  ��ƷId����2 skuId����3 ��Ʒ������Ч��4 ������Ϣ��Ч
					if(productIda.isEmpty()){
						type = 1;
					}else{
						String productName = titlesManager.checkItemName(productIda);   //�ж���ƷId�Ƿ���Ч
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
					//�鿴erm������Ʒ����û�и���Ʒ��Ϣ  �еĻ�  ����tag type�ֶ�
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
					}else {//�ϴ����ݴ����������б�
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
				Integer  importLogId = baiDuBlackFridayManager.addLog(importLog); // ������־����
				if (!baiDuBlackFridayErrorCards.isEmpty()) {
					baiDuBlackFridayManager.addError(baiDuBlackFridayErrorCards,importLogId); // ���ʧ������
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
	
	public static String getRandomChar(int length) {            //��������ַ���   
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
	 * @desc ��ȡСд��Ӣ��ƴ��
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
