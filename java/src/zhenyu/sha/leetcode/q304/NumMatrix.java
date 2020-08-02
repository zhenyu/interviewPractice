package zhenyu.sha.leetcode.q304;

class NumMatrix {
    int [][] cum ;
    public NumMatrix(int[][] matrix) {
        int m = null==matrix?0:matrix.length;
        int n = (m==0||null==matrix[0])?0:matrix[0].length;
        cum = new int[m+1][n+1];
        for( int i =1; i<=m;i++) {
            for(int j=1;j<=n;j++) {
                cum[i][j]=cum[i][j-1]+matrix[i-1][j-1];
            }
            for(int j=1;j<=n;j++) {
                cum[i][j]+=cum[i-1][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
         row1++;
         col1++;
         row2++;
         col2++;
         return cum[row2][col2]-cum[row2][col1-1]-cum[row1-1][col2]+cum[row1-1][col1-1];
    }

    public static void main(String[] args) {
        new NumMatrix(new int[0][0]);
    }
}
