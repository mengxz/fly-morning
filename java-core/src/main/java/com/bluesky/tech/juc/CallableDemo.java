package com.bluesky.tech.juc;

import com.google.common.base.Stopwatch;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("MyCallable call...");
        //线程睡眠指定时间
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return 1024;
    }
}

//@Slf4j
public class CallableDemo {

    public static void main(String[] args){
        test01();
        //test02();
        //test03();
    }

    private static void test01() {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Integer result = -1;
        Stopwatch stopwatch = Stopwatch.createStarted();
        try{
            futureTask.run();
            result = futureTask.get();
            System.out.println("result:"+result);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            System.out.println("time1:"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    private static void test02() {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Thread(futureTask,"AA").start();
        System.out.println("time1:"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        try {
            System.out.println("result:"+futureTask.get());
            //System.out.println("result:"+futureTask.get(1000L,TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("time2:"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void test03() {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Stopwatch stopwatch = Stopwatch.createStarted();
        //多线程只会调用一次
        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();
        System.out.println("time1:"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        try {
            System.out.println("result:"+futureTask.get());
            System.out.println("result2:"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("time2:"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
