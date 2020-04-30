package com.bluesky.tech.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    public static void main(String[] args) {
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
