package com.coo8.topic.expenditure.action;

import java.io.*;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coo8.topic.expenditure.pojo.*;
import com.gome.productwords.model.ProductWordsCard;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Arg;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.topic.controller.action.BaseAction;
import com.coo8.topic.expenditure.dto.ChannelDetailDto;
import com.coo8.topic.expenditure.dto.ExpenditureDto;
import com.coo8.topic.expenditure.dto.ExpenditurelListDto;
import com.coo8.topic.expenditure.dto.ExportExpenditureDto;
import com.coo8.topic.expenditure.service.ExpenditureService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gome.common.web.context.LoginContext;
import com.gome.utils.ExcelPoiUtils;
import com.gome.utils.ExportUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * REM后台花费系统控制器
 * @author yangjunjie-ds
 *	@date 2018年10月12日16:55:52
 */
@Slf4j
@ParentPackage("json-default")
@Namespace("/Expenditure")
public class ExpenditureAction extends BaseAction {

	private static final long serialVersionUID = -6066868937624161913L;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private ExpenditureService expenditureService;
	@Setter @Getter
	private ExpenditureDto expenditureDto;
	@Setter @Getter
	private Integer pageNum=1;
	@Setter @Getter
	private Integer pageSize=10;
	@Setter @Getter
	private Expenditure searchKey;
	@Setter @Getter
	protected PaginatedList<ExpenditureDto> expenditureList;
	@Setter @Getter
	private List<PortType> typeList;
	@Setter @Getter
	private List<ChannelDetailStair> stairList;
	@Setter @Getter
	private List<ChannelDetailSecond> secondList;
	@Setter @Getter
	private List<ChannelDetailThird> thirdList;
	@Setter @Getter
	private int selectPortIds;
	@Setter @Getter
	private long selectStairIds;
	@Setter @Getter
	private long selectSecondIds;
	@Setter @Getter
	private int selectThirdIds;
	//新端口id
	@Setter @Getter
	private int newPortId;
	//新一级渠道id
	@Setter @Getter
	private long newStairId;
	//新二级渠道id
	@Setter @Getter
	private long newSecondId;
	//新一级渠道name
	@Setter @Getter
	private String newStair;
	//新二级渠道name
	@Setter @Getter
	private String newSecond;
	//新三级渠道name
	@Setter @Getter
	private String newThird;
	@Setter @Getter
	private String[] noi;
	@Setter @Getter
	private String[] portIds;
	@Setter @Getter
	private String[] stairChanIds;
	@Setter @Getter
	private String[] secondChanIds;
	@Setter @Getter
	private String[] thirdChanIds;
	@Setter @Getter
	private String[] createTime;
	@Setter @Getter
	private String[] uvs;
	@Setter @Getter
	private String[] orderNums;
	@Setter @Getter
	private String[] sales;
	@Setter @Getter
	private String[] expenditures;
	@Setter @Getter
	private int expenditureId;
	@Setter @Getter
	private int sucNum;
	@Setter @Getter
	private int type;
	@Setter @Getter
	private int chanT;
	@Setter @Getter
	private String fileType;
	@Setter @Getter
	private File expFile;
	@Setter @Getter
	private String fromToDate;
	@Setter @Getter
	private List<ExpendAnalyseBase> expendAnalyseBaseList;
	@Setter @Getter
	private Integer firstStairId;
	@Setter @Getter
	private Integer realPortId;
	@Setter @Getter
    private BigDecimal wholeStageExpendAmount;
	@Setter @Getter
	private String startDate ;
	@Setter @Getter
	private String endDate ;
	
	
	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/expenditure/list.jsp") })
	public String expenditureList(){
		log.info("expenditureList  ongoing!");
		LoginContext context = LoginContext.getLoginContext();
		context = new LoginContext();
		if(null != context){
			if(searchKey == null){//初始化数据
				searchKey = new Expenditure();
				searchKey.setDelFlag(false);
				searchKey.setPortId(0);
				searchKey.setStairChanId(0);
				searchKey.setSecondChanId(0);
				searchKey.setThirdChanId(0);
			}
			if(StringUtils.isNotBlank(fromToDate)) {
				String[] split = fromToDate.split("~");
				String startDate = split[0].trim();
				String endDate = split[1].trim();
				searchKey.setStartDate(startDate);
				searchKey.setEndDate(endDate);
			}
			Page<Object> page = PageHelper.startPage(pageNum, pageSize);
			page.setOrderBy("id DESC");
			List<ExpenditureDto> list = expenditureService.findExpenditureList(searchKey);
			//页面分页处理
			if(list !=null){
				expenditureList = new PaginatedArrayList<ExpenditureDto>(
						(int)page.getTotal(), page.getPageNum(),page.getPageSize());
					expenditureList.addAll(list);
				}
					selectStairIds =searchKey.getStairChanId();
					selectSecondIds = searchKey.getSecondChanId();
					selectThirdIds = searchKey.getThirdChanId();
					selectPortIds = searchKey.getPortId();
			return SUCCESS;
		}

		return NONE;
	}
	
