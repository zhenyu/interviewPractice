package zhenyu.sha.leetcode.q408;

class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int wP =0; int aP =0;
        int count =0;
        while(aP<abbr.length()){
            char cur = abbr.charAt(aP) ;
            if(cur>='0'&&cur<='9'){
                count=count*10+(cur-'0');
                if(count==0)
                    return false;

            } else {
                //previous count
                if(count!=0) {
                    wP+=count;
                    count=0;
                    if (wP>word.length()){
                        return false;
                    }
                }
                if(word.charAt(wP)!=abbr.charAt(aP)) {
                    return false;
                }
                ++wP;
                if (wP>word.length()){
                    return false;
                }
            }
            aP++;
        }
        //previous count
        if(count!=0) {
            wP+=count;
            count=0;
            if (wP>word.length()){
                return false;
            }
        }
        return wP==word.length();
    }

    public static void main(String[] args) {
        new Solution().validWordAbbreviation("hi"
                ,"2i");
    }
}
