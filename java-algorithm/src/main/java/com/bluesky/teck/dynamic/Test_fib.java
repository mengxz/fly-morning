package com.bluesky.teck.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契数列
 * f(0)=0  f(1)=1
 * f(2)=f(0)+f(1)=0+1=1
 * f(3)=f(2)+f(1)=1+1=2
 * f(4)=f(3)+f(2)=2+1=3
 * ..
 * f(20)=f(20)+f(19)
 */
public class Test_fib {
    public static void main(String[] args) {
        Test_fib demo = new Test_fib();
        int solution = demo.solution(8);
    }


    int count_1 = 0;
    int count_2 = 0;
    private int solution(int n){
//        int result = fibonacci(n);
//        int result = fibonacciHelper(n);
        int result = fibonacciHelper_02(n);
        System.out.println("count_1=="+count_1);
        System.out.println("count_2=="+count_2);
        System.out.println("result="+result);
        return result;
    }

    /**
     * 从低往上迭代
     */
    int begin = 0;
    int end = 1;
    private int fibonacciHelper_02(int n){
        if(n==0||n==1){
            return n;
        }
        int result = 0;
        for (int i = 2; i <= n ; i++) {
            result = begin +end;
            begin = end;
            end = result;
        }
        return result;
    }

    /**
     * 递归2，从顶之下
     * 预存计算结果
     */
    Map<Integer,Integer> map = new HashMap();
    private int fibonacciHelper(int n){
        count_1++;
        if(n==0||n==1){
            return n;
        }
        if(map.containsKey(n)){
            return map.get(n);
        }
        count_2++;
        int result = fibonacciHelper(n-1)+fibonacciHelper(n-2);
        map.put(n,result);
        return result;
    }

    /**
     * 递归1
     * @param n
     * @return
     */
    private int fibonacci(int n){
        count_1++;
        if(n==0 || n==1)
            return n;
        count_2++;
        int result = fibonacci(n-1)+fibonacci(n-2);
        return result;
    }


}
