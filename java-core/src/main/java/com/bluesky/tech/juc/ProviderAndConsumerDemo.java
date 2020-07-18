package com.bluesky.tech.juc;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class ProviderAndConsumerDemo {
    private final static Queue<String> queue = new LinkedBlockingQueue<String>(1000);
    private final static Semaphore semaphore = new Semaphore(3);
    private final static Semaphore semaphore1 = new Semaphore(5);
    private final static ExecutorService executors = new ThreadPoolExecutor(5, 5,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());
    private final static ExecutorService executors2 = new ThreadPoolExecutor(5, 5,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(5),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {
        ProviderAndConsumerDemo demo = new ProviderAndConsumerDemo();
        try{
            demo.test04();
        }catch (Exception ex){
            System.out.println(ex);
        }finally {
            //executors.shutdown();
        }

    }

    public void test04(){
        new Thread(() -> {
            System.out.println("currentThreadName begin:"+Thread.currentThread().getName());
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= 100 ; i++) {
                queue.offer(name+"_"+i);
            }
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"AA").start();

        new Thread(() -> {
            System.out.println("currentThreadName begin:"+Thread.currentThread().getName());
            String name = Thread.currentThread().getName();
            String msg = "";
            try {
                while(StringUtils.isNotBlank(msg = queue.poll())){
                    semaphore.acquire();
                    msg = name +"_"+ msg;
                    Task task = new Task(msg,semaphore);
                    executors.submit(task);
                    msg = "";
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"CC").start();

        new Thread(() -> {
            System.out.println("currentThreadName begin:"+Thread.currentThread().getName());
            String name = Thread.currentThread().getName();
            String msg = "";
            try {
                while(StringUtils.isNotBlank(msg = queue.poll())){
                    semaphore1.acquire();
                    msg = name +"_"+ msg;
                    Task task = new Task(msg,semaphore1);
                    executors2.submit(task);
                    msg = "";
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"DD").start();

    }


    /**
     * 任务处理
     */
    public class Task implements Callable<String> {
        private String str;
        private Semaphore signal;

        public Task(String str,Semaphore signal) {
            this.str = str;
            this.signal = signal;
        }

        @Override
        public String call() throws Exception {
            return taskHandle(str,signal);
        }
    }

    private String taskHandle(String str,Semaphore signal){
        String result = "";
        //线程睡眠指定时间
        try {
            System.out.println(Thread.currentThread().getName()+"==taskHandle======"+str);
            TimeUnit.MILLISECONDS.sleep(1000);
            result = "handle "+str;
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }finally {
            signal.release();
        }
        return result;
    }
}
