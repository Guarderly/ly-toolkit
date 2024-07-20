package com.ly.excel.template;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tool.comTemplateExcelHandler.config")
public class TemplateExcelHandlerConfig {
    //模版路径
    private String templatePath = "jira-template-content.xls";
    //表头路径
    private String headerPath = "jira-template-header.xls";

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getHeaderPath() {
        return headerPath;
    }

    public void setHeaderPath(String headerPath) {
        this.headerPath = headerPath;
    }
}
