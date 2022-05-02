package zhenyu.sha.leetcode.q1235;

import java.util.*;

class Job implements Comparable<Job>{
    int start;
    int end;
    int profit;
    Job(int start, int end, int profit) {
        this.start = start;
        this.end = end;
        this.profit = profit;
    }

    @Override
    public int compareTo(Job o) {
        return Integer.compare(this.end, o.end);
    }
}

class Solution {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = new ArrayList<>(startTime.length);
        for(int i =0; i< startTime.length;i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        Collections.sort(jobs);
        int [][] profits= new int [2][endTime.length+1];
        Arrays.fill(profits[0],0);
        Arrays.fill(profits[1], 0);
        TreeMap<Integer, Integer> endTime2Index = new TreeMap<>();
        for (int i=0; i<jobs.size();i++) {
            Job job = jobs.get(i);
            profits[0][i+1] = Math.max(profits[0][i], profits[1][i]);
            // find the first
            Map.Entry<Integer,Integer> first = endTime2Index.floorEntry(job.start);
            int preMax =0;
            if(null!=first){
                preMax = Math.max(profits[0][first.getValue()+1],profits[1][first.getValue()+1]);
            }
            profits[1][i+1]=preMax+job.profit;
            endTime2Index.put(job.end, i);

        }
        return Math.max(profits[0][endTime.length], profits[1][endTime.length]);
    }

}