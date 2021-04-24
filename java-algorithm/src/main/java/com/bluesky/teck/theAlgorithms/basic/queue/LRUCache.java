package com.bluesky.teck.theAlgorithms.basic.queue;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	private Map<Integer,Node> map;
	private int cap;
	private int size;
	private Node head;
	private Node tail;
	LRUCache(int cap){
		this.cap = cap;
		this.size = 0;
		head = new Node(-1,-1);
		tail = new Node(-2,-2);
		head.next = tail;
		tail.prev = head;
		map = new HashMap<>();
	}

	public Node put(int key,int val) {
	    Node node;
		if(map.containsKey(key)) {
			node = map.get(key);
			node.val = val;
			moveToFront(node);
		}else {
			node = new Node(key,val);
			if(size == cap) {
				removeLast();
			}
			size ++;
			map.put(key, node);
			addHead(node);
		}
		return node;
	}
	
	public Node get(int key) {
		if(map.containsKey(key)) {
			Node node = map.get(key);
			moveToFront(node);
			return node;
		}
		return null;
	}
	

	public Node removeLast() {
		Node node = tail.prev;
		map.remove(node.key);
		node.prev.next = tail;
		tail.prev = node.prev;
		size --;
//		node.prev = null;
//		node.next = null;
		return node;
	}
	
	public Node remove(int key) {
		Node node = map.remove(key);
		if(node == null)
			return null;
		size --;
		node.prev.next = node.next;
		node.next.prev = node.prev;
//		node.prev = null;
//		node.next = null;
		return node;
	}
	
	public void moveToFront(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}
	

	public void addHead(Node node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}

	class Node{
		int key;
		int val;
		Node prev;
		Node next;
		Node(int key, int val){
			this.key = key;
			this.val = val;
		}
	}
	
	
    @Override
    public String toString() {
    	return map.keySet().toString();
    }
    
    public static void main(String[] args) {
    	LRUCache cache = new LRUCache(3);
        cache.put(1, 11);
        cache.put(2, 22);
        cache.put(3, 33);
        cache.get(1);
        cache.put(4, 44);
        cache.put(2,23);
		System.out.println(cache.get(2));
		System.out.println(cache.remove(3));
		cache.put(5, 55);
		cache.get(4);
        System.out.println(cache);
	}

}
