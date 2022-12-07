package zhenyu.sha.leetcode.q44;

class Solution {
    public boolean isMatch(String source, String pattern) {
        char[] s = source.toCharArray();
        char [] p = pattern.toCharArray();
        boolean[][] dp = new boolean[p.length+1][s.length+1];
        dp[0][0] = true;
        for(int i=1; i<p.length+1;i++) {
            for(int j=0;j<s.length+1;j++) {
                if(p[i-1]== '*'){
                    // try to iterate
                    for(int k=j;k>=0;k--){
                        // what if all *; maybe trim it
                        if(dp[i-1][k]) {
                            dp[i][j] = true;
                            break;
                        }
                    }
                } else if(j>0&&(p[i-1]=='?'||p[i-1]==s[j-1])) {
                    dp[i][j]=dp[i-1][j-1];
                }
            }
        }
        return dp[p.length][s.length];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("adceb", "*a*b"));
    }
}
