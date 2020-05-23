package zhenyu.sha.leetcode.q56;
import java.util.*;
class Event implements Comparable<Event> {
    //0 enter, 1, exit
    int type;
    int x;
    Event(int type, int x) {
        this.type = type;
        this.x = x;
    }
    @Override
    public int compareTo(Event o) {
        int ret = this.x-o.x;
        if(ret!=0)
            return ret;
        return this.type-o.type;
    }
}
class Solution {

    public int[][] merge(int[][] intervals) {
        if(null==intervals||intervals.length==0)
            return new int[][]{};
        List<List<Integer>> merged = new LinkedList<>();
        List<Event> events = new ArrayList<>(intervals.length*2);
        for(int i= 0; i< intervals.length;i++){
            events.add(new Event(0, intervals[i][0]));
            events.add(new Event(1, intervals[i][1]));
        }
        Collections.sort(events);
        int count=0;
        int start =0;
        for(Event e: events) {
            if(e.type==0){
                if(count==0){
                    start=e.x;
                }
                count++;
            } else {
                count--;
                if(count==0&&start!=e.x){
                    List<Integer> merge = new ArrayList<>(2);
                    merge.add(start);
                    merge.add(e.x);
                }
            }

        }
        int[][]ret = new int[][]{};
        if(merged.size()>0) {
            ret = new int[merged.size()][2];
            int i=0;
            for(List<Integer> interval : merged) {
                ret[i]=new int[]{interval.get(0), interval.get(1)};
            }
        }
        return ret;
    }
}