	@Action(value = "addView", results = { @Result(name = "success", location = "/jsp/expenditure/add.jsp") })
	public String expenditureAddView(){
		log.info("expenditureAddView  ongoing!");
		sucNum = 0;//初始化
		type = 0; //添加类型
		return SUCCESS;
	}

	@Action(value = "add_Expenditure", results = { @Result(name = "success", location = "/jsp/expenditure/hint.jsp") })
	public String expenditureAddList(){
		log.info("expenditureAddList  ongoing!");
		LoginContext context = LoginContext.getLoginContext();
		if(null != context){
			String pin = context.getPin();//操作人
		ExpenditurelListDto dto = new ExpenditurelListDto();
		dto.setCreator(pin);
		dto.setNoi(noi);
		dto.setPortIds(portIds);
		dto.setStairChanIds(stairChanIds);
		dto.setSecondChanIds(secondChanIds);
		dto.setThirdChanIds(thirdChanIds);
		dto.setCreateTime(createTime);
		dto.setOrderNums(orderNums);
		dto.setUvs(uvs);
		dto.setSales(sales);
		dto.setExpenditures(expenditures);
		sucNum = expenditureService.addExpenditureList(dto);
		type = 0; 
		}
		return SUCCESS;
	}
	
	@Action(value = "modifyView", results = { @Result(name = "success",location = "/jsp/expenditure/update.jsp") })
	public String expenditureModfiyView(){
		log.info("expenditureModfiyView ongoing!");
		sucNum = 0;//初始化
		type = 1; //修改类型
		expenditureDto = this.expenditureService.findExpenditure(expenditureId);
		return SUCCESS;
	}


	@Action(value = "removeExpenditure", results = { @Result(name = "success", type = "redirect", location = "/Expenditure/list.action") })
	public String removeExpenditure(){
		ExpenditureDto expenditure = this.expenditureService.findExpenditure(expenditureId);
		int i = expenditureService.modifyExpenditure(expenditure);
		return SUCCESS;

	}


	@Action(value = "modifyExpenditure", results = { @Result(name = "success",location = "/jsp/expenditure/hint.jsp") })
	public String expenditureModfiyExpenditure(){
		log.info("expenditureModfiyView ongoing!");
		LoginContext context = LoginContext.getLoginContext();
		if(null != context){
			String pin = context.getPin();//操作人
			expenditureDto.setMender(pin);
		this.expenditureService.modifyExpenditure(expenditureDto);
		sucNum = 1;
		type = 1; //修改类型
		
		}
		return SUCCESS;
	}
	
