package com.bluesky.teck.leecode;

//121. 买卖股票的最佳时机
//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
//注意：你不能在买入股票前卖出股票。

//输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
public class Subject121 {
    //暴力解决
    public static int maxProfit1(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            int j = i+1;
            for (int k = j; k < prices.length; k++) {
                int a = prices[i];
                int b = prices[k];
                int c = b - a;
                if(c > result){
                    result = c;
                    System.out.println("=========i="+i+",k="+k+"，result="+result);
                }
                System.out.println("i="+i+",k="+k+"，result="+result);
            }
        }
        return result;
    }

    //循环一次
    //https://blog.csdn.net/qq_41688840/article/details/104750399
    public static int maxProfit(int[] prices) {
        int minPrize = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            if(minPrize > prices[i]){
                minPrize = prices[i];
            }else {
                int tmp = prices[i] - minPrize;
                if(tmp > result){
                    result = tmp;
                }
                System.out.println("=========i="+i+"，result="+result);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //int[] prices = {7,1,5,3,6,4};
        //int[] prices = {7,6,4,3,1};
        int[] prices = {100,120,130,1,20,30};
        int fin = maxProfit(prices);
        System.out.println("fin="+fin);
    }
}
