package zhenyu.sha.leetcode.q975;
import org.w3c.dom.Node;

import java.util.*;
class Solution {
    static class OddNode implements Comparable<OddNode>{
        int val;
        int pos;

        public OddNode(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }

        @Override
        public int compareTo(OddNode o) {
            int ret = this.val-o.val;
            if(ret==0){
                ret = this.pos-o.pos;
            }
            return ret;
        }
    }
    static class EvenNode implements Comparable<EvenNode>{
        int val;
        int pos;

        public EvenNode(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }

        @Override
        public int compareTo(EvenNode o) {
            int ret = this.val-o.val;
            if(ret==0){
                ret = o.pos-this.pos;
            }
            return ret;
        }
    }
    public int oddEvenJumps(int[] a) {
        if(null==a||a.length==0) {
            return 0;
        }
        boolean[] odd = new boolean[a.length];
        boolean[] even = new boolean[a.length];
        odd[a.length-1]=true;
        even[a.length-1]=true;
        int ret =1;

        TreeSet<OddNode> oddCandidates = new TreeSet<>();
        TreeSet<EvenNode> evenCandidates = new TreeSet<>();
        oddCandidates.add(new OddNode(a[a.length-1],a.length-1));
        evenCandidates.add(new EvenNode(a[a.length-1],a.length-1));
        for(int i=a.length-2;i>=0;i--) {
            OddNode curOdd = new OddNode(a[i], i);
            //check odd , next will be even , larger or equal to me , with smallest index
            OddNode next = oddCandidates.ceiling(curOdd);
            if(null!=next&&even[next.pos]){
                odd[i]=true;
                ret++;

            }
            oddCandidates.add(curOdd);
            // check even, next will be odd, smaller or equal to me, with smallest index
            EvenNode curEven = new EvenNode(a[i], i);
            EvenNode nextOdd = evenCandidates.floor(curEven);
            if(null!=nextOdd&&odd[nextOdd.pos]){
                even[i]=true;
            }
            evenCandidates.add(curEven);

        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().oddEvenJumps(new int[]{1,2,3,2,1,4,4,5}));
    }
}
