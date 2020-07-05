package com.hz.lkyblog.dao.es.service.impl;

import com.google.common.collect.Lists;
import com.hz.lkyblog.dao.es.eo.ArticleEO;
import com.hz.lkyblog.dao.es.rep.ArticleRepository;
import com.hz.lkyblog.dao.es.service.ArticleEsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class ArticleEsServiceImpl implements ArticleEsService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ArticleEO getArticleById(Long id) {
        Optional<ArticleEO> byId = articleRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public List<ArticleEO> getArticles(String key) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.matchQuery("name",key));
        Iterable<ArticleEO> search = articleRepository.search(queryBuilder);
        Iterator<ArticleEO> iterator = search.iterator();
        List<ArticleEO> articleEOS = Lists.newArrayList();
        while (iterator.hasNext()) {
            articleEOS.add(iterator.next());
        }
        return articleEOS;
    }

    @Override
    public void saveSingleArticle(ArticleEO articleEO) {
        articleRepository.save(articleEO);
    }

    @Override
    public void saveMultArticle(List<ArticleEO> articleEOS) {

    }
}
