package com.ly.excel.template;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.ly.utils.DateUtil;
import com.ly.utils.NameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Component
public class TemplateExcelHandler {
    private static final Logger logger = LoggerFactory.getLogger(TemplateExcelHandler.class);

    @Autowired
    private TemplateExcelHandlerConfig config;

    public void handle(String filePath){
        TemplateExcelHandler.handle(filePath,config);
    }

    public static void handle(String filePath, TemplateExcelHandlerConfig config) {
        List<Map<Integer, String>> listMap = EasyExcel.read(config.getTemplatePath()).sheet().doReadSync();
        for (Map<Integer, String> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            logger.info("读取到数据:{}", JSON.toJSONString(data));
        }
        Map<Integer, String> dataTemplate = TemplateDataHandler.parseMapTemplate(listMap.get(0));

        //解析xls
        TemplateExcelListener templateExcelListener = new TemplateExcelListener();
        EasyExcel.read(filePath, templateExcelListener).sheet().doRead();
        //获取数据
        List<Map<Integer, String>> jiraDataList = templateExcelListener.getDataList();

        //根据模版格式化数据
        TemplateDataHandler.formatData(jiraDataList, dataTemplate);

        //输出xls
        String prefix = "f" + DateUtil.getCurrentDateStr("MMdd") + "_";
        String outFileName = prefix + NameUtil.getNoPrefixName(NameUtil.getFileName(filePath), "\\w\\d\\d\\d\\d_");
        EasyExcel.write(outFileName).withTemplate(config.getHeaderPath()).sheet().doWrite(jiraDataList);
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
