package zhenyu.sha.leetcode.q684;
class Union {
    Union(int father, int weight) {
        this.father = father;
        this.weight = weight;
    }
    int father;
    int weight;
}
class Solution {
    Union [] union;
    public int[] findRedundantConnection(int[][] edges) {
        int [] ret = null;
        union = new Union[edges.length+1];
        for(int i=0; i< union.length;i++) {
            union[i]= new Union(i, 1);
        }
        for(int []e : edges) {
            int u0= getFather(e[0]);
            int u1 = getFather(e[1]);
            if(u0==u1) {
                ret = e;
                break;
            }
            unionFather(u0, u1);
        }
        return  ret;
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
}