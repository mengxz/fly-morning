package com.bluesky.teck.zuo;

/**
 * 给定一个只有小写字母（a-z）组成的字符串str,
 * 返回其中最长无重复字符的子串长度
 *
 *
 */
public class Code_MaxLengthStr {

    /**
     * 方式1
     * @param s
     * @return
     */
    public static int lnrs(String s){
        if(s == null || s.length()==0)
            return 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[] last = new int[26];
        for (int i = 0; i < 26; i++) {
            last[i] = -1;
        }
        last[str[0] - 'a'] = 0;
        int max = 1;
        int preMaxLen = 1;
        for (int i = 1; i < N; i++) {
            preMaxLen = Math.min(i - last[str[i] - 'a'],preMaxLen+1);
            max = Math.max(max,preMaxLen);
            last[str[i]-'a'] = i;
        }
        return max;
    }

    /**
     * pos最多几个字符组成，pos=3，字符串由abc三个字符组成
     * maxSize生成字符串长度
     * @param pos
     * @param maxSize
     * @return
     */
    public static String getRandomString(int pos,int maxSize){
        //char[] ans = new char[(int)(Math.random()*maxSize)+1];
        char[] ans = new char[maxSize];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char)((int)(Math.random()*pos)+'a');
        }
        return String.valueOf(ans);
    }

    /**
     * 和方式1逻辑一样
     * @param str
     * @return
     */
    public static int findMaxDistinctSubStr(String str){
        if(str == null || str.length() ==0){
            return 0;
        }
        char[] chars = str.toCharArray();
        //上一次字符出现的位置
        int[] lastAddr = new int[26];
        for (int i = 0; i < lastAddr.length; i++) {
            lastAddr[i] = -1;
        }
        //第一个字母在0位置出现
        lastAddr[chars[0]-'a'] = 0;
        int preMax = 1;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            //按照当前字母计算最大位置
            int pos = chars[i] - 'a';
            int curMax = i - lastAddr[pos];
            int last = preMax + 1;
            preMax = Math.min(curMax,last);
            max = Math.max(preMax,max);
            lastAddr[chars[i]-'a'] = i;
        }
        return max;
    }

    public static void check(){
        String randomString = getRandomString(5, 15);
        //System.out.println("str:"+randomString);
        //randomString = "ccbacebcaabadebc";
        int lnrs = lnrs(randomString);
        int maxDistinctSubStr = findMaxDistinctSubStr(randomString);
        if(lnrs != maxDistinctSubStr){
            System.out.println("Oop!!!=="+randomString + "==" + lnrs +":"+maxDistinctSubStr);
        }else {
            System.out.println("success=="+randomString + "==" + lnrs);
        }
        //System.out.println("lnrs:"+lnrs);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            check();
        }
    }


}
