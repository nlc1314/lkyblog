package com.hz.lkyblog.biz.service;

import com.hz.lkyblog.dao.es.eo.ArticleEO;

import java.util.List;

public interface ArticleService {

    void saveSingleArticle(ArticleEO articleEO);

    ArticleEO getArticleById(Long id);

    List<ArticleEO> getArticles(Integer pageIndex, Integer pageSize);
}
