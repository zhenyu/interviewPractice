package zhenyu.sha.twohorse.currencyExchange;

// Some code
//Currency Exchange
//calculate the max exchange rate with at most k steps (1,n-1) from c1 to currency c2
//max exchange rate for k+1 steps from c1 to c2 = Max(rate(c, c2)* max exchangerate from c1 to c in at most k steps, k steps c1 to c2) (c2 will be unused currency in previous k steps)
//rate1*rate2> originalRate

//memo[k, current] max exchenge rate from original currency to current currency with k steps
// = memo[k-1, whatever currency that not used]*rate(current/whatever currency), memo[k-1, current]
public class Solution {
    public static int targetCurr;
    public static double maxTransactionRate(String[] currencies, double[][] table, String target, String currencyAtHand){
        //TOOD
        return 0;

    }

    public static void main(String[] args) {
        String[] currencies = new String[]{"USD","CAD","EUR","CNY"};
        double[][] table = new double[][]{{1,1.3,1,6.49},{0.72,1,0.9,5.5},{1.1,1.1,1,7.3},{0.18,0.2,0.136,1}};
        System.out.print(maxTransactionRate(currencies, table, "CNY","USD"));

    }

}