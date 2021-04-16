package com.bluesky.teck.theAlgorithms.basic.queue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LRUCacheSafeTest {

    public static void main(String[] args) throws Exception {
        test02();
    }

    private static void test02() throws InterruptedException {
        System.out.println("begin...");
        LRUCacheSafe cacheSafe = new LRUCacheSafe<String, String>(3);
        Thread aa = new Thread(() -> {
            System.out.println("currentThreadName:" + Thread.currentThread().getName());
            for (int i = 1; i <= 10; i++) {
                cacheSafe.put("A_key_" + i, "val_" + i);
                System.out.println("key=" + "A_key_" + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (Exception ex) {
                    System.out.println("ex:" + ex);
                }
            }
            System.out.println("currentThreadName:" + Thread.currentThread().getName() + " finish...");
        }, "AA");

        Thread bb = new Thread(() -> {
            System.out.println("currentThreadName:" + Thread.currentThread().getName());
            for (int i = 1; i <= 10; i++) {
                cacheSafe.put("B_key_" + i, "val_" + i);
                System.out.println("key=" + "B_key_" + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (Exception ex) {
                    System.out.println("ex:" + ex);
                }
            }
            System.out.println("currentThreadName:" + Thread.currentThread().getName() + " finish...");
        }, "BB");
        aa.start();
        bb.start();
        aa.join();
        bb.join();
        ConcurrentLinkedQueue queue = cacheSafe.getQueue();
        while (!queue.isEmpty()) {
            Object poll = queue.poll();
            System.out.println("res:" + poll);
        }
        System.out.println("end...");
    }

    private static void test01() throws InterruptedException {
        LRUCacheSafe cacheSafe = new LRUCacheSafe<String, String>(3);
        Thread previousThread = Thread.currentThread();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            System.out.println("currentThreadName:" + Thread.currentThread().getName());
            for (int i = 1; i <= 10; i++) {
                cacheSafe.put("A_key_" + i, "val_" + i);
                System.out.println("key=" + "A_key_" + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (Exception ex) {
                    System.out.println("ex:" + ex);
                }
            }
            countDownLatch.countDown();
            System.out.println("currentThreadName:" + Thread.currentThread().getName() + " finish...");
        }, "AA").start();

        new Thread(() -> {
            System.out.println("currentThreadName:" + Thread.currentThread().getName());
            for (int i = 1; i <= 10; i++) {
                cacheSafe.put("B_key_" + i, "val_" + i);
                System.out.println("key=" + "B_key_" + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (Exception ex) {
                    System.out.println("ex:" + ex);
                }
            }
            countDownLatch.countDown();
            System.out.println("currentThreadName:" + Thread.currentThread().getName() + " finish...");
        }, "BB").start();
        //previousThread.join();
        //TimeUnit.SECONDS.sleep(10);
        countDownLatch.await();
        ConcurrentLinkedQueue queue = cacheSafe.getQueue();

        while (!queue.isEmpty()) {
            Object poll = queue.poll();
            System.out.println("res:" + poll);
        }
    }
}