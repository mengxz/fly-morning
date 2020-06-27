package com.bluesky.tech.juc.forkJoinPool;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class JavaForkJoinsubmitExample2 extends RecursiveTask<Integer> {
    private static final int var = 5;
    private final int[] value;
    private final int st;
    private final int ed;
    //parameterized constructor
    public JavaForkJoinsubmitExample2(int[] value, int st, int ed) {
        this.value = value;
        this.st = st;
        this.ed = ed;
    }
    public JavaForkJoinsubmitExample2(int[] value) {
        this(value, 0, value.length);
    }
    @Override
    protected Integer compute() {
        final int length = ed - st;
        if (length < var) {
            return computeDirectly();
        }

        final int split = length / 2;
        //new class object
        JavaForkJoinsubmitExample2 left = new JavaForkJoinsubmitExample2(value, st, st + split);
        left.fork();
        System.out.println("submit left :" +ForkJoinPool.commonPool().submit(left));
        JavaForkJoinsubmitExample2 right = new JavaForkJoinsubmitExample2(value, st + split, ed);
        System.out.println("submit left :" +ForkJoinPool.commonPool().submit(right));
        return Math.max(right.compute(), left.join());
    }
    private Integer computeDirectly() {
        System.out.println(Thread.currentThread() + " computing: " + st + " to " + ed);
        int max = Integer.MIN_VALUE;
        for (int i = st; i < ed; i++) {
            if (value[i] > max) {
                max = value[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        // create a random data set
        final int[] value = new int[100];
        final Random rand = new Random();
        for (int i = 0; i < value.length; i++) {
            value[i] = rand.nextInt(100);
        }
        // submit the task to the poool
        ForkJoinPool pool = ForkJoinPool.commonPool();
        JavaForkJoinsubmitExample2 finder = new JavaForkJoinsubmitExample2(value);
        System.out.println(pool.invoke(finder));
        System.out.println("submit left :" +ForkJoinPool.commonPool().submit(finder));
    }
}
