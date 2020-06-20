package com.bluesky.tech.mdc;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JavaForkJoinsubmitExample4 implements Runnable{

    public JavaForkJoinsubmitExample4() {}
    public JavaForkJoinsubmitExample4(final Map<String, String> context) {
        MDC.setContextMap(context);
    }

    @Override
    public void run() {
        System.out.println("hello..."+Thread.currentThread().getName());
        System.out.println(MDC.get("username") + "    " + Thread.currentThread().getName());
        //线程睡眠指定时间
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("bye..."+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        test02();
    }

    public static void test02() {
        MDC.put("username", "zhangsan");
        //这种情况无法传递进入MDC
        JavaForkJoinsubmitExample4 run = new JavaForkJoinsubmitExample4(MDC.getCopyOfContextMap());
        new Thread(run,"AA").start();
        Runnable wrapRun = ThreadMdcUtil.wrap(new JavaForkJoinsubmitExample4(), MDC.getCopyOfContextMap());
        new Thread(wrapRun,"BB").start();
    }
}
