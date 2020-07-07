package zhenyu.sha.leetcode.q440;
import java.util.*;
class Solution {
    public int findKthNumber(int n, int k) {
        if(k==1)
            return 1;
        int t = n;
        int base =1;
        int unit =0;
        while (t>0){
            unit+=base;
            base*=10;
            t/=10;
        }

        LinkedList<Integer> answer = new LinkedList<>();
        do{
            k = k%unit;
            answer.addLast(k+1);
        } while (k>0);
        int ret =0;

        for(Integer i: answer){
            ret+=i*base;
            base/=10;
        }
        return  ret;
    }
}