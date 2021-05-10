package com.bluesky.teck.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口使用
 * ====题目描述
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *=== 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
 *
 * 示例1
 * 输入 [2,3,4,5]
 * 返回值4
 */
public class NC41_TheLongestChar {
    public static void main(String[] args) {
        String str = "abcabbacccdefghic";
        NC41_TheLongestChar demo = new NC41_TheLongestChar();
        int j = demo.maxLengthOfString02(str);
        System.out.println("string maxLen=="+j);
        int[] arr = {1,2,3,1,2,3,4,5,6,7,1,2,2,3,4,5,6,7,8,9,10,11,1,2};
        int i = demo.maxLengthOfNum(arr);
        System.out.println("num maxLen=="+i);
    }

    private int maxLengthOfString02(String str){
        int maxLen = 0;
        int startOffset = 0;
        int start = 0;
        //char[] chArr = str.toCharArray();
        int strLen = str.length();
        Map<Character,Integer> recMap = new HashMap<>();
        for (int i = 0; i < strLen; i++) {
            Character curr = str.charAt(i);
            if(recMap.containsKey(curr)){
                start = Math.max(start,recMap.get(curr));
            }
            int curLen = i - start + 1;
            if(curLen>maxLen){
                maxLen = curLen;
                startOffset = start;
            }
            recMap.put(curr,i+1);//i->i+1
        }
        String substring = str.substring(startOffset, startOffset+maxLen);
        System.out.println("substring:"+substring);
        return maxLen;
    }


    private int maxLengthOfString01(String str){
        int maxLen = 0;
        int startOffset = 0;
        int start = 0;
        char[] chArr = str.toCharArray();
        int strLen = str.length();
        Map<Character,Integer> recMap = new HashMap<>();
        for (int i = 0; i < strLen; i++) {
            Character curChar = chArr[i];
            if(recMap.containsKey(curChar)){
                start = Math.max(start,recMap.get(curChar));
            }
            int curLen = i - start + 1;
            if(curLen > maxLen){
                maxLen = curLen;
                startOffset = start;//i - maxLen + 1==>start
            }
            recMap.put(curChar,i+1);//i -> i+1
        }
        String maxStr = str.substring(startOffset,startOffset + maxLen);
        System.out.println("maxStr=="+maxStr);
        return maxLen;
    }

    private int maxLengthOfString(String str){
        int result = 0;
        int start = 0;
        char[] chars = str.toCharArray();
        int arrLen = chars.length;
        int[] lastPostRecord = new int[26];//26个字母
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
    public int maxLengthOfNum (int[] arr) {
        // 根据数字多少定义数组长度
        int[] lastPos = new int[10000];
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
