package zhenyu.sha.leetcode.q47;
import java.util.*;
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new LinkedList<>();
        if(null!=nums&&nums.length>0){
            Arrays.sort(nums);
            boolean [] used = new boolean[nums.length];
            Arrays.fill(used, false);
            LinkedList<Integer> path = new LinkedList<>();
            dfs(nums, 0, used, path, ret);
        }
        return ret;
    }
    void dfs(int[] nums, int dep, boolean[]used, LinkedList<Integer> path, List<List<Integer>> ret){
        if(dep== nums.length){
            ret.add(new LinkedList<>(path));
            return;
        }
        for(int i=0;i< nums.length;i++) {
            if (used[i]){
                continue;
            }
            if(i>0&&nums[i-1]==nums[i]&&!used[i-1]){
                continue;
            }
            path.addLast(nums[i]);
            used[i]=true;
            dfs(nums, dep+1, used, path, ret);
            path.removeLast();
            used[i]=false;

        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permuteUnique(new int[]{1,1,2}));
    }
}