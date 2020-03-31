package zhenyu.sha.leetcode.q42;

class Solution {
    public int trap(int[] height) {
        if(null==height || height.length <=1)
            return 0;
        int maxLeft =0;
        int maxRight=0;
        int [] leftMaxes = new int [height.length];
        int [] rightMaxes = new int [height.length];
        for(int i=0;i<height.length;i++) {
            leftMaxes[i]=maxLeft;
            maxLeft = Math.max(maxLeft, height[i]);
            rightMaxes[height.length-1-i]= maxRight;
            maxRight=Math.max(maxRight,height[height.length-1-i]);
        }
        int ret =0;
        for(int i=0;i<height.length;i++) {
            ret += Math.max(0,Math.min(leftMaxes[i], rightMaxes[i])-height[i]);
        }
        return ret;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().trap(new int[]{2,0,2}));
    }
}