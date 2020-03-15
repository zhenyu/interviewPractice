package zhenyu.sha.leetcode.KClosestPoints;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    class Node{
        int x;
        int y;
        int r;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
            r = x*x+y*y;
        }
    }
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Node> que = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.r-o2.r;
            }
        });
        return null;
    }
}