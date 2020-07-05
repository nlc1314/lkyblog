package com.hz.lkyblog.web;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.hz.*"})
@MapperScan("com.hz.lkyblog.dao.mapper")
@EnableMethodCache(basePackages = "com.hz.lkyblog.biz")
@EnableElasticsearchRepositories(basePackages = "com.hz.lkyblog.dao.es")
@EnableCreateCacheAnnotation
public class LkyblogWebApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(LkyblogWebApplication.class, args);
            System.out.println("==================================================Lkyblog===start===success================================================");
        } catch (Exception e) {
            System.err.println("=========================Lkyblog===start===error================");
            log.error("start error:{}", e);
        }

    }
}
