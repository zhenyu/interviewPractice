package zhenyu.sha.leetcode.q913;

import java.util.*;
class Solution {
    static class State {
        boolean mouse;
        int mPos;
        int cPos;

        public State(boolean mouse, int mPos, int cPos) {
            this.mouse = mouse;
            this.mPos = mPos;
            this.cPos = cPos;
        }

        @Override
        public String toString() {
            return "State{" +
                    "mouse=" + mouse +
                    ", mPos=" + mPos +
                    ", cPos=" + cPos +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return mouse == state.mouse &&
                    mPos == state.mPos &&
                    cPos == state.cPos;
        }

        @Override
        public int hashCode() {
            return Objects.hash(mouse, mPos, cPos);
        }
    }
    // return 1 if the game is won by Mouse, 2 if the game is won by Cat, and 0 if the game is a draw.
    // 2 mouse win, 1 draw, 0 catWin, -1 don't know yet
    Map<State, Integer> seenStates = new HashMap<>();
    public int catMouseGame(int[][] graph) {
        seenStates.clear();
        State initState = new State(true, 1, 2);
        dfs(initState, graph, 0);
        Integer score = seenStates.get(initState);
        if(score==1){
            score = 0;
        } else if(score==0) {
            score =2;
        } else if(score==2) {
            return 1;
        }
        //System.out.println("score="+score);
        return score;
    }
    void dfs(State state,int[][] graph , int dep){
        String prefix ="";
        for(int i = 0; i<dep;i++) {
            prefix=prefix+"    ";
        }
        System.out.println(prefix+ "visiting "+state);
        Integer ret = seenStates.get(state);
        if(null!=ret){
            System.out.println(prefix+"seen "+state.toString()+seenStates.get(state));
            return;
        }
        ret =1;
        seenStates.put(state, ret);
        if(state.mPos==0) {
            seenStates.put(state, 2);
            System.out.println(prefix+"mouse "+state.toString()+seenStates.get(state));
            return;
        }
        if(state.mPos==state.cPos) {
            seenStates.put(state, 0);
            System.out.println(prefix+"cat "+state.toString()+seenStates.get(state));
            return;
        }
        if(state.mouse) {
            for(int nextPos : graph[state.mPos]) {

                State nextState = new State(false, nextPos, state.cPos);
                dfs(nextState, graph, dep+1);
                int score =seenStates.get(nextState);
                if(-1!=score) {
                    ret = Math.max(ret,score);
                    System.out.println(prefix+"update mouse "+state.toString()+" "+ret);
                }
            }

        }  else {
            for(int nextPos : graph[state.cPos]) {

                if(nextPos!=0) {
                    State nextState = new State(true, state.mPos, nextPos);
                    dfs(nextState, graph,dep+1);
                    int score = seenStates.get(nextState);
                    if(-1!=score) {
                        ret = Math.min(ret,score);
                        System.out.println(prefix+"update cat "+state.toString()+" "+ret);
                    }

                }
            }
        }
        //update state
        if(ret!=Integer.MAX_VALUE&&ret!=Integer.MIN_VALUE) {

            seenStates.put(state, ret);
        }
        System.out.println(prefix+"exit "+state.toString()+seenStates.get(state));
    }

    public static void main(String[] args) {
        int[][]graph = new int[][]{{2,5},{3},{0,4,5},{1,4,5},{2,3},{0,2,3}};
        System.out.println(new Solution().catMouseGame(graph));
    }
}