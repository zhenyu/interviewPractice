package zhenyu.sha.leetcode.q465;
import java.util.*;
class Solution {

    public int minTransfers(int[][] transactions) {
        int ret=0;
        Map<Integer, Map<Integer, Integer>> borrowGraph = new HashMap();
        Map<Integer, Map<Integer, Integer>> lendGraph = new HashMap();
        //build graph
        for(int i=0;i<transactions.length;i++) {
            //zero, from, value
            Map<Integer, Integer> borrowTransaction = borrowGraph.getOrDefault(transactions[i][1], new HashMap<>());
            // 我欠别人的
            borrowTransaction.put(transactions[i][0], transactions[i][2]);
            borrowGraph.put(transactions[i][1], borrowTransaction);
            Map<Integer, Integer> lendTransactions = lendGraph.getOrDefault(transactions[i][0],new HashMap<>());
            // 别人欠我的
            lendTransactions.put(transactions[i][1], transactions[i][2]);
            lendGraph.put(transactions[i][0], lendTransactions);
        }
        //try reduce each node
        Set<Integer> processed = new HashSet<>();
        for(Map.Entry<Integer, Map<Integer, Integer>> entry: lendGraph.entrySet()) {
            //Node
            int currentNode = entry.getKey();
            // 别人欠我
            Map<Integer, Integer> lendTrans = entry.getValue();
            // 我欠别人的,只是为了避免后面的空指针
            Map<Integer, Integer> borrowTrans = borrowGraph.getOrDefault(currentNode, new HashMap<>());
            // try reduce edge as few as possible
            Iterator<Map.Entry<Integer, Integer>> lendTransIT = lendTrans.entrySet().iterator();
            // 我可能从来没欠过钱
            Iterator<Map.Entry<Integer, Integer>> borrowTransIT =  borrowTrans.entrySet().iterator();
            int borrowAmount =0;
            int lendAmount =0;
            int borrowPerson =0;
            int lendPerson=0;
            while (true) {
                while (borrowAmount==0&&borrowTransIT.hasNext()) {
                    Map.Entry<Integer, Integer> toTran = borrowTransIT.next();
                    if(!processed.contains(borrowPerson)) {
                        borrowPerson = toTran.getKey();
                        borrowAmount =toTran.getValue();
                    }

                }
                while (lendAmount==0&&lendTransIT.hasNext()) {
                    Map.Entry<Integer, Integer> fromTran = lendTransIT.next();
                    if(!processed.contains(lendPerson)) {
                        lendPerson = fromTran.getKey();
                        lendAmount =fromTran.getValue();
                    }

                }
                if(borrowAmount==0||lendAmount==0) {
                    break;
                }

                int reduceAmount = Math.min(borrowAmount, lendAmount);
                borrowAmount-=reduceAmount;
                lendAmount-= reduceAmount;
                if(lendPerson!=borrowPerson) {

                    if(null==borrowGraph.get(lendPerson)) {
                        borrowGraph.put(lendPerson, new HashMap<>());
                    }
                    int det = borrowGraph.get(lendPerson).getOrDefault(borrowPerson,0);
                    det+=reduceAmount;
                    borrowGraph.get(lendPerson).put(borrowPerson,det);
                    //数量一样，不用计算
                    if(null==lendGraph.get(borrowPerson)) {
                        lendGraph.put(borrowPerson, new HashMap<>());
                    }
                    lendGraph.get(borrowPerson).put(lendPerson, det);
                    lendGraph.get(borrowPerson).put(currentNode, borrowAmount);
                }
                borrowTrans.put(borrowPerson, borrowAmount);
                lendTrans.put(lendPerson, lendAmount);
            }

            processed.add(currentNode);
        }
        for(Map.Entry<Integer, Map<Integer, Integer>> entry: lendGraph.entrySet()) {
            for(int ammount: entry.getValue().values()){
                if(ammount!=0){
                    ret++;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minTransfers(new int[][]{{0,1,10}, {2,0,5}}));
    }

}
