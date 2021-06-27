package com.bluesky.tech.juc;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;

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

class MyCallableTask implements Callable<String> {
    private int id;
    public MyCallableTask(int id){
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        for(int i = 0;i<id;i++){
            System.out.println("Thread--"+ id+"--i:"+i);
            Thread.sleep(1000);
        }
        return "Result of callable: "+id;
    }
}

//@Slf4j
public class CallableDemo {

    public static void main(String[] args)throws Exception{
        //test01();
        //test02();
        //test03();
        //test04();
        test05();
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

    /**
     * Callable两种执行方式
     * 方式1:借助FutureTask执行
     */
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

    /**
     * Callable两种执行方式
     * 方式2:借助线程池来运行
     */
    private static void test04()throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        System.out.println(new Date());
        Future<Integer> future = exec.submit(new MyCallable());
        System.out.println("future:"+future.get()+",time:"+new Date());
        exec.shutdown();
    }

    /**
     * 参考:https://blog.csdn.net/m0_37204491/article/details/87930790
     * 当执行多个Callable任务，有多个返回值时，我们可以创建一个Future的集合
     */
    public static void test05() throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();

        for (int i = 0; i < 5; i++) {
            results.add(exec.submit(new MyCallableTask(i)));
        }
        TimeUnit.SECONDS.sleep(2);
        for (Future<String> fs : results) {
            if (fs.isDone()) {
                try {
                    System.out.println(fs.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("MyCallableTask任务未完成！");
            }
        }
        exec.shutdown();
    }
}
