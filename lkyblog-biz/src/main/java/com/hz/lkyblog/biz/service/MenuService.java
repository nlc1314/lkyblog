package com.hz.lkyblog.biz.service;

import com.hz.lkyblog.biz.vo.MenuVo;
import com.hz.lkyblog.utils.rest.WebResult;

import java.util.List;

public interface MenuService {

    /**
     * 获取管理员可见的菜单列表
     *
     * @param adminId 管理员id
     * @return
     */
    WebResult<List<MenuVo>> getMenus(Long adminId);

    /**
     * 保存菜单信息
     *
     * @param menuVo
     * @return
     */
    WebResult<Void> saveMenu(MenuVo menuVo);

    /**
     * 删除menuId
     *
     * @param menuId 菜单id
     * @return
     */
    WebResult<Void> delMenu(Long menuId);

    /**
     * 获取所有的菜单列表
     *
     * @return
     */
    WebResult<List<MenuVo>> getAllMenus();


}
