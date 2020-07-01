package com.hz.lkyblog.web.controller;


import com.hz.lkyblog.biz.service.MenuService;
import com.hz.lkyblog.biz.service.UserService;
import com.hz.lkyblog.biz.vo.AdminVo;
import com.hz.lkyblog.biz.vo.MenuVo;
import com.hz.lkyblog.biz.vo.PageVo;
import com.hz.lkyblog.dao.model.BlogUser;
import com.hz.lkyblog.dao.query.BlogUserQuery;
import com.hz.lkyblog.utils.rest.WebResult;
import com.hz.lkyblog.web.intercepor.AdminThreadLocal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.hz.lkyblog.utils.constans.ErrorConstants.NOT_LOGIN;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;


    /**
     * 获取管理员可见菜单
     *
     * @return
     */
    @RequestMapping(value = "getMenu", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<List<MenuVo>> getMenu() {
        BlogUser blogUser = AdminThreadLocal.loginInfo();
        if (blogUser == null) {
            return WebResult.buildFailedResponse(NOT_LOGIN);
        }
        return menuService.getMenus(Long.valueOf(blogUser.getRoleId()));
    }

    /**
     * 保存菜单信息
     *
     * @param menuVo 菜单信息
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public WebResult<Void> save(@RequestBody MenuVo menuVo) {
        return menuService.saveMenu(menuVo);
    }


    /**
     * 删除管理员信息
     *
     * @param menuId 菜单id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<Void> delete(@RequestParam(value = "menuId") Long menuId) {
        return menuService.delMenu(menuId);
    }
}
