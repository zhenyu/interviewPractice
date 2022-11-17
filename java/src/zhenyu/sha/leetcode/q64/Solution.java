package zhenyu.sha.leetcode.q64;
import java.util.*;
class Solution {
    public int minPathSum(int[][] grid) {
        int[][][] fathers = new int[grid.length][grid[0].length][2];
        int[][] dp = new int [2][grid[0].length];
        Arrays.fill(dp[0], 0);
        Arrays.fill(dp[1],0);
        int prev = 0;
        for(int i = 0; i< grid[0].length;i++) {
            dp[prev][i]= (i>0?dp[prev][i-1]:0)+grid[0][i];
            fathers[0][i] =i>0?new int[] {0, i-1}:new int[]{-1, -1};
        }
        for (int i = 1; i < grid.length; i++ ) {
            int current = (prev+1)%2;
            for (int j =0; j< grid[i].length;j++) {
                int upValue = dp[prev][j];
                int leftValue = j>0?dp[current][j-1]:Integer.MAX_VALUE;
                dp[current][j]=Math.min(upValue, leftValue)+grid[i][j];
                fathers[i][j] = upValue<leftValue? new int[]{i-1, j}:new int[]{i, j-1};
            }
            prev=current;
        }
        int [] father = fathers[grid.length-1][grid[0].length-1];
        System.out.println("x, y="+(grid.length-1)+" "+ (grid[0].length-1));
        while (father[0]!=-1 && father[1]!=-1) {
            System.out.println("x, y="+father[0]+" "+ father[1]);
            father=fathers[father[0]][father[1]];
        }
        return dp[prev][dp[prev].length-1];
    }
    public static void main(String[] args) {
        System.out.println(new Solution().minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }
}