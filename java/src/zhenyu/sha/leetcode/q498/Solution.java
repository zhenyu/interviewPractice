package zhenyu.sha.leetcode.q498;
import java.util.*;
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {

        int n = mat.length;
        int[] result = new int[n*n];
        int k  = 0;
        for(int j=0; j<n; j++) {
            int i =0;
            int c = j;
            while ( c>=0 ) {
                result[k]=mat[i][c];
                i++;
                c--;
                k++;
            }
        }
        for( int i = 1; i<n;i++) {
            int r = i;
            int j = n-1;
            while (r<n){
                result[k]=mat[r][j];
                k++;
                r++;
                j--;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().findDiagonalOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }
}

