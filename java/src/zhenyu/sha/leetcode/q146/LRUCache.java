package zhenyu.sha.leetcode.q146;
import java.util.*;
class Node {
    Node prev;
    Node next;
    int key;
    int val;
}
class LRUCache {
    int cap;
    Map<Integer, Node> map = new HashMap<>();
    Node header ;
    public LRUCache(int capacity) {
        cap = capacity;
        header = new Node();
        header.next = header;
        header.prev = header;
    }
    private void moveToTail( Node node ){
        if (header.prev != node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = header.prev;
            header.prev.next = node;
            header.prev = node;
            node.next = header;

        }
    }
    public int get(int key) {
        int ret = -1;
        Node node = map.get(key);
        if (null != node ) {
            ret = node.val;
            moveToTail(node);
        }
        return ret;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node!=null) {
            node.val =value;
            moveToTail(node);
        } else {
            if (this.cap > map.size()) {
                node = new Node();
                node.val = value;
                node.key = key;
                node.prev = header.prev;
                header.prev.next = node;
                node.next = header;
                header.prev = node;
            } else {
                node = header.next;
                map.remove(node.key);
                node.val = value;
                node.key = key;
                moveToTail(node);
            }
            map.put(key, node);
        }
    }

    public static void main(String[] args){
        //["LRUCache","put","put","get","put","get","put","get","get","get"]
        //[[2],[1,0],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        LRUCache cache = new LRUCache(2);
        cache.put(1,0);
        cache.put(2,2);;
        cache.get(1);
        cache.put(3,3);
        cache.get(2);
        cache.put(4,4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

