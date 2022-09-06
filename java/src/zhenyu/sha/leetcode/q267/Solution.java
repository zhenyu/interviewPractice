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
        List<Character> permList = new ArrayList<>();
        String single = "";
        Character[] perm = new Character[0];
        for( Map.Entry<Character, Integer> entry: countMap.entrySet()){
            int value = entry.getValue();
            if (1==value%2){
                oddCount++;
                if(oddCount>1){
                    return result;
                }
                single = single+entry.getKey();
                value --;
            }
            value = value/2;
            for(; value>0;value--){
                permList.add(entry.getKey());
            }
            perm = permList.toArray(new Character[]{});

        }
        Character[] cur = perm.clone();
        while (true){
            String newPerm  = single;
            for(char c: cur) {
                newPerm= c+newPerm+c;
            }
            result.add(newPerm);
            Character[] next = getNextPerm(cur);
            if(Arrays.equals(next,perm)){
                break;
            }
            cur = next;
        }
        return result;
    }
    Character[] getNextPerm(Character[] prev){
        if(prev.length <=1){
            return prev;
        }
        Character [] next = prev.clone();
        int i = next.length -2;
        for(; i>=0; i--){
            if(next[i]<next[i+1]){
                break;
            }
        }
        // swap
        if(i>=0){
            for(int j= next.length-1;j > i; j--){
                if(next[j]>next[i]){
                    //swap
                    char temp  = next[i];
                    next[i] = next[j];
                    next [j] = temp;
                    break;
                }
            }
        }
        // reverse i ;
        i++;
        int j= next.length-1;
        while (i<j){
            char temp  = next[i];
            next[i] = next[j];
            next [j] = temp;
            i++;
            j--;
        }
        return next;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().generatePalindromes("aabbcc"));
    }
}