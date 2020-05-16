package zhenyu.sha.leetcode.q163;
import java.util.*;
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ret = new LinkedList<>();
        long start=lower;
        for(int i =0;i<nums.length;i++){
            int end = nums[i];
            if(end>start){
                //append,
                if(end-1==start){
                    ret.add(Long.toString(start));
                } else{
                    ret.add(Long.toString(start)+"->"+Integer.toString(end-1));
                }
            }

            start=end+1;


        }
        if((long)(upper)>=start){
            //append,
            if(upper==start){
                ret.add(Long.toString(start));
            } else{
                ret.add(Long.toString(start)+"->"+Integer.toString(upper));
            }
        }
        return ret;
    }
}