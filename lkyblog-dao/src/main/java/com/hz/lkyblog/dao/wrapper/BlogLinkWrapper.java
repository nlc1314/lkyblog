package com.hz.lkyblog.dao.wrapper;

import com.hz.lkyblog.dao.mapper.BlogLinkMapper;
import com.hz.lkyblog.dao.model.BlogLink;
import com.hz.lkyblog.dao.query.BlogLinkQuery;
import com.shinemo.perform.common.mybatis.*;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BlogLinkWrapper {
    @Resource
    private BlogLinkMapper blogLinkMapper;

    public Page<BlogLink> pageBy(BlogLinkQuery query) {
        if(query.getPageIndex()!=null && query.getPageSize()!=null){
    PageHelper.startPage(query.getPageIndex(), query.getPageSize(), query.getOrderByStr());
}else{
    PageHelper.startPage(0, 999999999, query.getOrderByStr());}

        return blogLinkMapper.pageBy(query);
    }

    public Integer countBy(BlogLinkQuery query) {
        return blogLinkMapper.countBy(query);
    }

    public Integer deleteById(Long id) {
        return blogLinkMapper.deleteById(id);
    }

    public BlogLink getBy(BlogLinkQuery query) {
        return blogLinkMapper.getBy(query);
    }

    public int insertSelective(BlogLink domain) {
        return blogLinkMapper.insertSelective(domain);
    }

    public List<BlogLink> selectBy(BlogLinkQuery query) {
        return blogLinkMapper.selectBy(query);
    }

    public Integer updateById(BlogLink domain) {
        return blogLinkMapper.updateById(domain);
    }
}