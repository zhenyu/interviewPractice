package zhenyu.sha.leetcode.q209;

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(null==nums||nums.length==0)
            return 0;
        int begin =0;
        int end =0;
        int cum = nums[0];
        int ret =Integer.MAX_VALUE;
        while (end<nums.length){
            //try next
            if(cum>=s) {
                cum =0;
                if(end-begin+1<ret)
                    ret=end-begin+1;
            }
            if(end+1<nums.length) {
                end++;
                if(cum==0){
                    begin=end;
                }
                cum+=nums[end];
            }

        }
        if(cum>=s&&(end-begin+1<ret))
            ret = end-begin+1;
        return ret==Integer.MAX_VALUE?0:ret;
    }
    public static void main(String[]args){
        System.out.println(new Solution().minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }
}
