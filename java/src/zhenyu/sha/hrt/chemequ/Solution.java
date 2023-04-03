package zhenyu.sha.hrt.chemequ;
import java.util.*;
public class Solution {
    boolean validChemEqual(String equ){
        equ=equ.replace(" ", "");
        String[] parts = equ.split("=");
        if(parts.length!=2){
            return false;
        }
        int [] leftCounter =elementCounter(parts[0]);
        int [] rightCounter = elementCounter(parts[1]);
        return Arrays.equals(leftCounter, rightCounter);
    }
    int[] elementCounter(String part){
        int [] counter = new int[26];
        Arrays.fill(counter, 0);
        String[] units = part.split("\\+");
        Map<Character, Integer> unitCounter = new HashMap<>();
        for(String unit : units) {
            unitCounter.clear();
            int leading =0;
            int i=0;
            while (i<unit.length()&&Character.isDigit(unit.charAt(i))){
                leading=leading*10+unit.charAt(i)-'0';
                i++;
            }
            while (i<unit.length()){
                // must be a char
                char c = unit.charAt(i);
                i++;
                int repeat =0;
                while (i<unit.length()&&Character.isDigit(unit.charAt(i))){
                    repeat=repeat*10+unit.charAt(i)-'0';
                    i++;
                }
                int num = repeat>0?repeat:1;
                if (leading>0) {
                    num = num*leading;
                }
                unitCounter.put(c, unitCounter.getOrDefault(c, 0)+ num);
            }
            for(Map.Entry<Character, Integer> entry: unitCounter.entrySet()){
                counter[entry.getKey()-'A']+=entry.getValue();
            }
        }
        return counter;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().validChemEqual("2H2 + O2 = 2H2O"));
    }
}
