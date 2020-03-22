package zhenyu.sha.leetcode.q410;

class Solution {
    public int splitArray(int[] nums, int m) {
        long end =0;
        for(int i: nums){
            end+=i;
        }
        long begin =0;
        while(begin<end){
            long mid=begin+(end-begin)/2;
            if(canSplit(nums, m, mid)){
                end=mid;
            } else {
                begin = mid+1;
            }
        }
        return (int)end;
    }
    private boolean canSplit(int[] nums, int m, long max){
        int cum =0;
        int count = 0;
        for(int i=0; i<nums.length;i++) {
            if (nums[i]>max)
                    return false;
            if (cum+nums[i]<max){
                cum+=nums[i];
            } else {
                if(cum+nums[i]==max)
                    cum=0;
                else
                    cum=nums[i];
                count++;
            }
        }
        if(cum>0) count++;
        return count<=m;
    }
    public static void main(String[]args) {
        System.out.println(new Solution().splitArray(new int []{7,2,5,10,8}, 2));
    }
}
