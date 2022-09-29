package zhenyu.sha.twohorse.serwer;


import java.util.*;
public class Solution {
    int getLeastAbs(int [] parents, int[] input){
        // build the tree
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = -1; i< input.length; i++) {
            tree.put(i, new LinkedList<>());
        }
        for (int i = 0; i< input.length; i++) {
            tree.get(parents[i]).add(i);
        }
        // int the sum
        int [] sums = new int[input.length];
        Arrays.fill(sums, 0);
        dfs(0, tree, sums, input);
        int abs =Integer.MAX_VALUE;
        for(int sum: sums) {
            abs = Math.min(abs, Math.abs(sum-(sums[0]-sum)));
        }
        return abs;

    }
    private void dfs(int root, Map<Integer, List<Integer>> tree, int[] sums, int[] input) {
        int sum = input[root];
        for(int i : tree.get(root)) {
            dfs(i, tree, sums, input);
            sum+=sums[i];
        }
        sums[root] = sum;
    }
    public static void main (String[] args) {
        /**
        * parent = [-1, 0, 0, 1, 1, 2]
        * input = [1, 2, 2, 1, 1, 1]
        */
        System.out.println(new Solution().getLeastAbs(new int[]{-1, 0, 0, 1, 1, 2}, new int[] {1, 2, 50, 1, 1, 1}));
    }
}
