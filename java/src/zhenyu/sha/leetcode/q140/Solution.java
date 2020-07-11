package zhenyu.sha.leetcode.q140;
import java.util.*;
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new LinkedList<>();

        if(s!=null&&s.length()>0){
            int maxlen = 0;
            Set<String> dicts = new HashSet<>();
            for(String word : wordDict) {
                dicts.add(word);
                maxlen = Math.max(maxlen, word.length());
            }
            List<List<String>>  dp = new ArrayList<>(s.length()+1);
            for(int i =0; i< s.length()+1;i++){
                dp.add(new LinkedList<>());
            }
            List<String> empty = new LinkedList<>();
            empty.add("");
            dp.set(0, empty);
            for(int i=0; i< s.length();i++) {
                if(dp.get(i).size()>0){
                    for(int l=1; l<=maxlen&&i+l<=s.length();l++){
                        String curword = s.substring(i, i+l);
                        if(dicts.contains(curword)){
                            List<String> nextDp = dp.get(i+l);

                            for(String preSentence: dp.get(i)){
                                String newSetntence = preSentence+ ""+ curword;

                                nextDp.add(newSetntence.strip());
                            }
                            dp.set(i+l, nextDp);
                        }
                    }
                }
            }
            result = dp.get(s.length());
        }
        return result;
    }

    public static void main(String[] args) {

         String[] dic= new String[]{"cat","cats","and","sand","dog"};
         System.out.println(new Solution().wordBreak("catsanddog", Arrays.asList(dic)));
    }
}