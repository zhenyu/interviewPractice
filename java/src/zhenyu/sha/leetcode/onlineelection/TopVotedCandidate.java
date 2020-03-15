package zhenyu.sha.leetcode.onlineelection;

import java.util.*;

class TopVotedCandidate {
    int[]persons;
    int[] times;
    int [] lastMax;
    public TopVotedCandidate(int[] persons, int[] times) {
        this.persons = persons;
        this.times = times;
        this.lastMax = new int[times.length];
        Map<Integer, Integer> countMap = new HashMap<>();
        int max_time =0;
        int tc =-1;
        for(int i=0;i<=times.length;i++){
            int count = countMap.getOrDefault(persons[i],0);
            count++;
            if(count>=max_time) {
                max_time=count;
                tc = persons[i];
            }
            countMap.put(persons[i], count);
            this.lastMax[i]=tc;
        }
    }

    public int q(int t) {
        return this.lastMax[search(t)];
    }
    int search(int t) {
        int begin =0;
        int end = times.length-1;
        while (begin<=end) {
            int  mid = begin + (end-begin)/2;
            int day = times[mid];
            if(day==t) {
                return mid;
            }
            else if(day>t){
                end = mid-1;
            } else  {
                begin = mid+1;
            }
        }
        return begin-1;

    }
    int lastMax(int index){
        int ret = -1;
        int max_time=0;

        return ret;
    }

    public static void main(String[] args) {
        TopVotedCandidate tc = new TopVotedCandidate(new int[]{0,1,1,0,0,1,0},new int[]{0,5,10,15,20,25,30});
        int [] qs = new int[]{3,12,25,15,24,8};
        for (int q : qs) {
            System.out.println("day:"+q+" top:"+ tc.q(q));
        }
    }

}