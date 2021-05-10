package com.bluesky.teck.leecode;

import java.util.Arrays;

/**
 * 参考:https://blog.csdn.net/chinesekobe/article/details/110874773
 * 分别按照二叉树先序，中序和后序打印所有的节点。
 * 示例1
 * 输入{1,2,3}
 * 返回值
 * [[1,2,3],[2,1,3],[2,3,1]]
 */
public class NC45_TreeNodeScan {

    public static void main(String[] args) {
        NC45_TreeNodeScan test = new NC45_TreeNodeScan();
        test.test01();
    }

    private void test01(){
        TreeNode treeNode01 = new TreeNode(1);
        TreeNode treeNode02 = new TreeNode(2);
        TreeNode treeNode03 = new TreeNode(3);
        treeNode01.left = treeNode02;
        treeNode01.right = treeNode03;
        //threeOrders(treeNode01);
        int[][] res = new int[3][getSize(treeNode01)];
        showPre(res,treeNode01);
        System.out.println(Arrays.deepToString(res));
        showMiddle(res,treeNode01);
        System.out.println(Arrays.deepToString(res));
        showAfter(res,treeNode01);
        System.out.println(Arrays.deepToString(res));
    }

    private void showPre(int[][] arr,TreeNode treeNode){
        if(treeNode==null)
            return ;
        arr[0][i1++] = treeNode.val;
        showPre(arr,treeNode.left);
        showPre(arr,treeNode.right);
    }

    private void showMiddle(int[][] arr,TreeNode treeNode){
        if(treeNode==null)
            return ;
        showMiddle(arr,treeNode.left);
        arr[1][i2++] = treeNode.val;
        showMiddle(arr,treeNode.right);
    }

    private void showAfter(int[][] arr,TreeNode treeNode){
        if(treeNode==null)
            return ;
        showAfter(arr,treeNode.left);
        showAfter(arr,treeNode.right);
        arr[2][i3++] = treeNode.val;

    }

    int i1 = 0;
    int i2 = 0;
    int i3 = 0;

    /**
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders(TreeNode root) {
        // write code here
        int[][] res = new int[3][getSize(root)];
        order(res, root);
        System.out.println(Arrays.deepToString(res));
        return res;
    }

    public void order(int[][] res, TreeNode root) {
        if (root == null) return;
        res[0][i1++] = root.val;
        order(res, root.left);
        res[1][i2++] = root.val;
        order(res, root.right);
        res[2][i3++] = root.val;
    }

    public int getSize(TreeNode root) {
        if (root == null) return 0;
        return 1 + getSize(root.left) + getSize(root.right);
    }


    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val){
            this.val = val;
        }
    }

}
