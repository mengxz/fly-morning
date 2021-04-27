package com.bluesky.tech.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {

    public static void main(String[] args) {
        ThreadLocalDemo test = new ThreadLocalDemo();
        test.threadLocalTest();
    }

    private void threadLocalTest(){
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        ThreadLocal<Integer> threadLocal_01 = new ThreadLocal<Integer>();
        Thread threadAA = new Thread(()->{
            try{
                threadLocal.set("aa");// set函数set的值，只会设置本线程的值，不会对其他线程有任何影响。
                threadLocal_01.set(10);
                for (int i = 0; i < 10; i++) {
                    String s = threadLocal.get();//每个线程调度get函数获取本线程的副本。
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+" s-->"+s);
                    System.out.println(Thread.currentThread().getName()+" num-->"+threadLocal_01.get());
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }finally {
                threadLocal.remove();
            }
        },"AA");
        Thread threadBB = new Thread(()->{
            try{
                threadLocal.set("bb");// set函数set的值，只会设置本线程的值，不会对其他线程有任何影响。
                threadLocal_01.set(20);
                for (int i = 0; i < 10; i++) {
                    String s = threadLocal.get();//每个线程调度get函数获取本线程的副本。
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+" s-->"+s);
                    System.out.println(Thread.currentThread().getName()+" num-->"+threadLocal_01.get());
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }finally {
                threadLocal.remove();
            }
        },"BB");
        threadAA.start();
        threadBB.start();
    }
}
