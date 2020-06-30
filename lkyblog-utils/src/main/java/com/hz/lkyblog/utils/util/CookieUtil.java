package com.hz.lkyblog.utils.util;


import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;

public class CookieUtil {

    /**
     * 获取cookie中的值
     *
     * @param key
     * @param cookies
     * @return
     */
    public static String getCookieValue(String key, Cookie[] cookies) {
        if (StringUtils.isEmpty(key)) return null;
        if (null == cookies || cookies.length == 0) return null;
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) return cookie.getValue();
        }
        return null;
    }


    public static String getJsessionId(Cookie[] cookies) {
        return getCookieValue("JSSESIONID", cookies);
    }


}
