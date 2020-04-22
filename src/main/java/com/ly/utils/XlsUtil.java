package com.ly.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class XlsUtil {
	
	/**
	 * 行复制功能
	 * @param wb 工作簿
	 * @param fromRow 从哪行开始
	 * @param toRow 目标行
	 * @param copyValueFlag true则连同cell的内容一起复制
	 */
	public static void copyRow(Workbook wb, Row fromRow, Row toRow, boolean copyValueFlag) {
	    toRow.setHeight(fromRow.getHeight());

	    for (Iterator<Cell> cellIt = fromRow.cellIterator(); cellIt.hasNext();) {
	        Cell tmpCell = cellIt.next();
	        Cell newCell = toRow.createCell(tmpCell.getColumnIndex());
	        copyCell(wb, tmpCell, newCell, copyValueFlag);
	    }

	    Sheet worksheet = fromRow.getSheet();

	    for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
	        CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
	        if (cellRangeAddress.getFirstRow() == fromRow.getRowNum()) {
	            CellRangeAddress newCellRangeAddress = new CellRangeAddress(toRow.getRowNum(),
	                    (toRow.getRowNum() + (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())),
	                    cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
	            worksheet.addMergedRegionUnsafe(newCellRangeAddress);
	        }
	    }
	}
	
	/**
	 * 复制单元格
	 * @param srcCell 源单元格
	 * @param distCell 目标单元格
	 * @param copyValueFlag true则连同cell的内容一起复制
	 */
	public static void copyCell(Workbook wb, Cell srcCell, Cell distCell, boolean copyValueFlag) {
//	    CellStyle newStyle = wb.createCellStyle();
//	    CellStyle srcStyle = srcCell.getCellStyle();
//
//	    newStyle.cloneStyleFrom(srcStyle);
//	    newStyle.setFont(wb.getFontAt(srcStyle.getFontIndex()));

	    // 样式
	    //distCell.setCellStyle(newStyle);

	    // 内容
	    if (srcCell.getCellComment() != null) {
	        distCell.setCellComment(srcCell.getCellComment());
	    }

	    // 不同数据类型处理
	    CellType srcCellType = srcCell.getCellTypeEnum();
	    distCell.setCellType(srcCellType);

	    if (copyValueFlag) {
	        if (srcCellType == CellType.NUMERIC) {
	            if (DateUtil.isCellDateFormatted(srcCell)) {
	                distCell.setCellValue(srcCell.getDateCellValue());
	            } else {
	                distCell.setCellValue(srcCell.getNumericCellValue());
	            }
	        } else if (srcCellType == CellType.STRING) {
	            distCell.setCellValue(srcCell.getRichStringCellValue());
	        } else if (srcCellType == CellType.BLANK) {

	        } else if (srcCellType == CellType.BOOLEAN) {
	            distCell.setCellValue(srcCell.getBooleanCellValue());
	        } else if (srcCellType == CellType.ERROR) {
	            distCell.setCellErrorValue(srcCell.getErrorCellValue());
	        } else if (srcCellType == CellType.FORMULA) {
	            distCell.setCellFormula(srcCell.getCellFormula());
	        }
	    }
	}
	
	/**
	 * 从excel表格中读取第一个sheet，并以二维数组形式返回
	 * @param file 文件路径
	 * @param sheetNo 读取表下标
	 * @param ignoreLine 忽略行数，忽略第一行则送1
	 * @param maxCol 读取最大列数
	 * @return
	 * @throws IOException
	 */
	public static String[][] getDataFromXls(String file,int sheetNo,int ignoreLine,int maxCol) throws IOException{
		List<String[]> resultList = new ArrayList<String[]>();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		//打开表格
		POIFSFileSystem poiFS=new POIFSFileSystem(bis);
		HSSFWorkbook wb = new HSSFWorkbook(poiFS);
		HSSFSheet hs = null;
		HSSFRow hr = null;
		int rowNum=0;
		int rowSize=0;
		//定位表
		hs=wb.getSheetAt(sheetNo);
		//获取行数
		rowNum = hs.getLastRowNum();
		//跳过忽略行数开始
		for(int rowIndex = ignoreLine;rowIndex<=rowNum;rowIndex++) {
			hr = hs.getRow(rowIndex);
			if(hr==null) {
				continue;
			}
			rowSize = hr.getLastCellNum();
			if(maxCol>0&&rowSize>maxCol) {
				rowSize = maxCol;
			}
			
			String[] values = new String[rowSize];
			Arrays.fill(values, "");
			for(short colIndex = 0; colIndex < rowSize;colIndex++) {
				values[colIndex] = getCellValue(hr.getCell(colIndex));
			}
			//空行不处理
			if(values!=null) {
				resultList.add(values);
			}
		}
		
		bis.close();
		
		if(resultList.isEmpty())return null;
		
		int resultSize = resultList.size();
		String[][] result = new String[resultSize][];
		for(int i=0;i<resultSize;i++) {
			result[i]=resultList.get(i);
		}
		return result;
	}

	public static String getCellValue(HSSFCell cell) {
		if(cell==null)return "";
		String value="";
		switch (cell.getCellType()) {
		case STRING:
			value = cell.getStringCellValue();
			break;
		case NUMERIC:
			if(HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				if(date!=null) {
					value = new SimpleDateFormat("yyyy-MM-dd").format(date);
				}else {
					value="";
				}
			}else {
				value = new DecimalFormat("0").format(cell.getNumericCellValue());
			}
			break;
		case FORMULA:
			if(!cell.getStringCellValue().equals("")) {
				value = cell.getStringCellValue();
			}else {
				value = cell.getNumericCellValue()+"";
			}
			break;
		case BLANK:
			break;
		case ERROR:
			break;
		case BOOLEAN:
			value = cell.getBooleanCellValue()?"true":"false";
			break;
		default:
			break;
		}
		return value;
	}

}