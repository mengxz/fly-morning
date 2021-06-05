package com.bluesky.tech.lambda;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LambdaDemo {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
    }



    private static void test03(){
        Optional<Integer> result = Stream.of("a", "be", "hello")
                .map(s -> s.length())
                .filter(l -> l <= 3)
                .max((o1, o2) -> o1-o2);
        System.out.println(result.get()); // 输出2

        // 上面lambda表达式还原为函数接口的实现方式
        Optional<Integer> result2 = Stream.of("fo", "bar", "hello")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return s.length();
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer l) {
                        return l <= 3;
                    }
                })
                .max(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });
        System.out.println(result2.get()); // 输出2
    }

    private static void test02() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("do something 11...");
            }
        };
        new Thread(runnable).start();

        // 一行即可
        Runnable runnable01 = () -> System.out.println("do something 22...");
        new Thread(runnable01).start();
    }

    private static void test01() {
        Action action = System.out :: println;
        action.execute("Hello World!");
        test(System.out :: println, "Hello World!");
    }

    static void test(Action action, String str) {
        action.execute(str);
    }


    @FunctionalInterface
    interface Action<T> {
        void execute(T t);
    }
}
