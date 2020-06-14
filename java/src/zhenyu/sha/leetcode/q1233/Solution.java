package zhenyu.sha.leetcode.q1233;
import java.util.*;
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        List<String> result = new LinkedList<>();
        Arrays.sort(folder);
        String cur = folder[0];
        int i =1;
        while (i<folder.length){
            if(!folder[i].startsWith(cur)) {
                result.add(cur);
                cur = folder[i];
            }
            i++;
        }
        result.add(cur);
        return result;
    }
}