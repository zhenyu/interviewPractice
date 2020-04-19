package zhenyu.sha.leetcode.q913;

import java.util.*;
class Solution {
    class State {
        //mouse position
        int m;
        // cat position
        int c;
        // whose turn
        int t;
        public State(int m, int c, int t) {
            this.m = m;
            this.c = c;
            this.t = t;
        }
    }
    final int DRAW = 0, MOUSE = 1, CAT = 2;
    public int catMouseGame(int[][] graph) {
        int N = graph.length;

        int[][][] color = new int[N][N][3];
        int[][][] degree = new int[N][N][3];
        //init the win lose state
        LinkedList<State> finalizedStates = new LinkedList<>();
        for(int m=0; m<N;m++) {
            for(int c=0;c<N;c++) {
                for(int t=1;t<=2;t++) {
                    if(c==0) {
                        //猫不能在 0
                        continue;
                    }
                    if(m==c) {
                        color[m][c][t]=CAT;
                        finalizedStates.add(new State(m, c, t));
                    }
                    if(0==m) {
                        color[m][c][t]= MOUSE;
                        finalizedStates.add(new State(m, c, t));
                    }
                    //degree
                    degree[m][c][MOUSE]=graph[m].length;
                    degree[m][c][CAT]=graph[c].length;
                    for(int p:graph[c]){
                        if(p==0){
                            degree[m][c][CAT]--;
                        }
                    }
                }
            }
        }
        while (!finalizedStates.isEmpty()) {
            State curState = finalizedStates.remove();
            for (State father: fathers(graph, curState.m, curState.c, curState.t)) {
                // 否者剪掉degree
                degree[father.m][father.c][father.t]--;
                //上一步就是想这个颜色
                if(father.t==color[curState.m][curState.c][curState.t]) {
                    color[father.m][father.c][father.t]=color[curState.m][curState.c][curState.t];
                } else{
                    if(degree[father.m][father.c][father.t]==0){
                        //之前没上色，就是活命其他的都是输了
                        if(DRAW==color[father.m][father.c][father.t]){
                            color[father.m][father.c][father.t]=3-color[curState.m][curState.c][curState.t];
                        }
                    }
                }
            }
        }
        return color[1][2][MOUSE];
    }

    // What nodes could play their turn to
    // arrive at node (m, c, t) ?
    public List<State> fathers(int[][] graph, int m, int c, int t) {
        List<State> fathers = new LinkedList<>();
        if(t==CAT) {
            //previous is MOUSE move ,
            for(int father: graph[m]){
                fathers.add(new State(father, c, MOUSE));
            }
        } else {
            //previous is CAT
            for(int father:graph[c]){
                fathers.add(new State(m, father, CAT));
            }
        }
        return fathers;
    }
}