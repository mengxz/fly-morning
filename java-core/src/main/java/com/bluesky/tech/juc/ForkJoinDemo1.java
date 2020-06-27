package com.bluesky.tech.juc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@Slf4j
class SumTask1 extends RecursiveTask<Long> {

    static final int THRESHOLD = 10;
    long[] array;
    int start;
    int end;

    SumTask1(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        log.info("thread name:{}======compute MDC userName==={}",Thread.currentThread().getName(),MDC.get("userName"));
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(String.format("compute %d~%d = %d", start, end, sum));
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        SumTask1 subtask1 = new SumTask1(this.array, start, middle);
        SumTask1 subtask2 = new SumTask1(this.array, middle, end);
        invokeAll(subtask1, subtask2);
        Long subresult1 = subtask1.join();
        Long subresult2 = subtask2.join();
        Long result = subresult1 + subresult2;
        System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
        return result;
    }
}

@Slf4j
class TaskDemo implements Runnable{
    private int i;

    public TaskDemo(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        log.info("TaskDemo i=="+i +"========"+Thread.currentThread().getName()+" =====mdc name==="+MDC.get("userName"));
        System.out.println(Thread.currentThread().getName()+" =====mdc name==="+MDC.get("userName"));
    }
}

public class ForkJoinDemo1 {

    public static void main(String[] args) throws Exception {
        MDC.put("userName","ForkJoinDemo1111111");
        // 创建随机数组成的数组:
        long[] array = new long[40];
        fillRandom(array);
        System.out.println("array:"+array);
        System.out.println("mdc userName:"+MDC.get("userName"));
        // fork/join task:
        ForkJoinPool fjp = new ForkJoinPool(4); // 最大并发数4
        //MdcForkJoinPool fjp = new MdcForkJoinPool(4); // 最大并发数4
        //ForkJoinTask<Long> task = new SumTask1(array, 0, array.length);
        List<Integer> list  = Arrays.asList(2,3,4,5,6);

        long startTime = System.currentTimeMillis();
        //Long result = fjp.invoke(task);
        //fjp.invoke(demo);
        list.stream().forEach(x ->{
            TaskDemo demo = new TaskDemo(x);
            fjp.execute(demo);
        });
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join finish==== " + (endTime - startTime) + " ms.");
    }

    private static long[] fillRandom(long[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length ; i++) {
            OptionalLong first = random.longs(1, 10).findFirst();
            long asLong = first.getAsLong();
            //System.out.println(asLong+",");
            array[i] = asLong;
        }
        return array;
    }
}
