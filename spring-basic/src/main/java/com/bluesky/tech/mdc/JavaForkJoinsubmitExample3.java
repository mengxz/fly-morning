package com.bluesky.tech.mdc;

import org.slf4j.MDC;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class JavaForkJoinsubmitExample3 implements Runnable{

    @Override
    public void run() {
        System.out.println("hello111..."+Thread.currentThread().getName());
        System.out.println(MDC.get("username") + "    " + Thread.currentThread().getName());
//        //线程睡眠指定时间
//        try {
//            TimeUnit.MILLISECONDS.sleep(1000);
//        }catch (InterruptedException ex){
//            ex.printStackTrace();
//        }
        System.out.println("bye..."+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        test01();
    }

    public static void test02() {
        MDC.put("username", "zhangsan");
        new Thread(new JavaForkJoinsubmitExample3(),"AA").start();
    }

    public static void test01() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        System.out.println(" Before invoking number of active thread   :" +pool.getActiveThreadCount());
        JavaForkJoinsubmitExample3 runnable = new JavaForkJoinsubmitExample3();
        pool.execute(runnable);
//        Runnable wrapRun = ThreadMdcUtil.wrap(new JavaForkJoinsubmitExample3(), MDC.getCopyOfContextMap());
//        pool.execute(wrapRun);
        System.out.println(" submit:" + Thread.currentThread().getName() );
    }
}
