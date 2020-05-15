package zhenyu.sha.leetcode.q1007;
import java.util.*;
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if (null == A || null == B) {
            return 0;
        }
        if (A.length != B.length)
            return -1;

        Map<Integer, Integer> counterA = new HashMap<>();
        Map<Integer, Integer> counterB = new HashMap<>();
        Integer cadidate = -1;

        for ( int i = 0; i < A.length; i++) {
            int countA = counterA.getOrDefault(A[i], 0) + 1;
            counterA.put(A[i], countA);
            int countB = counterB.getOrDefault(B[i], 0) + 1;
            counterB.put(B[i], countB);
        }
        if (counterA.size() == 1 || counterB.size() == 1) {
            return 0;
        }
        for(Map.Entry<Integer, Integer> entry: counterA.entrySet()){
            if(entry.getValue()+counterB.getOrDefault(entry.getKey(), 0)>=A.length){
                cadidate = entry.getKey();
                break;
            }
        }
        if(cadidate==-1)
            return -1;
        for(int i =0;i<A.length;i++){
            if(A[i]!=cadidate&&B[i]!=cadidate)
                return -1;
        }
        return Math.min(A.length-counterA.get(cadidate), A.length-counterB.get(cadidate));
    }

    public static void main(String[] args) {
        //new [2,1,2,4,2,2]
        //[5,2,6,2,3,2]
        System.out.println(new Solution().minDominoRotations(new int[]{2,1,2,4,2,2}, new int[]{5,2,6,2,3,2}));
    }
}
