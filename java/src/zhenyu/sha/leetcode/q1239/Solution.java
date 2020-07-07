package zhenyu.sha.leetcode.q1239;

import java.util.*;

class State {
    public State(int x, int y, int k, int l) {
        this.x=x;
        this.y=y;
        this.k =k;
        this.l= l;
    }
    int x;
    int y;
    int k;
    int l;
}
class Solution {
    static int[][] moves = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    public int shortestPath(int[][] grid, int k) {
        if(null==grid||grid.length==0)
            return -1;
        int m = grid.length;
        if(grid[0]==null||grid[0].length==0){
            return -1;
        }
        int n = grid[0].length;
        Map<Long, Integer> statesK = new HashMap<>();
        LinkedList<State> que = new LinkedList<>();
        que.add(new State(0,0, k,0));
        while (que.size()>0){
            State cur = que.pollFirst();
            if ((cur.x==m-1)&&(cur.y==n-1)){
                return cur.l;
            }

            for(int i=0;i<4;i++){
                int nextX = cur.x+ moves[i][0];
                int nextY = cur.y+ moves[i][1];
                if(nextX<0||nextX>=m||nextY<0||nextY>=n){
                    continue;
                }
                //next whether block
                int nextK = cur.k-grid[nextX][nextY];
                Integer nextStateK = statesK.get((long)(nextX)*n+nextY);
                if(nextK<0||(nextStateK!=null&&nextK<=nextStateK)){
                    continue;
                }
                statesK.put((long)(nextX)*n+nextY, nextK);
                que.addLast(new State(nextX,nextY,nextK,cur.l+1));
            }
        }
        return -1;
    }
}