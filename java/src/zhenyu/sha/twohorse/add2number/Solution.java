package zhenyu.sha.twohorse.add2number;

// Some code
import java.util.*;


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
            result = computeNumberPart(n1, n2,true);
        } else if (n1[0]=='-'&& n2[0]=='-') {
            result = computeNumberPart(n1, n2, true);
        } else if(n1[0]=='-') {
            if (!isABSLarger(n1, n2)) {
                positive = true;
                result = computeNumberPart(n2,n1, false);
            } else {
                result = computeNumberPart(n1, n2, false);
            }
        } else {
            if (isABSLarger(n1, n2)) {
                positive = true;
                result = computeNumberPart(n1,n2, false);
            } else {
                result = computeNumberPart(n2, n1, false);
            }
        }

        if (result==null||result.length()==0) {
            return "0";
        }
        return (positive?"+":"-")+result;
    }

    private static String computeNumberPart(char[] n1, char[] n2, boolean add) {
        LinkedList<Integer> result = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int idx1 = n1.length - 1;
        int idx2 = n2.length - 1;
        int curExtra =0;
        while ((idx1>=0&&Character.isDigit(n1[idx1]))||(idx2>=0)||curExtra!=0) {
            int i1 = (idx1>=0&&Character.isDigit(n1[idx1])?(n1[idx1])-'0':0);
            int i2 = (idx2>=0&&Character.isDigit(n2[idx2])?(n2[idx2]-'0'):0);
            int cur =0;
            if (add) {
                cur=i1+i2+curExtra;
                curExtra = cur/10;
                cur = cur %10;
            } else {
                cur=i1-i2-curExtra;
                if (cur<0) {
                    curExtra =1;
                    cur=10+cur;
                } else {
                    curExtra =0;
                }
            }
            result.addFirst(cur );
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
        String s1 = "101";
        String s2 = "-100";
        System.out.print(addTwoBigNumbers(s1.toCharArray(),s2.toCharArray()));

    }
}
