package com.ly.excel.jira;

//import static org.testng.Assert.*;

import com.alibaba.excel.EasyExcel;
import com.ly.utils.DateUtil;
import com.ly.utils.NameUtil;

import java.util.List;

public class JiraExcelListenerTest {
    public static void main(String[] args) {
        String filePath = "f0705_jira.xls";

        //解析xls
        JiraExcelListener jiraExcelListener = new JiraExcelListener();
        EasyExcel.read(filePath,JiraData.class,jiraExcelListener).sheet().doRead();
        //获取数据
        List<JiraData> jiraDataList = jiraExcelListener.getDataList();
        //获取模版
        String jiraTamplatePath = "jira-tamplate-240706.xls";
        JiraExcelListener jiraTamplateExcelListener = new JiraExcelListener();
        EasyExcel.read(jiraTamplatePath,JiraData.class,jiraTamplateExcelListener).sheet().doRead();
        //解析模版，处理表达式内容
        JiraData jiraDataTamplate= JiraDataHandler.parseJiraDataTamplate(jiraTamplateExcelListener.getDataList().get(0));
        //根据模版格式化数据
        JiraDataHandler.formatData(jiraDataList,JiraDataHandler.createJiraDataModel());
        //输出xls
        String prefix = "f"+ DateUtil.getCurrentDateStr("MMdd")+"_";
        String outFileName = prefix+NameUtil.getNoPrefixName(filePath,"\\w\\d\\d\\d\\d_");
        EasyExcel.write(outFileName,JiraData.class).sheet().doWrite(jiraDataList);
    }
}