package zhenyu.sha.leetcode.q773;
import java.util.*;
class Board{
    static int[][] moves ={{-1,0},{1,0},{0,-1},{0,1}};
    int[][] board;
    int x;
    int  y;
    int dep;
    Board(int[][] board, int dep, int x, int y){
        this.board = board;
        this.dep = dep;
        this.x = x;
        this.y = y;
    }

}
class Solution {

    public int slidingPuzzle(int[][] board) {
        LinkedList<Board> que= new LinkedList<>();
        int x = -1;
        int y = -1;
        for(int i=0;i<board.length;i++){
            for(int j =0; j<board[i].length;j++) {
                if (board[i][j]==0){
                    x =i;
                    y=j;
                }
            }
        }
        Board initBoard = new Board(board,0, x, y);
        Set<String> visited = new HashSet<>();
        visited.add(board.toString());
        que.addLast(initBoard);
        while (que.size()>0) {
            Board current = que.pollFirst();
            for (int[] move: Board.moves){
                int nextX = current.x+move[0];
                int nextY = current.y+move[1];
                if(nextX>=0&&nextX< board.length&&nextY>=0&&nextY<board[0].length){
                    int[][]nextBoard = board.clone();
                    nextBoard[current.x][current.y] = nextBoard[nextX][nextY];
                    nextBoard[nextX][nextY] =0;
                    Board next= new Board(nextBoard, current.dep+1, nextX, nextY);
                    if (!visited.contains(nextBoard.toString())){
                        visited.add(nextBoard.toString());
                        que.addLast(next);
                    }
                }
            }

        }
        return -1;
    }

}
