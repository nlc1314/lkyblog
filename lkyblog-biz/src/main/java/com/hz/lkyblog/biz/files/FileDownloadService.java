package com.hz.lkyblog.biz.files;

public interface FileDownloadService {

    /**
     * 获取文件下载流
     *
     * @param fileDownloadUrl
     * @return
     */
    byte[] getFileBytes(String fileType, String fileDownloadUrl);


}
