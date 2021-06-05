package com.bluesky.tech.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 参考:https://zhuanlan.zhihu.com/p/75718480
 */
public class ReflectDemo {
    public static void main(String[] args) {
        ReflectDemo demo = new ReflectDemo();
        //demo.test01();
        User user=new User("zhangsan",20);
        //printClassMethodMessage(user);
        //printFieldMessage(user);
        /**
        User userBefore=new User("zhangsan",20);
        System.out.println(userBefore.getAge());
        User userAfter=(User) printFieldMsgBySelf(userBefore);
        System.out.println(userAfter.getAge());
        printFieldMsgBySelf(user);
         **/
        //printConMessage(user);
        getUserSuperClassAndInterface(user);
    }

    private void test01(){
        User user = new User();
        // 第一种表示方式：通过类名
        Class c1 = User.class;
        System.out.println(c1);
        // 第二中表达方式:通过对象
        Class c2 = user.getClass();
        System.out.println("c1==c2:"+(c1==c2));
        //第三种表达方式
        try {
            Class c3 = Class.forName("com.bluesky.tech.reflect.User");
            System.out.println("c1==c3:"+(c1==c3));
            System.out.println(c1==c3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            User user3 = (User)c1.newInstance();
            user3.test();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //需要有无参数的构造方法
        Class c11 = int.class;
        System.out.println(c11);
        Class c21 = String.class;
        System.out.println(c21);
        Class c3 = double.class;
        Class c4 = Double.class;
        Class c5 = void.class;
        System.out.println(c5);
    }

    //打印类的信息，包括类的成员函数、成员变量(只获取成员函数)
    public static void printClassMethodMessage(Object obj){
        //1、首先要获取类的类类型
        Class c = obj.getClass();
        //2、获取类的名称
        System.out.println("类的名称是:"+c.getName());
        //3、获取方法信息
        Method[] ms = c.getMethods();
        for(int i = 0; i < ms.length;i++){
            //第一步：得到方法的返回值类型的类类型
            Class returnType = ms[i].getReturnType();
            System.out.print(returnType.getName()+" ");
            //第二步：得到方法的名称
            System.out.print(ms[i].getName()+"(");
            //第三步：获取方法参数类型--->得到的是参数列表的类型的类类型
            Class[] paramTypes = ms[i].getParameterTypes();
            for (Class class1 : paramTypes) {
                System.out.print(class1.getName()+",");
            }
            System.out.println(")");
        }
    }

    public static void printFieldMessage(Object obj) {
        Class c = obj.getClass();
        //Field类封装了关于成员变量的操作
        //  getFields()方法获取的是所有的public的成员变量的信息
        //  getDeclaredFields获取的是该类自己声明的成员变量的信息
        //Field[] fs = c.getFields();
        Field[] fs = c.getDeclaredFields();
        for (Field field : fs) {
            //1、获取字段类型
            Class fieldType = field.getType();
            String typeName = fieldType.getName();
            //2、获取字段名称
            String fieldName = field.getName();
            //打印字段类型和字段名称
            System.out.println(typeName+" "+fieldName);
        }
    }

    public static Object printFieldMsgBySelf(Object obj) {
        Class c = obj.getClass();
        try {
            //1、获取指定字段
            Field age_field = c.getDeclaredField("age");
            //2、实例化一个User类
            Object object=c.newInstance();
            //3、使用反射机制打破封装
            age_field.setAccessible(true);
            //4、给我们实例化的对象重新设置年龄
            age_field.set(object, 100);
            //5、返回这个object
            return object;
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void printConMessage(Object obj){
        Class c = obj.getClass();
        //Constructor中封装了构造函数的信息
        //  (1)getConstructors获取所有的public的构造函数
        //  (2)getDeclaredConstructors得到所有的构造函数
        //第一步：获取构造函数
        Constructor[] cs = c.getDeclaredConstructors();
        for (Constructor constructor : cs) {
            //获取构造函数名字
            System.out.print(constructor.getName()+"(");
            //获取构造函数的参数列表
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class class1 : paramTypes) {
                System.out.print(class1.getName()+",");
            }
            System.out.println(")");
        }
    }

    //取得父类和接口
    public static void getUserSuperClassAndInterface(Object obj) {
        Class c = obj.getClass();
        //取得父类：父类只能有一个
        Object superClass = c.getSuperclass();
        System.out.println(superClass);
        //取得接口：接口可以有很多
        Object[] superInterface = c.getInterfaces();
        for (Object myInter:superInterface) {
            System.out.println(myInter);
        }
    }
}
