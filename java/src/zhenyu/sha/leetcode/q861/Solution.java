package zhenyu.sha.leetcode.q861;

class Solution {
    public int matrixScore(int[][]a) {
        for(int i=0;i<a.length;i++) {
            if(a[i][0]==0){
                //flip
                for(int j =0;j<a[i].length;j++) {
                    a[i][j]=1-a[i][j];
                }
            }
        }
        int ret = 0;
        for(int j=0;j<a[0].length;j++){
            int cum =0;
            for(int i=0;i<a.length;i++){
                cum+=a[i][j];
            }
            ret=ret*2+Math.max(cum, a.length-cum);
        }
        return ret;
    }
}
