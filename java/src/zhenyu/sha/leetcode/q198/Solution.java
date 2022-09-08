package zhenyu.sha.leetcode.q198;

class Solution {
    public int rob(int[] nums) {
        int rob =nums[0];
        int not_rob =0;
        for (int i =1; i< nums.length;i ++) {
            int new_rob = not_rob+nums[i];
            not_rob = Math.max(rob, not_rob);
            rob = new_rob;
        }
        return Math.max(rob, not_rob);
    }
}