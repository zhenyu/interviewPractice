package zhenyu.sha.leetcode.q267;
import java.util.*;
class Solution {
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        List<String> result = new LinkedList<>();
        for( char c: s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0)+1);
        }
        int oddCount  = 0;
        List<Character> chars = new ArrayList<>();
        Character middle = null;
        for( Map.Entry<Character, Integer> entry: countMap.entrySet()){
            int value = entry.getValue();
            if (1==value%2){
                oddCount++;
                if(oddCount>1){
                    return result;
                }
                middle = entry.getKey();
                value --;
            }
            value = value/2;
            for(; value>0;value--){
                chars.add(entry.getKey());
            }
        }
        Comparator<Character> characterComparator = new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1-o2;
            }
        };
        Character[] characters = chars.toArray(new Character[]{});
        Arrays.sort(characters, characterComparator);
        dfs(characters, 0, new LinkedList<Character>(), new HashSet<Integer>(), middle, result);
        return result;

    }
    void dfs(  Character[] chars, int level, LinkedList<Character> path, Set<Integer> seenPos,  Character middle, List<String> result) {
        if(level>=chars.length) {
            StringBuilder sb = new StringBuilder();
            if (null!= middle) {
                sb.append(middle);
            }

            for(char c: path) {
                sb.append(c);
                sb.insert(0, c);
            }
            result.add(sb.toString());
            return;
        }

        int pos =0;
        while (pos<chars.length) {
            if (!seenPos.contains(pos)){
                path.add(chars[pos]);
                seenPos.add(pos);
                dfs(chars, level+1, path, seenPos, middle, result);
                path.removeLast();
                seenPos.remove(pos);
                do {
                    pos++;
                }
                while (pos<chars.length&&chars[pos]==chars[pos-1]);
            } else {
                pos++;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().generatePalindromes("aabbcc"));
    }
}