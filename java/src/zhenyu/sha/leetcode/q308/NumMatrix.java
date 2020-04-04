package zhenyu.sha.leetcode.q308;

class NumMatrix {
    int [][] oldVals;
    int[][] BIT;
    public NumMatrix(int[][] matrix) {
        this.oldVals = matrix;
        int colSize = 0;
        int rowSize =0;
        if(null!=matrix&&matrix.length>0){
            rowSize = matrix.length;
            if(null!=matrix[0]&&matrix[0].length>0){
                colSize = matrix[0].length;
            }
        }
        BIT = new int[rowSize+1][colSize];
        for(int i=0; i<oldVals.length;i++) {
            increaseRow(i+1, oldVals[i]);
        }
    }
    private void increaseRow(int row, int[] rowVals) {
         while (row<BIT.length){
             for(int j=0;j<rowVals.length;j++) {
                 BIT[row][j]+=rowVals[j];
             }
             row+=row&(-row);
         }
    }
    public void update(int row, int col, int val) {
        int incVal = val-oldVals[row][col];
        oldVals[row][col]= val;
        row+=1;
        while (row<BIT.length) {
            BIT[row][col]+=incVal;
            row+=row&(-row);
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        row2+=1;
        int ret = 0;
        while (row1>0|row2>0) {
            for(int c=col1;c<=col2;c++) {
                ret += BIT[row2][c]-BIT[row1][c];
            }
            row1-=row1&(-row1);
            row2-=row2&(-row2);
        }
        return ret;
    }
}