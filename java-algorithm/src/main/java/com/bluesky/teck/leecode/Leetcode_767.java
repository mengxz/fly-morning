package com.bluesky.teck.leecode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 *
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 *
 * 示例 1:
 *
 * 输入: S = “aab”
 * 输出: “aba”
 * 示例 2:
 *
 * 输入: S = “aaab”
 * 输出: “”
 */
public class Leetcode_767 {
    public static void main(String[] args) {
        Leetcode_767 test = new Leetcode_767();
        String str = "abacc";
        //final String s = test.reorganizeString01(str);
        final String s = test.reorganizeString(str);
        System.out.println("s=="+s);
    }

    public String reorganizeString01(String S) {
        if(S.length()<2||S==null) return S;
        char[] chars=S.toCharArray();
        int[] nums=new int[26];
        int maxcount=0;
        //记录字符串中每个字母出现的次数
        for(char x:chars){
            nums[x-'a']++;
            maxcount=Math.max(maxcount,nums[x-'a']);
        }
        //如果最大的字母数大于数组的一半，那么他肯定就不能重组
        if(maxcount>(S.length()+1)/2) return "";
        PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>() {
            public int compare(Character letter1, Character letter2) {
                return nums[letter2 - 'a'] - nums[letter1 - 'a'];
            }
        });
        //对26个字母进行遍历但是不对数组chars进行遍历是为了queue中有重复的字符
        for(int i=0;i<26;i++){
            if(nums[i]>0)
                queue.offer((char)(i+'a'));
        }
        StringBuffer res=new StringBuffer();
        while(queue.size()>1){
            char a=queue.poll();
            char b=queue.poll();
            res.append(a);
            res.append(b);

            nums[a-'a']--;
            nums[b-'a']--;

            if(nums[a-'a']>0)
                queue.offer(a);
            if(nums[b-'a']>0)
                queue.offer(b);
        }
        if (queue.size() > 0) {
            res.append(queue.poll());
        }
        return res.toString();

    }


    public String reorganizeString(String S) {
        if(S.length()<2||S==null) return S;
        char[] chars=S.toCharArray();
        int[] nums=new int[26];
        int maxcount=0;
        //记录字符串中每个字母出现的次数
        for(char x:chars){
            nums[x-'a']++;
            maxcount=Math.max(maxcount,nums[x-'a']);
        }
        if(maxcount>(S.length()+1)/2) return "";
        StringBuffer res=new StringBuffer();
        while(res.length()!=S.length()){
            int max=0;
            //找出当前的具有最大字母数的字母
            for(int i=0;i<26;i++){
                if(nums[i]>nums[max])
                    max=i;
            }
            //将这个字母加入
            res.append((char)(max+'a'));
            nums[max]--;
            //再放入一个不同的字母
            for(char x:chars){
                if(nums[x-'a']>0&&x!=(char)(max+'a')){
                    res.append(x);
                    nums[x-'a']--;
                    break;
                }
            }
        }
        return res.toString();
    }

}
