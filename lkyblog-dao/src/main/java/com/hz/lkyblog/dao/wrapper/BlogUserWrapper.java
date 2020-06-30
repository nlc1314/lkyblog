package com.hz.lkyblog.dao.wrapper;

import com.hz.lkyblog.dao.mapper.BlogUserMapper;
import com.hz.lkyblog.dao.model.BlogUser;
import com.hz.lkyblog.dao.query.BlogUserQuery;
import com.shinemo.perform.common.mybatis.Page;
import com.shinemo.perform.common.mybatis.PageHelper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BlogUserWrapper {
    @Resource
    private BlogUserMapper blogUserMapper;

    public Page<BlogUser> pageBy(BlogUserQuery query) {
        if(query.getPageIndex()!=null && query.getPageSize()!=null){
    PageHelper.startPage(query.getPageIndex(), query.getPageSize(), query.getOrderByStr());
}else{
    PageHelper.startPage(0, 999999999, query.getOrderByStr());}

        return blogUserMapper.pageBy(query);
    }

    public Integer countBy(BlogUserQuery query) {
        return blogUserMapper.countBy(query);
    }

    public Integer deleteById(Long id) {
        return blogUserMapper.deleteById(id);
    }

    public BlogUser getBy(BlogUserQuery query) {
        return blogUserMapper.getBy(query);
    }

    public int insertSelective(BlogUser domain) {
        return blogUserMapper.insertSelective(domain);
    }

    public List<BlogUser> selectBy(BlogUserQuery query) {
        return blogUserMapper.selectBy(query);
    }

    public Integer updateById(BlogUser domain) {
        return blogUserMapper.updateById(domain);
    }
}