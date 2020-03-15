package zhenyu.sha.leetcode.SubArraySumK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        int occur=0;
        int [] sums=new int[nums.length];
        int sum=0;
        //sums2index
        Map<Integer, TreeSet<Integer>> sums2Index= new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            sum = sum+nums[i];
            if(sum==k)
                occur++;
            sums[i]=sum;
            //put 2 map;
            TreeSet<Integer> sumSet = sums2Index.get(sum);
            if(null==sumSet) {
                sumSet = new TreeSet<>();
                sums2Index.put(sum, sumSet);
            }
            sumSet.add(i);
        }
        //substract
        for(int i=1;i<sums.length;i++){
            int target = sums[i]-k;
            TreeSet<Integer> sumSet = sums2Index.get(sum);
            if(null!=sumSet) {
                Set<Integer> leftIndexs = sumSet.headSet(i);
                occur+=leftIndexs.size();
            }
        }

        return occur;
    }
    public static void main(String[] args) throws IOException {
        int[] inputs = new int[]{1,1,1};
        new Solution().subarraySum(inputs,2);
    }
}
