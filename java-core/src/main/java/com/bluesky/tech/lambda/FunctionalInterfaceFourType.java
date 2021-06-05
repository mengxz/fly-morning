package com.bluesky.tech.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceFourType {
    public static void main(String[] args) {
        consumerType();
        //supplierType();
//        functionType();
//        predicateType();
    }

    //type04:断言型接口
    public static void predicateType(){
        List<String> list = Arrays.asList("Hello","World","Function","Lambda","Java");
        List<String> newList = filterStr(list, (s) -> s.length() > 4);
        System.out.println(newList);
        Predicate<String> pre = (s) -> s.length() > 4;
        Predicate<String> pre01 = (s) -> s.contains("h");
        boolean test = pre.test("abcde");
        System.out.println("test:"+test);
        //两个条件判断
        Predicate<String> test02 = pre.and(pre01);
        boolean abcde = test02.test("abcdeh");
        System.out.println(abcde);
    }

    public static List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> newList = new ArrayList<>();

        for(String str : list){
            if(pre.test(str)){
                newList.add(str);
            }
        }
        return newList;
    }

    //type03:函数型接口
    public static void functionType(){
        Function<String,String> fun = (str)->str.substring(2,5);
        String s = fun.apply("我是函数型接口");
        System.out.println("s:"+s);
        String newStr = strHandler("我是函数型接口",fun);
        System.out.println(newStr);
        Function<String,String> fun01 = (str)->str.substring(2,5);
        Function<String,String> fun02 = (str)->str.replace("d","");
        //先fun01再fun02
        Function<String, String> andThen = fun01.andThen(fun02);
        String s1 = andThen.apply("abcdefghi");
        System.out.println("s1:"+s1);
        //先fun02再fun01
        Function<String, String> compose = fun01.compose(fun02);
        String s2 = compose.apply("abcdefghi");
        System.out.println("s2:"+s2);
    }

    public static String strHandler(String str, Function<String,String> fun){
        return fun.apply(str);
    }

    //type02:供给型接口
    private static void supplierType() {
        Supplier<Integer> supplier = () -> (int) (Math.random() * 100);
        Integer integer = supplier.get();
        System.out.println("integer:"+integer);
        FunctionalInterfaceFourType consumertest = new FunctionalInterfaceFourType();
        final List<Integer> numList = consumertest.getNumList(10, () -> (int) (Math.random() * 100));
        numList.stream().forEach(x -> System.out.println(x));
    }

    //产生一些整数，并放入集合中. int num 为产生的个数
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {

        List<Integer> list = new ArrayList<>();

        for(int i = 0;i<num;i++) {
            Integer n =supplier.get();
            list.add(n);
        }
        return list;
    }

    //type01:消费型接口
    private static void consumerType(){
        FunctionalInterfaceFourType consumertest = new FunctionalInterfaceFourType();
        //传入字符串，然后打印
        consumertest.pass("I'm consumerFunction", (item) -> System.out.println(item));

        Consumer<Integer> consumer01 = x -> {
            int a = x + 2;
            System.out.println("consumer01:"+a);// 12
        };
        consumer01.accept(10);
        Consumer<Integer> consumer02 = x -> {
            int a = x * 2;
            System.out.println("consumer02:"+a);// 20
        };
        final Consumer<Integer> andThen = consumer01.andThen(consumer02);
        andThen.accept(10);
    }

    //接受str字符串参数
    public void pass(String str, Consumer<String> consumer) {
        consumer.accept(str);
    }

}
