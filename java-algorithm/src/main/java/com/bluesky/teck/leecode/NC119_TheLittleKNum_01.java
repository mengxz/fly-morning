package com.bluesky.teck.leecode;

import java.util.ArrayList;

/**
 * 给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。如果K>数组的长度，那么返回一个空的数组
 * 示例1
 * 输入[4,5,1,6,2,7,3,8],4
 * 返回值[1,2,3,4]
 */
public class NC119_TheLittleKNum_01 {
    public static void main(String[] args) {
        int[] arr = {3,4,1,5,6,7,8,2,9};
        int k = 3;
        NC119_TheLittleKNum_01 demo = new NC119_TheLittleKNum_01();
        ArrayList<Integer> integers = demo.GetLeastNumbers_Solution(arr, k);
        integers.stream().forEach(x-> System.out.println("--"+x));
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(input.length < k){
            return list;
        }
        fastSort(input,0,input.length - 1);
        for(int i = 0;i < k;i++){
            list.add(input[i]);
        }
        return list;
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

}
