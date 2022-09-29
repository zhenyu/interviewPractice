package zhenyu.sha.twhorse.bidding;
import java.util.*;
/**
 * bids = [[1, 5, 5, 0], [2, 7, 8, 1], [3, 7, 5, 1], [4, 10, 3, 3]]
 * total_shares = 18
 */

public class Solution {
    static final int ID = 0;
    static final int SHARE = 1;
    static final int PRICE = 2;
    static final int TIMESTAMP = 3;
    private List<int[]> getCurGroup ( PriorityQueue<int[]> queue) {
        List<int[]> curGroup = new ArrayList<>();
        int [] highestBid = queue.poll();
        while (highestBid!=null) {
            // id and share
            curGroup.add(highestBid);
            if (!queue.isEmpty() && queue.peek()[PRICE] == highestBid[PRICE]) {
                highestBid = queue.poll();
            } else {
                highestBid = null;
            }
        }
        return curGroup;
    }
    private int allocateGroup(int totalShares, List<int[]> group, Set<Integer> Ids) {
        if (group.size() >0) {
            while (totalShares > 0 ) {
                boolean allZero = true;
                for(int i  =0; i < group.size();i++) {
                    int [] bid = group.get(i);
                    if(bid[SHARE]>0) {
                        allZero = false;
                        if(totalShares >0) {
                            totalShares--;
                            Ids.remove(bid[ID]);
                            bid[SHARE]--;
                        }
                    }
                }
                if (allZero) {
                    break;
                }
            }
        }

        return totalShares;
    }
    public List<Integer> getMoShareBidderId(int totalShares, int [][] bids) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(bids.length, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // index id, share, price, timestamp
                // first price
                if (o1[PRICE]!=o2[PRICE]) {
                    // larger first
                    return Integer.compare(o2[PRICE], o1[PRICE]);
                }
                // timestamp
                return Integer.compare(o1[TIMESTAMP],o2[TIMESTAMP]);
            }
        });
        Set<Integer> bidderIds = new HashSet<>();
        for(int [] bid : bids) {
            bidderIds.add(bid[0]);
            queue.add(bid);
        }

        while (totalShares>0 ) {
            List<int[]> curGroup = getCurGroup(queue);
            if(curGroup.size()==0) {
                break;
            }
            totalShares = allocateGroup(totalShares,curGroup, bidderIds);
        }
        List<Integer> result = new LinkedList<>();
        result.addAll(bidderIds);
        return result;
    }
    public static void main(String [] args) {
        // test case 1
        List<Integer> noShareIds = new Solution().getMoShareBidderId(0, new int[][]{{1, 5, 5, 0}, {2, 7, 8, 1}, {3, 7, 5, 1}, {4, 10, 3, 3}});
        System.out.println(noShareIds.toString());
    }
}
