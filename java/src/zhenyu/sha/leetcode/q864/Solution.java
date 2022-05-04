package zhenyu.sha.leetcode.q864;
import java.util.*;
class Node {
    static int allKeys =0;
    static void addKey (char key){
        int newKey =1;
        for(int i=1;i<=key-'a';i++){
            newKey=newKey<<1;
        }
        allKeys = allKeys| newKey;
    }
    Node(int x, int y, int keys, int step) {
        this.x = x;
        this.y = y;
        this.keys = keys;
        this.step = step;
    }
    int x;
    int y;
    int keys;
    int step;
    boolean getAllKeys(){
        return keys==allKeys;
    }
}
class Solution {
    public int shortestPathAllKeys(String[] grid) {
        boolean[][][] visited = new boolean[grid.length][grid[0].length()][64];

        // begin bfs
        LinkedList<Node> que = new LinkedList<>();

        for(int i=0; i<grid.length;i++) {
            String line = grid[i];
            for (int j=0; j<line.length();j++) {
                Arrays.fill(visited[i][j], false);
                //check
                if (line.charAt(j) == '@') {
                    // mark the fist node
                    visited[i][j][0]= true;
                    que.push(new Node(i,j,0, 0));
                } else if (line.charAt(j)>='a'&&line.charAt(j)<='f'){
                    Node.addKey(line.charAt(j));
                }
            }
        }

        int moves[][]={{-1,0},{1,0}, {0,-1}, {0, 1}};
        while (!que.isEmpty()) {
            Node current  = que.pollFirst();
            for(int[] move: moves) {
                int nextX = current.x+move[0];
                int  nextY = current.y+ move[1];

                if(nextX>=0&&nextX<grid.length&&nextY>=0&&nextY<grid[0].length()) {
                    char nextState = grid[nextX].charAt(nextY);
                    int nextKeys = current.keys;
                    if (nextState=='#') {
                        continue;
                    } else if(nextState>='A'&&nextState<='F') {
                        int test =1;
                        for(int i=1;i<=nextState-'A';i++){
                            test=test<<1;
                        }
                        if((test&current.keys)==0) {
                            continue;
                        }
                    }
                    if(nextState>='a'&&nextState<='f'){
                        int key =1;
                        for(int i=1;i<=nextState-'a';i++){
                            key=key<<1;
                        }
                        nextKeys = nextKeys| key;
                    }
                    if(!visited[nextX][nextY][nextKeys]) {
                        visited[nextX][nextY][nextKeys]=true;
                        Node next = new Node(nextX,nextY,nextKeys,current.step+1);
                        if(next.getAllKeys()){
                            return next.step;
                        }
                        que.addLast(next);
                    }

                }
            }
        }
        return -1;
    }
    public static void main(String[] argv){
        System.out.println(new Solution().shortestPathAllKeys(new String[]{".@aA"}));
    }
}