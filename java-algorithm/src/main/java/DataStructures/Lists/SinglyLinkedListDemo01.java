package DataStructures.Lists;

/**
 * http://c.biancheng.net/view/8105.html
 * https://blog.csdn.net/guyuealian/article/details/51119499
 */
public class SinglyLinkedListDemo01 {
        public static void main(String[] args) {
            SinglyLinkedListDemo01 demo01 = new SinglyLinkedListDemo01();
            demo01.test01();
        }

    private void test01() {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        // 打印反转前的链表
        Node h = head;
        while (null != h) {
            System.out.print(h.getData() + " ");
            h = h.getNext();
        }
        // 调用反转方法
        // head = reverse1(head);
        head = reverse2(head);

        System.out.println("\n**************************");
        // 打印反转后的结果
        while (null != head) {
            System.out.print(head.getData() + " ");
            head = head.getNext();
        }
    }

    /**
         * 遍历，将当前节点的下一个节点缓存后更改当前节点指针
         */
        public static Node reverse2(Node head) {
            if (head == null)
                return head;
            Node pre = head;// 上一结点
            Node cur = head.getNext();// 当前结点
            Node tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
            while (cur != null) {// 当前结点为null，说明位于尾结点
                tmp = cur.getNext();
                cur.setNext(pre);// 反转指针域的指向

                // 指针往下移动
                pre = cur;
                cur = tmp;
            }
            // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
            head.setNext(null);

            return pre;
        }

    class Node {
        private int Data;// 数据域
        private Node Next;// 指针域

        public Node(int Data) {
            // super();
            this.Data = Data;
        }

        public int getData() {
            return Data;
        }

        public void setData(int Data) {
            this.Data = Data;
        }

        public Node getNext() {
            return Next;
        }

        public void setNext(Node Next) {
            this.Next = Next;
        }
    }
}