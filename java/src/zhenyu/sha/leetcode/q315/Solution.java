package zhenyu.sha.leetcode.q315;
import java.util.*;
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if(nums==null||nums.length==0){
            return new LinkedList<Integer>();
        }

        Integer[] ret = new Integer[nums.length];

        for(int i=nums.length-1;i>=0;i--){
            int insertPoint = serchInsert(nums, i+1, nums.length-1, nums[i]);
            int size = insertPoint-(i+1)+1;
            ret[i]=size;
            int temp = nums[i];
            for(int j =i+1;j<=insertPoint;j++){
                nums[j-1]=nums[j];
            }
            nums[insertPoint]=temp;
        }
        return Arrays.asList(ret);
    }
    int serchInsert(int[]nums, int begin, int end, int target){
        // end is open
        if((begin>end)||(nums[begin]>=target)){
            return begin-1;
        }
        if (nums[end]<target){
            return end;
        }
        while (begin<end-1){
            int mid = begin+(end-begin)/2;
            if(nums[mid]<target){
                begin=mid;
            } else {
                end = mid;
            }
        }
        return begin;
    }
}
