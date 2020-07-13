package com.hz.lkyblog.biz.files.impl;

import com.hz.lkyblog.biz.files.AbstractFileService;
import com.hz.lkyblog.biz.files.FileDownloadService;
import com.hz.lkyblog.biz.files.FilePathEnums;
import com.hz.lkyblog.utils.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileDownloadServiceImpl extends AbstractFileService implements FileDownloadService {


    @Override
    public byte[] getFileBytes(String fileType, String fileDownloadUrl) {
        try {
            String fileDir = FilePathEnums.getFileDir(fileType);
            if (StringUtils.isEmpty(fileDir)) {
                log.error("getFileBytes error fileDir is null");
                return null;
            }
            return FileUtil.readFromFile(FilePathEnums.PIC.getPath() + fileDownloadUrl);
        } catch (Exception e) {
            log.error("getFileBytes error downloadUrl:{} e:{}", fileDownloadUrl, e);
            return null;
        }
    }
}
