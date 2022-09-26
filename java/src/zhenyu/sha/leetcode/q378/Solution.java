package zhenyu.sha.leetcode.q378;
import java.util.*;
class Item  implements Comparable<Item>{
    public Item(int[][] matrix, int x, int y ) {
        this.matrix = matrix;
        this.x = x;
        this.y = y;
    }
    int [][] matrix;
    int x;
    int y;
    public int compareTo( Item other) {
        return Integer.compare(this.matrix[this.x][this.y], other.matrix[other.x][other.y]);
    }
}
class Solution {

    public int kthSmallest(int[][] matrix, int k) {
        if(k==1) {
            return matrix[0][0];
        }
        boolean [][] visited = new boolean[matrix.length][matrix[0].length];
        PriorityQueue<Item> que = new PriorityQueue<>();
        int count =0;
        Item result = new Item(matrix, 0, 0);
        visited[0][0] = true;
        que.add(result);
        int [][] steps = new int [][] {{0,1}, {1,0}};
        while (count < k) {
            result = que.poll();
            count++;
            for (int [] step : steps) {
                int x = result.x+step[0];
                int y = result.y +step[1];
                if(x<matrix.length&&y<matrix[x].length&&!visited[x][y]){
                    Item next = new Item(matrix, x, y);
                    visited[x][y] = true;
                    que.add(next);
                }
            }
        }
        return matrix[result.x][result.y];
    }
}