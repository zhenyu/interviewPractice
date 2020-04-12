package zhenyu.sha.leetcode.q294;
import java.util.*;
class Solution {
    Map<Integer, Boolean> mustWin = new HashMap<>();
    public boolean canWin(String s) {
        if(null==s||s.length()<2)
            return false;
       char[]input = s.toCharArray();
       mustWin.clear();
       boolean ret= mustWin(input);
       return ret;
    }
    int getState(char[] state){
        int ret =0;
        for(int i=0;i<state.length;i++){
            ret=(ret<<1);
            if(state[i]=='+') {
                ret=ret|1;
            }
        }
        return ret;
    }
    boolean mustWin(char[] input) {
        int state = getState(input);
        if(null!=mustWin.get(state))
            return mustWin.get(state);
        int end = 2;
        while (end<=input.length) {
            if(input[end-1]=='+'&&input[end-2]=='+') {
                //change and try
                char a = input[end-1];
                char b = input[end-2];
                input[end-1] ='-';
                input[end-2] ='-';
                boolean win = mustWin(input);
                //recovery
                input[end-1] =a;
                input[end-2] =b;
                if(!win) {
                    mustWin.put(state,true);
                    return  true;
                }
            }
            end++;
        }
        mustWin.put(state, false);
        return false;
    }

    public static void main(String[] args) {
        boolean solution = new Solution().canWin("+++++");
        System.out.println(solution);
    }
}
