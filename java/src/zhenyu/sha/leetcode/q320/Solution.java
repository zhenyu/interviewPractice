package zhenyu.sha.leetcode.q320;
import java.util.*;
class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> ret = new LinkedList<>();
        dfs(word.toCharArray(), 0, 0,  ret, new StringBuilder());
        return ret;

    }
    void dfs(char[]input, int curP, int preArL, List<String> ret, StringBuilder sb) {
        if(curP>input.length){
            if(preArL>0){
                sb.append(preArL);
            }
            ret.add(sb.toString());
            return;
        }
        int preSbLen =sb.length();
        //dont abrr current
        if(preArL>0){
            sb.append(preArL);
        }
        sb.append(input[curP]);
        dfs(input, curP+1, 0, ret, sb);
        // abbrr it
        sb.setLength(preSbLen);
        dfs(input, curP+1, preArL+1, ret,sb);
    }
}