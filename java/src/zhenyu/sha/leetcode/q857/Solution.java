package zhenyu.sha.leetcode.q857;
import java.util.*;
class Solution {

    static class Node implements Comparable<Node>{
        int q;
        int w;
        public Node(int q, int w) {
            this.q = q;
            this.w = w;

        }
        double ratio(){
            return (double)w/q;
        }
        @Override
        public int compareTo(Node o) {
           return Double.compare(this.ratio(), o.ratio());
        }
    }
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double ret = Double.MAX_VALUE;
        //put to que;
        ArrayList<Node> nodes = new ArrayList<>(quality.length);
        for(int i=0;i<wage.length;i++) {
            nodes.add(i, new Node(quality[i], wage[i]));
        }
        Collections.sort(nodes);
        PriorityQueue<Integer> qualities = new PriorityQueue<>(K+1, new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int qSum =0;
        for(Node node : nodes) {
            qSum+=node.q;
            qualities.add(node.q);
            while (qualities.size()>K) {
                qSum-=qualities.poll();
            }
            if(qualities.size()==K) {
                ret=Math.min(ret, qSum*node.ratio());
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        //[3,1,10,10,1]
        //[4,8,2,2,7]
        //3
        System.out.println(new Solution().mincostToHireWorkers(new int[]{4,5}, new int[]{8,14}, 2));
    }
}