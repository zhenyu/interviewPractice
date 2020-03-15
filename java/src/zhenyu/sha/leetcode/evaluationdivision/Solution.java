package src.zhenyu.sha.leetcode.evaluationdivision;
import java.util.*;
class ToValue {
    String to;
    double val;
    public ToValue(String to, double val) {
        this.to = to;
        this.val = val;
    }
}
class Solution {
    //graph
    Map<String, List<ToValue>> graph = null;
    //queryCach
    Map<String, Double> cache = new HashMap<>();
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        graph = new HashMap<>();
        double[] ret = new double[queries.size()];
        //TODO size check
        for(int i = 0; i< equations.size(); i++) {
            List<String> equation = equations.get(i);
            String first = equation.get(0);
            String second = equation.get(1);
            addEdge(first, second, values[i]);
            addEdge(second, first, values[i]);
        }
        for (int i = 0; i<queries.size();i++) {
            ret[i] = queryVal(queries.get(i));
        }
        return ret;
    }
    private double queryVal(List<String> query){
        String from = query.get(0);
        String to = query.get(1);
        double ret = -1;
        if(graph.containsKey(from)&&graph.containsKey(to)) {
            //BFS
            Queue<ToValue> q = new LinkedList<>();
            q.add(new ToValue(from,1));
            while (q.size()>0) {
                ToValue cur = q.poll();
                double curVal = cur.val;
                if(cur.equals(to)){
                    ret = curVal;
                    break;
                }
                for(ToValue edge: graph.get(cur.to)){
                    q.offer(new ToValue(edge.to,curVal/edge.val));
                }
            }
        }
        return ret;
    }
    //helper
    private void addEdge(String from, String to, double val) {
        List<ToValue> edges = graph.get(from);
        if (null== edges ) {
            edges = new LinkedList<>();
            graph.put(from,edges);
        }
        edges.add(new ToValue(to, val));
    }
}