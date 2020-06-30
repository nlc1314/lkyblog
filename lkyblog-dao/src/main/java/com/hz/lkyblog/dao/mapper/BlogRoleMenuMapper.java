package com.hz.lkyblog.dao.mapper;

import com.hz.lkyblog.dao.model.BlogRoleMenu;
import com.hz.lkyblog.dao.query.BlogRoleMenuQuery;
import com.shinemo.perform.common.mybatis.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogRoleMenuMapper {
    int insertSelective(BlogRoleMenu record);

    BlogRoleMenu getBy(BlogRoleMenuQuery query);

    List<BlogRoleMenu> selectBy(BlogRoleMenuQuery query);

    Integer countBy(BlogRoleMenuQuery query);

    Integer updateById(BlogRoleMenu record);

    Integer deleteById(@Param("id") Long id);

    Page<BlogRoleMenu> pageBy(BlogRoleMenuQuery query);
}