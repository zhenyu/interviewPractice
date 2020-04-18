package zhenyu.sha.leetcode.q288;

import java.util.*;

class ValidWordAbbr {
    Map<String, Integer> abbrDic = new HashMap<>();
    Set<String> dic = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    public ValidWordAbbr(String[] dictionary) {
        abbrDic.clear();
        dic.clear();
        for(String word: dictionary){
            if(word.length()>=3) {
                dic.add(word);
                sb.setLength(0);
                String abbr= (sb.append(word.charAt(0)).append(word.length()-2).append(word.charAt(word.length()-1)).toString());
                abbrDic.put(abbr, abbrDic.getOrDefault(abbr, 0)+1);
            }
        }
    }
    public boolean isUnique(String word) {
        String abbr = word;
        if(word.length()<3)
            return true;
        sb.setLength(0);
        abbr= (sb.append(word.charAt(0)).append(word.length()-2).append(word.charAt(word.length()-1)).toString());
        return (null==abbrDic.get(abbr))||(dic.contains(word)&&(1==abbrDic.get(abbr)));
    }
}
