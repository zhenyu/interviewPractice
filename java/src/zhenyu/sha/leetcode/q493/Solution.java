package zhenyu.sha.leetcode.q493;

class Solution {
    int reverseCount;
    int [] buffer;
    public int reversePairs(int[] nums) {
        if(null==nums||nums.length==0){
            return 0;
        }
        reverseCount =0;
        buffer = new int[nums.length];
        mergeAndCount(nums, 0, nums.length-1);
        return reverseCount;
    }
    private void mergeAndCount(int[] nums, int begin, int end) {
        if(begin==end){
            return;
        }
        int mid = begin+(end-begin)/2;
        mergeAndCount(nums, begin, mid);
        mergeAndCount(nums,mid+1, end);
        for(int j= mid+1;j<=end;j++) {
            int tempReverse = getReverseCount(nums, begin, mid, (long)(nums[j])*2);
            if(tempReverse==0) {
                break;
            }
            reverseCount+=tempReverse;
        }
        int count =0;
        int i= begin;
        int j= mid+1;
        while (i<=mid&&j<=end) {
            if(nums[i]<=nums[j]) {
                buffer[begin+count]= nums[i];
                i++;
            } else {
                buffer[begin+count]=nums[j];
                j++;
            }
            count++;
        }
        while (i<=mid) {
            buffer[begin+count]= nums[i];
            i++;
            count++;
        }
        while (j<=end){
            buffer[begin+count]=nums[j];
            count++;
            j++;
        }
        for(i=0;i<count;i++){
            nums[begin+i]=buffer[begin+i];
        }
    }
    private int getReverseCount(int[] nums, int begin, int end, long target) {
         if(nums[end]<=target){
             return 0;
         }
         if(nums[begin]>target){
             return end-begin+1;
         }
         int oldEnd = end;
         while (begin<end-1){
             int mid = begin+(end-begin)/2;
             if(nums[mid]>target){
                 end=mid;
             } else{
                 begin =mid;
             }
         }
         return oldEnd-end+1;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().reversePairs(new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647}));
    }

}