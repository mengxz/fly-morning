package com.bluesky.tech.juc;

/**
 * https://www.cnblogs.com/grey-wolf/p/13069173.html
 * ConcurrentHash spread说明
 */
public class BinaryTest {
    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
    public static void main(String[] args) {
        int a = 0b00001111111111111111111111111011;
        int b = 0b10001101111111111111110111111011;

        int i = tabAt(32, a);
        System.out.println("index for a:" + i);

        i = tabAt(32, b);
        System.out.println("index for b:" + i);

        int a1 = 0b00001111111111111111111111111011;
        System.out.println("a1="+a1);
        spreadTest(a1);

    }

    /**
     * hashMap实现方式
     * @param arraySize
     * @param hash
     * @return
     */
    static final int tabAt(int  arraySize, int hash) {

        int h = hash;
        int finalHashCode = h ^ (h >>> 16);
        int i = finalHashCode & (arraySize - 1);

        return i;
    }

    /**
     * ConcurrentHashMap实现方式
     * @param h
     * @return
     */
    static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }

    static final int spreadTest(int h) {
        // 1
        String s = Integer.toBinaryString(h);
        System.out.println("h:" + s);

        // 2
        String lower16Bits = Integer.toBinaryString(h >>> 16);
        System.out.println("lower16Bits:" + lower16Bits);

        // 3
        int temp = h ^ (h >>> 16);
        System.out.println("h ^ (h >>> 16):" + Integer.toBinaryString(temp));

        // 4
        int result = (temp) & HASH_BITS;
        System.out.println("final:" + Integer.toBinaryString(result));


        return result;
    }
}
