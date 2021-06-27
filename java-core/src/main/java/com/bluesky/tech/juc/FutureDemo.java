package com.bluesky.tech.juc;

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args)throws Exception {
        FutureDemo demoTwo = new FutureDemo();
        System.out.println("执行任务");
        //demoTwo.test01();
        //demoTwo.test02();
        //demoTwo.test03();
        demoTwo.test04();
        System.out.println("任务执行完毕");
    }



    private void test01()throws Exception{
        ExecutorService executor = Executors.newCachedThreadPool();
        String target = "zhangsan";
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() {
                return "hello," + target;
            }
        });
        executor.execute(future);
        final String s = future.get();
        System.out.println("result:" + s);
        executor.shutdown();
    }



    private void test02()throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        String target = "zhangsan";
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() {
                return "hi," + target;
            }
        });
        final String s = future.get();
        System.out.println("result:" + s);
        executor.shutdown();
    }

    private void test03()throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        final long beginTime = System.currentTimeMillis();
        String target = "zhangsan";
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() {
                //线程睡眠指定时间
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                return "hello ," + target;
            }
        });
        final String s1 = future.get();
        System.out.println("s1 result:" + s1);
        Future<String> future02 = executor.submit(new Callable<String>() {
            @Override
            public String call() {
                //线程睡眠指定时间
                try {
                    TimeUnit.MILLISECONDS.sleep(3000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                return "hi," + target;
            }
        });
        final String s2 = future02.get();
        System.out.println("s2 result:" + s2);
        final long endTime = System.currentTimeMillis();
        System.out.println("costTime:"+(endTime-beginTime));
        executor.shutdown();
    }

    private void test04()throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        final long beginTime = System.currentTimeMillis();
        String target = "zhangsan";
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() {
                //线程睡眠指定时间
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                return "hello ," + target;
            }
        });

        Future<String> future02 = executor.submit(new Callable<String>() {
            @Override
            public String call() {
                //线程睡眠指定时间
                try {
                    TimeUnit.MILLISECONDS.sleep(3000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                return "hi," + target;
            }
        });
        final String s1 = future.get();
        System.out.println("s1 result:" + s1);
        final String s2 = future02.get();
        System.out.println("s2 result:" + s2);
        final long endTime = System.currentTimeMillis();
        System.out.println("costTime:"+(endTime-beginTime));
        executor.shutdown();
    }

}
