package zhenyu.sha.leetcode.q116;


// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


class Solution {
    public Node connect(Node root) {
        makeFather(root);
        doConnect(root);
        return root;
    }
    private void makeFather(Node root) {
        if(null!=root){
            if (null!=root.left) {
                root.left.next = root;
            }
            if (null!=root.right) {
                root.right.next = root;
            }
            makeFather(root.left);
            makeFather(root.right);
        }
    }
    private void doConnect(Node root) {
        if (null!=root) {
            Node father = root.next;
            if(null!=father){
                if(root==father.left) {
                    root.next = father.right;
                } else {
                    if(null!=father.next) {
                        root.next = father.next.left;
                    } else {
                        root.next = null;
                    }
                }
            }
            doConnect(root.left);
            doConnect(root.right);
        }
    }
}
