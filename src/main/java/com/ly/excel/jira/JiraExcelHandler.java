package com.ly.excel.jira;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.alibaba.excel.read.metadata.holder.ReadWorkbookHolder;

public class JiraExcelHandler {
    public static void main(String[] args) {
        String filePath = "jira.xls";

        //解析xls
        EasyExcel.read(filePath,JiraData.class,new JiraExcelListener()).sheet().doRead();

        //加载
        ExcelReader excelReader = EasyExcel.read(filePath).build();

        //获取sheet

    }
}
