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
            //System.out.println("x="+x+",y="+y);
            return Objects.hash(x, y);
        }
    };
    static int[][] turns = {{0,1}, {1,0},{0,-1},{-1,0}};

    public void cleanRoom(Robot robot) {
        Set<Cell> visited = new HashSet<>();
        dfs(robot, new Cell(0,0), 0, visited);
    }

    private void dfs(Robot robot, Cell cell, int d, Set<Cell> visited) {

        visited.add(cell);
        robot.clean();
        for(int i=0;i<4;i++){
            int nextD = (d+i)%4;
            Cell nextCell = new Cell(cell.x+turns[nextD][0], cell.y+turns[nextD][1]);
            if(!visited.contains(nextCell)&&robot.move()){
                dfs(robot, nextCell, nextD, visited);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnLeft();
            } else {
                robot.turnRight();
            }

        }

    }
}

