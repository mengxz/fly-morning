package com.bluesky.tech.juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 所有人员到了才能开会
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws Exception{
        Runnable runnable = () ->{System.out.println("人员已到齐，开始开会...");};
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6,runnable);
        for (int i = 1; i <= 7 ; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"  出发...");
                //线程睡眠指定时间
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"  到达会议室...");
                    cyclicBarrier.await();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            },"Thread_"+tempInt).start();
        }
    }
}
