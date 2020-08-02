package zhenyu.sha.pingguo;
import java.util.*;
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int[] times= new int[n];
        Arrays.fill(times, -1);
        times[headID] =0;
        for(int i=0; i<n;i++){
           findTime(i, manager, informTime, times);
        }
        int max =0;
        for(int time: times){
            max=Math.max(time, max);
        }
        return max;
    }
    void findTime(int e, int[]manager, int[] informTime, int[] times){
        if(times[e]<0) {
            findTime(manager[e], manager, informTime, times);
            times[e]=times[manager[e]]+informTime[manager[e]];
        }
    }
}