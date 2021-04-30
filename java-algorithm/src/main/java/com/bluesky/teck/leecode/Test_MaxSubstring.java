package com.bluesky.teck.leecode;

public class Test_MaxSubstring {

    // 求解两个字符号的最长公共子串
    public static void main(String[] args) {
//        int[][] a = new int[2][3];
//        test03(a,2,3);
        String strX = "zzzzabcdefkklkkk";
        String strY = "czzcabcdllllll";
        test04(strX, strY);
    }

    private static void test04(String strX, String strY) {
        int x = strX.length() + 1;
        int y = strY.length() + 1;
        int a[][] = new int[x + 1][y + 1];
        printArr(a, x, y);
        char[] charsY = strY.toCharArray();
        char[] charsX = strX.toCharArray();
        int max = 0;
        String str = "";
        for (int i = 0; i < y - 1; i++) {
            char charY = charsY[i];
            for (int j = 0; j < x - 1; j++) {
                char charX = charsX[j];
                if (charY == charX) {
                    a[i + 1][j + 1] = a[i][j] + 1;
                    if (a[i + 1][j + 1] > max) {
                        max = a[i + 1][j + 1];
                        str = strX.substring(j-max+1,j+1);
                    }
                }
            }
        }
        printArr(a, x, y);
        System.out.println("max==" + max);
        System.out.println("maxStr==="+str);
    }

    private static void printArr(int[][] a, int maxX, int maxY) {
        System.out.println("----------------begin----------------");
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                System.out.print(a[j][i] + "===");
            }
            System.out.println();
        }
        System.out.println("----------------end----------------");
    }
}
