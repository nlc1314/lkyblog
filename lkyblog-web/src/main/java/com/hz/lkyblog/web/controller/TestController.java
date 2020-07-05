package com.hz.lkyblog.web.controller;

import com.alibaba.fastjson.JSON;
import com.hz.lkyblog.dao.es.eo.ArticleEO;
import com.hz.lkyblog.dao.es.service.ArticleEsService;
import com.hz.lkyblog.utils.rest.WebResult;
import com.hz.lkyblog.web.aspect.IgnoreLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("test")
public class TestController {


    @Resource
    private ArticleEsService articleEsService;


    @IgnoreLogin
    @RequestMapping(value = "/saveArticle", method = RequestMethod.POST)
    @ResponseBody
    public WebResult<Void> saveArticle(@RequestBody ArticleEO articleEO) {
        articleEsService.saveSingleArticle(articleEO);
        return WebResult.buildSuccessResponse();
    }


    @IgnoreLogin
    @RequestMapping(value = "/getArticle", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<ArticleEO> getArticle(@RequestParam(value = "id") Long id) {
        ArticleEO articleById = articleEsService.getArticleById(id);
        return WebResult.buildSuccessResponse(articleById);
    }

    @IgnoreLogin
    @RequestMapping(value = "/getAllArticle", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<List<ArticleEO>> getAllArticle(@RequestParam(value = "key") String key) {
        List<ArticleEO> articles = articleEsService.getArticles(key);
        return WebResult.buildSuccessResponse(articles);
    }

    public static void main(String[] args) {
        ArticleEO articleEO = new ArticleEO();
        articleEO.setCreateTime(new Date());
        articleEO.setDesc("我就是测试下");
        articleEO.setId(1L);
        articleEO.setTagId(2L);
        articleEO.setName("测试文章1");
        articleEO.setType(1);
        articleEO.setPublishTime(new Date());

        System.out.println(JSON.toJSON(articleEO));
    }

}
