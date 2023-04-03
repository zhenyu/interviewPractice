package zhenyu.sha.leetcode.q48;

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n+1) / 2; i ++) {
            for (int j = i; j <matrix.length-i-1; j++) {
                int temp = matrix[i][j];
                matrix[i][j]=matrix[matrix.length-1-j][i];
                matrix[matrix.length-1-j][i]= matrix[matrix.length-i-1][matrix.length-1-j];
                matrix[matrix.length-1-i][matrix.length-1-j]= matrix[j][matrix.length-1-i];
                matrix[j][matrix.length-1-i] = temp;
            }
        }
    }
}