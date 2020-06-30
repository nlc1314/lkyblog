package com.hz.lkyblog.biz.service.impl;

import com.hz.lkyblog.biz.service.WeChatLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@Service
public class WeChatLoginServiceImpl implements WeChatLoginService {

    /**
     * 微信开放平台二维码链接
     */
    private final static String OPEN_QR_CODE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";

    /**
     * 开放平台回调url
     * 注意：test16web.tunnel.qydev.com 域名地址要和在微信端 回调域名配置 地址一直，否则会报回调地址参数错误
     */
    private final static String OPEN_REDIRECT_URL = "http://test16web.tunnel.qydev.com/pub/api/v1/wechat/user/callback1";

    /**
     * 微信审核通过后的appid
     */
    private final static String OPEN_APP_ID = "wx0255752c69a2d5b";

    @Override
    public String getWQRCode(String accessPage) {
        //官方文档说明需要进行编码
        String callbackUrl = OPEN_REDIRECT_URL; //进行编码
        try {
            callbackUrl = URLEncoder.encode(OPEN_REDIRECT_URL, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //格式化，返回拼接后的url，去调微信的二维码
        return String.format(OPEN_QR_CODE_URL, OPEN_APP_ID, callbackUrl, accessPage);
    }
}
