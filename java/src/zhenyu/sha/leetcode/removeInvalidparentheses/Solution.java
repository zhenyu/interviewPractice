package zhenyu.sha.leetcode.removeInvalidparentheses;

import java.util.*;

class Solution {
    private Set<String> validSet = new HashSet();
    private LinkedList<Character> express = new LinkedList<>();
    private String input;
    private int size =0;
    int leftCount =0;
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new LinkedList<>();
        input = s;
        visitBackTrace(0);
        if(validSet.isEmpty()) {
            result.add("");
        }
        for(String validExpress: validSet){
            result.add(validExpress);
        }
        return result;
    }
    private void visitBackTrace(int index){
        if(express.size()+input.length()-index < size)
            return;
        if(input.length()==index){
            if(express.size()>=size && leftCount == 0) {
                if(express.size() > size) {
                    size = express.size();
                    validSet.clear();
                }
                StringBuilder sb = new StringBuilder();
                for (char s: express)
                    sb.append(s);
                validSet.add(sb.toString());
            }
        } else {

            if(input.charAt(index) == '(') {
                //with
                leftCount++;
                express.addLast(input.charAt(index));
                visitBackTrace(index+1);
                express.removeLast();
                leftCount--;

                //without current
                visitBackTrace(index+1);

            } else if(input.charAt(index) == ')') {
                //with
                if(leftCount > 0) {
                    leftCount --;
                    express.addLast(input.charAt(index));
                    visitBackTrace(index+1);
                    express.removeLast();
                    leftCount++;
                }
                //without current
                visitBackTrace(index+1);
            } else{
                express.addLast(input.charAt(index));
                visitBackTrace(index+1);
                express.removeLast();
            }

        }
    }

}