package zhenyu.sha.hrt.query;
import java.util.*;
public class Solution {
    private Map<Integer, Set<Integer>> value2Indexes;
    private int[]a;
    private int[]b ;
    public Solution(int[]a, int[]b) {
        this.a = a;
        this.b = b;
        value2Indexes = new HashMap<>();
        for(int i=0;i<a.length;i++) {
            Set<Integer> indexes = value2Indexes.get(a[i]);
            if (null==indexes) {
                indexes = new HashSet<>();
                value2Indexes.put(a[i], indexes);
            }
            indexes.add(i);
        }
    }
    public int query(int[]q){
        int ret =0;
        if(q[0]==0) {
            int index = q[1];
            int value = q[2];
            //this
            int oldvalue = a[index];
            value2Indexes.get(oldvalue).remove(index);
            a[index] = value;
            Set<Integer> indexes = value2Indexes.get(value);
            if (null==indexes){
                indexes = new HashSet<>();
                value2Indexes.put(value, indexes);
            }
            indexes.add(index);

        } else {
            int target = q[1];
            for (int bvalue: this.b){
                ret+=value2Indexes.getOrDefault(target-bvalue, new HashSet<>()).size();
            }
        }
        return ret;
    }
}
