package com.ly.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static String getDateStr(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }

    public static String getCurrentDateStr(String format){
        return DateUtil.getDateStr(new Date(),format);
    }

    public static String getCurrentDateStr(){
        return DateUtil.getDateStr(new Date(), DEFAULT_DATE_FORMAT);
    }

    public static String getDateStr(int difference){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,difference);
        return DateUtil.getDateStr(calendar.getTime(), DEFAULT_DATE_FORMAT);
    }

    public static String getDateStr(String differenceStr){
        int difference = 0;
        difference = Integer.parseInt(differenceStr);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,difference);
        return DateUtil.getDateStr(calendar.getTime(), DEFAULT_DATE_FORMAT);
    }
}
