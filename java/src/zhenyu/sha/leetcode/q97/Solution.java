package zhenyu.sha.leetcode.q97;

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length()+s2.length()!=s3.length())
            return false;
        boolean[] ret = new boolean[s2.length()+1];
        for(int i=0;i<s2.length()+1;i++){
            if (s2.substring(0,i).equals(s3.substring(0,i))) {
                ret[i] = true;
            }
        }

        for(int i =1;i<=s1.length();i++) {
            for(int j=0; j<=s2.length();j++) {
                if(j==0){
                    ret[j]=(s1.substring(0,i).equals(s3.substring(0,i)));
                } else {
                    ret[j]=(s1.charAt(i-1)==s3.charAt(i+j-1)&&ret[j])||(s2.charAt(j-1)==s3.charAt(i+j-1)&&ret[j-1]);
                }
            }
        }
        return  ret[s2.length()];
    }

    public static void main(String[] args) {
        //"aabcc"
        //"dbbca"
        //"aadbbcbcac"
        System.out.println(new Solution().isInterleave("aabcc",
                "dbbca",
                "aadbbcbcac"));
    }
}