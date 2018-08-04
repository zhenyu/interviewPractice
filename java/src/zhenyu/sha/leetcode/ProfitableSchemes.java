package zhenyu.sha.leetcode;
import java.util.*;
public class ProfitableSchemes {
    private static final int M = 1000000007;
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int maxProfit = 0;
        int minProfit = Integer.MAX_VALUE;
        for(int p: profit){
            maxProfit+=p;
            minProfit = minProfit>p?p:minProfit;
        }

        int result = 0;

        List<Set<Integer>> schemas = new ArrayList<>();
        for(int i =0; i<G;i++){
            schemas.set(i, new HashSet<>());
        }

        for(int p= minProfit; p<=maxProfit;p++)
            for(int memberCount = G-1; memberCount>=0; memberCount--){
                //update schemes
                for(int schema = 0; schema < profit.length; schema++){

                }
            }
        }
        return result;
    }

}
