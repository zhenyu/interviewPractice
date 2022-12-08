package zhenyu.sha.leetcode.q10;

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (p.charAt(i - 1) == '*') {
                    f[i][j] = f[i - 2][j];
                    if (matches(s, p, j, i - 1)) {
                        f[i][j] = f[i][j] || f[i-1][j];
                    }
                } else {
                    if (matches(s, p, j, i)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[n][m];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aa","a*"));
    }
}
