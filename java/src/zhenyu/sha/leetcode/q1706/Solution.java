package zhenyu.sha.leetcode.q1706;

import java.util.*;

class Solution {
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    public int[] findBall(int[][] grid) {

        int[][] dps = new int[2][grid[0].length];
        int current = 0;
        for(int j =0; j< grid[grid.length-1].length; j++) {
            if(LEFT==grid[grid.length-1][j]) {
                if(j<=0||RIGHT==grid[grid.length-1][j-1]){
                    dps[current][j]=-1;
                } else {
                    dps[current][j]=j-1;
                }
            } else {
                if(j==grid[0].length-1||LEFT==grid[grid.length-1][j+1]){
                    dps[current][j]=-1;
                } else {
                    dps[current][j]=j+1;
                }
            }
        }
        for(int i = grid.length-2; i>=0;i--) {
            int next = (current+1)%2;
            for(int j=0;j<grid[i].length;j++) {
                if(LEFT==grid[i][j]) {
                    if(j<=0||RIGHT==grid[i][j-1]){
                        dps[next][j]=-1;
                    } else {
                        dps[next][j]=dps[current][j-1];
                    }
                } else {
                    if(j==grid[0].length-1||LEFT==grid[i][j+1]){
                        dps[next][j]=-1;
                    } else {
                        dps[next][j]=dps[current][j+1];
                    }
                }
            }
            // update the
            current =next;
        }
        return dps[current];
    }
}
