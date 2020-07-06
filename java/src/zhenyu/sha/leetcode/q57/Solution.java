package zhenyu.sha.leetcode.q57;
import java.util.*;
class Interval implements Comparable<Interval>{
    int s;
    int e;
    Interval (int s, int e){
        this.s =s;
        this.e =e;
    }
    public int compareTo(Interval o) {
        return Integer.compare(this.s, o.s);
    }
}
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<Interval> intervalList = new ArrayList<>();
        for(int[]interval:intervals){
           intervalList.add(new Interval(interval[0], interval[1]));
        }
        Collections.sort(intervalList);
        ArrayList<int[]> ret = new ArrayList<>();
        for(Interval interval: intervalList) {
            //try from begin
            if(interval.e<newInterval[0]) {
                ret.add(new int[]{interval.s, interval.e});
            } else{
                if(interval.s>newInterval[1]) {
                    ret.add(newInterval);
                    newInterval = new int[]{interval.s, interval.e};
                } else {
                    newInterval = new int[]{Math.min(interval.s,newInterval[0]),Math.max(interval.e, newInterval[1])};
                }
            }
        }


        ret.add(newInterval);
        // the last
        int[][] result = new int[ret.size()][];
        return ret.toArray(result);
    }

    public static void main(String[] args) {
        int [][] ret = new Solution().insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8});
        System.out.println(ret);
    }
}