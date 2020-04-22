package com.ly.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ly.base.Result;
import com.ly.utils.FileUtil;
import com.ly.utils.XlsUtil;

public class XlsToolModel {

	/**
	 * 从excel表格中读取第一个sheet，并以二维数组形式返回
	 * @param file 文件路径
	 * @param sheetNo 读取表下标
	 * @param ignoreLine 忽略行数，忽略第一行则送1
	 * @param maxCol 读取最大列数
	 * @return
	 * @throws IOException
	 */
	/**
	 * 从excel拆分出到其他文件
	 * @param inFilePath 文件路径
	 * @param colIndex 
	 * @param outPath
	 * @return
	 * @throws IOException 
	 */
	public static Result splitByCol(String inFilePath,int colIndex,String outPath) throws IOException {
		
			List<String[]> resultList = new ArrayList<String[]>();
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inFilePath));
			//打开表格
			POIFSFileSystem poiFS=new POIFSFileSystem(bis);
			HSSFWorkbook wb = new HSSFWorkbook(poiFS);
			HSSFSheet hs = null;
			HSSFRow hr = null;
			int rowNum=0;
			int rowSize=0;
			//定位表
			hs=wb.getSheetAt(0);
			//获取行数
			rowNum = hs.getLastRowNum();
			//获取首行
			HSSFRow rowHead = hs.getRow(0);
			//跳过首行开始
			HashMap<String,HSSFWorkbook> bookMap = new HashMap<String, HSSFWorkbook>();
			for(int rowIndex = 1;rowIndex<=rowNum;rowIndex++) {
				hr = hs.getRow(rowIndex);
				if(hr==null) {
					continue;
				}
				String key = XlsUtil.getCellValue(hr.getCell(colIndex));
				HSSFWorkbook currHWB = bookMap.get(key);
				HSSFSheet currSheet = null;
				//空则创建
				if(currHWB==null) {
					currHWB = new HSSFWorkbook();
					currSheet = currHWB.createSheet();
					HSSFRow currRow = currSheet.createRow(0);
					//拷贝首行
					XlsUtil.copyRow(wb, rowHead, currRow, true);
					bookMap.put(key, currHWB);
				}
				//读取表格创建行
				currSheet = currHWB.getSheetAt(0);
				int lastIndex = currSheet.getLastRowNum();
				HSSFRow currRow = currSheet.createRow(lastIndex+1);
				//拷贝行
				XlsUtil.copyRow(wb, hr, currRow, true);
			}
			
			//根据hashmap拆分文件
			Set<Entry<String, HSSFWorkbook>> wbSet = bookMap.entrySet();
			for (Entry<String, HSSFWorkbook> entry : wbSet) {
				String fileName = entry.getKey();
				HSSFWorkbook outBook = entry.getValue();
				//先创建目录
				FileUtil.createDir(outPath);
				//写文件
				String outFileName = outPath+"/"+fileName+".xls";
				OutputStream out = new FileOutputStream(outFileName);
				outBook.write(out);
				out.close();
			}
			
			//关闭读文件
			bis.close();	
			return new Result();
	}

}
