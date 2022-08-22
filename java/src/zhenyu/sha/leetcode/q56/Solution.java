package zhenyu.sha.leetcode.q56;
import java.util.*;

class Solution {

    public int[][] merge(int[][] intervals) {
        if(null==intervals||intervals.length==0)
            return new int[][]{};
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        List<int[]> result  = new LinkedList<>();
        int [] pre = intervals[0];
        for(int i = 1; i< intervals.length; i++) {
            int [] current  =  intervals[i];
            if (current[0]<=pre[1]) {
                 pre[1] = Math.max(pre[1],current[1]);
            } else {
                result.add(pre);
                pre = current;
            }
        }

        result.add(pre);
        return result.toArray(new int[][]{});
    }
}
