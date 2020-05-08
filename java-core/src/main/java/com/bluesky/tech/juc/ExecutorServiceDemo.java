package com.bluesky.tech.juc;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorServiceDemo {
    private final ExecutorService executors = new ThreadPoolExecutor(16, 32,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(100),
            //new ThreadPoolExecutor.AbortPolicy());
            new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) {
        ExecutorServiceDemo demo = new ExecutorServiceDemo();
        demo.test01();
    }

    public void test01(){
        //List<String> strings = Arrays.asList("a", "b", "c");
        List<String> strings = new ArrayList<>();
        //i=31,34,40,100,200
        for (int i = 1; i < 100 ; i++) {
            strings.add("key_"+i);
        }
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("==1=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        List<Future<String>> futureList = new ArrayList<>();
        strings.stream().forEach(x -> {
            Task task = new Task(x);
            Future<String> result = executors.submit(task);
            futureList.add(result);
        });
        System.out.println("==2=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        List<String> list = new ArrayList<>();
        /**
         * 并发执行查询任务
         */
        Lock lock = new ReentrantLock();
        futureList.parallelStream().forEachOrdered(result -> {
            try {
                String data = result.get();
                lock.lock();
                list.add(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        System.out.println("==3=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        list.stream().forEach(x -> System.out.println(x));
        System.out.println("==4=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
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
