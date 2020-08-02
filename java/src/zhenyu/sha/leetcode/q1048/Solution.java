package zhenyu.sha.leetcode.q1048;
import java.util.*;
class Solution {
    int[] maxDep ;
    public int longestStrChain(String[] words) {
        if(null==words||words.length==0){
            return 0;
        }
        //build the graph
        int[][]graph = new int[words.length][words.length];
        for(int i=0;i< words.length;i++) {
            for(int j=0;j< words.length;j++){
                if(words[i].length()+1==words[j].length()){
                    int t =0;
                    boolean misMatch = false;
                    int k=0;
                    while (k<words[i].length()&&t< words[j].length()){
                        if (words[i].charAt(k)!=words[j].charAt(t)){
                            if(k!=t){
                                misMatch =true;
                                break;
                            } else {
                                t++;
                            }
                        } else {
                            k++;
                            t++;
                        }
                    }
                    if(!misMatch){
                        graph[i][j]=1;
                    }
                }
            }
        }


        maxDep = new int[words.length];
        Arrays.fill(maxDep, -1);
        for(int i=0;i< words.length;i++) {
            dfs(i, graph);
        }
        int max =0;
        for(int i: maxDep){
            max=Math.max(max, i);
        }
        return max+1;
    }
    void dfs(int node, int[][]graph){
        if(maxDep[node]<0){
            int max = 0;
            for(int i= 0; i< graph[node].length;i++){
                if(graph[node][i]==1){
                    dfs(i, graph);
                    max=Math.max(max, maxDep[i]+1);
                }
            }
            maxDep[node]=max;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));
    }
}