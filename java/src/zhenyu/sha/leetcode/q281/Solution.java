package zhenyu.sha.leetcode.q281;
import java.util.*;
public class ZigzagIterator {
    ArrayList<Iterator<Integer>> its = new ArrayList<>(2);
    int cur =0;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        cur=0;
        its.set(0,v1.iterator());
        its.set(1, v2.iterator());
    }

    public int next() {
        if(hasNext()){
            int ret= its.get(cur).next();
            cur=(cur+1)%2;
        }
        return -1;
    }

    public boolean hasNext() {
        Iterator<Integer> curIt = its.get(cur);
        if(curIt.hasNext()){
            return  true;
        }
        cur=(cur+1)%2;
        return its.get(cur).hasNext();
    }
}