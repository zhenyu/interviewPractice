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
    // 2 mouse win, 1 draw, 0 catWin
    Map<State, Integer> seenStates = new HashMap<>();
    public int catMouseGame(int[][] graph) {
        seenStates.clear();
        State initState = new State(true, 1, 2);
        Set<State> paths = new HashSet<>();
        int score = dfs(initState, graph, 0, paths);
        if(score==1){
            //draw
            score = 0;
        } else if(score==0) {
            // cat win
            score =2;
        } else if(score==2) {
            // mouse win
            return 1;
        }
        //System.out.println("score="+score);
        return score;
    }
    int dfs(State state,int[][] graph , int dep, Set<State> paths ){
        String prefix ="";
        for(int i = 0; i<dep;i++) {
            prefix=prefix+"    ";
        }

        Integer ret = seenStates.get(state);
        if(null!=ret){
            System.out.println(prefix+ "cached "+state+ret);
            return ret;
        }
        if(state.mPos==0) {
            seenStates.put(state, 2);
            System.out.println(prefix+state.toString()+2);
            return 2;
        }
        if(state.mPos==state.cPos) {
            seenStates.put(state, 0);
            System.out.println(prefix+state.toString()+0);
            return 0;
        }
        paths.add(state);
        if(state.mouse) {
            ret = Integer.MIN_VALUE;
            for(int nextPos : graph[state.mPos]) {
                State nextState = new State(false, nextPos, state.cPos);
                System.out.println(prefix+ "visiting "+nextState);
                //default
                int nextScore = 1;
                if(!paths.contains(nextState)) {
                    nextScore= dfs(nextState, graph, dep+1, paths);
                }
                ret = Math.max(ret,nextScore);
                System.out.println(prefix+state.toString()+ret);
                if(ret ==2){
                    break;
                }

            }

        }
        else {
            ret = Integer.MAX_VALUE;
            for(int nextPos : graph[state.cPos]) {
                State nextState = new State(true, state.mPos, nextPos);
                if(nextPos!=0&&(!paths.contains(nextState))) {
                    System.out.println(prefix+ "visiting "+nextState);
                    int nextScore= dfs(nextState, graph, dep+1,paths);
                    ret = Math.min(ret,nextScore);
                    System.out.println(prefix+state.toString()+ret);
                    if(ret ==0){
                        break;
                    }
                }
            }
            if(Integer.MAX_VALUE==ret){
                ret =1;
            }
        }
        paths.remove(state);

        seenStates.put(state, ret);

        System.out.println(prefix +"return "+state.toString()+ret);
        return  ret;
    }

    public static void main(String[] args) {
        int[][]graph = new int[][]{{6},{4},{9},{5},{1,5},{3,4,6},{0,5,10},{8,9,10},{7},{2,7},{6,7}};
        System.out.println(new Solution().catMouseGame(graph));
    }
}