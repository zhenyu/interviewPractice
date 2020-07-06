package zhenyu.sha.leetcode.q1153;
import java.util.*;
class Solution {
    public boolean canConvert(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;
        if(str1.equals(str2)) {
            return true;
        }
        Map<Character, Character> charMap = new HashMap<>();
        Set<Character> c2Chars = new HashSet<>();
        for(int i=0;i<str1.length();i++){
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            Character curMap = charMap.get(c1);
            if(null!=curMap&&c2!=curMap){
                return false;
            }
            charMap.put(c1, c2);
            c2Chars.add(c2);
        }
        if(charMap.size()==26&&c2Chars.size()==26){
            return false;
        }
        return true;
    }
}