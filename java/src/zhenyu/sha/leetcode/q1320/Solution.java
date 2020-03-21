package zhenyu.sha.leetcode.q1320;

import java.util.*;

class Solution {

    public int minimumDistance(String word) {
        if (null == word || word.length() <= 2)
            return 0;
        int []dp1 = new int[26];
        int []dp2 = new int[26];
        //index is the other figure
        // inital, one finger on index 0, other in space , that means in every space the cost is zero
        for(int i = 0; i<26; i++) {
            dp1[i]=0;
        }
        //iterater next char
        for(int i=1;i<word.length();i++) {
            int finger1 = word.charAt(i-1)-'A';
            int next = word.charAt(i)-'A';
            //from finger 1 to next
            for(int j =0;j<dp1.length;j++) {
                // from finger 1 to next
                dp2[j] = dp1[j]+getDst(finger1,next);
            }
            // from finger 2 to next, figure 1 become finger 2
            for(int j =0;j<dp1.length;j++) {
                int cost = getDst(j,next)+dp1[j];
                if(cost<dp2[finger1]){
                    dp2[finger1]=cost;
                }
            }
            int[] temp = dp1;
            dp1=dp2;
            dp2=temp;
        }
        int ret =Integer.MAX_VALUE;
        for(int cost: dp1) {
            if(cost<ret)
                ret = cost;
        }
        return ret;
    }
    private int getDst(int s, int target) {

        int ret =0;
        // row
        ret += Math.abs(s/6-target/6);
        //column
        ret += Math.abs(s%6-target%6);
        return ret;
    }
}
