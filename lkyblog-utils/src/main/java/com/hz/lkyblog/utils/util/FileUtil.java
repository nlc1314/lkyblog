package com.hz.lkyblog.utils.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

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

    public static void main(String[] args) {
        byte[] bytes = readFromFile("/Users/lky/Desktop/file.png");
        writeToFile(bytes, "/Users/lky/Desktop/file1.png");
    }
}
