package zhenyu.sha.leetcode.q833;
import java.util.*;
class Node implements Comparable<Node>{
    int i;
    String s;
    String t;
    Node(int i, String s, String t) {
        this.i =i;
        this.s = s;
        this.t= t;
    }
    public int compareTo(Node other) {
        long ret=(long)this.i-other.i;
        return ret==0?0:ret>0?1:-1;
    }
}
class Solution {

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        if(indexes==null||indexes.length==0)
            return S;
        ArrayList<Node> toReplace = new ArrayList<>(indexes.length);
        for(int i=0; i<indexes.length;i++){
            toReplace.add(new Node(indexes[i], sources[i], targets[i]));
        }
        Collections.sort(toReplace);
        StringBuilder sb = new StringBuilder();
        int start =0;
        char[] input = S.toCharArray();
        int rIndex = 0;
        while (start<input.length&&rIndex<toReplace.size()) {
            if(start!=toReplace.get(rIndex).i){
                sb.append(input[start]);
                start++;
            } else {
                boolean found =true;
                String source =toReplace.get(rIndex).s;
                for(int i=0;i<source.length();i++){
                    if(input[start+i]!=source.charAt(i)){
                        found=false;
                        break;
                    }
                }
                if(found) {
                    sb.append(toReplace.get(rIndex).t);
                    start+=source.length();
                }else {
                    sb.append(input[start]);
                    start++;
                }
                rIndex++;
            }
        }
        while (start<input.length){
            sb.append(input[start]);
            start++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //indexes
        //"vmokgggqzp"
        //[3,5,1]
        //["kg","ggq","mo"]
        //["s","so","bfr"]
        System.out.println(new Solution().findReplaceString("vmokgggqzp", new int[]{3,5,1},new String[]{"kg","ggq","mo"}, new String[]{"s","so","bfr"}));
    }
}