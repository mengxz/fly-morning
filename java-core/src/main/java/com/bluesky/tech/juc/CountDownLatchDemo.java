package com.bluesky.tech.juc;

import java.util.concurrent.*;

/**
 * 所有人员走了才能锁门
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }


    private static void test03() {
        CountDownLatch countDownLatch = new CountDownLatch(7);
        //Executor e = Executors.newFixedThreadPool(3);
        ExecutorService executor = new ThreadPoolExecutor(3,3,
                2000L,TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(4));

        for (int i = 1; i <= 7 ; i++) {
            executor.execute(new MyCountDownLathcTask(countDownLatch, i));
        }
        try{
            countDownLatch.await();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("currentThreadName:"+Thread.currentThread().getName()+"管理员锁门...");
        executor.shutdown();
    }

    private static void test02() {
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 1; i <= 7 ; i++) {
            final int tempInt = i;
            new Thread(() -> {
                doWork(countDownLatch,tempInt);
            },"Thread_"+tempInt).start();
        }
        try{
            countDownLatch.await();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("currentThreadName:"+Thread.currentThread().getName()+"管理员锁门...");
    }

    private static void doWork(CountDownLatch countDownLatch,int index) {
        System.out.println("开始处理:"+index);
        //线程睡眠指定时间
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("currentThreadName:" + Thread.currentThread().getName() + "离开...");
        countDownLatch.countDown();
    }

    private static void test01() {
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 1; i <= 7 ; i++) {
            final int tempInt = i;
            new Thread(() -> {
                //线程睡眠指定时间
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+"离开...");
                countDownLatch.countDown();
            },"Thread_"+tempInt).start();
        }
        try{
            countDownLatch.await();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("currentThreadName:"+Thread.currentThread().getName()+"管理员锁门...");
    }

}

class MyCountDownLathcTask implements Runnable{
    private CountDownLatch countDownLatch;
    private int index;
    public MyCountDownLathcTask(CountDownLatch countDownLatch,int index) {
        this.countDownLatch = countDownLatch;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            doWork(index);
            countDownLatch.countDown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void doWork(int index) {
        System.out.println("MyCountDownLathcTask 开始处理:"+index);
        System.out.println("MyCountDownLathcTask currentThreadName:" + Thread.currentThread().getName() + "处理...");
        //线程睡眠指定时间
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("MyCountDownLathcTask 处理完成......"+index);
    }
}
