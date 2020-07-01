package com.hz.lkyblog.web.controller;


import com.hz.lkyblog.biz.cache.SessionCache;
import com.hz.lkyblog.biz.service.WeChatLoginService;
import com.hz.lkyblog.biz.vo.LoginVo;
import com.hz.lkyblog.dao.model.BlogUser;
import com.hz.lkyblog.dao.query.BlogUserQuery;
import com.hz.lkyblog.dao.wrapper.BlogUserWrapper;
import com.hz.lkyblog.utils.rest.WebResult;
import com.hz.lkyblog.utils.util.Base64Util;
import com.hz.lkyblog.web.aspect.IgnoreLogin;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hz.lkyblog.utils.constans.SessionConstants.ADMIN_ID;
import static com.hz.lkyblog.utils.constans.SessionConstants.MOBILE_ENCODE;

@Slf4j
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Resource
    private BlogUserWrapper blogUserWrapper;

    @Resource
    private WeChatLoginService weChatLoginService;

    /**
     * 登录管理后台
     *
     * @param request
     * @param response
     * @param loginVo
     * @return
     */
    @IgnoreLogin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public WebResult<Void> loginIn(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody LoginVo loginVo) {
        Assert.isTrue(!StringUtils.isEmpty(loginVo.getMobile()), "手机号码不能为空");
        Assert.isTrue(!StringUtils.isEmpty(loginVo.getPassword()), "密码不能为空");
        BlogUser by = blogUserWrapper.getBy(BlogUserQuery.builder().mobile(loginVo.getMobile()).build());
        //校验用户手机号码或者密码是否正确
        if (by == null || !by.getPassword().equals(loginVo.getPassword())) {
            return WebResult.buildFailedResponse("用户名或者密码错误");
        }
        setLoginSession(request, response, loginVo.getMobile());
        return WebResult.buildSuccessResponse();
    }

    @IgnoreLogin
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<Void> loginIn(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "mobile") String mobile) {

        setLoginSession(request, response, mobile);
        return WebResult.buildSuccessResponse();
    }

    @RequestMapping("/upload")
    @ResponseBody
    public WebResult<Void> uploadFile(@RequestParam("upfile") MultipartFile upfile,
                                      @RequestParam("configKey") String configKey) {
//        uploadZipService.uploadFile(upfile);
        return WebResult.buildSuccessResponse();
    }

    /**
     * 登出管理后台
     *
     * @param request
     * @return
     */
    @IgnoreLogin
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<Void> logout(HttpServletRequest request) {
        //删除缓存
        sessionCache.delAdminInfo(request.getSession().getId());
        String mobile = (String) request.getSession().getAttribute(MOBILE_ENCODE);
        if (StringUtils.isEmpty(mobile)) {
            sessionCache.delSession(mobile);
        }
        //删除session中的key
        request.getSession().removeAttribute(ADMIN_ID);
        request.getSession().removeAttribute(MOBILE_ENCODE);

        return WebResult.buildSuccessResponse();
    }

    /**
     * 获取微信扫码登录的二维码
     *
     * @return
     */
    @ApiOperation("获取微信二维码接口")
    @RequestMapping(value = "/wqrUrl", method = RequestMethod.GET)
    @ResponseBody
    public WebResult<String> weChatLoginUrl() {
        //跳转链接
        String accessPage = "";
        return WebResult.buildSuccessResponse(weChatLoginService.getWQRCode(accessPage));
    }

    @Resource
    private SessionCache sessionCache;


    /**
     * 往cookie和session中写adminid
     *
     * @param request
     * @param response
     * @param mobile
     */
    private void setLoginSession(HttpServletRequest request,
                                 HttpServletResponse response,
                                 String mobile) {
        BlogUser by = blogUserWrapper.getBy(BlogUserQuery.builder().mobile(mobile).build());
        if (by != null) {
            //缓存session对应的admin信息
            sessionCache.saveAdminInfo(request.getSession().getId(), by);
            //缓存手机号码对应的session信息
            sessionCache.saveSession(by.getMobile(), request.getSession().getId());
            
            //写cookie
            Cookie cookie = new Cookie(ADMIN_ID, String.valueOf(by.getId()));// 创建一个cookie，cookie的名字是key
            cookie.setPath("/");
            // 设置Cookie的有效期
            cookie.setMaxAge(-1);
            response.addCookie(cookie);

            //将sessionid写到cookie中
            Cookie jssesionid = new Cookie("JSSESIONID", request.getSession().getId());
            jssesionid.setPath("/");
            // 设置Cookie的有效期
            cookie.setMaxAge(-1);
            response.addCookie(jssesionid);

            //将手机加密写到cookie中
            Cookie mobileId = new Cookie(MOBILE_ENCODE, Base64Util.encoder(by.getMobile()));
            mobileId.setPath("/");
            // 设置Cookie的有效期
            cookie.setMaxAge(-1);
            response.addCookie(mobileId);
        }
    }
}
