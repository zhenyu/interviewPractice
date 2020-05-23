package zhenyu.sha.leetcode.q360;

import java.util.ArrayList;

class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {

        ArrayList<Integer> inc = new ArrayList<>();
        ArrayList<Integer> dec = new ArrayList<>();
        int buf[]= new int[nums.length];
        for(int i=0;i<nums.length;i++) {
            buf[i]= a*nums[i]*nums[i]+b*nums[i]+c;
        }
        int i=0;
        while (i+1<nums.length&&buf[i]==buf[i+1]){
            i++;
        }
        if(i==nums.length-1)
            return buf;
        boolean incTrend = buf[i+1]>buf[i];
        if(incTrend){
            inc.add(buf[0]);
        } else {
            dec.add(buf[0]);
        }
        for(i=1;i<buf.length;i++){
            if(buf[i]>buf[i-1]){
                inc.add(buf[i]);
                incTrend = true;
            } else if (buf[i]<buf[i-1]){
                dec.add(buf[i]);
                incTrend = false;
            } else {
                if(incTrend){
                    inc.add(buf[i]);
                } else {
                    dec.add(buf[i]);
                }
            }
        }
        i = 0;
        int j = dec.size()-1;
        int[] ret = new int[nums.length];
        int k=0;
        while (i<inc.size()&&j>=0){
            int x = inc.get(i);
            int y = dec.get(j);
            if(x<y){
                ret[k]=x;
                ++i;
            } else {
                ret[k]=y;
                --j;
            }
            ++k;
        }
        while (i<inc.size()){
            ret[k]=inc.get(i);
            i++;
            k++;
        }
        while (j>=0) {
            ret[k]=dec.get(j);
            k++;
            j--;
        }

        return ret;
    }
}