package zhenyu.sha.leetcode.q209;

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(null==nums||nums.length==0)
            return 0;
        int begin =-1;
        int end =0;
        int ret =Integer.MAX_VALUE;
        int cum =0;
        while (end<nums.length){
            //try next
            cum=cum+nums[end];
            end++;
            if(cum>=s){
                if(end-(begin+1)<ret)
                    ret =end-begin-1;
                //try reduce, can be empty
                while(begin+1<end) {
                    begin ++;
                    cum=cum-nums[begin];
                    if(cum<s){
                        break;
                    } else if(end-begin-1<ret){
                        ret =end-begin-1;
                    }
                }
            }
        }
        return ret==Integer.MAX_VALUE?0:ret;
    }
}
