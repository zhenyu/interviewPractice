package zhenyu.sha.leetcode.q528;
import java.util.*;
import java.math.*;
class Solution {
    int bound;
    int []s;
    public Solution(int[] w) {
        bound = 0;
        s   = new int[w.length];
        for(int i =0; i<w.length;i++) {
            s[i]=bound;
            bound+=w[i];
        }

    }

    public int pickIndex() {
        double w = Math.random()*bound;
        return lowerBound(w);
    }
    private int lowerBound(double w) {
        int start=0;
        int end = s.length-1;
        if (s[end]<=w){
            return end;
        }
        while (start<end-1){
            int mid = start+(end-start)/2;
            if(s[mid]<=w){
                start = mid;
            } else {
                end = mid;
            }
        }
        if(start+1<s.length&&s[start+1]<=end){
            return start+1;
        }
        return start;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */