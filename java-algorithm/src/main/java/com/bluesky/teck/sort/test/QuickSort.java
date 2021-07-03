package com.bluesky.teck.sort.test;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] a2 = {6,93, 9, 36, 60, 70, 86, 87, 92};
        System.out.println("begin="+ Arrays.toString(a2));
        sort(a2,0,a2.length-1);
        System.out.println("end="+ Arrays.toString(a2));
    }

    public static void sort(int[] a,int low,int high) {
        if(high>low){
            int i = par(a,low,high);
            sort(a,low,i-1);
            sort(a,i+1,high);
        }
    }

    public static int par(int[] a,int low,int high){
        int i = low -1;
        int par = a[high];//问题-1
        for(int j=low;j<=high-1;j++){
            if(a[j]<=par){
                i=i+1;
                swap(a,i,j);
            }
        }
        i = i+1;
        swap(a,i,high);
        return i;
    }

    public static void swap(int[] a,int i,int j){
        int tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }
}
