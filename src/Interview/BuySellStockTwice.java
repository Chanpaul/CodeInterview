package Interview;

/**
 * Created by yongyangyu on 8/21/15.
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 */
public class BuySellStockTwice {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        // for day i, compute the max profit when selling a stock
        int[] sell = new int[n];
        // for day i, compute the max profit when buying a stock
        int[] buy = new int[n];

        int low = prices[0];
        for (int i = 1; i < n; i ++) {
            low = Math.min(low, prices[i]);
            sell[i] = Math.max(sell[i - 1], prices[i] - low);
        }

        int high = prices[n-1];
        for (int i = n - 2; i >= 0; i --) {
            high = Math.max(high, prices[i]);
            buy[i] = Math.max(buy[i], high - prices[i]);
        }
        int profit = 0;
        for (int i = 0; i < n; i ++) {
            profit = Math.max(profit, sell[i] + buy[i]);
        }
        return profit;
    }
}
