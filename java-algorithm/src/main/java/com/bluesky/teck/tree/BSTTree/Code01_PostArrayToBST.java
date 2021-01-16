package com.bluesky.teck.tree.BSTTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 将数组转二叉搜索树
 * https://www.bilibili.com/video/BV15D4y1X7Tt?t=2678
 * 数组[5,9,7,13,19,15,10]==>10,小于10的[5,9,7],大于10的[13,19,15]
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

    public static Node posArrayToBST(int[] arr){
        //return process01(arr,0,arr.length-1);
        //return process02(arr,0,arr.length-1);
        return process03(arr,0,arr.length-1);
    }

    /**
     * 可以先看process02
     * process01是对process02的简化
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static Node process01(int[] arr,int left,int right){
        if(left > right){
            return null;
        }
        Node head = new Node(arr[right]);
        if(left == right){
            return head;
        }
        int m = left - 1;
        for(int i = left;i < right; i++){
            if(arr[i] < head.value){
                m = i;
            }
        }

        head.left = process01(arr,left,m);
        head.right = process01(arr,m+1, right-1);
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

    /**
     * 查找使用了二分法
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static Node process03(int[] arr,int left,int right){
        if(left > right){
            return null;
        }
        Node head = new Node(arr[right]);
        if(left == right){
            return head;
        }
        int m = left - 1;//小于所在数组的位置
        int low = left;
        int high = right -1;
        while(low <= high){
            //int mid = (low +(high) -low)/2;
            //二分查找，位运算，与上面算式等价
            int mid = low +((high -low)>>1);
            if(arr[mid] < head.value){
                m = mid;
                //如果中间的数小于head值，查找右边一半
                low = mid+1;
            }else{
                //如果中间的数大于head值，查找左边一半
                high = mid -1;
            }

        }

        head.left = process03(arr,left,m);
        head.right = process03(arr,m+1, right-1);
        return head;
    }

    //for test
    public static int[] getBstPosArray(Node head){
        List<Integer> posList = new ArrayList<>();
        pos(head,posList);
        int[] ans = new int[posList.size()];
        for(int i=0; i< ans.length; i++){
            ans[i] = posList.get(i);
        }
        return ans;
    }

    public static void pos(Node head, List<Integer> posList){
        if(head != null){
            pos(head.left, posList);
            pos(head.right, posList);
            posList.add(head.value);
        }
    }

    public static Node generateRandomBST(int min,int max,int N){
        if(min > max) {
            return null;
        }
        return createTree(min,max,1,N);
    }

    public static Node createTree(int min,int max,int level,int N){
        if(min>max || level > N){
            return null;
        }
        Node head = new Node(random(min,max));
        head.left = createTree(min,head.value-1,level+1,N);
        head.right = createTree(head.value+1,max,level+1,N);
        return head;
    }

    public static void printTree(Node head){
        System.out.println("Binary Tree:");
        printInOrder(head,0,"H",17);
        System.out.println();
    }

    public static void printInOrder(Node head,int height,String to,int len){
        if(head==null){
            return;
        }
        printInOrder(head.right, height+1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len-lenM)/2;
        int lenR = len-lenM-lenL;
        val = getSpace(lenL)+val+getSpace(lenR);
        System.out.println(getSpace(height*len)+val);
        printInOrder(head.left, height+1, "^", len);

    }

    public static String getSpace(int num){
        String space = "  ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static int random(int min,int max){
        return min + (int)(Math.random()*(max-min+1));
    }

    public static boolean isSameValueStructure(Node head1,Node head2){
        if(head1 == null && head2 != null){
            return false;
        }
        if(head1 != null && head2 == null){
            return false;
        }
        if(head1 == null && head2 ==null){
            return true;
        }
        return head1.value == head2.value
                && isSameValueStructure(head1.left, head2.left)
                && isSameValueStructure(head1.right, head2.right);
    }

    public static void toString(Node root) {
        if (root != null) {
            toString(root.left);
            System.out.print("value = " + root.value + " -> ");
            toString(root.right);
        }
    }

    public static void main(String[] args) {
        int min = 0;
        int max = 12;
        int level = 4;
        Node head = generateRandomBST(min,max,level);
        int[] pos = getBstPosArray(head);
        printTree(head);
        printTree(posArrayToBST(pos));
        System.out.println(isSameValueStructure(head,posArrayToBST(pos)));

        int testTime = 1;
        System.out.println("test begin---");
        for (int i = 0; i < testTime ; i++) {
            head = generateRandomBST(min,max,level);
            pos = getBstPosArray(head);
            if(!isSameValueStructure(head,posArrayToBST(pos))){
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish---");
    }

    public static void mainTest01() {
        //int[] arr = {5,9,7};
        int[] arr = {5,9,7,13,19,15,10};
        //都在左节点
        //int[] arr = {3,4,5,6};
        //都在右节点
        //int[] arr = {6,5,4,3};
        Node node = posArrayToBST(arr);
        toString(node);
    }

}
