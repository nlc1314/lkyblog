package com.hz.lkyblog.dao.es.service;

import com.hz.lkyblog.dao.es.eo.ArticleEO;

import java.util.List;

/**
 * 文章es全文检索查询
 */
public interface ArticleEsService {



    /**
     * 根据id查询文章
     *
     * @param id
     * @return
     */

    ArticleEO getArticleById(Long id);

    /**
     * 根据关键字查询文章
     *
     * @param key
     * @return
     */

    List<ArticleEO> getArticles(String key);


    /**
     * 保存单个文章到es
     *
     * @param articleEO
     */
    void saveSingleArticle(ArticleEO articleEO);


    /**
     * 保存多个文章到es
     *
     * @param articleEOS
     */
    void saveMultArticle(List<ArticleEO> articleEOS);


}
