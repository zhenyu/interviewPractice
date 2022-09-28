package zhenyu.sha.leetcode.q48;

class Solution {
    public void rotate(int[][] matrix) {
        // flip horiz
        for (int j = 0; j< matrix[0].length; j++) {
            for(int i = 0; i<matrix.length/2;i++) {
                int temp  = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-i][j];
                matrix[matrix.length-1-i][j] = temp;
            }
        }
        // flip by triangle
        for (int i = 0; i < matrix.length;i++) {
            int x1 = i;
            int y1 = 0;
            int x2 = 0;
            int y2 = i;
            while (x1>=x2&&y1<=y2){
                int temp = matrix[x1][y1];
                matrix[x1][y1] = matrix[x2][y2];
                matrix[x2][y2] = temp;
                x1--;
                y1++;
                x2++;
                y2--;
            }
        }
        for (int j = 1; j < matrix[0].length;j++) {
            int x1 = matrix.length-1;
            int y1 = j;
            int x2 = j;
            int y2 = matrix.length-1;
            while (x1>=x2&&y1<=y2){
                int temp = matrix[x1][y1];
                matrix[x1][y1] = matrix[x2][y2];
                matrix[x2][y2] = temp;
                x1--;
                y1++;
                x2++;
                y2--;
            }
        }
    }
}