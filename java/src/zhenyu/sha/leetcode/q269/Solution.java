package zhenyu.sha.leetcode.q269;
import java.util.*;
class Node {
    int degree;
    public char alpha;
    ArrayList<Node> edges;
}
class Solution {
    public String alienOrder(String[] words) {
        ArrayList<Node> graph = new ArrayList<>(26);
        int alphaCount =0;
        for(int i =0; i< words.length;i++) {
            for (int j=i+1; j< words.length;j++) {
                int k =0;
                while (k<words[i].length()&&k<words[k].length()){
                    char first = words[i].charAt(k);
                    char second = words[j].charAt(k);
                    if(first!=second){
                        Node firstNode = graph.get(first-'a');
                        if(null==firstNode){
                            alphaCount++;
                            firstNode = new Node();
                            firstNode.alpha = first;
                            firstNode.degree = 0;
                            firstNode.edges = new ArrayList<>(26);
                            graph.add(first-'a', firstNode);
                        }

                        Node secondNode = graph.get(second-'a');
                        if(null==secondNode){
                            alphaCount++;
                            secondNode = new Node();
                            secondNode.alpha = first;
                            secondNode.degree = 0;
                            secondNode.edges = new ArrayList<>(26);
                            graph.add(second-'a', secondNode);
                        }

                        if(firstNode.edges.get(second-'a')==null){
                            firstNode.edges.add(second-'a', secondNode);
                        }
                        secondNode.degree++;
                    }

                }
            }
        }
        // topoloysort
        List<Character> resuts = new LinkedList<>();
        int visited =0;
        LinkedList<Node> que = new LinkedList<>();
        for(Node node : graph) {
            if (node!=null&&node.degree==0) {
                que.add(node);
            }
        }
        while (que.size()>0) {
            Node current = que.pollFirst();
            visited++;
            resuts.add(current.alpha);
            for(Node n: current.edges){
                if(null!=n){
                    n.degree--;
                    if(n.degree==0){
                        que.addLast(n);
                    }
                }
            }
        }
        if (visited!=alphaCount){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for(char c: resuts){
            sb.append(c);
        }
        return sb.toString();
    }
}
