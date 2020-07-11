package zhenyu.sha.leetcode.q316;
import java.util.*;
class Solution {
    public String removeDuplicateLetters(String input) {
        if(null==input||input.length()==0)
            return input;
        char [] s = input.toCharArray();
        Map<Character, LinkedList<Integer>> charPos = new HashMap<>();
        TreeSet<Character> chars = new TreeSet<>();
        List<Set<Character>> posCount = new ArrayList<>(s.length);
        for(int i = s.length-1;i>=0;i--) {
            posCount.add(new HashSet<>());
        }
        Set<Character> preSet = new HashSet<>();
        for(int i = s.length-1;i>=0;i-- ) {
            LinkedList<Integer> poses  = charPos.getOrDefault(s[i], new LinkedList<>());
            poses.addFirst(i);
            charPos.put(s[i], poses);
            chars.add(s[i]);
            Set<Character> curSet = posCount.get(i);
            curSet.addAll(preSet);
            curSet.add(s[i]);
            preSet = curSet;
        }
        StringBuilder sb = new StringBuilder();
        int curPos = 0;
        while(chars.size()>0){
            List<Character> ignores = new LinkedList<>();
            for(char c: chars) {
                LinkedList<Integer> poses = charPos.get(c);
                while(poses.peekFirst()<curPos){
                    poses.pollFirst();
                }
                boolean found = false;
                Iterator<Integer> it = poses.iterator();
                while (it.hasNext()) {
                    Integer pos =it.next();
                    if(posCount.get(pos).containsAll(chars)) {
                        sb.append(c);
                        chars.remove(c);
                        curPos = pos;
                        found =true;
                        break;
                    }
                }
                if(found) {
                    break;
                } else {
                    ignores.add(c);
                }
            }
            if(ignores.size()>0) {
                chars.addAll(ignores);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicateLetters("bcabc"));
    }
}
