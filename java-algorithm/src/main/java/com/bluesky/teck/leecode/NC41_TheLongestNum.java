package com.bluesky.teck.leecode;

/**
 * 题目描述
 * 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
 *
 * 示例1
 * 输入 [2,3,4,5]
 * 返回值4
 *
 *  示例2
 *  输入[2,2,3,4,3]
 * 返回值 3
 */
public class NC41_TheLongestNum {
    public static void main(String[] args) {
        //int[] arr = {1,2,3,1,2,3,4,5,6,7,1,2,2,3,4,5,6,7,8,9};
//        int[] arr = {1,1,2,1,1,1};
        //int[] arr = {1,2,1};
        int[] arr = {1,2,3,1,2,2,2,2,2,3,3};
        NC41_TheLongestNum demo = new NC41_TheLongestNum();
        int i = demo.maxLength(arr);
        System.out.println("i:"+i);
        int maxLength = demo.maxLength(arr);
        System.out.println("maxLength:"+maxLength);
        if(i==maxLength){
            System.out.println("yes...");
        }
    }



    /**
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxLength (int[] arr) {
        // write code here
        int[] lastPos = new int[100];
        int len = arr.length;
        int start = 0;
        int res = 0;
        for(int i =0;i<len;i++){
            int now = arr[i];
            start = Math.max(start,lastPos[now]);
            res = Math.max(res,i-start+1);
            lastPos[now] = i+1;
        }

        return res;
    }



    private int maxLength02(int[] arr){
        int result = 0;
        int start = 0;
        int[] recordArr = new int[10];
        int arrLen = arr.length;
        for (int i = 0; i < arrLen; i++) {
            int curNum = arr[i];
            int curNumLastPos = recordArr[curNum];
            if(curNumLastPos > start)
                start = curNumLastPos;
            recordArr[curNum] = i+1;
            int curResult = i - start + 1;
            System.out.println("====i==="+i+"======curResult==="+curResult);
            //result = Math.max(result,curResult);
            if(curResult > result){
                //System.out.println("curResult==="+curResult+"====i==="+i);
                result  = curResult;
            }
        }
        return result;
    }




    private int maxLength01(int[] arr){
        int result = 0;
        int start = 0;
        int[] numRecord = new int[100];
        int arrLen = arr.length;
        for (int i = 0; i < arrLen; i++) {
            int curNum = arr[i];
            //start = numRecord[curNum];
            start = Math.max(start,numRecord[curNum]);
            result = Math.max(result,i-start+1);
            numRecord[curNum] = i + 1;
        }
        return result;
    }

}
