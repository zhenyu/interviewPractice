package zhenyu.sha.leetcode.q340;

import java.util.*;

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length()<=k)
            return s.length();
        Map <Character, Integer> counter = new HashMap<>();
        int ret=0;
        char[] sa = s.toCharArray();
        //inclusion
        int begin =0;
        // exclusion
        int end=0;
        while (end!=sa.length){
            int curCount = counter.getOrDefault(sa[end], 0);
            counter.put(sa[end], curCount+1);
            end++;
            while (counter.size()>k){
                curCount = counter.get(sa[begin]);
                curCount--;

                if(curCount==0){
                    counter.remove(sa[begin]);
                }else {
                    counter.put(sa[begin], curCount);
                }
                begin++;
            }
            ret = Math.max(ret, end-begin);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct("eceba", 2));
    }
}
