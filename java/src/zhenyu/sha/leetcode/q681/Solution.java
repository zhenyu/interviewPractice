package zhenyu.sha.leetcode.q681;
import java.util.*;
class Solution {
    public String nextClosestTime(String time) {
        char[] t= time.toCharArray();
        TreeSet<Character> digits= new TreeSet<>();
        for(char c : t) {
            if(c!=':')
                digits.add(c);
        }
        char[]ret = time.toCharArray();
        for(int i=4;i>0;i++) {
            if(i!=2){
                //check wether is valid large
                //if yes, increase and break;
                char cur=t[i];
                Character cadidate = digits.higher(cur);
                if(null!=cadidate&&isValid(i, cadidate, digits, t)) {
                    ret[i]=cadidate;
                    char smallest = digits.first();
                    for(int j=i+1;j<5;j++){
                        if(j!=2) {
                            ret[j]=smallest;
                        }
                    }
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c: ret){
            sb.append(c);
        }
        return sb.toString();
    }
    boolean isValid(int index, char c, Set<Character> digits, char[]time) {
        if(index==4){
            return c>='0'&&c<='9';
        }
        if(index==3) {
            return (c>='0'&&c<='5');
        }
        if(index==1){
            if (time[0]=='2') {
                return c>='0'&&c<='3';
            }
            return c>='0'&&c<='9';
        }
        return c>='0'&&c<='2';
    }
}