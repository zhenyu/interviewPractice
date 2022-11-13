package zhenyu.sha.leetcode.q1801;
import org.junit.experimental.max.MaxHistory;

import java.util.*;
class Solution {
    static final int PRICE = 0;
    static final int COUNT = 1;
    static final int TYPE = 2;
    public int getNumberOfBacklogOrders(int[][] orders) {
        Comparator<int[]> buyComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[PRICE], o1[PRICE]);
            }
        };
        PriorityQueue<int[]> buyQue = new PriorityQueue<>(orders.length, buyComparator);
        Comparator<int[]> sellComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[PRICE], o2[PRICE]);
            }
        };
        PriorityQueue<int[]> sellQue = new PriorityQueue<>(orders.length, sellComparator);
        for (int[] order : orders) {
            PriorityQueue<int[]> srcQue = order[TYPE]==0? buyQue:sellQue;
            PriorityQueue<int[]> targetQue = order[TYPE]==1? buyQue:sellQue;
            matchOrder(order, srcQue, targetQue);
        }
        List<PriorityQueue<int[]>> ques = new ArrayList<>(2);
        ques.add(sellQue);
        ques.add(buyQue);
        return getCount(ques);
    }
    private int getCount(List<PriorityQueue<int[]>>ques) {
        long count =0;
        for (PriorityQueue<int[]> que: ques) {
            while (!que.isEmpty()) {
                count=(que.poll()[COUNT]+count)%(109 + 7);

            }

        }
        return (int)count;
    }
    private void matchOrder(int[] order, PriorityQueue<int[]> srcQue, PriorityQueue<int[]> targetQue ) {
        while (!targetQue.isEmpty()&&(targetQue.comparator().compare(order, targetQue.peek())>=0)) {
            int count = Math.min(order[COUNT], targetQue.peek()[COUNT]);
            order[COUNT]-=count;
            targetQue.peek()[COUNT]-=count;
            if(targetQue.peek()[COUNT]==0) {
                targetQue.poll();
            }
            if(order[COUNT]==0){
                break;
            }
        }
        if (order[COUNT]>0) {
            srcQue.add(order);
        }
    }
    public static void main(String[] args) {
        System.out.println(new Solution().getNumberOfBacklogOrders(new int[][]{{10,5,0},{15,2,1},{25,1,1},{30,4,0}}));
    }
}