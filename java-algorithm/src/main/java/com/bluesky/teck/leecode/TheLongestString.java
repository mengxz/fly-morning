package com.bluesky.teck.leecode;

import java.util.Arrays;
import java.util.Scanner;

public class TheLongestString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str1 = scanner.nextLine().toLowerCase();
            String str2 = scanner.nextLine().toLowerCase();
            System.out.println("str1=="+str1);
            System.out.println("str2=="+str2);
            System.out.println(findLCS(str1, str1.length(), str2, str2.length()));
        }
    }

    public static int findLCS(String A, int n, String B, int m) {
        int[][] dp = new int[n + 1][m + 1];
        System.out.println("==1==:"+ Arrays.deepToString(dp));
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = 0;
            }
        }
        System.out.println("==2==:"+ Arrays.deepToString(dp));
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
            System.out.println("==2="+i+"=:"+ Arrays.deepToString(dp));
        }
        return dp[n][m];

    }
}