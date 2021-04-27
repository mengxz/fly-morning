package DataStructures.Lists;

public class SinglyLinkedListDemo {
    public static void main(String[] args) {
        SinglyLinkedListDemo demo = new SinglyLinkedListDemo();
        demo.test01();
    }

    private void test01(){
        Node node3 = new Node(null,3);
        Node node2 = new Node(node3,2);
        Node node1 = new Node(node2,1);
        print(node1);
        Node reverse = reverse(node1);
        print(reverse);
    }

    private void print(Node node){
        while (node != null){
            System.out.print(node.val+"->");
            node = node.next;
        }
        System.out.println();
    }

    private int addOne(Node node){

        int newVal = node.val +1;
        if(newVal >= 10){
            node.val = newVal %10;
            return 1;
        }else{
            node.val = newVal;
            return 0;
        }
    }

    private Node reverse(Node node){
        if(node ==null ||node.next==null){
            return node;
        }
        Node rehead = reverse(node.next);
        Node nextNode  = node.next;
        nextNode.next = node;
        node.next = null;
        return rehead;
    }


    class Node{
        public Node(Node next,int val){
            this.next = next;
            this.val = val;
        }
        Node next;
        int val;
    }
}
