package com.bluesky.tech.juc;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

/**
 * 主线程调度三个子线程执行，如果一个子线程执行完成，则主线程结束返回
 *
 */
public class FastThreadFinishTwo {
    private final static ExecutorService executors = new ThreadPoolExecutor(5, 5,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());
    volatile String result = "";

    public static void main(String[] args)throws Exception {
        FastThreadFinishTwo fastThreadFinish = new FastThreadFinishTwo();
        String s = fastThreadFinish.test01();
        System.out.println("game over =="+s);

    }

    private String test01()throws InterruptedException{
        Semaphore semaphore = new Semaphore(3);
        Task taskA = new Task("AA",semaphore);
        Task taskB = new Task("BB",semaphore);
        Task taskC = new Task("CC",semaphore);
        executors.submit(taskA);
        executors.submit(taskB);
        executors.submit(taskC);
        //线程睡眠指定时间
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        semaphore.acquire();
        System.out.println("test01 over=="+result);
        return result;
    }

    /**
     * 任务处理
     */
    public class Task implements Callable<String> {
        Semaphore semaphore;
        String name;
        public Task(String name,Semaphore semaphore) {
            this.name = name;
            this.semaphore = semaphore;
        }

        @Override
        public String call() throws Exception {
            try {
                semaphore.acquire();
                int i = new Random().nextInt(10);
                System.out.println(Thread.currentThread().getName()+"sleep begin---"+i);
                TimeUnit.SECONDS.sleep(i);
                System.out.println(Thread.currentThread().getName()+"sleep finish---"+i);
                if(StringUtils.isNotBlank(result))
                    result = "我是"+Thread.currentThread().getName()+"   finish！！";
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                semaphore.release();
            }
            return "re=="+Thread.currentThread().getName();
        }
    }
}
