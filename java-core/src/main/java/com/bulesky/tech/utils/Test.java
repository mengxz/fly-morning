package com.bulesky.tech.utils;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        //test01();
        //test02();
        test03();
        Test test = new Test();
        test.test04();

    }

    public void test04(){
        ListNode node01 = new ListNode(1);
        ListNode node02 = new ListNode(2);
        ListNode node03 = new ListNode(3);
        ListNode node04 = new ListNode(4);
        node01.next = node02;
        node03.next = node04;
        ListNode listNode = mergeTwoLists(node01,node03);
        System.out.println(listNode.val);
    }

    /**
     *
     * @param l1 ListNode类
     * @param l2 ListNode类
     * @return ListNode类
     */
    public ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        // write code here
        ListNode newhead = new ListNode(-1);
        ListNode res = newhead;
        while(l1 != null || l2 != null){
            if(l1 != null && l2 != null){
                if(l1.val > l2.val){
                    newhead.next = l2;
                    l2 = l2.next;
                }else{
                    newhead.next = l1;
                    l1 = l1.next;
                }
                newhead = newhead.next;
            }else if(l1 != null && l2 == null){
                newhead.next = l1;
                l1 = l1.next;
                newhead = newhead.next;
            }else if(l1 == null && l2 != null){
                newhead.next = l2;
                l2 = l2.next;
                newhead = newhead.next;
            }

        }
        return res.next;
    }


    private static void test03() {
        HashMapNew<String,String> map = new HashMapNew<>();
//        Map<Integer,String> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            //map.put(i%10000,"val_"+i);
            map.put("key_"+i+"_AA","val_"+i);
        }
        //System.out.println(map.keySet());
        System.out.println("first==" + map.getFirst());
        System.out.println("second==" + map.getSecond());
        System.out.println("getSecond_one==" + map.getSecond_one());
        System.out.println("getSecond_two==" + map.getSecond_two());
        System.out.println("getSecond_three==" + map.getSecond_three());
    }

    /**
     * key为整数不出现tree情况
     */
    private static void test02() {
        HashMapNew<Integer,String> map = new HashMapNew<>();
//        Map<Integer,String> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            //map.put(i%10000,"val_"+i);
            map.put(i,"val_"+i);
        }
        //System.out.println(map.keySet());
        System.out.println("first==" + map.getFirst());
        System.out.println("second==" + map.getSecond());
        System.out.println("getSecond_one==" + map.getSecond_one());
        System.out.println("getSecond_two==" + map.getSecond_two());
        System.out.println("getSecond_three==" + map.getSecond_three());
    }

    private static void test01() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i <10 ; i++) {
            list.add(i%7);
        }
        Integer integer = list.get(7);
        System.out.println("integer==="+integer);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("====================================");
        Set set = new HashSet<Integer>();
        set.add(null);
        set.add(100);
        System.out.println(Arrays.toString(set.toArray()));
        for (int i = 0; i <10 ; i++) {
            set.add(i%7);
        }
        System.out.println(Arrays.toString(set.toArray()));
    }

    class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val){
            this.val = val;
        }
    }
}
