package zhenyu.sha.leetcode.q1168;


import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static class
    Cost{
        int well;
        int roads;
        int father;
        int rank;
        int index;
        public Cost(int well, int roads, int father) {
            this.well = well;
            this.roads = roads;
            this.father = father;
            rank =1;
            index=father;
        }

    }
    class Edge implements Comparable<Edge>{
        int cost;
        int x;
        int y;

        public Edge(int cost, int x, int y) {
            this.cost = cost;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost-o.cost;
        }
    }
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {

        Cost[] costs = new Cost[n+1];
        for(int i=1;i<=n;i++) {
            costs[i]=new Cost(wells[i-1],0, i);
        }
        ArrayList<Edge> edges = new ArrayList<>();
        for(int i=0;i<pipes.length;i++) {

            edges.add(new Edge(pipes[i][2], pipes[i][0], pipes[i][1]));

        }
        Collections.sort(edges);
        for(Edge edge: edges) {
            Cost root1 = getRootCost(costs, edge.x);
            Cost root2 = getRootCost(costs, edge.y);
            if(root1.index!=root2.index){
                int maxWell = Math.max(root1.well, root2.well);
                if(maxWell-edge.cost>0){
                    mergeRoot(root1, root2, edge.cost);
                }
            }
        }

        int ret =0;
        for(int i=1;i<=n;i++) {
            Cost cost = costs[i];
            if(cost.father==i){
                ret+=cost.well+cost.roads;
            }
        }
        return ret;
    }

    private void mergeRoot(Cost root1, Cost root2, int edgeCost) {
        int minWell = Math.min(root1.well, root2.well);
        if (root1.rank>root2.rank){
            append(root1, root2, minWell, edgeCost,false);
        } else if (root1.rank<root2.rank){
            append(root2, root1, minWell, edgeCost, false);
        } else {
            append(root1, root2, minWell, edgeCost,true);
        }
    }

    private void append(Cost root1, Cost root2, int minWell, int edgeCost, boolean incRank) {
        root2.father=root1.index;
        root1.well=minWell;
        root1.roads+=root2.roads+edgeCost;
        if(incRank){
            root1.rank++;
        }
    }

    private Cost getRootCost(Cost[] costs, int i) {

        int father=i;
        while (costs[father].father!=father){
            father=costs[father].father;
        }
        while (costs[i].father!=father){
            int next =costs[i].father;
            costs[i].father=father;
            i=next;
        }

        return costs[father];
    }

    public static void main(String[] args) {
        //n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
        System.out.println(new Solution().minCostToSupplyWater(3, new int[]{1,2,2}, new int[][]{{1,2,1},{2,3,1}}));
    }
}
