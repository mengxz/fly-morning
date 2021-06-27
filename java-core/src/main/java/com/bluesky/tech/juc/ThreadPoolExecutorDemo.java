package com.bluesky.tech.juc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorDemo {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    private void test01(){
        System.out.println("COUNT_BITS="+COUNT_BITS);
        System.out.println("CAPACITY="+CAPACITY);
        System.out.println("~CAPACITY="+(~CAPACITY));
        System.out.println("~7="+(~7));
        System.out.println("RUNNING="+RUNNING);
        System.out.println("SHUTDOWN="+SHUTDOWN);
        System.out.println("STOP="+STOP);
        System.out.println("TIDYING="+TIDYING);
        System.out.println("TERMINATED="+TERMINATED);
        System.out.println("runStateOf(5)="+runStateOf(5));
        System.out.println("workerCountOf(5)="+workerCountOf(5));
        System.out.println("ctlOf(5,8)="+ ctlOf(5,8));
        System.out.println("ctl="+ctl);

    }

    private void test02(){
        //ExecutorService executorService = Executors.newFixedThreadPool(2);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int re = random.nextInt(3);
            System.out.println("re=="+re);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutorDemo demo = new ThreadPoolExecutorDemo();
        demo.test02();
    }
}
