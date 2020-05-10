package com.bluesky.tech.spring.util;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static JsonFactory jsonFactory = objectMapper.getFactory();

    public static JavaType getJavaType(ArrayList<Class> clazzes, JavaType javaType) {
        if (clazzes.size() < 1) {
            return javaType;
        }
        if (javaType == null) {
            Class inner = clazzes.remove(clazzes.size() - 1);
            Class outter = clazzes.remove(clazzes.size() - 1);
            javaType = objectMapper.getTypeFactory().constructParametricType(outter, inner);
        } else {
            Class outter = clazzes.remove(clazzes.size() - 1);
            javaType = objectMapper.getTypeFactory().constructParametricType(outter, javaType);
        }
        return getJavaType(clazzes, javaType);
    }

    public static String toJsonString(Object obj) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(out, JsonEncoding.UTF8);
            jsonGenerator.writeObject(obj);
            String jsonStr = new String(out.toByteArray(),"UTF-8");
            jsonGenerator.close();
            return jsonStr;
        } catch (Exception e) {
            logger.error("parse json string error", e);
            return "";
        }
    }

    public static <T> T parseObject(String jsonStr, Class<T> clazz) {
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//兼容pojo增加字段的版本升级
            return objectMapper.readValue(jsonStr, clazz);
        }
        catch (IOException e){
            logger.error("parseObject error:{}",e.getMessage());
            return null;
        }
    }

    public static <T> List<T> parseArrayList(String jsonStr, Class<T> clazz) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
            return objectMapper.readValue(jsonStr, javaType);
        }
        catch (IOException e){
            logger.error("parseArrayList error:{}",e.getMessage());
            return null;
        }
    }

    public static <T> T parseObjectUncheck(String jsonStr, Class<T> clazz){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//兼容pojo增加字段的版本升级
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            logger.error("parse json string with specific class error",e);
            return null;
        }
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static void setObjectMapper(final ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }

    public static <T> T parseObject(String jsonStr, JavaType javaType)  {
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//兼容pojo增加字段的版本升级
            return objectMapper.readValue(jsonStr, javaType);
        } catch (Exception e) {
            logger.error("parseObject error");
            return null;
        }

    }


    public static ObjectNode getJsonNode(){
        return objectMapper.createObjectNode();
    }

    public static ArrayNode getjsonArrayNode(){
        return objectMapper.createArrayNode();
    }

    /**
     * DTO 转成 Map
     * @param obj
     * @return
     */
    public static MultiValueMap<String,String> dto2Map(Object obj){
        MultiValueMap<String,String> data = new LinkedMultiValueMap<>();
        data.putAll(objectMapper.convertValue(obj, Map.class));
        return data;
    }

    /**
     * 创建新的Json Object对象
     * @return
     */
    public static ObjectNode newObjectNode(){
        return objectMapper.createObjectNode();
    }

}
