package com.hz.lkyblog.web.exception;


import lombok.Data;

@Data
public class NonAuthException extends RuntimeException {

    public NonAuthException(String msg) {
        super(msg);
    }


}
