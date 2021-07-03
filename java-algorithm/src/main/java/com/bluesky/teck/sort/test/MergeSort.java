package com.bluesky.teck.sort.test;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int i=3,j=2;
        System.out.println("i="+i+";j="+j);
        test01();
    }

    private static void test01() {
        int[] a2 = {6, 9, 36, 60, 70, 86, 87, 92};
        System.out.println("begin="+ Arrays.toString(a2));
        sort(a2,0,a2.length-1);
        System.out.println("end="+ Arrays.toString(a2));
    }


    public static void sort(int[] arr,int low,int high){
        if(high>low){
            int mid = (high+low)/2;
            sort(arr, low, mid);
            sort(arr, mid+1, high);
            merge(arr,low,mid,high);
        }
    }

    public static void merge(int[] arr,int low,int mid,int high) {
        int[] tmp = new int[high-low+1];
        int k = 0;
        int i = low;
        int j = mid+1;
        while(i<=mid && j<=high){
            if(arr[i]<arr[j]){
                tmp[k++] = arr[i++];
            }else{
                tmp[k++] = arr[j++];
            }
        }
        while (i>mid & j<=high){
            tmp[k++] = arr[j++];
        }
        while (i<=mid & j>high){
            tmp[k++] = arr[i++];
        }
        for (int ii = 0; ii < tmp.length; ii++) {
            arr[low+ii] = tmp[ii];
        }
    }



}
