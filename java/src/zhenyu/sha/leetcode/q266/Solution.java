package zhenyu.sha.leetcode.q266;
import java.util.*;

class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        for( char c: s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0)+1);
        }
        int oddCount  = 0;
        for( Integer count: countMap.values()){
            if (1==count%2){
                oddCount++;
                if(oddCount>1){
                    return false;
                }
            }
        }
        return true;
    }
}
