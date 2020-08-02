package zhenyu.sha.leetcode.q1423;

import java.util.HashMap;
import java.util.Objects;

class Tuple {
    int s;
    int e;
    int k;
    Tuple(int s, int e, int k){
        this.s =s;
        this.e =e;
        this.k=k;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass()!=this.getClass()){
            return false;
        }
        Tuple other = (Tuple) obj;
        return (this.s==other.s&&this.e==other.e&&this.k==other.k);
    }
}
class Solution {
    HashMap<Tuple, Integer> scores;
    public int maxScore(int[] cardPoints, int k) {
        scores = new HashMap<>();
        return max(cardPoints, 0, cardPoints.length-1, k);
    }
    private int max(int[] cardPoints, int s, int e, int k){
        if(k==0)
            return 0;
        Tuple t = new Tuple(s, e, k);
        if(null!=scores.get(t)){
            return scores.get(t);
        }
        int max = Integer.MIN_VALUE;
        if(k==e-s+1){
            int cum =0;
            for(int i=s;i<=e;i++) {
                cum+= cardPoints[i];
            }
            max = cum;
        } else if (k<e-s+1){
            max=Math.max( max(cardPoints, s+1, e, k-1)+cardPoints[s], max(cardPoints,s, e-1, k-1)+cardPoints[e]);
        }
        scores.put(t, max);
        return max;
    }
}