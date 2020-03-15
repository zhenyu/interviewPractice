package zhenyu.sha.leetcode.encodeshorteststring;

/**
 * 本参考程序来自九章算法，由 @九章算法助教团队 提供。版权所有，转发请注明出处。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，
 * - Big Data 项目实战班，算法面试高频题班, 动态规划专题班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
 */

class Solution {
    /**
     * @param s: a string
     * @return: return a string
     */
//    区间dp, O(n^3)
    public String encode(String s) {
        // write your code here
        int n = s.length();
        String[][] dp = new String[n][n];
        for (int len = 1; len <= n; ++len) {        //枚举区间长度
            for (int i = 0; i + len - 1 < n; ++i) {        //枚举起点
                int j = i + len - 1;                    //计算终点
                dp[i][j] = s.substring(i, j + 1);
                for (int k = i; k < j; ++k) {            //利用k分割出左部分和右部分
                    if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {  //如果左右两侧长度和小于当前答案就更新
                        dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                }
                String now = s.substring(i, j + 1);    //截取当前区间
                int st = (now + now).indexOf(now, 1);  //1表示从拼接的字符串首字母后开始查找
                if (st < now.length()) {    //如果区间内包含重复字符串，则返回的起点不会超过区间
                    now = String.valueOf(now.length() / st) + "[" + dp[i][i + st - 1] + "]";    //计算字符串前的数字即题面中的k
                }
                if (now.length() < dp[i][j].length()) dp[i][j] = now;  //如果长度更小就进行更新
            }
        }
        return dp[0][n - 1];
    }
}