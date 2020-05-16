package zhenyu.sha.leetcode.q361;

class Solution {
    public int maxKilledEnemies(char[][] grid) {
        int ret =0;
        if(null==grid||grid.length==0||grid[0].length==0)
            return ret;
        int [][] counter = new int[grid.length][grid[0].length];
        // row to row
        for(int i=0;i<grid.length;i++){
            //slding window before
            int start =0;
            int end = 0;
            int enemies =0;
            while (end<grid[i].length) {
                if(grid[i][end]=='W') {
                    //make all
                    while (start<end){
                        if(grid[i][start]!='E'){
                            counter[i][start]=enemies;
                        }
                        start++;
                    }
                    enemies=0;
                    start=end+1;
                } else if(grid[i][end]=='E'){
                    enemies++;
                }
                end=end+1;
            }
            //dont forget the last one
            while (start<end){
                if(grid[i][start]!='E'){
                    counter[i][start]=enemies;
                }
                start++;
            }
        }
        //colum by colum
        for(int i=0;i<grid[0].length;i++) {
            //slding window before
            int start =0;
            int end = 0;
            int enemies =0;
            while (end<grid.length) {
                if(grid[end][i]=='W') {
                    //make all
                    while (start<end){
                        if(grid[start][i]!='E'){
                            counter[start][i]+=enemies;
                            if(counter[start][i]>ret){
                                ret=counter[start][i];
                            }
                        }

                        start++;
                    }
                    enemies=0;
                    start=end+1;
                } else if(grid[end][i]=='E'){
                    enemies++;
                }
                end=end+1;
            }
            //dont forget the last one
            while (start<end){
                if(grid[start][i]!='E'){
                    counter[start][i]+=enemies;
                    if(counter[start][i]>ret){
                        ret=counter[start][i];
                    }
                }

                start++;
            }
        }
        return ret;
    }
}