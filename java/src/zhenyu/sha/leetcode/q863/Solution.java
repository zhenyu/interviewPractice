package zhenyu.sha.leetcode.q863;

/**
 * Definition for a binary tree node.
 *
 * }
 */

import java.util.*;
class TreeNode {
             int val;
            TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
}
class Solution {
    LinkedList<Integer> result=new LinkedList<>();
    Map<TreeNode, Integer> candidates = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        result.clear();
        candidates.clear();
        searchTarget(root, target, K);
        for(Map.Entry<TreeNode, Integer> entry: candidates.entrySet()) {
            dfsMaxDep(entry.getKey(), 0, K-entry.getValue());
        }
        return result;
    }
    void dfsMaxDep(TreeNode root, int currentDep, int maxDep) {
        if(currentDep==maxDep) {
            result.add(root.val);
            return;
        }
        int nextD = currentDep+1;
        if(nextD<=maxDep) {
            if(null!=root.left&&(!candidates.containsKey(root.left))) {
                dfsMaxDep(root.left, nextD, maxDep);
            }
            if(null!=root.right&&(!candidates.containsKey(root.right))) {
                dfsMaxDep(root.right, nextD, maxDep);
            }
        }
    }
    int searchTarget(TreeNode root, TreeNode target, int k ){
        if(root==target) {
            candidates.put(root, 0);
            return 0;
        }
        if(null!=root.left) {
            int d = searchTarget(root.left, target, k)+1;
            if(d>0&&d<=k){
                candidates.put(root, d);
                return d;
            }
        }
        if(null!=root.right) {
            int d = searchTarget(root.right, target, k)+1;
            if(d>0&&d<=k){
                candidates.put(root, d);
                return d;
            }
        }
        return -1;
    }
}
