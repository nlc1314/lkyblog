package com.hz.lkyblog.dao.es.rep;

import com.hz.lkyblog.dao.es.eo.ArticleEO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ArticleRepository extends ElasticsearchRepository<ArticleEO, Long> {
}
