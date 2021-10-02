package zhenyu.sha.leetcode.q981;
import java.util.*;
class TimeMap {
    class Node {
        String k;
        String v;
        public Node(String k, String v){
            this.k = k;
            this.v = v;
        }
    }
    TreeMap<Integer, Node> kvs;
    /** Initialize your data structure here. */
    public TimeMap() {
        kvs = new TreeMap<>();
    }

    public void set(String key, String value, int timestamp) {
        kvs.put(timestamp, new Node(key, value));
    }

    public String get(String key, int timestamp) {
        for(Map.Entry<Integer,Node> entry: kvs.headMap(timestamp, true).descendingMap().entrySet()) {
            if (entry.getValue().k.equals(key)) {
                return entry.getValue().v;
            }
        }
        return "";
    }
}

