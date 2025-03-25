package com.xcode.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil {

    // 线程安全的ObjectMapper实例 ‌:ml-citation{ref="2" data="citationList"}
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule()) // 支持Java8日期类型
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // 禁用时间戳格式

    /**
     * 将任意对象转换为JSON字符串
     * @param obj 需要转换的Java对象
     * @return JSON字符串
     * @throws RuntimeException 转换失败时抛出异常
     */
    public static <T> String toJsonString(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("对象转JSON失败", e);
        }
    }

}
