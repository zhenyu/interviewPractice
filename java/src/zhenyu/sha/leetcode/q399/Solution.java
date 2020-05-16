package zhenyu.sha.leetcode.q399;
import java.util.*;
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //buid grahp
        Map<String, Map<String, Double>> graph = new HashMap<>();
        int i=0;
        for(List<String> eq: equations) {
            String src = eq.get(0);
            String dst = eq.get(1);
            Map<String, Double> dsts=graph.getOrDefault(src, new HashMap<>());
            dsts.put(dst, values[i]);
            graph.put(src, dsts);
            dsts=graph.getOrDefault(dst, new HashMap<>());
            dsts.put(src, 1/values[i]);
            graph.put(dst, dsts);
            i++;
        }
        double[] ret = new double[queries.size()];
        Arrays.fill(ret, -1.0);
        //bfs with cache
        i=0;
        for(List<String> q : queries) {
            String src=q.get(0);
            String dst =q.get(1);
            if(graph.containsKey(src)&&graph.containsKey(dst)) {
                if(src.equals(dst)){
                    ret[i]=1.0;
                } else {
                    bfs(graph, src, dst);
                    ret[i]=graph.get(src).getOrDefault(dst,-1.0);
                }
            }
            i++;
        }
        return ret;
    }
    static class Node {
        Node(double val, String dst){
            this.val=val;
            this.dst=dst;
        }
        double val;
        String dst;
    }
    private void bfs(Map<String, Map<String, Double>> graph, String src, String dst) {
        if(null!=graph.get(src).get(dst))
            return ;

        Set<String> seen = new HashSet<>();
        LinkedList<Node> que = new LinkedList<>();
        seen.add(src);
        que.addLast(new Node(1.0, src));

        while (que.size()>0) {
            Node cur = que.pollLast();
            seen.add(cur.dst);
            for (Map.Entry<String, Double> entry : graph.get(cur.dst).entrySet()) {
                if (!seen.contains(entry.getKey())) {
                    que.addFirst(new Node(cur.val * entry.getValue(), entry.getKey()));
                }
            }
            if (!cur.dst.equals(src)) {
                //both src and dst , shoud be update
                graph.get(src).put(cur.dst, cur.val);
            }
            if (dst.equals(cur.dst)) {
                graph.get(dst).put(src, cur.val);
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> eqs = new ArrayList<>(2);
        List<String> eq = new ArrayList<>(2);
        eq.add(0,"a");
        eq.add(1,"b");
        eqs.add(eq);
        eq = new ArrayList<>(2);
        eq.add(0,"b");
        eq.add(1,"c");
        eqs.add(eq);
        //[["x1","x2"],["x2","x3"],["x3","x4"],["x4","x5"]]
        //[3.0,4.0,5.0,6.0]
        //[["x1","x5"],["x5","x2"],["x2","x4"],["x2","x2"],["x2","x9"],["x9","x9"]]
        List<List<String>> qs = new ArrayList<>(5);
        List<String> q = new ArrayList<>(2);
        q.add(0,"a");
        q.add(1,"c");
        qs.add(q);

        q = new ArrayList<>(2);
        q.add(0,"a");
        q.add(1,"c");
        qs.add(q);

        q = new ArrayList<>(2);
        q.add(0,"b");
        q.add(1,"a");
        qs.add(q);
        q = new ArrayList<>(2);
        q.add(0,"a");
        q.add(1,"e");
        qs.add(q);
        q = new ArrayList<>(2);
        q.add(0,"a");
        q.add(1,"a");
        qs.add(q);
        q = new ArrayList<>(2);
        q.add(0,"x");
        q.add(1,"x");
        qs.add(q);
        System.out.println(new Solution().calcEquation(eqs, new double[]{2.0, 3.0},qs));
    }
}
