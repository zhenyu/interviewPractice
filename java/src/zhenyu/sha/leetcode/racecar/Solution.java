package zhenyu.sha.leetcode.racecar;

import java.util.Arrays;

/**
 * 本参考程序来自九章算法，由 @九章算法助教团队 提供。版权所有，转发请注明出处。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，
 * - Big Data 项目实战班，算法面试高频题班, 动态规划专题班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
 */

class Solution {
    public int racecar(int target) {
        int[] dp = new int[target + 3];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; dp[1] = 1; dp[2] = 4;

        for (int t = 3; t <= target; ++t) {
            int k = 32 - Integer.numberOfLeadingZeros(t);
            if (t == (1<<k) - 1) {
                dp[t] = k;
                continue;
            }
            for (int j = 0; j < k-1; ++j)
                dp[t] = Math.min(dp[t], dp[t - (1<<(k-1)) + (1<<j)] + k-1 + j + 2);
            if ((1<<k) - 1 - t < t)
                dp[t] = Math.min(dp[t], dp[(1<<k) - 1 - t] + k + 1);
        }

        return dp[target];
    }
}
