package zhenyu.sha.leetcode.q471;

class Solution {
    public String encode(String s) {
        if(null==s)
            return "";
        //plus one is tricky for length zero
        String dp[][] = new String[s.length()+1][s.length()+1];
        for (int i = 0; i< s.length();i++) {
            dp[i][i+1] = s.substring(i,i+1);
        }
        for(int l=2;l<=s.length();l++) {
            // i is the start position
            for(int i=0; i<=s.length()-l;i++) {
                //iterator with length
                for(int t=1;t<l;t++) {
                    String cadidate = dp[i][i+t]+dp[i+t][i+l];
                    if(null==dp[i][i+l]||dp[i][i+l].length()>cadidate.length()){
                        dp[i][i+l]=cadidate;
                    }
                }
                //pretty tricky here
                String current = s.substring(i, i+l);
                int st =(current+current).indexOf(current, 1);
                if(st<l) {
                    //repeat part
                    int repeatL =st;
                    int repeatTimes = l/repeatL;
                    //try compress
                    String repeatDP = dp[i][i+st];
                    String cadidate=Integer.toString(repeatTimes)+"["+repeatDP+"]";
                    if(dp[i][i+l].length()>cadidate.length()){
                        dp[i][i+l]=cadidate;
                    }
                }

            }

        }
        return dp[0][s.length()];
    }

    public static void main(String[] args) {
        // "a2[abc]a"
        System.out.println(new Solution().encode("aabcabca"));
    }
}