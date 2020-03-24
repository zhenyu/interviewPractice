package zhenyu.sha.leetcode.q568;

import java.util.Arrays;

class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        //state per city , vacantion cumlates

        int N = flights.length;
        int K = days[0].length;
        int curDP =0;
        int dp[][]= new int[2][N+1];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        dp[curDP][0]=0;
        //for days
        for(int i=0;i<K;i++) {
            int nextDP = (curDP+1)%2;
            // for cities
            for(int c =0; c<N; c++) {
                if(dp[curDP][c]>-1) {
                    int cum = dp[curDP][c];
                    for(int n=0;n<N;n++) {
                        if(c==n||flights[c][n]==1){
                            if (dp[nextDP][n]<cum+days[n][i]){
                                dp[nextDP][n]=cum+days[n][i];
                            }
                        }
                    }
                }
            }
            //swap dp status
            Arrays.fill(dp[curDP],-1);
            curDP = nextDP;
        }
        // CheckMax
        int ret =0;
        for(int i =0; i<N;i++) {
            if(dp[curDP][i]>ret){
                ret = dp[curDP][i];
            }
        }
        return ret;
    }
    public static void main(String[]args){
        System.out.println(new Solution().maxVacationDays(new int[][]{{0,1,1},{1,0,1},{1,1,0}}, new int[][]{{1,3,1},{6,0,3},{3,3,3}}));
    }
}