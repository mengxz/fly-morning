package com.bluesky.tech.juc.forkJoinPool;

import java.util.concurrent.ForkJoinPool;

public class JavaForkJoinsubmitExample1   {
    public static void main(final String[] arguments) throws InterruptedException
    {
        int proc = Runtime.getRuntime().availableProcessors();
        System.out.println("numbers of core available in your processor:"  +proc);
        ForkJoinPool Pool = ForkJoinPool.commonPool();
        System.out.println(" Before invoking number of active thread   :" +Pool.getActiveThreadCount());
        Taskk1 t = new Taskk1(400);
        System.out.println(" submit  :" +Pool.submit(t));
    }
}
