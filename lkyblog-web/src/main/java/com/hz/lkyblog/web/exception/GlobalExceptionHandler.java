package com.hz.lkyblog.web.exception;

import com.hz.lkyblog.utils.rest.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Validated 约束在参数前的校验处理
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResult<?> handleMethodValidatorException(MethodArgumentNotValidException e) {
        log.error("参数校验失败", e);
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        final StringBuilder builder = new StringBuilder();
        errorList.forEach(error -> builder.append(error.getDefaultMessage()).append(","));
        return WebResult.buildFailedResponse(builder.length() > 1 ? builder.substring(0, builder.length() - 1) : "");
    }

    /**
     * 全局异常处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public WebResult<String> handleException(Throwable e) {
        log.error("服务运行异常", e);
        return WebResult.buildFailedResponse(e.getMessage() == null ? "服务器错误" : e.getMessage());
    }

    /**
     * 全局异常处理
     */
    @ExceptionHandler(NonAuthException.class)
    public WebResult<String> handleNonAuthException(NonAuthException e) {
        log.error("登录失效", e);
        return WebResult.buildFailedResponse(e.getMessage() == null ? "登录失效" : e.getMessage(), 602);
    }
}
