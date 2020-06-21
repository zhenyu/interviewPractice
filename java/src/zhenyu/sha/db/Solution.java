package zhenyu.sha.db;
import java.util.*;
class Node {
    Node (int val){
        this.val = val;
    }
    int val;
    Node left;
    Node right;
    Node father;
}
public class Solution {

    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.left.father = node.left;
        node.left.left = new Node(3);
        node.left.left.father = node.left;
        node.right = new Node(4);
        node.right.father = node;
        node.right.left = new Node(5);

    }
    public List<Integer> visit(Node root) {
        List<Integer> ret = new LinkedList<>();
        Node cur = root;
        while (cur!=null) {
            ret.add(cur.val);
            if (cur.left!=null){
                cur = cur.left;
            } else if(cur.right!=null) {
                cur = cur.right;
            } else {
                while (cur.father!=null){
                    if(cur.father.right!=null&&cur.father.right!=cur) {
                        cur=cur.father.right;
                        break;
                    }
                    cur = cur.father;
                }
                if(cur.father==null){
                    break;
                }
            }
        }
        return ret;
    }
}
