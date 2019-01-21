package com.gome.utils;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

/**
 * Created by wangkeqiang-ds on 2018/9/4.
 */
public class ExcelPoiUtils {
	/**
	 * ����excel�ļ��������󷵻�
	 * @param file
	 * @throws IOException
	 * 
	 */
	//���Workbook����������
	static	Workbook   workbook = null;
	public static List<String[]> readExcel(File file) throws IOException{

		
		//�������ض��󣬰�ÿ���е�ֵ��Ϊһ�����飬��������Ϊһ�����Ϸ���
		List<String[]> list = new ArrayList<String[]>();
		try {
			workbook = WorkbookFactory.create(file);
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
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return list;
	}


	public static String getCellValue(Cell cell){
		String cellValue = "";
		if(cell == null){
			return cellValue;
		}
		//�����ֵ���String�������������1����1.0�����
		if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			 if(HSSFDateUtil.isCellDateFormatted(cell)){
                 DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                 cellValue=dateFormat.format(cell.getDateCellValue());
              return cellValue;
			 }else{
				 cell.setCellType(Cell.CELL_TYPE_STRING);
			 }
			
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