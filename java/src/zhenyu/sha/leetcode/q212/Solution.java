package zhenyu.sha.leetcode.q212;
import java.util.*;
class Node{
    Map<Character, Node> children;
    boolean isLeaf;
    Node(){
        children = new HashMap<>();
        isLeaf = false;
    }
}
public class Solution {
    int [][] steps = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
    private void addWord(Node node, String word) {
        int index = 0;
        while (index<word.length()){
           // get next
           Node next = node.children.get(word.charAt(index));
           if (null==next) {
               next = new Node();
               node.children.put(word.charAt(index),next);
           }
           node = next;
           index++;
        }
        node.isLeaf = true;
    }
    public List<String> findWords(char[][] board, String[] words) {
        // build trie
        Set<String> result = new HashSet<>();
        Node root = new Node();
        for(String w: words) {
            addWord(root, w);
        }
        // do dfs
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        LinkedList<Character> path = new LinkedList<>();
        for(int i=0;i<board.length;i++) {
            for(int j=0; j<board[i].length;j++) {
                dfs(board, i, j, visited, path, root, result);
            }
        }
        LinkedList<String>ret = new LinkedList<>();
        ret.addAll(result);
        return ret;
    }
    private void dfs(char[][]board,
                     int i, int j, Map<Integer, Set<Integer>> visited,
                     LinkedList<Character>path, Node current, Set<String > result) {

        Node next = current.children.get(board[i][j]);
        if(null==next){
            return;
        }
        // put to path
        path.addLast(board[i][j]);
        Set<Integer> cols = visited.getOrDefault(i, new HashSet<>());
        cols.add(j);
        visited.put(i, cols);
        // check wether already leaf
        if(next.isLeaf) {
            StringBuilder sb = new StringBuilder();
            for(Character c: path){
                sb.append(c);
            }
            result.add(sb.toString());
        }
        // check wther still sub need to search
        if(next.children.size()>0){

            //check steps
            for(int [] step: steps) {
                int nextI = i+step[0];
                int nextJ = j+step[1];
                //next postion
                if(isValid(board, nextI, nextJ) && !isVisited(visited, nextI, nextJ)){
                    dfs(board, nextI,nextJ,visited, path, next, result);
                }
            }

        }
        path.removeLast();
        cols.remove(j);
    }
    boolean isValid(char[][] board, int i, int j) {
        return i>=0&&i<board.length&&j>=0&&j<board[i].length;
    }
    boolean isVisited(Map<Integer, Set<Integer>> visited, int i, int j) {
        return visited.getOrDefault(i, new HashSet<>()).contains(j);
    }
}
