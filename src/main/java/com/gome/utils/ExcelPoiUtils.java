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
	 * 读入excel文件，解析后返回
	 * @param file
	 * @throws IOException
	 * 
	 */
	//获得Workbook工作薄对象
	static	Workbook   workbook = null;
	public static List<String[]> readExcel(File file) throws IOException{

		
		//创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<String[]> list = new ArrayList<String[]>();
		try {
			workbook = WorkbookFactory.create(file);
			if(workbook != null){
				for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
					//获得当前sheet工作表
					Sheet sheet = workbook.getSheetAt(sheetNum);
					if(sheet == null){
						continue;
					}
					//获得当前sheet的开始行
					int firstRowNum  = sheet.getFirstRowNum();
					//获得当前sheet的结束行
					int lastRowNum = sheet.getLastRowNum();
					//循环除了第一行的所有行
					for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
						//获得当前行
						Row row = sheet.getRow(rowNum);
						if(row == null){
							continue;
						}
						//获得当前行的开始列
						int firstCellNum = row.getFirstCellNum();
						//获得当前行的列数
						int lastCellNum = row.getPhysicalNumberOfCells();
						String[] cells = new String[row.getPhysicalNumberOfCells()];
						//循环当前行
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
		//把数字当成String来读，避免出现1读成1.0的情况
		if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			 if(HSSFDateUtil.isCellDateFormatted(cell)){
                 DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                 cellValue=dateFormat.format(cell.getDateCellValue());
              return cellValue;
			 }else{
				 cell.setCellType(Cell.CELL_TYPE_STRING);
			 }
			
		}
		//判断数据的类型
		switch (cell.getCellType()){
			case Cell.CELL_TYPE_NUMERIC: //数字
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING: //字符串
				cellValue = String.valueOf(cell.getStringCellValue());
				break;
			case Cell.CELL_TYPE_BOOLEAN: //Boolean
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA: //公式
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_BLANK: //空值
				cellValue = "";
				break;
			case Cell.CELL_TYPE_ERROR: //故障
				cellValue = "非法字符";
				break;
			default:
				cellValue = "未知类型";
				break;
		}
		return cellValue;
	}
}