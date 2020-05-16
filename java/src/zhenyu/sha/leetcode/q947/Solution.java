package zhenyu.sha.leetcode.q947;
import java.util.*;

class UF {
    UF(int x, int y) {
        this.x=x;
        this.y=y;
        this.father=this;
    }
    int x;
    int y;
    UF father;
    int size =1;

}
class Solution {


    public int removeStones(int[][] stones) {
        Map<Integer, UF> rowSets = new HashMap<>();
        Map<Integer, UF> colSets = new HashMap<>();

        int ret =0;
        for(int i=0;i<stones.length;i++){
            UF cur = new UF(stones[i][0], stones[i][1]);
            UF rowSet = rowSets.get(cur.x);
            if(null!=rowSet){
                union(rowSet,cur);
            } else {
                rowSets.put(cur.x, cur);
            }
            UF colSet = colSets.get(cur.y);
            if(null!=colSet){
                union(colSet,cur);
            } else {
                colSets.put(cur.y, cur);
            }
        }
        Set<UF> cc = new HashSet<>();
        for(UF set : rowSets.values() ){
            while (set.father!=set){
                set=set.father;
            }
            if(!cc.contains(set)){
                cc.add(set);
                ret +=(set.size-1);
            }
        }
        for(UF set : colSets.values() ){
            while (set.father!=set){
                set=set.father;
            }
            if(!cc.contains(set)){
                cc.add(set);
                ret +=(set.size-1);
            }
        }
        return ret ;
    }
    // make sure the uf1 is not null;
    void union(UF uf1, UF uf2) {

        UF root = uf2;
        UF child =uf1;
        if(uf2.size<uf1.size){
            root =uf1;
            child = uf2;
        }
        while (root.father!=root){
            root = root.father;
        }

        while (child.father!=child){
            child=child.father;
        }
        if(child!=root) {
            child.father=root;
            root.size+=child.size;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeStones(new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}}));
    }
}