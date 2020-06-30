package com.hz.lkyblog.dao.mapper;

import com.hz.lkyblog.dao.model.BlogLink;
import com.hz.lkyblog.dao.query.BlogLinkQuery;
import com.shinemo.perform.common.mybatis.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogLinkMapper {
    int insertSelective(BlogLink record);

    BlogLink getBy(BlogLinkQuery query);

    List<BlogLink> selectBy(BlogLinkQuery query);

    Integer countBy(BlogLinkQuery query);

    Integer updateById(BlogLink record);

    Integer deleteById(@Param("id") Long id);

    Page<BlogLink> pageBy(BlogLinkQuery query);
}