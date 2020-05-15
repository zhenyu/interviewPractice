package zhenyu.sha.leetcode.q146;
import java.util.*;
public class LRUCache {
    static class Node {
        int val;
        int key;
        Node prev;
        Node next;
    }
    Map<Integer, Node> cache;
    int cap;
    //with header node;
    // tail is the most recently
    Node keyList;
    LRUCache(int cap) {
        this.cap = cap;
        cache = new HashMap<>(cap);
        keyList = new Node();
        keyList.prev = keyList;
        keyList.next = keyList;
    }
    void put(int key, int val){
        Node valueNode = cache.get(key);
        if(null==valueNode){
            valueNode = new Node();
            if(cache.size()==cap) {
                int removeKey = keyList.next.key;
                if(null!=keyList.next.next) {
                    keyList.next.next.prev=keyList;
                }
                keyList.next=keyList.next.next;
                cache.remove(removeKey);
            }
        }
        valueNode.val = val;
        valueNode.key = key;
        cache.put(key, valueNode);
        //move the node to tail
        moveToTail(valueNode);
    }
    // return null, if not exist
    int get(int key) {
        Node valNode = cache.get(key);
        if(null==valNode){
            return -1;
        }
        moveToTail(valNode);
        return valNode.val;
    }
    void moveToTail(Node valNode){
        if(valNode.next==null&&valNode.prev!=null) {
            return;
        }
        //move the Node to tail
        Node tail = keyList.prev;
        //maybe check null , let us review in set
        if(null!=valNode.prev) {
            valNode.prev.next = valNode.next;
        }
        if(null!=valNode.next) {
            valNode.next.prev = valNode.prev;
        }
        tail.next=valNode;
        valNode.prev=tail;
        valNode.next = null;
        keyList.prev=valNode;
    }

    public static void main(String[] args) {
        LRUCache cache =new LRUCache(1);
        cache.put(2,1);
        cache.get(2);
        cache.put(3,2);
        cache.get(2);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */