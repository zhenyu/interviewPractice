package zhenyu.sha.leetcode.q685;

import java.util.*;
class Union {
    Union(int father, int weight) {
        this.father = father;
        this.weight = weight;
    }
    int father;
    int weight;
}

class Solution {
   Union[] union;
    public int[] findRedundantDirectedConnection(int[][] edges) {

        int[] ans1 = null;
        int[] ans2 = null;
        union = new  Union[edges.length+1];
        int[]fathers = new int[edges.length+1];

        for(int i=0; i< union.length;i++) {
            union[i]= new  Union(i, 1);
        }
        for(int []e : edges) {
            if(fathers[e[1]]>0){
                ans1 = new int[]{fathers[e[1]], e[1]};
                ans2 = new int[]{e[0], e[1]};
                e[0]=0;
                e[1]=0;
                break;
            }
            fathers[e[1]] = e[0];

        }
        for(int []e : edges) {
            if(e[0]==0||e[1]==0)
                continue;
            int u0= getFather(e[0]);
            int u1 = getFather(e[1]);
            if(u0==u1) {
                if(null!=ans1) {
                    return ans1;
                }
                return e;
            } else {
                unionFather(u0, u1);
            }
        }
        return ans2;
    }
    int getFather(int i) {
        while (union[i].father!=i){
            i = union[i].father;
        }
        return i;
    }
    void unionFather(int u1, int  u2){
        Union uion1 = union[u1];
        Union uion2 = union[u2];
        if(uion1.weight>uion2.weight){
            uion2.father=u1;
        } else {
            uion1.father = u2;
            if(uion2.weight==uion1.weight){
                uion2.weight++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findRedundantDirectedConnection(new int[][]{{1,2},{1,3},{2,3}}));
    }
}