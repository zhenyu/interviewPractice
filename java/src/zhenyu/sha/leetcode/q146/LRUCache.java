package zhenyu.sha.leetcode.q146;
import java.util.*;
class LRUCache {
    class Node {
        Node next;
        Node prev;
        int val;
        int key;
    }
    int cap;
    // with head node, the prev of head is the tail
    Node head;
    Map<Integer, Node> kv ;
    public LRUCache(int capacity) {
        cap = capacity;
        head = new Node();
        head.prev=head;
        kv = new HashMap<>();
    }

    public int get(int key) {
        int ret =-1;
        Node v = kv.get(key);
        if (null!=v) {
            ret=v.val;
            moveToTail(v);
        }
        return ret;
    }

    public void put(int key, int value) {
        Node v = kv.get(key);
        if(null!=v){
            v.val=value;
            moveToTail(v);

        } else {
            if(kv.size()==cap){
                //remove head;
                int remove_key = head.next.key;
                head.next=head.next.next;
                if(null!= head.next){
                    head.next.prev=head;
                }
                if(head.prev!=head&&head.prev.key==remove_key){
                    head.prev=head;
                }
                kv.remove(remove_key);
            }
            //construct New node and put to tail
            v = new Node();
            v.val=value;
            v.key =key;
            head.prev.next = v;
            v.prev = head.prev;
            head.prev = v;
            kv.put(key,v);
        }
    }
    private void moveToTail(Node v) {
        if (head.prev==v)
            return;
        // taken from pre and next
        v.prev.next=v.next;
        v.next.prev=v.prev;
        head.prev.next=v;
        v.prev=head.prev;
        v.next = null;
        head.prev= v;
    }
    public static void main(String[] args){
        //["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
        //[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
        LRUCache cache = new LRUCache(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        cache.get(4);
        cache.get(3);
        cache.get(2);
        cache.get(1);
        cache.put(5,5);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(4);
        cache.get(5);
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

