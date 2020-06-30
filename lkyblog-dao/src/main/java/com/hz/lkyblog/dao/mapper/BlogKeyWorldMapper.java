package com.hz.lkyblog.dao.mapper;

import com.hz.lkyblog.dao.model.BlogKeyWorld;
import com.hz.lkyblog.dao.query.BlogKeyWorldQuery;
import com.shinemo.perform.common.mybatis.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogKeyWorldMapper {
    int insertSelective(BlogKeyWorld record);

    BlogKeyWorld getBy(BlogKeyWorldQuery query);

    List<BlogKeyWorld> selectBy(BlogKeyWorldQuery query);

    Integer countBy(BlogKeyWorldQuery query);

    Integer updateById(BlogKeyWorld record);

    Integer deleteById(@Param("id") Long id);

    Page<BlogKeyWorld> pageBy(BlogKeyWorldQuery query);
}