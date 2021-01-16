package com.bluesky.teck.tree.BSTTree;

/**
 * 将数组转二叉搜索树
 * https://www.bilibili.com/video/BV15D4y1X7Tt?t=2678
 * 数组[5,9,7,13,19,15,10]==>10,[5,9,7],[13,19,15]
 * -------------------------------------------------
 *             10
 *         /      \
 *       7        15
 *      /  \     /   \
 *    5    9    13    19
 *
 */
public class Code01_PostArrayToBST {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node postArrayToBST(int[] arr){
//        return process01(arr,0,arr.length-1);
        return process01_1(arr,0,arr.length-1);
        //return process02(arr,0,arr.length-1);
    }

    public static Node process01(int[] arr,int left,int right){
        if(left > right){
            return null;
        }
        Node head = new Node(arr[right]);
        if(left == right){
            return head;
        }
        //都是右节点
        if(arr[left] > head.value){
            head.right = process01(arr, left, right-1);
            return head;
        }
        //都是左节点
        if(arr[right-1] < head.value){
            head.left = process01(arr, left, right-1);
            return head;
        }
        for(int i = left;i < right; i++){
            if(arr[i] > head.value){
                head.left = process01(arr,left,i-1);
                head.right = process01(arr,i, right-1);
                //开始写没有break,注意
                break;
            }
        }
        return head;
    }

    public static Node process01_1(int[] arr,int left,int right){
        if(left > right){
            return null;
        }
        Node head = new Node(arr[right]);
        if(left == right){
            return head;
        }
        //都是右节点
        if(arr[left] > head.value){
            head.right = process01_1(arr, left, right-1);
            return head;
        }
        //都是左节点
        if(arr[right-1] < head.value){
            head.left = process01_1(arr, left, right-1);
            return head;
        }
        int m = -1;
        for(int i = left;i < right; i++){
            if(arr[i] < head.value){
                m = i;
            }else{
                break;
            }
        }
        head.left = process01_1(arr,left,m);
        head.right = process01_1(arr,m+1, right-1);
        return head;
    }

    public static Node process02(int[] arr,int left,int right){
        if(left > right){
            return null;
        }
        Node head = new Node(arr[right]);
        if(left == right){
            return head;
        }
        int m = -1;
        for(int i = left;i < right; i++){
            if(arr[i] < head.value){
                m = i;
            }
        }
        //都是右节点
        if(m == -1){
            head.right = process02(arr, left, right-1);
            return head;
        }
        //都是左节点
        if(m == (right -1)){
            head.left = process02(arr, left, right-1);
            return head;
        }
        //左右都包含
        head.left = process02(arr,left,m);
        head.right = process02(arr,m+1, right-1);
        return head;
    }

    public static void toString(Node root) {
        if (root != null) {
            toString(root.left);
            System.out.print("value = " + root.value + " -> ");
            toString(root.right);
        }
    }

    public static void main(String[] args) {
        //int[] arr = {5,9,7};
        int[] arr = {5,9,7,13,19,15,10};
        //都在左节点
        //int[] arr = {3,4,5,6};
        //都在右节点
        //int[] arr = {6,5,4,3};
        Node node = postArrayToBST(arr);
        toString(node);
    }

}
