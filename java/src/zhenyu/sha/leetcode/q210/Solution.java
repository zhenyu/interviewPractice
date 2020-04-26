package zhenyu.sha.leetcode.q210;

import java.util.*;

class Solution {
    static class CycleException extends Exception {};
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<List<Integer>> graph = new ArrayList<>(numCourses);
        for(int i=0;i<numCourses;i++) {
            graph.add(new LinkedList<>());
        }
        //build graph
        for(int[] pre: prerequisites) {
            int father = pre[1];
            int child = pre[0];
            List<Integer> children = graph.get(father);
            children.add(child);
            graph.set(father, children);
        }
        List<Integer> topoSortResult = topSort(graph);
        if(topoSortResult.size()==0){
            return new int[0];
        }
        int[] ret = new int[topoSortResult.size()];
        int index =0;
        for(int i : topoSortResult) {
            ret[index] = i;
            ++index;
        }
        return ret;
    }
    List<Integer> topSort(ArrayList<List<Integer>> graph) {
        LinkedList<Integer> result = new LinkedList<>();
        boolean[] seen= new boolean[graph.size()];
        boolean[] path = new boolean[graph.size()];
        for(int i=0;i<graph.size(); i++) {
            try {
                dfs(i, graph, seen, result, path);
            } catch (CycleException e) {
                result.clear();
            }

        }
        return result;
    }

    private void dfs(int node, ArrayList<List<Integer>> graph, boolean[] seen, LinkedList<Integer> result, boolean[] path) throws CycleException{
        if(!seen[node]){
            //put myself in seen, put myself in path, visit child
            seen[node]=true;
            path[node]=true;
            for(int child: graph.get(node)) {
                if(path[child]){
                    throw  new CycleException();
                }
                dfs(child, graph, seen, result, path);
            }
            //remove myself from path
            path[node]=false;
            //put myself in the head of result;
            result.addFirst(node);
        }
    }

    public static void main(String[] args) {
        int []ret= new Solution().findOrder(2, new int[][]{{1,0}});
        for(int i: ret){
            System.out.print(i);
            System.out.print("->");
        }

    }
}