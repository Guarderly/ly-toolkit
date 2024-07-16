package com.ly.excel.template;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.ly.utils.DateUtil;
import com.ly.utils.NameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class ComTemplateExcelListenerTest {
    private static final Logger logger = LoggerFactory.getLogger(ComTemplateExcelListenerTest.class);

    @Test
    public void testInvoke() {
        //获取通用模版
        String tamplatePath = "jira-tamplate-240706.xls";
        List<Map<Integer, String>> listMap = EasyExcel.read(tamplatePath).sheet().doReadSync();
        for (Map<Integer, String> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            logger.info("读取到数据:{}:{}", JSON.toJSONString(data));
        }
        Map<Integer, String> dataTamplate = ComTemplateDataHandler.parseMapTemplate(listMap.get(0));

        String filePath = "f0705_jira.xls";

        //解析xls
        ComTemplateExcelListener comTemplateExcelListener = new ComTemplateExcelListener();
        EasyExcel.read(filePath, comTemplateExcelListener).sheet().doRead();
        //获取数据
        List<Map<Integer, String>> jiraDataList = comTemplateExcelListener.getDataList();

        //根据模版格式化数据
        ComTemplateDataHandler.formatData(jiraDataList,dataTamplate);

        //输出xls
        String prefix = "f"+ DateUtil.getCurrentDateStr("MMdd")+"_";
        String outFileName = prefix+ NameUtil.getNoPrefixName(filePath,"\\w\\d\\d\\d\\d_");
        String headerPath = "jira-template-header.xls";
        EasyExcel.write(outFileName).withTemplate(headerPath).sheet().doWrite(jiraDataList);

    }
}