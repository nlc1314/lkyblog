package com.hz.lkyblog.dao.mapper;

import com.hz.lkyblog.dao.model.BlogArticleWithBLOBs;
import com.hz.lkyblog.dao.query.BlogArticleQuery;
import com.shinemo.perform.common.mybatis.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogArticleMapper {
    int insertSelective(BlogArticleWithBLOBs record);

    BlogArticleWithBLOBs getBy(BlogArticleQuery query);

    List<BlogArticleWithBLOBs> selectBy(BlogArticleQuery query);

    Integer countBy(BlogArticleQuery query);

    Integer updateById(BlogArticleWithBLOBs record);

    Integer deleteById(@Param("id") Long id);

    Page<BlogArticleWithBLOBs> pageBy(BlogArticleQuery query);
}