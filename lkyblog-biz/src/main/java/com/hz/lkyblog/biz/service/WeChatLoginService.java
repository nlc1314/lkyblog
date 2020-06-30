package com.hz.lkyblog.biz.service;

/**
 * 微信扫码登录
 */
public interface WeChatLoginService {

    /**
     * 获取微信登录二维码
     *
     * @param accessPage
     * @return
     */
    String getWQRCode(String accessPage);
}
