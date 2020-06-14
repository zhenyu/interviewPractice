package zhenyu.sha.leetcode.q391;

import java.util.*;

class Interval implements Comparable<Interval>{
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
    int start;
    int end;
    @Override
    public int compareTo(Interval o){
        return Integer.compare(this.start, o.start);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interval)) return false;
        Interval interval = (Interval) o;
        return start == interval.start &&
                end == interval.end;
    }

}

class Solution {

    public boolean isRectangleCover(int[][] rectangles) {
        TreeMap<Integer, ArrayList<Interval>> inIntervals = new TreeMap<>();
        TreeMap<Integer, ArrayList<Interval>> outIntervals = new TreeMap<>();
        for(int[] rec : rectangles){
            //in rect[1]
            ArrayList<Interval> intervals = inIntervals.getOrDefault(rec[1], new ArrayList<>());
            intervals.add(new Interval(rec[0],rec[2]));
            inIntervals.put(rec[1], intervals);
            intervals = outIntervals.getOrDefault(rec[3], new ArrayList<>());
            intervals.add(new Interval(rec[0],rec[2]));
            outIntervals.put(rec[3], intervals);
        }
        if(inIntervals.size()==0)
            return false;
        ArrayList<Interval> first = merge(inIntervals.firstEntry().getValue());
        ArrayList<Interval> last = merge(outIntervals.lastEntry().getValue());
        if(first.size()!=1||!first.equals(last))
            return false;
        inIntervals.remove(inIntervals.firstKey());
        outIntervals.remove(outIntervals.lastKey());
        for(Map.Entry<Integer, ArrayList<Interval>> entry : inIntervals.entrySet()){
            ArrayList merged = merge(entry.getValue());
            if(merged.isEmpty())
                return false;
            inIntervals.put(entry.getKey(), merged);
        }
        for(Map.Entry<Integer, ArrayList<Interval>> entry : outIntervals.entrySet()){
            ArrayList merged = merge(entry.getValue());
            if(merged.isEmpty())
                return false;
            outIntervals.put(entry.getKey(), merged);
        }
        return inIntervals.equals(outIntervals);
    }
    ArrayList<Interval> merge(ArrayList<Interval> intervals){
        if(intervals.isEmpty())
            return intervals;
        ArrayList<Interval> ret = new ArrayList<>();
        Collections.sort(intervals);
        Interval pre = intervals.get(0);
        for(int i =1; i< intervals.size();i++) {
            Interval cur = intervals.get(i);
            if(cur.start<pre.end)
                return new ArrayList<>();
            if(cur.start==pre.end)
                pre.end=cur.end;
            else {
                ret.add(pre);
                pre =cur;
            }
        }
        ret.add(pre);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isRectangleCover(new int[][]{{1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4}}));
    }
}