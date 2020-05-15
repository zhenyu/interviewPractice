package zhenyu.sha.leetcode.q1088;
import java.util.*;
class Solution {
    Map<Integer, Integer> mirros = new HashMap<>();
    Solution() {
        mirros.put(1,1);
        mirros.put(6,9);
        mirros.put(8,8);
        mirros.put(9,6);
    }
    public int confusingNumberII(int N) {
        int counter =0;
        int len =0;
        int target = N;
        while (N%10>0){
            len++;
            N=N/10;
        }
        for(int l =1; l<len--; l++) {
            counter+=count(l, false);
        }
        //the last
        counter+=counterLess(len, target);
        return counter;
    }
    public int counterLess(int len, int target) {
        int counter =0;
        if(len==1) {
            if(6<=target){
                counter++;
            }
            if(9<=target){
                counter++;
            }
        } else {
            int tTail = target%10;
            int div =1;
            for(int i =0; i<len;i++) {
                div = 10*div;
            }
            int tLead = target/div;
            //TODO
            List<Integer> tails = new ArrayList<>();
            for(int lead :mirros.keySet()) {
                if(lead<tLead) {
                    // 0?
                    counter+= 3*count(len-2, true);
                } else if (lead == tLead) {
                    for(int tail: tails) {
                        if(tail<tTail) {
                            counter+=count(len-2, true);
                        } else {
                            
                        }
                    }
                }
            }
        }
        return counter;
    }
    // restrict true means can not be equal, while false loose the condition
    // TODO reduce to one boolean , inner should be enough
    public int count(int len,  boolean inner) {
        int counter =0;
        if(len==0) {
            counter =1;
        } else if(len==1){
            counter = !inner?2:5;

        } else {
            int innerCounter = (count(len-2, true));
            counter+=4*4*innerCounter; //leading zero is not allowed and tail can not be your self
            if(inner){
                //leading zero, and mirror allowed
                counter+=(5+4)*innerCounter;
            }
        }
        return counter;
    }
}