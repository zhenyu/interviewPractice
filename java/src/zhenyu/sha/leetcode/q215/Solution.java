package zhenyu.sha.leetcode.q215;

import java.util.Random;
//TODO
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int p =0;
        int start = 0;
        int end = nums.length-1;
        while (p!=k){
            int cum =partion(nums, start, end);
            if(p+cum==k){
                p=p+cum;
                break;
            } else if(p+cum>k){
                end = start+cum;
            } else {

            }
        }
        return nums[p-1];
    }
    int partion(int[] nums, int start, int end){
        if(start>=end-1) {
            if(nums[end]<nums[start]){
                int temp = nums[end];
                nums[end]=nums[start];
                nums[start] = temp;
            }
            return end-start+1;
        }

        int index = start+new Random().nextInt(end-start);
        return index;
    }
    int swap(int[] nums, int start) {
        return 0;
    }
}
