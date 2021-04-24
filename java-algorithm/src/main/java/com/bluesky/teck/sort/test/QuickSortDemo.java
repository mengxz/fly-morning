package com.bluesky.teck.sort.test;

public class QuickSortDemo {
    public static void main(String[] args) {
        QuickSortDemo demo = new QuickSortDemo();
        int[] arr = {4,1,8,6,13,9,7,5,3,2};
        print(arr);
        demo.quickSort(arr,0,arr.length-1);
        //sort(arr,0,arr.length-1);
        //quickSort01(arr,0,arr.length - 1);
        System.out.println("after...");
        print(arr);
    }

    private static void quickSort01(int[] arr,int low,int high){
        int l = low;
        int h = high;
        int pivot = arr[low];
        while (l < h){
            while (l < h && arr[h] >= pivot)
                h--;
            if(l < h){
                arr[l] = arr[h];
                l++;
            }
            while (l < h && arr[l] <= pivot)
                l ++;
            if(l < h){
                arr[h] = arr[l];
                h--;
            }
        }
        arr[l] = pivot;
        if(l -1 > low)
            quickSort01(arr, low, l-1);
        if(h+1 < high)
            quickSort01(arr, h+1, high);
    }








    private void quickSort(int[] arr,int begin,int end){
        int source = begin;
        int target = end;
        int povit = arr[source];
        while (begin < end){
            while(begin< end && arr[end]>=povit){
                end --;
            }
            if(begin<end){
                arr[begin] = arr[end];
                begin++;
            }
            while (begin < end && arr[begin] <= povit){
                begin++;
            }

            if(begin<end){
                arr[end] = arr[begin];
                end --;
            }
        }
        arr[begin] = povit;
        if (begin - 1 > source)
            quickSort(arr, source, begin - 1);
        if (end + 1 < target)
            quickSort(arr,  end + 1, target);
    }

    public static void sort(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int povit = arr[low];

        while (l < h) {
            while (l < h && arr[h] >= povit)
                h--;
            if (l < h) {
                arr[l] = arr[h];
                l++;
            }

            while (l < h && arr[l] <= povit)
                l++;

            if (l < h) {
                arr[h] = arr[l];
                h--;
            }
        }
        arr[l] = povit;
        print(arr);
        System.out.print("l=" + (l + 1) + "h=" + (h + 1) + "povit=" + povit + "\n");
        if (l - 1 > low)
            sort(arr, low, l - 1);
        if (h + 1 < high)
            sort(arr, h + 1, high);
    }

    private void swap(int[] arr, int i, int j){
        if(i == j)
            return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void print(int[] arr){
        for (int val : arr) {
            System.out.print("->"+val);
        }
    }
}
