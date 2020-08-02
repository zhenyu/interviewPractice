package zhenyu.sha.leetcode.q520;

class Solution {
    public boolean detectCapitalUse(String word) {
        char[]w = word.toCharArray();
        if(Character.isLowerCase(w[0])){
            for(int i=1;i<w.length;i++){
                if(!Character.isLowerCase(w[i])) {
                    return false;
                }
            }
            return true;
        } else if(Character.isUpperCase(w[0])){
            if(w.length>1&&Character.isUpperCase(w[1])){
                for(int i=1;i<w.length;i++){
                    if(!Character.isUpperCase(w[i])) {
                        return false;
                    }
                }
                return true;
            } else {
                for(int i=1;i<w.length;i++){
                    if(!Character.isLowerCase(w[i])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;

    }
}