package com.bluesky.teck.zuo;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int maxLength = 10;
        int maxChar = 5;
        String str = makeStr(maxLength,maxChar);
        System.out.println("str:"+str);
        str = "aabcb";
        int maxDistinctSubStr = findMaxDistinctSubStr(str);
        System.out.println("maxDistinctSubStr:"+maxDistinctSubStr);
    }

    private static String makeStr(int maxLength, int maxChar) {
        String res = "";
        char[] chars = new char[maxLength];
        for (int i = 0; i < maxLength; i++) {
            int num = new Random().nextInt(maxChar);
            char a = (char)('a' + num);
            chars[i] = a;
        }
        res = new String(chars);//eddeddbacb
        return res;
    }

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
}
