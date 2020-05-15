package zhenyu.sha.leetcode.q904;
import java.util.*;
class Solution {
    public int totalFruit(int[] tree) {
        int ret =0;
        if(null!=tree){
            HashMap<Integer, Integer> seenTypes = new HashMap<>(2);
            int start =0;
            int end =0;//exclusive
            while (end<tree.length){
                if(seenTypes.size()==2&&!seenTypes.containsKey(tree[end])) {
                    //update the length
                    ret=Math.max(ret, end-start);
                    //move the start, until just one kind of key
                    while (seenTypes.size()==2) {
                        int count = seenTypes.get(tree[start]);
                        --count;
                        if(0==count){
                            seenTypes.remove(tree[start]);
                        } else {
                            seenTypes.put(tree[start], count);
                        }
                        start=start+1;
                    }

                }
                int count = seenTypes.getOrDefault(tree[end], 0);
                seenTypes.put(tree[end], ++count);
                end++;
            }
            //do forget to update the last slot
            ret = Math.max(ret, end-start);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().totalFruit(new int[]{0,1,1,4,3}));
    }
}