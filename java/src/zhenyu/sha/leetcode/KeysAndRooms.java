package zhenyu.sha.leetcode;
import java.util.*;
public class KeysAndRooms {
}
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(null==rooms||0==rooms.size())
            return false;
        BitSet visitedRoom = new BitSet();
        visitedRoom.set(0);
        Queue<Integer> keys = new LinkedList();
        for(Integer key: rooms.get(0)){
            keys.add(key);
        }
        while (!keys.isEmpty()){
            Integer key = keys.poll();
            visitedRoom.set(key);
            List<Integer> nextKeys = rooms.get(key);
            if(null!=nextKeys) {
                for(Integer nextKey:nextKeys ){
                    keys.add(nextKey);
                }
            }
        }
        return visitedRoom.cardinality()==rooms.size();
    }
}
