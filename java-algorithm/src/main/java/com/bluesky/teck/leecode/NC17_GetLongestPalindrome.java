package com.bluesky.teck.leecode;

/**
 * 题目描述
 * 对于一个字符串，请设计一个高效算法，计算其中最长回文子串的长度。
 * 给定字符串A以及它的长度n，请返回最长回文子串的长度。
 *
 * 示例1
 * 输入
 * "abc1234321ab",12
 * 返回值
 * 7
 */
public class NC17_GetLongestPalindrome {

    public static void main(String[] args) {
        String str = "abc12344321ab";
//        String str = "abba";
        String s = longestPalindrome(str);
        System.out.println("s=="+s);
    }

    public static String longestPalindrome01(String s){
        if(s == null || s.length()==0)return "";
        int maxLen = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int i1 = expandAroundCenter01(s, i, i + 1);
            int i2 = expandAroundCenter01(s, i, i);
            if(i1 > maxLen){
                maxLen = i1;
                start = i - maxLen/2 + 1;
            }
            if(i2 > maxLen){
                maxLen = i2;
                start = i - maxLen/2;
            }
        }
        String res = s.substring(start,start+maxLen);
        return res;
    }

    public static int expandAroundCenter01(String s,int left,int right){
        //int left = le,right = ri;
        while (left>=0 && right <s.length() && s.charAt(left)==s.charAt(right)) {
            left--;
            right++;
        }
        int len = (right-1) - (left+1) + 1;
        return len;
    }


    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);//aba
            int len2 = expandAroundCenter(s, i, i+1);//abba

            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len-1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    public static  int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return (R-1) - (L+1) + 1;
    }
}
