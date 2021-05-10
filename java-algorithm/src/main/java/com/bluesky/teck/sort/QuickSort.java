package com.bluesky.teck.sort;

/**
 * 参考[详细介绍，有动画]:https://blog.csdn.net/weixin_42437295/article/details/90771962
 */
public class QuickSort {
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

    static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int low = 0;
        int high = 18;
        int[] arr = { 45, 43, 16, 4, 36, 36, 12, 17, 43, 12, 42, 7, 26, 23, 35, 4, 14, 21, 9 };
        QuickSort.fastSort01(arr, low, high);
        print(arr);
    }

    public static void fastSort01(int[] arr,int start,int tail){
        if(start > tail)
            return;
        int key = arr[start];
        int s = start;
        int t = tail;
        while (s < t){
            while (arr[t]>=key && s < t){
                t --;
            }
            if(s<t){
                arr[s] = arr[t];
                s++;
            }
            while (arr[s]<=key && s < t){
                s ++;
            }
            if(s<t){
                arr[t] = arr[s];
                t--;
            }
        }
        arr[s] = key;
        if(start<s-1)
            fastSort01(arr, start, s-1);
        if(s+1<tail)
            fastSort01(arr,t+1,tail);
    }


    public static void fastSort(int[] array,int start,int tail) {
        if(start >= tail) {
            return;
        }
        //将第一个元素作为比较元素，从第二个开始到最后一个执行快速排序算法
        int begin = start;
        int end = tail;
        int key = array[start];
        while(begin < end) {
            while(array[end] >= key && begin < end) {
                end = end - 1;
            }
            while(array[begin] <= key && begin < end) {
                begin = begin + 1;
            }
            if(end > begin) {
                int temp = array[begin];
                array[begin] = array[end];
                array[end] = temp;
            }
        }
        array[start] = array[begin];
        array[begin] = key;
        fastSort(array,start,begin - 1);
        fastSort(array,begin + 1,tail);
    }


    public static void fastSort02(int[] arr, int head, int tail){
        int h = head;
        int t = tail;
        int key = arr[head];
        while(h < t){
            while (arr[t] >= key && h < t){
                t --;
            }
            if(h < t){
                arr[h] = arr[t];
                h++;
            }
            while (arr[h] <= key && h < t){
                h ++;
            }
            if(h < t){
                arr[t] = arr[h];
                t --;
            }
        }
        arr[h] = key;
        if(h-1 > head){
            fastSort02(arr, head, h-1);
        }
        if(t+1 < tail){
            fastSort02(arr, t+1, tail);
        }
    }

    public static void fastSort03(int[] arr, int head, int tail){
        int h = head;
        int t = tail;
        int key = arr[h];
        while (h < t){
            while (h < t && arr[t] >= key){
                t--;
            }
            if( h<t ){
                arr[h] = arr[t];
                h++;
            }
            while (h<t && arr[h]<=key){
                h++;
            }
            if(h<t){
                arr[t] = arr[h];
                t--;
            }
        }
        arr[h] = key;
        if(head < h){
            fastSort03(arr, head, h-1);
        }
        if(tail > t){
            fastSort03(arr,t+1,tail);
        }
    }








}

