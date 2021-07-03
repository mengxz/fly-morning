package com.bluesky.tech.juc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@Slf4j
class TTask implements Runnable{
    private int i;

    public TTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        log.info("TaskDemo i=="+i +"========"+Thread.currentThread().getName()+" =====mdc name==="+MDC.get("userName"));
        System.out.println(Thread.currentThread().getName()+" =====mdc name==="+MDC.get("userName"));
    }
}

public class ForkJoinDemo2 {

    public static void main(String[] args) throws Exception {
        MDC.put("userName","ForkJoinDemo1111111");
        System.out.println("mdc userName:"+MDC.get("userName"));
        // fork/join task:
        ForkJoinPool fjp = new ForkJoinPool(4); // 最大并发数4
        //MdcForkJoinPool fjp = new MdcForkJoinPool(4); // 最大并发数4
        List<Integer> list  = Arrays.asList(2,3,4,5,6);

        long startTime = System.currentTimeMillis();
        TTask demo = new TTask(1);
        fjp.execute(demo);
        //fjp.invoke(demo);
//        list.stream().forEach(x ->{
//            TaskDemo demo = new TaskDemo(x);
//            fjp.execute(demo);
//        });
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join finish==== " + (endTime - startTime) + " ms.");
    }
}
