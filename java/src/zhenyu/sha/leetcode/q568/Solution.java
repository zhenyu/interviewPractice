package zhenyu.sha.leetcode.q568;
import java.util.*;
class Solution {
    static class Station implements Comparable<Station>{
        int position;
        int gas;

        public Station(int position, int gas) {
            this.position = position;
            this.gas = gas;
        }

        @Override
        public int compareTo(Station o) {
            return this.position-o.position;
        }
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        //state ,  cost,  left gas
        Map<Integer, Integer>dp1 = new HashMap<>();
        Map<Integer, Integer>dp2 = new HashMap<>();

        List<Station> stationList = new ArrayList<>();
        for(int i =0; i<stations.length;i++) {
            stationList.add(new Station(stations[i][0], stations[i][1]));
        }
        stationList.add(new Station(target,0));
        Collections.sort(stationList);
        dp1.put(0, startFuel);
        int lastPos = 0;
        for (Station s: stationList) {
            int currentPos = s.position;
            int curGas = s.gas;
            for(Map.Entry<Integer, Integer> entry: dp1.entrySet()){
                int leftGas = entry.getValue();
                int cost = entry.getKey();
                if(leftGas>=currentPos-lastPos) {
                    // not fill it
                    int newLeft = leftGas-(currentPos-lastPos);
                    int newCost = cost;
                    if(newLeft>dp2.getOrDefault(newCost, Integer.MIN_VALUE)){
                        dp2.put(newCost, newLeft);
                    }
                    //fill it
                    newLeft +=curGas;
                    newCost++;
                    if(newLeft>dp2.getOrDefault(newCost, Integer.MIN_VALUE)){
                        dp2.put(newCost, newLeft);
                    }
                }
            }
            if(dp2.size()==0)
                return -1;
            lastPos=currentPos;
            Map<Integer, Integer> temp = dp1;
            dp1=dp2;
            dp2=temp;
            dp2.clear();
        }
        int ret =Integer.MAX_VALUE;
        for(int cost:dp1.keySet()){
            if(cost<ret) {
                ret = cost;
            }
        }
        return ret;
    }
}