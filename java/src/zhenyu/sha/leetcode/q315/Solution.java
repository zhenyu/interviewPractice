package zhenyu.sha.leetcode.q315;
import java.util.*;
class IndexNode implements Comparable<IndexNode>{
    IndexNode(int index, int val){
        this.index=index;
        this.val=val;
    }
    public int compareTo(IndexNode o){
        return Integer.compare(o.val, this.val);
    }
    int index;
    int val;
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
        TreeSet<Integer> indexSet = new TreeSet<>();
        for(int i= indexNodes.length-1;i>=0;i--){
            int currentIndex = indexNodes[i].index;
            ret[currentIndex]= indexSet.tailSet(currentIndex).size();
            indexSet.add(currentIndex);
        }

        return Arrays.asList(ret);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countSmaller(new int[]{5,2,6,1}));
    }

}
