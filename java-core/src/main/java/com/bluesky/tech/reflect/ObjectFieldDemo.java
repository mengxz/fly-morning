package com.bluesky.tech.reflect;

import com.bluesky.tech.entity.Person;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class ObjectFieldDemo {

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static String getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return (String)field.get(object);
        } catch (Exception e) {
            log.error("ex:",e);
            return null;
        }
    }

    /**
     * 根据属性名设置属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static void setFieldValueByFieldName(String fieldName, Object object,String value) {
        try {
            // 获取obj类的字节文件对象
            Class c = object.getClass();
            log.info("class name:",c.getName());
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(object, value);
        } catch (Exception e) {
            //log.error(e.getMessage(),e);

        }
    }

    public static void main(String[] args)throws Exception {
        Person person = new Person(1,"zhangsan",21);
        person.setId(1);
        String name = ObjectFieldDemo.getFieldValueByFieldName("name", person);
        System.out.println("name:"+name);
        ObjectFieldDemo.setFieldValueByFieldName("name", person,"lisi");
        System.out.println("name:"+person.getName());
    }
}
