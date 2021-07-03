package com.bluesky.teck;

public class Test{
    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
    /** Number of CPUS, to place bounds on some sizings */
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    public static void main(String[]args){
        int n = 16;
        int i = n - (n >>> 2);
        System.out.println("i="+i);

    }

    static final int spread(int h) {
        System.out.println("HASH_BITS="+HASH_BITS);
        System.out.println("MAX_VALUE="+Integer.MAX_VALUE);
        int i = h >>> 16;
        int j = h ^ i;
        int m = j & HASH_BITS;
        return m;
    }

    static final int spread1(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }
}