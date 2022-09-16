package zhenyu.sha.rp;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Regional Sum
// Input
// (1) A two-dimensional(N*M) array containing integers.
// (2) Range of the region (r)

// Output
// For each element in the input array,
// compute the sum of all elements around it in the region described above(including itself) and store the sum in the output array.

// Formula : output[i][j] =   Sum(Input[x][y]) , where |i-x| <=r and |j-y| <=r

// Example
// Input_matrix = [
//   [1,1,1,1,1],
//   [1,1,1,1,1],
//   [1,1,1,1,1],
//   [1,1,1,1,1],
// ]

// When r = 1
// output = [
//   [4,6,6,6,4],
//   [6,9,9,9,6],
//   [6,9,9,9,6],
//   [4,6,6,6,4]
// ]

// When r = 2
// output = [
// [9,12,15,12,9],
// [12,16,20,16,12],
// [12,16, 20,16,12],
// [9,12,15,12,9]
// ]
public class Solution {

    public int[][] rangeSum(int [][] input, int r) {
        if(null==input||input.length==0||input[0]==null||input[0].length==0){
            return new int[][]{{}};
        }
        int [][] output = new int[input.length][input[0].length];
        if(r<1){
            return output;
        }
        //init the windowsize
        int cur_sm = 0;
        for (int i=0;i<=r;i++){
            for(int j=0; j<=r; j++) {
                if(i<input.length&&j<input[i].length){
                    cur_sm += input[i][j];
                } else {
                    // TODO better break;
                    break;
                }
            }
        }
        output[0][0]=cur_sm;
        int prevLineFirstWindow = cur_sm;
        for (int i =0; i< input.length; i++) {
            // down direct;
            // slidw from above
            if(i!=0) {
                cur_sm = prevLineFirstWindow;
                for (int cl = -r; cl<=r; cl++) {
                    if(cl>=0&&cl<input[i].length){
                        int l1 = i-r-1;
                        int l2=  i+r;
                        if(l1>=0&&l1<input.length) {
                            cur_sm -= input[l1][cl];
                        }
                        if(l2>=0&&l2<input.length) {
                            cur_sm+= input[l2][cl];
                        }

                    }

                }
                prevLineFirstWindow = cur_sm;
                output[i][0]= cur_sm;
            }

            for (int j =1; j<input[i].length;j++) {
                if (i==0&&j==0){
                    continue;
                }
                // right direct
                for (int l = i-r; l<=i+r;l++) {
                    if (l>=0&&l<input.length){
                        int cl1 = j-r-1;
                        if (cl1>=0&&cl1<input[l].length){
                            cur_sm-=input[l][cl1];

                        }
                        if (j+r>=0&&j+r<input[l].length){
                            cur_sm+=input[l][j+r];

                        }
                    }
                    output[i][j]=cur_sm;
                }

            }

        }
        return output;
    }
    public static void main(String[] args) {
        int[][] case1 = {{1,1,1,1,1}, {1,1,1,1,1}, {1,1,1,1,1}, {1,1,1,1,1}};
        int[][] result = new Solution().rangeSum(case1, 1);
        for (int []line: result) {
            System.out.println();
            for(int c: line){
                System.out.print(c);
                System.out.print(" ");
            }

        }

    }
}
