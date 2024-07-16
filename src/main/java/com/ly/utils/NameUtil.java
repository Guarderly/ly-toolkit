package com.ly.utils;

import org.apache.poi.util.StringUtil;

public class NameUtil {
    /**
     * 去前缀
     * @param name
     * @param prefix
     * @return
     */
    public static String getNoPrefixName(String name,String prefix){

       return name.replaceFirst(prefix,"");
    }

    public static String getFileName(String path){
        return path.replaceAll(".*\\\\","");
    }
}
