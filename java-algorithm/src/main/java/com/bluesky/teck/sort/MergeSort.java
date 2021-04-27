package com.bluesky.teck.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] numbers = {14, 2, 25, 17, 7, 38, 34, 5, 42, 15, 42};

        MergeSort mergeSort = new MergeSort();

        System.out.println("排序前 = " + Arrays.toString(numbers));
        mergeSort.sort(numbers, 0, numbers.length - 1);
        System.out.println("排序后 = " + Arrays.toString(numbers));
    }

    /**
     * 对 [left,right] 范围进行排序
     * @param nums 数组
     * @param left 对 [left,right] 范围进行排序
     * @param right 对 [left,right] 范围进行排序
     */
    public void sort(int[] nums, int left, int right) {

        if (left >= right) {
            return;
        }

        // 找到数组中间值
        int mid = left + (right - left) / 2;

        // 对 [left,mid] 和 [mid+1,right] 分别排序
        sort(nums, left, mid);
        sort(nums, mid + 1, right);

        // 对以排序的 [left,mid] 和 [mid+1,right] 进行合并
        merge(nums, left, mid + 1, right);
        System.out.println("nums = " + Arrays.toString(nums));
    }

    /**
     * 对两段 [left,right-1][right,length] 排序过的数组范围进行合并
     * @param nums 数组
     * @param left 左起点 [left,right-1]
     @param right 右起点 [right,length]
      * @param length 排序端长度
     */
    public void merge(int[] nums, int left, int right, int length) {

        int mid = right - 1;

        int i = left;
        int j = right;
        int index = 0;

        int[] tmp = new int[length - left + 1];

        // 对 [i,mid] [j,length] 两段区域进行合并
        while (i <= mid && j <= length) {
            tmp[index++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }

        // [i,mid] 有剩余则加入数组尾部
        while (i <= mid) {
            tmp[index++] = nums[i++];
        }

        // [j,length] 有剩余则加入数组尾部
        while (j <= length) {
            tmp[index++] = nums[j++];
        }

        // 排序过的区域赋值给原数组
        for (int x = 0; x < tmp.length; x++) {
            nums[left + x] = tmp[x];
        }
    }
}
