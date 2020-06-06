package com.bluesky.teck.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * https://sort.hust.cc/1.bubblesort
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a1 = {3,4,5,10,20,50,60};
        sort(a1);
        int[] a2 = {33,40,15,10,20,50,60};
        sort(a2);
    }

    public static void sort(int sourceArray[]){
        System.out.println("before sort:"+Arrays.toString(sourceArray)+",length:"+sourceArray.length);
        int[] a = Arrays.copyOf(sourceArray, sourceArray.length);
        int count = 0;
        boolean isChange = true;
        for (int i = 0; i < a.length-1 && isChange; i++) {
            isChange = false;
            for (int j = 0; j < a.length-1-i; j++) {
                count++;
                if(a[j] > a[j+1]){
                    isChange = true;
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
                //System.out.println("count:"+count+"=="+Arrays.toString(a)+";i="+i+";j="+j+";isChange="+isChange);
            }
        }
        System.out.println("after sort:"+Arrays.toString(a)+",count="+count);
    }
}
