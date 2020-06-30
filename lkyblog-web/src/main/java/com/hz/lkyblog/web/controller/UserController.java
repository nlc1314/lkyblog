package com.hz.lkyblog.web.controller;


import com.hz.lkyblog.biz.service.UserService;
import com.hz.lkyblog.biz.vo.AdminVo;
import com.hz.lkyblog.biz.vo.PageVo;
import com.hz.lkyblog.dao.model.BlogUser;
import com.hz.lkyblog.dao.query.BlogUserQuery;
import com.hz.lkyblog.utils.rest.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 获取管理员列表
     *
     * @param pageIndex 页码
     * @param pageSize  每页大小
     * @param mobile    手机号码
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<PageVo<AdminVo>> list(@RequestParam(value = "pageIndex") Integer pageIndex,
                                           @RequestParam(value = "pageSize") Integer pageSize,
                                           @RequestParam(value = "mobile", required = false) String mobile) {
        BlogUserQuery query = BlogUserQuery
                .builder()
                .mobile(StringUtils.isEmpty(mobile) ? null : mobile)
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .build();
        return userService.adminList(query);
    }

    /**
     * 保存管理员信息
     *
     * @param adminVo
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public WebResult<Void> save(@RequestBody AdminVo adminVo) {
        BlogUser blogUser = BlogUser
                .builder()
                .mobile(adminVo.getMobile())
                .email(adminVo.getEmail())
                .name(adminVo.getName())
                .roleId(adminVo.getRoleId())
                .id(adminVo.getId())
                .status(adminVo.getStatus())
                .build();
        return userService.saveAdmin(blogUser);
    }

    /**
     * 获取管理员状态列表
     *
     * @return
     */
    @RequestMapping(value = "getStatus", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<Map<Integer, String>> getStatus() {
        return userService.getStatus();
    }


    /**
     * 更新管理员状态
     *
     * @param adminId 管理员id
     * @param status  状态
     * @return
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<Void> updateStatus(@RequestParam(value = "adminId") Long adminId,
                                        @RequestParam(value = "status") Integer status) {
        return userService.updateStatus(adminId, status);
    }


    /**
     * 删除管理员信息
     *
     * @param adminId 管理员id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<Void> delete(@RequestParam(value = "adminId") Long adminId) {
        return userService.delete(adminId);
    }
}
