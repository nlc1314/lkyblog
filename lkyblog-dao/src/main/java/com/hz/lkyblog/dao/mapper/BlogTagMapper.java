package com.hz.lkyblog.dao.mapper;

import com.hz.lkyblog.dao.model.BlogTag;
import com.hz.lkyblog.dao.query.BlogTagQuery;
import com.shinemo.perform.common.mybatis.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogTagMapper {
    int insertSelective(BlogTag record);

    BlogTag getBy(BlogTagQuery query);

    List<BlogTag> selectBy(BlogTagQuery query);

    Integer countBy(BlogTagQuery query);

    Integer updateById(BlogTag record);

    Integer deleteById(@Param("id") Long id);

    Page<BlogTag> pageBy(BlogTagQuery query);
}