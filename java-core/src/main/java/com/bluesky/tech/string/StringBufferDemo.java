package com.bluesky.tech.string;

/**
 * 比较StringBuffer和StringBuilder
 */
public class StringBufferDemo {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ab")
                .append("cd");
        final String s = stringBuffer.toString();
        System.out.println("s=="+s);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ab")
                .append("cd");
        final String s1 = stringBuilder.toString();
        System.out.println("s1=="+s1);
    }

}
