package zhenyu.sha.leetcode.q753;


import java.util.*;

class Solution {
    //edge
    LinkedList<Character> result;
    //Node
    Set<Integer> seeNode;
    int MODE;
    void dfs(int node, int k) {
        seeNode.add(node);
        int nexBase = (node*10)%MODE;
        for(int i=0;i<k; i++) {
            int next = nexBase+i;
            if(!seeNode.contains(next)){
                dfs(next,k);
                result.addFirst((char)('0'+i));
            }
        }
    }
    public String crackSafe(int n, int k) {
        StringBuffer sb = new StringBuffer();
        if(n==1){
            for(int i=0;i<k;i++){
                sb.append((char)('0'+i));
            }
            return sb.toString();
        }
        seeNode = new HashSet<>();
        result = new LinkedList<>();
        MODE = 1;
        for(int i=0;i<n;i++) {
            MODE = MODE*10;
        }
        dfs(0, k);
        for(int i=0;i<n;i++) {
            result.addFirst('0');
        }


        for(char c: result){
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().crackSafe(2, 2));
    }
}
