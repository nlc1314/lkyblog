package com.hz.lkyblog.dao.wrapper;

import com.hz.lkyblog.dao.mapper.BlogRoleMenuMapper;
import com.hz.lkyblog.dao.model.BlogRoleMenu;
import com.hz.lkyblog.dao.query.BlogRoleMenuQuery;
import com.shinemo.perform.common.mybatis.Page;
import com.shinemo.perform.common.mybatis.PageHelper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BlogRoleMenuWrapper {
    @Resource
    private BlogRoleMenuMapper blogRoleMenuMapper;

    public Page<BlogRoleMenu> pageBy(BlogRoleMenuQuery query) {
        if(query.getPageIndex()!=null && query.getPageSize()!=null){
    PageHelper.startPage(query.getPageIndex(), query.getPageSize(), query.getOrderByStr());
}else{
    PageHelper.startPage(0, 999999999, query.getOrderByStr());}

        return blogRoleMenuMapper.pageBy(query);
    }

    public Integer countBy(BlogRoleMenuQuery query) {
        return blogRoleMenuMapper.countBy(query);
    }

    public Integer deleteById(Long id) {
        return blogRoleMenuMapper.deleteById(id);
    }

    public BlogRoleMenu getBy(BlogRoleMenuQuery query) {
        return blogRoleMenuMapper.getBy(query);
    }

    public int insertSelective(BlogRoleMenu domain) {
        return blogRoleMenuMapper.insertSelective(domain);
    }

    public List<BlogRoleMenu> selectBy(BlogRoleMenuQuery query) {
        return blogRoleMenuMapper.selectBy(query);
    }

    public Integer updateById(BlogRoleMenu domain) {
        return blogRoleMenuMapper.updateById(domain);
    }
}