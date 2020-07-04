package zhenyu.sha.leetcode.q1249;
import java.util.*;
class Solution {
    public String minRemoveToMakeValid(String s) {

        char [] input = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<>();
        int i =0;
        // unpair left
        int count = 0;
        while (i< input.length) {
            if(input[i]==')') {
                if(count<=0){
                    i++;
                    continue;
                } else {
                    count--;
                }
            } else if(input[i]=='(') {
                count ++;
            }
            stack.addLast(input[i]);
            i++;
        }
        LinkedList<Character> buffer = new LinkedList<>();
        while (stack.size()>0){
            char c = stack.pollLast();
            if(c=='('&&count>0) {
                count--;
            } else {
                buffer.addFirst(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c: buffer) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minRemoveToMakeValid("())()((("));
    }
}