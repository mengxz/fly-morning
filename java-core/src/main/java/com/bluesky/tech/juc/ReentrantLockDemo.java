package com.bluesky.tech.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        AQSTest();
    }

    /**
     * AQS源码解析
     */
    private static void AQSTest() {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try{
                lock.lock();
                System.out.println("currentThreadName:"+Thread.currentThread().getName());
                //线程睡眠指定时间
                TimeUnit.MINUTES.sleep(20);
            }catch (Exception ex){
                System.out.println(ex);
            }finally {
                lock.unlock();
            }
        },"AA").start();

        new Thread(() -> {
            try{
                lock.lock();
                System.out.println("currentThreadName:"+Thread.currentThread().getName());
                //线程睡眠指定时间
                TimeUnit.MINUTES.sleep(1);
            }catch (Exception ex){
                System.out.println(ex);
            }finally {
                lock.unlock();
            }
        },"BB").start();

        new Thread(() -> {
            try{
                lock.lock();
                System.out.println("currentThreadName:"+Thread.currentThread().getName());
                //线程睡眠指定时间
                TimeUnit.MINUTES.sleep(1);
            }catch (Exception ex){
                System.out.println(ex);
            }finally {
                lock.unlock();
            }
        },"CC").start();
    }

    /**
     * 使用LockSupport实现加锁解锁
     */
    private static void LockSupportTest() {
        Thread aa = new Thread(() -> {
            try{
                System.out.println("currentThreadName:" + Thread.currentThread().getName()+" begin");
                LockSupport.park();

                System.out.println("currentThreadName:" + Thread.currentThread().getName()+" end");
            }catch (Exception ex){
                System.out.println(ex);
            }
        }, "AA");
        aa.start();
        //线程睡眠指定时间
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        Thread bb = new Thread(() -> {
            try{
                System.out.println("currentThreadName:" + Thread.currentThread().getName()+" begin");
                TimeUnit.MILLISECONDS.sleep(3000);
                System.out.println("currentThreadName:" + Thread.currentThread().getName()+" sleep end");
                LockSupport.unpark(aa);
                System.out.println("currentThreadName:" + Thread.currentThread().getName() + " end");
            }catch (Exception ex){
                System.out.println(ex);
            }
        }, "BB");
        bb.start();
    }

    /**
     * 使用Condition实现加锁，睡眠，唤醒
     */
    private static void LockConditionTest() {
        new Thread(() -> {
            System.out.println("currentThreadName:"+Thread.currentThread().getName()+" begin");
            try{
                lock.lock();
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+" lock begin");
                //线程睡眠指定时间
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+" sleep finish");
                condition.await();
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+" end");
            }catch (Exception ex){
                System.out.println(ex);
            }finally {
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+" finish");
                lock.unlock();
            }
        },"AA").start();

        new Thread(() -> {
            try{
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+" begin");
                lock.lock();
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+" lock begin");
                condition.signal();
                //线程睡眠指定时间
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+" sleep finish");
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+" end");
            }catch (Exception ex){
                System.out.println(ex);
            }finally {
                lock.unlock();
            }

        },"BB").start();
    }

    /**
     * 使用synchronized实现加锁，睡眠，唤醒
     */
    private static void ObjectWaitNotifyTest() {
        new Thread(() -> {
            try{
                synchronized (objectLock){
                    System.out.println("currentThreadName:"+Thread.currentThread().getName()+" begin");
                    //线程睡眠指定时间
                    TimeUnit.MILLISECONDS.sleep(2000);
                    System.out.println("currentThreadName:"+Thread.currentThread().getName()+" sleep finish");
                    objectLock.wait();
                    System.out.println("currentThreadName:"+Thread.currentThread().getName()+" sys end");
                }
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+" end");
            }catch (Exception ex){
                ex.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try{
                synchronized (objectLock){
                    System.out.println("currentThreadName:"+Thread.currentThread().getName()+" begin");
                    objectLock.notify();
                    //线程睡眠指定时间
                    TimeUnit.MILLISECONDS.sleep(2000);
                    System.out.println("currentThreadName:"+Thread.currentThread().getName()+" syn end");
                }
                //线程睡眠指定时间
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("currentThreadName:"+Thread.currentThread().getName()+"  end");
            }catch (Exception ex){
                ex.printStackTrace();
            }
        },"BB").start();
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
