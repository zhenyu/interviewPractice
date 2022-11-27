package zhenyu.sha.leetcode.q1146;
import java.util.*;
class SnapshotArray {
    int snap_id;
    int length;
    ArrayList<TreeMap<Integer, Integer>> values;
    public SnapshotArray(int length) {
        snap_id = 0;
        this.length = length;
        values = new ArrayList<>(length);
        for(int i=0;i<length;i++){
            TreeMap<Integer, Integer> indexVersions = new TreeMap<>();
            indexVersions.put(0, 0);
            values.add(indexVersions);
        }
    }

    public void set(int index, int val) {
        Map<Integer, Integer> indexVersions = values.get(index);
        indexVersions.put(snap_id, val);
    }

    public int snap() {
        snap_id++;
        return snap_id-1;
    }

    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> indexVersions = values.get(index);
        return  indexVersions.floorEntry(snap_id).getValue();
    }

    public static void main(String[] args) {
       // ["SnapshotArray","set","snap","snap","snap","get","snap","snap","get"]
//[[1],[0,15],[],[],[],[0,2],[],[],[0,0]]
        SnapshotArray snapshotArr = new SnapshotArray(1);

        snapshotArr.set(0,15);
        snapshotArr.snap();
        snapshotArr.snap();
        snapshotArr.snap();

        snapshotArr.get(0,2);
        snapshotArr.snap();
        snapshotArr.snap();
        snapshotArr.get(0,0);
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
