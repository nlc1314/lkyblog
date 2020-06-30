package com.hz.lkyblog.dao.wrapper;

import com.hz.lkyblog.dao.mapper.BlogKeyWorldMapper;
import com.hz.lkyblog.dao.model.BlogKeyWorld;
import com.hz.lkyblog.dao.query.BlogKeyWorldQuery;
import com.shinemo.perform.common.mybatis.Page;
import com.shinemo.perform.common.mybatis.PageHelper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BlogKeyWorldWrapper {
    @Resource
    private BlogKeyWorldMapper blogKeyWorldMapper;

    public Page<BlogKeyWorld> pageBy(BlogKeyWorldQuery query) {
        if(query.getPageIndex()!=null && query.getPageSize()!=null){
    PageHelper.startPage(query.getPageIndex(), query.getPageSize(), query.getOrderByStr());
}else{
    PageHelper.startPage(0, 999999999, query.getOrderByStr());}

        return blogKeyWorldMapper.pageBy(query);
    }

    public Integer countBy(BlogKeyWorldQuery query) {
        return blogKeyWorldMapper.countBy(query);
    }

    public Integer deleteById(Long id) {
        return blogKeyWorldMapper.deleteById(id);
    }

    public BlogKeyWorld getBy(BlogKeyWorldQuery query) {
        return blogKeyWorldMapper.getBy(query);
    }

    public int insertSelective(BlogKeyWorld domain) {
        return blogKeyWorldMapper.insertSelective(domain);
    }

    public List<BlogKeyWorld> selectBy(BlogKeyWorldQuery query) {
        return blogKeyWorldMapper.selectBy(query);
    }

    public Integer updateById(BlogKeyWorld domain) {
        return blogKeyWorldMapper.updateById(domain);
    }
}