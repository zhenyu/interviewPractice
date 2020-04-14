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
    }
    // 2 mouse win, 1 draw, 0 catWin, -1 don't know yet
    Map<State, Integer> seenStates = new HashMap<>();
    public int catMouseGame(int[][] graph) {
        seenStates.clear();
        int score= dfs(new State(true, 1, 2), graph);
        if(score==2) {
            return 2;
        } else if(score==1){
            return 0;
        }
        return 1;
    }
    int dfs(State state,int[][] graph ){
        Integer ret = seenStates.get(state);
        if(null!=ret){
            if(ret==-1) {
                return 0;
            }
            return ret;
        }
        //make Sure
        seenStates.put(state, -1);
        if(state.mouse) {
            ret = Integer.MIN_VALUE;
            for(int nextPos : graph[state.mPos]) {
                //我一定赢
                if(nextPos==0){
                    ret = 2;
                    break;
                }
                // 我可能lose
                if(nextPos==state.cPos) {
                    ret = Math.max(ret, 0);
                } else { //我不知道， 只能去visit
                    State nextState = new State(false, nextPos, state.cPos);
                    ret = Math.max(ret,dfs(nextState, graph));
                }
            }

        }  else {
            ret = Integer.MAX_VALUE;
            for(int nextPos : graph[state.cPos]) {
                //我一定赢
                if(nextPos==state.mPos){
                    ret = 0;
                    break;
                }
                State nextState = new State(true, state.mPos, nextPos);
                ret = Math.max(ret,dfs(nextState, graph));
            }
        }
        //update state
        seenStates.put(state, ret);
        return ret;
    }
}