package com.hz.lkyblog.utils.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * list类型
     */
    public static CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Long.class);

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * @param jsonStr
     * @return
     */
    public static <T> T convertFrom(String jsonStr, Class<T> clazz) {
        if (jsonStr == null || jsonStr.isEmpty()) {
            jsonStr = "{}"; // to avoid JSON error
        }

        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            log.error("convertFrom error", e);
            return null;
        }
    }

    /**
     * Object to string,不保留为null或者空的字段
     *
     * @param obj
     * @return
     */
    public static String convertFrom(Object obj) {
        if (obj == null) {
            return "";
        }

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getObj(String str, Class<T> clazz) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        T pojo = null;
        try {
            pojo = objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.error("getObj error",e);
        }
        return pojo;
    }
}
