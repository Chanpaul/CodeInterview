package Interview;

/**
 * Created by yongyangyu on 2/23/15.
 */
public class MaxProfitKtimes {
    public static int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }
        // do as many transactions as possible
        if (k >= len / 2)
            return largeK(prices);
        // maxProfit[i][j]: max profit for i transactions in j days
        int [][] profit = new int[k+1][len];
        for (int i = 1; i <= k; i ++) {
            // tmp is max profit doing at most i-1 transactions using at most
            // first j-1 prices and buying stock at price[j]
            int tmp = profit[i-1][0] - prices[0];  // profit[i-1][0] is always 0
            for (int j = 1; j < len; j ++) {
                profit[i][j] = Math.max(profit[i][j-1], prices[j] + tmp);
                tmp = Math.max(tmp, profit[i-1][j-1] - prices[j]);
            }
        }
        return profit[k][len-1];
    }

    private static int largeK(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i ++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,4,5,6};
        System.out.println(maxProfit(2, prices));
    }
}
