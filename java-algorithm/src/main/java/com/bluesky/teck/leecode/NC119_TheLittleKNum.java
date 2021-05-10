package com.bluesky.teck.leecode;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。如果K>数组的长度，那么返回一个空的数组
 * 示例1
 * 输入[4,5,1,6,2,7,3,8],4
 * 返回值[1,2,3,4]
 */
public class NC119_TheLittleKNum {
    public static void main(String[] args) {
        int[] arr = {3,4,1,5,6,7,8,2,9};
        int k = 3;
        NC119_TheLittleKNum demo = new NC119_TheLittleKNum();
        ArrayList<Integer> integers = demo.GetLeastNumbers_Solution(arr, k);
        integers.stream().forEach(x-> System.out.println("--"+x));
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList relist = new ArrayList();
        if(input.length < k){
            //return Collections.EMPTY_LIST;
            return relist;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k,(x1, x2)->x2-x1);
        for(int i=0;i<input.length;i++){
            int curNum = input[i];
            if(queue.size()>=k){
                if(curNum < queue.peek()){
                    queue.poll();
                    queue.offer(curNum);
                }
            }else{
                queue.offer(curNum);
            }

        }
        while(queue.size()>0){
            relist.add(queue.poll());
        }
        return relist;
    }
}
