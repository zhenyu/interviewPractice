package zhenyu.sha.leetcode.q809;

import java.util.ArrayList;

class Node {
    Node(int count, char c){
        this.c=c;
        this.count=count;
    }
    int count;
    char c;
}
class Solution {

    public int expressiveWords(String S, String[] words) {
        int ret =0;
        if(null==S||S.length()==0){
            for(String word:words){
                if(null==word||word.length()==0){
                    ret++;
                }
            }
            return ret;
        }

        ArrayList<Node> group= new ArrayList<>();
        char[]s = S.toCharArray();
        int end =0;
        int groupCount =0;
        while (end<s.length){
            if(end==0||s[end]==s[end-1]){
                groupCount++;
            } else {
                group.add(new Node(groupCount,s[end-1]));
                groupCount=1;
            }
            end++;
        }
        group.add(new Node(groupCount, s[end-1]));
        for(String w:words){
            if(checkGroup(w, group)) {
                ret++;
            }
        }
        return ret;
    }
    boolean checkGroup(String w, ArrayList<Node> compareGroup) {
        char[]s = w.toCharArray();
        int end =0;
        int groupCount =0;
        int groupIndex =0;
        while (end<s.length&&groupIndex<compareGroup.size()){
            if(end==0||s[end]==s[end-1]){
                if(s[end]!=compareGroup.get(groupIndex).c)
                    return false;
                groupCount++;
            } else {
                int compareCount = compareGroup.get(groupIndex).count;
                if(groupCount>compareCount||(groupCount<compareCount&&compareCount<3)){
                    return false;
                }
                groupCount=1;
                groupIndex++;
            }
            end++;
        }
        if(groupIndex!=compareGroup.size()-1){
            return false;
        }
        int compareCount = compareGroup.get(groupIndex).count;
        if(groupCount>compareCount||(groupCount<compareCount&&compareCount<3)){
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //"heeellooo"
        //["hello", "hi", "helo"]
        System.out.println(new Solution().expressiveWords("heeellooo"
                , new String[]{"hello", "hi", "helo"}));
    }
}