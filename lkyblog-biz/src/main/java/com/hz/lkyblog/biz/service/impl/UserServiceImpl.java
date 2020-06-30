package com.hz.lkyblog.biz.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hz.lkyblog.biz.service.UserService;
import com.hz.lkyblog.biz.vo.AdminVo;
import com.hz.lkyblog.biz.vo.PageVo;
import com.hz.lkyblog.dao.model.BlogUser;
import com.hz.lkyblog.dao.query.BlogUserQuery;
import com.hz.lkyblog.dao.wrapper.BlogUserWrapper;
import com.hz.lkyblog.utils.constans.CommonConstants;
import com.hz.lkyblog.utils.enums.AdminStatusEnum;
import com.hz.lkyblog.utils.rest.WebResult;
import com.shinemo.perform.common.mybatis.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hz.lkyblog.utils.constans.ErrorConstants.USER_NOT_EXIT;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private BlogUserWrapper blogUserWrapper;

    @Override
    public WebResult<PageVo<AdminVo>> adminList(BlogUserQuery query) {
        PageVo<AdminVo> adminPage = new PageVo<>();

        Page<BlogUser> blogUsers = blogUserWrapper.pageBy(query);

        List<AdminVo> adminVoList = Lists.newArrayList();

        Optional.ofNullable(blogUsers.getResult())
                .ifPresent(bu -> adminVoList.addAll(bu.stream()
                        .map(AdminVo::new)
                        .collect(Collectors.toList())));

        adminPage.setCount(blogUsers.getTotal());
        adminPage.setList(adminVoList);

        return WebResult.buildSuccessResponse(adminPage);
    }

    @Override
    public WebResult<Void> saveAdmin(BlogUser blogUser) {
        if (blogUser.getId() != null && blogUser.getId() != 0L) {
            //UPDATE
            blogUserWrapper.updateById(blogUser);
        } else {
            //insert
            blogUser.setPassword(CommonConstants.defPwd);
            blogUserWrapper.insertSelective(blogUser);
        }
        return WebResult.buildSuccessResponse();
    }

    @Override
    public WebResult<Map<Integer, String>> getStatus() {
        Map<Integer, String> statusMap = Maps.newHashMap();
        for (AdminStatusEnum value : AdminStatusEnum.values()) {
            statusMap.put(value.getStatus(), value.getMsg());
        }
        return WebResult.buildSuccessResponse(statusMap);
    }

    @Override
    public WebResult<Void> updateStatus(Long adminId, Integer status) {
        BlogUser by = blogUserWrapper.getBy(BlogUserQuery.builder().id(adminId).build());
        if (by == null) {
            throw new RuntimeException(USER_NOT_EXIT);
        }
        by.setStatus(status);
        blogUserWrapper.updateById(by);
        return WebResult.buildSuccessResponse();
    }

    @Override
    public WebResult<Void> delete(Long adminId) {
        BlogUser by = blogUserWrapper.getBy(BlogUserQuery.builder().id(adminId).build());
        if (by == null) {
            throw new RuntimeException(USER_NOT_EXIT);
        }
        blogUserWrapper.deleteById(adminId);
        return WebResult.buildSuccessResponse();
    }
}
