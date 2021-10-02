package zhenyu.sha.leetcode.q63;
import java.util.*;
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int [][] counts = new int[2][obstacleGrid[0].length];
        int cur =0;
        if(obstacleGrid[0][0]==0) {
            counts[0][0]=1;
        }
        for(int i =0; i< obstacleGrid.length;i++) {
            for(int j=0; j<obstacleGrid[i].length;j++){
                if(obstacleGrid[i][j]==0){
                    //up
                    if(i>0){
                        counts[cur][j]+=counts[(cur+1)%2][j];
                    }
                    if(j>0){
                        counts[cur][j]+=counts[cur][j-1];
                    }
                    //left
                }
            }
            cur=(cur+1)%2;
            Arrays.fill(counts[cur], 0);
        }
        return counts[(cur+1)%2][counts[0].length-1];
    }
}
