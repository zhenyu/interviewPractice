package zhenyu.sha.twohorse.add2number;

// Some code
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



public class Solution {

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

//determine the signs of each strings
//both +
//one + one -

//both -    == -(+)


    public static String addTwoBigNumbers(char[] n1, char[] n2){

        boolean positive = false;
        String result = null;
        if( n1[0]=='+' && n2[0]=='+') {
            positive = true;
            result = addNumberPart(n1, n2);
        } else if (n1[0]=='-'&& n2[0]=='-') {
            result = addNumberPart(n1, n2);
        } else if(n1[0]=='-') {
            if (!isABSLarger(n1, n2)) {
                positive = true;
                result = subNumberPart(n2,n1);
            } else {
                result = subNumberPart(n1, n2);
            }
        } else {
            if (isABSLarger(n1, n2)) {
                positive = true;
                result = subNumberPart(n1,n2);
            } else {
                result = subNumberPart(n2, n1);
            }
        }

        if (result==null||result.length()==0) {
            return "0";
        }
        return positive?"+":"-"+result;
    }

    private static String addNumberPart(char[] n1, char[] n2) {
        LinkedList<Integer> result = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int idx1 = n1.length - 1;
        int idx2 = n2.length - 1;
        int curExtra =0;
        while ((idx1>=0)||(idx2>=0)||curExtra>0) {
            int cur = n1[idx1]-'0'+n2[idx2]-'0'+curExtra;
            result.addFirst( cur %10 );
            curExtra = cur/10;
            idx1--;
            idx2--;
        }

        while (!result.isEmpty()&&result.peekFirst()==0) {
            result.pollFirst();
        }
        while (!result.isEmpty()) {
            sb.append(result.pollFirst());
        }
        return sb.toString();
    }
    private static String subNumberPart(char[] n1, char[] n2) {
        LinkedList<Integer> result = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int idx1 = n1.length - 1;
        int idx2 = n2.length - 1;
        int  preSub = 0;
        while (idx1>=0&&idx2>=0&&Character.isDigit(n1[idx1])&&Character.isDigit(n2[idx2])) {
            int cur = n1[idx1]-preSub-n2[idx2];
            if (cur <0){

            } else {
                cur = 10+cur;
                preSub = 1;
            }
            result.addFirst(cur);
            idx1--;
            idx2--;
        }
        while (idx1>=0&&Character.isDigit(n1[idx1])){
            result.addFirst(n1[idx1]-'0');

        }
        while (idx2>=0&&Character.isDigit(n2[idx2])){
            result.addFirst(n1[idx2]-'0');

        }
        while (!result.isEmpty()&&result.peekFirst()==0) {
            result.pollFirst();
        }
        while (!result.isEmpty()) {
            sb.append(result.pollFirst());
        }
        return sb.toString();
    }
    private static int skipSignAndZero(char[]n ) {
        int idx =0;
        while (!Character.isDigit(n[idx])||n[idx]=='0') {
            idx++;
            if (idx >= n.length) {
                break;
            }
        }
        return idx;
    }
    private static boolean isABSLarger(char[] n1, char[] n2) {
        int idx1 = skipSignAndZero(n1);
        int idx2 =skipSignAndZero(n2);
        int len1 = n1.length-idx1;
        int len2 = n2.length-idx2;
        if(len1>len2) {
            return true;
        }
        if(len1<len2) {
            return false;
        }
        while (idx1<n1.length) {
            if(n1[idx1]>n2[idx2]) {
                return true;
            }
            idx1++;
            idx2++;
        }
        return false;
    }


    public static void main(String[] args) {
        String s1 = "10";
        String s2 = "-10";
        System.out.print(addTwoBigNumbers(s1.toCharArray(),s2.toCharArray()));

    }
}
