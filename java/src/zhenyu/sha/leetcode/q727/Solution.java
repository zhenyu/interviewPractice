package zhenyu.sha.leetcode.q727;

class Solution {
    public String minWindow(String S, String T) {
        //state,  j, l  end and length of w, value k  end of That T[0, k）is the subsequence of W
        int[]dp1 = new int[S.length()+1];
        int[]dp2 = new int[S.length()+1];
        int minL = Integer.MAX_VALUE;
        int end = 0;

        for(int j=1;j<=S.length();j++) {
            for(int l=1;l<=j;l++) {
                int nextT = dp1[l-1];
                if(nextT< T.length()&& (T.charAt(nextT)==S.charAt(j-1))) {
                    //extend;
                    ++nextT;
                    if(nextT==T.length()&&l<minL) {
                        minL =l;
                        end = j;
                    }
                }
                dp2[l]=nextT;
            }

            int [] temp = dp1;
            dp1=dp2;
            dp2= temp;
        }
        return minL==Integer.MAX_VALUE?"":S.substring(end-minL, end);
    }
    public static void main(String []args) {
        System.out.println(new Solution().minWindow("abcdebdde", "bde"));
    }
}