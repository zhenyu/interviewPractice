package zhenyu.sha.leetcode.q85;

import java.util.*;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int [] h = new int[matrix[0].length+1];
        int ret = 0;
        for(int i =0; i<matrix.length;i++) {
            for(int j =0 ;j<matrix[i].length;j++){
                if(matrix[i][j]=='0') {
                    h[j]=0;
                } else {
                    h[j]=h[j]+1;
                }
            }
            ret =Math.max(ret, rec(h));
        }
        return ret;
    }
    int rec(int[] h) {
        int ret =0;
        Stack<Integer> stack = new Stack<>();
        int i =0;
        while (i<h.length) {
            int cur = h[i];
            int rightMost = stack.peek();
            while (!stack.isEmpty()&&h[stack.peek()]>cur) {
                int peak= stack.pop();
                ret = Math.max(ret, (rightMost-peak+1)*h[peak]);
            }
            stack.push(i);
            i++;
        }

        return ret;
    }
}
