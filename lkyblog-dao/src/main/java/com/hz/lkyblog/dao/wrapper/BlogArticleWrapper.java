package com.hz.lkyblog.dao.wrapper;

import com.hz.lkyblog.dao.mapper.BlogArticleMapper;
import com.hz.lkyblog.dao.model.BlogArticleWithBLOBs;
import com.hz.lkyblog.dao.query.BlogArticleQuery;
import com.shinemo.perform.common.mybatis.Page;
import com.shinemo.perform.common.mybatis.PageHelper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BlogArticleWrapper {
    @Resource
    private BlogArticleMapper blogArticleMapper;

    public Page<BlogArticleWithBLOBs> pageBy(BlogArticleQuery query) {
        if(query.getPageIndex()!=null && query.getPageSize()!=null){
    PageHelper.startPage(query.getPageIndex(), query.getPageSize(), query.getOrderByStr());
}else{
    PageHelper.startPage(0, 999999999, query.getOrderByStr());}

        return blogArticleMapper.pageBy(query);
    }

    public Integer countBy(BlogArticleQuery query) {
        return blogArticleMapper.countBy(query);
    }

    public Integer deleteById(Long id) {
        return blogArticleMapper.deleteById(id);
    }

    public BlogArticleWithBLOBs getBy(BlogArticleQuery query) {
        return blogArticleMapper.getBy(query);
    }

    public int insertSelective(BlogArticleWithBLOBs domain) {
        return blogArticleMapper.insertSelective(domain);
    }

    public List<BlogArticleWithBLOBs> selectBy(BlogArticleQuery query) {
        return blogArticleMapper.selectBy(query);
    }

    public Integer updateById(BlogArticleWithBLOBs domain) {
        return blogArticleMapper.updateById(domain);
    }
}