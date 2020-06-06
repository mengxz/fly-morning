package com.bluesky.teck.sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] a1 = {3,4,5,10,20,50,60};
        sort(a1);
        int[] a2 = {33,40,15,10,20,50,60};
        sort(a2);
    }

    public static void sort(int sourceArray[]){
        System.out.println("before sort:"+ Arrays.toString(sourceArray)+",length:"+sourceArray.length);
        int[] a = Arrays.copyOf(sourceArray, sourceArray.length);
        int count = 0;
        for (int i = 0; i < a.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < a.length-1; j++) {
                if(a[minIndex] > a[j]){
                    minIndex = j;
                }
            }
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
        System.out.println("after sort:"+Arrays.toString(a)+",count="+count);
    }
}
