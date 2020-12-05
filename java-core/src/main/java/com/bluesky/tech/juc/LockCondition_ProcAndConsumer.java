package com.bluesky.tech.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SourceData{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase(){
        lock.lock();
        try{
            while (num != 0){
                condition.await();
            }
            System.out.println("currentThreadName:"+Thread.currentThread().getName()+"\t num="+(num ++));
            condition.signalAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrease(){
        lock.lock();
        try{
            while (num != 1){
                condition.await();
            }
            System.out.println("currentThreadName:"+Thread.currentThread().getName()+"\t num="+(num --));
            condition.signalAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
/**
 * 两个线程操作一个数，以两个遥控器控制空调为例
 * 线程1对数加1；然后线程2对数减1；以此循环10次
 * 线程操作资源类
 */
public class LockCondition_ProcAndConsumer {
        public static void main(String[] args)throws Exception {
            SourceData data = new SourceData();
            new Thread(() -> {
                data.increase();
                //线程睡眠指定时间
                try {TimeUnit.SECONDS.sleep(2);}catch (InterruptedException ex){ex.printStackTrace();}
                data.increase();
                //线程睡眠指定时间
                try {TimeUnit.SECONDS.sleep(2);}catch (InterruptedException ex){ex.printStackTrace();}
                data.increase();
            },"AA").start();

            new Thread(() -> {
                data.decrease();
                data.decrease();
                data.decrease();
            },"BB").start();
        }
}
