package com.ly.excel.template;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComTemplateExcelListener extends AnalysisEventListener<Map<Integer, String>> {
    private static final Logger logger = LoggerFactory.getLogger(ComTemplateExcelListener.class);

    private static final int BATCH_MAX_COUNT = 10000;//最多一万条

    private final List<Map<Integer, String>> dataList = new ArrayList<>();

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext analysisContext) {
        logger.info("解析到数据：{}", data.toString());
        dataList.add(data);
        if(dataList.size()>= BATCH_MAX_COUNT){
            logger.info("数据达到最大值，清理缓存");
            dataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        logger.info("sheet={} 所有数据解析完成！", analysisContext.readSheetHolder().getSheetName());
    }

    public List<Map<Integer, String>> getDataList() {
        return dataList;
    }
}
