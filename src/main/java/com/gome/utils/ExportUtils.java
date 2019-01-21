package com.gome.utils;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 文件导出工具
 * @author yangjunjie-ds
 *
 */
public class ExportUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExportUtils.class);  
	
	public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat LONG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    private static final int sheetMaxCount = 1000000;//单个sheet最多写入行数，2007版以上Excel最大单页104万行，此处设置100万自动分页 
    
    /**
     * 根据ResultSet导出Excel
     * @param title
     * @param headers
     * @param fields
     * @param startRow
     * @param wb
     * @param rs
     * @throws Exception
     */
    public static <T> void exportExcel(String title, String[] headers, String[] fields, int startRow, Workbook wb, ResultSet rs) throws Exception {  
  
        Sheet sheet = null;  
        startRow = startRow>0?startRow+2:startRow;
        int index = startRow, pageRowNo = startRow, columnCount = headers.length; // 行号、页码、列数
        Row nRow = null;
        ResultSetMetaData rsm = rs.getMetaData();
        int rsColumnCount = rsm.getColumnCount();// ResultSet列数
        Map<String, Object> obj_map = new HashMap<String, Object>(0);
        
        while(rs.next()) {//遍历游标 
        	for (int i = 0; i < rsColumnCount; i++) {//转换rs到map便于提取
				obj_map.put(rsm.getColumnName(i).toLowerCase(), rs.getObject(index));
			}
            int sheetIndex = index/sheetMaxCount;  
            if (index % sheetMaxCount == 0) {  
                sheet = wb.createSheet(title + "_" + (sheetIndex + 1));  
                sheet = wb.getSheetAt(sheetIndex);   
                sheet.setDisplayGridlines(false);// 设置表标题是否有表格边框  
                pageRowNo = 2;   
                createHeader(sheet, title, headers);                  
            }else{  
                sheet = wb.getSheetAt(sheetIndex);   
            }  
            index++;  
            nRow = sheet.createRow(pageRowNo++); // 新建行对象 
            
            for (int j = 0; j < columnCount; j++) {//正式写入字段，梳理顺序为传入字段顺序
                setCellValue(sheet, nRow.createCell(j), obj_map.get(fields[j].toLowerCase()));  
            } 
            obj_map.clear();//清空map待用
        }  
          
    }  

    /**
     * 根据List数据导出excel
     * @param title
     * @param headers
     * @param fields
     * @param startRow
     * @param wb 
     * @param data
     * @throws IOException
     */
    public static <T> void exportExcel(String title, String[] headers, String[] fields, int startRow, Workbook wb, List<T> data) throws IOException {  
    	if (data.size()>20000) {
    		LOGGER.info("警告：导出Excel时过多装载List数据，这样可能造成JVM内存溢出！！！");
		}
        Sheet sheet = null;  
        startRow = startRow>0?startRow+2:startRow;  
        int index = startRow, pageRowNo = startRow, columnCount = headers.length; // 行号、页码、列数  
        Row nRow = null;
        
        for (T obj : data) {  
            int sheetIndex = index/sheetMaxCount;  
            if (index % sheetMaxCount == 0) {  
                sheet = wb.createSheet(title + "_" + (sheetIndex + 1));  
                sheet = wb.getSheetAt(sheetIndex);   
                sheet.setDisplayGridlines(false);// 设置表标题是否有表格边框  
                pageRowNo = 2;   
                createHeader(sheet, title, headers);                  
            }else{  
                sheet = wb.getSheetAt(sheetIndex);   
            }  
            index++;  
            @SuppressWarnings("unchecked")  
            Map<String, Object> map = obj instanceof Map ? (Map<String, Object>) obj : beanToMap(obj);    
            nRow = sheet.createRow(pageRowNo++); // 新建行对象     
            for (int j = 0; j < columnCount; j++) {  
                setCellValue(sheet, nRow.createCell(j), map.get(fields[j]));  
            }             
        }  
          
    }  
    /** 
     * write Workbook 
     * @param wb 
     * @param filePath 
     * @throws IOException 
     */  
    public static void writeWorkbook(Workbook wb, String filePath)throws IOException{  
        FileOutputStream fos = new FileOutputStream(filePath + "/workbook"+System.currentTimeMillis()+".xlsx");  
        wb.write(fos);  
        fos.flush();   
        fos.close();  
    }  
      
    /** 
     * responseWorkbook 
     * @param title 
     * @param wb 
     * @param request 
     * @param response 
     * @throws IOException 
     */  
    public static void responseWorkbook(String title, Workbook wb,HttpServletRequest request, HttpServletResponse response)throws IOException{  
        String sFileName = title + ".xlsx";  
        // 火狐浏览器导出excel乱码  
        String agent = request.getHeader("User-Agent");  
        // 判断是否火狐浏览器  
        boolean isFirefox = agent != null && agent.contains("Firefox");  
        if (isFirefox) {  
            sFileName = new String(sFileName.getBytes("UTF-8"), "ISO-8859-1");  
        } else {  
            sFileName = URLEncoder.encode(sFileName, "UTF8");  
        }  
        response.setHeader("Content-Disposition", "attachment; filename=".concat(sFileName));  
//        response.setHeader("Connection", "close");  
        response.setContentType("application/vnd.ms-excel");    
        wb.write(response.getOutputStream());
    }  
    /** 
     * 设置单元格的值 
     * @param cell 
     * @param cellVal 
     */  
    private static void setCellValue(Sheet sheet, Cell cell, Object cellVal){  
        if(cellVal == null || String.class.equals(cellVal.getClass())){  
            cell.setCellValue(cellVal!=null&&!"".equals(cellVal)?String.valueOf(cellVal):"");  
        }else if(Integer.class.equals(cellVal.getClass()) || int.class.equals(cellVal.getClass())){  
            cell.setCellValue(Integer.valueOf(cellVal.toString()));  
        }else if(Long.class.equals(cellVal.getClass()) || long.class.equals(cellVal.getClass())){  
            cell.setCellValue(Integer.valueOf(cellVal.toString()));  
        }else if(Double.class.equals(cellVal.getClass()) || double.class.equals(cellVal.getClass())){  
            cell.setCellValue(Double.valueOf(cellVal.toString()));  
        }else if(Float.class.equals(cellVal.getClass()) || float.class.equals(cellVal.getClass())){  
            cell.setCellValue(Float.valueOf(cellVal.toString()));  
        }else if(BigDecimal.class.equals(cellVal.getClass())){  
            cell.setCellValue(new BigDecimal(cellVal.toString()).doubleValue());  
        }else if(Date.class.equals(cellVal.getClass())){  
            cell.setCellValue(SHORT_DATE_FORMAT.format(cellVal));  
        }else if(Timestamp.class.equals(cellVal.getClass())){  
            cell.setCellValue(LONG_DATE_FORMAT.format(cellVal));  
        }else{  
            cell.setCellValue(cellVal!=null&&!"".equals(cellVal)?String.valueOf(cellVal):"");  
        }  
        cell.setCellStyle(sheet.getWorkbook().getCellStyleAt((short) 3));  
    }  
     
    
    /** 
     * JavaBean转Map 
     *  
     * @param obj 
     * @return 
     */  
    public static Map<String, Object> beanToMap(Object obj) {  
        Map<String, Object> params = new HashMap<String, Object>(0);  
        try {  
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();  
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);  
            for (int i = 0; i < descriptors.length; i++) {  
                String name = descriptors[i].getName();  
                if (!StringUtils.equals(name, "class")) {  
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));  
                }  
            }  
        } catch (Exception e) {  
            LOGGER.error("URLDecoder fail :", e);  
        }  
        return params;  
    }  
      
    /** 
     * 创建表头 
     * @param sheet 
     * @param headers 
     */  
    private static void createHeader(Sheet sheet, String title, String[] headers){  
          
        //设置标题  
        Row tRow = sheet.createRow(0);  
        Cell hc = tRow.createCell(0);  
        hc.setCellValue(new XSSFRichTextString(title));  
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));// 合并标题行：起始行号，终止行号， 起始列号，终止列号  
        hc.setCellStyle(sheet.getWorkbook().getCellStyleAt((short) 1));  
          
        //设置表头  
        Row nRow = sheet.createRow(1);  
        for (int i = 0; i < headers.length; i++) {  
            Cell cell = nRow.createCell(i);  
            cell.setCellValue(headers[i]);  
            cell.setCellStyle(sheet.getWorkbook().getCellStyleAt((short) 2));  
        }  
    }  
      
      
    /** 
     * 创建Workbook
     * @param tmpNm SXSSFWorkbook缓冲区大小
     * @return 
     */  
    public static SXSSFWorkbook createWorkbook(int tmpNm){
    	if (tmpNm == 0) {
			tmpNm = 1000;//传0默认1000行缓冲
		}
    	SXSSFWorkbook wb = new SXSSFWorkbook(tmpNm);
        wb.setCompressTempFiles(true); //压缩临时文件，很重要，否则磁盘很快就会被写满
        CellStyle hcs = wb.createCellStyle();  
        hcs.setBorderBottom(CellStyle.BORDER_THIN);  
        hcs.setBorderLeft(CellStyle.BORDER_THIN);  
        hcs.setBorderRight(CellStyle.BORDER_THIN);  
        hcs.setBorderTop(CellStyle.BORDER_THIN);  
        hcs.setAlignment(CellStyle.ALIGN_CENTER);  
        Font hfont = wb.createFont();  
        hfont.setFontName("宋体");  
        hfont.setFontHeightInPoints((short) 16);// 设置字体大小  
        hfont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 加粗  
        hcs.setFont(hfont);  
          
        CellStyle tcs = wb.createCellStyle();  
        tcs.setBorderBottom(CellStyle.BORDER_THIN);  
        tcs.setBorderLeft(CellStyle.BORDER_THIN);  
        tcs.setBorderRight(CellStyle.BORDER_THIN);  
        tcs.setBorderTop(CellStyle.BORDER_THIN);  
        Font tfont = wb.createFont();  
        tfont.setFontName("宋体");  
        tfont.setFontHeightInPoints((short) 12);// 设置字体大小  
        tfont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 加粗  
        tcs.setFont(tfont);  
          
        CellStyle cs = wb.createCellStyle();  
        cs.setBorderBottom(CellStyle.BORDER_THIN);  
        cs.setBorderLeft(CellStyle.BORDER_THIN);  
        cs.setBorderRight(CellStyle.BORDER_THIN);  
        cs.setBorderTop(CellStyle.BORDER_THIN);  
        Font font = wb.createFont();  
        font.setFontName("宋体");  
        font.setFontHeightInPoints((short) 12);// 设置字体大小  
          
        return wb;  
    }
    
    /**
     * 用法及测试
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args){
    	/*SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session=sessionFactory.openSession(); 
//    	Transaction tx=session.beginTransaction();//事务
    	//以上获取hibernate 中session，在实际项目中可以通过其他方式获得，此处仅为示例
    	//如 Session session = super.getSession();
    	//其他框架自行获取
    	
    	
    	long startTime = System.currentTimeMillis(); 
    	Connection connection = session.connection();
		Statement statement;
		ResultSet rs = null;
		//查询整个对象的所有字段  
    	String strSQL="select * from prpDuser";
    	String title = "用户记录";
    	String[] headers = {"用户代码","姓名","性别","归属机构名称","归属机构代码","邮箱地址","手机号","创建时间","更新时间","有效状态"};
    	String[] fields = {"userCode", "userName","sex","comName","comCode","email","phoneNo","createTime","updateTime","validStatus"};
    	SXSSFWorkbook wb = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(strSQL);
			connection.commit();
			//开始导出数据
			wb = ExportUtil.createWorkbook(1000);//创建工作簿对象
			ExportUtil.exportExcel(title, headers, fields, 0, wb, rs);//根据结果集rs导出数据到工作簿
			ExportUtil.writeWorkbook(wb, "/temp");
			
			//ExportUtil.responseWorkbook(title, wb, request, response);//可根据req和resp直接输出excel
			//结束导出数据
			
			rs.close();
			statement.close();
			connection.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			rs = null;
			statement = null;
			if (wb != null) {
				wb.dispose();
			}
		}

		long endTime = System.currentTimeMillis();  
        System.out.println("take time :"+(endTime - startTime));
    	

    }*/
    }
}
