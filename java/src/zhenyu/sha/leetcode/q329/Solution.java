package zhenyu.sha.leetcode.q329;
import java.util.*;
class Solution {
    int result;
    int[][][] memo;
    public int longestIncreasingPath(int[][] matrix) {
        result =0;
        //memo, 0 left, 1 up, 2 right, 3 down
        memo= new int[matrix.length][matrix[0].length][4];
        for(int i =0; i< matrix.length; i++) {
            for(int j=0; j< matrix[i].length;j++) {
                for(int d=0;d<4;d++) {
                    memo[i][j][d]=-1;
                }
            }
        }
        for(int i =0; i< matrix.length; i++) {
            for(int j=0; j< matrix[i].length;j++) {
                for(int d=0;d<4;d++) {
                    dfs(i, j, d,matrix);
                }
            }
        }
        return result;
    }
    private void dfs(int row, int col, int d, int [][]matrix) {
        if(memo[row][col][d]==-1) {
            //myself
            int curMax =1;
            int nextRow = row;
            int nextCol = col;
            //visit
            if(d==0&&col>0){
                nextCol = col-1;
            } else if (d==1&&row>0){
                nextRow = row -1;
            } else if(d==2&&col<matrix[row].length-1) {
                nextCol = col+1;
            } else if(d==3&&row<matrix.length-1){
                nextRow = row+1;
            }
            //try visit child
            if(matrix[nextRow][nextCol]>matrix[row][col]){
                for(int i =0; i<4;i++) {
                    if((i+2)%4!=d) {
                        dfs(nextRow, nextCol, i, matrix);
                        curMax=Math.max(memo[nextRow][nextCol][i]+1, curMax);
                    }
                }

            }
            // update memo
            memo[row][col][d]=curMax;
            // update result;
            result = Math.max(result, curMax);
        }
    }
}
