package com.bluesky.tech.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private Lock lock = new ReentrantLock();
    private int flag = 1;//a:1;b:2;c:3
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printOne(){
        lock.lock();
        try{
            //判断
            while (flag != 1){
                condition1.await();
            }
            //doing
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t i=="+i);
            }
            //通知
            flag = 2;
            condition2.signal();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printTwo(){
        lock.lock();
        try{
            //判断
            while (flag != 2){
                condition2.await();
            }
            //doing
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t i=="+i);
            }
            //通知
            flag = 3;
            condition3.signal();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printThree(){
        lock.lock();
        try{
            //判断
            while (flag != 3){
                condition3.await();
            }
            //doing
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t i=="+i);
            }
            //通知
            flag = 1;
            condition1.signal();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 控制循环执行以下3次
 * 线程1打印5次；然后线程2打印10次；然后线程3打印15次；
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
               shareData.printOne();
            }
            },"AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                shareData.printTwo();
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                shareData.printThree();
            }
        },"CC").start();
    }
}
