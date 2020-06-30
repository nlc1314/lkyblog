package com.hz.lkyblog.biz.service;

import com.hz.lkyblog.biz.vo.AdminVo;
import com.hz.lkyblog.biz.vo.PageVo;
import com.hz.lkyblog.dao.model.BlogUser;
import com.hz.lkyblog.dao.query.BlogUserQuery;
import com.hz.lkyblog.utils.rest.WebResult;

import java.util.Map;

public interface UserService {

    /**
     * 获取管理员列表信息
     *
     * @param query 查询条件
     * @return
     */
    WebResult<PageVo<AdminVo>> adminList(BlogUserQuery query);

    /**
     * 保存管理员信息
     *
     * @param blogUser 管理员对象
     * @return
     */
    WebResult<Void> saveAdmin(BlogUser blogUser);


    /**
     * 获取管理员状态map
     *
     * @return
     */
    WebResult<Map<Integer, String>> getStatus();

    /**
     * 更改管理员状态
     *
     * @param adminId 管理员id
     * @param status 状态
     * @return
     */
    WebResult<Void> updateStatus(Long adminId, Integer status);

    /**
     * 删除管理员
     *
     * @param adminId 管理员id
     * @return
     */
    WebResult<Void> delete(Long adminId);
}
