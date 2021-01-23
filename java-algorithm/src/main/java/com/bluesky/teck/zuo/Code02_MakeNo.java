package com.bluesky.teck.zuo;

import java.util.Arrays;

/**
 * https://www.bilibili.com/video/BV15D4y1X7Tt?t=6&p=3
 * 输入一个int类型的值N，构造一个长度为N的数组arr并返回，要求：
 * 对于任意的i<k<j，都满足arr[i] + arr[j] ==arr[k]*2
 */
public class Code02_MakeNo {

    public static int[] makeNo(int size){
        if(size == 1){
            return new int[]{1};
        }
        int halfSize = (size + 1)/2;
        int[] base = makeNo(halfSize);
        int[] ans = new int[size];
        int index = 0;
        for (; index < halfSize; index++){
            ans[index] = base[index]*2 +1;
        }
        for (int i=0; index<size; index++,i++){
            ans[index] = base[i] * 2;
        }
        return ans;
    }

    public static boolean isValid(int[] arr){
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i+1; k < N; k++) {
                for (int j = k+1; j < N; j++) {
                    if(arr[i] + arr[j] == 2 * arr[k]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin...");
        int[] arr01 = makeNo(10);
        System.out.println(Arrays.toString(arr01));
        boolean valid01 = isValid(arr01);
        System.out.println(valid01);
//        for (int n = 0; n < 10; n++) {
//            int[] arr = makeNo(n);
//            if(!isValid(arr)){
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("test end ...");

    }


}
