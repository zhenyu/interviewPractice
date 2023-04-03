package zhenyu.sha.hrt.rotate;

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n+1) / 2; i ++) {
            for (int j = i+1; j <matrix.length-i-1; j++) {
                int temp = matrix[i][j];
                matrix[i][j]=matrix[matrix.length-1-j][i];
                matrix[matrix.length-1-j][i]= matrix[matrix.length-i-1][matrix.length-1-j];
                matrix[matrix.length-1-i][matrix.length-1-j]= matrix[j][matrix.length-1-i];
                matrix[j][matrix.length-1-i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        new Solution().rotate(matrix);
        for(int[] l:matrix) {
            for(int i: l) {
                System.out.print(i+", ");
            }
            System.out.println();
        }
    }
}
