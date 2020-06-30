package com.hz.lkyblog.utils.rest;


import com.hz.lkyblog.utils.constans.ErrorConstants;
import lombok.Data;

@Data
public class WebResult<T> {
    private boolean success;
    private T data;
    private String msg;
    private Integer code;

    public static <T> WebResult<T> buildSuccessResponse(T data) {
        WebResult<T> r = new WebResult<>();
        r.setSuccess(true);
        r.setData(data);
        r.setCode(200);
        return r;
    }


    public static <T> WebResult<T> buildSuccessResponse() {
        return buildSuccessResponse(null);
    }


    public static <T> WebResult<T> buildServerErrorResponse() {
        WebResult<T> r = new WebResult<>();
        r.setMsg(ErrorConstants.SERVER_ERROR);
        r.setSuccess(false);
        return r;
    }

    public static <T> WebResult<T> buildFailedResponse(String message) {
        WebResult<T> r = new WebResult<>();
        r.setMsg(message);
        r.setSuccess(false);
        r.setCode(500);
        return r;
    }

    public static <T> WebResult<T> buildFailedResponse(String message, int code) {
        WebResult<T> r = new WebResult<>();
        r.setMsg(message);
        r.setSuccess(false);
        r.setCode(code);
        return r;
    }
}
