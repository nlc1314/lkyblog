package com.hz.lkyblog.biz.service.impl;

import com.hz.lkyblog.biz.service.ArticleService;
import com.hz.lkyblog.dao.es.eo.ArticleEO;
import com.hz.lkyblog.dao.es.service.ArticleEsService;
import com.hz.lkyblog.dao.model.BlogArticle;
import com.hz.lkyblog.dao.model.BlogArticleWithBLOBs;
import com.hz.lkyblog.dao.query.BlogArticleQuery;
import com.hz.lkyblog.dao.wrapper.BlogArticleWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleEsService articleEsService;

    @Resource
    private BlogArticleWrapper blogArticleWrapper;

    @Override
    public void saveSingleArticle(ArticleEO articleEO) {
        BlogArticleWithBLOBs blogArticle = buildEO2DO(articleEO);
        int i;
        if (articleEO.getId() != null && articleEO.getId() != 0L) {
            //update
            i = blogArticleWrapper.updateById(blogArticle);
        } else {
            //insert
            i = blogArticleWrapper.insertSelective(blogArticle);
        }
        if (i > 0) {
            articleEsService.saveSingleArticle(articleEO);
        }
    }

    @Override
    public ArticleEO getArticleById(Long id) {
        BlogArticleWithBLOBs by = blogArticleWrapper.getBy(BlogArticleQuery.builder().id(id).build());
        return Optional
                .ofNullable(by)
                .map(this::buildDO2EO)
                .orElse(null);
    }

    @Override
    public List<ArticleEO> getArticles(Integer pageIndex, Integer pageSize) {
        List<BlogArticleWithBLOBs> blogArticleWithBLOBs = blogArticleWrapper.selectBy(BlogArticleQuery.builder().pageIndex(pageIndex).pageSize(pageSize).build());
        if (CollectionUtils.isEmpty(blogArticleWithBLOBs)) {
            return Collections.emptyList();
        }
        return blogArticleWithBLOBs
                .stream()
                .map(this::buildDO2EO)
                .collect(Collectors.toList());
    }

    private BlogArticleWithBLOBs buildEO2DO(ArticleEO articleEO) {
        BlogArticleWithBLOBs bloBs = new BlogArticleWithBLOBs();
        BeanUtils.copyProperties(articleEO,bloBs);
        bloBs.setCreateTime(new Date());
        bloBs.setPublishTime(new Date());
        return bloBs;
    }

    private ArticleEO buildDO2EO(BlogArticleWithBLOBs blogArticleWithBLOBs) {
        ArticleEO articleEO = new ArticleEO();
        articleEO.setContext(blogArticleWithBLOBs.getContext());
        BeanUtils.copyProperties(blogArticleWithBLOBs,articleEO);
        articleEO.setCreateTime(new Date());
        articleEO.setPublishTime(new Date());
        return articleEO;
    }
}
