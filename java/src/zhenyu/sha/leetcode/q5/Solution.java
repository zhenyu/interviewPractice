package zhenyu.sha.leetcode.q5;

import java.util.Arrays;

class Solution {
    public String longestPalindrome(String input) {
        if (null == input || input.length()==0){
            return "";
        }
        char[] s = input.toCharArray();
        int[][] dp  = new int[s.length][s.length];
        for(int[] a : dp) {
            Arrays.fill(a, -1);
        }
        // we use []
        // init the lenght 1 and length 2
        int max = 1;
        String result = input.substring(0,1);
        for (int i = 0; i < dp.length; i++) {
            dp[i][i]=1;
            if(i+1< dp.length&&s[i]==s[i+1]) {
                dp[i][i+1] = 2;
                if(max<2) {
                    max=2;
                    result=""+input.substring(i, i+2);
                }
            }
        }
        for (int l=3;l<= dp.length;l++) {
            int i =0;
            while (i+l-1<dp.length) {
                int l1 = l-2;
                int prev = dp[i+1][i+l1];
                if(prev>0 && s[i]==s[i+l-1]) {
                    dp[i][i+l-1] = prev+2;
                    if (prev+2>max) {
                        result = input.substring(i, i+l);
                        max = prev+2;
                    }
                }
                i++;
            }
        }
        return result;
    }
    public static void main (String[] args) {
        System.out.println(new Solution().longestPalindrome("caba"));
    }
}