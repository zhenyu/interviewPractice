package zhenyu.sha.leetcode.q1163;
import  java.util.*;
class SuffixArrayBuilder {
    static class Result{
        int[] indexToRank;
        boolean allSorted;

        public Result(int[] indexToRank, boolean allSorted) {
            this.indexToRank = indexToRank;
            this.allSorted = allSorted;
        }
    }

    int[] countSort(int[] indexes, int [] indexToRank, int maxRank) {
        int [] ret = new int[indexes.length];
        for(int rank: indexToRank){
            maxRank = Math.max(maxRank, rank);
        }
        int [] rankCount = new int[maxRank+1]; //plus one for rank 0;
        Arrays.fill(rankCount, 0);
        for(int rank: indexToRank) {
            rankCount[rank]+=1;
        }
        for(int i =1; i< rankCount.length;i++) {
            rankCount[i]+=rankCount[i-1];
        }
        for(int i = indexToRank.length-1; i>=0;i--) {
            int index = indexes[i];
            int curRank = indexToRank[index];
            int targetP = rankCount[curRank]-1;
            ret[targetP]=index;
            rankCount[curRank]-=1;
        }
        return ret;
    }

    Result radixSort(int[] indexToRank, int k){
        //根据 index i 和 index i+k 的rank, 排序 index
        int [] indexes = new int[indexToRank.length];
        for(int i=0;i<indexToRank.length;i++) {
            indexes[i]=i;
        }
        int[] indexKToRank = new int[indexToRank.length];
        int ranMax = Integer.MIN_VALUE;
        for(int i =0; i< indexToRank.length;i++) {
            ranMax = Math.max(ranMax, indexToRank[i]);
            if(i+k>=indexToRank.length){
                indexKToRank[i]=0;
            } else {
                indexKToRank[i]=indexToRank[i+k];
            }
        }
        //  LSD the first is rank at index i
        indexes = countSort(indexes, indexKToRank,ranMax);
        indexes = countSort(indexes, indexToRank, ranMax);
        int first =-1;
        int second =-1;
        int curRank = 0;
        int [] retIndexToRank = new int[indexToRank.length];
        for(int index: indexes) {
            if(first!=indexToRank[index]||second!=indexKToRank[index]) {
                first =indexToRank[index];
                second = indexKToRank[index];
                curRank++;
            }
            retIndexToRank[index]= curRank;
        }
        return new Result(retIndexToRank, curRank>=indexToRank.length);
    }
    int[] build(String s) {
        int length =s.length();
        char[] input = s.toCharArray();
        int rank[] = new int[26];
        boolean exist[] = new boolean[26];
        for(char c: input) {
            exist[c-'a'] = true;
        }
        int curRank = 1;
        for(int i = 0; i<26; i++) {
            if(exist[i]) {
                rank[i]=curRank;
                curRank++;
            }
        }
        int [] indexToRank = new int[length];
        for(int i= 0;i<length;i++){
            indexToRank[i] = rank[input[i]-'a'];
        }
        for(int k=1;k<=length;k=k<<1) {
            Result r= radixSort(indexToRank, k);
            indexToRank = r.indexToRank;
            if(r.allSorted) {
                //rankToIndex
                int[]rankToIndex = new int[length];
                for(int index =0; index<length; index++) {
                    rankToIndex[indexToRank[index]-1]=index;
                }
                return rankToIndex;
            }
        }
        return null;
    }

}
class Solution {
    public String lastSubstring(String s) {
        if(s==null||s.length()==0){
            return "";
        }
        if(s.length()==1){
            return s;
        }
        int[]sa =new SuffixArrayBuilder().build(s);
        return s.substring(sa[sa.length-1]);
    }
}
