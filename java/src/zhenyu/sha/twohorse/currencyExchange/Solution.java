package zhenyu.sha.twohorse.currencyExchange;
import java.util.*;
public class Solution {
    public static double maxTransactionRate(String[] currencies, double[][] exchangeRate, String target, String start) {
        int startIndex =0;
        int targetIndex =0;
        for(int i =0; i<currencies.length;i++) {
            if (currencies[i].equals(start)) {
                startIndex = i;
            }
            if (currencies[i].equals(target)) {
                targetIndex = i;
            }
        }

        return currencyExchange(exchangeRate,startIndex, targetIndex);

    }
    public static double currencyExchange(double[][] exchangeRate, int start, int end)
    {
        if(start == end) {
            return exchangeRate[start][start];
        }
        int n = exchangeRate.length;
        double best [][]= new double[n][(1<<n)-1];
        for(int i=0; i<n; i++) {
            Arrays.fill(best[i], Double.MIN_VALUE);
        }
        dfs(start,end, 0, exchangeRate, best);
        return best[start][0];

    }

    private static void dfs(int start, int end, int path,  double[][] exchangeRate, double[][] best) {

        if(best[start][path]==Double.MIN_VALUE) {
            double curBest = Double.MIN_VALUE;
            if (start == end ) {
                curBest = 1;
            } else {
                int n = exchangeRate.length;
                int newPath = setVisited(path, start);
                for(int i =0; i<n; i++) {
                    if  (!isVisited(path, i)) {
                        dfs(i, end, newPath, exchangeRate,best);
                        curBest = Math.max(curBest, best[i][newPath]*exchangeRate[start][i]);
                    }
                }
            }
            best[start][path] = curBest;
        }
    }
    private static int setVisited(int visited, int index) {
        int mask = 1 << index;
        return  visited | mask;
    }
    private static boolean isVisited(int visited, int index) {
        int mask = 1 << index;
        return (visited & mask) !=0;
    }
    public static void main(String[] args) {
        String[] currencies = new String[]{"USD","CAD","EUR","CNY"};
        double[][] table = new double[][]{{1,1.3,1,6.49},
                                            {0.72,1,0.9,5.5},
                                            {1.1,1.1,1,7.3},
                                            {0.18,0.2,0.136,1}};
        System.out.print(maxTransactionRate(currencies, table, "CAD","EUR"));

    }

}