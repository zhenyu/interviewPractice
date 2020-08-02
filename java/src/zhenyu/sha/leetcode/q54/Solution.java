package zhenyu.sha.leetcode.q54;
import java.util.*;
class Solution {
    public List<Integer> spiralOrder(int[][] m) {
        List<Integer> ret = new LinkedList<Integer>();
        if(null!=m&&m.length>0) {
            int w = m[0].length;
            int h = m.length;
            while (w>0||h>0){
                int top = (m[0].length-w)/2;
                int left =  (m.length-h)/2;
                // top
                for(int i= 0;i<w;i++){
                    ret.add(m[top][left+i]);
                }
                // right
                for(int i=0;i<h-2;i++) {
                    ret.add(m[top+1+i][left+w-1]);
                }
                // bottom
                if(h>1) {
                    for(int i=w-1; i>=0; i--) {
                        ret.add(m[top+h-1][left+i]);
                    }
                }
                if(w>1) {
                    // left
                    for(int i=h-3;i>=0;i--) {
                        ret.add(m[top+1+i][left]);
                    }
                }

                w-=2;
                h-=2;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().spiralOrder(new int[][]{{6,7,8}}));
    }
}