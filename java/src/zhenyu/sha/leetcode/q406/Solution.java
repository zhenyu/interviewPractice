package zhenyu.sha.leetcode.q406;
import java.util.*;

class Solution {

    public int[][] reconstructQueue(int[][] peoples) {
        Arrays.sort(peoples, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int ret = o1[0]-o2[0];
                if(0==ret){
                    ret = o2[1]-o1[1];
                }
                return ret;
            }
        });

        int [][] ret = new int[peoples.length][];
        for(int [] people: peoples ){
            int largerCount =0;
            for(int i=0;i<ret.length;i++) {
                if(largerCount==people[2]&&ret[i]==null){
                    ret[i]=people;
                    break;
                }
                if(null==ret[i]){
                    largerCount++;
                }
            }
        }
        return ret;
    }
}