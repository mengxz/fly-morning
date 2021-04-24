package com.bluesky.teck.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 牛客网119题：题目描述
 给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。如果K>数组的长度，那么返回一个空的数组
 示例1
 输入
 复制
 [4,5,1,6,2,7,3,8],4
 返回值
 复制
 [1,2,3,4]
 * @author simba
 *
 */
public class NC119_GetLeastNumbers {


    public static void main(String[] args) {
        int[] arr = {10,1,6,3,22,8,2,4,9,17};
        PriorityQueue<Integer> queue = new PriorityQueue<>((t1,t2)->t2-t1);
        for(int i : arr){
            queue.add(i);
        }

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        int k = 4;
        //List list = Test01.GetLeastNumbers_Solution(arr,4);
        //List list = Test01.GetLeastNumbers_Solution01(arr,4);
        //List list = Test01.test01(arr,4);
        //List list = NC119_GetLeastNumbers.test02(arr,4);
        //list.stream().forEach(x -> System.out.println(x));
    }

    public static ArrayList<Integer> test02(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>(k);
        if(input.length ==0 || k ==0 ||input.length < k)
            return list;
        PriorityQueue<Integer> queue = new PriorityQueue<>((t1,t2)->t2-t1);
        for(Integer i : input) {
            if(queue.size()<k) {
                queue.add(i);
                continue;
            }
            if(queue.size() >= k && queue.peek() > i){
                queue.poll();
                queue.add(i);
            }
        }
        list = new ArrayList(queue);
        return list;
    }


    public static ArrayList<Integer> test01(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>(k);
        if(input.length ==0 || k ==0 ||input.length < k)
            return list;
        Arrays.sort(input);
        for(int i=0;i<k;i++) {
            list.add(input[i]);
        }
        return list;
    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>(k);
        if(input.length ==0 || k ==0 ||input.length < k)
            return list;
        int length = input.length;
        int max = -1;
        for(int i=0;i<input.length;i++){
            if(list.size()<k){
                max = max > input[i] ? max : input[i];
                list.add(input[i]);
            }else{
                if(max > input[i]){
                    list.remove(k-1);
                    list.add(input[i]);
                    max =  getMax(list);
                }
            }
        }
        return list;
    }

    private static int getMax(List<Integer> list) {
        int result  = list.get(0);
        int size = list.size();
        int maxIndex = -1;
        for(int i=1;i<list.size();i++) {
            if(list.get(i)>result) {
                result = list.get(i);
                maxIndex = i;
            }
        }
        if(maxIndex > 0) {
            list.remove(maxIndex);
            list.add(result);
        }
        return result;
    }

    public static ArrayList<Integer> GetLeastNumbers_Solution01(int [] input, int k) {
        if(k>input.length){
            ArrayList<Integer> list=new ArrayList<Integer>();
            return list;
        }

        ArrayList<Integer> list=new ArrayList<Integer>();
        quickSort(input, 0, input.length - 1);
        for(int i=0;i<k;i++){
            list.add(input[i]);
        }
        return list;


    }


    public static void quickSort(int[] keys,int begin,int end){
        if(begin>=end){
            return;
        }

        int vot=keys[begin];
        int i=begin,j=end;
        while(i!=j){
            while(i<j&&vot<=keys[j]){
                j--;
            }
            swap(keys,i,j);
            while(i<j&&vot>=keys[i]){
                i++;
            }
            swap(keys,i,j);
        }
        quickSort(keys,begin,i-1);
        quickSort(keys,i+1,end);

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}


