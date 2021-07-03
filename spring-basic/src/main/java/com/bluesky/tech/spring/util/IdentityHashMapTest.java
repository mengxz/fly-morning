package com.bluesky.tech.spring.util;

public class IdentityHashMapTest {

    private static final int MAXIMUM_CAPACITY = 1 << 29;
    private static final int MINIMUM_CAPACITY = 4;
    private static int capacity(int expectedMaxSize) {
        // assert expectedMaxSize >= 0;
        return
                (expectedMaxSize > MAXIMUM_CAPACITY / 3) ? MAXIMUM_CAPACITY :
                        (expectedMaxSize <= 2 * MINIMUM_CAPACITY / 3) ? MINIMUM_CAPACITY :
                                highestOneBit(expectedMaxSize + (expectedMaxSize << 1));
    }

    public static int highestOneBit(int i) {
        // HD, Figure 3-1
        i |= (i >>  1);
        i |= (i >>  2);
        i |= (i >>  4);
        i |= (i >>  8);
        i |= (i >> 16);
        return i - (i >>> 1);
    }

    public static void main(String[] args) {
        System.out.println("MAXIMUM_CAPACITY=="+MAXIMUM_CAPACITY);
        System.out.println("MINIMUM_CAPACITY=="+MINIMUM_CAPACITY);
        for (int i = 1; i <=100 ; i++) {
            System.out.println("i="+i+"=="+capacity(i));
        }
    }
}
