package com.bluesky.teck.sort.test;

public class Test {
    // 求解两个字符号的最长公共子串
    public static void main(String[] args) {
//        int[][] a = new int[2][3];
//        test03(a,2,3);
        String strX = "zzzzabcdefkklkkk";
        String strY = "czzcabcdllllll";
        final String s = maxSubstring(strX, strY);
        System.out.println("s=="+s);
    }

    // 求解两个字符号的最长公共子串
    public static String maxSubstring(String strOne, String strTwo) {
        if(strOne==null ||strOne.length()==0)
            return null;
        if(strTwo==null ||strTwo.length()==0)
            return null;
//        Integer[] topLine = new Integer[strOne.length()];
//        Integer[] curLine = new Integer[strOne.length()];
        // 保存矩阵的上一行
        int[] topLine = new int[strOne.length()];
        // 保存矩阵的当前行
        int[] curLine = new int[strOne.length()];

        int maxLength = 0;
        int pos = 0;
        for (int i = 0; i < strTwo.length(); i++) {
            char ch = strTwo.charAt(i);
            for (int j = 0; j < strOne.length(); j++) {
                final char c = strOne.charAt(j);
                if(ch == c){
                    if(j==0){
                        curLine[0] = 1;
                    }else {
                        curLine[j] = topLine[j-1]+1;
                    }
                }
                if(curLine[j] > maxLength){
                    maxLength = curLine[j];
                    pos = j;
                }
            }
            for (int j = 0; j < curLine.length; j++) {
                topLine[j] = curLine[j];
                curLine[j] = 0;
            }
        }
        final String substring = strOne.substring(pos - maxLength + 1, pos+1);
        return substring;
    }

}
