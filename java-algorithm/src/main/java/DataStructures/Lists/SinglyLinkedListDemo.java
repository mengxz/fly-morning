package DataStructures.Lists;
/**
 * http://c.biancheng.net/view/8105.html
 * https://blog.csdn.net/guyuealian/article/details/51119499
 */
public class SinglyLinkedListDemo {

    public static void main(String[] args) {
        SinglyLinkedListDemo linkedList = new SinglyLinkedListDemo();
        Node node = linkedList.genList();
        linkedList.print(node);
        Node re = linkedList.re03(node);
        linkedList.print(re);
    }

    //递归反转列表
    private Node re(Node head){
        if(head.next == null){
            return head;
        }
        Node last = re(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }


    /**
     * 头插法反转列表
     * @param head
     * @return
     */
    private Node re01(Node head){
        Node newHead = null;
        Node temp = null;
        if(head == null || head.next == null)
            return head;
        while (head != null){
            temp = head;
            head = head.next;
            temp.next = newHead;
            newHead = temp;
        }
        return newHead;
    }


    /**
     * 迭代反转列表
     * @param head
     * @return
     */
    private Node re02(Node head){
        if(head == null || head.next == null){
            return head;
        }

        Node begin = null;
        Node mid = head;
        Node end = head.next;
        while (true){
            mid.next = begin;
            if(end == null){
                break;
            }
            begin = mid;
            mid = end;
            end = end.next;
        }
        head = mid;
        return head;
    }

    /**
     * 就地逆置反转列表
     * @param head
     * @return
     */
    private Node re03(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node begin = head;
        Node end = head.next;
        while (end != null){
            begin.next = end.next;
            end.next = head;
            head = end;
            end = begin.next;
        }
        return head;
    }

    /**
     * 生成列表
     * @return
     */
    private Node genList(){
        Node next = null;
        for (int i = 10; i > 0; i--) {
            Node node = new Node(i);
            node.next = next;
            next = node;
        }
        return next;
    }

    /**
     * 打印列表
     * @param node
     */
    private void print(Node node){
        while (node != null){
            System.out.print("->"+node.val);
            node = node.next;
        }
        System.out.println();
    }

    class Node {
        int val;
        Node next;

        public Node(){
        }
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