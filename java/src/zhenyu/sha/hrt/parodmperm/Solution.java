package zhenyu.sha.hrt.parodmperm;
import java.util.*;
public class Solution {
    int waystoParom(String input) {
        int ret =0;
        int[] counterMap = new int[26];
        Arrays.fill(counterMap, 0);
        for( char c: input.toCharArray()){
            counterMap[c-'a'] +=1;
        }
        int oddCounter =0;
        for(int counter: counterMap) {
            if(1==counter%2) {
                oddCounter++;
            }
        }
        if(oddCounter ==0) {
            ret=27;
        } else if (oddCounter<=2) {
            ret =2;
        }
        return ret;
    }
}
