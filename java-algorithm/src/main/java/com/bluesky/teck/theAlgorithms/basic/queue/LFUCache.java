package com.bluesky.teck.theAlgorithms.basic.queue;

import java.util.HashMap;
import java.util.Map;

/**
 * 方案二：使用一条双向链表
 * 思路：
 * 只用一条双向链表，来维护频次和时间先后顺序。那么，可以这样想。把频次 freq 小的放前面，频次大的放后面。如果频次相等，就从当前节点往后遍历，直到找到第一个频次比它大的元素，并插入到它前面。（当然，如果遍历到了tail，则插入到tail前面）这样可以保证同频次的元素，最近访问的总是在最后边。
 * 因此，总的来说，最低频次，并且最久未访问的元素肯定就是链表中最前面的那一个了。这样的话，当 cache容量满的时候，直接把头结点删除掉就可以了。但是，我们这里为了方便链表的插入和删除操作，用了两个哨兵节点，来表示头节点 head和尾结点tail。因此，删除头结点就相当于删除 head.next。
 * PS：哨兵节点只是为了占位，实际并不存储有效数据，只是为了链表插入和删除时，不用再判断当前节点的位置。不然的话，若当前节点占据了头结点或尾结点的位置，还需要重新赋值头尾节点元素，较麻烦。
 */
public class LFUCache {
    private Map<Integer,Node> cache;
    private Node head;
    private Node tail;
    private int capacity;
    private int size;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        /**
         * 初始化头结点和尾结点，并作为哨兵节点
         */
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) return -1;
        node.freq++;
        moveToPostion(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        Node node = cache.get(key);
        if(node != null){
            node.value = value;
            node.freq++;
            moveToPostion(node);
        }else{
            //如果元素满了
            if(size == capacity){
                //直接移除最前面的元素，因为这个节点就是频次最小，且最久未访问的节点
                cache.remove(head.next.key);
                removeNode(head.next);
                size--;
            }
            Node newNode = new Node(key, value);
            //把新元素添加进来
            addNode(newNode);
            cache.put(key,newNode);
            size++;
        }
    }

    //只要当前 node 的频次大于等于它后边的节点，就一直向后找，
    // 直到找到第一个比当前node频次大的节点，或者tail节点，然后插入到它前面
    private void moveToPostion(Node node){
        Node nextNode = node.next;
        //先把当前元素删除
        removeNode(node);
        //遍历到符合要求的节点
        while (node.freq >= nextNode.freq && nextNode != tail){
            nextNode = nextNode.next;
        }
        //把当前元素插入到nextNode前面
        node.pre = nextNode.pre;
        node.next = nextNode;
        nextNode.pre.next = node;
        nextNode.pre = node;

    }

    //添加元素（头插法），并移动到合适的位置
    private void addNode(Node node){
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        moveToPostion(node);
    }

    //移除元素
    private void removeNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    class Node {
        int key;
        int value;
        int freq = 1;
        //当前节点的前一个节点
        Node pre;
        //当前节点的后一个节点
        Node next;

        public Node(){

        }

        public Node(int key ,int value){
            this.key = key;
            this.value = value;
        }
    }


    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        // 返回 1
        System.out.println(cache.get(1));
        cache.put(3, 3);    // 去除 key 2
        // 返回 -1 (未找到key 2)
        System.out.println(cache.get(2));
        // 返回 3
        System.out.println(cache.get(3));
        cache.put(4, 4);    // 去除 key 1
        // 返回 -1 (未找到 key 1)
        System.out.println(cache.get(1));
        // 返回 3
        System.out.println(cache.get(3));
        // 返回 4
        System.out.println(cache.get(4));

    }
}
