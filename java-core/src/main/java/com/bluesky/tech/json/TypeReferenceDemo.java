package com.bluesky.tech.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://juejin.cn/post/6885180672428900365
 * https://zhuanlan.zhihu.com/p/160823716
 * TypeReference的实现原理
 * protected TypeReference(){
 *
 *  // 获取父类的 Type,参考test05验证，https://www.cnblogs.com/maokun/p/6773203.html
 *  Type superClass = getClass().getGenericSuperclass();
 *  // 如果父类是参数化类型，会返回 java.lang.reflect.ParameterizedType
 *  // 调用 getActualTypeArguments 获取实际类型的数组 并拿到第一个
 *  Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
 */
public class TypeReferenceDemo {
    public static void main(String[] args)throws Exception {
        TypeReferenceDemo demo = new TypeReferenceDemo();
        //demo.test01();
        //demo.test02();
        //demo.test03();
        demo.test04();
        demo.getGenericSuperclassTest();
    }

    /**
     * 原理分析:https://zhuanlan.zhihu.com/p/160823716
     * @throws Exception
     */
    private void test04()throws Exception{
        // 构造数据
        User user = new User();
        user.setId(101L);
        user.setName("tom");
        List<User> users = new ArrayList<>();
        users.add(user);
        // 转为JSON字符串
        String jsonString = JSON.toJSONString(users);
        // 反序列化
        //List<User> usersGet = JSONObject.parseObject(jsonString, List.class);
        List<User> usersGet = JSONObject.parseObject(jsonString, new com.alibaba.fastjson.TypeReference<List<User>>(){});
        for (User each : usersGet) {
            System.out.println(each);
        }
    }

    /**
     * 原理分析:https://zhuanlan.zhihu.com/p/160823716
     * @throws Exception
     */
    private void test03()throws Exception{
        String jsonString = "[\"a\",\"b\"]";
        List<String> list = JSONObject.parseObject(jsonString, new com.alibaba.fastjson.TypeReference<List<String>>() {});
        System.out.println(list);
    }

    /**
     * https://zhuanlan.zhihu.com/p/262271430
     * @throws Exception
     */
    private void test02()throws Exception{
        String str = "[{\"id\": null,\"name\": \"zhangsan\",\"age\": 26,\"gender\": false,\"email\": \"email@qq.com\", \"employed\": true,\"salary\": 10},{\"id\": null,\"name\": \"lisi\",\"age\": 36,\"gender\": false,\"email\": \"10001@qq.com\", \"employed\": true,\"salary\": 20}]";
        //报错:Cannot select from parameterized type
        //List<UserResource> list = new ObjectMapper().readValue(str, List<UserResource>.class);
        //转为LinkedHashMap
//        List list = new ObjectMapper().readValue(str, List.class);
//        System.out.println("list.size:"+list.size());
        List<UserResource> list = new ObjectMapper().readValue(str, new TypeReference<List<UserResource>>(){});
        final UserResource userResource = list.get(0);
        System.out.println("age:"+userResource.getAge());

    }

    private void test01(){
        Map<String,String> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        final String s = JsonUtil.toJsonString(map);
        System.out.println("s==="+s);
        Map map01 = JsonUtil.parseObject(s,Map.class);
        Object object = map01.get("k1");
        System.out.println("map01 k1:"+object);

        // 可以直接使用
        HashMap<String, String> map02 = JSONObject.parseObject(s,new com.alibaba.fastjson.TypeReference<HashMap<String, String>>(){});
        System.out.println("map02 k2:"+map02.get("k2"));
        HashMap<String, String> map03 = JSON.parseObject(s,new com.alibaba.fastjson.TypeReference<HashMap<String, String>>(){});
        System.out.println("map03 k2:"+map03.get("k2"));
    }

    private void getGenericSuperclassTest(){

        System.out.println("Student.class.getSuperclass()\t"                             + Student.class.getSuperclass());
        System.out.println("Student.class.getGenericSuperclass()\t"                            + Student.class.getGenericSuperclass());

        System.out.println("Test.class.getSuperclass()\t"                             + User.class.getSuperclass());
        System.out.println("Test.class.getGenericSuperclass()\t"                            + User.class.getGenericSuperclass());

        System.out.println("Object.class.getGenericSuperclass()\t"                             + Object.class.getGenericSuperclass());
        System.out.println("Object.class.getSuperclass()\t"                             + Object.class.getSuperclass());

        System.out.println("void.class.getSuperclass()\t"                             + void.class.getSuperclass());
        System.out.println("void.class.getGenericSuperclass()\t"                             + void.class.getGenericSuperclass());

        System.out.println("int[].class.getSuperclass()\t"                             + int[].class.getSuperclass());
        System.out.println("int[].class.getGenericSuperclass()\t"                             + int[].class.getGenericSuperclass());
    }

    class Person<T> {

    }

    class Student extends Person<User> {

    }

}
