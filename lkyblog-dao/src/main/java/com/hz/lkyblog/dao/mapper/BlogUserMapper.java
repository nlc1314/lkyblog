package com.hz.lkyblog.dao.mapper;

import com.hz.lkyblog.dao.model.BlogUser;
import com.hz.lkyblog.dao.query.BlogUserQuery;
import com.shinemo.perform.common.mybatis.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogUserMapper {
    int insertSelective(BlogUser record);

    BlogUser getBy(BlogUserQuery query);

    List<BlogUser> selectBy(BlogUserQuery query);

    Integer countBy(BlogUserQuery query);

    Integer updateById(BlogUser record);

    Integer deleteById(@Param("id") Long id);

    Page<BlogUser> pageBy(BlogUserQuery query);
}