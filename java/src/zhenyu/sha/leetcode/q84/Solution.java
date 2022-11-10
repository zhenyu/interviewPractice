package zhenyu.sha.leetcode.q84;
import java.util.*;
class Solution {
    public int largestRectangleArea(int[] heights) {
        int ret = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<=heights.length;i++) {
            int cur = stack.isEmpty()? 0: heights[i];
            while (stack.size()>0 && cur<=heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty()? i : i-stack.peek()-1;
                ret = Math.max(ret, h*w);
            }
            stack.push(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestRectangleArea(new int[]{0,2,1,2}));
    }
}
