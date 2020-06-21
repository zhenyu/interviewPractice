package zhenyu.sha.leetcode.q726;
import java.util.*;

class Solution {
    public String countOfAtoms(String formula) {
        if(null==formula||formula.length()==0){
            return "";
        }
        Stack<TreeMap<String, Integer>> stacks = new Stack<>();
        stacks.push(new TreeMap<>());
        int i =0;
        char[]f = formula.toCharArray();
        while (i<f.length){
            char cur = f[i];
            i++;
            if(cur =='(') {
                stacks.push(new TreeMap<>());

            } else if (cur==')'){
                int mult = 0;
                while (i<f.length&&Character.isDigit(f[i])){
                    mult=mult*10+f[i]-'0';
                    i++;
                }
                mult = mult==0?1:mult;
                TreeMap<String, Integer> curMap = stacks.pop();
                TreeMap<String, Integer> preMap = stacks.peek();
                for(Map.Entry<String,Integer> entry: curMap.entrySet()){
                    int count = preMap.getOrDefault(entry.getKey(),0)+entry.getValue()*mult;
                    preMap.put(entry.getKey(), count);
                }

            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(cur);
                while (i<f.length&&(f[i]>='a'&&f[i]<='z')){
                    sb.append(f[i]);
                    i++;
                }
                int count = 0;
                while (i<f.length&&Character.isDigit(f[i])){
                    count=count*10+f[i]-'0';
                    i++;
                }

                count = count==0?1:count;
                stacks.peek().put(sb.toString(), count);
            }
        }
        TreeMap<String, Integer> retMap = stacks.pop();
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> entry: retMap.entrySet()){
            sb.append(entry.getKey());
            if(entry.getValue()>1) {
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countOfAtoms("H2O"));
    }
}
