package com.bluesky.teck.leecode;

/**
 * 题目描述
 * 给定一个链表，删除链表的倒数第 nn 个节点并返回链表的头指针
 * 例如，
 * 给出的链表为: 1\to 2\to 3\to 4\to 51→2→3→4→5, n= 2n=2.
 * 删除了链表的倒数第 nn 个节点之后,链表变为1\to 2\to 3\to 51→2→3→5.
 *
 * 备注：
 * 题目保证 nn 一定是有效的
 * 请给出请给出时间复杂度为\ O(n) O(n) 的算法
 */
public class NC53_DeleteTheLastN_Node {
    public static void main(String[] args) {
        NC53_DeleteTheLastN_Node test = new NC53_DeleteTheLastN_Node();
        //int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12};
        //int[] arr = {1,2,3};
        int[] arr = {1};
        ListNode linkList = test.getLinkList(arr);
        test.printf(linkList);
        ListNode solution = test.solution_1(linkList, 1);
        test.printf(solution);
    }

    private ListNode solution(ListNode head,int n){
        ListNode first = head;
        ListNode second = head;
        ListNode pre = head;
        if(head.next == null)
            return new ListNode(Integer.MAX_VALUE);
        while (n > 1){
            if(first.next != null){
                first = first.next;
                n--;
            }else {
                return head;
            }
        }
        while (first.next != null){
            pre = second;
            first = first.next;
            second = second.next;
        }
        pre.next = second.next;
        return head;
    }

    private void printf(ListNode node){
        while (node != null){
            System.out.print("-->"+node.val);
            node = node.next;
        }
        System.out.println();
    }

    private ListNode getLinkList(int[] arr){
        ListNode head = new ListNode(0);
        ListNode pre = head;
        for (int i = 0; i <  arr.length; i++) {
            ListNode cur = new ListNode(arr[i]);
            pre.next = cur;
            pre = cur;
        }
        return head.next;
    }

     public class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val){
            this.val = val;
        }
      }

    private ListNode solution_1(ListNode head,int n){
        ListNode first = head;
        ListNode second = head;
        ListNode pre = head;
        if(head == null){
            return head;
        }
        while (n > 1){
            if(first.next != null){
                first = first.next;
                n--;
            }else {
                return head;
            }
        }
        while (first.next != null){
            pre = second;
            first = first.next;
            second = second.next;
        }
        pre.next = second.next;
        return head;
    }
}
