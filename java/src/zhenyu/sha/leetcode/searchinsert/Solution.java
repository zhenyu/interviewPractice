package zhenyu.sha.leetcode.searchinsert;

class Solution {
    public int searchInsert(int[] nums, int target) {
        int begin =0;
        int end = nums.length-1;
        while(begin<=end){
            //System.out.println("begin="+begin+" end="+end);
            int mid = begin+(end-begin)/2;
            int curVal=nums[mid];
            if(curVal==target) {
                return mid;
            } else if(curVal<target) {
                begin = mid+1;
            } else {
                end = mid-1;
            }
        }
        return begin;

    }
}