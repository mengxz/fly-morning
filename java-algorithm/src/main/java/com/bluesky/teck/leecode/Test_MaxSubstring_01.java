package com.bluesky.teck.leecode;

public class Test_MaxSubstring_01 {


    // 求解两个字符号的最长公共子串
    public static String maxSubstring(String strOne, String strTwo){
        // 参数检查
        if(strOne==null || strTwo == null){
            return null;
        }
        if(strOne.equals("") || strTwo.equals("")){
            return null;
        }
        // 二者中较长的字符串
        String max = "";
        // 二者中较短的字符串
        String min = "";
        if(strOne.length() < strTwo.length()){
            max = strTwo;
            min = strOne;
        } else{
            max = strTwo;
            min = strOne;
        }
        String current = "";
        // 遍历较短的字符串，并依次减少短字符串的字符数量，判断长字符是否包含该子串
        for(int i=0; i<min.length(); i++){
            for(int begin=0, end=min.length()-i; end<=min.length(); begin++, end++){
                current = min.substring(begin, end);
                if(max.contains(current)){
                    return current;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String strOne = "abcdefg";
        String strTwo = "adefgwgeweg";
        String result = Test_MaxSubstring_01.maxSubstring(strOne, strTwo);
        System.out.println(result);

    }
}
