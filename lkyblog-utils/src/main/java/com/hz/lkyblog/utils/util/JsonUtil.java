package com.hz.lkyblog.utils.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将json字符串转为列表
     *
     * @param jsonStr
     * @return
     */
    public static <T> ArrayList<T> convertListFromStr(String jsonStr, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonStr)) {
            return Lists.newArrayList();
        }
        ArrayList<T> someClassList = Lists.newArrayList();
        try {
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            someClassList = objectMapper.readValue(jsonStr, collectionType);
        } catch (IOException e) {
            log.error("convertListFromStr.IOException e:{}", e);
        }
        return someClassList;
    }

    /**
     * 将对象转为json字符串
     *
     * @param obj
     * @return
     */
    public static String convertObj2Str(Object obj) {
        if (obj == null) {
            return "{}";
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("convertObj2Str.JsonProcessingException e:{}", e);
            return "{}";
        }
    }

    /**
     * 根据类型将json转为对象
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertStr2Obj(String str, Class<T> clazz) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        T pojo = null;
        try {
            pojo = objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            log.error("convertStr2Obj.IOException error e:{}", e);
        }
        return pojo;
    }
}
