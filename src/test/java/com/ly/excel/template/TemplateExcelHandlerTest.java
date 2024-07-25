package com.ly.excel.template;

import org.springframework.util.StringUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TemplateExcelHandlerTest {

    @Test
    public void testHandle() {
        //获取文件名
        String filePath = "jira.xls";//args[0];

        //获取通用模版
        String headerPath = "jira-template-header.xls";
        String templatePath = "jira-template-content.xls";
        TemplateExcelHandlerConfig templateExcelHandlerConfig = new TemplateExcelHandlerConfig();
        templateExcelHandlerConfig.setTemplatePath(templatePath);
        templateExcelHandlerConfig.setHeaderPath(headerPath);

        TemplateExcelHandler.handle(filePath, templateExcelHandlerConfig);
    }

    public static void main(String[] args) {
        //获取文件名
        String filePath = args[0];

        //获取通用模版
        String headerPath = "jira-template-header.xls";
        String templatePath = "jira-template-content.xls";
        TemplateExcelHandlerConfig templateExcelHandlerConfig = new TemplateExcelHandlerConfig();
        templateExcelHandlerConfig.setTemplatePath(templatePath);
        templateExcelHandlerConfig.setHeaderPath(headerPath);

        TemplateExcelHandler.handle(filePath, templateExcelHandlerConfig);
    }
}