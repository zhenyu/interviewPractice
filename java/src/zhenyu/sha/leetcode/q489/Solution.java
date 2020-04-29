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
    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    };
    static class State{
        boolean [] visited = new boolean[4];
        boolean cleaned;
    }
    static class Visiting extends Node {
        int d;
        Visiting(int x, int y, int d) {
            super(x,y);
            this.d = d;
        }
    }
    public void cleanRoom(Robot robot) {
        Map<Node, State> graph = new HashMap<>();
        LinkedList<Visiting> dfsStack = new LinkedList<>();
        dfsStack.push(new Visiting(0,0, 0));
        int d = 0;
        while (!dfsStack.isEmpty()) {
            Visiting curVisit = dfsStack.pop();
            State cellState = graph.getOrDefault(curVisit, new State());
            if(!cellState.cleaned) {
                robot.clean();
            }
            cellState.visited[curVisit.d]=true;
            //other direction into stack
            for(int i=0; i<4;i++){
                if(!cellState.visited[i]){
                    //put to stack
                    dfsStack.push(new Visiting(curVisit.x, curVisit.y, i));
                }
            }
            //check wether direct equals
            if(d!=curVisit.d){
                //turn robot to the direct
                turnRobot(robot, d, curVisit.d);
                d=curVisit.d;
            }
            Visiting nextVisiting = getNextNode(curVisit.x, curVisit.y, curVisit.d);
            State nextState = graph.getOrDefault(nextVisiting, new State());
            //check wether already in stack
            if(!nextState.visited[curVisit.d]&&robot.move()){
                dfsStack.push(nextVisiting);
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
         }else if(rotate==-1){
             robot.turnLeft();
         }
    }


    private static Visiting getNextNode(int x, int y, int d){
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
            case 3:
                y1=y-1;
        }
        return new Visiting(x1, y1, d);
    }
}
