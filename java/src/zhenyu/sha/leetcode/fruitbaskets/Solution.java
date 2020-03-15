package zhenyu.sha.leetcode.fruitbaskets;
import java.util.*;
class Solution {
    Map<Integer, Integer> typeCountMap;
    public int totalFruit(int[] tree) {
        int ret =0;
        if(null!=tree && tree.length>0) {
            typeCountMap = new HashMap<>();
            int begin = 0;
            int end=1;
            incTypeCount(tree[0]);
            while (end<tree.length){
                //try to move next
                if(typeCountMap.size()<2||typeCountMap.containsKey(tree[end])){
                   incTypeCount(tree[end]);
                   end++;
                } else {
                   //update
                   if(end-begin>ret){
                       ret =end-begin;
                   }
                   while(begin+1<end&&typeCountMap.size()>=2) {
                       decTypeCount(tree[begin]);
                       begin++;
                   }
                }
            }
            // the last window
            if(end-begin>ret){
                ret =end-begin;
            }

        }
        return ret;
    }
    private void incTypeCount(int type){
        Integer count = typeCountMap.get(type);
        if(null==count) {
            count=0;
        }
        typeCountMap.put(type, count+1);
    }
    private void decTypeCount(int type) {
        Integer count = typeCountMap.get(type);
        if(null!=count){
            count--;
            if (0==count){
                typeCountMap.remove(type);
            } else {
                typeCountMap.put(type, count);
            }
        }
    }
    public static void main(String[] args) {
        new Solution().totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4});
    }
}