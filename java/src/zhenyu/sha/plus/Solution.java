package zhenyu.sha.plus;

/*
"""
Data scientists need to search for valuable data collected by truck, and then label them and train and improve the self-driving model. They can search by multiple conditions or filters, imaging that different search queries go to different search engine nodes, here we need to implement a function to merge the search result from different nodes.
The result returned by search engine is like this:
Raining =
 [
  {
    truck_id: "Truck1",
    timeSlot: [[1545679897, 1545689456], [1545689897, 1545979897]]
  },
  {
    truck_id: "Truck2",
    timeSlot: [[1545680897, 1545979111], [1546680895, 1565934161]]
  }
 ]
Motorcycle = [
  {
    truck_id: "Truck2",
    timeSlot: [[1545760800, 1545689456], [1545689897, 1545979897]]
  },
  {
    truck_id: "Truck3",
    timeSlot: [[1545760800, 1545789459], [1546680895, 1565934161]]
  }
]
Write a function to find the intersection of the search results.
"""
*/

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    Map<String, List<int[]>> mergeResult(List<Map<String, List<int[]>>> inputs) {
        Map<String, List<List<int[]>>> truckTimeslotMap = new HashMap<>();
        for (Map<String, List<int[]>> input: inputs){
            for( String truckId: input.keySet()){
                List<List<int[]>> timslotList = truckTimeslotMap.get(truckId);
                if(null==timslotList) {
                    timslotList = new LinkedList<>();
                    truckTimeslotMap.put(truckId, timslotList);
                }
                timslotList.add(input.get(truckId));
            }
        }
        Map<String, List<int[]>> result = new HashMap<>();
        for (String truckId: truckTimeslotMap.keySet() ) {
            if (truckTimeslotMap.get(truckId).size()>1) {
                List<int[]> overlap = mergeOverlap(truckTimeslotMap.get(truckId));
                if (null!=overlap&&overlap.size()>0){
                    result.put(truckId, overlap);
                }
            }
        }
        return result;
    }
    class Event implements Comparable<Event>{
        Event(int type, int timestamp) {
            this.type = type;
            this.timestamp = timestamp;
        }
        //0 , begin, 1 end
        int type;
        int timestamp;
        @Override
        public int compareTo(Event o) {
            // TODO Auto-generated method stub
            if (this.timestamp ==o.timestamp) {
                return Integer.compare(o.type, this.type);
            }
            return Integer.compare(this.timestamp, o.timestamp);
        }
    }
    List<int[]> mergeOverlap(List<List<int[]>> timeSlotsList) {
        PriorityQueue<Event> heap = new PriorityQueue<>();
        for (List<int []> timeSlots: timeSlotsList) {
            for (int [] timeslot : timeSlots) {
                heap.add(new Event(0, timeslot[0]));
                heap.add(new Event(1, timeslot[1]));
            }
        }
        int count = 0;
        int begin = 0;
        List<int[]> result = new LinkedList<>();
        while(heap.size()>0) {
            Event e = heap.poll();
            if (e.type ==1) {
                count--;
                if (count == 1) {
                    result.add(new int []{begin, e.timestamp});
                }
            } else {
                count ++;
                if (count ==2) {
                    begin = e.timestamp;
                }
            }
        }

        return result;
    }
    public static void main(String[] args) {
        List<Map<String, List<int[]>>> inputs = new LinkedList<>();
        Map<String, List<int[]>> rainningMap = new HashMap<>();
        List<int[]> rainningList = new LinkedList<>();
        rainningList.add(new int []{1, 15});
        rainningList.add(new int []{18, 19});
        rainningMap.put("t1", rainningList);

        List<int[]> rainningList1 = new LinkedList<>();
        rainningList1.add(new int []{1, 2});
        rainningMap.put("t2", rainningList1);
        inputs.add(rainningMap);


        Map<String, List<int[]>> motoMap = new HashMap<>();
        List<int[]> motoList = new LinkedList<>();
        motoList.add(new int []{2, 3});
        motoList.add(new int []{19, 25});
        motoMap.put("t1", motoList);

        List<int[]> motoList1 = new LinkedList<>();
        motoList1.add(new int []{1, 2});
        motoMap.put("t3", motoList1);
        inputs.add(motoMap);

        Map<String, List<int[]>> result = new Solution().mergeResult(inputs);
        for (String truckId : result.keySet()) {
            System.out.println(truckId);
            for (int[] slot: result.get(truckId)){
                System.out.print(slot[0]+","+ slot[1]+" ");
            }
            System.out.println();
        }
        /**
         Raining =
         [
         {
         truck_id: "Truck1",
         timeSlot: [[], [1545689897, 1545979897]]
         },
         {
         truck_id: "Truck2",
         timeSlot: [[1545680897, 1545979111], [1546680895, 1565934161]]
         }
         ]
         Motorcycle = [
         {
         truck_id: "Truck2",
         timeSlot: [[1545760800, 1545689456], [1545689897, 1545979897]]
         },
         {
         truck_id: "Truck3",
         timeSlot: [[1545760800, 1545789459], [1546680895, 1565934161]]
         }
         ]
         **/

    }
}
