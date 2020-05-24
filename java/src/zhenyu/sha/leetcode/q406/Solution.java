package zhenyu.sha.leetcode.q406;
import java.util.*;
class Node implements Comparable<Node> {
    int h;
    int i;
    Node(int h, int i) {
        this.h=h;
        this.i=i;
    }
    @Override
    public int compareTo(Node o) {
        int ret = this.h-o.h;
        if(0==ret){
            ret = o.i-this.i;
        }
        return ret;
    }
}
class Solution {

    public int[][] reconstructQueue(int[][] people) {
        ArrayList<Node> nodes = new ArrayList<>(people.length);
        for(int i=0; i<people.length;i++){
            nodes.add(new Node(people[i][0],people[i][1]));
        }
        Collections.sort(nodes);
        int [][] ret = new int[people.length][];
        for(Node node : nodes){
            int largerCount =0;
            for(int i=0;i<ret.length;i++) {
                if(largerCount==node.i&&ret[i]==null){
                    ret[i]=new int[]{node.h, node.i};
                    break;
                }
                if(null==ret[i]){
                    largerCount++;
                }
            }
        }
        return ret;
    }
}