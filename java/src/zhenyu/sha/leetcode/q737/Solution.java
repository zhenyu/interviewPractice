package zhenyu.sha.leetcode.q737;
import java.util.*;
class UF {
    UF(){
        father = this;
    }
    UF getRoot(){
        UF root = this;
        while (root.father!=root){
            root=root.father;
        }
        return root;
    }
    UF father;
}
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if(null==words1){
            return null==words2;
        }
        if(words1.length!=words2.length)
            return false;
        boolean ret = true;
        Map<String, UF> unionSets = new HashMap<>();
        for(List<String> similars: pairs) {
            UF prev =null;
            for(String cur: similars) {
                UF curUF = unionSets.getOrDefault(cur, new UF());
                if(prev!=null) {
                    union(prev, curUF);
                }
                unionSets.put(cur, curUF);
                prev = curUF;
            }
        }
        for(int i=0;i<words1.length;i++){
            if(words1[i].equals(words2[i]))
                continue;
            UF uf1=unionSets.get(words1[i]);
            UF uf2=unionSets.get(words2[i]);
            if(uf1==null||uf2==null){
                ret=false;
                break;
            }
            if(uf1.getRoot()!=uf2.getRoot()){
                ret= false;
                break;

            }
        }
        return ret;
    }
    void union(UF uf1, UF uf2) {
        UF root=uf1.getRoot();
        UF child = uf2.getRoot();
        if(root==child)
            return;
        while (uf2.father!=root){
            UF temp = uf2.father;
            uf2.father=root;
            uf2 = temp;
        }
        while (uf1.father!=root){
            UF temp = uf1.father;
            uf1.father=root;
            uf1 = temp;
        }
    }
}