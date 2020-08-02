package zhenyu.sha.leetcode.q31;
class Solution {
    public void nextPermutation(int[] nums) {
        if(nums.length<=1)
            return;
        // find first decrese instance
        int i = nums.length-2;
        while (i>=0&&nums[i]>= nums[i+1]){
            i--;
        }
        if(i>=0){
            // swap
            for(int j = nums.length-1; j>i ;j--){
                if(nums[i]<nums[j]){
                    swap(nums, i, j);
                    break;
                }
            }
        }
        // reverse
        reverse(nums, i+1, nums.length-1);

    }
    private void reverse(int []nums, int begin, int end){
        while (begin<end){
            swap(nums, begin, end);
            begin++;
            end--;
        }
    }
    private void swap(int[] nums,int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] input = new int[]{0,1,0,1,1};
        new Solution().nextPermutation(input);
        System.out.println(input);
    }
}