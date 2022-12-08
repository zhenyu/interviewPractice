package zhenyu.sha.leetcode.q79;

import java.util.*;

public class Solution {
    int [][] steps = new int[][]{{-1, 0}, {1,0}, {0,-1}, {0, 1}};
    public boolean exist(char[][] board, String word) {
        // TOOD make sure the word is not null;
        if(null==word){
            return true;
        }
        Map<Integer, Set<Integer>> visited =new HashMap<>();
        for(int i=0; i<board.length;i++) {
            for(int j=0; j<board[i].length; j++) {
                visited.clear();
                if (dfs(board, i, j,visited , word.toCharArray(), 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isValid(int i, int j, int M, int N) {
        return  i>=0&&i<M&&j>=0&&j<N;
    }
    private boolean isVisited(Map<Integer, Set<Integer>> visited, int i, int j) {
        Set<Integer> cols = visited.getOrDefault(i, new HashSet<>());
        return cols.contains(j);
    }
    private boolean dfs(char[][] board, int i, int j, Map<Integer,Set<Integer>> visited, char[] pattern, int index) {

        // check whether equal
        if (board[i][j]!=pattern[index]){
            return false;
        }
        // already the last one
        if (index==pattern.length-1)
            return true;
        // put to visited
        Set<Integer> cols = visited.getOrDefault(i, new HashSet<>());
        cols.add(j);
        visited.put(i, cols);

        for(int [] step: steps) {
            int nextI = i+step[0];
            int nextJ = j+step[1];
            if(isValid(nextI, nextJ,board.length, board[0].length )&&!isVisited(visited, nextI, nextJ)) {
                if (dfs(board, nextI, nextJ, visited, pattern, index+1)) {
                    return true;
                }
            }
        }
        // remove from path
        cols.remove(j);
        return false;
    }
}