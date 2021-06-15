package zhenyu.sha.leetcode.q695;

import java.util.*;
class Solution {
    int max;
    boolean[][] visited;
    int [][] moves = {{-1,0},{1,0},{0,-1}, {0,1}};
    int maxX;
    int maxY;
    int area;
    int[][] grid;
    public int maxAreaOfIsland(int[][] grid) {
        this.grid= grid;
        max =0;
        maxX = grid.length;
        maxY = grid[0].length;
        visited = new boolean[grid.length][grid[0].length];
        for( int i= 0; i<maxX; i++) {
            for(int j = 0; j<maxY;j++) {
                area =0;
                dfs( i, j);

            }
        }
        return max;
    }
    private void dfs(int x, int y ) {
        if (visited[x][y]||grid[x][y]==0)
            return;
        visited[x][y] = true;
        area++;
        max = Math.max(area, max);
        for(int[] move : moves){
            int nextX=x+move[0];
            int nextY = y+move[1];
            if(nextX>=0&&nextX<maxX&&nextY>=0&&nextY<maxY) {
                dfs(nextX,nextY);
            }
        }
    }
}