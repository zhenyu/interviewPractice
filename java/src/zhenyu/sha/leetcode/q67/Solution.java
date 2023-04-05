package zhenyu.sha.leetcode.q67;
import java.util.*;
class Solution {
    public String addBinary(String a, String b) {
        char[] n1 =  a.toCharArray();
        char[]n2 = b.toCharArray();
        LinkedList<Character> ret = new LinkedList<>();
        int i=a.length()-1;
        int j=b.length()-1;
        int prev =0;
        int r =0;
        while(i>=0||j>=0){
            r= (i>=0?n1[i]-'0':0)+(j>=0?n2[j]-'0':0)+prev;
            prev = r/2;
            ret.addFirst((char)(r%2+'0'));
            i--;
            j--;
        }
        if(prev!=0) {
            ret.addFirst((char)(prev%2+'0'));
        }
        StringBuilder sb = new StringBuilder();
        for(char c: ret){
            sb.append(c);
        }
        return sb.toString();
    }
}