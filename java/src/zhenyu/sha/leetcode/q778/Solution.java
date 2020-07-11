package zhenyu.sha.leetcode.q778;
import java.util.*;
class Edge implements Comparable<Edge>{
    Edge(int x1, int y1, int x2, int y2, int v ){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.v = v;
    }
    int x1;
    int y1;
    int x2;
    int y2;
    int v;
    public int compareTo(Edge o) {
        return Integer.compare(this.v, o.v);
    }
}
class Solution {
    int [][] moves = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    public int swimInWater(int[][] grid) {
        if(null==grid||grid.length==0||grid[0].length==0)
            return 0;
        int [][] cost = new int[grid.length][grid[0].length];
        for(int i =0; i< cost.length;i++){
            Arrays.fill(cost[i], -1);
        }
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        cost[0][0] =0;
        edges.addAll(getNewEdges(0, 0, cost, grid));
        while (!edges.isEmpty()) {
            Edge e = edges.poll();
            // update cost
            cost[e.x2][e.y2]=cost[e.x1][e.y1]+e.v;
            // add new Edges
            edges.addAll(getNewEdges(e.x2, e.y2, cost, grid));
        }
        return cost[grid.length-1][grid[0].length-1];
    }
    List<Edge> getNewEdges(int x, int y, int[][]cost, int[][] grid){
        List<Edge> edges = new LinkedList<>();
        for(int i =0; i<4;i++) {
            int x2 = x+moves[i][0];
            int y2 = y+moves[i][1];
            if(x2>=0&&x<grid.length&&y2>=0&&y2<grid[0].length&&cost[x2][y2]<0){
                edges.add(new Edge(x, y, x2, y2, Math.max(0, grid[x2][y]-grid[x][y])));
            }
        }
        return edges;
    }
}
