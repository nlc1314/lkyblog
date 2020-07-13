package com.hz.lkyblog.biz.files;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class AbstractFileService {
    protected final static String FILE_LD_STR = "lkyblog/filedl/";


    protected String getDownloadPre() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        return addr.getHostAddress() + ":8989/" + FILE_LD_STR;
    }


    /**
     * 获取每天生成的文件名称
     *
     * @param fileName
     * @return
     */

    protected static String getFilePath(String fileDir, String fileName) {
        return fileDir + "/" + fileName;
    }
}
