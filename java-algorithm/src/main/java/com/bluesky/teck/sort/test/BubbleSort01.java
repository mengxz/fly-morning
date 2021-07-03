package com.bluesky.teck.sort.test;

import java.util.Arrays;

public class BubbleSort01 {
    public static void main(String[] args) {
        int[] a1 = {9,6,70,60,36,87,92,86};
        int[] a2 = {6, 9, 36, 60, 70, 86, 87, 92};
        int[] a3 = {6};
        sort(a3);
    }

    public static int[] sort(int[] arr){
        System.out.println("begin=="+Arrays.toString(arr));
        boolean isChange =true;
        for(int i=0;isChange && i<arr.length;i++){
            isChange = false;
            for (int j = 0; j < arr.length-i-1; j++) {
               if(arr[j]>arr[j+1]){
                   isChange = true;
                   int tmp = arr[j+1];
                   arr[j+1] = arr[j];
                   arr[j] = tmp;
               }
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println("end=="+Arrays.toString(arr));
        return arr;
    }


}
