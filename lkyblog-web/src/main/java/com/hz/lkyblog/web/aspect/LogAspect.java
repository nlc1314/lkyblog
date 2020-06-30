package com.hz.lkyblog.web.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Aspect
@Component
public class LogAspect {

    ThreadLocal<Long> beginTime = new ThreadLocal<>();

    @Pointcut("@annotation(logAnnotation)")
    public void serviceStatistics(LogAnnotation logAnnotation) {
    }

    @Before("serviceStatistics(logAnnotation)")
    public void doBefore(JoinPoint joinPoint, LogAnnotation logAnnotation) {
        // 记录请求到达时间
        String paramValue = getParamOrResultStr(joinPoint);
        beginTime.set(System.currentTimeMillis());
        log.error(" {} start param:{}", joinPoint.getSignature().getDeclaringTypeName(), paramValue);
    }

    @After("serviceStatistics(logAnnotation)")
    public void doAfter(JoinPoint joinPoint, LogAnnotation logAnnotation) {
        String resultValue = getParamOrResultStr(joinPoint);
        log.error(" {} end cost param :{},time:{}", joinPoint.getSignature().getDeclaringTypeName(), resultValue, System.currentTimeMillis() - beginTime.get());
    }

    private String getParamOrResultStr(JoinPoint joinPoint) {
        return Stream.of(joinPoint.getArgs()).map(JSONObject::toJSONString).collect(Collectors.joining(",", "{", "}"));
    }
}
