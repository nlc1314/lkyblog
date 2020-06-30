package com.hz.lkyblog.utils.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtil {
    private static final String commonpartten = "yyyy年MM月dd日:HH:mm";
    private static final String yypartten = "yyMMdd:HH:mm:ss";
    private static final String partten = "yyyy-MM-dd HH:mm:ss";
    private static final String commonpartten1 = "yyyy-MM-dd HH:mm";
    private static final String partten1 = "yyyy-MM-dd";
    private static final String partten2 = "yyyy/MM";
    private static final String noHourpartten = "yyyyMMdd";

    private static final String today = "HH:mm";//今天

    private static DateFormat commonsdf = new SimpleDateFormat(commonpartten1);
    private static DateFormat yysdf = new SimpleDateFormat(yypartten);
    private static DateFormat ysdf = new SimpleDateFormat(partten);
    private static DateFormat ysdf1 = new SimpleDateFormat(partten1);
    private static DateFormat ysdf2 = new SimpleDateFormat(partten2);
    private static DateFormat noHoursdf = new SimpleDateFormat(noHourpartten);

    private static DateFormat todaysdf = new SimpleDateFormat(today);

    private static Calendar calendar;

    public static String getCommonsDateStrOfOperations(Date date) {
        try {
            String format = "";
            calendar = Calendar.getInstance();
            //获取当前的日期，
            int now = calendar.get(Calendar.DAY_OF_YEAR);

            calendar.setTime(date);
            int obj = calendar.get(Calendar.DAY_OF_YEAR);
            if (now - obj == 0) {//今天
                format = todaysdf.format(date);
            } else if (now - obj == 1) {//昨天
                format = "昨天 " + todaysdf.format(date);
            } else {//两天前
                format = commonsdf.format(date);
            }
            return format;
        } catch (Exception e) {
            log.error("getCommonsDateStr error", e);
            return "";
        }
    }

    public static String getCommonsDateStr(Date date) {
        try {
            String format = ysdf1.format(date);
            return format;
        } catch (Exception e) {
            log.error("getCommonsDateStr error", e);
            return "";
        }
    }

    public static Date getDateFromCommonsStr(String dateStr) {
        try {
            if (dateStr == null || "".equals(dateStr)) {
                return null;
            }
            Date parse = ysdf1.parse(dateStr);
            return parse;
        } catch (Exception e) {
            log.error("getDateFromCommonsStr error", e);
            return null;
        }
    }


    public static String getCommonsDateStr1(Date date) {
        try {
            if (date == null) {
                return "";
            }
            String format = ysdf2.format(date);
            return format;
        } catch (Exception e) {
            log.error("getCommonsDateStr1 error", e);
            return "";
        }
    }

    public static Date getDateFromCommonsStr1(String dateStr) {
        try {
            if (dateStr == null || "".equals(dateStr)) {
                return null;
            }
            Date parse = ysdf2.parse(dateStr);
            return parse;
        } catch (Exception e) {
            log.error("getDateFromCommonsStr1 error", e);
            return null;
        }
    }

    public static Date getDateFromInterViewStr(String dateStr) {
        try {
            if (dateStr == null || "".equals(dateStr)) {
                return null;
            }
            Date parse = commonsdf.parse(dateStr);
            return parse;
        } catch (Exception e) {
            log.error("getDateFromCommonsStr error", e);
            return null;
        }
    }

    public static String getFromInterViewStr(Date date) {
        try {
            String format = commonsdf.format(date);
            return format;
        } catch (Exception e) {
            log.error("getCommonsDateStr error", e);
            return "";
        }
    }
}
