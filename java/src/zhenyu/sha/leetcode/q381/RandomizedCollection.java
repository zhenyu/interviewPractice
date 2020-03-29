package zhenyu.sha.leetcode.q381;
import  java.util.*;
class RandomizedCollection {
    List<Integer> elements ;
    int size;
    Map<Integer, PriorityQueue<Integer>> valuePosMap;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        elements= new ArrayList<>();
        size =0;
        valuePosMap = new HashMap<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean ret = false;
        PriorityQueue<Integer> poses  = valuePosMap.get(val);
        if(poses==null) {
            ret = true;
            poses = new PriorityQueue(1, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }


            });
        }
        //insert to the ArrayList;
        elements.add(size, val);
        poses.add(size);
        valuePosMap.put(val, poses);
        size++;
        return ret;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        boolean ret = false;
        PriorityQueue<Integer> poses  = valuePosMap.get(val);
        if(poses!=null) {
            ret = true;
            Integer lastElem = elements.get(size-1);
            int pos = poses.poll();
            if(pos!=size-1){
                elements.set(pos, lastElem);
                PriorityQueue<Integer> lastElemPoses = valuePosMap.get(lastElem);
                lastElemPoses.poll();
                lastElemPoses.add(pos);
                valuePosMap.put(lastElem, lastElemPoses);
            }
            size--;
            if(poses.isEmpty()){
                valuePosMap.remove(val);
            }
        }
        return ret;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        if(size==0)
            return 0;
        int p =new Random().nextInt(size);
        return elements.get(p);
    }

}