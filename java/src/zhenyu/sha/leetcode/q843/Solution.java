package zhenyu.sha.leetcode.q843;

/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 *
 * }
 */
import  java.util.*;
interface Master {
      int guess(String word); }
class Solution {
    int [][] matches;
    public void findSecretWord(String[] wordlist, Master master) {
        if(null==wordlist||wordlist.length==0)
            return;
        matches = new int[wordlist.length][wordlist.length];
        Set<Integer> candidates = new HashSet<>();
        for(int i = 0; i< wordlist.length;i++) {
            candidates.add(i);
            matches[i][i] =6;
            for(int j=i+1;j<wordlist.length;j++) {
                int match = 0;
                for(int k =0; k<6; k++) {
                    if(wordlist[i].charAt(k)==wordlist[j].charAt(k)){
                        ++match;
                    }
                }
                matches[i][j]=match;
                matches[j][i]=match;
            }
        }
        for(int i =0; i<11;i++) {
            System.out.println("i="+i);
            if(candidates.size()>0) {
                int index = pickGuess(candidates);
                int match = master.guess(wordlist[index]);
                if(6==match)
                    return;
                for(int j =0; j<wordlist.length;j++) {
                    if(match!=matches[index][j]) {
                        candidates.remove(j);
                    }
                }
                candidates.remove(index);
            }
        }
    }
    int pickGuess(Set<Integer> candidates){
        //可能淘汰的最大的
        int maxMin = Integer.MIN_VALUE;
        int index =-1;
        for(int c : candidates) {
            //每个match 的candidate 数量
            int []matchCount=new int[7];
            for(int j: candidates) {
                matchCount[matches[c][j]]+=1;
            }
            int sum =0;
            for(int r:matchCount){
                sum+=r;
            }
            int min = Integer.MAX_VALUE;
            //可能淘汰的其他人的最小值，最少能淘汰多少人
            for( int r:matchCount) {
                min = Math.min(min, sum-r);
            }
            // 我最少能淘汰的比别人多，所以我比较有辨识度
            if(min>maxMin) {
                index=c;
                maxMin=min;
            }
        }
        System.out.println("minmax="+maxMin+" index="+index);
        return index;
    }
}

