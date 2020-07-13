package com.hz.lkyblog.biz.files.impl;

import com.hz.lkyblog.biz.files.AbstractFileService;
import com.hz.lkyblog.biz.files.FilePathEnums;
import com.hz.lkyblog.biz.files.FileUploadService;
import com.hz.lkyblog.utils.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Slf4j
@Service
public class FileUploadServiceImpl extends AbstractFileService implements FileUploadService {

    @Override
    public String uploadPicFile(byte[] fileBytes, String fileName) {
        try {
            //获取文件名称
            String fileMd5 = FileUtil.getFileMd5(fileBytes);
            String fileDownloadUrl = getDownloadUrl(fileName, fileMd5);
            //获取文件的保存完整路径
            String filePath = getFilePath(FilePathEnums.PIC.getPath(), getRealFileName(fileName, fileMd5));
            FileUtil.writeToFile(fileBytes, filePath);
            return fileDownloadUrl;
        } catch (Exception e) {
            log.error("uploadPicFile error fileName:{}, e:{}", fileName, e);
            return "";
        }
    }

    private String getDownloadUrl(String fileName, String fileMd5) throws UnknownHostException {
        return getDownloadPre() + FilePathEnums.PIC.getFileType() + "/" + getRealFileName(fileName, fileMd5);
    }

    private String getRealFileName(String fileName, String fileMd5) {
        return fileMd5 + "_" + fileName;
    }
}
