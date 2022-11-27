package zhenyu.sha.leetcode.q10;

class Solution {
    public boolean isMatch(String source, String patent) {
        char[] s= source.toCharArray();
        char []  p = patent.toCharArray();
        boolean [][] dp = new boolean[p.length+1][s.length+1];
        // init
        dp[0][0] = true;
        for (int i =1 ;i<=p.length; i++) {
            dp[i][0] = false;
        }
        for (int i =1 ;i<=s.length; i++) {
            dp[0][i] = false;
        }
        for(int i =1; i<=p.length;i++) {
            char currentP =p[i-1];
            for(int j=1;j<=s.length;j++) {
                char currentS = s[j-1];
                if(currentP=='*') {
                    if (dp[i-2][j]) {
                        dp[i][j] = true;
                    } else {
                        for(int k =j; k>0;k--) {
                            if((p[i-2]=='.'||p[i-2]==s[k-1])) {
                                if (dp[i-2][k-1]) {
                                    dp[i][j]=true;
                                    break;
                                }
                            }
                        }
                    }

                } else if(currentP =='.'||currentP==currentS) {
                    dp[i][j]=dp[i-1][j-1];
                }
            }
        }
        return dp[p.length][s.length];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aa", "a*"));
    }
}
