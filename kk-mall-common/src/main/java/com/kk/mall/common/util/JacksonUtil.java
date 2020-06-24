package com.kk.mall.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JSON处理工具类
 * 依靠jackson提供Bean Json转换
 */
final public class JacksonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    /**
     * 防止反射调用构造器创建对象
     */
    private JacksonUtil() {
        throw new AssertionError();
    }

    /**
     * json数据转Bean
     *
     * @param jsonStr json字符串
     * @param cls     映射类型
     * @param <T>     推导类型
     * @return 推导类型json对象
     */
    public static <T> T toBean(String jsonStr, Class<T> cls) {
        T object = null;
        try {
            object = objectMapper.readValue(jsonStr, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static <T> T toBean(String jsonStr, TypeReference<T> typeReference) {
        T bean = null;
        try {
            bean = objectMapper.readValue(jsonStr, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * json数据转BeanList
     *
     * @param jsonStr json数据
     * @param cls     类型
     * @param <T>     推导类型
     * @return BeanList
     */
    public static <T> List<T> toBeanList(String jsonStr, Class<T> cls) {
        List<T> beanList = null;
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, cls);
            beanList = objectMapper.readValue(jsonStr, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    public static <T> List<T> toBeanList(String jsonStr, TypeReference<List<T>> typeReference) {
        List<T> beanList = null;
        try {
            beanList = objectMapper.readValue(jsonStr, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    /**
     * Bean转json
     *
     * @param obj Bean
     * @return json字符串
     */
    public static String toJsonString(Object obj) {
        String jsonStr = "";
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * json转map
     *
     * @param json json字符串
     * @return map对象
     */
    public static Map<String, Object> parseObject(String json) {
        Map<String, Object> convertedMap = null;
        try {
            convertedMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedMap;
    }

    /**
     * json转listMap
     *
     * @param jsonArray jsonArray字符串
     * @return Listmap对象
     */
    public static List<Map<String, Object>> parseArray(String jsonArray) {
        List<Map<String, Object>> convertedListMap = null;
        try {
            convertedListMap = objectMapper.readValue(jsonArray, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedListMap;
    }

    /**
     * listMap转json
     *
     * @param listMap listMap
     * @return
     */
    public static String toJsonArrayString(List<Map<String, Object>> listMap) {
        String jsonStr = "";
        try {
            jsonStr = objectMapper.writeValueAsString(listMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    public static byte[] toJsonBytes(String jsonStr) {
        byte[] jsonBytes = new byte[0];
        try {
            jsonBytes = objectMapper.writeValueAsBytes(jsonStr);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonBytes;
    }


}