package zhenyu.sha.leetcode.q315;
import java.util.*;
class IndexNode implements Comparable<IndexNode>{
    IndexNode(int index, int val){
        this.index=index;
        this.val=val;
    }
    public int compareTo(IndexNode o){
        int ret= Integer.compare(o.val, this.val);
        if(ret==0){
            ret = Integer.compare(o.index, this.index);
        }
        return ret;
    }
    int index;
    int val;
}
class BIT {
    int [] vals;
    BIT(int size) {
        vals = new int[size+1];
    }
    void inc(int index) {
        index++;
        while (index<=vals.length-1){
            vals[index]+=1;
            index+=index&(-index);
        }
    }
    int getSum(int index) {
        int cum = 0;
        index++;
        while (index>0){
            cum+=vals[index];
            index-=index&(-index);
        }
        return cum;
    }
}
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if(nums==null||nums.length==0){
            return new LinkedList<Integer>();
        }
        IndexNode [] indexNodes = new IndexNode[nums.length];
        for(int i=0;i<nums.length;i++) {
            indexNodes[i]= new IndexNode(i, nums[i]);
        }
        Arrays.sort(indexNodes);
        Integer[] ret = new Integer[nums.length];
        BIT bit = new BIT(nums.length);
        for(int i= indexNodes.length-1;i>=0;i--){
            int currentIndex = indexNodes[i].index;
            ret[currentIndex]= bit.getSum(nums.length-1)-bit.getSum(currentIndex);
            bit.inc(currentIndex);
        }
        return Arrays.asList(ret);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countSmaller(new int[]{-1}));
    }

}
