package zhenyu.sha.leetcode.q44;

class Solution {
    public boolean isMatch(String s, String p) {
        boolean [][] matches = new boolean[p.length()+1][s.length()+1];
        matches[0][0]=true;
        for(int i=1;i<=p.length();i++){
            for (int j=0;j<=s.length();j++) {
                if(p.charAt(i-1)=='*') {
                    for (int k=0; k<=j; k++) {
                        if (matches[i-1][k]) {
                            matches[i][j]=true;
                            break;
                        }
                    }

                } else if (j>0 && (p.charAt(i-1)=='?'||p.charAt(i-1)==s.charAt(j-1))){
                    matches[i][j] = matches[i-1][j-1];
                }
            }
        }
        return matches[p.length()][s.length()];
    }
    public static void main(String[]args){
        //"adceb"
        //"*a*b"
        System.out.println(new Solution().isMatch("adceb","*a*b"));
    }
}
