package zhenyu.sha.leetcode.FlattenNestedList;

import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {
    int currentIndex;
    NestedIterator currentListIter;
    final List<NestedInteger> mList;
    public NestedIterator(List<NestedInteger> nestedList) {
        mList = nestedList;
        currentIndex = 0;
        currentListIter = null;
        if((nestedList.size()>0)&&(nestedList.get(0).getList()!=null)) {
            currentListIter = new NestedIterator(nestedList.get(0).getList());
        }
    }
    private void moveTonext(){
        currentIndex++;
        if(currentIndex<mList.size()){
            List<NestedInteger> newList =  mList.get(currentIndex).getList();
            if(null!=newList) {
                currentListIter= new NestedIterator(newList);
            } else {
                currentListIter = null;
            }
        }
    }
    @Override
    public Integer next() {
        while(currentIndex<mList.size()) {
            if(null==currentListIter){
                moveTonext();
                return mList.get(currentIndex-1).getInteger();
            }
            while(null!=currentListIter) {
                if (currentListIter.hasNext()){
                    return currentListIter.next();
                }
                moveTonext();
            }
        }
        return null;
    }
    @Override
    public boolean hasNext() {
        while(currentIndex<mList.size()&&null!=currentListIter) {
            if (currentListIter.hasNext()){
                return true;
            }
            moveTonext();
        }
        return currentIndex<mList.size();
    }
}