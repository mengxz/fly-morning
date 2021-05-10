package com.bluesky.teck.leecode;

/**
 * 合并有序链表
 */
public class NC33_MergeLinkList {

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


    class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val){
            this.val = val;
        }
    }


    public static void main(String[] args) {
        NC33_MergeLinkList test = new NC33_MergeLinkList();
        test.test04();

    }

    public void test04(){
        ListNode node01 = new ListNode(1);
        ListNode node02 = new ListNode(2);
        ListNode node03 = new ListNode(3);
        ListNode node04 = new ListNode(4);
        node01.next = node03;
        node02.next = node04;
        ListNode listNode = mergeTwoLists(node01,node03);
        System.out.println(listNode.val);
    }
}
