package zhenyu.sha.leetcode.q772;

import java.util.*;
class Solution {

    public int calculate(String s) {
        Stack<Integer> valueStack = new Stack<>();
        Stack<Integer> signStack = new Stack<>();
        int sign = 1;
        int number = 0;
        int index = 0;
        int result =0;
        while (index<s.length()) {
            char current = s.charAt(index);
            if(')'==current) {
                number=result+sign*number;
                // pop sign
                sign = signStack.pop();
                result=valueStack.pop();
                result+=sign*number;
                number =0;
                sign =1;
            } else if ('('==current) {
                //push
                valueStack.push(result);
                signStack.push(sign);
                //reset
                number =0;
                sign = 1;
                result =0;
            } else if('+'==current) {
                result+=sign*number;
                sign=1;
                number =0;
            } else if ('-'==current) {
                result+=sign*number;
                number =0;
                sign =-1;
            } else if (Character.isDigit(current)) {
                number=number*10+current-'0';
            }
            index++;
        }

        result += sign*number;

        //value stack
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().calculate("1-(     -2)"));
    }
}
