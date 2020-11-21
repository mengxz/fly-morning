package com.bluesky.teck.theAlgorithms.basic.queue;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheDemoTwo<K,V> extends LinkedHashMap<K,V> {
    private int capacity;
    public LRUCacheDemoTwo(int capacity){
        super(capacity,0.75f,false);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemoTwo<Integer, String> lruCacheDemoTwo = new LRUCacheDemoTwo<Integer, String>(3);
        lruCacheDemoTwo.put(1,"a");
        lruCacheDemoTwo.put(2,"b");
        lruCacheDemoTwo.put(3,"c");
        System.out.println(lruCacheDemoTwo.keySet());
        lruCacheDemoTwo.put(4,"d");
        System.out.println(lruCacheDemoTwo.keySet());
        lruCacheDemoTwo.put(5,"e");
        System.out.println(lruCacheDemoTwo.keySet());
    }
}
