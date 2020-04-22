package zhenyu.sha.leetcode.q247;
import java.util.*;
class Solution {
    char[] selfMirrors= new char[]{'0','1','8'};

    public List<String> findStrobogrammatic(int n) {

        List<String> result = new LinkedList<>();
        dfs(0, n, new char[(n+1)/2], result);
        return result;
    }
    void dfs(int p, int len, char[] path, List<String> result){
        if(p==path.length) {
            StringBuilder sb = new StringBuilder(len);
            for(int i=0;i<path.length;i++){
                sb.append(path[i]);
            }
            for(int i=path.length-1;i>=0;i--) {
                if(((len&1)==1)&&(i==path.length-1)){
                    continue;
                }
                if(path[i]=='6'){
                    sb.append('9');
                } else if(path[i]=='9') {
                    sb.append('6');
                } else {
                    sb.append(path[i]);
                }
            }
            result.add(sb.toString());
            return;
        }
        for(char mirror :selfMirrors){
            if((len!=1)&&(mirror=='0')&&(p==0)){
                continue;
            }
            path[p]=mirror;
            dfs(p+1, len, path, result);
        }
        if((p!=path.length-1)||((len&1)!=1)){
            path[p] ='6';
            dfs(p+1,len,path, result);
            path[p] ='9';
            dfs(p+1,len,path, result);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findStrobogrammatic(3));
    }
}