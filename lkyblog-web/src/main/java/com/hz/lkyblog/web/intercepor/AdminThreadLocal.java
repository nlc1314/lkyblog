package com.hz.lkyblog.web.intercepor;

import com.hz.lkyblog.dao.model.BlogUser;

public class AdminThreadLocal {

    private static final ThreadLocal<BlogUser> blogUserThreadLocal = new ThreadLocal<>();

    public static BlogUser loginInfo() {
        return blogUserThreadLocal.get();
    }

    public static void putLoginInfo(BlogUser blogUser) {
        blogUserThreadLocal.set(blogUser);
    }

    public static void removeLoginInfo() {
        blogUserThreadLocal.remove();
    }
}
