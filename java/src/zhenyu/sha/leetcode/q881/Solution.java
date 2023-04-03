package zhenyu.sha.leetcode.q881;
import java.util.*;
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int ret =0;
        Arrays.sort(people);
        if(people[people.length-1]>limit){
            return 0;
        }
        int i=0;
        int j= people.length-1;
        while(i<=j){

            if(people[j]+people[i]<=limit){
                i++;
                j--;

            }  else {
                j--;
            }
            ret++;
        }
        return ret;
    }
}
