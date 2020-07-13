package com.hz.lkyblog.web.controller;

import com.hz.lkyblog.biz.service.ArticleService;
import com.hz.lkyblog.dao.es.eo.ArticleEO;
import com.hz.lkyblog.dao.es.service.ArticleEsService;
import com.hz.lkyblog.utils.rest.WebResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleEsService articleEsService;

    @Resource
    private ArticleService articleService;

    @RequestMapping(value = "/saveArticle", method = RequestMethod.POST)
    @ResponseBody
    public WebResult<Void> saveArticle(@RequestBody ArticleEO articleEO) {
        articleService.saveSingleArticle(articleEO);
        return WebResult.buildSuccessResponse();
    }

    @RequestMapping(value = "/getArticleDetail", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<ArticleEO> getArticle(@RequestParam(value = "id") Long id) {
        ArticleEO articleById = articleService.getArticleById(id);
        return WebResult.buildSuccessResponse(articleById);
    }

    @RequestMapping(value = "/getArticles", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<List<ArticleEO>> getArticles(@RequestParam(value = "pageIndex") Integer pageIndex,
                                                    @RequestParam(value = "pageSize") Integer pageSize) {
        List<ArticleEO> articles = articleService.getArticles(pageIndex, pageSize);
        return WebResult.buildSuccessResponse(articles);
    }

    @RequestMapping(value = "/searchArticle", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<List<ArticleEO>> searchArticle(@RequestParam(value = "key",required = false) String key,
                                                    @RequestParam(value = "pageIndex") Integer pageIndex,
                                                    @RequestParam(value = "pageSize") Integer pageSize) {
        List<ArticleEO> articles = articleEsService.getArticles(key, pageIndex, pageSize);
        return WebResult.buildSuccessResponse(articles);
    }
}
