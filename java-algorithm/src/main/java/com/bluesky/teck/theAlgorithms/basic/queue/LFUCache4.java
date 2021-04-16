package com.bluesky.teck.theAlgorithms.basic.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;


/**
 * 参考https://www.cnblogs.com/starry-skys/p/12651282.html
 * 方案一：使用优先队列
 * 我们可以使用JDK提供的优先队列 PriorityQueue 来实现 。 因为优先队列内部维护了一个二叉堆，即可以保证每次 poll 元素的时候，都可以根据我们的要求，取出当前所有元素的最大值或是最小值。只需要我们的实体类实现 Comparable 接口就可以了。
 * 因此，我们需要定义一个 Node 来保存当前元素的访问频次 freq，全局的自增的 index，用于比较大小。然后定义一个 Map<Integer,Node> cache ，用于存放元素的信息。
 * 当 cache 容量不足时，根据访问频次 freq 的大小来删除最小的 freq 。若相等，则删除 index 最小的，因为index是自增的，越大说明越是最近访问过的，越小说明越是很长时间没访问过的元素。
 * 因本质是用二叉堆实现，故时间复杂度为O(logn)。
 */
public class LFUCache4 {
    //缓存了所有元素的node
    Map<Integer,Node> cache;
    //优先队列
    Queue<Node> queue;
    //缓存cache 的容量
    int capacity;
    //当前缓存的元素个数
    int size;
    //全局自增
    int index = 0;

    //初始化
    public LFUCache4(int capacity){
        this.capacity = capacity;
        if(capacity > 0){
            queue = new PriorityQueue<>(capacity);
        }
        cache = new HashMap<>();
    }

    public int get(int key){
        Node node = cache.get(key);
        // node不存在，则返回 -1
        if(node == null) return -1;
        //每访问一次，频次和全局index都自增 1
        node.freq++;
        node.index = index++;
        // 每次都重新remove，再offer是为了让优先队列能够对当前Node重排序
        //不然的话，比较的 freq 和 index 就是不准确的
        queue.remove(node);
        queue.offer(node);
        return node.value;
    }

    public void put(int key, int value){
        //容量0，则直接返回
        if(capacity == 0) return;
        Node node = cache.get(key);
        //如果node存在，则更新它的value值
        if(node != null){
            node.value = value;
            node.freq++;
            node.index = index++;
            queue.remove(node);
            queue.offer(node);
        }else {
            //如果cache满了，则从优先队列中取出一个元素，这个元素一定是频次最小，最久未访问过的元素
            if(size == capacity){
                cache.remove(queue.poll().key);
                //取出元素后，size减 1
                size--;
            }
            //否则，说明可以添加元素，于是创建一个新的node，添加到优先队列中
            Node newNode = new Node(key, value, index++);
            queue.offer(newNode);
            cache.put(key,newNode);
            //同时，size加 1
            size++;
        }
    }


    //必须实现 Comparable 接口才可用于排序
    class Node implements Comparable<Node>{
        int key;
        int value;
        int freq = 1;
        int index;

        public Node(){

        }

        public Node(int key, int value, int index){
            this.key = key;
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            //优先比较频次 freq，频次相同再比较index
            int minus = this.freq - o.freq;
            return minus == 0? this.index - o.index : minus;
        }
    }


    public static void main(String[] args) {
        LFUCache4 cache = new LFUCache4(2);
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

