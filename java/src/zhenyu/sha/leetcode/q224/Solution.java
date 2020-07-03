package zhenyu.sha.leetcode.q224;
import java.util.*;
class Token {
    Token(int type, int value){
        this.type=type;
        this.value=value;
    }
    // 0 number, 1, +, 2, -1, 3 (, 4, )
    int type;
    int value;
}
class Solution {
    int i =0;
    char[] input;
    public int calculate(String s) {
        input =s.toCharArray();
        i = 0;
        Stack<Token> stack = new Stack<>();
        Token token = getNextToken();
        while (token!=null){
            if(token.type==0) {
                if(stack.size()>0) {
                    Token pre= stack.peek();
                    if(pre.type==1) {
                       stack.pop();
                       token.value = token.value+stack.pop().value;
                    } else if (pre.type==2) {
                        stack.pop();
                        token.value = stack.pop().value-token.value;
                    }
                }
                stack.push(token);

            } else if (token.type ==4){
                token = stack.pop();
                stack.pop();
                continue;
            } else {
                stack.push(token);
            }
            token = getNextToken();
        }

        return stack.size()>=1?stack.pop().value:0;
    }

    Token getNextToken() {
        while (i<input.length&&input[i]==' '){
            i++;
        }
        if (i>=input.length) {
            return null;
        }
        char cur = input[i];
        i++;
        switch (cur) {
            case '(':
                return new Token(3, 0);
            case ')':
                return new Token(4, 0);
            case '+':
                return new Token(1, 0);
            case '-':
                return new Token(2, 0);
            default:
                break;
        }
        int val = cur -'0';
        while (i<input.length&&Character.isDigit(input[i])){
            val=val*10+input[i]-'0';
            i++;
        }
        return new Token(0, val);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().calculate(" 2-1 + 2 "));
    }
}