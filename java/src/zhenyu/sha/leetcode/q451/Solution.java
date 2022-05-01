package zhenyu.sha.leetcode.q451;

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

        while (index<code.length()) {
            Token t = getNext(code);
            if(null==t){
                return false;
            }
            if (t.type ==Type.START) {
                stack.push(t);
            } else if (t.type == Type.CLOSE) {
                if(stack.isEmpty()){
                    return false;
                }
                Token start = stack.pop();
                if(start.content.compareTo(t.content)!=0){
                    return false;
                }
            }
            // we do nothing to the content token
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
            if(code.charAt(index)=='!') {
                result.type = Type.CONTENT;

                // parse CDATA
                index++;
                if(index+ "[CDATA".length()>code.length()){
                    return null;
                }
                if (code.substring(index, index+"[CDATA".length()).compareTo("[CDATA")!=0){
                    return null;
                }
                index=index+"[CDATA".length();
                while (index<code.length()) {
                    if (code.charAt(index)==']'&&index+1<code.length()&&code.charAt(index+1)==']'){
                        index++;
                        break;
                    }
                    index++;
                }
                // we should return
                return result;
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
            //skip the '>'
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
        System.out.println(s.isValid("<A>  <B> </A>   </B>"));
    }
}