package zhenyu.sha.leetcode.q411;
import java.util.*;
class Solution {
    public String minAbbreviation(String target, String[] dictionary) {
        boolean noSameLen = true;
        List<Integer> masks = new LinkedList<>();
        for(String w : dictionary) {
            if(w.length()==target.length()){
                noSameLen = false;
                masks.add(getMask(w, target));
            }
        }
        if(noSameLen)
            return ""+target.length();

        //just a place holder
        String ret=target;
        int max=1;
        for(int i=0;i<target.length();i++) {
            max=max<<1;
        }
        for(int i=0;i<max;i++) {
            boolean noConflict= true;
            for(int dicMask: masks) {
                if((dicMask&i)==0){
                    noConflict =false;
                    break;
                }
            }
            if(noConflict) {
                //try to update
                String candidate = outPutAbbr(target, i);
                if(candidate.length()<ret.length()){
                    ret = candidate;
                }
            }

        }
        return ret;
    }
    String outPutAbbr(String w, int mask) {
        StringBuilder ret = new StringBuilder();
        int count =0;
        for(int i=0;i<w.length();i++) {
            if((1&mask)!=0){
                if(count!=0) {
                    ret.append(count);
                    count=0;
                }
                ret.append(w.charAt(i));
            } else {
                ++count;
            }
            mask=mask>>1;
        }
        if(count!=0)
            ret.append(count);
        return ret.toString();
    }
    int getMask(String w1, String w2) {
        int mask = 0;
        for(int i= w1.length()-1;i>=0;i--) {
            mask = mask<<1;
            if(w1.charAt(i)!=w2.charAt(i)){
                mask = mask+1;
            }
        }
        return mask;
    }

    public static void main(String[] args) {
        //"apple"
        //["blade","plain","amber"]
        System.out.println(new Solution().minAbbreviation("apple", new String[]{"blade","plain","amber"}));
    }
}