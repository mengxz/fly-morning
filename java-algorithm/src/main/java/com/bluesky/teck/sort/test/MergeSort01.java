package com.bluesky.teck.sort.test;

import java.util.Arrays;

public class MergeSort01 {
    public static void main(String[] args) {
        int[] a2 = {6,93, 9, 36, 60, 70, 86, 87, 92};
        System.out.println("begin="+ Arrays.toString(a2));
        sort(a2,0,a2.length-1);
        System.out.println("end="+ Arrays.toString(a2));
    }

    public static void sort(int[] a,int low,int high) {
        if(high>low){
            int mid = (high+low)/2;//问题1
            sort(a,low,mid);
            sort(a,mid+1,high);
            merge(a,low,mid,high);
        }
    }

    public static void merge(int[] a,int low,int mid,int high){
        int[] tmp = new int[high-low+1];
        int index = 0;
        int i = low;
        int j = mid+1;

        while(i<=mid && j<=high){
            if(a[i]>a[j]){
                tmp[index++] = a[j++];
            }else{
                tmp[index++] = a[i++];
            }
        }
        while(i>mid && j<=high){
            tmp[index++] = a[j++];
        }
        while(i<=mid && j>high){
            tmp[index++] = a[i++];
        }
        for (int k = 0; k < tmp.length; k++) {
            a[low+k] = tmp[k];
        }
    }
}
