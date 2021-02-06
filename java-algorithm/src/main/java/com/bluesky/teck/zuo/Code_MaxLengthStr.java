package com.bluesky.teck.zuo;

/**
 * 给定一个只有小写字母（a-z）组成的字符串str,
 * 返回其中最长无重复字符的子串长度
 *
 *
 */
public class Code_MaxLengthStr {

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

    public static void main(String[] args) {
        String randomString = getRandomString(5, 15);
        System.out.println("str:"+randomString);
        randomString = "ccbacebcaabadebc";
        int lnrs = lnrs(randomString);
        System.out.println("lnrs:"+lnrs);
    }
}
