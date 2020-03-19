package zhenyu.sha.leetcode.q1011;
import java.util.*;
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int begin =1;
        int end = 0;
        for(int i : weights) {
            end = end + i;
        }
        while (begin<end) {
            int mid = begin+(end-begin)/2;

            if(canShip(weights, D, mid)){
                end=mid;
            } else {
                begin = mid+1;
            }
        }
        return canShip(weights, D, begin)? begin:-1;

    }
    private boolean canShip(int[]weights, int D, int max) {
        int cum =0;
        int d=0;
        for(int i=0;i<weights.length;i++) {
            if(weights[i]>max){
                return false;
            }
            if(cum+weights[i]<=max) {
                cum = cum + weights[i];
            } else {
                d++;
                if(d>D) {
                    return false;
                }
                cum = weights[i];
            }
        }
        if(cum>0) d++;
        return d<=D;
    }
    public static void main(String[] args){
        System.out.println(new Solution().shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
    }
}