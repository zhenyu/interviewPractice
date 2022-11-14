package zhenyu.sha.twohorse.noOverlap;
import java.util.*;

/**
 * # 应该是新题 至少我在地里没看过
 * # 给很多线段 求多少种取法能取出两个段落彼此不overlap
 * # example:
 * # [[1,3],[1,2],[4,6],[5,7]] -> return 4
 */
public class Solution {
    static final int BEGIN = 0;
    static final int END = 1;
    public static void main(String[] args) {
        System.out.println(new Solution().getNoOverLapCount(new int[][]{{1,3},{1,2},{4,6},{5,7}}));
    }
    int getNoOverLapCount(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[BEGIN], o2[END]);
            }
        });
        int count =0;
        for (int i =0; i<intervals.length;i++) {
            if(intervals[intervals.length-1][BEGIN]<=intervals[i][END]) {
                count+=intervals.length-i;
            } else {
                int begin = i;
                int end = intervals.length-1;
                while (begin+1<end){
                    int mid = begin+(end-begin)/2;
                    if(intervals[mid][BEGIN]<=intervals[i][END]){
                        begin=mid;
                    } else {
                        end = mid;
                    }
                }
                count+=begin-i;
            }
        }
        return count;
    }
}
