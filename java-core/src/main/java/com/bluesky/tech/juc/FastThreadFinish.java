package com.bluesky.tech.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 主线程调度三个子线程执行，如果一个子线程执行完成，则主线程结束返回
 *
 */
public class FastThreadFinish {
    private final static ExecutorService executors = new ThreadPoolExecutor(5, 5,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());
    volatile String result = "";

    public static void main(String[] args)throws Exception {
        FastThreadFinish fastThreadFinish = new FastThreadFinish();
        String s = fastThreadFinish.test01();
        System.out.println("game over =="+s);

    }

    private String test01()throws InterruptedException{
        Task taskA = new Task("AA");
        Task taskB = new Task("BB");
        Task taskC = new Task("CC");
        executors.submit(taskA);
        executors.submit(taskB);
        executors.submit(taskC);
        while (result.length()==0){
            TimeUnit.MILLISECONDS.sleep(10);
        }
        System.out.println("test01 over=="+result);
        return result;
    }

    /**
     * 任务处理
     */
    public class Task implements Callable<String> {
        String name;
        public Task(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            int i = new Random().nextInt(10);
            System.out.println(Thread.currentThread().getName()+"sleep begin---"+i);
            TimeUnit.SECONDS.sleep(i);
            System.out.println(Thread.currentThread().getName()+"sleep finish---"+i);
            result = "我是"+Thread.currentThread().getName()+"   finish！！";
            return "re=="+Thread.currentThread().getName();
        }
    }

    public class ThreadDemo extends Thread{

    }
}
