package com.bluesky.tech.juc;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class ExecutorServiceDemo2 {
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
            new LinkedBlockingQueue<Runnable>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {
        ExecutorServiceDemo2 demo = new ExecutorServiceDemo2();
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
                    Task task = new Task(msg);
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
                    semaphore.acquire();
                    msg = name +"_"+ msg;
                    Task task = new Task(msg);
                    executors2.submit(task);
                    msg = "";
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"DD").start();

    }

    public void test03(){
        new Thread(() -> {
            System.out.println("currentThreadName begin:"+Thread.currentThread().getName());
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= 100 ; i++) {
                queue.offer(name+"_"+i);
            }
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"AA").start();

//        new Thread(() -> {
//            System.out.println("currentThreadName begin:"+Thread.currentThread().getName());
//            String name = Thread.currentThread().getName();
//            for (int i = 1; i <= 200 ; i++) {
//                queue.offer(name+"_"+i);
//            }
//            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
//        },"BB").start();

        new Thread(() -> {
            System.out.println("currentThreadName begin:"+Thread.currentThread().getName());
            String name = Thread.currentThread().getName();
            String msg = "";
            try {
                while(StringUtils.isNotBlank(msg = queue.poll())){
                    semaphore.acquire();
                    Task task = new Task(msg);
                    executors.submit(task);
                    msg = "";
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"CC").start();

    }

    public void test02(){
        new Thread(() -> {
            System.out.println("currentThreadName begin:"+Thread.currentThread().getName());
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= 200 ; i++) {
                queue.offer(name+"_"+i);
            }
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
         },"AA").start();

//        new Thread(() -> {
//            System.out.println("currentThreadName begin:"+Thread.currentThread().getName());
//            String name = Thread.currentThread().getName();
//            for (int i = 1; i <= 200 ; i++) {
//                queue.offer(name+"_"+i);
//            }
//            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
//        },"BB").start();

        new Thread(() -> {
            System.out.println("currentThreadName begin:"+Thread.currentThread().getName());
            String name = Thread.currentThread().getName();
            String msg = "";
            try {
                while(StringUtils.isNotBlank(msg = queue.poll())){
                    System.out.println("consumer===name="+name+"====msg:"+msg);
                    //线程睡眠指定时间
                    TimeUnit.MILLISECONDS.sleep(1000);
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
                    System.out.println("consumer===name="+name+"====msg:"+msg);
                    //线程睡眠指定时间
                    TimeUnit.MILLISECONDS.sleep(1000);
                    msg = "";
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"DD").start();


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
            System.out.println(Thread.currentThread().getName()+"==taskHandle======"+str);
            TimeUnit.MILLISECONDS.sleep(1000);
            result = "handle "+str;
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }finally {
            semaphore.release();
        }
        return result;
    }
}
