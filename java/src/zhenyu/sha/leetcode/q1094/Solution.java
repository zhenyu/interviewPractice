package zhenyu.sha.leetcode.q1094;
import java.util.*;
class Solution {
    class Event implements Comparable<Event>{
        int pos;
        int num;
        // 0 is exit, 1 is enter
        int type;

        @Override
        public int compareTo(Event o) {
            int delta = this.pos- o.pos;
            if (delta ==0) {
                return this.type- o.type;
            }
            return delta;
        }
    }

    public boolean carPooling(int[][] trips, int capacity) {
        PriorityQueue<Event> events = new PriorityQueue<>();
        int current =0;
        for(int[] trip: trips){
            Event enter = new Event();
            enter.num = trip[0];
            enter.type = 1;
            enter.pos = trip[1];
            events.add(enter);
            Event exit = new Event();
            exit.num = trip[0];
            exit.type = 0;
            exit.pos=trip[2];
            events.add(exit);
        }
        while (events.size()>0){
            Event e = events.poll();
            if(e.type==1) {
                current+=e.num;
                if (current>capacity) {
                    return false;
                }
            } else {
                current-=e.num;
            }
        }
        return capacity>current;
    }
}