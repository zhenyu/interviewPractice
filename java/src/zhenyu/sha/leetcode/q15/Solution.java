package zhenyu.sha.leetcode.q15;
import java.util.*;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        LinkedList<List<Integer>> ret = new LinkedList<>();
        Arrays.sort(nums);
        int start =0;
        int end = nums.length-1;
        while(start+2<=end){
            int next = -(nums[end]+nums[start]);
            int pos =Arrays.binarySearch(nums, start+1, end, next);
            if(pos>0){
                ArrayList<Integer> tup = new ArrayList<>();
                tup.add(nums[start]);
                tup.add(next);
                tup.add(nums[end]);
                boolean same = false;
                if(ret.size()>0) {
                    same =true;
                    ArrayList<Integer> pre=(ArrayList<Integer>)ret.peekLast();
                    for (int i = 0; i < 3; i++) {
                        if(pre.get(i)!=tup.get(i)){
                            same =false;
                            break;
                        }
                    }
                }
                if(!same){
                    ret.add(tup);
                }

            }
            if(nums[start]+nums[start+1]+nums[end]>0){
                    end--;
            } else {
                    start++;
            }

        }
        return ret;
    }

    public static void main(String[] args) {
        //[-1,0,1,2,-1,-4]
        System.out.println(new Solution().threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}