	@Action(value = "addChannelView", results = { @Result(name = "success", location = "/jsp/expenditure/addChannel.jsp") })
	public String addChannelView(){
		log.info("addChannelView  ongoing!");
		sucNum = 0;//初始化
		type = 2; //添加类型 2代表渠道添加,1代表修改expenditure操作,0代表添加expenditure操作
		chanT =0;
		typeList = this.expenditureService.findPortTypeList();
		return SUCCESS;
	}
	
	
	@Action(value = "addChannel", results = { @Result(name = "success",location = "/jsp/expenditure/hint.jsp") })
	public String addChannelList(){
		log.info("addChannelList  ongoing!");
		ChannelDetailDto dto = new ChannelDetailDto();
			dto.setPortId(newPortId);
			sucNum = 0;//初始化
			type = 2;
		if(newStairId>0){
			dto.setStairId(newStairId);
		}
		if(newSecondId>0){
			dto.setSecondId(newSecondId);
		}
		if(StringUtils.isNotBlank(newStair)){
			dto.setStairName(newStair);
			ChannelDetailStair stair = this.expenditureService.findChannelStair(newStair, newPortId);
			if(stair != null){
				chanT = 1;
				return SUCCESS;
			}
		}
		if(StringUtils.isNoneBlank(newSecond)){
			dto.setSecondName(newSecond);
		}
		if(newStairId>0 && StringUtils.isNoneBlank(newSecond)){
			ChannelDetailSecond second = this.expenditureService.findChannelSecond(newSecond, newStairId);
			if(second !=null){
				chanT = 2;
				return SUCCESS;
			}
		}
		if(StringUtils.isNotBlank(newThird)){
			dto.setThirdName(newThird);
		}
		if(newSecondId>0 && StringUtils.isNotBlank(newThird)){
			ChannelDetailThird third = this.expenditureService.findChannelThird(newThird, newSecondId);
			if(third !=null){
				chanT = 3;
				return SUCCESS;
			}
		}
		try {
			expenditureService.addChannelDetailList(dto);
			sucNum = 1;
			type = 2;
		} catch (Exception e) {
			log.error("渠道添加异常!",e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 异步端口
	 * @return
	 */
	@Action(value = "portList_ajax",results = { @Result(name = "success",type = "json", params = { "root", "typeList"})})
	public String ajaxPortTypeList(){
		log.info("ajaxPortTypeList  ongoing!");
		typeList = expenditureService.findPortTypeList();
			return SUCCESS;
	}
	
	/**
	 * 联动一级渠道
	 * @return
	 */
	@Action(value = "stairList_ajax",results = { @Result(name = "success",type = "json", params = { "root", "stairList"})})
	public String ajaxChannelDetailStairList(){
		log.info("ajaxChannelDetailStairList  ongoing!");
		stairList = expenditureService.findChannelStairList(selectPortIds);
			return SUCCESS;
	}
	
	@Action(value = "stair_ajax",results = { @Result(name = "success",type = "json")})
	public String ajaxChannelDetailStair(){
		log.info("ajaxChannelDetailStair  ongoing!");
		if(StringUtils.isNotBlank(newStair)){
			 ChannelDetailStair stair = expenditureService.findChannelStair(newStair,newPortId);
			 if(stair !=null){
				 return ERROR;
			 }
		}
		return SUCCESS;
	}
	/**
	 * 联动二级渠道
	 * @return
	 */
	@Action(value = "secondList_ajax",results = { @Result(name = "success",type = "json", params = { "root", "secondList"})})
	public String ajaxChannelDetailSecondList(){
		log.info("ajaxChannelDetailSecondList  ongoing!");
		if(selectStairIds>0){
			secondList = expenditureService.findChannelSecondList(selectStairIds);
			return SUCCESS;
		}
		return NONE;
	}
	
	@Action(value = "second_ajax",results = { @Result(name = "success",type = "json")})
	public String ajaxChannelDetailSecond(){
		log.info("ajaxChannelDetailSecond  ongoing!");
		if(StringUtils.isNotBlank(newSecond)){
			  ChannelDetailSecond second = expenditureService.findChannelSecond(newSecond,newStairId);
			 if(second !=null){
				 return ERROR;
			 }
		}
		return SUCCESS;
	}
	/**
	 * 联动三级渠道
	 * @return
	 */
	@Action(value = "thirdList_ajax",results = { @Result(name = "success",type = "json", params = { "root", "thirdList"})})
	public String ajaxChannelDetailThirdList(){
		log.info("ajaxChannelDetailStairList  ongoing!");
		if(selectSecondIds>0){
			thirdList = expenditureService.findChannelThirdList(selectStairIds,selectSecondIds);
			return SUCCESS;
		}
		return NONE;
	}
	
	@Action(value = "third_ajax",results = { @Result(name = "success",type = "json")})
	public String ajaxChannelDetailThird(){
		log.info("ajaxChannelDetailThird  ongoing!");
		if(StringUtils.isNotBlank(newThird)){
			   ChannelDetailThird third = expenditureService.findChannelThird(newThird,newSecondId);
			 if(third !=null){
				 return ERROR;
			 }
		}
		return SUCCESS;
	}
	
	/**
	 * 导入花费信息
	 * @return
	 * @throws ParseException
	 */
	@Action(value = "import")
	public void importExpenditure() throws ParseException {
		String configration = (String) ReloadableConfig.getProperty("ANCHORWORD_GOME_PATH");
		String creator = getRealUserName();
		Date now = new Date();	
		DateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat  formatS = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileDate = formatS.format(now);
		if(StringUtils.isBlank(fileType)) {
			fileType = ".xlsx";
		}
		String filePath = configration + fileDate + "-expenditureDetail" + fileType;
		File destFile = new File(filePath);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		int lineNum = 0;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			FileUtils.copyFile(expFile, destFile);
			List<String[]> readExcel = ExcelPoiUtils.readExcel(destFile);
			log.info("excel读取条数为"+readExcel.size());
			for (int i = 0; i < readExcel.size(); i++) {
				Expenditure expend = new Expenditure();
				String[] rows = readExcel.get(i);
				//时间
				expend.setCreateDate(format.parse(rows[0]));
				if(StringUtils.isBlank(rows[0]) 
						|| StringUtils.isBlank(rows[1]) 
						|| StringUtils.isBlank(rows[2]) 
						|| StringUtils.isBlank(rows[3]) 
						|| StringUtils.isBlank(rows[4]) 
						|| StringUtils.isBlank(rows[5])
						|| StringUtils.isBlank(rows[6]) 
						|| StringUtils.isBlank(rows[7])  
						|| StringUtils.isBlank(rows[8])) throw new Exception("上传文件数据空值异常~~~");
				
				//端口
				int portId = this.expenditureService.findPortByName(rows[1]);
				expend.setPortId(portId);
				//一级渠道
				ChannelDetailStair stair = this.expenditureService.findChannelStair(rows[2], portId);
				if(stair !=null){
					expend.setStairChanId(stair.getId());
				}
				//二级渠道
				if(!rows[3].trim().equals("0")){
					ChannelDetailSecond second = this.expenditureService.findChannelSecond(rows[3], stair.getId());
					if( second !=null){
						expend.setSecondChanId(second.getId());
						//三级渠道
						if(!rows[4].trim().equals("0")){
							ChannelDetailThird third = this.expenditureService.findChannelThird(rows[4], second.getId());
							if(third !=null)	expend.setThirdChanId(third.getId());
							}
					}
				}
				
					int uv = Integer.parseInt(rows[5].trim());	//UV
					int orderNum = Integer.parseInt(rows[6].trim());//订单量
					BigDecimal sale = new BigDecimal(rows[7].trim()).setScale(1,BigDecimal.ROUND_HALF_DOWN);//销售额
					BigDecimal expenditure = new BigDecimal(rows[8].trim()).setScale(1,BigDecimal.ROUND_HALF_DOWN);//花费
					if(uv>0) expend.setUV(uv);
					if(orderNum>0) expend.setOrderNum(orderNum);
					if(BigDecimal.ZERO != sale) expend.setSale(sale);
					if(BigDecimal.ZERO != expenditure) expend.setExpenditure(expenditure);
					expend.setCreator(creator);
					expend.setMender(creator);
					lineNum +=this.expenditureService.addExpenditure(expend);
				log.info("插入成功条数:{}",lineNum);
			}
			String success = JSON.toJSONString("上传成功条数:"+lineNum);
			writer.println(success);
		} catch (Exception e) {
			log.error("上传数据异常 !",e);
			lineNum += 1;
			String error = JSON.toJSONString("前"+(lineNum-1)+"行上传成功 ! 请校验 第"+lineNum+" 行 数据,空值请用数字0填充!上传成功数据请勿重复上传！");
			writer.println(error);
		}finally{
			writer.flush();
			writer.close();
		}
	}
	
	
	/**
	 * 花费导入模板下载
	 */
	@Action(value = "download")
	public void downloadTemplate() {
        String fileName = "expTemplate.xlsx"; 
        String urlDir = PropertiesUtils.getStringValueByKey("expenditureTemplateUrlDir","/app/coo8_html/gome_html/anchorwords_upload/expenditure/");
        InputStream inStream = null;
		try {
			inStream = new FileInputStream(urlDir+fileName);
		} catch (FileNotFoundException fe) {
			log.error("花费模板不存在!",fe);
		}
        getResponse().reset();
        getResponse().setContentType("bin");
        getResponse().addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        byte[] b = new byte[1024];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
            	getResponse().getOutputStream().write(b, 0, len);
                inStream.close();
        } catch (IOException e) {
           log.error("模板下载异常!",e);
        }
	}
	
	 @Action(value = "export")
	public void exportExpenditure(){
		if(StringUtils.isNotBlank(fromToDate)){
			String[] split = fromToDate.split("~");
			String startDate = split[0].trim();
			String endDate = split[1].trim();
			List<ExportExpenditureDto> list = this.expenditureService.exportExpenditureListByDate(startDate, endDate);
			if(list !=null){
				 String title =fromToDate+"花费记录";
			     String[] headers = {"日期","端口","一级渠道","二级渠道","三级渠道","UV","订单量","销售额","费用"};
			     String[] fields = {"dateTime", "portName","stairName","secondName","thirdName","uvNum","orderNum","saleNum","expNum"};
			     SXSSFWorkbook wb = null;
			     try{
			    	 wb = ExportUtils.createWorkbook(1000);//创建工作簿对象
			    	 ExportUtils.exportExcel(title, headers, fields, 0, wb, list);
			    	 HttpServletRequest request = getRequest();
			    	 HttpServletResponse response = getResponse();
			    	 ExportUtils.responseWorkbook(title, wb, request, response);
			    	 getResponse().flushBuffer();
			    	 wb.close();
			     }catch(Exception e){
			    	 log.error("导出数据异常",e);
			     }
			}
		}
	}

	@Action(value = "expendAnalyse" ,results = { @Result(name = "success", location = "/jsp/expenditure/dataAnalyse.jsp") })
	public String expendAnalyseData(){
		if(StringUtils.isNotBlank(fromToDate)) {
			String[] split = fromToDate.split("~");
			startDate = split[0].trim();
			endDate = split[1].trim();
			expendAnalyseBaseList = expenditureService.getExpenditureAnalyseListByDate(startDate,endDate);

			for (ExpendAnalyseBase expendAnalyseBase : expendAnalyseBaseList) {
				Integer portId = expendAnalyseBase.getPortId();
				BigDecimal portExpend = expenditureService.selectPortExpendAmount(startDate,endDate,portId);
				expendAnalyseBase.setPortCostAmount(portExpend);
			}
            wholeStageExpendAmount = expenditureService.selectWholeExpendAmount(startDate,endDate);

		}else {
			Calendar  cal_1=Calendar.getInstance();//获取当前日期
			cal_1.add(Calendar.MONTH, -1);
			cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
			startDate= format.format(cal_1.getTime());
			//获取前月的最后一天
			Calendar cale = Calendar.getInstance();
			cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
			endDate = format.format(cale.getTime());
			expendAnalyseBaseList = expenditureService.getExpenditureAnalyseListByDate(startDate,endDate);
			for (ExpendAnalyseBase expendAnalyseBase : expendAnalyseBaseList) {
				Integer portId = expendAnalyseBase.getPortId();
				BigDecimal portExpend = expenditureService.selectPortExpendAmount(startDate,endDate,portId);
				expendAnalyseBase.setPortCostAmount(portExpend);
			}
			fromToDate = startDate+" ~ "+endDate;
            wholeStageExpendAmount = expenditureService.selectWholeExpendAmount(startDate,endDate);
		}
		return SUCCESS;
	}


	@Action(value = "downloadExpendAnalyse")
	public void downloadExpendAnalyseData(){
		if(StringUtils.isNotBlank(fromToDate)){
			String[] split = fromToDate.split("~");
			String startDate = split[0].trim();
			String endDate = split[1].trim();
			List<ExpendAnalyseBase> list = expenditureService.getExpenditureAnalyseListByDate(startDate, endDate);
			if(list !=null){

				List<ExpendAnalyseData> expendAnalyseDataList = new ArrayList<>();
				for (ExpendAnalyseBase expendAnalyseBase : list) {
					for (DataContains stairChannelAndExpend : expendAnalyseBase.getStairChannelAndExpends()) {

						String portName = expendAnalyseBase.getPortName();
						String firstChannel = stairChannelAndExpend.getStairName();
						BigDecimal expendAmount = stairChannelAndExpend.getExpendAmount();
						ExpendAnalyseData expendAnalyseData = new ExpendAnalyseData(portName,firstChannel,expendAmount);
						expendAnalyseDataList.add(expendAnalyseData);
					}
				}

				try {
					String fileName = "OnLine-Expend-" +fromToDate+ ".xls";
					getResponse().setContentType("application/vnd.ms-excel");
					getResponse().setHeader("Content-disposition", "attachment; filename=" + fileName);
					OutputStream os = getResponse().getOutputStream();
					HSSFWorkbook wb = new HSSFWorkbook();
					HSSFSheet sheet = wb.createSheet("一级花费信息");
					HSSFRow row = sheet.createRow(0);
					HSSFCellStyle style = wb.createCellStyle();
					row.createCell(0).setCellValue("端口");
					row.createCell(1).setCellValue("一级渠道");
					row.createCell(2).setCellValue("花费");
					for (int i = 0; i < expendAnalyseDataList.size(); i++) {
						ExpendAnalyseData expendAnalyseData = expendAnalyseDataList.get(i);
						HSSFRow rowData = sheet.createRow(i+1);
						rowData.createCell(0).setCellValue(expendAnalyseData.getPortName());
						rowData.createCell(1).setCellValue(expendAnalyseData.getFirstChannel());
						String expendCountFormat = getNumberFormat(expendAnalyseData.getExpendCount().doubleValue());
						rowData.createCell(2).setCellValue(expendCountFormat);

					}
					wb.write(os);
					getResponse().flushBuffer();
					wb.close();
					os.flush();
					os.close();

				} catch (IOException e) {
					e.printStackTrace();
				}


			}
		}


	}





	@Action(value = "ajaxJump" ,results = { @Result(name = "success", location = "/jsp/expenditure/stair.jsp") })
	public String ajaxTurnIntoStamp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("firstStairId",firstStairId);
		return SUCCESS;
	}

