package zhenyu.sha.leetcode.q79;

import java.util.Arrays;

public class Solution {
    public boolean exist(char[][] board, String word) {
        boolean [][]visited= new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for (int j=0;j< board[i].length; j++) {
                Arrays.fill(visited, false);
                if (dfs(i, j, visited, board,0, word)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(int i, int j, boolean[][]visited, char[][] board, int lev, String word ){
        if (visited[i][j]||word.charAt(lev)!=board[i][j]){
            return false;
        }
        lev++;
        visited[i][j] = true;
        if(lev==word.length())
            return true;
        int [][] d ={{-1, 0},{1,0},{0,-1},{0,1}};
        for(int []m : d){
            int nextI= i+m[0];
            int nextJ=j+m[1];
            if(nextI>=0&&nextI<board.length&&nextJ>=0&&nextJ<board[0].length){
                if (dfs(nextI, nextJ, visited, board, lev, word)){
                    return true;
                }
            }
        }
        visited[i][j] = false;
        return false;
    }
}