package com.hz.lkyblog.utils.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class FileUtil {


    /**
     * 写文件
     *
     * @param bytes    文件流
     * @param pathName 文件路径
     */
    public static void writeToFile(byte[] bytes, String pathName) {
        File file = new File(pathName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException e:{}", e);
        } catch (IOException e) {
            log.error("IOException e:{}", e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error("IOException e:{}", e);
                }
            }
        }
    }


    /**
     * 将文件读取成文件流
     *
     * @param pathName 文件路径
     * @return
     */
    public static byte[] readFromFile(String pathName) {
        FileInputStream fis = null;
        File file = new File(pathName);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            while (fis.read(buffer) != -1) {
                bos.write(buffer);
            }
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            log.error("FileNotFound exception e:{}", e);
            return null;
        } catch (IOException e) {
            log.error("IOException exception e:{}", e);
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("bos close exception e:{}", e);
            }
        }
    }


    //获取上一天凌晨
    public static Date getDayBeforeZeroTimeStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);//设置为上一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    //获取当天凌晨
    public static Date getDayNowZeroTimeStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 获取文件md5
     *
     * @param bytes
     * @return
     */
    public static String getFileMd5(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte b[] = md.digest();
            int d;
            for (int i = 0; i < b.length; i++) {
                d = b[i];
                if (d < 0) {
                    d = b[i] & 0xff;
                    // 与上一行效果等同
                    // i += 256;
                }
                if (d < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(d));
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("");
            return "";
        }
    }

    public static void main(String[] args) {
        byte[] bytes = readFromFile("/Users/lky/Desktop/file.png");
        writeToFile(bytes, "/Users/lky/Desktop/file1.png");
    }
}
