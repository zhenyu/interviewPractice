package zhenyu.sha.hackerrank.sherlockvalid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    static String isValid(String s){
        // Complete this function
        List<Short> charCounter = new ArrayList<>(26);
        for(int i = 0; i< s.length(); i++){
            int charIndex=s.indexOf(i)-'a';
            if(null==charCounter.get(charIndex)){
                
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String result = isValid(s);
        System.out.println(result);
    }
}
