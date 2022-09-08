package zhenyu.sha.leetcode.q213;
class Solution {
    public int rob(int[] nums) {
        int ret =0;
        if (nums.length <=3) {
            for( int num: nums){
                ret = Math.max(num, ret);
            }
            return ret;
        }

        // case 1 rob the first
        int not_rob = nums[0];
        int rob = not_rob+nums[2];
        for(int i = 3; i< nums.length-1; i++) {
            int new_rob = not_rob+nums[i];
            not_rob = Math.max(not_rob, rob);
            rob = new_rob;
        }
        ret = Math.max(rob, not_rob);
        // not rob the first
        not_rob=0;
        rob=nums[1];
        for(int i = 2; i< nums.length; i++) {
            int new_rob = not_rob+nums[i];
            not_rob = Math.max(not_rob, rob);
            rob = new_rob;
        }

        ret = Math.max(ret, Math.max(rob,not_rob));
        return ret;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().rob(new int[]{1,2,1,1}));
    }
}
