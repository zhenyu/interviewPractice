package zhenyu.sha.leetcode.q353;


import java.util.*;

class SnakeGame {
    LinkedList<int[]> body;
    Set<AbstractMap.SimpleEntry<Integer, Integer>> bodyCell;
    int w;
    int h;
    int[][] foods;
    int foodIndex;
    Map<String, int[]> moves;
    public SnakeGame(int width, int height, int[][] food) {
        this.w = width;
        this.h = height;
        moves = new HashMap<>();
        moves.put("U", new int[]{-1,0});
        moves.put("D", new int[]{1,0});
        moves.put("R", new int[]{0,1});
        moves.put("L", new int[]{0,-1});

        body = new LinkedList<>();
        bodyCell = new HashSet<>();
        this.foods = food;
        this.foodIndex =0;
        int[] head = new int[]{0,0};
        body.add(head);
        bodyCell.add(new AbstractMap.SimpleEntry<>(0,0));
    }

    public int move(String direction) {
        int [] head = body.peekFirst();
        int nX = head[0]+moves.get(direction)[0];
        int nY = head[1]+moves.get(direction)[1];
        AbstractMap.SimpleEntry<Integer, Integer> next = new AbstractMap.SimpleEntry<>(nX, nY);
        //check collison
        if(nX<0||nX>=this.h||nY<0||nY>=w){
            return -1;
        }
        if (foodIndex<foods.length&&foods[foodIndex][0]==nX&&foods[foodIndex][1]==nY) {
            foodIndex++;
        } else {
            int [] tail = body.pollLast();
            bodyCell.remove(new AbstractMap.SimpleEntry<>(tail[0], tail[1]));
        }
        if (bodyCell.contains(next)){
            return -1;
        }
        bodyCell.add(next);
        body.addFirst(new int[]{nX, nY});
        return body.size()-1;
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(3, 3, new int[][]{{2,0},{0,0},{0,2},{2,2}});
        // ["D"],["D"],["R"],["U"],["U"],["L"],
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        //["D"],["R"],["R"],["U"],["L"],["D"]]
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("D"));
    }
}