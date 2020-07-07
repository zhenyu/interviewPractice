package zhenyu.sha.leetcode.q715;
import java.util.*;
class Interval implements Comparable<Interval> {
    int s;
    int e;
    Interval(int s, int e) {
        this.s =s;
        this.e =e;
    }
    // much more convinient
    public int compareTo(Interval o) {
        int ret= Integer.compare(this.e, o.e);
        if (ret==0) {
            ret = Integer.compare(this.s, o.s);
        }
        return ret;
    }
}
class RangeModule {
    TreeSet<Interval> ranges;
    public RangeModule() {
        ranges = new TreeSet<>();
    }

    public void addRange(int left, int right) {
        Iterator<Interval> it = ranges.tailSet(new Interval(0, left)).iterator();
        Interval cur = new Interval(left, right);
        while (it.hasNext()){
            Interval i =it.next();
            if(i.s>cur.e){
                ranges.add(cur);
                return;
            }
            //interset
            cur = new Interval(Math.min(cur.s, i.s), Math.max(cur.e, i.e));
            it.remove();
        }
        ranges.add(cur);
    }

    public boolean queryRange(int left, int right) {
        if(left==right)
            return true;
        Interval i =ranges.higher(new Interval(0, left));
        return i!=null&&i.s<=left&&i.e>=right;
    }

    public void removeRange(int left, int right) {

        if(left==right)
            return ;
        Iterator<Interval> it = ranges.tailSet(new Interval(0, left)).iterator();
        ArrayList<Interval> todo = new ArrayList();
        while (it.hasNext()) {
            Interval iv = it.next();
            if (right < iv.s) break;
            if (iv.s < left) todo.add(new Interval(iv.s, left));
            if (right < iv.e) todo.add(new Interval(right, iv.e));
            it.remove();
        }
        for (Interval iv: todo) ranges.add(iv);
    }
}