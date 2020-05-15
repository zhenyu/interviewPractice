package zhenyu.sha.leetcode.q317;
import java.util.*;
class node {
    int x, y;
    public node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Dur implements Comparable<Dur>{
    Dur(int start, int end) {
        this.start = start;
        this.end = end;
    }
    int start;
    int end;

    @Override
    public int compareTo(Dur o) {
        return 0;
    }
}
class Solution {
    public int shortestDistance(int[][] grid) {
        // write your code here
        int n = grid.length;
        int m = grid[0].length;
        int [][] reach = new int[n][m];
        int [][] distance = new int[n][m];
        int building = 0;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(grid[i][j] == 1){
                    building++;
                    boolean[][] visited = new boolean[n][m];
                    Queue<node> q = new LinkedList<>();
                    q.offer(new node(i,j));
                    int dist = 0;
                    while(!q.isEmpty()){
                        int size = q.size();
                        for(int k = 0;k < size;k++){
                            node curr = q.poll();
                            int x = curr.x;
                            int y = curr.y;
                            distance[x][y] += dist;
                            reach[x][y]++;
                            if(x > 0 && grid[x - 1][y] == 0 && !visited[x - 1][y]){
                                q.offer(new node(x - 1,y));
                                visited[x - 1][y] = true;
                            }
                            if(x + 1 < n && grid[x + 1][y] == 0 && !visited[x + 1][y]){
                                q.offer(new node(x + 1,y));
                                visited[x + 1][y] = true;
                            }
                            if(y > 0 && grid[x][y - 1] == 0 && !visited[x][y - 1]){
                                q.offer(new node(x,y - 1));
                                visited[x][y - 1] = true;
                            }
                            if(y + 1 < m && grid[x][y + 1] == 0 && !visited[x][y + 1]){
                                q.offer(new node(x,y + 1));
                                visited[x][y + 1] = true;
                            }
                        }
                        dist++;
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0;i  < n;i++){
            for(int j = 0;j < m;j++){
                if(grid[i][j] == 0 && distance[i][j] < res && reach[i][j] == building){
                    res = distance[i][j];
                }
            }
        }
        if(res == Integer.MAX_VALUE){
            return -1;
        }
        else{
            return res;
        }
    }
}
