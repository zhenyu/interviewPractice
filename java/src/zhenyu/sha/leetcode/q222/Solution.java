package zhenyu.sha.leetcode.q222;

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
    public int countNodes(TreeNode root) {
        if(null==root)
            return 0;
        int level =1;
        TreeNode next=root;
        while(next.left!=null){
            level++;
            next=next.left;
        }
        return dfs(root, 1, 1, level);

    }
    int dfs(TreeNode cur, int curNum, int curLevel, int maxLevel) {
        if(curLevel==maxLevel){
            return curNum;
        }
        if(cur.right!=null){
            int right = dfs(cur.right, curNum*2+1, curLevel+1, maxLevel);
            if(right!=-1) {
                return right;
            }
        }
        return cur.left==null?-1:dfs(cur.left, curNum*2, curLevel+1,maxLevel);
    }
}
