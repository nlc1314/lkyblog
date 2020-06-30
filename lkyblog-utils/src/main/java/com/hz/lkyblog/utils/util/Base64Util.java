package com.hz.lkyblog.utils.util;

import java.util.Base64;

public class Base64Util {

    /**
     * encode字符串
     *
     * @param source
     * @return
     */
    public static String encoder(String source) {
        return Base64.getEncoder().encodeToString(source.getBytes());
    }


    /**
     * decode字符串
     *
     * @param source
     * @return
     */
    public static String decoder(String source) {
        return new String(Base64.getDecoder().decode(source.getBytes()));
    }
}
