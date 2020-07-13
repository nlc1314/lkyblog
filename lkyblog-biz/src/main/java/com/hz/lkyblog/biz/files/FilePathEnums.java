package com.hz.lkyblog.biz.files;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public enum FilePathEnums {

    PIC("/Users/lky/Desktop/", "图片保存路径", "1");

    private String path;
    private String msg;
    private String fileType;

    FilePathEnums(String path, String msg, String fileType) {
        this.path = path;
        this.msg = msg;
        this.fileType = fileType;
    }

    public static String getFileDir(String fileType) {
        for (FilePathEnums value : values()) {
            if (value.getFileType().equals(fileType)) {
                return value.getPath();
            }
        }
        return null;
    }
}
