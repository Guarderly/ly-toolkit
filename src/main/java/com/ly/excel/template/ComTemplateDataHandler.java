package com.ly.excel.template;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.ly.utils.DateUtil;
import com.ly.utils.ExpressionUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComTemplateDataHandler {

    /**
     * 根据jira模版更新jiraList记录字段信息
     */
    public static void formatData(List<Map<Integer, String>> dataList, Map<Integer, String> dataFormat){
        if(dataFormat ==null||dataList==null){
            return;
        }

        for(Map<Integer, String> jira:dataList) {
           formatData(jira,dataFormat);
        }
    }

    /**
     * 根据模版更新记录字段信息
     * @param data
     * @param dataFormat
     */
    public static void formatData(Map<Integer, String> data, Map<Integer, String> dataFormat){
        if(dataFormat ==null||data==null){
            return;
        }

        for(Map.Entry<Integer, String> field:dataFormat.entrySet()){
            Integer key = field.getKey();
            String value = field.getValue();
            data.put(key,value);
        }
    }

    /**
     * 生成模板类
     * @return
     */
    public static Map<Integer, String> parseMapTemplate(Map<Integer, String> templateData){
        if(templateData==null){
            return null;
        }
        for(Map.Entry<Integer, String> field:templateData.entrySet()){
            Integer key = field.getKey();
            String value = field.getValue();
            templateData.put(key,ExpressionUtil.parse(value));
        }
        return templateData;
    }
}
