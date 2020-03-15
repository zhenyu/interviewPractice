package zhenyu.sha.facebook;

public class Solution {
    int cubicRoot(int n) {
        if(n==0)
            return 0;
        boolean minus =n<0;
        if(minus) {
            if(n==Integer.MIN_VALUE) {
                n=Integer.MAX_VALUE;
            } else {
                n=-n;
            }
        }
        int i=1;
        while (i<n/(i*i)) {
            i++;
        }
        if(i*i*i>n && (!minus)){
            i--;
        }
        return minus?(-i):i;
    }

    public static void main(String[] args) {
        Solution s= new Solution();
        System.out.println(s.cubicRoot(8));
        System.out.println(s.cubicRoot(9));
        System.out.println(s.cubicRoot(-9));
        System.out.println(s.cubicRoot(Integer.MIN_VALUE));

    }
}
