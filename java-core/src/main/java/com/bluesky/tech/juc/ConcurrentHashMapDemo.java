package com.bluesky.tech.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        safeMapTest();
    }

    private static void safeMapTest() {
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30 ; i++) {
            final int tempInt = i;
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },"Thread_"+tempInt).start();
        }
    }

    private static void notSafeMap() {
        Map<String,String> map = new HashMap<>();
        for (int i = 1; i <= 30 ; i++) {
            final int tempInt = i;
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },"Thread_"+tempInt).start();
        }
    }
}
