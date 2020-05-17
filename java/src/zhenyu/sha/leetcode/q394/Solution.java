package zhenyu.sha.leetcode.q394;

import java.util.*;
class Solution {
    public String decodeString(String s) {

        if(null==s||s.length()==0)
            return "";
        LinkedList<Character> stack= new LinkedList<>();
        int end =0;
        char [] input= s.toCharArray();
        while (end<input.length){
            char c = input[end];
            if(c==']'){
                LinkedList<Character> cur = new LinkedList<>();
                while (stack.getLast()!='['){
                   char temp = stack.pollLast();
                   cur.addFirst(temp);
                }
                stack.pollLast();
                //try times;
                int times =0;
                int base=1;
                while (stack.size()>0&&Character.isDigit(stack.getLast())){
                    times+=base*(stack.pollLast()-'0');
                    base=base*10;
                }
                for(int i=0;i<times;i++){
                    stack.addAll(cur);
                }
            }else{
                stack.addLast(c);
            }
            end++;
        }
        StringBuilder sb = new StringBuilder();
        for(Character c: stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}