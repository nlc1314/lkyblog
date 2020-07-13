package com.hz.lkyblog.dao.es.service;

import com.hz.lkyblog.dao.es.eo.ArticleEO;
import io.swagger.models.auth.In;

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
     * @param key  关键字
     * @param page 页码
     * @param size 每页大小
     * @return
     */

    List<ArticleEO> getArticles(String key, Integer page, Integer size);


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
    void saveBatchArticle(List<ArticleEO> articleEOS);


    /**
     * 根据文章id批量删除文章
     *
     * @param articleIds
     */
    void deleteArticleByIds(List<Long> articleIds);


}
