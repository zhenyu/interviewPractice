package zhenyu.sha.leetcode.q683;
import java.util.*;
class Solution {
    public int kEmptySlots(int[] bulbs, int k) {
        int ret = -1;
        TreeSet<Integer> onPoses = new TreeSet<>();
        for(int i = 0; i< bulbs.length;i++) {
            int curPos = bulbs[i];
            onPoses.add(curPos);
            Integer prev= onPoses.lower(curPos);
            if(prev!=null&&(curPos-prev-1)==k){
                ret = i+1;
                break;
            }
            Integer next = onPoses.higher(curPos);
            if(next!=null&&(next-curPos-1)==k){
                ret =i+1;
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().kEmptySlots(new int[]{1,3,2}, 1));
    }
}
