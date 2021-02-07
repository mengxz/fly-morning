package com.bluesky.teck.zuo;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 如果两个字符串，所含字符种类完全一样，算作一类，如:abcd,adcbb,ddccab算作一类；
 * 只有小写字母（a-z）组成的一批字符串，都放在字符类型的数组String[] arr中，返回arr中一共有多少类？
 * arr[abc,cba,abd,aadb]==>两类
 */
public class Code_StringTypeCal {
    public static int type1(String[] arr){
        HashSet<String> types = new HashSet<>();
        for (String str : arr) {
            char[] chs = str.toCharArray();
            boolean[] map = new boolean[26];
            for (int i = 0; i < chs.length; i++) {
                map[chs[i] - 'a'] = true;
            }
            String key = "";
            for (int i = 0; i < map.length ; i++) {
                if(map[i]){
                    key += String.valueOf((char)(i+'a'));
                }
            }
            types.add(key);
        }
        return types.size();
    }

    public static int type2(String[] arr){
        HashSet<Integer> types = new HashSet<>();
        for (String str : arr) {
            char[] chs = str.toCharArray();
            int key = 0;
            for (int i = 0; i < chs.length; i++) {
                key |= (1 << (chs[i] - 'a'));
            }
            types.add(key);
        }
        return types.size();
    }
    /**
     * pos最多几个字符组成，pos=3，字符串由abc三个字符组成
     * maxSize生成字符串长度
     * @param pos
     * @param maxSize
     * @return
     */
    public static String[] getRandomStringArr(int pos,int maxSize,int num){
        String[] arr = new String[num];
        for (int j = 0; j < num; j++) {
            //char[] ans = new char[(int)(Math.random()*maxSize)+1];
            char[] ans = new char[maxSize];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = (char)((int)(Math.random()*pos)+'a');
            }
            String s = String.valueOf(ans);
            arr[j] = s;
        }
        return arr;
    }

    public static void main(String[] args) {
        String[] randomStringArr = getRandomStringArr(5, 10, 5);
        Arrays.asList(randomStringArr).stream().forEach(s -> System.out.println(s));
        int type1 = type1(randomStringArr);
        System.out.println("type1=="+type1);
        int type2 = type2(randomStringArr);
        System.out.println("type2=="+type2);
    }

}
