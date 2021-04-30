package com.bluesky.teck.leecode;

/**
 * 题目描述
 * 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
 *
 * 示例1
 * 输入 abcdab
 * 返回值4
 *
 *  示例2
 *  输入abacd
 * 返回值 3
 */
public class NC41_TheLongestChar {
    public static void main(String[] args) {
        String str = "abcab";
        NC41_TheLongestChar demo = new NC41_TheLongestChar();
        int i = demo.maxLength01(str);
        System.out.println("i:"+i);
//        if(i==maxLength){
//            System.out.println("yes...");
//        }
    }

    private int maxLength01(String str){
        int result = 0;
        char[] chars = str.toCharArray();
        int arrLen = chars.length;
        int start = 0;
        int[] lastPostRecord = new int[26];
        for (int i = 0; i < arrLen; i++) {
            char curChar = chars[i];
            int index = curChar - 'a';
            start = Math.max(start,lastPostRecord[index]);
            result = Math.max(result,i-start+1);
            lastPostRecord[index] = i+1;
        }
        return result;
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
}
