package zhenyu.sha.leetcode.q200;
class UF {
    static int count =0;
    static UF [][] ufs = null;
    UF(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 1;
    }
    int x;
    int y;
    int size;
    void union(UF other) {
        UF uf1 = this;
        while (ufs[uf1.x][uf1.y]!= uf1){
            uf1 = ufs[uf1.x][uf1.y];
        }
        UF uf2 = other;
        while (ufs[uf2.x][uf2.y]!= uf2){
            uf2 = ufs[uf1.x][uf1.y];
        }
        if (uf1 == uf2) {
            return;
        }
        if (uf1.size < uf2.size) {
            UF temp = uf1;
            uf1= uf2;
            uf2 = temp;
        }
        uf2.x = uf1.x;
        uf2.y = uf1.y;
        uf1.size += uf2.size;
        count --;
        //TODO compres
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        UF.ufs = new UF[grid.length][grid[0].length];
        int [][] steps = {{0,-1}, {-1, 0}};
        for(int i = 0; i< grid.length; i++) {
            for( int j = 0; j<grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    UF uf = new UF(i, j);
                    UF.ufs[i][j] = uf ;
                    UF.count++;
                    for(int [] step : steps) {
                        int neigh_x =i+step[0];
                        int neigh_y = j+step[1];
                        if(neigh_x>=0&&neigh_x<grid.length&&neigh_y>=0&&neigh_y<grid[0].length&&grid[neigh_x][neigh_y]=='1'){
                            uf.union(UF.ufs[neigh_x][neigh_y]);
                        }
                    }
                }

            }
        }
        return  UF.count;
    }
}