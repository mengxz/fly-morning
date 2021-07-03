package com.bluesky.teck.sort.test;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] a2 = {6,93, 9, 36, 60, 70, 86, 87, 92};
        System.out.println("begin="+ Arrays.toString(a2));
        sort(a2);
        System.out.println("end="+ Arrays.toString(a2));
    }

    public static void sort(int[] a) {
        int len = a.length;
        for(int gap = len/2;gap>0;gap = gap/2){
            for(int i = gap;i<len;i++){
                int cur = a[i];
                int j = i;
                while (j-gap>=0 && a[j-gap]>cur){
                    a[j] = a[j-gap];
                    j = j-gap;
                }
                a[j] = cur;
            }
        }
    }
}
