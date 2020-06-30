package com.hz.lkyblog.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hz.lkyblog.biz.service.MenuService;
import com.hz.lkyblog.biz.vo.MenuVo;
import com.hz.lkyblog.dao.model.BlogMenu;
import com.hz.lkyblog.dao.model.BlogRoleMenu;
import com.hz.lkyblog.dao.model.BlogUser;
import com.hz.lkyblog.dao.query.BlogMenuQuery;
import com.hz.lkyblog.dao.query.BlogRoleMenuQuery;
import com.hz.lkyblog.dao.query.BlogUserQuery;
import com.hz.lkyblog.dao.wrapper.BlogMenuWrapper;
import com.hz.lkyblog.dao.wrapper.BlogRoleMenuWrapper;
import com.hz.lkyblog.dao.wrapper.BlogUserWrapper;
import com.hz.lkyblog.utils.rest.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private BlogMenuWrapper blogMenuWrapper;

    @Resource
    private BlogUserWrapper blogUserWrapper;

    @Resource
    private BlogRoleMenuWrapper blogRoleMenuWrapper;


    @Override
    public WebResult<List<MenuVo>> getMenus(Long adminId) {
        try {
            BlogUser user = blogUserWrapper.getBy(BlogUserQuery.builder().id(adminId).build());
            if (user == null) {
                return WebResult.buildFailedResponse("无权限");
            }
            Integer roleId = user.getRoleId();
            BlogRoleMenu menus = blogRoleMenuWrapper.getBy(BlogRoleMenuQuery.builder().roleId(Long.valueOf(roleId)).build());
            if (menus == null || StringUtils.isEmpty(menus.getMenuIds())) {
                return WebResult.buildSuccessResponse(Collections.emptyList());
            }
            List<Long> menuIds = JSON.parseArray(menus.getMenuIds(), Long.class);
            List<BlogMenu> blogMenus = blogMenuWrapper.selectBy(BlogMenuQuery.builder().status(1).build());
            Map<Integer, List<BlogMenu>> collect = blogMenus.stream().collect(Collectors.groupingBy(BlogMenu::getHierarchy));
            TreeMap<Integer, List<BlogMenu>> treeMap = Maps.newTreeMap();
            treeMap.putAll(collect);
            List<MenuVo> menuVos = setChildMenus(blogMenus.stream().filter(m -> m.getHierarchy().equals(1)).collect(Collectors.toList()), treeMap, menuIds);
            return WebResult.buildSuccessResponse(menuVos);
        } catch (Exception e) {
            log.error("getMenus error adminId:{},e:{}", adminId, e);
            return WebResult.buildServerErrorResponse();
        }
    }

    private List<MenuVo> setChildMenus(List<BlogMenu> menuVos, TreeMap<Integer, List<BlogMenu>> treeMap, List<Long> menuIds) {
        if (CollectionUtils.isEmpty(treeMap) || CollectionUtils.isEmpty(menuVos)) {
            return Collections.emptyList();
        }
        List<MenuVo> menuVosParent = menuVos.stream().map(this::build2MenuVo).collect(Collectors.toList());
        menuVosParent
                .stream()
                .filter(m -> menuIds.contains(m.getId()))
                .forEach(m -> {
                    List<BlogMenu> blogMenus = treeMap.get(m.getLevel() + 1);
                    if (blogMenus == null) {
                        return;
                    }
                    List<BlogMenu> menuVosChild = blogMenus
                            .stream()
                            .filter(b -> b.getParentId().equals(m.getId()))
                            .collect(Collectors.toList());
                    m.setChildList(setChildMenus(menuVosChild, treeMap, menuIds));
                });
        return menuVosParent;
    }

    /**
     * @param blogMenu
     * @return
     */
    private MenuVo build2MenuVo(BlogMenu blogMenu) {
        MenuVo vo = new MenuVo();
        vo.setId(blogMenu.getId());
        vo.setLevel(blogMenu.getHierarchy());
        vo.setName(blogMenu.getName());
        vo.setParentId(blogMenu.getParentId());
        vo.setTarget(blogMenu.getTarget());
        return vo;
    }



    @Override
    public WebResult<Void> saveMenu(MenuVo menuVo) {
        return null;
    }

    @Override
    public WebResult<Void> delMenu(Long menuId) {
        return null;
    }

    @Override
    public WebResult<List<MenuVo>> getAllMenus() {
        return null;
    }

    public static void main(String[] args) {

        MenuServiceImpl im = new MenuServiceImpl();

        BlogMenu b = new BlogMenu(1L, "1", 0L, 1, 1, new Date(), new Date(), "");
        BlogMenu b2 = new BlogMenu(2L, "1", 0L, 1, 1, new Date(), new Date(), "");
        BlogMenu b3 = new BlogMenu(3L, "1", 0L, 1, 1, new Date(), new Date(), "");
        BlogMenu b4 = new BlogMenu(4L, "1", 1L, 2, 1, new Date(), new Date(), "");
        BlogMenu b5 = new BlogMenu(5L, "1", 2L, 2, 1, new Date(), new Date(), "");
        BlogMenu b6 = new BlogMenu(6L, "1", 3L, 2, 1, new Date(), new Date(), "");
        BlogMenu b7 = new BlogMenu(7L, "1", 4L, 3, 1, new Date(), new Date(), "");
        BlogMenu b8 = new BlogMenu(8L, "1", 5L, 3, 1, new Date(), new Date(), "");
        BlogMenu b9 = new BlogMenu(9L, "1", 6L, 3, 1, new Date(), new Date(), "");
        BlogMenu b10 = new BlogMenu(10L, "1", 1L, 2, 1, new Date(), new Date(), "");
        BlogMenu b11 = new BlogMenu(11L, "1", 5L, 3, 1, new Date(), new Date(), "");

        List<BlogMenu> blogMenus = Lists.newArrayList(b, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11);

        Map<Integer, List<BlogMenu>> collect = blogMenus.stream().collect(Collectors.groupingBy(BlogMenu::getHierarchy));
        TreeMap<Integer, List<BlogMenu>> treeMap = Maps.newTreeMap();
        treeMap.putAll(collect);

        List<BlogMenu> menuVosParent = blogMenus.stream().filter(m -> m.getHierarchy().equals(1)).collect(Collectors.toList());

        List<MenuVo> menuVos = im.setChildMenus(menuVosParent, treeMap, Lists.newArrayList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L));
        System.out.println("sasaasa");
    }
}
