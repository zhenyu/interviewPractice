package zhenyu.sha.leetcode.q1289;

class Solution {
    public int minFallingPathSum(int[][] arr) {
        int rawCount = arr.length;
        int colCount = arr[0].length;
        int[] dp = new int[colCount];
        int[] nextPre = new int[colCount];
        for (int i = 0; i < rawCount; i++) {

            for (int j = 0; j < colCount; j++) {
                nextPre[j] = dp[j] + arr[i][j];
            }
            fillMinExceptMe(nextPre, dp);
        }
        int ret = Integer.MAX_VALUE;
        for (int j = 0; j < colCount; j++) {
            if (dp[j] < ret)
                ret = dp[j];
        }
        return ret;
    }

    private void fillMinExceptMe(int[] values, int[] target) {
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            target[i] = minVal;
            if (values[i] < minVal) {
                minVal = values[i];
            }
        }
        minVal = Integer.MAX_VALUE;
        for (int i = values.length - 1; i > -1; i--) {
            if (minVal < target[i]) {
                target[i] = minVal;
            }
            if (values[i] < minVal) {
                minVal = values[i];
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minFallingPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }
}
