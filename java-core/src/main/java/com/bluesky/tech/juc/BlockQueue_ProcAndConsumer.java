package com.bluesky.tech.juc;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockQueueData{

    private volatile AtomicBoolean flag = new AtomicBoolean(true);
    private AtomicInteger num = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public BlockQueueData(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println("blockingQueue="+blockingQueue.getClass().getName());
    }

    public void myProduce(){
        try{
            String data;
            boolean offer;
            while (flag.get()){
                data = "" + num.incrementAndGet();
                offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName()+"flag=="+flag.get()+";produce==="+data+";offer=="+offer);
                //线程睡眠指定时间
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+ " task end");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void myConsumer(){
        try{
            while (flag.get()){
//            while (true){
                String data = blockingQueue.poll(2, TimeUnit.SECONDS);
                if(StringUtils.isBlank(data)) {
                    System.out.println("data is blank "+Thread.currentThread().getName() + " finish");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " consumer===" + data);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void stop(){
        System.out.println("flag=1="+flag.get());
        flag.set(false);
        System.out.println("flag=2="+flag.get());
    }
}

/**
 * 生产者消费者，以蛋糕店为例
 * 生产一个消费一个，直至停止整个任务
 */
public class BlockQueue_ProcAndConsumer {
    public static void main(String[] args)throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        BlockQueueData data = new BlockQueueData(blockingQueue);
        new Thread(() -> {
            data.myProduce();
        },"prod").start();

        new Thread(() -> {
            data.myConsumer();
        },"consumer").start();

        //线程睡眠指定时间
        try {
            TimeUnit.MILLISECONDS.sleep(2000*5);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("time is end!");
        data.stop();
    }
}
