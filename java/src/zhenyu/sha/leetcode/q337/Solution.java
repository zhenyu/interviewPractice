package zhenyu.sha.leetcode.q337;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.*;
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}
class Solution {
    Map<Boolean, Map<TreeNode, Integer>> resultMap;
    public int rob(TreeNode root) {
        resultMap = new HashMap<>();
        resultMap.put(false, new HashMap<>());
        resultMap.put(true, new HashMap<>());
        return dfs(root, false);
    }
    private int dfs(TreeNode root, boolean father_robbed){
        int ret = 0;
        if (root!=null){
            if(null!=resultMap.get(father_robbed) && null!=resultMap.get(father_robbed).get(root)) {
                return resultMap.get(father_robbed).get(root);
            }
            // case 1 not rob this
            ret += dfs(root.left, false);
            ret += dfs(root.right, false);
            // case 2 only why father is not robbed
            if (!father_robbed){
                int rob_ret = root.val;
                rob_ret +=dfs(root.left, true);
                rob_ret += dfs(root.right, true);
                ret = Math.max(ret, rob_ret);
            }
            resultMap.get(father_robbed).put(root, ret);
        }
        return ret;
    }
}