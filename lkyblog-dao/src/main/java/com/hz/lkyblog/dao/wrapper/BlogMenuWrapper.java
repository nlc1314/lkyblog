package com.hz.lkyblog.dao.wrapper;

import com.hz.lkyblog.dao.mapper.BlogMenuMapper;
import com.hz.lkyblog.dao.model.BlogMenu;
import com.hz.lkyblog.dao.query.BlogMenuQuery;
import com.shinemo.perform.common.mybatis.Page;
import com.shinemo.perform.common.mybatis.PageHelper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BlogMenuWrapper {
    @Resource
    private BlogMenuMapper blogMenuMapper;

    public Page<BlogMenu> pageBy(BlogMenuQuery query) {
        if(query.getPageIndex()!=null && query.getPageSize()!=null){
    PageHelper.startPage(query.getPageIndex(), query.getPageSize(), query.getOrderByStr());
}else{
    PageHelper.startPage(0, 999999999, query.getOrderByStr());}

        return blogMenuMapper.pageBy(query);
    }

    public Integer countBy(BlogMenuQuery query) {
        return blogMenuMapper.countBy(query);
    }

    public Integer deleteById(Long id) {
        return blogMenuMapper.deleteById(id);
    }

    public BlogMenu getBy(BlogMenuQuery query) {
        return blogMenuMapper.getBy(query);
    }

    public int insertSelective(BlogMenu domain) {
        return blogMenuMapper.insertSelective(domain);
    }

    public List<BlogMenu> selectBy(BlogMenuQuery query) {
        return blogMenuMapper.selectBy(query);
    }

    public Integer updateById(BlogMenu domain) {
        return blogMenuMapper.updateById(domain);
    }
}