package zhenyu.sha.leetcode.q393;

class Solution {
    public boolean validUtf8(int[] data) {
        int state =0;
        int toRead =0;
        for(int b: data) {
            if(0==state) {
                while (128 == (b & 128)){
                    ++toRead;
                    b=(b<<1)&255;
                }
                if(toRead>0){
                    state =1;
                    --toRead;
                    if(0==toRead||toRead>3)
                        return false;
                }
            } else {
                int lead = b & 192;
                if(lead==128){
                    --toRead;
                    if(toRead ==0) {
                        state = 0;
                    }
                }
            }
        }
        return state ==0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().validUtf8(new int[]{250,145,145,145,145}));
    }
}