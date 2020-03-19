package zhenyu.sha.leetcode.q33;

class Solution {
    public int search(int[] nums, int target) {
        int begin =0;
        int end = nums.length-1;
        while(begin<=end) {
            int mid = begin + (end-begin)/2;
            if(nums[mid]==target){
                return mid;
            } else if(nums[mid]>=nums[begin]) {
                if(target<nums[mid]&&target>=nums[begin]){
                    end=mid-1;
                } else {
                    begin = mid+1;
                }
            } else {
                if(target>nums[mid]&&target<=nums[end]) {
                    begin = mid+1;
                } else {
                    end = mid-1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{3,1},3));
    }
}
