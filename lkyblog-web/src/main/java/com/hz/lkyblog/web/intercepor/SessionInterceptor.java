package com.hz.lkyblog.web.intercepor;

import com.hz.lkyblog.biz.cache.SessionCache;
import com.hz.lkyblog.dao.model.BlogUser;
import com.hz.lkyblog.utils.constans.ErrorConstants;
import com.hz.lkyblog.utils.util.Base64Util;
import com.hz.lkyblog.utils.util.CookieUtil;
import com.hz.lkyblog.web.aspect.IgnoreLogin;
import com.hz.lkyblog.web.exception.NonAuthException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.hz.lkyblog.utils.constans.SessionConstants.ADMIN_ID;
import static com.hz.lkyblog.utils.constans.SessionConstants.MOBILE_ENCODE;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Resource
    private SessionCache sessionCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        ///如果匹配到静态资源直接放行
        boolean handlerMethodBoolean = handler instanceof HandlerMethod;
        //静态资源映射
        if (!handlerMethodBoolean) {
            return true;
        }
        //如果匹配到swagger直接放行
        String requestURI = request.getRequestURI();
        if (requestURI.contains("swagger")) {
            return true;
        }

        //如果不需要校验权限直接放行
        final Method methodApi = ((HandlerMethod) handler).getMethod();
        final IgnoreLogin ignoreLogin = methodApi.getAnnotation(IgnoreLogin.class);
        if (ignoreLogin != null) {
            return true;
        }
        //校验登录信息，成功放行，否则返回602提示登录失效
        checkCookie(request);
        return true;
    }

    /**
     * 校验用户登录信息
     *
     * @param request
     */
    private void checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        //如果cookie为空则返回未登录
        if (cookies == null || cookies.length == 0) {
            throw new NonAuthException(ErrorConstants.NOT_LOGIN);
        }

        //获取用户登录的sessionId
        String jsessionId = CookieUtil.getJsessionId(cookies);

        String mobile = CookieUtil.getCookieValue(MOBILE_ENCODE, request.getCookies());
        //获取cookie中存的adminId
        String adminId = CookieUtil.getCookieValue(ADMIN_ID, request.getCookies());
        if (StringUtils.isEmpty(adminId) || StringUtils.isEmpty(mobile)) {
            throw new NonAuthException(ErrorConstants.NOT_LOGIN);
        }
        //缓存中获取手机号码对应的sessionId
        String session = sessionCache.getSession(Base64Util.decoder(mobile));
        if (session == null) {
            throw new NonAuthException(ErrorConstants.NOT_LOGIN);
        }
        //判断sessionId是否一致，判断其他地方给登录
        if (!jsessionId.equals(session)) {
            throw new NonAuthException(ErrorConstants.OTHER_WHERE_LOGIN);
        }
        //判断用户是否登录失效
        BlogUser adminInfo = sessionCache.getAdminInfo(jsessionId);
        if (adminInfo == null || (!String.valueOf(adminInfo.getId()).equals(adminId))) {
            throw new NonAuthException(ErrorConstants.LOGIN_TIME_OUT);
        }
        //将管理员信息放到线程变量里面
        AdminThreadLocal.putLoginInfo(adminInfo);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //线程走完从线程变量里删除管理员信息
        AdminThreadLocal.removeLoginInfo();
    }
}
