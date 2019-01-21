package com.gome.utils;

import com.gome.baidublackfriday.controller.action.BaiDuBlackFridayCardAction;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangkeqiang-ds on 2018/9/4.
 */
public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(BaiDuBlackFridayCardAction.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /**
     * ����excel�ļ��������󷵻�
     * @param file
     * @throws IOException
     */
    public static List<String[]> readExcel(File file) throws IOException{

        //����ļ�
        checkFile(file);
        //���Workbook����������
        Workbook workbook = getWorkBook(file);
        //�������ض��󣬰�ÿ���е�ֵ��Ϊһ�����飬��������Ϊһ�����Ϸ���
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //��õ�ǰsheet������
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //��õ�ǰsheet�Ŀ�ʼ��
                int firstRowNum  = sheet.getFirstRowNum();
                //��õ�ǰsheet�Ľ�����
                int lastRowNum = sheet.getLastRowNum();
                //ѭ�����˵�һ�е�������
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                    //��õ�ǰ��
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //��õ�ǰ�еĿ�ʼ��
                    int firstCellNum = row.getFirstCellNum();
                    //��õ�ǰ�е�����
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //ѭ����ǰ��
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }
    public static void checkFile(File file) throws IOException{
        //�ж��ļ��Ƿ����
        if(null == file){
            logger.error("�ļ������ڣ�");
            throw new FileNotFoundException("�ļ������ڣ�");
        }
        //����ļ���
        String fileName = file.getName();
        //�ж��ļ��Ƿ���excel�ļ�
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
            logger.error(fileName + "����excel�ļ�");
            throw new IOException(fileName + "����excel�ļ�");
        }
    }
    public static Workbook getWorkBook(File file) {
        //����ļ���
        String fileName = file.getName();
        //����Workbook���������󣬱�ʾ����excel
        Workbook workbook = null;
        try {
            //��ȡexcel�ļ���io��
            InputStream is = new FileInputStream(file);
            //�����ļ���׺����ͬ(xls��xlsx)��ò�ͬ��Workbookʵ�������
            if(fileName.endsWith(xls)){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(xlsx)){
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //�����ֵ���String�������������1����1.0�����
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //�ж����ݵ�����
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //����
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //�ַ���
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //��ʽ
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //��ֵ
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //����
                cellValue = "�Ƿ��ַ�";
                break;
            default:
                cellValue = "δ֪����";
                break;
        }
        return cellValue;
    }
}