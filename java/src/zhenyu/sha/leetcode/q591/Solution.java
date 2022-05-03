package zhenyu.sha.leetcode.q591;

import java.util.*;
enum Type {
    START,
    CLOSE,
    CONTENT,
}
class Token {
    Type type;
    String content;
}
class Solution {
    int index  =0;
    LinkedList<Token> stack = new LinkedList<>();
    public boolean isValid(String code) {
        if (code.length()==0){
            return false;
        }
        Token first = null;
        while (index<code.length()) {
            Token t = getNext(code);
            if(null==t){
                return false;
            }
            if (t.type ==Type.START) {
                if (null == first) {
                    first = t;
                }
                stack.push(t);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (t.type == Type.CLOSE) {
                    if (stack.peek().content.compareTo(t.content)!=0){
                        return false;
                    }
                    stack.pop();
                    if (stack.isEmpty() && first.content.compareTo(t.content)!=0){
                        return false;
                    }
                }
            }

        }

        return stack.isEmpty();
    }
    public Token getNext(String code) {
        Token result = new Token();
        if (index>=code.length()) {
            return null;
        }
        if (code.charAt(index)=='<') {
            index++;
            if(index==code.length()){
                return null;
            }
            if(code.charAt(index)=='!') {
                result.type = Type.CONTENT;

                // parse CDATA
                index++;
                if(index+ "[CDATA[".length()>code.length()){
                    return null;
                }
                if (code.substring(index, index+"[CDATA[".length()).compareTo("[CDATA[")!=0){
                    return null;
                }
                index=index+"[CDATA[".length();
                boolean closed = false;
                while (index<code.length()) {
                    if (code.charAt(index)==']'&&index+2<code.length()&&code.charAt(index+1)==']'&&code.charAt(index+2)=='>'){
                        index=index+2;
                        closed= true;
                        break;
                    }
                    index++;
                }
                // we should return
                return closed? result:null;
            } else if (code.charAt(index)=='/') {
                result.type=Type.CLOSE;
                index++;
            } else  {
                result.type= Type.START;
            }
            //valid  the name
            int startIndex = index;
            while (index<code.length()&&code.charAt(index)!='>') {
                if(!Character.isUpperCase(code.charAt(index))){
                    return null;
                }
                if(index-startIndex+1>9){
                    return null;
                }
                index++;
            }
            if (index>=code.length()){
                return null;
            }
            //skip the '>'
            if(startIndex==index){
                return null;
            }
            result.content=code.substring(startIndex, index);
            index++;

        } else {
            while (index<code.length()&&code.charAt(index)!='<'){
                index++;
            }
            result.type= Type.CONTENT;

        }
        return result;

    }
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isValid("<A><A></A></A>"));
    }
}