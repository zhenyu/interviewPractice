package zhenyu.sha.leetcode.q1057;
import java.util.*;
class Pair implements Comparable<Pair> {
    Pair(int w, int b, int d) {
        this.w=w;
        this.b =b;
        this.d =d;
    }
    int w;
    int b;
    int d;

    @Override
    public int compareTo(Pair o) {
        int ret= this.d-o.d;
        if(ret==0){
            ret=this.w-o.w;
        }
        if(ret==0){
            ret=this.b-o.b;
        }
        return ret;
    }
}
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers==null||workers.length==0)
            return null;
        PriorityQueue<Pair> dists = new PriorityQueue<>();
        //make pair
        for(int i=0;i <workers.length;i++){
            for(int j=0;j<bikes.length;j++){
                dists.add(new Pair(i, j, Math.abs(workers[i][0]-bikes[j][0])+Math.abs(workers[i][1]-bikes[j][1])));
            }
        }
        Set<Integer> occupiedBikes = new HashSet<>();
        int [] ret = new int[workers.length];
        Arrays.fill(ret, -1);
        int wCOunt=0;
        while (wCOunt<workers.length){
            Pair d =dists.poll();
            if(!occupiedBikes.contains(d.b)&&ret[d.w]==-1){
                wCOunt++;
                occupiedBikes.add(d.b);
                ret[d.w]=d.b;
            }
        }
        return ret;
    }
}