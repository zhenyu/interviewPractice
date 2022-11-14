package zhenyu.sha.twohorse.stockquery;

import java.util.Arrays;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        int [] ret = new Solution().getCloseDays(
                new int[]{5, 5,5,5,9,5,5,5,5,5},
                new int[]{1,2,3,4,5,6,7,8,9,10});
        System.out.println(Arrays.toString(ret));
    }
    int[] getCloseDays(int[] dayPrices, int []query) {
        if (null==dayPrices||null==query||dayPrices.length==0||query.length==0) {
            return new int[]{};
        }
        // stack for Left
        int [] leftSmaller = new int[dayPrices.length];
        LinkedList<Integer> monoStack = new LinkedList<>();
        for (int i = dayPrices.length-1;i>=-1;i--) {
            int curValue = i ==-1? Integer.MIN_VALUE:dayPrices[i];
            while (!monoStack.isEmpty()&&dayPrices[monoStack.peekFirst()]>curValue){
                int index = monoStack.pollFirst();
                leftSmaller[index] = i;
            }
            monoStack.addFirst(i);
        }
        // stack for right
        int [] rightSmaller = new int[dayPrices.length];
        monoStack.clear();
        for (int i = 0;i<=dayPrices.length;i++) {
            int curValue = i ==dayPrices.length? Integer.MIN_VALUE:dayPrices[i];
            while (!monoStack.isEmpty()&&dayPrices[monoStack.peekFirst()]>curValue){
                int index = monoStack.pollFirst();
                rightSmaller[index] = i;
            }
            monoStack.addFirst(i);
        }
        int [] ret = new int[query.length];
        for(int i=0; i<query.length;i++) {
            int leftSmall = leftSmaller[query[i]-1];
            int rightSmall = rightSmaller[query[i]-1];
            int cur =-1;
            if(leftSmall!=-1&&rightSmall!=dayPrices.length){
                if (rightSmall-i<i-leftSmall){
                    cur = rightSmall+1;
                } else {
                    cur = leftSmall+1;
                }
            } else if (leftSmall!=-1){
                cur = leftSmall+1;
            } else if (rightSmall!= dayPrices.length){
                cur = rightSmall+1;
            }
            ret[i]=cur;
        }
        return ret;
    }
}
