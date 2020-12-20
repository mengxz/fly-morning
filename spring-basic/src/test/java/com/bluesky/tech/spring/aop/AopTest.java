package com.bluesky.tech.spring.aop;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.SpringVersion;

public class AopTest {

    @Test
    public void test01() {
        System.out.println("SpringVersion.getVersion:"+ SpringVersion.getVersion()+";SpringBootVersion.getVersion:"+ SpringBootVersion.getVersion());
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        //1、不要自己创建对象
//		MathCalculator mathCalculator = new MathCalculator();
//		mathCalculator.div(1, 1);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(10, 2);

        applicationContext.close();
    }

    @Test
    public void test02() {
        System.out.println("SpringVersion.getVersion:"+ SpringVersion.getVersion()+";SpringBootVersion.getVersion:"+ SpringBootVersion.getVersion());
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        //1、不要自己创建对象
//		MathCalculator mathCalculator = new MathCalculator();
//		mathCalculator.div(1, 1);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(10, 0);

        applicationContext.close();
    }
}
