package zhenyu.sha.leetcode.wildmatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }
    boolean isMatch(String s, int i, String p, int j) {
        if (i == s.length() && j == p.length()) return true;
        if (i == s.length() || j == p.length()) return false;
        if (p.charAt(j) == '*') {
            while (j < p.length() && p.charAt(j) == '*') ++j; //skip continuous '*'
            if (j == p.length()) return true;
            while (i < s.length() && !isMatch(s, i, p, j)) ++i;
            return i < s.length();
        } else if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?')
            return isMatch(s, ++i, p, ++j);
        else return false;
    }

}
public class MainClass {
    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().isMatch("aa", "a"));
    }
}
