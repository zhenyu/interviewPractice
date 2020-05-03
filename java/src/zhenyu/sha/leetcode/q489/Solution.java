package zhenyu.sha.leetcode.q489;

import java.util.*;

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 */
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
}

class Solution {
    static class Cell {
        int x;
        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return x == cell.x &&
                    y == cell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    };


    public void cleanRoom(Robot robot) {
        Set<Cell> visited = new HashSet<>();
        dfs(robot, new Cell(0,0), 0, visited);
    }

    private void dfs(Robot robot, Cell cell, int d, Set<Cell> visited) {
        visited.add(cell);
        robot.clean();
        for(int i=0;i<4;i++){
            int nextD = (d+i)%4;
            Cell nextCell = getNextNode(cell.x, cell.y, nextD);
            if(!visited.contains(nextCell)&&robot.move()){
                dfs(robot, nextCell, nextD, visited);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                turnRobot(robot, (nextD+2)%4,(nextD+1)%4);
            } else {
                robot.turnRight();
            }

        }
    }

    private void turnRobot(Robot robot, int cur, int target) {
         int rotate = (target-cur)%4;
         if(Math.abs(rotate)==2) {
             robot.turnRight();
             robot.turnRight();
         } else if(rotate==1){
             robot.turnRight();
         } else if(rotate==-1){
             robot.turnLeft();
         }
    }


    private static Cell getNextNode(int x, int y, int d){
        int x1= x;
        int y1 =y;
        switch (d) {
            case 0:
                x1=x+1;
                break;
            case 1:
                y1=y+1;
                break;
            case 2:
                x1=x-1;
                break;
            case 3:
                y1=y-1;
        }
        return new Cell(x1, y1);
    }
}
