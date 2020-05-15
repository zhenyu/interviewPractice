package zhenyu.sha.leetcode.q402;

class Solution {
    boolean leadingFound;
    public String removeKdigits(String num, int k) {
        if(k>=num.length())
            return "0";
        leadingFound = false;
        StringBuilder sb = new StringBuilder();
        char[] charNum = num.toCharArray();
        int next= findSmall(sb, 0, num.length()-k, charNum);

        for(int i=num.length()-k-1;i>0;i--){
            next=findSmall(sb, next+1, i, charNum);
        }
        return sb.toString();
    }
    int findSmall(StringBuilder sb, int start, int len, char[]num) {
        int index = num.length;
        char s ='9'+1;
        for(int i=start; num.length-i>=len;i++){
            char cur = num[i];
            if(cur<s) {
                s=cur;
                index = i;
            }
        }
        if(s!='0'){
            leadingFound=true;
            sb.append(s);
        } else if(leadingFound||len==1){
            sb.append(s);
        }

        return index;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeKdigits("1432219", 3));
    }
}