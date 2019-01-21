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
 * �ļ���������
 * @author yangjunjie-ds
 *
 */
public class ExportUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExportUtils.class);  
	
	public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat LONG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    private static final int sheetMaxCount = 1000000;//����sheet���д��������2007������Excel���ҳ104���У��˴�����100���Զ���ҳ 
    
    /**
     * ����ResultSet����Excel
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
        int index = startRow, pageRowNo = startRow, columnCount = headers.length; // �кš�ҳ�롢����
        Row nRow = null;
        ResultSetMetaData rsm = rs.getMetaData();
        int rsColumnCount = rsm.getColumnCount();// ResultSet����
        Map<String, Object> obj_map = new HashMap<String, Object>(0);
        
        while(rs.next()) {//�����α� 
        	for (int i = 0; i < rsColumnCount; i++) {//ת��rs��map������ȡ
				obj_map.put(rsm.getColumnName(i).toLowerCase(), rs.getObject(index));
			}
            int sheetIndex = index/sheetMaxCount;  
            if (index % sheetMaxCount == 0) {  
                sheet = wb.createSheet(title + "_" + (sheetIndex + 1));  
                sheet = wb.getSheetAt(sheetIndex);   
                sheet.setDisplayGridlines(false);// ���ñ�����Ƿ��б��߿�  
                pageRowNo = 2;   
                createHeader(sheet, title, headers);                  
            }else{  
                sheet = wb.getSheetAt(sheetIndex);   
            }  
            index++;  
            nRow = sheet.createRow(pageRowNo++); // �½��ж��� 
            
            for (int j = 0; j < columnCount; j++) {//��ʽд���ֶΣ�����˳��Ϊ�����ֶ�˳��
                setCellValue(sheet, nRow.createCell(j), obj_map.get(fields[j].toLowerCase()));  
            } 
            obj_map.clear();//���map����
        }  
          
    }  

    /**
     * ����List���ݵ���excel
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
    		LOGGER.info("���棺����Excelʱ����װ��List���ݣ������������JVM�ڴ����������");
		}
        Sheet sheet = null;  
        startRow = startRow>0?startRow+2:startRow;  
        int index = startRow, pageRowNo = startRow, columnCount = headers.length; // �кš�ҳ�롢����  
        Row nRow = null;
        
        for (T obj : data) {  
            int sheetIndex = index/sheetMaxCount;  
            if (index % sheetMaxCount == 0) {  
                sheet = wb.createSheet(title + "_" + (sheetIndex + 1));  
                sheet = wb.getSheetAt(sheetIndex);   
                sheet.setDisplayGridlines(false);// ���ñ�����Ƿ��б��߿�  
                pageRowNo = 2;   
                createHeader(sheet, title, headers);                  
            }else{  
                sheet = wb.getSheetAt(sheetIndex);   
            }  
            index++;  
            @SuppressWarnings("unchecked")  
            Map<String, Object> map = obj instanceof Map ? (Map<String, Object>) obj : beanToMap(obj);    
            nRow = sheet.createRow(pageRowNo++); // �½��ж���     
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
        // ������������excel����  
        String agent = request.getHeader("User-Agent");  
        // �ж��Ƿ��������  
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
     * ���õ�Ԫ���ֵ 
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
     * JavaBeanתMap 
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
     * ������ͷ 
     * @param sheet 
     * @param headers 
     */  
    private static void createHeader(Sheet sheet, String title, String[] headers){  
          
        //���ñ���  
        Row tRow = sheet.createRow(0);  
        Cell hc = tRow.createCell(0);  
        hc.setCellValue(new XSSFRichTextString(title));  
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));// �ϲ������У���ʼ�кţ���ֹ�кţ� ��ʼ�кţ���ֹ�к�  
        hc.setCellStyle(sheet.getWorkbook().getCellStyleAt((short) 1));  
          
        //���ñ�ͷ  
        Row nRow = sheet.createRow(1);  
        for (int i = 0; i < headers.length; i++) {  
            Cell cell = nRow.createCell(i);  
            cell.setCellValue(headers[i]);  
            cell.setCellStyle(sheet.getWorkbook().getCellStyleAt((short) 2));  
        }  
    }  
      
      
    /** 
     * ����Workbook
     * @param tmpNm SXSSFWorkbook��������С
     * @return 
     */  
    public static SXSSFWorkbook createWorkbook(int tmpNm){
    	if (tmpNm == 0) {
			tmpNm = 1000;//��0Ĭ��1000�л���
		}
    	SXSSFWorkbook wb = new SXSSFWorkbook(tmpNm);
        wb.setCompressTempFiles(true); //ѹ����ʱ�ļ�������Ҫ��������̺ܿ�ͻᱻд��
        CellStyle hcs = wb.createCellStyle();  
        hcs.setBorderBottom(CellStyle.BORDER_THIN);  
        hcs.setBorderLeft(CellStyle.BORDER_THIN);  
        hcs.setBorderRight(CellStyle.BORDER_THIN);  
        hcs.setBorderTop(CellStyle.BORDER_THIN);  
        hcs.setAlignment(CellStyle.ALIGN_CENTER);  
        Font hfont = wb.createFont();  
        hfont.setFontName("����");  
        hfont.setFontHeightInPoints((short) 16);// ���������С  
        hfont.setBoldweight(Font.BOLDWEIGHT_BOLD);// �Ӵ�  
        hcs.setFont(hfont);  
          
        CellStyle tcs = wb.createCellStyle();  
        tcs.setBorderBottom(CellStyle.BORDER_THIN);  
        tcs.setBorderLeft(CellStyle.BORDER_THIN);  
        tcs.setBorderRight(CellStyle.BORDER_THIN);  
        tcs.setBorderTop(CellStyle.BORDER_THIN);  
        Font tfont = wb.createFont();  
        tfont.setFontName("����");  
        tfont.setFontHeightInPoints((short) 12);// ���������С  
        tfont.setBoldweight(Font.BOLDWEIGHT_BOLD);// �Ӵ�  
        tcs.setFont(tfont);  
          
        CellStyle cs = wb.createCellStyle();  
        cs.setBorderBottom(CellStyle.BORDER_THIN);  
        cs.setBorderLeft(CellStyle.BORDER_THIN);  
        cs.setBorderRight(CellStyle.BORDER_THIN);  
        cs.setBorderTop(CellStyle.BORDER_THIN);  
        Font font = wb.createFont();  
        font.setFontName("����");  
        font.setFontHeightInPoints((short) 12);// ���������С  
          
        return wb;  
    }
    
    /**
     * �÷�������
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args){
    	/*SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session=sessionFactory.openSession(); 
//    	Transaction tx=session.beginTransaction();//����
    	//���ϻ�ȡhibernate ��session����ʵ����Ŀ�п���ͨ��������ʽ��ã��˴���Ϊʾ��
    	//�� Session session = super.getSession();
    	//����������л�ȡ
    	
    	
    	long startTime = System.currentTimeMillis(); 
    	Connection connection = session.connection();
		Statement statement;
		ResultSet rs = null;
		//��ѯ��������������ֶ�  
    	String strSQL="select * from prpDuser";
    	String title = "�û���¼";
    	String[] headers = {"�û�����","����","�Ա�","������������","������������","�����ַ","�ֻ���","����ʱ��","����ʱ��","��Ч״̬"};
    	String[] fields = {"userCode", "userName","sex","comName","comCode","email","phoneNo","createTime","updateTime","validStatus"};
    	SXSSFWorkbook wb = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(strSQL);
			connection.commit();
			//��ʼ��������
			wb = ExportUtil.createWorkbook(1000);//��������������
			ExportUtil.exportExcel(title, headers, fields, 0, wb, rs);//���ݽ����rs�������ݵ�������
			ExportUtil.writeWorkbook(wb, "/temp");
			
			//ExportUtil.responseWorkbook(title, wb, request, response);//�ɸ���req��respֱ�����excel
			//������������
			
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
