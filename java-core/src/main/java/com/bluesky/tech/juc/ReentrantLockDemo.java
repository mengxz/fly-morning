package com.bluesky.tech.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        Lock lock = new ReentrantLock(true);
        for (int i = 1; i <= 5 ; i++) {
            new Thread(() -> {
                try{
                    lock.lock();
                    System.out.println("currentThreadName:"+Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(2);
                }catch (Exception ex){
                    System.out.println(ex);
                }finally {
                    lock.unlock();
                }
            },"AA_" + i).start();
        }

    }
}
