package zhenyu.sha.leetcode.q362;

import java.util.LinkedList;

class HitCounter {
    LinkedList<Integer[]> counters;
    public HitCounter() {
        counters = new LinkedList<>();
    }

    public void hit(int timestamp) {
        if( !counters.isEmpty() && counters.getLast()[0]==timestamp) {
            counters.getLast()[1]++;
        } else {
            counters.addLast(new Integer[]{timestamp, 1});
        }
    }

    public int getHits(int timestamp) {
        int hit = 0;
        while (!counters.isEmpty()) {
            if(counters.getFirst()[1]<timestamp-301){
                counters.removeFirst();
            } else {
                break;
            }
        }
        for(Integer[] current: counters) {
            if(current[0]<=timestamp){
                hit += current[1];
            } else {
                break;
            }
        }
        return hit;
    }
}

public class Solution {
}
