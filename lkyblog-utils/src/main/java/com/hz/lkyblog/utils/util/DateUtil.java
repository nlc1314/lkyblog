package com.hz.lkyblog.utils.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtil {
    /**
     * 精确到分钟包含中文字符的日期格式
     */
    private static final String noSecond_WhitCh_partten = "yyyy年MM月dd日:HH:mm";
    /**
     * yyMMdd:HH:mm:ss 精确到秒的日期格式
     */
    private static final String yyMMdd_partten = "yyMMdd:HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm:ss 精确到秒的日期格式
     */
    private static final String yyyy_MM_dd_partten = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm 精确到分的日期格式
     */
    private static final String noSecond_yyyy_MM_dd_partten = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd 精确到天的日期格式
     */
    private static final String noHour_yyyy_MM_dd_partten = "yyyy-MM-dd";
    /**
     * yyyy/MM 精确到月的日期格式
     */
    private static final String noDay_yyyycMM_partten = "yyyy/MM";
    /**
     * yyyyMMdd 精确到天的日期格式
     */
    private static final String noHour_yyyyMMdd_partten = "yyyyMMdd";
    /**
     * 只显示当天时分
     */
    private static final String HH_mm_partten = "HH:mm";

    public static final DateFormat noSecond_WhitCh_partten_sdf = new SimpleDateFormat(noSecond_WhitCh_partten);
    public static final DateFormat yyMMdd_partten_sdf = new SimpleDateFormat(yyMMdd_partten);
    public static final DateFormat yyyy_MM_dd_partten_sdf = new SimpleDateFormat(yyyy_MM_dd_partten);
    public static final DateFormat noSecond_yyyy_MM_dd_partten_sdf = new SimpleDateFormat(noSecond_yyyy_MM_dd_partten);
    public static final DateFormat noHour_yyyy_MM_dd_partten_sdf = new SimpleDateFormat(noHour_yyyy_MM_dd_partten);
    public static final DateFormat noDay_yyyycMM_partten_sdf = new SimpleDateFormat(noDay_yyyycMM_partten);
    public static final DateFormat noHour_yyyyMMdd_partten_sdf = new SimpleDateFormat(noHour_yyyyMMdd_partten);
    public static final DateFormat HH_mm_partten_sdf = new SimpleDateFormat(HH_mm_partten);

    /**
     * 根据格式sdf获取日期字符串
     *
     * @param date       日期
     * @param dateFormat sdf格式
     * @return
     */
    public static String getStrByDateFormat(Date date, DateFormat dateFormat) {
        try {
            if (dateFormat == null) {
                log.error("dateFormat is null");
                return "";
            }
            return dateFormat.format(date);
        } catch (Exception e) {
            log.error("getDateStrByDateFormat error:{}", e);
            return "";
        }
    }

    /**
     * 根据sdf格式获取日期
     *
     * @param dateStr    日期字符串
     * @param dateFormat sdf格式
     * @return
     */
    public static Date getDateByDateFormat(String dateStr, DateFormat dateFormat) {
        try {
            if (dateStr == null || "".equals(dateStr)) {
                log.error("dateStr is null");
                return null;
            }
            if (dateFormat == null) {
                log.error("dateFormat is null");
                return null;
            }
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            log.error("getDateByDateFormat error:{}", e);
            return null;
        }
    }

    public static void main(String[] args) {

    }
}
