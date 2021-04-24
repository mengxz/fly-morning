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
        QuickSort.sort(arr, low, high);
        print(arr);
    }
}

