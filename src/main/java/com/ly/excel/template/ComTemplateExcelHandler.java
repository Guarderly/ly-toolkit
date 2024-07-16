package com.ly.excel.template;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.ly.utils.DateUtil;
import com.ly.utils.NameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


public class ComTemplateExcelHandler {
        private static final Logger logger = LoggerFactory.getLogger(ComTemplateExcelHandler.class);
    public static void main(String[] args) {
            if(StringUtils.isEmpty(args)){
                    return;
            }

            //获取文件名
            //String filePath = "D:\\git\\ly-toolkit\\out\\artifacts\\ly_toolkit_jar\\jira.xls";//args[0];
            String filePath = args[0];

            //获取通用模版
            String tamplatePath = "jira-template-content.xls";
            List<Map<Integer, String>> listMap = EasyExcel.read(tamplatePath).sheet().doReadSync();
            for (Map<Integer, String> data : listMap) {
                // 返回每条数据的键值对 表示所在的列 和所在列的值
                logger.info("读取到数据:{}:{}", JSON.toJSONString(data));
            }
            Map<Integer, String> dataTamplate = ComTemplateDataHandler.parseMapTemplate(listMap.get(0));

            //解析xls
            ComTemplateExcelListener comTemplateExcelListener = new ComTemplateExcelListener();
            EasyExcel.read(filePath, comTemplateExcelListener).sheet().doRead();
            //获取数据
            List<Map<Integer, String>> jiraDataList = comTemplateExcelListener.getDataList();

            //根据模版格式化数据
            ComTemplateDataHandler.formatData(jiraDataList,dataTamplate);

            //输出xls
            String prefix = "f"+ DateUtil.getCurrentDateStr("MMdd")+"_";
            String outFileName = prefix+ NameUtil.getNoPrefixName(NameUtil.getFileName(filePath),"\\w\\d\\d\\d\\d_");
            String headerPath = "jira-template-header.xls";
            EasyExcel.write(outFileName).withTemplate(headerPath).sheet().doWrite(jiraDataList);
    }
}
