package zhenyu.sha.leetcode.q407;
import java.util.*;
//credit to https://www.youtube.com/watch?v=cJayBq38VYw
class Solution {
    PriorityQueue<Cell> levelQue;
    boolean[][] visited;
    int [][]heightMap;
    static class Cell implements Comparable<Cell>{
        int height;
        int x;
        int y;

        public Cell(int height, int x, int y) {
            this.height = height;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Cell other) {
            return this.height-other.height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        //check
        if(null==heightMap||heightMap.length==0)
            return 0;
        if(null==heightMap[0]||heightMap[0].length==0)
            return 0;
        int ret=0;
        this.heightMap = heightMap;
        levelQue= new PriorityQueue<>();
        visited =new boolean[heightMap.length][heightMap[0].length];
        //initial with
        //left
        for(int i=0;i<heightMap.length;i++) {
            levelQue.add(new Cell(heightMap[i][0], i, 0));
            visited[i][0]=true;
        }
        //top
        for(int i=0;i<heightMap[0].length;i++) {
            if(visited[0][i])
                continue;
            levelQue.add(new Cell(heightMap[0][i], 0, i));
            visited[0][i]=true;
        }
        //right
        for(int i=0;i<heightMap.length;i++){
            if(visited[i][heightMap[0].length-1])
                continue;
            levelQue.add(new Cell(heightMap[i][heightMap[0].length-1], i, heightMap[0].length-1));
            visited[i][heightMap[0].length-1]=true;
        }
        //bottom
        for(int i=0;i<heightMap[0].length;i++) {
            if(visited[heightMap.length-1][i])
                continue;
            levelQue.add(new Cell(heightMap[heightMap.length-1][i], heightMap.length-1, i));
            visited[heightMap.length-1][i]=true;
        }
        int level =levelQue.peek().height;
        while (!levelQue.isEmpty()) {
            Cell curCell = levelQue.poll();
            //check neigh
            int x = curCell.x;
            int y = curCell.y;
            if(curCell.height>level){
                level= curCell.height;
            }
            ret+=checkCell(x-1,y,level);
            ret+=checkCell(x+1,y,level);
            ret+=checkCell(x,y-1,level);
            ret+=checkCell(x,y+1,level);
        }
        return ret;
    }
    private int  checkCell(int x, int y, int level){

        if((x<0||x>=heightMap.length)||(y<0||y>=heightMap[0].length))
            return 0;
        if(visited[x][y])
            return 0;
        int ret =0;
        //check trap water
        if(heightMap[x][y]<level){
            ret=level-heightMap[x][y];
        }
        levelQue.add(new Cell(heightMap[x][y], x,y));
        visited[x][y]= true;
        return ret;
    }
}