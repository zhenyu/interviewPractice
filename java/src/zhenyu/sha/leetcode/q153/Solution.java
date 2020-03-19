package zhenyu.sha.leetcode.q153;

class Solution {
    public int findMin(int[] nums) {
        if(null==nums||0==nums.length)
            return -1;
        int begin =0;
        int end = nums.length-1;
        while(begin+1<end) {
            int mid = begin+(end-begin)/2;
            if (nums[mid]>nums[end]) {
                begin=mid;
            } else {
                end =mid;
            }
        }
        return nums[begin]<nums[end]?nums[begin]:nums[end];
    }
    public static void main(String[] args) {
        System.out.println(new Solution().findMin(new int[]{3,4,5,1,2}));
    }
}