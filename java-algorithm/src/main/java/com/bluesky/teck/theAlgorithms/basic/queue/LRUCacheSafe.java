package com.bluesky.teck.theAlgorithms.basic.queue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LRUCacheSafe<K, V> {

    private final int maxSize;

    private ConcurrentHashMap<K, V> map;

    private ConcurrentLinkedQueue<K> queue;

    public LRUCacheSafe(final int maxSize) {
        this.maxSize = maxSize;
        map = new ConcurrentHashMap<K, V>(maxSize);
        queue = new ConcurrentLinkedQueue<K>();
    }

    public void put(final K key, final V value) {
        if (map.containsKey(key)) {
            // remove the key from the FIFO queue
            queue.remove(key);
        }

        while (queue.size() >= maxSize) {
            K oldestKey = queue.poll();
            if (null != oldestKey) {
                map.remove(oldestKey);
            }
        }
        queue.add(key);
        map.put(key, value);
    }

    public V get(final K key) {

        if (map.containsKey(key)) {
            // remove from queue and add it again in FIFO queue
            queue.remove(key);
            queue.add(key);
        }
        return map.get(key);
    }

    public ConcurrentLinkedQueue<K> getQueue() {
        return queue;
    }
}