package com.bluesky.teck.theAlgorithms.basic.queue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCacheDemo<K,V> {
   private int cacheSize;
   private DoubleLinkedList<K,V> doubleLinkedList = new DoubleLinkedList<>();
   private Map<K,Node<K,V>> map = new ConcurrentHashMap<>();
   public LRUCacheDemo(int cacheSize){
       this.cacheSize = cacheSize;
   }

   public void put(K key,V value){
        if(map.containsKey(key)){
            Node<K, V> node = map.get(key);
            node.value = value;
            map.put(key,node);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addNode(node);
        }else {
            if(map.size() == cacheSize){
                Node lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            Node<K,V> newNode = new Node<>(key,value);
            map.put(key,newNode);
            doubleLinkedList.addNode(newNode);
        }
   }

    public V get(K key){
        if(map.containsKey(key)){
            Node<K, V> node = map.get(key);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addNode(node);
            return node.value;
        }
        return null;
    }


    class Node<K,V> {
        K key;
        V value;
        Node<K, V> pre;
        Node<K, V> next;

        public Node(){
            this.pre = this.next = null;
        }

        public  Node(K key,V value){
            this.key = key;
            this.value = value;
            this.pre = this.next = null;
        }
    }

    class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;

        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.pre = head;
        }

        public void addNode(Node<K,V> node){
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }

        public void removeNode(Node<K,V> node){
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = null;
            node.next = null;
        }

        public Node getLast(){
            return tail.pre;
        }
    }

    public Map<K, Node<K, V>> getMap() {
        return map;
    }

    public static void main(String[] args) {
        LRUCacheDemo<Integer,String> lruDemo = new LRUCacheDemo<>(3);
        lruDemo.put(1,"a");
        lruDemo.put(2,"b");
        lruDemo.put(3,"c");
        System.out.println("====1===="+lruDemo.getMap().keySet());
        lruDemo.put(4,"d");
        System.out.println("====2===="+lruDemo.getMap().keySet());
        lruDemo.put(5,"e");
        System.out.println("====3===="+lruDemo.getMap().keySet());
    }
}
