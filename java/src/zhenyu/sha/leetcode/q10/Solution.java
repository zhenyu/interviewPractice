package zhenyu.sha.leetcode.q10;

class Solution {
    public boolean isMatch(String source, String patent) {
        char[] s= source.toCharArray();
        char []  p = patent.toCharArray();
        boolean [][] dp = new boolean[s.length+1][p.length+1];
        dp[0][0] = true;
        for(int i =1; i<=s.length;i++) {
            for(int j=1;j<=p.length;j++) {
                if(j<p.length&&p[j] == '*') {
                    if (p[j-1] == '.') {
                        for(int k=i;k<s.length;k++) {
                            dp[k][j+1]=true;
                        }
                    } else {
                        int k = i;
                        while (k<s.length&&s[k-1]==p[j-1]){
                            dp[k][j+1]=true;
                        }
                    }
                } else if (p[j-1] == '.'||p[j-1] == s[i-1]){
                    dp[i][j]= dp[i][j]||dp[i-1][j-1];
                }
            }
        }
        return dp[s.length][p.length];
    }
}
