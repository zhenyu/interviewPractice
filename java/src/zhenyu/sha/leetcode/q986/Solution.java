package zhenyu.sha.leetcode.q986;

import java.util.*;
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i=0;
        int j=0;
        int [] prev=null;
        List<int[]> ret = new LinkedList<>();
        while (i<firstList.length&&j<secondList.length){
            int[] cur=firstList[i];
            if(cur[0]>secondList[j][0]){
                cur=secondList[j];
                j++;
            } else {
                i++;
            }

            if (prev!=null&&cur[0]<=prev[1]) {
                ret.add(new int[]{cur[0], Math.min(prev[1], cur[1])});
            }
            if (prev==null||cur[1]>prev[1]){
                prev= cur;
            }
        }
        while(i<firstList.length&&prev!=null&&firstList[i][0]<=prev[1]){
            ret.add(new int[]{firstList[i][0], Math.min(firstList[i][1], prev[1])});
            i++;
        }
        while(j<secondList.length&&prev!=null&&secondList[j][0]<=prev[1]){
            ret.add(new int[]{secondList[j][0], Math.min(prev[1], secondList[j][1])});
            j++;
        }
        int [][] result = new int[ret.size()][];
        int k=0;
        for(int[] interSec: ret){
            result[k]=interSec;
            k++;
        }
        return result;
    }
}
