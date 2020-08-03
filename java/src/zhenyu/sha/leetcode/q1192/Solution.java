package zhenyu.sha.leetcode.q1192;
import java.util.*;
class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> ret = new LinkedList<>();
        if(n>0){
            //build graph
            //Edge Set
            Set<ArrayList<Integer>> edgeSet = new HashSet<>();
            ArrayList<List<Integer>> graph = new ArrayList<>();
            for(int i=0;i<n;i++){
                graph.add(new LinkedList<>());
            }
            for(List<Integer> e: connections){
                int src=e.get(0);
                int target = e.get(1);
                graph.get(src).add(target);
                graph.get(target).add(src);
                ArrayList<Integer> c= new ArrayList<>(2);
                c.add(src);
                c.add(target);
                edgeSet.add(c);
            }
            //dfs for cycle;
            boolean [] visited = new boolean[n];
            LinkedList<Integer> path = new LinkedList<>();
            boolean [] pathVisited = new boolean[n];
            for(int i= 0 ;i<n;i++){
                if(!visited[i]){
                    Arrays.fill(pathVisited, false);
                    path.clear();
                    dfs(i, graph, path, pathVisited, visited, edgeSet);
                }

            }
            for(List<Integer> e: edgeSet){
                ret.add(e);
            }
        }
        return ret;
    }

    private void dfs(int i, ArrayList<List<Integer>> graph, LinkedList<Integer> path, boolean[] pathVisted, boolean[] visited, Set<ArrayList<Integer>> edgeSet) {

        if(pathVisted[i]) {
            Iterator<Integer> rIt = path.descendingIterator();
            int pre =i;
            while (rIt.hasNext()){
                int cur = rIt.next();
                ArrayList<Integer> c= new ArrayList<>(2);
                c.add( cur);
                c.add(pre);
                edgeSet.remove(c);
                c= new ArrayList<>();
                c.add(pre);
                c.add(cur);
                edgeSet.remove(c);
                pre=cur;
                if(cur==i){
                    break;
                }
            }
        } else {
            pathVisted[i]=true;
            int p = path.isEmpty()?-1:path.getLast();
            path.addLast(i);
            for(int next :graph.get(i) ){
                if(next!=p&&!visited[next]){
                    dfs(next, graph, path,pathVisted, visited, edgeSet);
                }
            }
            path.removeLast();
            pathVisted[i]=false;
            visited[i]=true;
        }

    }

    public static void main(String[] args) {
        //4
        //[[0,1],[1,2],[2,0],[1,3]]
        LinkedList<List<Integer>> graph = new LinkedList<>();
        Integer[] e = new Integer[]{0,1};
        graph.add(Arrays.asList(e));
        e = new Integer[]{1,2};
        graph.add(Arrays.asList(e));
        e = new Integer[]{2,0};
        graph.add(Arrays.asList(e));
        e = new Integer[]{1,3};
        graph.add(Arrays.asList(e));
        System.out.println(new Solution().criticalConnections(4,graph));
    }
}
