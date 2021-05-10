package com.bluesky.teck.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述
 * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
 * 你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
 * 假设给出的数组中只存在唯一解
 * 例如：
 * 给出的数组为 {20, 70, 110, 150},目标值为90
 * 输出 index1=1, index2=2
 *
 *
 * 示例1
 * 输入[3,2,4],6
 * 返回值[2,3]
 */
public class NC61_TwoSum {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,19,7,8,9};
        int target = 28;
        NC61_TwoSum demo = new NC61_TwoSum();
        int[] res = demo.twoSum(arr, target);
        if(res==null){
            System.out.println("no result");
            return;
        }
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+"---");
        }
    }

    /**
     *
     * @param numbers int整型一维数组
     * @param target int整型
     * @return int整型一维数组
     */
    public int[] twoSum (int[] numbers, int target) {
        int firstIndex=-1,secondIndex=-1;
        // write code here
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<numbers.length;i++){
            int curNum = numbers[i];
            int needNum = target - curNum;
            if(map.containsKey(needNum)){
                firstIndex = i;
                secondIndex = map.get(needNum);
                break;
            }
            map.put(curNum,i);
        }
        int[] result = {firstIndex,secondIndex};
        return result;
    }
}
