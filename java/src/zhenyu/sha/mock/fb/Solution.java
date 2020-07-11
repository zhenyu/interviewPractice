package zhenyu.sha.mock.fb;
import java.util.*;
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> s1Count = new HashMap<>();
        for(char c: s1.toCharArray()) {
            int count = s1Count.getOrDefault(c, 0)+1;
            s1Count.put(c, count);
        }
        int begin =0;
        int end =0;
        Map<Character, Integer> s2Count = new HashMap<>();
        char[] s2Char = s2.toCharArray();
        while(end<s2.length()){

            char c2 =s2Char[end];
            Integer c2Count = s2Count.getOrDefault(c2, 0)+1;
            s2Count.put(c2, c2Count);
            end++;
            if(null==s1Count.get(c2)) {
                begin =end;
                s2Count.clear();
                continue;
            }
            if(s2Count.equals(s1Count)){
                return true;
            }
            while (s1Count.get(c2)<s2Count.get(c2)) {
                s2Count.put(s2Char[begin], s2Count.get(s2Char[begin])-1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().checkInclusion("adc",
                "dcda"));
    }
}