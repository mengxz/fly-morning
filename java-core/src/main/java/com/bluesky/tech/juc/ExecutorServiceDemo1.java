package com.bluesky.tech.juc;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorServiceDemo1 {
    private final static ExecutorService executors = new ThreadPoolExecutor(2, 5,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());
            //new ThreadPoolExecutor.CallerRunsPolicy());
            //new ThreadPoolExecutor.DiscardOldestPolicy());
            //new ThreadPoolExecutor.DiscardPolicy());


    public static void main(String[] args) {
        ExecutorServiceDemo1 demo = new ExecutorServiceDemo1();
        try{
            demo.test01();
        }catch (Exception ex){
            System.out.println(ex);
        }finally {
            executors.shutdown();
        }

    }

    public void test01(){
        List<String> strings = new ArrayList<>();
        for (int i = 1; i <= 20 ; i++) {
            strings.add("key_"+i);
        }
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("==1=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        strings.stream().forEach(x -> {
            Task task = new Task(x);
            try{
                executors.submit(task);
            }catch (Exception ex){
                System.out.println("异常，等待重新放入队列");
                //线程睡眠指定时间
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                }catch (InterruptedException ex1){
                    ex1.printStackTrace();
                }
                executors.submit(task);
                System.out.println("重新放入队列ok");
            }

        });
        System.out.println("==2=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }


    /**
     * 任务处理
     */
    public class Task implements Callable<String> {
        private String str;

        public Task(String str) {
            this.str = str;
        }

        @Override
        public String call() throws Exception {
            return taskHandle(str);
        }
    }

    private String taskHandle(String str){
        String result = "";
        //线程睡眠指定时间
        try {
            System.out.println(Thread.currentThread().getName()+"==="+str);
            TimeUnit.MILLISECONDS.sleep(1000);
            result = "handle "+str;
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return result;
    }
}
