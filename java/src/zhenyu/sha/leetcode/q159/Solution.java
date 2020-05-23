package zhenyu.sha.leetcode.q159;
import java.util.*;
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(null==s||s.length()==0)
            return 0;
        Map<Character, Integer> counter = new HashMap<>(2);
        char[]input = s.toCharArray();
        int start=0;
        int end =0;
        int ret =0;
        while (end<input.length){
            char c = input[end];
            if(!counter.containsKey(c)&&counter.size()>=2){
                ret = Math.max(ret, end-start);
                while (counter.size()>=2){
                    char sC =input[start];
                    int count = counter.get(sC)-1;
                    if(count<=0){
                        counter.remove(sC);
                    } else{
                        counter.put(sC, count);
                    }
                    start++;
                }
            }
            int count = counter.getOrDefault(c, 0)+1;
            counter.put(c, count);
            end++;
        }
        //don't forget the last one
        ret = Math.max(ret, end-start);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }
}