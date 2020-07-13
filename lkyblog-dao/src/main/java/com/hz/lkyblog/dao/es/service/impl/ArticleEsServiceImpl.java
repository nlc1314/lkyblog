package com.hz.lkyblog.dao.es.service.impl;

import com.google.common.collect.Lists;
import com.hz.lkyblog.dao.es.eo.ArticleEO;
import com.hz.lkyblog.dao.es.rep.ArticleRepository;
import com.hz.lkyblog.dao.es.service.ArticleEsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    public List<ArticleEO> getArticles(String key, Integer page, Integer size) {
        Iterable<ArticleEO> search;
        if (StringUtils.isEmpty(key)) {
            search = articleRepository.findAll(PageRequest.of(page-1, size));
        } else {
            BoolQueryBuilder queryBuilder = QueryBuilders
                    .boolQuery()
                    .should(QueryBuilders.matchQuery("name", key))
                    .should(QueryBuilders.matchQuery("desc", key))
                    .should(QueryBuilders.matchQuery("context", key));
            search = articleRepository.search(queryBuilder, PageRequest.of(page-1, size));
        }
        Iterator<ArticleEO> iterator = search.iterator();
        List<ArticleEO> articleEOS = Lists.newArrayList();
        while (iterator.hasNext()) {
            articleEOS.add(iterator.next());
        }
        return articleEOS;
    }

    @Override
    public void saveSingleArticle(ArticleEO articleEO) {
        if (articleEO == null) {
            return;
        }
        articleRepository.save(articleEO);
    }

    @Override
    public void saveBatchArticle(List<ArticleEO> articleEOS) {
        if (CollectionUtils.isEmpty(articleEOS)) {
            return;
        }
        articleRepository.saveAll(articleEOS);
    }

    @Override
    public void deleteArticleByIds(List<Long> articleIds) {
        if (CollectionUtils.isEmpty(articleIds)) {
            return;
        }
        articleIds.forEach(a -> articleRepository.deleteById(a));
    }
}
