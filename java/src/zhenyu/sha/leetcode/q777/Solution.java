package zhenyu.sha.leetcode.q777;

class Solution {
    public boolean canTransform(String s, String e) {
        char[] src = s.toCharArray();
        char[] dst = e.toCharArray();
        if (src.length != dst.length)
            return false;
        int i =0, j=0;
        while (i<src.length&&j<src.length){
            while (i<src.length&&src[i]=='X'){
                i++;
            }
            while (j<src.length&&dst[j]=='X'){
                j++;
            }
            if((i<src.length)&&(j<src.length)) {
                if((src[i]!=dst[j])||(src[i]=='L'&&i<j)||(src[i]=='R'&&i>j)){
                    return false;
                }
                i++;
                j++;
            }
       }
        while (i<src.length&&src[i]=='X'){
            i++;
        }
        while (j<src.length&&dst[j]=='X'){
            j++;
        }
        return (i>=src.length)&(j>=src.length);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canTransform("XXRXLXRXXX",
                "XXRLXXXXXR"));
    }
}
