package zhenyu.sha.leetcode.q227;
import java.util.*;
class Token {
    Token(int type, int val){
        this.type = type;
        this.val = val;
    }
    int type;
    int val;
}
class Solution {
    int i;
    char input[];
    Token cacheToken;
    public int calculate(String s) {
        i = 0;
        input=s.toCharArray();
        cacheToken = null;
        Stack<Token> stack = new Stack<>();
        Token cur = getNext(true);
        while (cur!=null) {
            if(cur.type==0) {
                if(stack.size()>0) {
                    Token next = getNext(false);
                    if(null==next||next.type<=stack.peek().type){
                        Token op  = stack.pop();
                        if(op.type==1) {
                            if(op.val==0){
                                //+
                                cur.val=stack.pop().val+cur.val;
                            } else {
                                cur.val=stack.pop().val-cur.val;
                            }
                        } else {
                            if(op.val==0){
                                //*
                                cur.val=stack.pop().val*cur.val;
                            } else {
                                // div
                                cur.val=stack.pop().val/cur.val;
                            }
                        }
                        continue;

                    }
                }

            }
            stack.push(cur);
            cur=getNext(true);
        }
        return stack.size()>=1?stack.pop().val:0;
    }
    Token getNext(boolean moveForward) {
        if(i>=input.length){
            return null;
        }
        if(cacheToken==null) {
            while (i<input.length&&input[i]==' '){
                i++;
            }
            if(i<input.length) {
                char cur = input[i];
                i++;
                switch (cur) {
                    case '+':
                        cacheToken= new Token(1, 0);
                        break;
                    case '-':
                        cacheToken= new Token(1, 1);
                        break;
                    case '*':
                        cacheToken= new Token(2, 0);
                        break;
                    case '/':
                        cacheToken= new Token(2, 1);
                        break;
                    default: {
                        int val = cur-'0';
                        while (i<input.length&&Character.isDigit(input[i])) {
                            val=val*10+input[i]-'0';
                            i++;
                        }
                        cacheToken= new Token(0, val);
                        break;
                    }
                }

            }

        }
        Token result = cacheToken;
        if(moveForward){
            cacheToken = null;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().calculate("3*5+1"));
    }
}