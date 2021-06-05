package DataStructures.Lists;

/**
 * 部分链表反转
 */
public class ReversePartSinglyLinkedList {

    public static void main(String[] args) {
        ReversePartSinglyLinkedList demo = new ReversePartSinglyLinkedList();
        Node node = demo.genSinglyList();
        demo.print(node);
//        Node reverse = demo.reverse(node);
//        demo.print(reverse);
//        Node reverseN01 = demo.reverseN(node,3);
//        demo.print(reverseN01);
        Node node1 = demo.reverseBetween(node, 2, 4);
        demo.print(node1);
    }

    /**
     * 翻转中间某几个元素
     * 输入:a-b-c-d-e-f-g-h-i;3;2
     * 输出:a-b-d-c-e-f-g-h-i
     * @param head
     * @param m
     * @param n
     * @return
     */
    private Node reverseBetween(Node head,int m,int n){
        if(m == 1){
            Node node = reverseN(head, n);
            return node;
        }

        head.next = reverseBetween(head.next, m-1, n-1);
        return head;
    }


    /**
     * 翻转前N个节点
     */
    Node suc = null;
    private Node reverseN(Node head, int num){
        if(num == 1){
            suc = head.next;
            return head;
        }
        Node newHead = reverseN(head.next, num-1);
        head.next.next = head;
        head.next = suc;
        return newHead;
    }

    /**
     * 翻转整个单向列表
     * @param head
     * @return
     */
    private Node reverse(Node head){
        if(head.next == null){
            return head;
        }
        Node last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    private Node genSinglyList() {
        String str = "abcdefghi";
        Node head = null;
        Node begin = null;
        Node end;
        for (int i = 0; i < str.length(); i++) {
            if(i == 0){
                head = new Node(str.charAt(0));
                begin = head;
            }else {
                end = new Node(str.charAt(i));
                begin.next = end;
                begin = end;
            }
        }
        return head;
    }

    class Node{
        char val;
        Node next;
        public Node(char val){
            this.val = val;
        }

        public Node(){
        }

        public char getVal() {
            return val;
        }

        public void setVal(char val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private void print(Node node){
        while (node != null){
            System.out.print("->"+node.getVal());
            node = node.next;
        }
        System.out.println();
    }


}
