package com.ly.excel.jira;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.ly.utils.DateUtil;
import com.ly.utils.ExpressionUtil;

import java.lang.reflect.Field;
import java.util.List;

public class JiraDataHandler {

    /**
     * 根据jira模版更新jiraList记录字段信息
     * @param jiraDataList
     * @param jiraDataFormat
     */
    public static void formatData(List<JiraData> jiraDataList, JiraData jiraDataFormat){
        if(jiraDataFormat ==null||jiraDataList==null){
            return;
        }

        for(JiraData jira:jiraDataList) {
           formatData(jira,jiraDataFormat);
        }
    }

    /**
     * 根据jira模版更新jira记录字段信息
     * @param jiraData
     * @param jiraDataFormat
     */
    public static void formatData(JiraData jiraData,JiraData jiraDataFormat){
        if(jiraDataFormat ==null||jiraData==null){
            return;
        }
        Field[] fields = jiraDataFormat.getClass().getDeclaredFields();
        try {
            for(Field field:fields){
                field.setAccessible(true);
                Object value = field.get(jiraDataFormat);
                if(value!=null){
                    field.set(jiraData,value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }



    /**
     * 生成模板类
     * @return
     */
    public static JiraData createJiraDataModel(){
        JiraData jiraData = new JiraData();
        jiraData.setDepartment("珠海开发一部");
        jiraData.setDesignExpiryDate(DateUtil.getDateStr(1));
        jiraData.setCodingExpiryDate(DateUtil.getDateStr(3));
        jiraData.setFunTestingExpiryDate(DateUtil.getDateStr(5));
        return jiraData;
    }

    /**
     * 生成模板类
     * @return
     */
    public static JiraData parseJiraDataTamplate(JiraData jiraData){
        if(jiraData==null){
            return null;
        }
        Field[] fields = jiraData.getClass().getDeclaredFields();
        try {
            for(Field field:fields){
                field.setAccessible(true);
                Object value = field.get(jiraData);
                if(value!=null){
                    field.set(jiraData, ExpressionUtil.parse(value.toString()));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return jiraData;
    }

    public static void main(String[] args) {
        String filePath = "jira.xls";

        //解析xls
        EasyExcel.read(filePath,JiraData.class,new JiraExcelListener()).sheet().doRead();

        //加载
        ExcelReader excelReader = EasyExcel.read(filePath).build();

        //获取sheet

    }
}
