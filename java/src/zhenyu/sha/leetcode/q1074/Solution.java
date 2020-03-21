package zhenyu.sha.leetcode.q1074;

class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rowCount = matrix.length;
        if(rowCount==0)
            return 0;
        int colCount = matrix[0].length;
        if(colCount==0)
            return 0;
        int sum[][] = new int[rowCount+1][colCount+1];

        //first row
        for(int i =0; i<rowCount; i++) {
            int rowCum =0;
            for(int j=0; j<colCount;j++) {
                rowCum+=matrix[i][j];
                sum[i+1][j+1]=sum[i][j+1]+rowCum;
            }
        }
        int found =0;
        //iterater
        for(int m =1 ;m <=rowCount;m++) {
            for (int n=1; n<=colCount;n++ ) {
                for (int i=m-1;i<rowCount;i++) {
                    for (int j=n-1;j<colCount;j++) {
                        int curSum = sum[i+1][j+1]-sum[i+1-m][j+1]-sum[i+1][j+1-n]+sum[i+1-m][j+1-n];
                        if(curSum==target)
                            found++;
                    }
                }
            }

        }
        return found ;
    }
}