package com.bulesky.tech.utils;

public class HashMapTest {

    public static void main(String[] args) {
        testHash();
    }

    private static void testHash() {
        for (int n = 0; n < 100; n++) {
            int hash = hash(n);
            int i1 = n - 1;
//            System.out.println("hash==="+Integer.toBinaryString(hash));
//            System.out.println("i1==="+Integer.toBinaryString(i1));
            int i = i1 & hash;
            System.out.println("n="+n+",i="+i);
        }
    }

    static final int hash(Object key) {
        int h = key.hashCode();
        int i = h >>> 16;
        int i1 = h ^ i;
        return (key == null) ? 0 : i1;
    }

    static final int hash1(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
