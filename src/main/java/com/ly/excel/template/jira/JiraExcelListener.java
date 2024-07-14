package com.ly.excel.template.jira;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JiraExcelListener extends AnalysisEventListener<JiraData> {
    private static final Logger logger = LoggerFactory.getLogger(JiraExcelListener.class);

    private static final int BATCH_MAX_COUNT = 10000;//最多一万条

    private List<JiraData> dataList = new ArrayList<JiraData>();

    @Override
    public void invoke(JiraData jiraData, AnalysisContext analysisContext) {
        logger.info("解析到数据：{}", jiraData.toString());
        dataList.add(jiraData);
        if(dataList.size()>= BATCH_MAX_COUNT){
            logger.info("数据达到最大值，清理缓存");
            dataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        logger.info("sheet={} 所有数据解析完成！", analysisContext.readSheetHolder().getSheetName());
    }

    public List<JiraData> getDataList() {
        return dataList;
    }
}
