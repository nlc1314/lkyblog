package com.hz.lkyblog.dao.wrapper;

import com.hz.lkyblog.dao.mapper.BlogTagMapper;
import com.hz.lkyblog.dao.model.BlogTag;
import com.hz.lkyblog.dao.query.BlogTagQuery;
import com.shinemo.perform.common.mybatis.Page;
import com.shinemo.perform.common.mybatis.PageHelper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BlogTagWrapper {
    @Resource
    private BlogTagMapper blogTagMapper;

    public Page<BlogTag> pageBy(BlogTagQuery query) {
        if(query.getPageIndex()!=null && query.getPageSize()!=null){
    PageHelper.startPage(query.getPageIndex(), query.getPageSize(), query.getOrderByStr());
}else{
    PageHelper.startPage(0, 999999999, query.getOrderByStr());}

        return blogTagMapper.pageBy(query);
    }

    public Integer countBy(BlogTagQuery query) {
        return blogTagMapper.countBy(query);
    }

    public Integer deleteById(Long id) {
        return blogTagMapper.deleteById(id);
    }

    public BlogTag getBy(BlogTagQuery query) {
        return blogTagMapper.getBy(query);
    }

    public int insertSelective(BlogTag domain) {
        return blogTagMapper.insertSelective(domain);
    }

    public List<BlogTag> selectBy(BlogTagQuery query) {
        return blogTagMapper.selectBy(query);
    }

    public Integer updateById(BlogTag domain) {
        return blogTagMapper.updateById(domain);
    }
}