package com.hz.lkyblog.dao.mapper;

import com.hz.lkyblog.dao.model.BlogMenu;
import com.hz.lkyblog.dao.query.BlogMenuQuery;
import com.shinemo.perform.common.mybatis.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogMenuMapper {
    int insertSelective(BlogMenu record);

    BlogMenu getBy(BlogMenuQuery query);

    List<BlogMenu> selectBy(BlogMenuQuery query);

    Integer countBy(BlogMenuQuery query);

    Integer updateById(BlogMenu record);

    Integer deleteById(@Param("id") Long id);

    Page<BlogMenu> pageBy(BlogMenuQuery query);
}