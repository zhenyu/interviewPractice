package zhenyu.sha.dd;

import java.util.*;
class Node {
    int val;
    // Not null
    List<Node> children;
}
class Ret {
    Node node;
    int index;
}
//rootval N {}{}
//    3
//. 1. 0
//
class Solution {
    static Node buildTestCase(){
        Node root = new Node();
        root.val =3;
        root.children = new ArrayList<Node>();
        Node child1 = new Node();
        child1.val =1;
        child1.children = new ArrayList<Node>();
        root.children.add(child1);
        Node child2 = new Node();
        child2.val =2;
        child2.children= new ArrayList<Node>();
        Node sunzi1 = new Node();
        sunzi1.val=2;
        sunzi1.children = new ArrayList<Node>();
        child2.children.add(sunzi1);
        Node sunzi2 = new Node();
        sunzi2.val=2;
        sunzi2.children = new ArrayList<Node>();
        child2.children.add(sunzi1);
        root.children.add(child2);
        return root;
    }
    static boolean equalTree(Node root1, Node root2) {
        if(root1.val!=root2.val)
            return false;
        if(root1.children.size()!=root2.children.size())
            return false;
        for(int i=0;i<root1.children.size();i++){
            if(!equalTree(root1.children.get(i),root2.children.get(i))){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        Node root = buildTestCase();
        String content = solution.encodeTree(root);
        System.out.println(content);
        if(equalTree(root, solution.decodeTree(content))){
            System.out.println("hello DD");
        } else {
            System.out.println("sorry, I am not good enough, Didi Lab");
        }
    }
    String encodeTree(Node root) {
        StringBuilder sb = new StringBuilder();
        inerEncode(root, sb);
        return sb.toString();
    }
    void inerEncode(Node root, StringBuilder sb){
        if(null!=root){
            sb.append(root.val);
            sb.append(",");
            sb.append(root.children.size());
            sb.append(",");
            for(Node child: root.children) {
                inerEncode(child, sb);
            }
        }
    }
    Node decodeTree(String content) {

        if(content==null||content.length()==0){
            return null;
        }
        char[] con= content.toCharArray();
        int index =0;
        return inerDecode(con, index).node;

    }
    Ret inerDecode(char[] con, int index) {

        Ret ret = new Ret();
        ret.node = new Node();
        //get the root, value
        int val = 0;
        while(index<con.length&&con[index]!=','){
            val=val*10+(con[index]-'0');
            index++;
        }
        ret.node.val = val;
        index++;
        // get the childSize
        val = 0;
        while(index<con.length&&con[index]!=','){
            val=val*10+(con[index]-'0');
            index++;
        }

        if(val>0) {
            //exception
            //System.out.println("childSize>0");
            ret.node.children = new ArrayList<Node>(val);
        } else {
            ret.node.children = new ArrayList<Node>();
        }

        //","
        index++;
        // recusive childern
        for(int i=0; i<val;i++){
            Ret chilRet = inerDecode(con, index);
            ret.node.children.add(i,chilRet.node);
            index = chilRet.index;
        }
        ret.index = index;
        System.out.println("child size="+ret.node.children.size());
        return ret;
    }
}