package zhenyu.sha.leetcode.q246;

class Solution {
    public boolean isStrobogrammatic(String num) {
        boolean ret = true;
        int begin = 0;
        int end = num.length()-1;
        while(begin<=end){
            if(!isMirror(num.charAt(begin), num.charAt(end))){
                ret = false;
                break;
            }
            begin++;
            end--;
        }
        return ret;
    }
    boolean isMirror(char a, char b){
        if(a>b){
            char temp =b;
            b=a;
            a=temp;
        }
        if(a==b&&(a=='0'||a=='8'||a=='1'))
            return true;
        if(a=='6'&&b=='9')
            return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isStrobogrammatic("69"));
    }
}