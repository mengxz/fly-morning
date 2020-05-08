package com.bluesky.tech.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 抢车位
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6 ; i++) {
            final int tempInt = i;
            new Thread(() -> {
                //线程睡眠指定时间
                try {
                    semaphore.acquire();
                    System.out.println("currentThreadName:"+Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.MILLISECONDS.sleep(2000);
                    System.out.println("currentThreadName:"+Thread.currentThread().getName()+"离开车位");
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },"Thread_"+tempInt).start();
        }
    }
}

//Semaphore源码中使用
class Pool {
    private static final int MAX_AVAILABLE = 100;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    public void putItem(Object x) {
        if (markAsUnused(x))
            available.release();
    }

    // Not a particularly efficient data structure; just for demo
    protected String[] items = new String[MAX_AVAILABLE];//whatever kinds of items being managed
    protected boolean[] used = new boolean[MAX_AVAILABLE];

    protected synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; // not reached
    }

    protected synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else{
                    return false;
                }
            }
        }
        return false;
    }
}
