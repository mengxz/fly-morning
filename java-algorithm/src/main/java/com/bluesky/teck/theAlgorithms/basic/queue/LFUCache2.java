package com.bluesky.teck.theAlgorithms.basic.queue;

import java.util.HashMap;
import java.util.Map;

/**
 * 方案四：手动实现一个频次链表
 * 由于方案三用的是JDK自带的 LinkedHashSet ，其是实现了哈希表和双向链表的一个类，因此为了减少哈希相关的计算，提高效率，我们自己实现一条双向链表来替代它。
 * 那么，这条双向链表，就需要维护当前频次下的所有元素的先后访问顺序。我们采用头插法，把新加入的元素添加到链表头部，这样的话，最久未访问的元素就在链表的尾部。
 */
public class LFUCache2 {

    public static void main(String[] args) {
        LFUCache2 cache = new LFUCache2(2);
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

    private Map<Integer,Node> cache;
    private Map<Integer,DoubleLinkedList> freqMap;
    private int capacity;
    private int size;
    private int min;

    public LFUCache2(int capacity){
        this.capacity = capacity;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key){
        Node node = cache.get(key);
        if(node == null) return -1;
        freqInc(node);
        return node.value;
    }

    public void put(int key, int value){
        if(capacity == 0) return;
        Node node = cache.get(key);
        if(node != null){
            node.value = value; //更新value值
            freqInc(node);
        }else{
            //若size达到最大值，则移除频次最小，最久未访问的元素
            if(size == capacity){
                //因链表是头插法，所以尾结点的前一个节点就是最久未访问的元素
                DoubleLinkedList list = freqMap.get(min);
                //需要移除的节点
                Node deadNode = list.tail.pre;
                cache.remove(deadNode.key);
                list.removeNode(deadNode);
                size--;
            }
            //新建一个node，并把node放到频次为 1 的 list 里面
            Node newNode = new Node(key,value);
            DoubleLinkedList newList = freqMap.get(1);
            if(newList == null){
                newList = new DoubleLinkedList();
                freqMap.put(1,newList);
            }
            newList.addNode(newNode);
            cache.put(key,newNode);
            size++;
            min = 1;//此时需要把min值重新设置为1
        }

    }

    //修改频次
    private void freqInc(Node node){
        //先删除node对应的频次list
        DoubleLinkedList list = freqMap.get(node.freq);
        if(list != null){
            list.removeNode(node);
        }
        //判断min是否等于当前node的频次，且当前频次的list为空，是的话更新min值
        if(min == node.freq && list.isEmpty()){
            min ++;
        }
        //然后把node频次加 1，并把它放到高频次list
        node.freq ++;
        DoubleLinkedList newList = freqMap.get(node.freq);
        if(newList == null){
            newList = new DoubleLinkedList();
            freqMap.put(node.freq, newList);
        }
        newList.addNode(node);
    }


    private class Node {
        int key;
        int value;
        int freq = 1;
        Node pre;
        Node next;

        public Node(){

        }

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    //自实现的一个双向链表
    private class DoubleLinkedList {
        Node head;
        Node tail;

        // 设置两个哨兵节点，作为头、尾节点便于插入和删除操作
        public DoubleLinkedList(){
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }

        //采用头插法，每次都插入到链表的最前面，即 head 节点后边
        public void addNode(Node node){
            node.pre = head;
            node.next = head.next;
            //注意先把head的后节点的前节点设置为node
            head.next.pre = node;
            head.next = node;
        }

        //删除元素
        public void removeNode(Node node){
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        //判断是否为空，即是否存在除了哨兵节点外的有效节点
        public boolean isEmpty(){
            //判断头结点的下一个节点是否是尾结点，是的话即为空
            return head.next == tail;
        }

    }

}
