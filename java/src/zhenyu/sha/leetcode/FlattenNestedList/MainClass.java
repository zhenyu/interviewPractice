package zhenyu.sha.leetcode.FlattenNestedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class AtomicNestInteger implements NestedInteger {
    final int val;
    public AtomicNestInteger(int val) {
        this.val = val;
    }
    @Override
    public boolean isInteger() {
        return true;
    }

    @Override
    public Integer getInteger() {
        return val;
    }

    @Override
    public List<NestedInteger> getList() {
        return null;
    }
}

class ListNestInteger implements NestedInteger {
    final List<NestedInteger> mlist;
    public ListNestInteger() {
        this.mlist = new LinkedList<>();
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public Integer getInteger() {
        return null;
    }

    @Override
    public List<NestedInteger> getList() {
        return mlist;
    }
}
public class MainClass {
    public static void main(String[] args) {
        //[[1,1],2,[1,1]]
        AtomicNestInteger a1 = new AtomicNestInteger(1);
        AtomicNestInteger a2 = new AtomicNestInteger(2);
        ListNestInteger list1 = new ListNestInteger();
        list1.mlist.add(a1);
        list1.mlist.add(a1);
        List<NestedInteger> list = new ArrayList<>();
        list.add(list1);
        list.add(a2);
        list.add(list1);
        NestedIterator iterator = new NestedIterator(list);
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
