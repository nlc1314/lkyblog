package com.hz.lkyblog.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AdminStatusEnum {
    COMMON(1, "正常状态"),
    UN_SAVE(2, "禁止修改"),
    UN_LOGIN(3, "禁止登录"),
    ;
    private Integer status;
    private String msg;

    public static String getMsg(Integer status) {
        for (AdminStatusEnum value : values()) {
            if (value.status.equals(status)) {
                return value.getMsg();
            }
        }
        return COMMON.getMsg();
    }
}
