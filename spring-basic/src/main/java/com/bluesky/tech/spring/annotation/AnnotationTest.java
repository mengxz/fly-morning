package com.bluesky.tech.spring.annotation;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

public class AnnotationTest {

    @MyAnno
    interface Eat {
    }

    class Parent implements Eat {
    }

    class Child extends Parent {
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @RequestMapping // 特意注解上放一个注解，方面测试看结果
    @Inherited
    @interface MyAnno {

    }

    // 现在我们来获取类上面的注解如下
    public static void main(String[] args) {
        test05();
    }

    //本来，接口上的注解我们无论如何都继承不了了，但用了Spring的，你就可以
    //备注：哪怕@MyAnno上没有标注@Inherited，也是能找出来的（这是后面讲解@RequestMapping为何能被子类继承的重要原因）
    private static void test05() {
        MyAnno annotation = AnnotationUtils.findAnnotation(Child.class, MyAnno.class);
        System.out.println(annotation);
    }

    private static void test01(){
        MyAnno anno1 = Eat.class.getAnnotation(MyAnno.class);
        MyAnno anno2 = Parent.class.getAnnotation(MyAnno.class);
        MyAnno anno3 = Child.class.getAnnotation(MyAnno.class);
        System.out.println(anno1); //@com.fsx.maintest.MyAnno()
        System.out.println(anno2); //null
        System.out.println(anno3); //null
    }

    private static void test02(){
        MyAnno anno1 = Eat.class.getAnnotation(MyAnno.class);

        // 注解交给这么一处理  相当于就会被Spring代理了  这就是优势
        MyAnno sAnno1 = AnnotationUtils.getAnnotation(anno1, MyAnno.class);
        System.out.println(sAnno1); //@com.fsx.maintest.MyAnno()

        // 这样前后类型不一致的话，会把这个注解上面的注解给获取出来
        RequestMapping annotation = AnnotationUtils.getAnnotation(anno1, RequestMapping.class);
        System.out.println(annotation); //@org.springframework.web.bind.annotation.RequestMapping
    }

    private static void test03(){
        MyAnno anno1 = Eat.class.getAnnotation(MyAnno.class);

        // 注意这两种写法的区别：
        // 这个相当于是获取Child.class的它上面的所有注解， 所以只有继承过来的一个@MyAnno
        Annotation[] sAnno1 = AnnotationUtils.getAnnotations(Child.class);
        // 而这里传入的为anno1.annotationType，所以相当于获取该注解上面的注解  所以使用的时候需要注意
        Annotation[] sAnno2 = AnnotationUtils.getAnnotations(anno1.annotationType());
        for(Annotation a1 : sAnno1){
            System.out.println("t1:"+a1);
        }
        for(Annotation a2 : sAnno2){
            System.out.println("t2:"+a2);
        }
    }

    private static void test04(){
        MyAnno anno1 = Eat.class.getAnnotation(MyAnno.class);

        // 注意这两种写法的区别：
        // 这个相当于是获取Child.class的它上面的所有注解， 所以只有继承过来的一个@MyAnno
        Annotation[] sAnno1 = AnnotationUtils.getAnnotations(Child.class);
        // 而这里传入的为anno1.annotationType，所以相当于获取该注解上面的注解  所以使用的时候需要注意
        Annotation[] sAnno2 = AnnotationUtils.getAnnotations(anno1.annotationType());
        for(Annotation a1 : sAnno1){
            System.out.println("t1:"+a1);
        }
        for(Annotation a2 : sAnno2){
            System.out.println("t2:"+a2);
        }
    }

}
