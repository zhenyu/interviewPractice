package zhenyu.sha.leetcode.q994;


import java.util.LinkedList;

class Solution {
    private final static int EMPTY =0;
    private final static int FRESH =1;
    private final static int ROTTING =2;
    public int orangesRotting(int[][] grid) {
        // check rotten
        LinkedList<int[]> current = new LinkedList<>();
        LinkedList<int[]> next = new LinkedList<>();

        for(int i =0; i<grid.length;i++) {
            for(int j =0; j<grid[0].length;j++) {
                if(ROTTING==grid[i][j]) {
                    current.add(new int[]{i,j});

                }
            }
        }
        int round =0;
        int [][] neighs = new int[][]{{0,-1 },{-1,0}, {0,1}, {1,0}};

        while (!current.isEmpty()) {

            for (int [] rotting: current) {
                for (int[] neigh: neighs) {
                    int nX = rotting[0]+neigh[0];
                    int nY = rotting[1]+neigh[1];
                    if(nX<0||nX>=grid.length||nY<0||nY>=grid[0].length)
                        continue;
                    if(FRESH == grid[nX][nY] ) {
                        grid[nX][nY] =ROTTING;
                        next.add(new int[]{nX, nY});
                    }
                }
            }
            round++;
            LinkedList<int[]> temp = current;
            current = next;
            next = temp;
            next.clear();
        }

        // check fresh
        boolean fresh = false;
        for(int i =0; i<grid.length;i++) {
            for(int j =0; j<grid[0].length;j++) {
                if(FRESH==grid[i][j]) {
                    fresh = true;
                    break;
                }
            }
        }
        return fresh? -1: round>0?round-1:0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().orangesRotting(new int[][]{{2,0,0,0,0}}));
    }
}