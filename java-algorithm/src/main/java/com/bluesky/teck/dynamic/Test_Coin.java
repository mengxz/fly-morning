package com.bluesky.teck.dynamic;

import java.util.Arrays;
import java.util.List;

public class Test_Coin {
    //记录已经计算的结果，避免重复计算
    int[] resArr;
    int count01= 0;
    int count02 = 0;


    public static void main(String[] args) {
        Test_Coin demo = new Test_Coin();
        int[] arr = {1,2,5};
        int amount = 980;
        demo.test04(arr,amount);
    }

    private int test04(int[]arr,int amount){
        resArr = new int[amount+1];
        Arrays.fill(resArr,-666);
        int res = coinChange04(arr,amount);
        printArr(resArr);
        System.out.println("res="+res);
        return res;
    }

    private int coinChange04(int[] arr, int amount){
        if(amount == 0)
            return 0;
        if(amount < 0)
            return -1;
        if(resArr[amount] != -666){
            return resArr[amount];
        }
        Integer res = Integer.MAX_VALUE;
        for (int curCoinNum : arr) {
            int subProblem = coinChange04(arr,amount - curCoinNum);
            if(subProblem == -1)
                continue;
            res = Math.min(res,subProblem+1);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        resArr[amount] = res;
        return res;
    }


    private int test02(int[] arr,int amount){
        resArr = new int[amount+1];
        Arrays.fill(resArr,-666);
        int i = coinChange02(arr, amount);
        printArr(resArr);
        System.out.println("count01==>"+count01);
        System.out.println("count02==>"+count02);
        System.out.println("i==" + i);
        return i;
    }

    private int coinChange02(int[] arr,int amount){
        count01++;
        if(amount==0)
            return 0;
        if (amount<0)
            return -1;
        if(resArr[amount]!=-666)
            return resArr[amount];
        int res = Integer.MAX_VALUE;
        for (Integer num : arr) {
            count02++;
            int subRes = coinChange02(arr, amount-num);
            if(subRes==-1)
                continue;
            res = Math.min(res,subRes+1);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        resArr[amount] = res;
        return res;
    }

    private void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print("-->"+i+"["+arr[i]+"]");
        }
        System.out.println();
    }

    private int test01(int[] arr,int amount){
        int i = coinChange01(arr, amount);
        System.out.println("i==" + i);

        System.out.println("count01==>"+count01);
        System.out.println("count02==>"+count02);
        return i;
    }



    /**
     * 有重复计算，复杂度高
     * @param arr
     * @param amount
     * @return
     */
    private int coinChange01(int[] arr,int amount){
        count01++;
        System.out.println("amount"+ amount);
        if(amount==0)
            return 0;
        if(amount<0)
            return -1;
        int res01 = Integer.MAX_VALUE;
        for (Integer num : arr) {
            count02++;
            int subPro = coinChange01(arr,amount - num);
            if(subPro==-1)
                continue;
            res01 = Math.min(res01,subPro+1);
        }
        return res01 == Integer.MAX_VALUE ? -1 : res01;
    }

    /**
     * 有重复计算，复杂度高
     * @param arr
     * @param amount
     * @return
     */
    private int coinChange(int[] arr, int amount){
        if(amount == 0)
            return 0;
        if(amount < 0)
            return -1;

        int res = Integer.MAX_VALUE;
        for (Integer num : arr) {
            int subProblem = coinChange(arr,amount - num);
            if(subProblem == -1)
                continue;
            res = Math.min(res,subProblem+1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
