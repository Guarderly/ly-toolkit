package com.ly.utils;

public class ExpressionUtil {
    public static final String METHOD_DATE = "DATE";

    /**
     * 表达式解析
     * @param str
     * @return
     */
    public static String parse(String str){
        if(!str.matches("\\$\\{.*}")){
            return str;
        }
        //拆解表达式
        String result = str.replaceFirst("\\$\\{","");
        result = result.substring(0,result.length()-1);
        String[] agrs = result.split(":");
        if(agrs.length<=0){
            return str;
        }
        //分拣表达式逻辑
        switch (agrs[0]){
            case METHOD_DATE:result=DateUtil.getDateStr(agrs.length==0?"0":agrs[1]);
                break;
            default:
                break;
        }
        return result;
    }
}
