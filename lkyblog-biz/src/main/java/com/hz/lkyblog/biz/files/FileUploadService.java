package com.hz.lkyblog.biz.files;

public interface FileUploadService {

    /**
     * 将文件上传到指定文件夹中
     *
     * @param fileBytes
     * @param fileName
     */
    String uploadPicFile(byte[] fileBytes, String fileName);


}
