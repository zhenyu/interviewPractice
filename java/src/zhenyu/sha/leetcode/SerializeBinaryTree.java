package zhenyu.sha.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
public class SerializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(null==root)
            return " *";
        StringBuilder sb = new StringBuilder(" ");
        sb.append(root.val);
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        Queue<String> tokens = new LinkedList<>();
        for(String token: Arrays.asList(data.split(" "))) {
            tokens.add(token);
        }
        tokens.poll();
        return deTokens(tokens);
    }
    private TreeNode deTokens(Queue<String> tokens){

        String token = tokens.poll();

        if(token.charAt(0)=='*')
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(token));
        root.left =  deTokens(tokens);
        root.right = deTokens(tokens);
        return root;

    }
}
