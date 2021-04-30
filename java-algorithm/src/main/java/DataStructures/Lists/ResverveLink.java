package DataStructures.Lists;
/**
 *将数字123放置单向链表1-2-3，然后实现对改单向链表+1，1-2-3加1后变成1-2-4
 * 举例:
 * 1-2-9====>1-3-0
 * 1-9-9====>2-0-0
 * 199-9====>1-0-0-0
 */
public class ResverveLink {

    public static void main(String[] args) {
        ResverveLink link = new ResverveLink();
        link.test01();
    }

    private void test01() {
        int num = 999;
        String str = ""+ num;
        char[] chars = str.toCharArray();
        Node head = new Node(-1);
        Node cur = head;
        for (char ch : chars){
            int i = Integer.parseInt(""+ch);
            Node newNode = new Node(i);
            cur.next  = newNode;
            cur = newNode;
        }
        print(head);
        Node reversal = reversal(head);
        print(reversal);
        addOne(reversal);
        System.out.println("after addOne==>");
        print(reversal);
        Node reversal1 = reversal(reversal);
        print(reversal1);

    }

    private void addOne(Node node){
        Node curNode = node;
        boolean flag = false;
        int index = 0;
        while (curNode != null){
            int val = curNode.val;
            if(val == -1){
                curNode = curNode.next;
                continue;
            }
            index ++;
            if(index >1 && flag == false){
                break;
            }
            if(index == 1){
                val = val + 1;
            }
            if(flag){
                val = val + 1;
            }
            if(val >= 10){
                flag = true;
                val = val % 10;
            }else {
                flag = false;
            }
            curNode.setVal(val);
            if(curNode.next == null && flag){
                Node newNode = new Node(1);
                curNode.next = newNode;
                break;
            }else{
                curNode = curNode.next;
            }
        }
    }

    private Node reversal(Node head){
        Node newHead = new Node(-1);
        Node curNode = head;
        while (curNode != null){
            int val = curNode.val;
            if(val == -1){
                curNode = curNode.next;
                continue;
            }
            Node newNode = new Node(val);
            newNode.next = newHead.next;
            newHead.next = newNode;
            curNode = curNode.next;
        }
        return newHead;
    }


    private void print(Node head){
        Node curNode = head;
        while (curNode != null){
            if(curNode.val >= 0){
                System.out.print("->"+curNode.val);
            }
            curNode = curNode.next;
        }
        System.out.println();
    }

    class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
