package zhenyu.sha.leetcode.CoinChange;

class Solution {
    int []amounts ;
    public int coinChange(int[] coins, int amount) {
        amounts = new int[amount+1];
        for(int i = 0 ; i< amounts.length; i++) {
            amounts[i] = -2;
        }
        return inerChange(coins, amount);
    }

    private int inerChange(int[] coins, int amount) {
        if(amount<=0)
            return -1;
        if(amounts[amount+1] == -1) {
            return -1;
        } else if(amounts[amount+1] ==-2) {
            int count = -1;
            for(int coin: coins) {
                int num = inerChange(coins, amount-coin);
                if(num>0) {
                    count = num;
                }
            }
            amounts[amount+1] = count;
        }
        return amounts[amount+1];
    }
}