	@Action(value = "ajaxPortJump" ,results = { @Result(name = "success", location = "/jsp/expenditure/portStair.jsp") })
	public String ajaxPortTurnIntoStamp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("realPortId",realPortId);
		return SUCCESS;
	}
    @Action(value = "ajaxWholeJump" ,results = { @Result(name = "success", location = "/jsp/expenditure/whole.jsp") })
    public String ajaxWholeTurnIntoStamp(){
        return SUCCESS;
    }




	@Action(value = "expendAnalyseInMonth")
	public void expendDataInMonthAnalyse(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String yearNum = request.getParameter("yearNum");
		String stairId = request.getParameter("stairId");
		if (StringUtils.isBlank(stairId)){
			return;
		}
		if (StringUtils.isBlank(yearNum)){
			yearNum = getSysYear();
		}

		List<ExpendAnalyseCharge> expendAnalyseCharges = expenditureService.selectExpendDataInMonth(yearNum, Integer.valueOf(stairId));
		String[] month = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
		BigDecimal[] count = new BigDecimal[]{new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0)};
		for (int i = 0; i < expendAnalyseCharges.size(); i++) {
		    int monthNumberIndex = Integer.valueOf(expendAnalyseCharges.get(i).getMonthNumber())-1;
		    count[monthNumberIndex] = expendAnalyseCharges.get(i).getMonthCost();
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("intervals",month);
		map.put("count",count);
		String s = JSON.toJSONString(map);
		try {
			PrintWriter writer = response.getWriter();
			writer.println(s);
			writer.flush();
			writer.close();
		}catch (IOException e){
			return;
		}


	}

	@Action(value = "portExpendAnalyseInMonth")
	public void expendPortDataInMonthAnalyse(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String yearNum = request.getParameter("yearNum");
		String realPortId = request.getParameter("realPortId");
		if (StringUtils.isBlank(realPortId)){
			return;
		}
		if (StringUtils.isBlank(yearNum)){
			yearNum = getSysYear();
		}

		List<ExpendPortAnalyseCharge> expendAnalyseCharges = expenditureService.selectPortExpendDataInMonth(yearNum, Integer.valueOf(realPortId));
		String[] month = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
        BigDecimal[] count = new BigDecimal[]{new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0)};
		for (int i = 0; i < expendAnalyseCharges.size(); i++) {
            int monthNumberIndex = Integer.valueOf(expendAnalyseCharges.get(i).getMonthNumber())-1;
            count[monthNumberIndex] = expendAnalyseCharges.get(i).getMonthCost();

		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("intervals",month);
		map.put("count",count);
		String s = JSON.toJSONString(map);
		try {
			PrintWriter writer = response.getWriter();
			writer.println(s);
			writer.flush();
			writer.close();
		}catch (IOException e){
			return;
		}


	}


    @Action(value = "wholeExpendAnalyseInMonth")
    public void wholeExpendDataInMonthAnalyse(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String yearNum = request.getParameter("yearNum");
        if (StringUtils.isBlank(yearNum)){
            yearNum = getSysYear();
        }

        List<WholeExpendAnalyseCharge> expendAnalyseCharges = expenditureService.selectWholeExpendDataInMonth(yearNum);
        String[] month = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
        BigDecimal[] count = new BigDecimal[]{new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0)};
        for (int i = 0; i < expendAnalyseCharges.size(); i++) {
            int monthNumberIndex = Integer.valueOf(expendAnalyseCharges.get(i).getMonthNumber())-1;
            count[monthNumberIndex] = expendAnalyseCharges.get(i).getMonthCost();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("intervals",month);
        map.put("count",count);
        String s = JSON.toJSONString(map);
        try {
            PrintWriter writer = response.getWriter();
            writer.println(s);
            writer.flush();
            writer.close();
        }catch (IOException e){
            return;
        }
    }






	public String getSysYear() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		return year;
	}

	public String getNumberFormat(double expendAmount){
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		return numberFormat.format(expendAmount);
		/*将一个可能包含千分位的数字转换为不含千分位的形式：
		String amount1 = "13,000.00";
		double d1 = new DecimalFormat().parse(amount1).doubleValue(); //这里使用的是parse，不是format
		System.out.println(String.valueOf(d1)); //结果是13000.00*/
	}

}
